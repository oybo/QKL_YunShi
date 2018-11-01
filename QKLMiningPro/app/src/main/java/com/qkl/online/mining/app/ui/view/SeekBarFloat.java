package com.qkl.online.mining.app.ui.view;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.os.Build;
import android.support.v7.widget.AppCompatSeekBar;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.SeekBar;

import com.qkl.online.mining.app.R;

import java.text.DecimalFormat;

/**
 * Created by yao.fu on 6/23/17.
 * Email: lookfuyao@gmail.com
 * An enhanced version of the seekBar can support negative numbers,
 *      floating point numbers (maximum precision is 4 bits).
 * Max Range(-21474 - 21474)
 * For example, 1:
 * Range (-1000.2323232f, 9999.3434343f)
 * For example 2:
 * Range (500f, 8888f)
 */
public class SeekBarFloat extends AppCompatSeekBar {

    private float mAccuracy = 10000f;
    private float mMin = 0f;
    private float mMax = 100f;

    public SeekBarFloat(Context context) {
        super(context);
    }

    public SeekBarFloat(Context context, AttributeSet attrs) {
        super(context, attrs);
        initValue(context, attrs);
    }

    public SeekBarFloat(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initValue(context, attrs);
    }

    private void initValue(Context context, AttributeSet attrs) {
        TypedArray tArray = context.obtainStyledAttributes(attrs, R.styleable.seekBarFloat);
        float min = tArray.getFloat(R.styleable.seekBarFloat_seekBarFloat_min, 0f);
        float max = tArray.getFloat(R.styleable.seekBarFloat_seekBarFloat_max, 100f);
        float default_value = tArray.getFloat(R.styleable.seekBarFloat_seekBarFloat_default_value, 0);
        tArray.recycle();
        setRangeF(min, max);
        if(default_value < mMin || default_value > mMax) {
            default_value = mMin;
        }
        setProgressF(default_value);
    }

    public String getSHValue() {
        String value = new DecimalFormat("##0.00").format(getProgressF() / getMaxF() * 100) + "%";
        return value;
    }

    private OnSeekBarChangeListener mOnSeekBarChangeListenerEx;

    SeekBar.OnSeekBarChangeListener mOnSeekBarChangeListener;

    /**
     * don't use it, use seekBarFloat#setSecondaryProgressF instead
     * Sets the seekBar's secondary progress value
     */
    @Override
    public synchronized void setSecondaryProgress(int secondaryProgress) {
        super.setSecondaryProgress(secondaryProgress);
    }

    /**
     * Sets the seekBar's secondary progress value
     * @param secondaryProgress the new secondary progress, between 0 and {@link #getMax()}
     * can be negative number and float number
     */
    public synchronized void setSecondaryProgressF(float secondaryProgress) {
        int coverProgress = Math.abs((int) ((secondaryProgress - mMin) * mAccuracy));
        super.setSecondaryProgress(coverProgress);
    }

    /**
     * get the seekBar's secondary progress value
     * @return the secondary progress, between {@link #getMinF()} ()} and {@link #getMaxF()}
     * can be negative number and float number
     */
    public synchronized float getSecondaryProgressF() {
        int progress = super.getSecondaryProgress();
        return (mMax - mMin == 0 || super.getMax() == 0) ? 0 : (((float)progress / (float)super.getMax()) * (mMax - mMin) + mMin);
    }

    /**
     * Don't use it, use {@link SeekBarFloat#getMaxF()} instead
     * you cann't get right value if you call it
     */
    @Override
    public synchronized int getMax() {
        return super.getMax();
    }

    /**
     * Don't use it, use {@link SeekBarFloat#getSecondaryProgressF} instead
     * get the seekBar's secondary progress value
     */
    @Override
    public synchronized int getSecondaryProgress() {
        return super.getSecondaryProgress();
    }

    /**
     * Don't use it, use {@link SeekBarFloat#setProgressF} instead
     * Sets the current progress to the specified value, optionally animating
     * the visual position between the current and target values.
     * <p>
     * @param progress the new progress value, between {@link #getMinF()} and {@link #getMaxF()}
     */
    @Override
    public synchronized void setProgress(int progress) {
        super.setProgress(progress);
    }

    /**
     * see {@link SeekBarFloat#setProgressF(int progress, boolean animate)}
     * Sets the current progress to the specified value, optionally animating
     * the visual position between the current and target values.
     * <p>
     * @param progress the new progress value, between {@link #getMinF()} and {@link #getMaxF()}
     *                 can be negative number and float number
     */
    public synchronized void setProgressF(float progress) {
        int coverProgress = Math.abs((int) ((progress - mMin) * mAccuracy));
        super.setProgress(coverProgress);
    }

    /**
     * Don't use it, use {@link SeekBarFloat#setProgressF} instead
     * Sets the current progress to the specified value, optionally animating
     * the visual position between the current and target values.
     * <p>
     * @param progress the new progress value, between {@link #getMinF()} and {@link #getMaxF()}
     *                 can be negative number and float number
     * @param animate {@code true} to animate between the current and target
     *                values or {@code false} to not animate
     */
    @Override
    public void setProgress(int progress, boolean animate) {
        super.setProgress(progress, animate);
    }

