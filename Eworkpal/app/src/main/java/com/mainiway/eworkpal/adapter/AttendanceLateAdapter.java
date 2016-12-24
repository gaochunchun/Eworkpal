package com.mainiway.eworkpal.adapter;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

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
    private Context context;
    public AttendanceLateAdapter(List<AttendanceLateModle> data, Context context) {
        super( data);
        this.context=context;
        addItemType(0, R.layout.item_statistics_late);//迟到
        addItemType(1, R.layout.item_statistics_late);//早退
        addItemType(2, R.layout.item_statistics_un_sign);//未签到
        addItemType(3, R.layout.item_statistics_un_sign);//未签退
        addItemType(4, R.layout.item_attendance_statistics_field);//外勤
        addItemType(5, R.layout.item_attendance_statistics_equipment_abnorma);//设备异常
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, AttendanceLateModle item) {
        //公共显示信息
        baseViewHolder.setText(R.id.date_tv, "日期");
        baseViewHolder.setText(R.id.day_of_week, "星期几");
        baseViewHolder.setText(R.id.state_tv, "打卡状态");
        //为了逻辑方便 就不进行简化了
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
            baseViewHolder.setText(R.id.type_tv, "签到类型");
            baseViewHolder.setText(R.id.time_tv, "签到时间");
            baseViewHolder.setText(R.id.address_tv, "签到地址");
        }else if(item.state==2||item.state==3){//未签退 未签到

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

        }else if(item.state==4){//外勤
            baseViewHolder.setText(R.id.time_tv, "签到时间");
            baseViewHolder.setText(R.id.address_tv, "签到地址");
            baseViewHolder.setText(R.id.field_matter_tv, "外勤事由");
            //如果有图片
            RecyclerView recyclerView=baseViewHolder.getView(R.id.field_matter_recycleview);
            RecyclerView.LayoutManager layoutManager=new GridLayoutManager(context,3);
            recyclerView.setLayoutManager(layoutManager);
        }else if(item.state==5){//设备异常
            baseViewHolder.setText(R.id.time_tv, "签到时间");
            baseViewHolder.setText(R.id.address_tv, "签到地址");
            baseViewHolder.setText(R.id.equipment_type_tv, "设备名");
        }
    }
}
