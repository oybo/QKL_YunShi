package com.qkl.online.mining.app.mvp.view;

import com.qkl.online.mining.app.data.entity.BaseBean;
import com.qkl.online.mining.app.data.entity.TeamBean;

/**
 *
 * @param <T>
 */

public interface IMyteamView<T extends BaseBean> extends IBaseView {

    void result(TeamBean teamBean);

}
