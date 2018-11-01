package com.qkl.online.mining.app.ui.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.qkl.online.mining.app.R;
import com.qkl.online.mining.app.application.AccountManager;
import com.qkl.online.mining.app.data.entity.Exchange;
import com.qkl.online.mining.app.utils.CommonsUtils;
import com.qkl.online.mining.app.utils.TimeUtils;

import java.util.List;

/**
 * author：oyb on 2018/9/16 22:53
 * 兑换历史
 */
public class ExchangeHistoryAdapter extends BaseQuickAdapter<Exchange.ListBean, BaseViewHolder> {

    public ExchangeHistoryAdapter() {
        super(R.layout.item_exchange_history);
    }

    @Override
    protected void convert(BaseViewHolder helper, Exchange.ListBean item) {

        helper.setText(R.id.item_exchange_price_txt, item.getExchangeAmount() + " " + item.getExchangeMoney());

        helper.setText(R.id.item_exchange_date_txt, TimeUtils.millis2String(item.getAddTime()));
        String status = AccountManager.getInstance().getExchangeStatus(item.getTxid(), item.getExchangeStatus());
        helper.setText(R.id.item_exchange_status_txt, status);

    }

}
