package com.qkl.online.mining.app.ui.activity;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.widget.ImageView;

import com.orhanobut.hawk.Hawk;
import com.qkl.online.mining.app.R;
import com.qkl.online.mining.app.data.commons.Constants;
import com.qkl.online.mining.app.listener.OnDialogCancelListenner;
import com.qkl.online.mining.app.listener.OnDialogOkListenner;
import com.qkl.online.mining.app.mvp.presenter.WelcomePresenter;
import com.qkl.online.mining.app.mvp.view.IWelcomeView;
import com.qkl.online.mining.app.ui.BaseActivity;
import com.qkl.online.mining.app.ui.view.DialogTips;
import com.qkl.online.mining.app.utils.CommonsUtils;
import com.qkl.online.mining.app.utils.glide.GlideImageLoader;
import com.qkl.online.mining.app.utils.languagelib.MultiLanguageUtil;

import butterknife.BindView;

/**
 * 欢迎页面
 */

public class WelComeActivity extends BaseActivity<WelcomePresenter> implements IWelcomeView {

    @BindView(R.id.welcome_bg_imageview)
    ImageView mImageView;

    @Override
    protected void initPresenter(Intent intent) {
        mPresenter = new WelcomePresenter(this,this);
    }

    @Override
    protected boolean isTransparencyStatusBar() {
        return true;
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_welcome;
    }

    @Override
    protected void initView() {
        String path = Hawk.get(Constants.WELCOME_BG_IMAGEVIEW_PATH_KEY, "");
        if(!TextUtils.isEmpty(path)) {
            GlideImageLoader.loadImage(this, path, mImageView);
        } else {
            GlideImageLoader.loadImage(this, R.drawable.weather_bg, mImageView);
        }
    }

    @Override
    protected void initData() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            //首先检查相应的权限
            mPresenter.checkPermission(this);
        } else {
            mPresenter.init();
        }

        // 默认初始化繁体
        MultiLanguageUtil.getInstance().updateLanguage(MultiLanguageUtil.getInstance().getLanguageType());
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == WelcomePresenter.SET_REQUEST_PERMISSION_CODE) {
            mPresenter.checkPermission(WelComeActivity.this);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == WelcomePresenter.REQUEST_PERMISSION_CODE && hasAllPermissionsGranted(grantResults)) {
            mPresenter.init();
        } else {
            // 如果用户没有授权，那么应该说明意图，引导用户去设置里面授权。
            DialogTips dialogTips = new DialogTips(WelComeActivity.this);
            dialogTips.setTitle(getXmlString(R.string.permissions_title));
            dialogTips.setMessage(getXmlString(R.string.permissions_message));
            dialogTips.setCanceledOnTouchOutside(false);
            dialogTips.setCancelable(false);
            dialogTips.setOkListenner(getXmlString(R.string.permissions_get_txt), new OnDialogOkListenner() {
                @Override
                public void onClick() {
                    Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                    intent.setData(Uri.parse("package:" + getPackageName()));
                    startActivity(intent);
                    startActivityForResult(intent, WelcomePresenter.SET_REQUEST_PERMISSION_CODE);
                }
            });
            dialogTips.setCancelListenner(getXmlString(R.string.permissions_exit_txt), new OnDialogCancelListenner() {
                @Override
                public void onClick() {
                    finish();
                }
            });
            dialogTips.show();
        }
    }

    private static boolean hasAllPermissionsGranted(int[] grantResults) {
        int len = grantResults.length;
        for (int i = 0; i < len; i++) {
            int grantResult = grantResults[i];
            if ((i == 0 || i == 1 || i == 2) && grantResult == PackageManager.PERMISSION_DENIED) {
                return false;
            }
        }
        return true;
    }

    @Override
    public void onBackPressed() {
//        super.onBackPressed();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if ((getIntent().getFlags() & Intent.FLAG_ACTIVITY_BROUGHT_TO_FRONT) != 0) {
            return;
        }
        mPresenter.onDestory();
    }

}
