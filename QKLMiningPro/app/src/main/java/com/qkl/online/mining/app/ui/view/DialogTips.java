package com.qkl.online.mining.app.ui.view;

import android.content.Context;
import android.support.v7.app.AppCompatDialog;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

import com.qkl.online.mining.app.R;
import com.qkl.online.mining.app.listener.NoDoubleClickListener;
import com.qkl.online.mining.app.listener.OnDialogCancelListenner;
import com.qkl.online.mining.app.listener.OnDialogOkListenner;

/**
 * 自定义提示框
 * 
 * 
 */
public class DialogTips extends AppCompatDialog {

	private Context mContext;

	private TextView mTitleTxt;
	private TextView mMessageTxt;

	private Button mOkBt;
	private Button mCancelBt;

	private OnDialogOkListenner mOkListenner;
	private OnDialogCancelListenner mCancelListenner;

	public DialogTips(Context context) {
		this(context, "");
	}

	public DialogTips(Context context, String message) {
		this(context, "", message);
	}

	public DialogTips(Context context, String title, String message) {
		super(context, R.style.signin_dialog_style);

		mContext = context;
		init();

		setTitle(title);
		setMessage(message);

		mOkBt.setVisibility(View.VISIBLE);
	}

	private void init() {
		setContentView(R.layout.dialog_tips);
		Window window = getWindow();
		window.getAttributes().width = LayoutParams.MATCH_PARENT;
		window.getAttributes().height = LayoutParams.WRAP_CONTENT;
		window.getAttributes().gravity = Gravity.CENTER;

		mTitleTxt = (TextView) findViewById(R.id.dialog_title);
		mMessageTxt = (TextView) findViewById(R.id.dialog_message);

		mOkBt = (Button) findViewById(R.id.dialog_ok);
		mCancelBt = (Button) findViewById(R.id.dialog_cancel);

		mOkBt.setOnClickListener(listener);
		mCancelBt.setOnClickListener(listener);
	}

	private NoDoubleClickListener listener = new NoDoubleClickListener() {
		@Override
		protected void onNoDoubleClick(View v) {
			switch (v.getId()) {
				case R.id.dialog_ok:
					if (mOkListenner != null) {
						mOkListenner.onClick();
					}
					dismiss();
					break;
				case R.id.dialog_cancel:
					if (mCancelListenner != null) {
						mCancelListenner.onClick();
					}
					dismiss();
					break;
			}
		}
	};

	public void setTitle(String title) {
		if (!TextUtils.isEmpty(title)) {
			mTitleTxt.setText(title);
		}
	}

	public void setMessage(String message) {
		mMessageTxt.setText(message);
	}

	public void setOkListenner(OnDialogOkListenner listenner) {
		setOkListenner("", listenner);
	}

	public void setOkListenner(String buttonTxt, OnDialogOkListenner listenner) {
		if (!TextUtils.isEmpty(buttonTxt)) {
			mOkBt.setText(buttonTxt);
		}

		mOkBt.setVisibility(View.VISIBLE);
		this.mOkListenner = listenner;
	}

	public void setCancelListenner(OnDialogCancelListenner listenner) {
		setCancelListenner("", listenner);
	}

	public void setCancelListenner(String buttonTxt, OnDialogCancelListenner listenner) {
		if (!TextUtils.isEmpty(buttonTxt)) {
			mCancelBt.setText(buttonTxt);
		}

		mCancelBt.setVisibility(View.VISIBLE);
		this.mCancelListenner = listenner;
	}

}