package com.mainiway.eworkpal.activity.attendance;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.mainiway.eworkpal.R;
import com.mainiway.eworkpal.base.BaseTitleActivity;


/**
 * Created by zhangbiao on 2016/12/13 00013.
 * 考勤统计
 */

public class StatisticsActivity extends BaseTitleActivity {
    //迟到 早退 未签到 未签退 外勤 设备异常
    private TextView late_tv, leave_early_tv, un_sign_in_tv, un_sign_out_tv, field_tv, equipment_abnorma_tv;
    //请假 加班 出差
    private TextView leave_tv, over_time_tv, business_travel_tv;
    //签到状态
    private String SIGN_TYPE;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attendance_statistics);
        setTitle("统计");
        showBackwardView(true);
        showForwardView("团队成员");
        init();
    }

    private void init() {
        late_tv = findView(R.id.late_tv);
        late_tv.setOnClickListener(this);

        leave_early_tv = findView(R.id.leave_early_tv);
        leave_early_tv.setOnClickListener(this);

        un_sign_in_tv = findView(R.id.un_sign_in_tv);
        un_sign_in_tv.setOnClickListener(this);

        un_sign_out_tv = findView(R.id.un_sign_out_tv);
        un_sign_out_tv.setOnClickListener(this);

        field_tv = findView(R.id.field_tv);
        field_tv.setOnClickListener(this);

        equipment_abnorma_tv = findView(R.id.equipment_abnorma_tv);
        equipment_abnorma_tv.setOnClickListener(this);

        leave_tv = findView(R.id.leave_tv);
        leave_tv.setOnClickListener(this);

        over_time_tv = findView(R.id.over_time_tv);
        over_time_tv.setOnClickListener(this);

        business_travel_tv = findView(R.id.business_travel_tv);
        business_travel_tv.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        Intent intent=null;
        //请假
        if (v.equals(leave_tv)) {
            startActivity(new Intent(this, LeaveListActivity.class));
            return;
        }
        //加班
        if (v.equals(over_time_tv)) {
            return;
        }
        //出差
        if (v.equals(business_travel_tv)) {
            return;
        }

        switch (v.getId()) {
            case R.id.late_tv://迟到
                SIGN_TYPE = "迟到";
                break;
            case R.id.leave_early_tv://早退
                SIGN_TYPE = "早退";
                break;
            case R.id.un_sign_in_tv://未签到
                SIGN_TYPE = "未签到";
                break;
            case R.id.un_sign_out_tv://未签退
                SIGN_TYPE = "未签退";
                break;
            case R.id.field_tv://外勤
                SIGN_TYPE = "外勤";
                break;
            case R.id.equipment_abnorma_tv://设备异常
                SIGN_TYPE = "设备异常";
                break;
        }
        intent = new Intent(this, LateListActivity.class);
        intent.putExtra("SIGN_TYPE", SIGN_TYPE);
        startActivity(intent);
    }
}
