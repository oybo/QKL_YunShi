package com.qkl.online.mining.app.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.qkl.online.mining.app.R;
import com.qkl.online.mining.app.mvp.presenter.PublicPresenter;
import com.qkl.online.mining.app.mvp.view.IPublicView;
import com.qkl.online.mining.app.ui.BaseActivity;
import com.qkl.online.mining.app.utils.CommonsUtils;
import com.qkl.online.mining.app.utils.languagelib.LanguageType;
import com.qkl.online.mining.app.utils.languagelib.MultiLanguageUtil;

/**
 * 设置语言页面
 */
public class SetLanguageActivity extends BaseActivity<PublicPresenter> implements View.OnClickListener, IPublicView {

    private RelativeLayout rl_simplified_chinese;
    private RelativeLayout rl_traditional_chinese;
    private RelativeLayout rl_english;
    private ImageView iv_english;
    private ImageView iv_simplified_chinese;
    private ImageView iv_traditional_chinese;
    private int savedLanguageType;

    @Override
    protected void initPresenter(Intent intent) {
        mPresenter = new PublicPresenter(this, this);
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_set_language;
    }

    @Override
    protected void initView() {
        initTopBarOnlyTitle(R.id.activity_set_language_headerview, getXmlString(R.string.language_type_page_title));

        rl_simplified_chinese = findViewById(R.id.rl_simplified_chinese);
        rl_traditional_chinese = findViewById(R.id.rl_traditional_chinese);
        rl_english = findViewById(R.id.rl_english);
        iv_english = findViewById(R.id.iv_english);
        iv_simplified_chinese = findViewById(R.id.iv_simplified_chinese);
        iv_traditional_chinese = findViewById(R.id.iv_traditional_chinese);
        rl_simplified_chinese.setOnClickListener(this);
        rl_traditional_chinese.setOnClickListener(this);
        rl_english.setOnClickListener(this);
        savedLanguageType = MultiLanguageUtil.getInstance().getLanguageType();
        if (savedLanguageType == LanguageType.LANGUAGE_CHINESE_TRADITIONAL) {
            setTraditionalVisible();
        } else if (savedLanguageType == LanguageType.LANGUAGE_EN) {
            setEnglishVisible();
        } else if (savedLanguageType == LanguageType.LANGUAGE_CHINESE_SIMPLIFIED) {
            setSimplifiedVisible();
        } else {
            setSimplifiedVisible();
        }
    }

    @Override
    protected void initData() {

    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        int selectedLanguage = 0;
        switch (id) {
            case R.id.rl_simplified_chinese:
                setSimplifiedVisible();
                selectedLanguage = LanguageType.LANGUAGE_CHINESE_SIMPLIFIED;

                break;
            case R.id.rl_traditional_chinese:
                setTraditionalVisible();
                selectedLanguage = LanguageType.LANGUAGE_CHINESE_TRADITIONAL;

                break;
            case R.id.rl_english:
                setEnglishVisible();
                selectedLanguage = LanguageType.LANGUAGE_EN;
                break;
        }
        MultiLanguageUtil.getInstance().updateLanguage(selectedLanguage);
        setResult(Activity.RESULT_OK);
        finish();
    }

    private void setSimplifiedVisible() {
        iv_english.setVisibility(View.GONE);
        iv_simplified_chinese.setVisibility(View.VISIBLE);
        iv_traditional_chinese.setVisibility(View.GONE);
    }

    private void setEnglishVisible() {
        iv_english.setVisibility(View.VISIBLE);
        iv_simplified_chinese.setVisibility(View.GONE);
        iv_traditional_chinese.setVisibility(View.GONE);
    }

    private void setTraditionalVisible() {
        iv_english.setVisibility(View.GONE);
        iv_simplified_chinese.setVisibility(View.GONE);
        iv_traditional_chinese.setVisibility(View.VISIBLE);
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

}
