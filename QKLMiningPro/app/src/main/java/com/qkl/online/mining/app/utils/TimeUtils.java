package com.qkl.online.mining.app.utils;

import android.content.Context;

import com.qkl.online.mining.app.R;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * <pre>
 *     author: Blankj
 *     blog  : http://blankj.com
 *     time  : 2016/08/02
 *     desc  : 时间相关工具类
 * </pre>
 */
public final class TimeUtils {

    private static final String DEFAULT_FORMAT = "yyyy-MM-dd HH:mm:ss";

    private TimeUtils() {
        throw new UnsupportedOperationException("u can't instantiate me...");
    }

    /**
     * 将时间戳转为时间字符串
     * <p>格式为yyyy-MM-dd HH:mm:ss</p>
     *
     * @param millis 毫秒时间戳
     * @return 时间字符串
     */
    public static String millis2String(final long millis) {
        Date date = new Date(millis * 1000);
        SimpleDateFormat format = new SimpleDateFormat(DEFAULT_FORMAT);
        return format.format(date);
    }

    public static String getToday() {
        Date date = new Date(System.currentTimeMillis());
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        return format.format(date);
    }

    /**
     * 时间戳转换天数
     * @param context
     * @param mss
     * @return
     */
    public static String formatDateTime(Context context, long mss) {
        try {
            long day = mss / (24 * 60 * 60 * 1000);
            long hour = (mss / (60 * 60 * 1000) - day * 24);
            long min = ((mss / (60 * 1000)) - day * 24 * 60 - hour * 60);
            long s = (mss / 1000 - day * 24 * 60 * 60 - hour * 60 * 60 - min * 60);

            if (day > 0) {
                return day + CommonsUtils.getXmlString(context, R.string.time_day);
            }
            if (hour > 0) {
                return hour + CommonsUtils.getXmlString(context, R.string.time_hour);
            }
            if (min > 0) {
                return min + CommonsUtils.getXmlString(context, R.string.time_min);
            }
            if (s > 0) {
                return s + CommonsUtils.getXmlString(context, R.string.time_ss);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "";
    }

}
