package org.pcc.ViewPagerInFragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class PagerFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {
        View result=inflater.inflate(R.layout.pager, container, false);
        ViewPager pager=(ViewPager)result.findViewById(R.id.pager);

        pager.setAdapter(buildAdapter());

        return(result);
    }

    private PagerAdapter buildAdapter() {
        return(new SimpleAdapter(getActivity(), getChildFragmentManager()));
    }
}