package com.qkl.online.mining.app.ui.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.qkl.online.mining.app.R;
import com.qkl.online.mining.app.data.entity.HomeNews;
import com.qkl.online.mining.app.utils.TimeUtils;

/**
 * author：oyb on 2018/8/27 13:34
 * 主页底下的列表
 */
public class HomeNewsAdapter extends BaseQuickAdapter<HomeNews.ListEntity, BaseViewHolder> {

    public HomeNewsAdapter() {
        super(R.layout.item_home_news_layout);
    }

    @Override
    protected void convert(BaseViewHolder helper, HomeNews.ListEntity item) {

        helper.setText(R.id.item_home_news_title_txt, item.getTitle());

        helper.setText(R.id.item_home_news_date_txt, TimeUtils.millis2String(item.getUpdateTime()));
    }

}
