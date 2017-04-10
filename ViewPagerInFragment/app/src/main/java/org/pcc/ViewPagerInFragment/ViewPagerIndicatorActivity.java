package org.pcc.ViewPagerInFragment;

/**
 * Created by ptyagi on 3/10/17.
 */

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

public class ViewPagerIndicatorActivity extends FragmentActivity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.replace_me,
                        new PagerFragment()).commit();
//        if (getSupportFragmentManager().findFragmentById(R.id.replace_me) != null) {
//            getSupportFragmentManager().beginTransaction()
//                    .replace(R.id.replace_me,
//                            new PagerFragment()).commit();
//        }

//        android.support.v4.app.FragmentManager fragmentManager = getSupportFragmentManager();
//        android.support.v4.app.FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
//        PagerFragment fragment = new PagerFragment();
//        fragmentTransaction.replace(R.id.fragment_container, fragment);
//        fragmentTransaction.commit();
    }
}