package com.qkl.online.mining.app.ui.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.qkl.online.mining.app.R;
import com.qkl.online.mining.app.data.entity.Earnings;
import com.qkl.online.mining.app.utils.TimeUtils;

import java.util.List;

/**
 * author：oyb on 2018/9/16 22:53
 */
public class JieDianEarningsAdapter extends BaseQuickAdapter<Earnings.ListBean, BaseViewHolder> {

    public JieDianEarningsAdapter() {
        super(R.layout.item_jiedian_earnings);
    }

    @Override
    protected void convert(BaseViewHolder helper, Earnings.ListBean item) {

        helper.setText(R.id.item_jiedian_earnings_price_txt, item.getBillAmount() + " YUNT");
        helper.setText(R.id.item_jiedian_earnings_type_txt, item.getDescription());
        helper.setText(R.id.item_jiedian_earnings_date_txt, TimeUtils.millis2String(item.getAddTime()));
        helper.setText(R.id.item_jiedian_earnings_status_txt, item.getOperatorName());

    }

}
