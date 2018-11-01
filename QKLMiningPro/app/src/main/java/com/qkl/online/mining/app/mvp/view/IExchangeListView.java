package com.qkl.online.mining.app.mvp.view;

import com.qkl.online.mining.app.data.entity.BaseBean;
import com.qkl.online.mining.app.data.entity.Exchange;
import com.qkl.online.mining.app.data.entity.ExchangeDetail;

/**
 *
 * @param <T>
 */

public interface IExchangeListView<T extends BaseBean> extends IBaseView {

    void resultExchange(Exchange exchange);

    void resultExchangeDetail(ExchangeDetail exchangeDetail);

}
