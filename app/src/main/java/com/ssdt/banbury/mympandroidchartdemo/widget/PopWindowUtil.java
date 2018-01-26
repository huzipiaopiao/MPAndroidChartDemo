package com.ssdt.banbury.mympandroidchartdemo.widget;

import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.PopupWindow;

import com.ssdt.banbury.mympandroidchartdemo.R;


/**
 * @author banbury
 * @version v1.0
 * @created 2017/12/29_9:43.
 * @description
 */
public class PopWindowUtil {
    private Context mContext;
    private String text;
    private View mInPopWindowView;
    private View mTheShowPopWindowView;
    private View.OnClickListener mDefaultClickListener;

    private boolean isNotDropDown =true;

    private boolean isWrapcontent =false;

    public PopWindowUtil setWrapcontent(boolean wrapcontent) {
        isWrapcontent = wrapcontent;
        return this;
    }


    public PopWindowUtil setNotDropDown(boolean notDropDown) {
        isNotDropDown = notDropDown;
        return this;
    }

    public PopWindowUtil(Context context) {
        mContext = context;
    }

    /**
     * 设置popwindow内text
     * @param text
     * @return
     */
    public PopWindowUtil setText(String text) {
        this.text = text;
        return this;
    }

    /**
     * 设置popwindow内的自定义的view
     * @param inPopWindowView
     * @return
     */
    public PopWindowUtil setInPopWindowView(View inPopWindowView) {
        mInPopWindowView = inPopWindowView;
        return this;
    }

    /**
     * 设置触发popwindow的view,主要是为了摆放popwindow的位置
     * @param theShowPopWindowView
     * @return
     */
    public PopWindowUtil setTheShowPopWindowView(View theShowPopWindowView) {
        mTheShowPopWindowView = theShowPopWindowView;
        return this;
    }

    /**
     * 设置默认的popwindow内view的点击事件
     * @param defaultClickListener
     * @return
     */
    public PopWindowUtil setDefaultClickListener(View.OnClickListener defaultClickListener) {
        mDefaultClickListener = defaultClickListener;
        return this;
    }

    /**
     * 显示popwindow
     * @return
     */
    public PopupWindow showPopWindow() {
        if (mContext == null) {
            throw new IllegalArgumentException("Builder必须设置mContext");
        }

        if (TextUtils.isEmpty(text)) {
            text = "删除";
        }

        if (mInPopWindowView == null) {
            mInPopWindowView = View.inflate(mContext, R.layout.em_row_delete_popwindow, null);
            Button btn = (Button) mInPopWindowView.findViewById(R.id.btn_delete);
            btn.setText(text);
            btn.setOnClickListener(mDefaultClickListener);
        }
        PopupWindow popupWindow = new PopupWindow(mInPopWindowView
//                , isWrapcontent? ViewGroup.LayoutParams.WRAP_CONTENT:mContext.getResources().getDimensionPixelSize(R.dimen.dp_120)
                , mContext.getResources().getDimensionPixelSize(R.dimen.dp_100)
                , isWrapcontent? ViewGroup.LayoutParams.WRAP_CONTENT:mContext.getResources().getDimensionPixelSize(R.dimen.dp_50)
                , true);
        popupWindow.setBackgroundDrawable(new BitmapDrawable());
        popupWindow.setOutsideTouchable(true);
        if (isNotDropDown) {
            popupWindow.showAsDropDown(mTheShowPopWindowView, mTheShowPopWindowView.getWidth() / 2, -30);
        }else {
            popupWindow.showAsDropDown(mTheShowPopWindowView);
        }
        return popupWindow;
    }
}
