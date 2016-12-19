package com.mainiway.eworkpal.adapter;

import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.mainiway.eworkpal.R;
import com.mainiway.eworkpal.model.AttendanceLateModle;

import java.util.List;

/**
 * Created by zhangbiao on 2016/12/13 00013.
 * 考勤-统计-迟到-适配器
 */

public class AttendanceLateAdapter extends BaseQuickAdapter<AttendanceLateModle,BaseViewHolder> {

    public AttendanceLateAdapter(int layoutResId, List<AttendanceLateModle> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, AttendanceLateModle item) {
            if(item.getAppeal_state().equals("未申诉")){
                baseViewHolder.getView(R.id.appeal_state_tv).setVisibility(View.GONE);
                baseViewHolder.getView(R.id.appeal_tv).setVisibility(View.VISIBLE);
                baseViewHolder.setText(R.id.appeal_tv,"申诉");
            }else if(item.getAppeal_state().equals("处理中")){
                baseViewHolder.getView(R.id.appeal_state_tv).setVisibility(View.VISIBLE);
                baseViewHolder.getView(R.id.appeal_tv).setVisibility(View.GONE);
                baseViewHolder.setText(R.id.appeal_state_tv,"申诉待处理");
            }else if(item.getAppeal_state().equals("驳回")){
                baseViewHolder.getView(R.id.appeal_state_tv).setVisibility(View.VISIBLE);
                baseViewHolder.getView(R.id.appeal_tv).setVisibility(View.VISIBLE);
                baseViewHolder.setText(R.id.appeal_state_tv,"申诉被驳回");
                baseViewHolder.setText(R.id.appeal_tv,"重新申诉");
            }
        baseViewHolder.addOnClickListener(R.id.appeal_tv);
    }
}
