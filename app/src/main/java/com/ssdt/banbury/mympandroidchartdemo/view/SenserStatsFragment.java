package com.ssdt.banbury.mympandroidchartdemo.view;

import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.charts.PieChart;
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
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.formatter.IValueFormatter;
import com.github.mikephil.charting.formatter.LargeValueFormatter;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.github.mikephil.charting.utils.ViewPortHandler;
import com.ssdt.banbury.mympandroidchartdemo.R;
import com.ssdt.banbury.mympandroidchartdemo.widget.CustomDatePicker;
import com.ssdt.banbury.mympandroidchartdemo.widget.CustomMarkerView;
import com.ssdt.banbury.mympandroidchartdemo.widget.PopWindowUtil;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Random;
import java.util.TreeMap;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

/**
 * @author banbury
 * @version v1.0
 * @created 2018/1/23_15:28.
 * @description
 */

public class SenserStatsFragment extends Fragment {
    public static final String PROVIDED_BY = "Provided by盛世电梯大数据平台";
    @InjectView(R.id.tv_start_time_temperature)
    TextView mTvStartTimeTemperature;
    @InjectView(R.id.tv_end_time_temperature)
    TextView mTvEndTimeTemperature;
    @InjectView(R.id.tv_temperature_component)
    TextView mTvTemperatureComponent;
    @InjectView(R.id.line_chart_temperature)
    LineChart mLineChartTemperature;
    @InjectView(R.id.tv_start_time_humidity)
    TextView mTvStartTimeHumidity;
    @InjectView(R.id.tv_end_time_humidity)
    TextView mTvEndTimeHumidity;
    @InjectView(R.id.tv_humidity_component)
    TextView mTvHumidityComponent;
    @InjectView(R.id.line_chart_humidity)
    LineChart mLineChartHumidity;
    @InjectView(R.id.tv_start_time_senser_alarm)
    TextView mTvStartTimeSenserAlarm;
    @InjectView(R.id.tv_end_time_senser_alarm)
    TextView mTvEndTimeSenserAlarm;
    @InjectView(R.id.pie_chart_senser_alarm)
    PieChart mPieChartSenserAlarm;
    @InjectView(R.id.tv_start_time_component_alarm)
    TextView mTvStartTimeComponentAlarm;
    @InjectView(R.id.tv_end_time_component_alarm)
    TextView mTvEndTimeComponentAlarm;
    @InjectView(R.id.pie_chart_component_alarm)
    PieChart mPieChartComponentAlarm;
    @InjectView(R.id.tv_start_time_component_both)
    TextView mTvStartTimeComponentBoth;
    @InjectView(R.id.tv_end_time_component_both)
    TextView mTvEndTimeComponentBoth;
    @InjectView(R.id.bar_chart_component_both)
    BarChart mBarChartComponentBoth;
    private CustomDatePicker customDatePicker1;
    private CustomDatePicker customDatePicker2;
    private List<Integer> randomData = new ArrayList<>();
    private List<String> randomXString = new ArrayList<>();
    private Random mRandom = new Random();
    String[] temperatures = {"全部部件", "电机", "主板", "电缆", "轿厢", "井道"};
    String[] humidities = {"全部部件", "传动件", "桥架", "对重", "轿厢", "井道"};
    String[] componentBoth = {"电机", "主板", "电缆", "轿厢", "井道", "对重", "桥架", "传动件"};
    String[] senserAlarm = {"湿度传感器", "温度传感器", "震动传感器"};
    String[] componetAlarm = {"井道", "电缆", "轿厢"};
    private PopupWindow mTempPopupWindow;
    private PopupWindow mHumidityPopupWindow;
    private List<ILineDataSet> mTempLists = new ArrayList<>();//温度图表的数据集合
    private List<ILineDataSet> mHumidityLists = new ArrayList<>();//湿度图表的数据集合
    private CustomDatePicker customDatePicker3;
    private CustomDatePicker customDatePicker4;
    private CustomDatePicker customDatePicker5;
    private CustomDatePicker customDatePicker6;
    private CustomDatePicker customDatePicker7;
    private CustomDatePicker customDatePicker8;
    private CustomDatePicker customDatePicker9;
    private CustomDatePicker customDatePicker10;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_senser_stats, null);
        ButterKnife.inject(this, view);
        initDatePicker();
        initView();
        return view;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.second:
                initView();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * 设置时间
     */
    private void initDatePicker() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.CHINA);
        String now = sdf.format(new Date());

        mTvStartTimeTemperature.setText(now);
        mTvEndTimeTemperature.setText(now);
        customDatePicker1 = getDatePicker(now, mTvStartTimeTemperature);
        customDatePicker2 = getDatePicker(now, mTvEndTimeTemperature);

        mTvStartTimeHumidity.setText(now);
        mTvEndTimeHumidity.setText(now);
        customDatePicker3 = getDatePicker(now, mTvStartTimeHumidity);
        customDatePicker4 = getDatePicker(now, mTvEndTimeHumidity);

        mTvStartTimeComponentBoth.setText(now);
        mTvEndTimeComponentBoth.setText(now);
        customDatePicker5 = getDatePicker(now, mTvStartTimeComponentBoth);
        customDatePicker6 = getDatePicker(now, mTvEndTimeComponentBoth);

        mTvStartTimeSenserAlarm.setText(now);
        mTvEndTimeSenserAlarm.setText(now);
        customDatePicker7 = getDatePicker(now, mTvStartTimeSenserAlarm);
        customDatePicker8 = getDatePicker(now, mTvEndTimeSenserAlarm);

        mTvStartTimeComponentAlarm.setText(now);
        mTvEndTimeComponentAlarm.setText(now);
        customDatePicker9 = getDatePicker(now, mTvStartTimeComponentAlarm);
        customDatePicker10 = getDatePicker(now, mTvEndTimeComponentAlarm);


    }

    private CustomDatePicker getDatePicker(String now, final TextView textView) {
        //        customDatePicker1 = new CustomDatePicker(getActivity(), new CustomDatePicker.ResultHandler() {
//            @Override
//            public void handle(String time) { // 回调接口，获得选中的时间
//                mTvStartTimeTemperature.setText(time.split(" ")[0]);
//            }
//        }, "2010-01-01 00:00", now); // 初始化日期格式请用：yyyy-MM-dd HH:mm，否则不能正常运行
//        customDatePicker1.showSpecificTime(false); // 不显示时和分
//        customDatePicker1.setIsLoop(false); // 不允许循环滚动

        CustomDatePicker datePicker = new CustomDatePicker(getActivity(), new CustomDatePicker.ResultHandler() {
            @Override
            public void handle(String time) { // 回调接口，获得选中的时间
                textView.setText(time);
            }
        }, "2010-01-01 00:00", now); // 初始化日期格式请用：yyyy-MM-dd HH:mm，否则不能正常运行
        datePicker.showSpecificTime(true); // 显示时和分
        datePicker.setIsLoop(false); // 不允许循环滚动
        return datePicker;
    }

    private void initView() {
        mTempLists.clear();
        mHumidityLists.clear();
        setLineChart(mLineChartTemperature, temperatures, mTempLists, "温度");
        setLineChart(mLineChartHumidity, humidities, mHumidityLists, "湿度");
        setBarChart();
        setPieChart(mPieChartSenserAlarm, senserAlarm);
        setPieChart(mPieChartComponentAlarm, componetAlarm);
    }

    private void setBarChart() {
        Description desc = new Description();
        desc.setEnabled(true);
        desc.setText(PROVIDED_BY);
        desc.setTextColor(getArgb());
        mBarChartComponentBoth.setDescription(desc);
        mBarChartComponentBoth.setScaleEnabled(false);
        mBarChartComponentBoth.setTouchEnabled(false);

        Legend legend = mBarChartComponentBoth.getLegend();
        legend.setEnabled(true);
        legend.setForm(Legend.LegendForm.CIRCLE);//设置legend的头像样式
        legend.setHorizontalAlignment(Legend.LegendHorizontalAlignment.CENTER);//设置legend的位置
        legend.setTypeface(Typeface.MONOSPACE);//设置legend字体样式
        legend.setWordWrapEnabled(true);//legend太长时，是否另起一行

        //x轴设置动画了不好看，只设置y轴
        mBarChartComponentBoth.animateY(2000, Easing.EasingOption.EaseInOutCubic);//设置一个y方向的动画
        mBarChartComponentBoth.setDrawValueAboveBar(true);

//        CustomMarkerView mv = new CustomMarkerView(getActivity(), R.layout.custom_marker_view_layout);
//        mBarChartComponentBoth.setMarker(mv);


        //设置X轴lable样式
        IAxisValueFormatter formatter = new IAxisValueFormatter() {

            @Override
            public String getFormattedValue(float value, AxisBase axis) {
                return (int) value >= 0 && value < componentBoth.length ? componentBoth[(int) value] : "";
            }

        };

        //设置每个点上默认显示时的样式
        IValueFormatter iValueFormatter = new IValueFormatter() {
            @Override
            public String getFormattedValue(float value, Entry entry, int dataSetIndex, ViewPortHandler viewPortHandler) {
                return entry.getY() <= 0 ? "" : (int) entry.getY() + "℃";
            }
        };

        //设置每个点上默认显示时的样式
        IValueFormatter iValueFormatter2 = new IValueFormatter() {
            @Override
            public String getFormattedValue(float value, Entry entry, int dataSetIndex, ViewPortHandler viewPortHandler) {
                return entry.getY() <= 0 ? "" : (int) entry.getY() + "％RH";
            }
        };

        XAxis xAxis = mBarChartComponentBoth.getXAxis();
        xAxis.setGranularity(1);
        xAxis.setValueFormatter(formatter);
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setDrawGridLines(false);
        xAxis.setCenterAxisLabels(true);//有群组数据时，让lable居中
        xAxis.setAxisMinimum(0f);
        xAxis.setAxisMaximum(componentBoth.length);


        YAxis axisRight = mBarChartComponentBoth.getAxisRight();
        axisRight.setGranularity(1f);//设置放大时，最小粒度
        axisRight.setAxisMinimum(0);
        axisRight.setEnabled(false);

        YAxis axisLeft = mBarChartComponentBoth.getAxisLeft();
        axisLeft.setGranularity(1f);//设置放大时，最小粒度
        axisLeft.setAxisMinimum(0);
        axisLeft.setEnabled(true);
        axisLeft.setDrawAxisLine(false);
        axisLeft.setDrawLabels(false);
        axisLeft.setGridLineWidth(1);
        axisLeft.setGridColor(0xffe6e6e6);


        ArrayList<BarEntry> entryArrayList = new ArrayList<>();
        getBarEntry(entryArrayList, temperatures);
        BarDataSet tempBarDataSet = new BarDataSet(entryArrayList, "温度");
        tempBarDataSet.setColor(getArgb());
        tempBarDataSet.setValueFormatter(iValueFormatter);
        tempBarDataSet.setValueTextSize(10);

        ArrayList<BarEntry> entryArrayList2 = new ArrayList<>();
        getBarEntry(entryArrayList2, humidities);
        BarDataSet humiBarDataSet2 = new BarDataSet(entryArrayList2, "湿度");
        humiBarDataSet2.setColor(getArgb());
        humiBarDataSet2.setValueFormatter(iValueFormatter2);
        humiBarDataSet2.setValueTextSize(10);

        //一个group总值是1，下面的值groupSpace+barSpace*群内数据个数+barWidth*群内数据个数=1
        float groupSpace = 0.3f;
        float barSpace = 0.05f; // x2 dataset
        float barWidth = 0.3f; // x2 dataset

        BarData data = new BarData(tempBarDataSet, humiBarDataSet2);
        data.setBarWidth(barWidth);

        mBarChartComponentBoth.setData(data);
        data.groupBars(0, groupSpace, barSpace);
        mBarChartComponentBoth.invalidate();

    }

    @NonNull
    private void getBarEntry(ArrayList<BarEntry> entryArrayList, String[] indexs) {
        for (int i = 0; i < componentBoth.length; i++) {
            String s = componentBoth[i];
            boolean haveThisOne = false;
            for (String index : indexs) {
                if (s.equals(index)) {
                    haveThisOne = true;
                    break;
                }
            }
            if (haveThisOne) {
                entryArrayList.add(new BarEntry(i, mRandom.nextInt(120)));
            } else {
                entryArrayList.add(new BarEntry(i, 0));
            }
        }

    }

    private void setPieChart(PieChart pieChart, String[] alarm) {
        Description desc = new Description();
        desc.setEnabled(true);
        desc.setText(PROVIDED_BY);
        desc.setTextColor(getArgb());
        desc.setYOffset(20);
        pieChart.setDescription(desc);
        Legend legend = pieChart.getLegend();
        legend.setVerticalAlignment(Legend.LegendVerticalAlignment.TOP);
        legend.setHorizontalAlignment(Legend.LegendHorizontalAlignment.RIGHT);
        legend.setOrientation(Legend.LegendOrientation.VERTICAL);

        pieChart.setRotationEnabled(false);

        List<PieEntry> entryList = new ArrayList<>();
        entryList.add(new PieEntry(mRandom.nextInt(200), alarm[0]));
        entryList.add(new PieEntry(mRandom.nextInt(200), alarm[1]));
        entryList.add(new PieEntry(mRandom.nextInt(200), alarm[2]));

        PieDataSet mDataSet = new PieDataSet(entryList, "");
        mDataSet.setSliceSpace(1);//各数据间的饼图间隔
        mDataSet.setColors(getArgb(), getArgb(), getArgb());//各数据的颜色
        mDataSet.setValueTextSize(10);//值的字体大小
        mDataSet.setValueTextColor(getArgb());//值的颜色
        LargeValueFormatter valueFormatter = new LargeValueFormatter();
        valueFormatter.setAppendix("次");
        PercentFormatter percentFormatter = new PercentFormatter();
        mDataSet.setValueFormatter(percentFormatter);
        mDataSet.setYValuePosition(PieDataSet.ValuePosition.OUTSIDE_SLICE);//值显示的位置

        PieData data = new PieData(mDataSet);
        pieChart.setData(data);
        pieChart.setDrawEntryLabels(false);//是否在饼图上显示Lable
        pieChart.setUsePercentValues(true);//百分比显示
        pieChart.setOnChartValueSelectedListener(new OnChartValueSelectedListener() {
            @Override
            public void onValueSelected(Entry e, Highlight h) {
                Toast.makeText(getContext(), ((PieEntry) e).getLabel() + ":" + (int) e.getY() + "次", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected() {

            }
        });
        pieChart.animateY(2000);
        pieChart.invalidate();
    }

    /**
     * @param lineChart        要初始化的图表
     * @param components       图表上部件名称
     * @param lineDataSetLists 用于存放对应图表数据的集合
     * @param pointLable       点上要实现的内容
     */
    private void setLineChart(LineChart lineChart, String[] components, List<ILineDataSet> lineDataSetLists, String pointLable) {
        Description description = new Description();
        description.setEnabled(true);
        description.setText(PROVIDED_BY);
        description.setTextColor(getArgb());
        lineChart.setDescription(description);
        lineChart.setAutoScaleMinMaxEnabled(true);//缩放时，是否显示一个值

        Legend legend = lineChart.getLegend();//获取左下角的那个图例，就是每条线会对应有个legend，根据legend的文字说明和颜色可以知道对应线是什么数据
        legend.setEnabled(true);
        legend.setForm(Legend.LegendForm.CIRCLE);//设置legend的头像样式
        legend.setHorizontalAlignment(Legend.LegendHorizontalAlignment.CENTER);//设置legend的位置
        legend.setTypeface(Typeface.MONOSPACE);//设置legend字体样式
        legend.setWordWrapEnabled(true);//legend太长时，是否另起一行

        //x轴设置动画了不好看，只设置y轴
        lineChart.animateY(2000, Easing.EasingOption.EaseInOutCubic);//设置一个y方向的动画

        CustomMarkerView mv = new CustomMarkerView(getActivity(), R.layout.custom_marker_view_layout);
        lineChart.setMarker(mv);

        //设置X轴lable样式
        IAxisValueFormatter formatter = new IAxisValueFormatter() {

            @Override
            public String getFormattedValue(float value, AxisBase axis) {
                return value >= 0 && value < randomXdateMap2.size() ? getFormatDate(randomXdateMap2.get((int) value)) : "";
            }

        };

        //设置每个点上默认显示时的样式
        IValueFormatter iValueFormatter = new IValueFormatter() {
            @Override
            public String getFormattedValue(float value, Entry entry, int dataSetIndex, ViewPortHandler viewPortHandler) {
                return "" + (int) entry.getY();
            }
        };

        XAxis xAxis = lineChart.getXAxis();
        xAxis.setGranularity(1);
        xAxis.setValueFormatter(formatter);
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setDrawGridLines(false);
//        xAxis.setCenterAxisLabels(true);
//        xAxis.setLabelRotationAngle(15);
        xAxis.setAvoidFirstLastClipping(true);
        xAxis.setLabelCount(3);

        lineChart.getAxisRight().setEnabled(false);
        YAxis axisLeft = lineChart.getAxisLeft();
        axisLeft.setGranularity(1f);//设置放大时，最小粒度
        axisLeft.setAxisMinimum(0);
        axisLeft.setDrawAxisLine(false);


        List<Entry> tempEntries = null;
        for (int j = 0; j < components.length - 1; j++) {
            makeRandomDate();
            tempEntries = new ArrayList<>();

//            for (Map.Entry<Long, Integer> longIntegerEntry : randomXdateMap.entrySet()) {
//                tempEntries.add(new Entry(longIntegerEntry.getKey(), longIntegerEntry.getValue(), components[j + 1] + "\n时间:" + getFormatDate(longIntegerEntry.getKey()) + "\n" + pointLable + ":"));
//            }
            for (Map.Entry<Integer, Long> integerLongEntry : randomXdateMap2.entrySet()) {
                tempEntries.add(new Entry(integerLongEntry.getKey(), mRandom.nextInt(120), components[j + 1] + "\n时间:" + getFormatDate(integerLongEntry.getValue()) + "\n" + pointLable + ":"));
            }
            LineDataSet tempDataSet = new LineDataSet(tempEntries, components[j + 1]); // add entries to dataset
            tempDataSet.setColor(getArgb());
            tempDataSet.setAxisDependency(YAxis.AxisDependency.LEFT);
            tempDataSet.setHighlightEnabled(true);//是否开启高亮线（触摸时显示）
            tempDataSet.setHighLightColor(0xff000000);//设置高亮线的颜色
            tempDataSet.setMode(LineDataSet.Mode.LINEAR);//设置线的形状
            tempDataSet.setDrawCircles(false);
            tempDataSet.setDrawValues(false);
            lineDataSetLists.add(tempDataSet);
        }
        List<ILineDataSet> temp = new ArrayList<>();
        temp.addAll(lineDataSetLists);
        LineData mLineData = new LineData(temp);
        mLineData.setValueFormatter(iValueFormatter);
        lineChart.setData(mLineData);
        lineChart.setVisibleXRange(0, 20);

        lineChart.setDragYEnabled(false);
        lineChart.setDragXEnabled(true);
        lineChart.setScaleEnabled(false);

        lineChart.invalidate(); // refresh
    }

    private String getFormatDate(long date) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yy/MM/dd HH:mm");
        return simpleDateFormat.format(date);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }

    @OnClick({R.id.tv_start_time_temperature, R.id.tv_end_time_temperature, R.id.tv_temperature_component,
            R.id.tv_start_time_humidity, R.id.tv_end_time_humidity, R.id.tv_humidity_component,
            R.id.tv_start_time_component_both, R.id.tv_end_time_component_both,
            R.id.tv_start_time_senser_alarm, R.id.tv_end_time_senser_alarm,
            R.id.tv_start_time_component_alarm, R.id.tv_end_time_component_alarm})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_start_time_temperature:
                customDatePicker1.show(mTvStartTimeTemperature.getText().toString());
                break;
            case R.id.tv_end_time_temperature:
                customDatePicker2.show(mTvEndTimeTemperature.getText().toString());
                break;
            case R.id.tv_temperature_component:
                ListView listView = getListView(temperatures);
                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        mLineChartTemperature.clear();
                        if (position == 0) {
                            LineData mLineData = new LineData(mTempLists);
                            mLineChartTemperature.setData(mLineData);
                        } else {
                            LineData mLineData = new LineData(mTempLists.get(position - 1));
                            mLineChartTemperature.setData(mLineData);
                        }
                        mLineChartTemperature.animateY(1000);
                        mLineChartTemperature.invalidate(); // refresh
                        mTvTemperatureComponent.setText(temperatures[position]);
                        mTempPopupWindow.dismiss();
                    }
                });
                mTempPopupWindow = new PopWindowUtil(getContext())
                        .setInPopWindowView(listView)
                        .setTheShowPopWindowView(mTvTemperatureComponent)
                        .setNotDropDown(false)
                        .setWrapcontent(true)
                        .showPopWindow();
                break;
            case R.id.tv_start_time_humidity:
                customDatePicker3.show(mTvStartTimeHumidity.getText().toString());
                break;
            case R.id.tv_end_time_humidity:
                customDatePicker4.show(mTvEndTimeHumidity.getText().toString());
                break;
            case R.id.tv_humidity_component:
                ListView listView2 = getListView(humidities);
                listView2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        mLineChartHumidity.clear();
                        if (position == 0) {
                            LineData mLineData = new LineData(mHumidityLists);
                            mLineChartHumidity.setData(mLineData);
                        } else {
                            LineData mLineData = new LineData(mHumidityLists.get(position - 1));
                            mLineChartHumidity.setData(mLineData);
                        }
                        mLineChartHumidity.animateY(1000);
                        mLineChartHumidity.invalidate(); // refresh
                        mTvHumidityComponent.setText(humidities[position]);
                        mHumidityPopupWindow.dismiss();
                    }
                });
                mHumidityPopupWindow = new PopWindowUtil(getContext())
                        .setInPopWindowView(listView2)
                        .setTheShowPopWindowView(mTvHumidityComponent)
                        .setNotDropDown(false)
                        .setWrapcontent(true)
                        .showPopWindow();
                break;
            case R.id.tv_start_time_component_both:
                customDatePicker5.show(mTvStartTimeComponentBoth.getText().toString());
                break;
            case R.id.tv_end_time_component_both:
                customDatePicker6.show(mTvEndTimeComponentBoth.getText().toString());
                break;
            case R.id.tv_start_time_senser_alarm:
                customDatePicker7.show(mTvStartTimeSenserAlarm.getText().toString());
                break;
            case R.id.tv_end_time_senser_alarm:
                customDatePicker8.show(mTvEndTimeSenserAlarm.getText().toString());
                break;
            case R.id.tv_start_time_component_alarm:
                customDatePicker9.show(mTvStartTimeComponentAlarm.getText().toString());
                break;
            case R.id.tv_end_time_component_alarm:
                customDatePicker10.show(mTvEndTimeComponentAlarm.getText().toString());
                break;
        }
    }

    @NonNull
    private ListView getListView(String[] components) {
        ListView listView = new ListView(getContext());
        listView.setAdapter(new ArrayAdapter<>(getContext(), R.layout.row_popwindow, components));
        listView.setBackgroundColor(0xffffffff);
        return listView;
    }

    private int getArgb() {
        return Color.argb(255, mRandom.nextInt(256), mRandom.nextInt(256), mRandom.nextInt(256));
    }

    //    private Map<Long, Integer> randomXdateMap = new TreeMap<>();
    private Map<Integer, Long> randomXdateMap2 = new TreeMap<>();

    /**
     * 随机日期
     *
     * @return
     */
    public List<String> makeRandomDate() {
//        randomXdateMap.clear();
        randomXdateMap2.clear();
        long currentTimeMillis = System.currentTimeMillis();
        for (int i1 = 0; i1 < 100; i1++) {
//            randomXdateMap.put(currentTimeMillis - mRandom.nextInt(10 * 24 * 60 * 60 * 1000) + mRandom.nextInt(10 * 24 * 60 * 60 * 1000), mRandom.nextInt(120));
            randomXdateMap2.put(i1, currentTimeMillis - mRandom.nextInt(10 * 24 * 60 * 60 * 1000) + mRandom.nextInt(10 * 24 * 60 * 60 * 1000));
        }
        return randomXString;
    }

}