    /**
     * Sets the current progress to the specified value, optionally animating
     * the visual position between the current and target values.
     * <p>
     * Animation does not affect the result of {@link #getProgress()}, which
     * will return the target value immediately after this method is called.
     *
     * @param progress the new progress value, between {@link #getMinF()} and {@link #getMaxF()}
     *                 can be negative number and float number
     * @param animate {@code true} to animate between the current and target
     *                values or {@code false} to not animate
     */
    @TargetApi(Build.VERSION_CODES.N)
    public void setProgressF(int progress, boolean animate) {
        int coverProgress = Math.abs((int) ((progress - mMin) * mAccuracy));
        super.setProgress(coverProgress, animate);
    }

    /**
     * Don't use it, use {@link SeekBarFloat#getProgressF} instead
     */
    @Override
    public synchronized int getProgress() {
        return super.getProgress();
    }

    /**
     * <p>Get the progress bar's current level of progress</p>
     *
     * @return the current progress, between {@link #getMinF()} ()} and {@link #getMaxF()}
     * can be negative number and float number
     */
    public synchronized float getProgressF() {
        int progress = super.getProgress();
        return (mMax - mMin == 0 || super.getMax() == 0) ? 0 : (((float)progress / (float)super.getMax()) * (mMax - mMin) + mMin);
    }

    /**
     * get the seekBar's max value
     * @return can be negative number and float number
     */
    public synchronized float getMaxF() {
        return mMax;
    }

    /**
     * get the seekBar's min value
     * @return can be negative number and float number
     */
    public synchronized float getMinF() {
        return mMin;
    }

    /**
     * Don't use it, use {@link SeekBarFloat#setMaxF} instead
     * Sets the seekBar's max value
     * @param max
     * will auto correct max & min if max < min
     */
    @Override
    public synchronized void setMax(int max) {
        super.setMax(max);
    }

    /**
     * Sets the seekBar's max value
     * @param max max can be negative number and float number
     * will auto correct max & min if max < min
     */
    public synchronized void setMaxF(float max) {
        setRangeF(mMin, max);
    }

    /**
     * Sets the seekBar's min value
     * @param min min can be negative number and float number
     * will auto correct max & min if max < min
     */
    public synchronized void setMinF(float min) {
        setRangeF(min, mMax);
    }

    /**
     * Sets the seekBar's min value and max value
     * @param min min can be negative number and float number
     * @param max min can be negative number and float number
     * will auto correct max & min if max < min
     */
    public synchronized void setRangeF(float min, float max) {
        mMin = Math.min(min, max);
        mMax = Math.max(min, max);
        int range = Math.abs((int) ((mMax - mMin) * mAccuracy));
        super.setMax(range);
    }

    /**
     * Sets a listener to receive notifications of changes to the SeekBar's progress level. Also
     * provides notifications of when the user starts and stops a touch gesture within the SeekBar.
     *
     * @param l The seek bar notification listener
     *
     * @see OnSeekBarChangeListener
     */
    public void setOnSeekBarChangeListener(OnSeekBarChangeListener l) {
        mOnSeekBarChangeListenerEx = l;
        mOnSeekBarChangeListener = new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if (mOnSeekBarChangeListenerEx != null) {
                    mOnSeekBarChangeListenerEx.onProgressChanged(SeekBarFloat.this, getProgressF(), fromUser);
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                if (mOnSeekBarChangeListenerEx != null) {
                    mOnSeekBarChangeListenerEx.onStartTrackingTouch(SeekBarFloat.this);
                }
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                if (mOnSeekBarChangeListenerEx != null) {
                    mOnSeekBarChangeListenerEx.onStopTrackingTouch(SeekBarFloat.this);
                }
            }
        };
        setOnSeekBarChangeListener(mOnSeekBarChangeListener);
    }

    public interface OnSeekBarChangeListener {

        /**
         * Notification that the progress level has changed. Clients can use the fromUser parameter
         * to distinguish user-initiated changes from those that occurred programmatically.
         *
         * @param seekBarFloat The seekBarFloat whose progress has changed
         * @param progress The current progress value, can be float or negative.
         * @param fromUser True if the progress change was initiated by the user.
         */
        void onProgressChanged(SeekBarFloat seekBarFloat, float progress, boolean fromUser);

        /**
         * Notification that the user has started a touch gesture. Clients may want to use this
         * to disable advancing the seekBarFloat.
         * @param seekBarFloat The SeekBar in which the touch gesture began
         */
        void onStartTrackingTouch(SeekBarFloat seekBarFloat);

        /**
         * Notification that the user has finished a touch gesture. Clients may want to use this
         * to re-enable advancing the seekbar.
         * @param seekBarFloat The seekBarFloat in which the touch gesture began
         */
        void onStopTrackingTouch(SeekBarFloat seekBarFloat);
    }

    private boolean isTouch;

    public void setTouchEnable(boolean isTouch) {
        this.isTouch = isTouch;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if(!isTouch) {
            return true;
        }
        return super.onTouchEvent(event);
    }

}
