package com.qkl.online.mining.app.ui.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.text.Editable;
import android.text.Html;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import com.lazy.viewpager.fragment.LazyFragmentLazy;
import com.qkl.online.mining.app.R;
import com.qkl.online.mining.app.application.AccountManager;
import com.qkl.online.mining.app.data.commons.Constants;
import com.qkl.online.mining.app.data.entity.AppCommonsConfig;
import com.qkl.online.mining.app.data.entity.DictConfig;
import com.qkl.online.mining.app.data.entity.MyYuntEntity;
import com.qkl.online.mining.app.data.entity.SSExchangerate;
import com.qkl.online.mining.app.data.entity.User;
import com.qkl.online.mining.app.data.event.EventBase;
import com.qkl.online.mining.app.mvp.presenter.UserInfoPresenter;
import com.qkl.online.mining.app.mvp.view.IUserInfoView;
import com.qkl.online.mining.app.ui.activity.WebViewActivity;
import com.qkl.online.mining.app.ui.view.CircleImageView;
import com.qkl.online.mining.app.ui.view.SeekBarFloat;
import com.qkl.online.mining.app.utils.CommonsUtils;
import com.qkl.online.mining.app.utils.IntentUtil;
import com.qkl.online.mining.app.utils.ToastUtils;
import com.qkl.online.mining.app.utils.glide.GlideImageLoader;
import java.text.DecimalFormat;

/**
 * author：oyb on 2018/8/27 11:25
 * 个人中心
 */
public class UserInfoFragment extends LazyFragmentLazy<UserInfoPresenter> implements IUserInfoView, View.OnClickListener, SeekBar.OnSeekBarChangeListener, TextWatcher {

    private static Handler mHandler = new Handler();

    private SwipeRefreshLayout mSwipeRefreshLayout;

    private CircleImageView mCircleImageView;
    private TextView mNickNameTxt;
    private TextView mEmailTxt;
    private TextView mKuangJiTxt;
    private TextView mYuntYETxt;

    private TextView muYunEchangeRateTxt;
    private float yunEchangeRateFloat;
    private int inputNumber;    // 用户输入的提币数量
    private int drawMinAmount;  // 最小提币数量
    private double drawMaxFeeRate;  // 最大比手续费
    private double drawMinFeeRate;  // 最小比手续费

    // 兑换YUN
    private boolean isSelectYUN;
    private TextView mParitiesYUN;
    private LinearLayout mParitiesYUNLayout;
    private EditText mDHYunNumberET;
    private TextView mDHNeedNemberTxt;
    private SeekBarFloat mDHYunSeekBar;
    private TextView mDHYunSHTxt;
    private TextView mDHYunChargeTxt;
    private TextView mDHAdressInTxt;
    private EditText mDHPasswordET;
    private String mDHYunChargeValue;

    // 兑换YUNT
    private boolean isSelectYUNT;
    private TextView mParitiesYUNT;
    private LinearLayout mParitiesYUNTLayout;
    private TextView mDHtAdressInTxt;

    private TextView mKuangJiEarningsTxt;
    private TextView mJieDianEarningsTxt;
    private TextView mAllJieDianEarningsTxt;
    private TextView mStarEarningsTxt;
    private TextView mStarNumberTxt;
    private TextView mDHMinNumberTxt;
    private TextView mMyJieDianEarningsTxt;

    @Override
    protected void initPresenter() {
        mPresenter = new UserInfoPresenter(getActivity(), this);
    }

    @Override
    protected void onCreateViewLazy(Bundle savedInstanceState) {
        super.onCreateViewLazy(savedInstanceState);
        setContentView(R.layout.fragment_userinfo);

        initView();
        initData();
    }

