package com.qkl.online.mining.app.ui.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.text.TextUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.qkl.online.mining.app.R;
import com.qkl.online.mining.app.application.AccountManager;
import com.qkl.online.mining.app.data.entity.DictConfig;
import com.qkl.online.mining.app.data.entity.User;
import com.qkl.online.mining.app.mvp.presenter.PublicPresenter;
import com.qkl.online.mining.app.mvp.view.IPublicView;
import com.qkl.online.mining.app.ui.BaseActivity;
import com.qkl.online.mining.app.utils.CommonsUtils;
import com.qkl.online.mining.app.utils.ImgUtils;
import com.qkl.online.mining.app.utils.ToastUtils;
import com.qkl.online.mining.app.utils.glide.GlideImageLoader;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * author：oyb on 2018/9/9 02:10
 * 邀请节点
 */
public class InviteNodeActivity extends BaseActivity<PublicPresenter> implements IPublicView {

    @BindView(R.id.share_qrcodeImg_image)
    ImageView mQrcodeImgeview;
    @BindView(R.id.invite_node_code_txt)
    TextView mNoteCodeTxt;
    @BindView(R.id.invite_node_link_txt)
    TextView mLinkTxt;
    @BindView(R.id.invite_node_appname_txt)
    TextView mAppNameTxt;
    @BindView(R.id.invite_node_appsummary_txt)
    TextView mAppSummaryTxt;

    private String qrcodeImg;

    @Override
    protected void initPresenter(Intent intent) {
        mPresenter = new PublicPresenter(this, this);
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_invite_node;
    }

    @Override
    protected void initView() {
        initTopBarOnlyTitle(R.id.activity_invite_node_headerview, getXmlString(R.string.invite_node_page_title));
    }

    @Override
    protected void initData() {
        User user = AccountManager.getInstance().getUser();
        if (user != null) {
            // 二维码
            qrcodeImg = user.getQrcodeImg();
            GlideImageLoader.loadImage(this, qrcodeImg, mQrcodeImgeview);
            // 推荐码
            mNoteCodeTxt.setText(CommonsUtils.getXmlString(this, R.string.invite_node_invitation_code, user.getReferralCode()));
            // 链接
            mLinkTxt.setText(user.getReferralLink());
        }

        DictConfig dictConfig = AccountManager.getInstance().getDictConfig();
        if (dictConfig != null) {
            mAppNameTxt.setText(dictConfig.getAppName());
            mAppSummaryTxt.setText(dictConfig.getAppSummary());
        } else {
            mAppNameTxt.setText(CommonsUtils.getAppName());
        }
    }

    @OnClick(R.id.invite_node_code_layout)
    void copyInviteCode() {
        // 点击邀请码复制
        User user = AccountManager.getInstance().getUser();
        if (user != null) {
            String codeLink = user.getReferralCode();
            if (!TextUtils.isEmpty(codeLink)) {
                CommonsUtils.copyStr(this, codeLink);
            }
        }
    }

    @OnClick(R.id.invite_node_copy_bt)
    void copyLink() {
        // 复制邀请链接
        String codeLink = mLinkTxt.getText().toString().trim();
        if (!TextUtils.isEmpty(codeLink)) {
            CommonsUtils.copyStr(this, codeLink);
        }
    }

    @OnClick(R.id.invite_node_invite_bt)
    void invite() {
        // 邀请节点，保存二维码图片至相册
        mQrcodeImgeview.setDrawingCacheEnabled(true);
        Bitmap qrcodeBitmap = Bitmap.createBitmap(mQrcodeImgeview.getDrawingCache());
        mQrcodeImgeview.setDrawingCacheEnabled(false);
        if (qrcodeBitmap != null && !TextUtils.isEmpty(qrcodeImg)) {
            boolean success = ImgUtils.saveImageToGallery(this, qrcodeBitmap, "qrcodeImage.jpg");
            if (success) {
                ToastUtils.showShort(R.string.save_bitmap_success);
            } else {
                ToastUtils.showShort(R.string.save_bitmap_failed);
            }
        }
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

}
