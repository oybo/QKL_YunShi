package com.qkl.online.mining.app.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Base64;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.orhanobut.hawk.Hawk;
import com.qkl.online.mining.app.R;
import com.qkl.online.mining.app.application.AccountManager;
import com.qkl.online.mining.app.data.commons.Constants;
import com.qkl.online.mining.app.listener.OnDialogOkListenner;
import com.qkl.online.mining.app.listener.OnDragViewClickListener;
import com.qkl.online.mining.app.ui.view.DialogTips;
import com.qkl.online.mining.app.ui.view.DragView;
import com.qkl.online.mining.app.ui.view.HeaderView;
import com.qkl.online.mining.app.ui.view.ProgressWheel;
import com.qkl.online.mining.app.ui.view.browse.ProgressBarWebView;
import com.qkl.online.mining.app.utils.CommonsUtils;
import com.qkl.online.mining.app.utils.ScreenListener;
import com.qkl.online.mining.app.utils.ToastUtils;
import com.qkl.online.mining.app.utils.glide.GlideImageLoader;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * author：oyb on 2018/9/5 19:08
 * 游戏网页
 */
public class GameWebViewActivity extends AppCompatActivity {

    @BindView(R.id.activity_webview_headerview)
    HeaderView mHeaderView;
    @BindView(R.id.fragment_game_exit)
    DragView mExitView;
    @BindView(R.id.fragment_game_prigressbar)
    ProgressWheel mProgressWheel;
    @BindView(R.id.activity_webview_webview)
    ProgressBarWebView mWebView;

    @BindView(R.id.fragment_game_loading_view)
    ImageView mGameLoadingView;

    private AudioManager mAudioManager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_webview);

        //初始化ButterKnife
        ButterKnife.bind(this);

        Intent intent = getIntent();
        String title = intent.getStringExtra("title");
        String url = intent.getStringExtra("url");

        initView(title);

        Map<String, String> headers = new HashMap<>();
        try {
            String str = AccountManager.getInstance().getEmail() + ":" + Hawk.get(Constants.LOGIN_PASSWORD_KEY);
            String value = "Basic " + Base64.encodeToString(str.getBytes("utf-8"), Base64.NO_WRAP);
            headers.put("Authorization", value);
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (!TextUtils.isEmpty(url)) {
            mWebView.loadUrl(url, headers);
        }

        mWebView.setWebViewTitleListener(new ProgressBarWebView.OnWebViewListener() {
            @Override
            public void onReceivedTitle(String title) {

            }

            @Override
            public void onLoadFinish() {
                mExitView.setVisibility(View.VISIBLE);
                mProgressWheel.setVisibility(View.GONE);
                mHeaderView.setVisibility(View.GONE);
                mGameLoadingView.setVisibility(View.GONE);
            }
        });

        mExitView.SetClickListener(new OnDragViewClickListener() {
            @Override
            public void onDragViewListener(String name, String context) {
                exit();
            }
        });

        mAudioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
        ScreenListener screenListener = new ScreenListener(this);
        screenListener.begin(new ScreenListener.ScreenStateListener() {
            @Override
            public void onScreenOn() {
                // 开屏
            }

            @Override
            public void onScreenOff() {
                // 锁屏静音
                if (Build.VERSION.SDK_INT >= 23) {
                    mAudioManager.setStreamMute(AudioManager.STREAM_MUSIC, true);
                } else {
                    mAudioManager.adjustStreamVolume(AudioManager.STREAM_MUSIC, AudioManager.ADJUST_MUTE, AudioManager.FLAG_SHOW_UI);
                }
                mAudioManager.setMicrophoneMute(true);
            }

            @Override
            public void onUserPresent() {
                // 解锁打开声音
                if (Build.VERSION.SDK_INT >= 23) {
                    mAudioManager.setStreamMute(AudioManager.STREAM_MUSIC, false);
                } else {
                    mAudioManager.adjustStreamVolume(AudioManager.STREAM_MUSIC, AudioManager.ADJUST_UNMUTE, AudioManager.FLAG_SHOW_UI);
                }
                mAudioManager.setMicrophoneMute(false);
            }
        });
    }

    private void initView(String title) {
        mHeaderView.setTitile(title);
        mHeaderView.getToolbar().setTitle("");
        setSupportActionBar(mHeaderView.getToolbar());
    }

    @Override
    public void onBackPressed() {
//        super.onBackPressed();

        if (!mWebView.getWebView().canGoBack()) {
            exit();
        }
    }

    private void exit() {
        // 提示是否退出
        DialogTips dialogTips = new DialogTips(this);
        dialogTips.setMessage(CommonsUtils.getXmlString(this, R.string.game_is_exit_txt));
        dialogTips.setOkListenner(new OnDialogOkListenner() {
            @Override
            public void onClick() {
                finish();
            }
        });
        dialogTips.setCancelListenner(null);
        dialogTips.setCanceledOnTouchOutside(false);
        dialogTips.show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        AccountManager.getInstance().resetLogin();
    }

}
