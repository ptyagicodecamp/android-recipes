package org.pcc.viewpagertemplates;

/**
 * Created by ptyagi on 6/21/17.
 */

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

public class ViewPagerActivity extends Activity {


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.viewpager);

        // Loop through the ids to create a list of full screen image views
        SlideAdapter slideAdapter = new SlideAdapter(this);
        List<ImageView> images = new ArrayList<ImageView>();

        for (int i = 0; i < slideAdapter.getCount(); i++) {
            ImageView imageView = new ImageView(this);
            imageView.setImageResource(slideAdapter.mThumbIds[i]);
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            images.add(imageView);
        }

        // Finally create the adapter
        ViewPagerAdapter imagePagerAdapter = new ViewPagerAdapter(images);
        ViewPager viewPager = (ViewPager) findViewById(R.id.view_pager);
        viewPager.setAdapter(imagePagerAdapter);

        // Set the ViewPager to point to the selected image from the previous activity
        // Selected image id
        int position = getIntent().getExtras().getInt("id");
        viewPager.setCurrentItem(position);
    }
}
