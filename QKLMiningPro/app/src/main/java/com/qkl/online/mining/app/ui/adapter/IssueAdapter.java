package com.qkl.online.mining.app.ui.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.qkl.online.mining.app.R;
import com.qkl.online.mining.app.data.entity.HomeNews;

import java.util.List;

/**
 * author：oyb on 2018/9/6 21:55
 * 常见问题
 */
public class IssueAdapter extends BaseQuickAdapter<HomeNews.ListEntity, BaseViewHolder> {

    public IssueAdapter(@Nullable List<HomeNews.ListEntity> data) {
        super(R.layout.item_common_issue_layout, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, HomeNews.ListEntity item) {

        helper.setText(R.id.item_issue_name_txt, item.getTitle());

    }

}
