package com.qkl.online.mining.app.utils.glide;

import android.content.Context;
import android.graphics.Bitmap;
import android.text.TextUtils;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.target.SimpleTarget;

/**
 * Created by ooo on 2017/8/19.
 */

public class GlideImageLoader {

    public static void loadImage(Context context, String url, ImageView imageView) {
        if (imageView == null || TextUtils.isEmpty(url)) {
            return;
        }
        Glide.with(context)
                .load(url)
                .placeholder(0)
                .fitCenter()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(imageView);
    }

    public static void loadImage(Context context, String url, SimpleTarget<Bitmap> simpleTarget) {
        if (TextUtils.isEmpty(url)) {
            return;
        }
        Glide.with(context)
                .load(url)
                .asBitmap()
                .into(simpleTarget);
    }

    public static void loadImage(Context context, int resid, ImageView imageView) {
        Glide.with(context)
                .load(resid)
                .placeholder(0)
                .into(imageView);
    }

    public static void loadRoundImage(Context context, String url, ImageView mCover) {
        Glide.with(context).load(url).centerCrop().transform(new GlideCircleTransform(context)).into(mCover);
    }

}
