package com.qkl.online.mining.app.mvp.view;

import com.qkl.online.mining.app.data.entity.BannerBean;
import com.qkl.online.mining.app.data.entity.BaseBean;
import com.qkl.online.mining.app.data.entity.HomeNews;
import com.qkl.online.mining.app.utils.suitlines.Unit;

import java.util.List;

/**
 *
 * @param <T>
 */

public interface IHomeView<T extends BaseBean> extends IBaseView {

    void resultBanner(BannerBean bannerBean);

    void resultNews(HomeNews homeNews);

    void resultRollNews(HomeNews homeNews);

    void resultPool(String yunAmount, String yuntHashrate);

    void resultYunExchangeRate(List<Unit> lines);

}
