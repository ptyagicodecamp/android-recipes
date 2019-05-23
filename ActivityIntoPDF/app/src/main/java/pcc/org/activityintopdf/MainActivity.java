package pcc.org.activityintopdf;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    View inflatedLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String textStr = "L<h>A<h>TE";
        String markedSubStr = textStr.substring(textStr.indexOf("<h>") + "<h>".length());

        int startIndex = textStr.indexOf("<h>") + "<h>".length();
        int endIndex = startIndex + markedSubStr.indexOf("<h>");

        SpannableStringBuilder builder = new SpannableStringBuilder();

        SpannableString str1= new SpannableString(textStr.substring(0, textStr.indexOf("<h>")));
        str1.setSpan(new ForegroundColorSpan(Color.BLACK), 0, str1.length(), 0);
        builder.append(str1);

        SpannableString str2= new SpannableString(textStr.substring(startIndex, endIndex));
        str2.setSpan(new ForegroundColorSpan(Color.RED), 0, str2.length(), 0);
        builder.append(str2);

        SpannableString str3= new SpannableString(textStr.substring(endIndex + "<h>".length()));
        str3.setSpan(new ForegroundColorSpan(Color.BLACK), 0, str3.length(), 0);
        builder.append(str3);

        TextView textView = findViewById(R.id.hello);
        textView.setText( builder, TextView.BufferType.SPANNABLE);


//        LayoutInflater inflater = LayoutInflater.from(this);
//        inflatedLayout = inflater.inflate(R.layout.activity_main, null, false);
    }

//    public void saveIntoPDF(View view) {
//        final String path = getExternalFilesDir(Environment.DIRECTORY_PICTURES) + "/simple.jpg";
//        LinearLayout pdfView = (LinearLayout) inflatedLayout.findViewById(R.id.pdf);
//
//        pdfView.setDrawingCacheEnabled(true);
//        //pdfView.buildDrawingCache();
//        //Bitmap mBitmap=pdfView.getDrawingCache();
//
//        //dimensions for the view
//        pdfView.measure(View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED),
//                View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED));
//
//        pdfView.layout(0, 0, pdfView.getMeasuredWidth(), pdfView.getMeasuredHeight());
//
//        pdfView.buildDrawingCache(true);
//        Bitmap bitmap = Bitmap.createBitmap(pdfView.getDrawingCache());
//
//        try {
//            bitmap.compress(Bitmap.CompressFormat.JPEG,100,new FileOutputStream(new File(path)));
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        } finally {
//            pdfView.setDrawingCacheEnabled(false); // clear drawing cache
//        }
//    }


}
