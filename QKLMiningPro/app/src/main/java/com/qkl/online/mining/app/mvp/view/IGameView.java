package com.qkl.online.mining.app.mvp.view;

import com.qkl.online.mining.app.data.entity.BannerBean;
import com.qkl.online.mining.app.data.entity.BaseBean;
import com.qkl.online.mining.app.data.entity.GameBean;

/**
 *
 * @param <T>
 */

public interface IGameView<T extends BaseBean> extends IBaseView {

    void resultBanner(BannerBean bannerBean);

    void resultGame(GameBean gameBean);

}
