package com.qkl.online.mining.app.utils;

import android.Manifest;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.RecyclerView;
import android.telephony.TelephonyManager;
import android.text.TextUtils;

import com.orhanobut.hawk.Hawk;
import com.qkl.online.mining.app.R;
import com.qkl.online.mining.app.application.AppContext;
import com.qkl.online.mining.app.utils.languagelib.MultiLanguageUtil;

import java.lang.reflect.Method;
import java.util.UUID;

/**
 * author：oyb on 2018/7/7 15:02
 */
public class CommonsUtils {

    public static int getXmlColor(int colorId) {
        return ContextCompat.getColor(AppContext.getInstance(), colorId);
    }

    /**
     * 获取xml资源字符串
     *
     * @param resId
     * @param context
     * @return
     */
    public static String getXmlString(Context context, int resId) {
        if(context == null) {
            context = AppContext.getInstance();
        }
        return context.getString(resId);
    }

    /**
     * 获取xml资源字符串
     *
     * @param resId
     * @param context
     * @param str
     * @return
     */
    public static String getXmlString(Context context, int resId, Object... str) {
        if(context == null) {
            context = AppContext.getInstance();
        }
        return context.getString(resId, str);
    }

    /**
     * 复制字符串
     * @param context
     * @param str
     */
    public static void copyStr(Context context, String str) {
        if (!TextUtils.isEmpty(str)) {
            ClipboardManager cm = (ClipboardManager) AppContext.getInstance().getSystemService(Context.CLIPBOARD_SERVICE);
            cm.setText(str);
            ToastUtils.showShort(CommonsUtils.getXmlString(context, R.string.invite_node_copy_success));
        }
    }

    public static void setDividerItemDecoration(RecyclerView recyclerView) {
        setDividerItemDecoration(recyclerView, 0);
    }

    public static void setDividerItemDecoration(RecyclerView recyclerView, int dividerColor) {
        if(dividerColor == 0) {
            dividerColor = R.color.division_line_color;
        }
        DividerItemDecoration divider = new DividerItemDecoration(AppContext.getInstance(), DividerItemDecoration.VERTICAL);
        divider.setDrawable(ContextCompat.getDrawable(AppContext.getInstance(), dividerColor));
        recyclerView.addItemDecoration(divider);
    }


    /**
     * 获取App名称
     *
     * @return App名称
     */
    public static String getAppName() {
        try {
            PackageManager pm = AppContext.getInstance().getPackageManager();
            PackageInfo pi = pm.getPackageInfo(AppContext.getInstance().getPackageName(), 0);
            return pi == null ? null : pi.applicationInfo.loadLabel(pm).toString();
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 获取应用的版本名称（用于显示给用户时使用）
     * 使用 x.yy.mmdd 格式, 如 1.12.0906
     *
     * @param context
     * @return
     */
    public static String getSoftVersionName(Context context) {
        String version = "1.0.0";
        PackageManager packageManager = context.getPackageManager();
        try {
            PackageInfo packageInfo = packageManager.getPackageInfo(context.getPackageName(), 0);
            version = packageInfo.versionName;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return version;
    }

    /**
     * 获取 移动终端设备id号
     *
     * @param context :上下文文本对象
     * @return id 移动终端设备id号
     */
    public static String getDevId(Context context) {
        String id = Hawk.get("devicesID", "");
        if (id.length() == 0) {
            try {
                if (ActivityCompat.checkSelfPermission(context, Manifest.permission.READ_PHONE_STATE) == PackageManager.PERMISSION_GRANTED) {
                    id = ((TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE)).getDeviceId();
                }
            } catch (Exception e) {
            }
            if (id == null)
                id = "";
        }
        if (id.length() == 0) {
            try {
                if (ActivityCompat.checkSelfPermission(context, Manifest.permission.READ_PHONE_STATE) == PackageManager.PERMISSION_GRANTED) {
                    id = ((TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE)).getSimSerialNumber();
                }
            } catch (Exception e) {
            }
            if (id == null)
                id = "";
        }
        if (id.length() == 0) {
            try {
                if (ActivityCompat.checkSelfPermission(context, Manifest.permission.READ_SMS) == PackageManager.PERMISSION_GRANTED
                        && ActivityCompat.checkSelfPermission(context, Manifest.permission.READ_PHONE_NUMBERS) != PackageManager.PERMISSION_GRANTED
                        && ActivityCompat.checkSelfPermission(context, Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
                    id = ((TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE)).getLine1Number();
                }
            } catch (Exception e) {
            }
            if (id == null)
                id = "";
        }
        if (id.length() == 0) {
            try {
                Class<?> c = Class.forName("android.os.SystemProperties");
                Method get = c.getMethod("get", String.class, String.class);
                id = (String) (get.invoke(c, "ro.serialno", "unknown"));
            } catch (Exception e) {
            }
        }
        if (id.length() == 0 || "0".equals(id)) {
            // 随机生成
            id = UUID.randomUUID().toString().replaceAll("-", "");
            Hawk.put("devicesID", id);
        }
        return id;
    }

    public static String getLanguage() {
        String language = "zh-TW";
        try {
            int languageType = MultiLanguageUtil.getInstance().getLanguageType();
            switch (languageType) {
                case 1:
                    language = "zh-CN";
                    break;
                case 2:
                    language = "zh-TW";
                    break;
                case 3:
                    language = "en";
                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return language;
    }

    /**
     * 我邀请的星球列表-传递需要单独把en改成en-us
     * @return
     */
    public static String getMyTeamLanguage() {
        String language = "zh-CN";
        int languageType = MultiLanguageUtil.getInstance().getLanguageType();
        switch (languageType) {
            case 1:
                language = "zh-CN";
                break;
            case 2:
                language = "zh-TW";
                break;
            case 3:
                language = "en-us";
                break;
        }
        return language;
    }

}
