package com.qkl.online.mining.app.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.qkl.online.mining.app.R;
import com.qkl.online.mining.app.application.AccountManager;
import com.qkl.online.mining.app.data.commons.Constants;
import com.qkl.online.mining.app.data.entity.User;
import com.qkl.online.mining.app.data.event.EventBase;
import com.qkl.online.mining.app.listener.NoDoubleClickListener;
import com.qkl.online.mining.app.mvp.presenter.PublicPresenter;
import com.qkl.online.mining.app.mvp.view.IPublicView;
import com.qkl.online.mining.app.ui.BaseActivity;
import com.qkl.online.mining.app.ui.view.CircleImageView;
import com.qkl.online.mining.app.ui.view.PicktureDialog;
import com.qkl.online.mining.app.utils.CommonsUtils;
import com.qkl.online.mining.app.utils.glide.GlideImageLoader;
import com.yuyh.library.imgsel.ISNav;
import com.yuyh.library.imgsel.common.ImageLoader;
import com.yuyh.library.imgsel.config.ISCameraConfig;
import com.yuyh.library.imgsel.config.ISListConfig;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * author：oyb on 2018/8/29 12:31
 * 设置个人信息
 */
public class SetUserInfoActivity extends BaseActivity<PublicPresenter> implements IPublicView {

    private static final int REQUEST_CAMERA_CODE = 1;
    private static final int REQUEST_LIST_CODE = 2;

    private String iconLocalFilePath;

    @BindView(R.id.set_userinfo_icon)
    CircleImageView mIconView;
    @BindView(R.id.set_userinfo_nickname)
    EditText mNickNameET;
    @BindView(R.id.set_userinfo_phone)
    EditText mPhoneET;

    @Override
    protected void initPresenter(Intent intent) {
        mPresenter = new PublicPresenter(this, this);
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_set_userinfo;
    }

    @Override
    protected void initView() {
        initTopBarOnlyTitle(R.id.activity_set_userinfo_headerview, getXmlString(R.string.set_userinfo_page_title));
    }

    @Override
    protected void initData() {
        initUserData();
        initCamera();
    }

    private void initUserData() {
        // 头像
        setIconView(AccountManager.getInstance().getPicture(), false);
        // 昵称
        String nickName = AccountManager.getInstance().getNickName();
        if(!TextUtils.isEmpty(nickName)) {
            mNickNameET.setText(nickName);
            mNickNameET.setSelection(nickName.length());
        }
//        // 联系电话
//        String mobile = AccountManager.getInstance().getPhone();
//        if(!TextUtils.isEmpty(mobile)) {
//            mPhoneET.setText(mobile);
//            mPhoneET.setSelection(mobile.length());
//        }
    }

    private void initCamera() {
        // 自定义图片加载器
        ISNav.getInstance().init(new ImageLoader() {
            @Override
            public void displayImage(Context context, String path, ImageView imageView) {
                GlideImageLoader.loadImage(context, path, imageView);
            }
        });
    }

    @OnClick(R.id.set_userinfo_camera_layout)
    void camera() {
        new PicktureDialog(this, doubleClickListener).show();
    }

    @OnClick(R.id.set_userinfo_save)
    void save() {
        String nickName = mNickNameET.getText().toString().trim();
        String mobile = mPhoneET.getText().toString().trim();

        mPresenter.updateUserInfo(iconLocalFilePath, nickName, mobile);
    }

    private NoDoubleClickListener doubleClickListener = new NoDoubleClickListener() {
        @Override
        protected void onNoDoubleClick(View v) {
            switch (v.getId()) {
                case R.id.pop_pickture_camera_txt:
                    toCamera();
                    break;
                case R.id.pop_pickture_select_txt:
                    selectImage();
                    break;
            }
        }
    };

    private void selectImage() {
        // 自由配置选项
        ISListConfig config = new ISListConfig.Builder()
                // 是否多选, 默认true
                .multiSelect(false)
                // 是否记住上次选中记录, 仅当multiSelect为true的时候配置，默认为true
                .rememberSelected(false)
                // “确定”按钮背景色
                .btnBgColor(Color.GRAY)
                // “确定”按钮文字颜色
                .btnTextColor(Color.BLUE)
                // 使用沉浸式状态栏
                .statusBarColor(CommonsUtils.getXmlColor(R.color.colorPrimary))
                // 返回图标ResId
                .backResId(R.drawable.kd_back_white)
                // 标题
                .title(getXmlString(R.string.image))
                // 标题文字颜色
                .titleColor(Color.WHITE)
                // TitleBar背景色
                .titleBgColor(CommonsUtils.getXmlColor(R.color.colorPrimary))
                // 裁剪大小。needCrop为true的时候配置
                .cropSize(1, 1, 300, 300)
                .needCrop(true)
                // 第一个是否显示相机，默认true
                .needCamera(false)
                // 最大选择图片数量，默认9
                .maxNum(1)
                .build();
        ISNav.getInstance().toListActivity(this, config, REQUEST_LIST_CODE);
    }

    private void toCamera() {
        ISCameraConfig config = new ISCameraConfig.Builder()
                .needCrop(true) // 裁剪
                .cropSize(1, 1, 200, 200)
                .build();

        ISNav.getInstance().toCameraActivity(this, config, REQUEST_CAMERA_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == RESULT_OK && data != null) {
            if(requestCode == REQUEST_CAMERA_CODE) {
                // 拍照返回
                String path = data.getStringExtra("result");
                setIconView(path, true);
            } else if(requestCode == REQUEST_LIST_CODE) {
                List<String> pathList = data.getStringArrayListExtra("result");
                for (String path : pathList) {
                    setIconView(path, true);
                }
            }
        }
    }

    private void setIconView(String path, boolean isSet) {
        if(!TextUtils.isEmpty(path)) {
            GlideImageLoader.loadImage(SetUserInfoActivity.this, path, mIconView);
            if(isSet) {
                iconLocalFilePath = path;
            }
        }
    }

    @Override
    protected void onEventCallback(EventBase eventBase) {
        super.onEventCallback(eventBase);
        String action = eventBase.getAction();
        if(Constants.REFRESH_USER_INFO_KEY.equals(action)) {
            initUserData();
        }
    }

    @Override
    public void showLoading() {
        showLoading(true);
    }

    @Override
    public void hideLoading() {
        showLoading(false);
    }

}
