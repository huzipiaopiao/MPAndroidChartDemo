package com.ssdt.banbury.mympandroidchartdemo.widget;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.PointF;
import android.util.AttributeSet;
import android.view.MotionEvent;

import com.github.mikephil.charting.charts.LineChart;

/**
 * @author banbury
 * @version v1.0
 * @created 2018/1/26_16:02.
 * @description 解决MPAndroidChart的LineChart在ViewPager+Fragment滑动冲突解决
 */

public class LineChartInViewPager extends LineChart {

    PointF downPoint = new PointF();

    public LineChartInViewPager(Context context) {
        super(context);
    }

    public LineChartInViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public LineChartInViewPager(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public boolean onTouchEvent(MotionEvent evt) {
        switch (evt.getAction()) {
            case MotionEvent.ACTION_DOWN:
                downPoint.x = evt.getX();
                downPoint.y = evt.getY();
                break;
            case MotionEvent.ACTION_MOVE:
                if (getScaleX() > 1 && Math.abs(evt.getX() - downPoint.x) > 5) {
                    getParent().requestDisallowInterceptTouchEvent(true);
                }
                break;
        }
        return super.onTouchEvent(evt);
    }
}
