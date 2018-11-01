package com.qkl.online.mining.app.utils.update;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.widget.TextView;

import com.google.gson.JsonObject;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.qkl.online.mining.app.R;
import com.qkl.online.mining.app.data.json.JsonCallback;
import com.qkl.online.mining.app.utils.CommonsUtils;

/**
 * author：oyb on 2018/7/7 14:50
 * 版本检测工具类
 */
public class UpdateManager {

    private static UpdateManager mInstance;

    private MyIUpdateAgent mIUpdateAgent;
    private IUpdatePrompter mPrompter;

    public static UpdateManager getInstance() {
        if(mInstance == null) {
            synchronized (UpdateManager.class) {
                if(mInstance == null) {
                    mInstance = new UpdateManager();
                }
            }
        }
        return mInstance;
    }

    private UpdateManager() {
        mPrompter = new DefaultUpdatePrompter();
        mIUpdateAgent = new MyIUpdateAgent();
    }

    /**
     * 检测服务端是否最新版本
     */
    public void checkUpdate(Context context, boolean isShowLoading) {

        updateTask(context, isShowLoading);
    }

    private static class MyIUpdateAgent implements IUpdateAgent {

        private UpdateInfo mUpdateInfo;

        public MyIUpdateAgent() {

        }

        @Override
        public void setInfo(UpdateInfo updateInfo) {
            mUpdateInfo = updateInfo;
        }

        @Override
        public UpdateInfo getInfo() {
            return mUpdateInfo;
        }

        @Override
        public void update() {

        }

        @Override
        public void ignore() {

        }
    }

    private void updateTask(final Context context, final boolean isShowLoading) {
        String url = "https://update.zsdfm.com/v4/kanshushenqi/version.html";

        if(isShowLoading) {
            showLoading(context, null);
        }
        OkGo.<JsonObject>get(url).execute(new JsonCallback<JsonObject>() {
            @Override
            public void onSuccess(Response<JsonObject> response) {

                Log.e("===1==", response.toString());

                if(context != null && !((Activity) context).isFinishing()) {

                    mIUpdateAgent.setInfo(new UpdateInfo());
                    mPrompter.prompt(context, mIUpdateAgent);
                }

                hideLoading();
            }

            @Override
            public void onError(Response<JsonObject> response) {
                super.onError(response);
                hideLoading();
            }
        });
    }

    private static class DefaultUpdatePrompter implements IUpdatePrompter {

        private Context mContext;

        public DefaultUpdatePrompter() {
        }

        @Override
        public void prompt(Context context, IUpdateAgent agent) {
            if (context instanceof Activity && ((Activity) context).isFinishing()) {
                return;
            }
            mContext = context;

            final UpdateInfo info = agent.getInfo();
            info.updateContent = "aaaaaaaaaaaaaaaaaaa\naaaaaaaaaaaaaaaaaaa\naaaaaaaaaaaaaaaaaaa\naaaaaaaaaaaaaaaaaaa\naaaaaaaaaaaaaaaaaaa\naaaaaaaaaaaaaaaaaaa\naaaaaaaaaaaaaaaaaaa\naaaaaaaaaaaaaaaaaaa\naaaaaaaaaaaaaaaaaaa\naaaaaaaaaaaaaaaaaaa\naaaaaaaaaaaaaaaaaaa\naaaaaaaaaaaaaaaaaaa\naaaaaaaaaaaaaaaaaaa\naaaaaaaaaaaaaaaaaaa\naaaaaaaaaaaaaaaaaaa\naaaaaaaaaaaaaaaaaaa\naaaaaaaaaaaaaaaaaaa\naaaaaaaaaaaaaaaaaaa\naaaaaaaaaaaaaaaaaaa\naaaaaaaaaaaaaaaaaaa\naaaaaaaaaaaaaaaaaaa\naaaaaaaaaaaaaaaaaaa\naaaaaaaaaaaaaaaaaaa\naaaaaaaaaaaaaaaaaaa\naaaaaaaaaaaaaaaaaaa\naaaaaaaaaaaaaaaaaaa\naaaaaaaaaaaaaaaaaaa\naaaaaaaaaaaaaaaaaaa\naaaaaaaaaaaaaaaaaaa\naaaaaaaaaaaaaaaaaaa\naaaaaaaaaaaaaaaaaaa\naaaaaaaaaaaaaaaaaaa\naaaaaaaaaaaaaaaaaaa\naaaaaaaaaaaaaaaaaaa\naaaaaaaaaaaaaaaaaaa\naaaaaaaaaaaaaaaaaaa\naaaaaaaaaaaaaaaaaaa\naaaaaaaaaaaaaaaaaaa\naaaaaaaaaaaaaaaaaaa\naaaaaaaaaaaaaaaaaaa\naaaaaaaaaaaaaaaaaaa\naaaaaaaaaaaaaaaaaaa\naaaaaaaaaaaaaaaaaaa\naaaaaaaaaaaaaaaaaaa\naaaaaaaaaaaaaaaaaaa\naaaaaaaaaaaaaaaaaaa\naaaaaaaaaaaaaaaaaaa\n";
            String content = String.format("更新内容\n%1$s", info.updateContent);

            final AlertDialog dialog = new AlertDialog.Builder(mContext).create();

            dialog.setTitle("应用更新");
            dialog.setCancelable(false);
            dialog.setCanceledOnTouchOutside(false);


            float density = mContext.getResources().getDisplayMetrics().density;
            TextView tv = new TextView(mContext);
            tv.setMovementMethod(new ScrollingMovementMethod());
            tv.setVerticalScrollBarEnabled(true);
            tv.setTextSize(14);
            tv.setMaxHeight((int) (250 * density));

            dialog.setView(tv, (int) (25 * density), (int) (15 * density), (int) (25 * density), 0);


            DialogInterface.OnClickListener listener = new DefaultPromptClickListener(agent, true);

            if (info.isForce) {
                tv.setText("您需要更新应用才能继续使用\n\n" + content);
                dialog.setButton(DialogInterface.BUTTON_POSITIVE, "确定", listener);
            } else {
                tv.setText(content);
                dialog.setButton(DialogInterface.BUTTON_POSITIVE, "立即更新", listener);
                dialog.setButton(DialogInterface.BUTTON_NEGATIVE, "以后再说", listener);
            }
            dialog.show();
        }
    }

    private Dialog progressDialog;

    public void showLoading(Context context, String message) {
        try {
            if(progressDialog == null) {
                progressDialog = new Dialog(context, R.style.progress_dialog);
                progressDialog.setContentView(R.layout.dialog_loading_layout);
                progressDialog.setCancelable(false);
                progressDialog.setCanceledOnTouchOutside(false);
                progressDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
            }
            if(TextUtils.isEmpty(message)) {
                message = CommonsUtils.getXmlString(context, R.string.progress_dialog_holdon);
            }
            ((TextView) progressDialog.findViewById(R.id.id_tv_loadingmsg)).setText(message);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if(progressDialog != null) {
            progressDialog.show();
        }
    }

    public void hideLoading() {
        if(progressDialog != null && progressDialog.isShowing()) {
            progressDialog.dismiss();
        }
    }

}
