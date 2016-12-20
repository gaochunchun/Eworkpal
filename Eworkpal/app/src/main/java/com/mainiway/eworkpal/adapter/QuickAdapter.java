package com.mainiway.eworkpal.adapter;

import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.mainiway.library.adapter.base.BaseQuickAdapter;
import com.mainiway.library.adapter.base.BaseViewHolder;
import com.mainiway.eworkpal.R;
import com.mainiway.eworkpal.activity.test.DataServer;
import com.mainiway.eworkpal.activity.test.GlideCircleTransform;
import com.mainiway.eworkpal.model.Status;

/**
 * ===========================================
 * 作    者：gao_chun
 * 版    本：1.0
 * 创建日期：2016-11-19.
 * 描    述：测试
 * ===========================================
 */
public class QuickAdapter extends BaseQuickAdapter<Status, BaseViewHolder> {
    public QuickAdapter() {
        super(R.layout.test_activity_adapter_tweet, DataServer.getSampleData(100));
    }

    public QuickAdapter(int dataSize) {
        super(R.layout.test_activity_adapter_tweet, DataServer.getSampleData(dataSize));
    }

    @Override
    protected void convert(BaseViewHolder helper, Status item) {
        helper.setText(R.id.tweetName, item.getUserName())
                .setText(R.id.tweetText, item.getText())
                .setText(R.id.tweetDate, item.getCreatedAt())
                //.setVisible(R.id.tweetRT, item.isRetweet())
                .addOnClickListener(R.id.tweetAvatar)
                .addOnClickListener(R.id.tweetName)
                .linkify(R.id.tweetText);

        Glide.with(mContext).load(item.getUserAvatar()).crossFade().placeholder(R.mipmap.def_head).transform(new GlideCircleTransform(mContext)).into((ImageView) helper.getView(R.id.tweetAvatar));
    }


}
