package com.qkl.online.mining.app.mvp.presenter;

import android.app.Activity;
import android.os.Handler;

import com.qkl.online.mining.app.mvp.view.IMainView;


/**
 * Created by 张皓然 on 2018/1/29.
 */

public class MainPresenter extends BasePresenter<IMainView>{

    private Handler mHandler;
    private Activity mContext;

    public MainPresenter(Activity context, IMainView view) {
        super(context, view);
        mHandler = new Handler(context.getMainLooper());
        mContext = context;
    }

    /**
     * 初始化
     */
    public void init(){
    }

}
