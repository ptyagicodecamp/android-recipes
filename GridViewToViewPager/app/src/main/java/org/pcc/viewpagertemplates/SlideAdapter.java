package org.pcc.viewpagertemplates;

/**
 * Created by ptyagi on 6/21/17.
 */
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

public class SlideAdapter extends BaseAdapter {
    private Context mContext;

    // Keep all Images in array
    Integer[] mThumbIds = {
            R.mipmap.ic_launcher, R.mipmap.ic_launcher_round,
            R.mipmap.ic_launcher, R.mipmap.ic_launcher_round,
            R.mipmap.ic_launcher, R.mipmap.ic_launcher_round,
            R.mipmap.ic_launcher, R.mipmap.ic_launcher_round

    };

    // Constructor
    public SlideAdapter(Context c){
        mContext = c;

        //TODO: READ Slide data from repo
    }

    @Override
    public int getCount() {
        return mThumbIds.length;
    }

    @Override
    public Object getItem(int position) {
        return mThumbIds[position];
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView imageView = new ImageView(mContext);
        imageView.setImageResource(mThumbIds[position]);
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        imageView.setLayoutParams(new GridView.LayoutParams(265, 265));
        return imageView;
    }

}
