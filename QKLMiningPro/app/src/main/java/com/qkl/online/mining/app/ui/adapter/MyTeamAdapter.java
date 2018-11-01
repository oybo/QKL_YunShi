package com.qkl.online.mining.app.ui.adapter;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.text.TextUtils;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.qkl.online.mining.app.R;
import com.qkl.online.mining.app.data.entity.TeamBean;
import com.qkl.online.mining.app.ui.view.CircleImageView;
import com.qkl.online.mining.app.utils.CommonsUtils;
import com.qkl.online.mining.app.utils.TimeUtils;
import com.qkl.online.mining.app.utils.glide.GlideImageLoader;

import java.util.List;

/**
 * author：oyb on 2018/8/28 17:19
 * 我的团队 - 个人节点
 */
public class MyTeamAdapter extends BaseQuickAdapter<TeamBean.ListBean, BaseViewHolder> {

    public MyTeamAdapter(Context context) {
        super(R.layout.item_myteam_layout);

        mContext = context;
    }

    @Override
    protected void convert(BaseViewHolder helper, TeamBean.ListBean item) {

        CircleImageView imageView = helper.getView(R.id.item_myteam_header_image);

        String picture = item.getProfilePicture();
        if(!TextUtils.isEmpty(picture)) {
            GlideImageLoader.loadImage(mContext, picture, imageView);
        } else {
            GlideImageLoader.loadImage(mContext, R.drawable.myteam_logo, imageView);
        }

        String nickName = item.getNickName();
        if(TextUtils.isEmpty(nickName)) {
            nickName = CommonsUtils.getXmlString(mContext, R.string.userinfo_defult_nickname);
        }
        helper.setText(R.id.item_myteam_nickname_txt, nickName);

        helper.setText(R.id.item_myteam_email_txt, item.getEmail());

        helper.setText(R.id.item_myteam_levelname_txt, item.getLevelName());

        helper.setText(R.id.item_myteam_register_date_txt, TimeUtils.millis2String(item.getAddTime()));

    }

}
