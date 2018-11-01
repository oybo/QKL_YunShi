package com.qkl.online.mining.app.ui.adapter;

import android.content.Context;
import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.qkl.online.mining.app.R;
import com.qkl.online.mining.app.data.entity.StarProduct;
import com.qkl.online.mining.app.utils.CommonsUtils;
import com.qkl.online.mining.app.utils.glide.GlideImageLoader;

import java.util.List;

/**
 * author：oyb on 2018/8/27 18:37
 * 星球页面-矿机列表
 */
public class StarProductAdapter extends BaseQuickAdapter<StarProduct.ListEntity, BaseViewHolder> {

    public StarProductAdapter(Context context, @Nullable List<StarProduct.ListEntity> data) {
        super(R.layout.item_startproduct_layout, data);

        mContext = context;
    }

    @Override
    protected void convert(BaseViewHolder helper, StarProduct.ListEntity item) {

        ImageView iconView = helper.getView(R.id.item_startproduct_icon_view);
        GlideImageLoader.loadImage(mContext, item.getMinerImg(), iconView);

        // 名称
        helper.setText(R.id.item_startproduct_name_txt, item.getMinerName());

        // 价格
        helper.setText(R.id.item_startproduct_price_txt, CommonsUtils.getXmlString(mContext, R.string.public_price_txt, item.getSellPrice()));

        helper.addOnClickListener(R.id.item_startproduct_look_txt);
    }

}
