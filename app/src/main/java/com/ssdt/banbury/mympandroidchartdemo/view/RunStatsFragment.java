package com.ssdt.banbury.mympandroidchartdemo.view;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.ssdt.banbury.mympandroidchartdemo.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * @author banbury
 * @version v1.0
 * @created 2018/1/23_15:25.
 * @description
 */

public class RunStatsFragment extends Fragment {

    @InjectView(R.id.bar_chart)
    BarChart mChart;
    private List<Integer> randomData = new ArrayList<>();
    private List<String> randomXString = new ArrayList<>();
    private Random mRandom = new Random();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_run_stats, null);
        ButterKnife.inject(this, view);
        initView();
        return view;
    }

    private void initView() {
        Description description = new Description();
        description.setEnabled(false);
        mChart.setDescription(description);

        Legend legend = mChart.getLegend();//获取左下角的那个图例，就是每条线会对应有个legend，根据legend的文字说明和颜色可以知道对应线是什么数据
        legend.setEnabled(false);

        //x轴设置动画了不好看，只设置y轴
        mChart.animateY(2000, Easing.EasingOption.EaseInOutCubic);//设置一个y方向的动画
        mChart.setScaleEnabled(false);//不允许缩放

        IAxisValueFormatter formatter = new IAxisValueFormatter() {

            @Override
            public String getFormattedValue(float value, AxisBase axis) {
                return randomXString.get((int) value);
            }

        };
        XAxis xAxis = mChart.getXAxis();
        xAxis.setGranularity(1f); // 设置放大时，最小粒度
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setValueFormatter(formatter);
        xAxis.setDrawGridLines(false);
        xAxis.setLabelCount(10);//标签数量

        mChart.getAxisRight().setEnabled(false);
        YAxis axisLeft = mChart.getAxisLeft();
        axisLeft.setEnabled(false);
        axisLeft.setAxisMinimum(0);



        makeRandomData();

        List<BarEntry> entries = new ArrayList<>();

        for (int i = 0; i < randomData.size(); i++) {
            entries.add(new BarEntry(i, randomData.get(i)));
        }
        BarDataSet dataSet = new BarDataSet(entries, "数据一"); // add entries to dataset
        dataSet.setColor(getArgb());
        dataSet.setValueTextColor(getArgb());
        dataSet.setAxisDependency(YAxis.AxisDependency.LEFT);
        dataSet.setHighlightEnabled(true);//是否开启高亮线（触摸时显示）
        dataSet.setHighLightColor(getArgb());//设置高亮线的颜色

        BarData barData = new BarData(dataSet);
        mChart.setData(barData);
        mChart.invalidate(); // refresh
    }

    public void makeRandomData() {
        randomData.clear();
        for (int i1 = 1; i1 < 11; i1++) {
            randomData.add(mRandom.nextInt(12000));
            randomXString.add(i1 + "楼");
        }
    }

    private int getArgb() {
        return Color.argb(255, mRandom.nextInt(256), mRandom.nextInt(256), mRandom.nextInt(256));
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }
}
