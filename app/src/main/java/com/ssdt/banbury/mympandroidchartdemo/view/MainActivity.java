package com.ssdt.banbury.mympandroidchartdemo.view;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.formatter.IValueFormatter;
import com.github.mikephil.charting.utils.ViewPortHandler;
import com.ssdt.banbury.mympandroidchartdemo.R;
import com.ssdt.banbury.mympandroidchartdemo.widget.CustomMarkerView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import tyrantgit.explosionfield.ExplosionField;

public class MainActivity extends AppCompatActivity {

    private List<Integer> randomData = new ArrayList<>();
    private List<String> randomXString = new ArrayList<>();
    private Random mRandom = new Random();
    private LineChart mChart;
    private ExplosionField mExplosionField;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mExplosionField = ExplosionField.attach2Window(this);
        initChart3();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.second:
                startActivity(new Intent(this,BigDataActivity.class));
                Toast.makeText(this, "激活一下", Toast.LENGTH_SHORT).show();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void initChart3() {
        mChart = (LineChart) findViewById(R.id.chart);
        mChart.setBackgroundColor(getArgb());
        Description description = new Description();
        description.setEnabled(true);
        description.setText("水印MPAndroidChart");
        description.setTextColor(getArgb());
        mChart.setDescription(description);
        mChart.setAutoScaleMinMaxEnabled(true);//缩放时，是否显示一个值
        Legend legend = mChart.getLegend();//获取左下角的那个图例，就是每条线会对应有个legend，根据legend的文字说明和颜色可以知道对应线是什么数据
        legend.setEnabled(true);
        legend.setForm(Legend.LegendForm.CIRCLE);//设置legend的头像样式
        legend.setHorizontalAlignment(Legend.LegendHorizontalAlignment.CENTER);//设置legend的位置
        legend.setTypeface(Typeface.MONOSPACE);//设置legend字体样式
        legend.setWordWrapEnabled(true);//legend太长时，是否另起一行

        //x轴设置动画了不好看，只设置y轴
        mChart.animateY(2000, Easing.EasingOption.EaseInOutCubic);//设置一个y方向的动画

        CustomMarkerView mv = new CustomMarkerView(this, R.layout.custom_marker_view_layout);
        mChart.setMarker(mv);

        IAxisValueFormatter formatter = new IAxisValueFormatter() {

            @Override
            public String getFormattedValue(float value, AxisBase axis) {
                return randomXString.get((int) value);
            }

        };

        IValueFormatter iValueFormatter = new IValueFormatter() {
            @Override
            public String getFormattedValue(float value, Entry entry, int dataSetIndex, ViewPortHandler viewPortHandler) {
                return "值:" + (int) entry.getY();
            }
        };

        XAxis xAxis = mChart.getXAxis();
        xAxis.setGranularity(1f); // 设置放大时，最小粒度
        xAxis.setValueFormatter(formatter);
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setDrawGridLines(false);
        mChart.getAxisRight().setEnabled(false);
        YAxis axisLeft = mChart.getAxisLeft();
        axisLeft.setGranularity(1f);//设置放大时，最小粒度
        axisLeft.setAxisMinimum(0);
        List<Entry> entries = new ArrayList<>();
        List<Entry> entries2 = new ArrayList<>();


        makeRandomData();
        for (int i = 0; i < randomData.size(); i++) {
            entries.add(new Entry(i, randomData.get(i)));
        }
        LineDataSet dataSet = new LineDataSet(entries, "数据一"); // add entries to dataset
        dataSet.setColor(getArgb());
        dataSet.setValueTextColor(getArgb());
        dataSet.setAxisDependency(YAxis.AxisDependency.LEFT);
        dataSet.setHighlightEnabled(true);//是否开启高亮线（触摸时显示）
        dataSet.setHighLightColor(getArgb());//设置高亮线的颜色
        dataSet.setMode(LineDataSet.Mode.LINEAR);//设置线的形状

        makeRandomData();
        for (int i = 0; i < randomData.size(); i++) {
            entries2.add(new Entry(i, randomData.get(i)));
        }
        LineDataSet dataSet2 = new LineDataSet(entries2, "数据二"); // add entries to dataset
        dataSet2.setColor(getArgb());
        dataSet2.setValueTextColor(getArgb());
        dataSet2.setAxisDependency(YAxis.AxisDependency.LEFT);
        dataSet2.setHighlightEnabled(true);//是否开启高亮线（触摸时显示）
        dataSet2.setHighLightColor(getArgb());//设置高亮线的颜色
        dataSet2.setMode(LineDataSet.Mode.CUBIC_BEZIER);//设置线的形状


        LineData lineData = new LineData(dataSet, dataSet2);
        lineData.setValueFormatter(iValueFormatter);
        mChart.setData(lineData);
        mChart.invalidate(); // refresh

        //下面为表创建好后，添加或删除数据需要进行的操作
//        // EXAMPLE 1
//        // add entries to the "data" object
//        exampleData.addEntry(...);
//        chart.notifyDataSetChanged(); // let the chart know it's data changed
//        chart.invalidate(); // refresh
//
//        // EXAMPLE 2
//        // add entries to "dataSet" object
//        dataSet.addEntry(...);
//        exampleData.notifyDataChanged(); // let the data know a dataSet changed
//        chart.notifyDataSetChanged(); // let the chart know it's data changed
//        chart.invalidate(); // refresh

        mChart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                mExplosionField.explode(mChart);
            }
        });

    }

    private int getArgb() {
        return Color.argb(mRandom.nextInt(256), mRandom.nextInt(256), mRandom.nextInt(256), mRandom.nextInt(256));
    }

    public void makeRandomData() {
        randomData.clear();
        int i = mRandom.nextInt(5) + 6;
        for (int i1 = 0; i1 < i; i1++) {
            randomData.add(mRandom.nextInt(120));
            randomXString.add(mRandom.nextInt(24) + ":" + mRandom.nextInt(61));
        }
    }
}
