package com.qkl.online.mining.app.ui.view;

import android.content.Context;
import android.os.CountDownTimer;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;

import com.qkl.online.mining.app.R;
import com.qkl.online.mining.app.utils.CommonsUtils;

/**
 * author：oyb on 2018/9/1 22:33
 * 自定义倒计时View
 */
public class CountDownTextView extends AppCompatTextView {

    // 是否结束倒计时
    private boolean isCountDown;

    private int countTime = 60;

    // 定时器
    private CountDownTimer countDownTimer;

    public CountDownTextView(Context context) {
        super(context);
        init();
    }

    public CountDownTextView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public CountDownTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        countDownTimer = new CountDownTimer(60000, 1000) {
            @Override
            public void onTick(long l) {
                isCountDown = true;

                setText(countTime-- + CommonsUtils.getXmlString(getContext(), R.string.time_ss));
            }

            @Override
            public void onFinish() {
                isCountDown = false;

                setText(CommonsUtils.getXmlString(getContext(), R.string.register_get_code));
                countTime = 60;
            }
        };
    }

    /**
     * 是否倒计时
     * @return
     */
    public boolean isCountDown() {
        return isCountDown;
    }

    public void startTimer() {
        if(countDownTimer != null) {
            countDownTimer.start();
        }
    }

    public void stopTimer() {
        if(countDownTimer != null) {
            countDownTimer.cancel();
        }
    }

    public void onDestroy() {
        if(countDownTimer != null) {
            countDownTimer.cancel();
            countDownTimer = null;
        }
    }

}
