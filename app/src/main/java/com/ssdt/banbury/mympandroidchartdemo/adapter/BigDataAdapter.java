package com.ssdt.banbury.mympandroidchartdemo.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.ssdt.banbury.mympandroidchartdemo.view.RunStatsFragment;
import com.ssdt.banbury.mympandroidchartdemo.view.SenserStatsFragment;

/**
 * @author banbury
 * @version v1.0
 * @created 2018/1/23_15:21.
 * @description
 */

public class BigDataAdapter extends FragmentPagerAdapter {
    public BigDataAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new RunStatsFragment();
            case 1:
                return new SenserStatsFragment();
        }
        return null;
    }

    @Override
    public int getCount() {
        return 2;
    }
}
