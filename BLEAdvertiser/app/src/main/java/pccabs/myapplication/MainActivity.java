package pccabs.myapplication;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.le.AdvertiseCallback;
import android.bluetooth.le.AdvertiseData;
import android.bluetooth.le.AdvertiseSettings;
import android.bluetooth.le.BluetoothLeAdvertiser;
import android.bluetooth.le.BluetoothLeScanner;
import android.bluetooth.le.ScanCallback;
import android.bluetooth.le.ScanFilter;
import android.bluetooth.le.ScanResult;
import android.bluetooth.le.ScanSettings;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.ParcelUuid;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void startAdvertising(View view) {
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
            if( !BluetoothAdapter.getDefaultAdapter().isMultipleAdvertisementSupported() ) {
                Toast.makeText( this, "Bluetooth LE Advertising", Toast.LENGTH_SHORT ).show();
//GET A DEVICE THAT DOES SUPPORT MULTIPLE ADVERTISEMENT
                return;
            }

            //Get Advertiser
            BluetoothLeAdvertiser advertiser = BluetoothAdapter.getDefaultAdapter()
                    .getBluetoothLeAdvertiser();

            AdvertiseSettings settings = new AdvertiseSettings.Builder()
                    .setAdvertiseMode(AdvertiseSettings.ADVERTISE_MODE_LOW_LATENCY)
                    .setTxPowerLevel(AdvertiseSettings.ADVERTISE_TX_POWER_HIGH)
                    .setConnectable(false).build();

            ParcelUuid parcelUuid =
                    new ParcelUuid(UUID.fromString("BE8E50F8-8159-453E-9AB3-CC466CA0309D"));

            AdvertiseData advertiseData =
                    new AdvertiseData.Builder()
                            //.setIncludeDeviceName(true)
                            .addServiceUuid(parcelUuid)
                            .addServiceData(parcelUuid,
                                    "AdvertizeData".getBytes(Charset.forName("UTF-8")))
                            .build();

            AdvertiseCallback advertiseCallback = new AdvertiseCallback() {
                @Override
                public void onStartSuccess(AdvertiseSettings settingsInEffect) {
                    super.onStartSuccess(settingsInEffect);
                }

                @Override
                public void onStartFailure(int errorCode) {
                    super.onStartFailure(errorCode);
                }
            };

            advertiser.startAdvertising(settings, advertiseData, advertiseCallback);
        }
    }

    private ScanCallback scanCallback = new ScanCallback() {
        @Override
        public void onScanResult(int callbackType, ScanResult result) {
            super.onScanResult(callbackType, result);
            if (result == null
                    || result.getDevice() == null
                    || TextUtils.isEmpty(result.getDevice().getName()))
                return;
            StringBuilder builder = new StringBuilder(result.getDevice().getName());
            builder.append("\n").append(
                    new String(result.getScanRecord().getServiceData(
                            result.getScanRecord().getServiceUuids().get(0)),
                            Charset.forName("UTF-8")));
            TextView textView = (TextView)findViewById(R.id.hello_ble);
            textView.setText(builder.toString());
        }

        @Override
        public void onBatchScanResults(List<ScanResult> results) {
            super.onBatchScanResults(results);
        }

        @Override
        public void onScanFailed(int errorCode) {
            super.onScanFailed(errorCode);
        }
    };

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public void startScan(View view) {
        final BluetoothLeScanner bluetoothLeScanner =
                BluetoothAdapter.getDefaultAdapter().getBluetoothLeScanner();

        Handler handler = new Handler();

        ArrayList<ScanFilter> filters = new ArrayList<>();

        ScanFilter scanFilter =
                new ScanFilter.Builder()
                .setServiceUuid(new ParcelUuid(UUID.fromString("BE8E50F8-8159-453E-9AB3-CC466CA0309D")))
                .build();
        filters.add(scanFilter);

        ScanSettings scanSettings =
                new ScanSettings.Builder()
                .setScanMode(ScanSettings.SCAN_MODE_LOW_LATENCY)
                .build();

        bluetoothLeScanner.startScan(filters, scanSettings, scanCallback);

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                bluetoothLeScanner.stopScan(scanCallback);
            }
        }, 10000);
    }

}
