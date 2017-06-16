package org.pcc.launcherscreen;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class LauncherActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Present user UI which uses data loaded in the background.
        startActivity(new Intent(this, MainActivity.class));
    }
}
