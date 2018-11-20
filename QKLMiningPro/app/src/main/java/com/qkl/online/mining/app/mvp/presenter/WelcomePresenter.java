package com.qkl.online.mining.app.mvp.presenter;

import android.Manifest;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

import com.qkl.online.mining.app.application.AccountManager;
import com.qkl.online.mining.app.library.ToolBarUtils;
import com.qkl.online.mining.app.library.bar.ImmersionBar;
import com.qkl.online.mining.app.mvp.view.IWelcomeView;
import com.qkl.online.mining.app.utils.IntentUtil;

import java.util.ArrayList;
import java.util.List;

/**
 *
 */

public class WelcomePresenter extends BasePresenter<IWelcomeView> {

    public static final int REQUEST_PERMISSION_CODE = 1024;
    public static final int SET_REQUEST_PERMISSION_CODE = 1025;

    private Handler mHandler;
    private Activity mContext;
    private closeRunnable runnable;

    public WelcomePresenter(Activity context, IWelcomeView view) {
        super(context, view);
        mHandler = new Handler(context.getMainLooper());
        mContext = context;
        runnable = new closeRunnable();

        ToolBarUtils.stateBarHeight = ImmersionBar.getStatusBarHeight(context);
        ToolBarUtils.navigationBarHeight = ImmersionBar.getNavigationBarHeight(context);
        ToolBarUtils.isNotchInScreen = ToolBarUtils.isNotchInScreen(context);
    }

    //检查SD卡读写权限
    @TargetApi(Build.VERSION_CODES.M)
    public void checkPermission(AppCompatActivity activity) {
        List<String> lackedPermission = new ArrayList<String>();
        if (!(activity.checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED)) {
            lackedPermission.add(Manifest.permission.READ_EXTERNAL_STORAGE);
        }
        if (!(activity.checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED)) {
            lackedPermission.add(Manifest.permission.WRITE_EXTERNAL_STORAGE);
        }
        if (!(activity.checkSelfPermission(Manifest.permission.READ_PHONE_STATE) == PackageManager.PERMISSION_GRANTED)) {
            lackedPermission.add(Manifest.permission.READ_PHONE_STATE);
        }

        // 权限都已经有了，那么走广告接口
        if (lackedPermission.size() == 0) {
            init();
        } else {
            // 请求所缺少的权限，在onRequestPermissionsResult中再看是否获得权限，如果获得权限就可以调用SDK，否则不要调用SDK。
            String[] requestPermissions = new String[lackedPermission.size()];
            lackedPermission.toArray(requestPermissions);
            activity.requestPermissions(requestPermissions, REQUEST_PERMISSION_CODE);
        }
    }

    /**
     * 初始化
     */
    public void init() {
        // 系统配置字典接口 - 用在客服页面联系邮箱
        AccountManager.getInstance().getCommonsConfig();
        AccountManager.getInstance().getYunPlanetConfig(null);
        mHandler.postDelayed(runnable, 2000);
    }

    class closeRunnable implements Runnable {

        @Override
        public void run() {
            IntentUtil.ToLoginActivity(mContext);
            mContext.finish();
        }
    }

    public void onDestory() {
        mHandler.removeCallbacks(runnable);
    }

}
