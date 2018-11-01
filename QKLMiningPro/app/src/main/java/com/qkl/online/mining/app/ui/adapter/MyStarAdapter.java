package com.qkl.online.mining.app.ui.adapter;

import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.support.v4.view.PagerAdapter;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.qkl.online.mining.app.R;
import com.qkl.online.mining.app.application.AccountManager;
import com.qkl.online.mining.app.data.entity.MyStar;
import com.qkl.online.mining.app.utils.CommonsUtils;
import com.qkl.online.mining.app.utils.glide.GlideImageLoader;

import java.util.List;

/**
 * author：oyb on 2018/8/31 23:08
 * 我的星球适配器
 */
public class MyStarAdapter extends PagerAdapter {

    private Context mContext;
    private List<MyStar> mMyStarList;

    private AnimationDrawable mAnimationDrawable;

    public MyStarAdapter(Context context, List<MyStar> starList) {
        mContext = context;
        mMyStarList = starList;
    }

    @Override
    public int getCount() {
        return mMyStarList != null ? mMyStarList.size() : 0;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    /**
     * 返回要显示的view,即要显示的视图
     */
    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_my_xingqiu_layout, null);

        MyStar myStar = mMyStarList.get(position);

        // 图片
        ImageView imageView = view.findViewById(R.id.item_my_xingqiu_image);
        if(!TextUtils.isEmpty(myStar.getMinerImg())) {
            GlideImageLoader.loadImage(mContext, myStar.getMinerImg(), imageView);
        } else {
            GlideImageLoader.loadImage(mContext, R.drawable.star_defult_image, imageView);
        }

        // 名称
        TextView nameTxt = view.findViewById(R.id.item_my_xingqiu_name_txt);
        nameTxt.setText(CommonsUtils.getXmlString(mContext, R.string.purchase_star_title_txt, myStar.getMinerName()));

        // 算力
        TextView suanliTxt = view.findViewById(R.id.item_my_xingqiu_suanli_txt);
        suanliTxt.setText(CommonsUtils.getXmlString(mContext, R.string.purchase_star_suanli_bt, myStar.getHashrate()));

        // 状态
        TextView zhuangtaiTxt = view.findViewById(R.id.item_my_xingqiu_zhuangtai_txt);
        String runStatus = "";
        int isRun = myStar.getIsRun();
        switch (isRun) {
            case -1:
                runStatus = CommonsUtils.getXmlString(mContext, R.string.kuangjie_no_run);
                break;
            case 0:
                runStatus = CommonsUtils.getXmlString(mContext, R.string.kuangjie_run_stop);
                break;
            case 1:
                runStatus = CommonsUtils.getXmlString(mContext, R.string.kuangjie_run);
                break;
        }
        zhuangtaiTxt.setText(CommonsUtils.getXmlString(mContext, R.string.purchase_star_zhuangtai_bt, runStatus));

        // 剩余
        TextView shengyuTxt = view.findViewById(R.id.item_my_xingqiu_shengyu_txt);
        shengyuTxt.setText(
                CommonsUtils.getXmlString(mContext, R.string.purchase_star_shengyu_bt, String.valueOf(myStar.getRemaining()))
                + CommonsUtils.getXmlString(mContext, R.string.time_day));

        // 累计挖出
        TextView wcljTxt = view.findViewById(R.id.item_my_xingqiu_wclj_txt);
        wcljTxt.setText(CommonsUtils.getXmlString(mContext, R.string.purchase_star_ljwc_bt, String.valueOf(myStar.getAccumulated())));

        if(isRun == 1) {
            ImageView waKuangImage = view.findViewById(R.id.item_wakuang_imageview);
            if(waKuangImage != null) {
                waKuangImage.setVisibility(View.VISIBLE);
                mAnimationDrawable = (AnimationDrawable) waKuangImage.getDrawable();
                mAnimationDrawable.start();
            }
        }

        container.addView(view);
        return view;
    }

    /**
     * 销毁条目
     */
    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    public void onResume() {
        if(mAnimationDrawable != null) {
            mAnimationDrawable.start();
        }
    }

    public void onPause() {
        if(mAnimationDrawable != null) {
            mAnimationDrawable.stop();
        }
    }

}
