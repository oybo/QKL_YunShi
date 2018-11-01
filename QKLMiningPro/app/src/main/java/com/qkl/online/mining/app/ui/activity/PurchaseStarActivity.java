package com.qkl.online.mining.app.ui.activity;

import android.content.Intent;
import android.widget.ImageView;
import android.widget.TextView;

import com.qkl.online.mining.app.R;
import com.qkl.online.mining.app.application.AccountManager;
import com.qkl.online.mining.app.data.commons.Constants;
import com.qkl.online.mining.app.data.entity.MyStar;
import com.qkl.online.mining.app.data.entity.StarProduct;
import com.qkl.online.mining.app.data.event.EventBase;
import com.qkl.online.mining.app.listener.OnDialogOkListenner;
import com.qkl.online.mining.app.mvp.presenter.StarPresenter;
import com.qkl.online.mining.app.mvp.view.IStarView;
import com.qkl.online.mining.app.ui.BaseActivity;
import com.qkl.online.mining.app.ui.view.DialogTips;
import com.qkl.online.mining.app.utils.CommonsUtils;
import com.qkl.online.mining.app.utils.TimeUtils;
import com.qkl.online.mining.app.utils.glide.GlideImageLoader;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * author：oyb on 2018/9/9 22:03
 * 购买星球
 */
public class PurchaseStarActivity extends BaseActivity<StarPresenter> implements IStarView {

    private StarProduct.ListEntity mStarProduct;

    @BindView(R.id.purchase_star_image)
    ImageView mImageView;
    @BindView(R.id.purchase_star_name_txt)
    TextView mNameTxt;
    @BindView(R.id.purchase_star_price_txt)
    TextView mPriceTxt;
    @BindView(R.id.purchase_star_suanli_txt)
    TextView mSuanLiTxt;
    @BindView(R.id.purchase_star_zhouqi_txt)
    TextView mZhouQiTxt;
    @BindView(R.id.purchase_star_chanliang_txt)
    TextView mChanLiangTxt;
    @BindView(R.id.purchase_star_tip_txt)
    TextView mTipTxt;

    @Override
    protected void initPresenter(Intent intent) {
        mPresenter = new StarPresenter(this, this);
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_purchase_star;
    }

    @Override
    protected void initView() {
        mStarProduct = (StarProduct.ListEntity) getIntent().getSerializableExtra("listEntity");

        initTopBarOnlyTitle(R.id.purchase_star_headerview, mStarProduct != null ? mStarProduct.getMinerName() : "");
    }

    @Override
    protected void initData() {
        if(mStarProduct != null) {
            GlideImageLoader.loadImage(this, mStarProduct.getMinerImg(), mImageView);

            mNameTxt.setText(getXmlString(R.string.purchase_star_name_txt, mStarProduct.getDescription()));
            mPriceTxt.setText(getXmlString(R.string.purchase_star_price_txt, mStarProduct.getSellPrice()));
            mSuanLiTxt.setText(getXmlString(R.string.purchase_star_suanli_txt, mStarProduct.getHashrate()));
            mZhouQiTxt.setText(
                    getXmlString(R.string.purchase_star_zhouqi_txt,
                            TimeUtils.formatDateTime(this, mStarProduct.getLifecycle() * 1000)));
            mChanLiangTxt.setText(getXmlString(R.string.purchase_star_chanliang_txt, mStarProduct.getOutputAmount()));
            mTipTxt.setText(mStarProduct.getDescription());
        }
    }

    @OnClick(R.id.purchase_star_puchase_bt)
    void purchaseStar() {
        if(mStarProduct != null) {
            DialogTips dialogTips = new DialogTips(this);
            dialogTips.setTitle(CommonsUtils.getXmlString(this, R.string.tips_tip_title_txt));
            dialogTips.setMessage(getXmlString(R.string.purchase_star_affirm_txt,
                            String.valueOf(mStarProduct.getSellPrice()), getXmlString(R.string.main_ynu_txt)));
            dialogTips.setOkListenner(new OnDialogOkListenner() {
                @Override
                public void onClick() {
                    mPresenter.purchaseStar(String.valueOf(mStarProduct.getMinerId()));
                }
            });
            dialogTips.setCancelListenner(null);
            dialogTips.show();
        }
    }

    @Override
    public void resultMine(StarProduct starProduct) {
        // 无需处理
    }

    @Override
    public void resultMyStar(MyStar myStar) {
        // 无需处理
    }

    @Override
    public void resultStarRestNumber(int number) {
        // 无需处理
    }

    @Override
    public void resultPurchaseStar() {
        // 购买成功回调=========
        // 1.兑换成功刷新我的钱包
        AccountManager.getInstance().getUserYUNTData();
        // 2.刷新星球-我的星球
        EventBase eventBase = new EventBase();
        eventBase.setAction(Constants.REFRESH_MY_STAR_KEY);
        EventBus.getDefault().post(eventBase);
    }

    @Override
    public void showLoading() {
        showLoading(true, getXmlString(R.string.purchase_star_puchase_in_bt));
    }

    @Override
    public void hideLoading() {
        showLoading(false);
    }

}