    private void initView() {
        setTitleBar(findViewById(R.id.fragment_userinfo_titleview));

        mCircleImageView = (CircleImageView) findViewById(R.id.fragment_userinfo_headview);
        mNickNameTxt = (TextView) findViewById(R.id.fragment_userinfo_name);
        mEmailTxt = (TextView) findViewById(R.id.fragment_userinfo_email);
        mKuangJiTxt = (TextView) findViewById(R.id.fragment_userinfo_mykj);
        mYuntYETxt = (TextView) findViewById(R.id.fragment_userinfo_ynut_balance_txt);

        muYunEchangeRateTxt = (TextView) findViewById(R.id.userinfo_yunechangerate_txt);

        mParitiesYUN = (TextView) findViewById(R.id.fragment_userinfo_yun_parities);
        mParitiesYUNLayout = (LinearLayout) findViewById(R.id.fragment_userinfo_yun_parities_layout);
        mDHYunNumberET = (EditText) findViewById(R.id.duihuan_yun_number_et);
        mDHYunNumberET.addTextChangedListener(this);
        mDHNeedNemberTxt = (TextView) findViewById(R.id.duihuan_yun_need_number_txt);
        setTextViewExchangeValue("");
        mDHYunSeekBar = (SeekBarFloat) findViewById(R.id.duihuan_yun_charge_seekbar);
        mDHYunChargeTxt = (TextView) findViewById(R.id.duihuan_yun_charge_txt);
        mDHYunSHTxt = (TextView) findViewById(R.id.duihuan_yun_sh_txt);
        mDHAdressInTxt = (TextView) findViewById(R.id.duihuan_yun_adress_in_txt);
        mDHPasswordET = (EditText) findViewById(R.id.duihuan_yun_password_txt);
        mParitiesYUN.setOnClickListener(this);
        mDHYunSeekBar.setOnSeekBarChangeListener(this);
        setSeekBarTouchEnabled(false);
        findViewById(R.id.parities_ynu_affirm_bt).setOnClickListener(this);
        findViewById(R.id.parities_ynu_record_bt).setOnClickListener(this);
        findViewById(R.id.parities_ynut_record_bt).setOnClickListener(this);

        mParitiesYUNT = (TextView) findViewById(R.id.fragment_userinfo_yunt_parities);
        mParitiesYUNT.setOnClickListener(this);
        mParitiesYUNTLayout = (LinearLayout) findViewById(R.id.fragment_userinfo_yunt_parities_layout);
        mDHtAdressInTxt = (TextView) findViewById(R.id.duihuan_yunt_adress_in_txt);
        mDHtAdressInTxt.setOnClickListener(this);

        mKuangJiEarningsTxt = (TextView) findViewById(R.id.account_miningIncome_txt);
        mJieDianEarningsTxt = (TextView) findViewById(R.id.account_minerBonus_txt);
        mAllJieDianEarningsTxt = (TextView) findViewById(R.id.account_miningBonus_txt);
        mStarEarningsTxt = (TextView) findViewById(R.id.userinfo_star_earnings_txt);
        mStarNumberTxt = (TextView) findViewById(R.id.account_star_number_txt);
        mDHMinNumberTxt = (TextView) findViewById(R.id.userinfo_parities_min_ynu_txt);
        mMyJieDianEarningsTxt = (TextView) findViewById(R.id.account_my_jiedian_txt);
        findViewById(R.id.fragment_userinfo_invite_jiedian_layout).setOnClickListener(this);
        findViewById(R.id.fragment_userinfo_set_layout).setOnClickListener(this);
        findViewById(R.id.fragment_userinfo_logout_layout).setOnClickListener(this);

        findViewById(R.id.fragment_userinfo_kjsy_layout).setOnClickListener(this);
        findViewById(R.id.fragment_userinfo_jiedian_layout).setOnClickListener(this);
        findViewById(R.id.fragment_userinfo_zongjiedian_layout).setOnClickListener(this);
        findViewById(R.id.fragment_star_number_layout).setOnClickListener(this);
        findViewById(R.id.fragment_myteam_layout).setOnClickListener(this);

        findViewById(R.id.duihuan_yun_set_adress_bt).setOnClickListener(this);

        mSwipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.fragment_userinfo_refreshlayout);
        // 设置转动颜色变化
        mSwipeRefreshLayout.setColorSchemeResources(
                android.R.color.holo_blue_dark,
                android.R.color.holo_red_light,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light);

