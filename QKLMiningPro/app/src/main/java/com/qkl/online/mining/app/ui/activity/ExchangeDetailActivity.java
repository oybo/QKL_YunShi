package com.qkl.online.mining.app.ui.activity;

import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.qkl.online.mining.app.R;
import com.qkl.online.mining.app.application.AccountManager;
import com.qkl.online.mining.app.data.commons.UrlConfig;
import com.qkl.online.mining.app.data.entity.Exchange;
import com.qkl.online.mining.app.data.entity.ExchangeDetail;
import com.qkl.online.mining.app.mvp.presenter.ExchangeListPresenter;
import com.qkl.online.mining.app.mvp.view.IExchangeListView;
import com.qkl.online.mining.app.ui.BaseActivity;
import com.qkl.online.mining.app.utils.CommonsUtils;
import com.qkl.online.mining.app.utils.IntentUtil;
import com.qkl.online.mining.app.utils.TimeUtils;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * author：oyb on 2018/9/16 22:18
 * 兑换记录详情页
 */
public class ExchangeDetailActivity extends BaseActivity<ExchangeListPresenter> implements IExchangeListView {

    @BindView(R.id.activity_exchange_detail_title)
    TextView mTitleTxt;
    @BindView(R.id.activity_exchange_detail_number)
    TextView mNumberTxt;
    @BindView(R.id.activity_exchange_detail_status)
    TextView mStatusTxt;
    @BindView(R.id.activity_exchange_detail_qukuai)
    TextView mQkqrTxt;
    @BindView(R.id.activity_exchange_detail_address)
    TextView mAddressTxt;
    @BindView(R.id.activity_exchange_detail_txid)
    TextView mTxidTxt;
    @BindView(R.id.activity_exchange_detail_date)
    TextView mDateTxt;

    @BindView(R.id.activity_exchange_detail_copy_txid)
    TextView mCopyTxidTxt;
    @BindView(R.id.activity_exchange_detail_check_txid)
    TextView mCheckTxidTxt;

    private Exchange.ListBean listBean;

    @Override
    protected void initPresenter(Intent intent) {
        mPresenter = new ExchangeListPresenter(this, this);
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_exchange_detail;
    }

    @Override
    protected void initView() {
    }

    @Override
    protected void initData() {
        Intent intent = getIntent();
        String type = intent.getStringExtra("type");


        initTopBarOnlyTitle(R.id.activity_exchange_detail_headerview, ExchangeListPresenter.YUN2YUNT.equals(type) ?
                getXmlString(R.string.exchange_yunt_detail_page_title) :
                getXmlString(R.string.exchange_yun_detail_page_title) );

        if(ExchangeListPresenter.YUN2YUNT.equals(type)) {
            // 兑换YUNT的详情去掉复制和检查txtid按钮
            mCopyTxidTxt.setVisibility(View.GONE);
            mCheckTxidTxt.setVisibility(View.GONE);
        }

        listBean = (Exchange.ListBean) intent.getSerializableExtra("listBean");
        if(listBean != null) {
            setData(listBean, null);
            mPresenter.getExchangeDetail(listBean.getTxid());
        }
    }

    private void setData(Exchange.ListBean listBean, ExchangeDetail exchangeDetail) {
        if(listBean != null) {
            mTitleTxt.setText(listBean.getExchangeMoney());
            mNumberTxt.setText(CommonsUtils.getXmlString(this, R.string.exchange_detail_number, listBean.getExchangeAmount()));
            String status = AccountManager.getInstance().getExchangeStatus(listBean.getTxid(), listBean.getExchangeStatus());
            mStatusTxt.setText(CommonsUtils.getXmlString(this, R.string.exchange_detail_status, status));
            mQkqrTxt.setText(CommonsUtils.getXmlString(this, R.string.exchange_detail_qkqr, ""));
            mAddressTxt.setText(CommonsUtils.getXmlString(this, R.string.exchange_detail_address, listBean.getToAddress()));
            mTxidTxt.setText(CommonsUtils.getXmlString(this, R.string.exchange_detail_txid, listBean.getTxid()));
            mDateTxt.setText(CommonsUtils.getXmlString(this, R.string.exchange_detail_date, TimeUtils.millis2String(listBean.getAddTime())));
        } else {
            if(exchangeDetail != null) {
                mQkqrTxt.setText(CommonsUtils.getXmlString(this, R.string.exchange_detail_qkqr, exchangeDetail.getBlockHigh()));
            }
        }
    }

    @Override
    public void resultExchange(Exchange exchange) {
        // 无需处理
    }

    @Override
    public void resultExchangeDetail(ExchangeDetail exchangeDetail) {
        findViewById(R.id.include_loading_layout).setVisibility(View.GONE);

        setData(null, exchangeDetail);
    }

    @OnClick(R.id.activity_exchange_detail_copy_txid)
    void copyTxid() {
        // 复制Txid
        if(listBean != null && !TextUtils.isEmpty(listBean.getTxid())) {
            CommonsUtils.copyStr(this, listBean.getTxid());
        }
    }

    @OnClick(R.id.activity_exchange_detail_check_txid)
    void checkTxid() {
        // 检查Txid
        if(listBean != null && !TextUtils.isEmpty(listBean.getTxid())) {
            String url = UrlConfig.getCheckTxidUrl(listBean.getTxid());
            IntentUtil.ToWebViewActivity(this, getXmlString(R.string.exchange_detail_check_txid), url);
        }
    }

}
