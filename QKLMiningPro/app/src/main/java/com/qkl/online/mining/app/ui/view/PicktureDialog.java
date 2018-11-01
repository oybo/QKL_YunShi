package com.qkl.online.mining.app.ui.view;

import android.app.Dialog;
import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import com.qkl.online.mining.app.R;
import com.qkl.online.mining.app.listener.NoDoubleClickListener;

public class PicktureDialog extends Dialog implements View.OnClickListener {

	private NoDoubleClickListener mListener;

	public PicktureDialog(Context context, NoDoubleClickListener listener) {
		super(context, R.style.signin_dialog_style);

		this.mListener = listener;

		View view = View.inflate(context, R.layout.view_main_bookshelf_menu_layout, null);
		this.setContentView(view);

		Window dialogWindow = getWindow();
		WindowManager.LayoutParams lp = dialogWindow.getAttributes();
		lp.width = context.getResources().getDisplayMetrics().widthPixels;
		dialogWindow.setAttributes(lp);
		dialogWindow.setGravity(Gravity.BOTTOM);
		dialogWindow.setWindowAnimations(R.style.anim_popup_dir_icon);

		view.findViewById(R.id.pop_pickture_camera_txt).setOnClickListener(this);
		view.findViewById(R.id.pop_pickture_select_txt).setOnClickListener(this);

		view.findViewById(R.id.pop_cancel_txt).setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		mListener.onClick(v);
		dismiss();
	}

}
