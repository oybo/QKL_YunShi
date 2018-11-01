package com.qkl.online.mining.app.mvp.presenter;

import android.app.Activity;
import com.qkl.online.mining.app.mvp.view.IBaseView;


/**
 *
 * @param <GV>
 */

public abstract class BasePresenter<GV extends IBaseView> {

    protected GV mView;
    protected Activity mContext;

    public BasePresenter(Activity context, GV view) {
        mContext = context;
        mView = view;
    }

    protected BasePresenter() {
    }

    public long getCurrentTime(){
        java.util.Date date = new java.util.Date();
        long datetime = date.getTime();
        return datetime;
    }



}
