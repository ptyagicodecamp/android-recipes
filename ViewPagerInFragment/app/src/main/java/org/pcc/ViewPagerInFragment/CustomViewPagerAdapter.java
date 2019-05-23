package org.pcc.ViewPagerInFragment;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ptyagi on 4/10/17.
 */

public class CustomViewPagerAdapter extends FragmentStatePagerAdapter {
    private List<Object> mItems;

    public CustomViewPagerAdapter(final FragmentManager fragmentManager) {
        super(fragmentManager);
        mItems = new ArrayList<>();
        mItems.add("A");
        mItems.add("B");
        mItems.add("C");
    }
    public CustomViewPagerAdapter(final FragmentManager fragmentManager, final List<Object> items) {
        super(fragmentManager);
        mItems = items;
    }

    protected Fragment getFragmentForItem(final Object item) {
        return (Fragment) item;
    }

    @Override
    public Fragment getItem(final int position) {
        final int itemsSize = mItems.size();
        if(position == 0) {
            return getFragmentForItem(mItems.get(itemsSize - 1));
        } else if(position == itemsSize + 1) {
            return getFragmentForItem(mItems.get(0));
        } else {
            return getFragmentForItem(mItems.get(position - 1));
        }
    }

    @Override
    public int getCount() {
        final int itemsSize = mItems.size();
        return itemsSize > 1 ? itemsSize + 2 : itemsSize;
    }

    public int getCountWithoutFakePages() {
        return mItems.size();
    }

    public void setItems(final List<Object> items) {
        mItems = items;
        notifyDataSetChanged();
    }
}
