package com.qkl.online.mining.app.listener;

import android.view.View;

import java.util.Calendar;

/**
 * Created by O on 2017/6/26.
 * 重写点击事件，防止多次点击
 */

public abstract class NoDoubleClickListener implements View.OnClickListener {

    public static int MIN_CLICK_DELAY_TIME = 500;
    private long lastClickTime = 0;

    @Override
    public void onClick(View v) {
        int timeOut = MIN_CLICK_DELAY_TIME;

        long currentTime = Calendar.getInstance().getTimeInMillis();
        if (currentTime - lastClickTime > timeOut) {
            lastClickTime = currentTime;
            onNoDoubleClick(v);
        }
    }

    protected abstract void onNoDoubleClick(View v);

}