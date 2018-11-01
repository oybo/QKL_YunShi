package com.qkl.online.mining.app.ui.view;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.Toolbar;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.qkl.online.mining.app.R;

import skin.support.widget.SkinCompatFrameLayout;

/**
 * 自定义ActionBar ouyangbo
 */
public class HeaderView extends SkinCompatFrameLayout implements View.OnClickListener {

	private Toolbar mToolbar;
	private TextView mTitleTxt;

    public HeaderView(Context context) {
        this(context, null);
    }

    public HeaderView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public HeaderView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        initView();
    }

	private void initView() {
		View headerView = LayoutInflater.from(getContext()).inflate(R.layout.view_header_layout, this);

		if (headerView != null) {
			mToolbar = (Toolbar) headerView.findViewById(R.id.activity_public_toolbar);
			mTitleTxt = (TextView) headerView.findViewById(R.id.activity_public_title_txt);

			headerView.findViewById(R.id.activity_public_back_view).setOnClickListener(this);
		}
	}

	public Toolbar getToolbar() {
		return mToolbar;
	}

	/**
	 * 设置标题
	 * 
	 * @param txt
	 */
	public void setTitile(String txt) {
		mTitleTxt.setText(txt);
	}

	@Override
	public void onClick(View view) {
		try {
			((Activity) getContext()).finish();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
