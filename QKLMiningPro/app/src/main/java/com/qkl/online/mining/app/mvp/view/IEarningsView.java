package com.qkl.online.mining.app.mvp.view;

import com.qkl.online.mining.app.data.entity.BaseBean;
import com.qkl.online.mining.app.data.entity.Earnings;

/**
 *
 * @param <T>
 */

public interface IEarningsView<T extends BaseBean> extends IBaseView {

    void resultEarnings(Earnings earnings);

}
