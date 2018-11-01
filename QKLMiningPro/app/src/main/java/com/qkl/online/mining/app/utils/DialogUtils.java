package com.qkl.online.mining.app.utils;

import android.app.ProgressDialog;
import android.content.Context;

import com.qkl.online.mining.app.R;

/**
 * author：oyb on 2018/9/2 00:04
 */
public class DialogUtils {

    private ProgressDialog mProgressDialog;
    private Context mContext;

    public DialogUtils(Context context) {
        mContext = context;
        mProgressDialog = new ProgressDialog(context);
        mProgressDialog.setCanceledOnTouchOutside(false);
        mProgressDialog.setCancelable(false);
    }

    public void showProgressDialog(boolean show, String message) {
        if (show) {
            mProgressDialog.setMessage(message);
            mProgressDialog.show();

            KeyBoardUtils.closeKeybord(mContext);
        } else {
            mProgressDialog.hide();
        }
    }

    public void showProgressDialog(boolean show) {
        showProgressDialog(show, CommonsUtils.getXmlString(mContext, R.string.progress_dialog_holdon));
    }

    public void dismiss() {
        mProgressDialog.dismiss();
    }

}
