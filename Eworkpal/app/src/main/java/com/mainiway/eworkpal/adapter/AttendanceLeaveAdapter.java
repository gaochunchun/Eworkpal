package com.mainiway.eworkpal.adapter;

import com.mainiway.library.adapter.base.BaseQuickAdapter;
import com.mainiway.library.adapter.base.BaseViewHolder;
import com.mainiway.eworkpal.model.AttendanceLeaveModle;

import java.util.List;

/**
 *
 * Created by zhangbiao on 2016/12/15 00015.
 * 考勤-统计-请假列表-适配器
 */

public class AttendanceLeaveAdapter extends BaseQuickAdapter<AttendanceLeaveModle,BaseViewHolder>{
    public AttendanceLeaveAdapter(int layoutResId, List<AttendanceLeaveModle> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, AttendanceLeaveModle item) {

    }


}
