package com.qkl.online.mining.app.ui.adapter;

import android.content.Context;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.qkl.online.mining.app.R;
import com.qkl.online.mining.app.data.entity.GameBean;
import com.qkl.online.mining.app.utils.glide.GlideImageLoader;

import java.util.List;

/**
 * author：oyb on 2018/8/28 17:19
 * 游戏列表
 */
public class GameAdapter extends BaseQuickAdapter<GameBean.ListBean, BaseViewHolder> {

    private Context mContext;

    public GameAdapter(Context context) {
        super(R.layout.item_game_layout);
        mContext = context;
    }

    @Override
    protected void convert(BaseViewHolder helper, GameBean.ListBean item) {

        ImageView imageView = helper.getView(R.id.item_game_icon_view);
        GlideImageLoader.loadImage(mContext, item.getGameImg(), imageView);

        helper.setText(R.id.item_game_name_txt, item.getGameName());
        helper.setText(R.id.item_game_rulename_txt, item.getRuleDesc());

        // 0任务未完成,1任务已完成
        helper.setVisible(R.id.item_game_task_finish_view, item.getTaskStatus() == 1 ? true : false);

    }

}