        // 刷新监听
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override public void onRefresh() {
                // 开始转动
                mSwipeRefreshLayout.setRefreshing(true);
                AccountManager.getInstance().getUserYUNTData();
                AccountManager.getInstance().getUserInfo();
                mHandler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        // 停止转动
                        mSwipeRefreshLayout.setRefreshing(false);
                    }
                }, 2000);
            }
        });
    }

    private void initData() {
        setUserInfo();
        setDhExchangeRate();
    }

    @Override
    protected void onEventCallback(EventBase eventBase) {
        super.onEventCallback(eventBase);
        String action = eventBase.getAction();
        if(Constants.REFRESH_USER_INFO_KEY.equals(action)) {
            initData();
        }
    }

    private void setUserInfo() {
        // 头像
        if(!TextUtils.isEmpty(AccountManager.getInstance().getPicture())) {
            GlideImageLoader.loadImage(getActivity(), AccountManager.getInstance().getPicture(), mCircleImageView);
        }
        // 昵称
        mNickNameTxt.setText(AccountManager.getInstance().getNickName());
        mEmailTxt.setText(AccountManager.getInstance().getEmail());

        String starResidueName = AccountManager.getInstance().getStarResidueName();
        String starResidueDay = AccountManager.getInstance().getStarResidueDay();
        mKuangJiTxt.setText(getXmlString(R.string.main_shengyu_kj_txt, starResidueName, starResidueDay));

        MyYuntEntity yuntEntity = AccountManager.getInstance().getMyYuntEntity();
        if(yuntEntity != null) {
            // 我的钱包
            mYuntYETxt.setText(String.valueOf(yuntEntity.getBalance()));
            // 矿机收益
            mKuangJiEarningsTxt.setText(String.valueOf(yuntEntity.getMiningIncome()));
            // 节点收益
            mJieDianEarningsTxt.setText(String.valueOf(yuntEntity.getMinerBonus()));
            // 总节点收益
            mAllJieDianEarningsTxt.setText(String.valueOf(yuntEntity.getMiningBonus()));
            // 星球收益
            mStarEarningsTxt.setText(getXmlString(R.string.account_minerBonus_str_txt)
                    + "：" + String.valueOf(yuntEntity.getMiningBonus())
                    + getXmlString(R.string.main_ynu_txt));
//            mStarNumberTxt.setText(String.valueOf(yuntEntity.getMiningBonus()));

            // 最低手续费  && 最高手续费
            AppCommonsConfig appCommonsConfig = AccountManager.getInstance().getAppCommonsConfig();
            if (appCommonsConfig != null) {
                drawMinAmount = appCommonsConfig.getDrawMinAmount();
                drawMaxFeeRate = appCommonsConfig.getDrawMaxFeeRate();
                drawMinFeeRate = appCommonsConfig.getDrawMinFeeRate();
            } else {
                DictConfig dictConfig = AccountManager.getInstance().getDictConfig();
                if(dictConfig != null) {
                    drawMinAmount = dictConfig.getDrawMinAmount();
                    drawMaxFeeRate = dictConfig.getDrawMaxFeeRate();
                    drawMinFeeRate = dictConfig.getDrawMinFeeRate();
                }
            }
            mDHYunNumberET.setHint(CommonsUtils.getXmlString(getActivity(), R.string.parities_ynu_input_number_txt, String.valueOf(drawMinAmount)));
            mDHMinNumberTxt.setText(CommonsUtils.getXmlString(getActivity(), R.string.parities_ynu_in_tips_txt, String.valueOf(drawMinAmount)));

            mDHYunChargeValue = "0";
            setDHYunChargeTxt();

            // 我的节点
            User user = AccountManager.getInstance().getUser();
            if(user != null) {
                mMyJieDianEarningsTxt.setText(String.valueOf(user.getTeamNum()));
            }
        } else {
            mDHYunSeekBar.setMax(0);
        }

        // 实时汇率
        SSExchangerate ssExchangerate = AccountManager.getInstance().getSSExchangerate();
        if(ssExchangerate != null) {
            yunEchangeRateFloat = ssExchangerate.getYunToYunt();
            try {
                String rate = "1YUN=" + ssExchangerate.getYunToYunt() + "YUNT";
                muYunEchangeRateTxt.setText(getXmlString(R.string.parities_ynu_atexchange_txt, rate));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void setDhExchangeRate() {
        User user = AccountManager.getInstance().getUser();
        if(user != null) {
            // YUN转入地址
            String yunAddress = user.getEtcAddress();
            if(!TextUtils.isEmpty(yunAddress)) {
                mDHAdressInTxt.setText(yunAddress);
            }
            // YUNT转入地址
            mDHtAdressInTxt.setText(getXmlString(R.string.parities_ynu_str_in_address_txt, user.getYunAddress()));
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.fragment_userinfo_yun_parities:
                // 兑换YUN
                if(!isSelectYUN) {
                    // 显示
                    isSelectYUN = true;
                } else {
                    // 隐藏
                    isSelectYUN = false;
                }
                setYunStatus();

                if(isSelectYUNT) {
                    // 隐藏YNUT
                    if(mParitiesYUNTLayout.getVisibility() != View.GONE) {
                        isSelectYUNT = false;
                        setYuntStatus();
                    }
                }
                break;
            case R.id.fragment_userinfo_yunt_parities:
                // 兑换YUNT
                if(!isSelectYUNT) {
                    // 显示
                    isSelectYUNT = true;
                } else {
                    // 隐藏
                    isSelectYUNT = false;
                }
                setYuntStatus();

                if(isSelectYUN) {
                    // 隐藏YNU
                    if(mParitiesYUNLayout.getVisibility() != View.GONE) {
                        isSelectYUN = false;
                        setYunStatus();
                    }
                }
                break;
            case R.id.parities_ynu_affirm_bt:
                // yun确定兑换
                String inputAmount = mDHYunNumberET.getText().toString().trim();    // 输入的YUNT数量
                if(TextUtils.isEmpty(inputAmount)) {
                    ToastUtils.showShort(R.string.parities_ynu_input_number_txt, String.valueOf(drawMinAmount));
                    return;
                }
                String payPassword = mDHPasswordET.getText().toString().trim();
                if(TextUtils.isEmpty(payPassword)) {
                    ToastUtils.showShort(R.string.parities_ynu_in_password_txt);
                    return;
                }
                if(inputNumber < drawMinAmount) {
                    ToastUtils.showShort(CommonsUtils.getXmlString(getActivity(),
                            R.string.parities_ynu_input_number_number_check_txt, String.valueOf(drawMinAmount)));
                    return;
                }
                mPresenter.yunt2yun(inputAmount, mDHYunChargeValue, payPassword);
                break;
            case R.id.parities_ynu_record_bt:
                // yun兑换记录
                IntentUtil.ToExchangeListActivity(getActivity(), 0);
                break;
            case R.id.parities_ynut_record_bt:
                // yunt兑换记录
                IntentUtil.ToExchangeListActivity(getActivity(), 1);
                break;
            case R.id.duihuan_yunt_adress_in_txt:
                // 复制yunt转入地址
                User user = AccountManager.getInstance().getUser();
                if(user != null && !TextUtils.isEmpty(user.getYunAddress())) {
                    CommonsUtils.copyStr(getActivity(), user.getYunAddress());
                }
                break;
            case R.id.fragment_userinfo_kjsy_layout:
                // 矿机收益
                IntentUtil.ToKJEarningsActivity(getActivity());
                break;
            case R.id.fragment_userinfo_jiedian_layout:
                // 节点收益
                IntentUtil.ToJieDianEarningsActivity(getActivity());
                break;
            case R.id.fragment_userinfo_zongjiedian_layout:
                // 总节点收益
                IntentUtil.ToZongJieDianEarningsActivity(getActivity());
                break;
            case R.id.fragment_star_number_layout:
                // 星球收益
                IntentUtil.ToStarEarningsActivity(getActivity());
                break;
            case R.id.fragment_myteam_layout:
                // 我的团队
                AppCommonsConfig appCommonsConfig = AccountManager.getInstance().getAppCommonsConfig();
                if(appCommonsConfig != null) {
                    String url = appCommonsConfig.getInviteStartListUrl();
                    if(!TextUtils.isEmpty(url)) {
                        url += "?culture=" + CommonsUtils.getMyTeamLanguage() + "&mid=" + System.currentTimeMillis();
                        IntentUtil.ToWebViewActivity(getActivity(), getXmlString(R.string.fragment_myteam_page_title), url, true);
                    }
                }
//                IntentUtil.ToMyTeamActivity(getActivity());
                break;
            case R.id.fragment_userinfo_invite_jiedian_layout:
                // 邀请节点
                IntentUtil.ToInviteNodeActivity(getActivity());
                break;
            case R.id.fragment_userinfo_set_layout:
                // 设置
                IntentUtil.ToSetActivity(getActivity());
                break;
            case R.id.duihuan_yun_set_adress_bt:
                // 设置YUN地址
                IntentUtil.ToSetYunActivity(getActivity());
                break;
            case R.id.fragment_userinfo_logout_layout:
                // 注销
                mPresenter.logOut();
                break;
        }
    }

    private void setYunStatus() {
        mParitiesYUN.setSelected(isSelectYUN);
        mParitiesYUNLayout.setVisibility(isSelectYUN ? View.VISIBLE : View.GONE);
    }

    private void setYuntStatus() {
        mParitiesYUNT.setSelected(isSelectYUNT);
        mParitiesYUNTLayout.setVisibility(isSelectYUNT ? View.VISIBLE : View.GONE);
    }

    // ============yun兑换手续费==========

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        try {
            mDHYunChargeValue = String.valueOf(mDHYunSeekBar.getProgressF());
            setDHYunChargeTxt();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }

    private void setDHYunChargeTxt() {
        // 这里的值等于当前汇率乘以个数
        mDHYunChargeTxt.setText(mDHYunChargeValue);
        mDHYunSHTxt.setText(mDHYunSeekBar.getSHValue());
    }

    // ============yun兑换手续费==========

    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void afterTextChanged(Editable editable) {
        String value = editable.toString();
        if(!TextUtils.isEmpty(value)) {
            inputNumber = Integer.parseInt(value);
            if(inputNumber < drawMinAmount) {
                setSeekBarTouchEnabled(false);
                // 兑换YUN的数量
                setTextViewExchangeValue("0");
                // 手续费
                mDHYunSeekBar.setMaxF(10f);
                mDHYunSeekBar.setMinF(0f);
                mDHYunSeekBar.setProgressF(0f);
                mDHYunChargeValue = "0";
                setDHYunChargeTxt();
                return;
            }

            setSeekBarTouchEnabled(true);
            // 这里的值等于当前汇率乘以个数
            if(inputNumber % yunEchangeRateFloat == 0) {
                int dd = (int) (inputNumber / yunEchangeRateFloat);
                setTextViewExchangeValue(String.valueOf(dd));   // 手续费
            } else {
                float v = inputNumber / yunEchangeRateFloat;
                DecimalFormat fnum = new DecimalFormat("##0.00");
                String dd = fnum.format(v);
                setTextViewExchangeValue(dd);   // 手续费
            }
            // 手续费百分百和数值
            float maxF = (float) (inputNumber * drawMaxFeeRate);
            float minF = (float) (inputNumber * drawMinFeeRate);
            mDHYunSeekBar.setMaxF(maxF);
            mDHYunSeekBar.setMinF(minF);
            mDHYunSeekBar.setProgressF(minF);
            mDHYunChargeValue = String.valueOf(minF);
            setDHYunChargeTxt();
        } else {
            setSeekBarTouchEnabled(false);
            setTextViewExchangeValue("0");
        }
    }

    private void setSeekBarTouchEnabled(boolean enabled) {
        mDHYunSeekBar.setTouchEnable(enabled);
    }

    // 兑换YUN的数量
    private void setTextViewExchangeValue(String value) {
        String str;
        if(!TextUtils.isEmpty(value)) {
            str = "<font color=\"#9441EF\">" + value + "</font>";
        } else {
            str = "<font color=\"#9441EF\">" + 0 + "</font>";
        }
        str = getXmlString(R.string.parities_ynu_need_yunt_txt, str);
        mDHNeedNemberTxt.setText(Html.fromHtml(str));
    }

    // ============yun兑换手续费==========

    @Override
    public void showLoading() {
        showLoading(true);
    }

    @Override
    public void hideLoading() {
        showLoading(false);
    }

    @Override
    public void logOut() {
        IntentUtil.ToLoginActivity(getActivity());
        getActivity().finish();
    }

}
