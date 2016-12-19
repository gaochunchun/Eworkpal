package com.mainiway.eworkpal.activity.attendance;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.mainiway.eworkpal.R;
import com.mainiway.eworkpal.base.BaseTitleActivity;


/**
 * Created by zhangbiao on 2016/12/13 00013.
 * 考勤统计
 */

public class StatisticsActivity extends BaseTitleActivity {
    //迟到 早退 未签到 未签退 外勤 设备异常
    private LinearLayout late_layout,leave_early_layout,un_sign_in_layout,un_sign_out_layout,field_layout,equipment_abnorma_layout;
    //请假 加班 出差
    private LinearLayout leave_layout,over_time_layout,business_travel_layout;
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
    private void init(){
        late_layout=findView(R.id.late_layout);
        un_sign_in_layout=findView(R.id.un_sign_in_layout);
        leave_layout=findView(R.id.leave_layout);
        leave_early_layout=findView(R.id.leave_early_layout);
        un_sign_out_layout=findView(R.id.un_sign_out_layout);
        field_layout=findView(R.id.field_layout);
        equipment_abnorma_layout=findView(R.id.equipment_abnorma_layout);
        over_time_layout=findView(R.id.over_time_layout);
        business_travel_layout=findView(R.id.business_travel_layout);

        late_layout.setOnClickListener(this);
        un_sign_in_layout.setOnClickListener(this);
        leave_layout.setOnClickListener(this);
        leave_early_layout.setOnClickListener(this);
        un_sign_out_layout.setOnClickListener(this);
        field_layout.setOnClickListener(this);
        equipment_abnorma_layout.setOnClickListener(this);
        over_time_layout.setOnClickListener(this);
        business_travel_layout.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        super.onClick(v);

        //请假
        if(v.equals(leave_layout)){
            startActivity(new Intent(this,LeaveListActivity.class));
            return;
        }
        //加班
        if(v.equals(over_time_layout)){

            return;
        }
        //出差
        if(v.equals(business_travel_layout)){

            return;
        }

        switch (v.getId()){
            case R.id.late_layout ://迟到
                SIGN_TYPE="迟到";
                break;
            case R.id.leave_early_layout ://早退
                SIGN_TYPE="早退";
                break;
            case R.id.un_sign_in_layout ://未签到
                SIGN_TYPE="未签到";
                break;
            case R.id.un_sign_out_layout ://未签退
                SIGN_TYPE="未签退";
                break;
            case R.id.field_layout ://外勤
                SIGN_TYPE="外勤";
                break;
            case R.id.equipment_abnorma_layout ://设备异常
                SIGN_TYPE="设备异常";
                break;
            default:
                Intent intent =new Intent (this,LateListActivity.class);
                intent.putExtra("SIGN_TYPE",SIGN_TYPE);
                startActivity(intent);
        }
    }
}
