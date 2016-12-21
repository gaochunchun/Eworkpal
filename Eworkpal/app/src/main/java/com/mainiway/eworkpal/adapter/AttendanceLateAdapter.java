package com.mainiway.eworkpal.adapter;

import com.mainiway.eworkpal.R;
import com.mainiway.eworkpal.model.AttendanceLateModle;
import com.mainiway.library.adapter.base.BaseMultiItemQuickAdapter;
import com.mainiway.library.adapter.base.BaseViewHolder;

import java.util.List;

/**
 * Created by zhangbiao on 2016/12/13 00013.
 * 考勤-统计-迟到-适配器
 */

public class AttendanceLateAdapter extends BaseMultiItemQuickAdapter<AttendanceLateModle, BaseViewHolder> {

    public AttendanceLateAdapter( List<AttendanceLateModle> data) {
        super( data);
        addItemType(0, R.layout.item_statistics_late);//迟到
        addItemType(1, R.layout.item_statistics_late);//早退
        addItemType(2, R.layout.item_statistics_un_sign);//未签到
        addItemType(3, R.layout.item_statistics_un_sign);//未签退
        addItemType(4, R.layout.item_attendance_statistics_field);//外勤

    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, AttendanceLateModle item) {
        if(item.state==0||item.state==1){//迟到 早退
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
        }else if(item.state==2||item.state==3){//未签退 未签到

        }else if(item.state==4){//外勤

        }
    }
}
