package com.ssdt.banbury.mympandroidchartdemo.view;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.ssdt.banbury.mympandroidchartdemo.R;
import com.ssdt.banbury.mympandroidchartdemo.adapter.BigDataAdapter;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class BigDataActivity extends AppCompatActivity {

    @InjectView(R.id.vp)
    ViewPager mVp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_big_data);
        ButterKnife.inject(this);
        BigDataAdapter bigDataAdapter = new BigDataAdapter(getSupportFragmentManager());
        mVp.setAdapter(bigDataAdapter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
