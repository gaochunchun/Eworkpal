package com.mainiway.eworkpal.adapter;

import com.mainiway.eworkpal.R;
import com.mainiway.eworkpal.model.AttendanceLateModle;
import com.mainiway.library.adapter.base.BaseQuickAdapter;
import com.mainiway.library.adapter.base.BaseViewHolder;

import java.util.List;

/**
 * Created by zhangbiao on 2016/12/13 00013.
 * 考勤-统计-迟到-适配器
 */

public class AttendanceLateAdapter extends BaseQuickAdapter<AttendanceLateModle, BaseViewHolder> {

    public AttendanceLateAdapter(int layoutResId, List<AttendanceLateModle> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, AttendanceLateModle item) {
        if (item.appeal_state.equals("未申诉")) {
            baseViewHolder.setVisible(R.id.appeal_state_tv, false);
            baseViewHolder.setVisible(R.id.appeal_tv, true);
            baseViewHolder.setText(R.id.appeal_tv, "申诉");
        } else if (item.appeal_state.equals("处理中")) {
            baseViewHolder.setVisible(R.id.appeal_state_tv, true);
            baseViewHolder.setVisible(R.id.appeal_tv, false);
            baseViewHolder.setText(R.id.appeal_state_tv, "申诉待处理");
        } else if (item.appeal_state.equals("驳回")) {
            baseViewHolder.setVisible(R.id.appeal_state_tv, true);
            baseViewHolder.setVisible(R.id.appeal_tv, true);
            baseViewHolder.setText(R.id.appeal_state_tv, "申诉被驳回");
            baseViewHolder.setText(R.id.appeal_tv, "重新申诉");
        }
        baseViewHolder.addOnClickListener(R.id.appeal_tv);
    }
}
