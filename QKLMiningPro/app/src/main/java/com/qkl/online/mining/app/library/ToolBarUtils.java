package com.qkl.online.mining.app.library;

import android.content.Context;

import java.lang.reflect.Method;

/**
 * author：oyb on 2018/7/6 02:03
 */
public class ToolBarUtils {

    public static boolean isTempImmersion;

    /**    状态栏高度        */
    public static int stateBarHeight;

    /**    导航栏高度        */
    public static int navigationBarHeight;

    /**    是否为刘海屏幕手机        */
    public static boolean isNotchInScreen;

    /**
     * 判断是否有虚拟按键
     * @return
     */
    public static boolean isNavigationBarShow(){
        return navigationBarHeight > 0;
    }

    /**
     * 判断是否为齐刘海手机
     * @param context
     * @return
     */
    public static boolean isNotchInScreen(Context context) {
        boolean isNotchInScreen = false;
        String deviceBrand = android.os.Build.BRAND;

        if("huawei".equalsIgnoreCase(deviceBrand)) {
            // 华为手机
            isNotchInScreen = hasNotchInScreenAtHuawei(context);
        } else if("oppo".equalsIgnoreCase(deviceBrand)) {
            // Oppo手机
            isNotchInScreen = hasNotchInScreenAtOppo(context);
        } else if("vivo".equalsIgnoreCase(deviceBrand)) {
            // vivo
            isNotchInScreen = hasNotchInScreenAtVivo(context);
        } else if("xiaomi".equalsIgnoreCase(deviceBrand)) {
            isNotchInScreen = hasNotchInScreenAtXiaoMi(context);
        }
        return isNotchInScreen;
    }

    /**
     * 华为手机判断是否是刘海屏
     * @param context
     * @return
     */
    private static boolean hasNotchInScreenAtHuawei(Context context) {
        boolean ret = false;
        try {
            ClassLoader cl = context.getClassLoader();
            Class HwNotchSizeUtil = cl.loadClass("com.huawei.android.util.HwNotchSizeUtil");
            Method get = HwNotchSizeUtil.getMethod("hasNotchInScreen");
            ret = (boolean) get.invoke(HwNotchSizeUtil);
        } catch (ClassNotFoundException e) {
//            Log.e("test", "hasNotchInScreen ClassNotFoundException");
        } catch (NoSuchMethodException e) {
//            Log.e("test", "hasNotchInScreen NoSuchMethodException");
        } catch (Exception e) {
//            Log.e("test", "hasNotchInScreen Exception");
        } finally {
            return ret;
        }
    }

    /**
     * Oppo
     */
    public static boolean hasNotchInScreenAtOppo(Context context) {
        return context.getPackageManager().hasSystemFeature("com.oppo.feature.screen.heteromorphism");
    }

    /**
     * voio
     */
    public static final int NOTCH_IN_SCREEN_VOIO = 0x00000020;//是否有凹槽

    public static boolean hasNotchInScreenAtVivo(Context context) {
        boolean ret = false;
        try {
            ClassLoader cl = context.getClassLoader();
            Class FtFeature = cl.loadClass("android.util.FtFeature");
            Method get = FtFeature.getMethod("isFeatureSupport", int.class);
            ret = (boolean) get.invoke(FtFeature, NOTCH_IN_SCREEN_VOIO);
        } catch (ClassNotFoundException e) {
//            Log.e("test", "hasNotchInScreen ClassNotFoundException");
        } catch (NoSuchMethodException e) {
//            Log.e("test", "hasNotchInScreen NoSuchMethodException");
        } catch (Exception e) {
//            Log.e("test", "hasNotchInScreen Exception");
        } finally {
            return ret;
        }
    }

    /**
     * 小米
     * @param context
     * @return
     */
    public static boolean hasNotchInScreenAtXiaoMi(Context context) {
        boolean isNotchInScreen = false;

        try {
            int value = getInt(context, "ro.miui.notch", 0);
            isNotchInScreen = value == 1;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return isNotchInScreen;
    }

    /**
     * 根据给定的key返回int类型值.
     *
     * @param key 要查询的key
     * @param def 默认返回值
     * @return 返回一个int类型的值, 如果没有发现则返回默认值
     * @throws IllegalArgumentException 如果key超过32个字符则抛出该异常
     */
    public static Integer getInt(Context context, String key, int def) throws IllegalArgumentException {
        Integer ret = def;
        try {
            ClassLoader cl = context.getClassLoader();
            @SuppressWarnings("rawtypes")
            Class SystemProperties = cl.loadClass("android.os.SystemProperties");
            //参数类型
            @SuppressWarnings("rawtypes")
            Class[] paramTypes = new Class[2];
            paramTypes[0] = String.class;
            paramTypes[1] = int.class;
            Method getInt = SystemProperties.getMethod("getInt", paramTypes);
            //参数
            Object[] params = new Object[2];
            params[0] = new String(key);
            params[1] = new Integer(def);
            ret = (Integer) getInt.invoke(SystemProperties, params);
        } catch (IllegalArgumentException iAE) {
            throw iAE;
        } catch (Exception e) {
            ret = def;
        }
        return ret;
    }

}
