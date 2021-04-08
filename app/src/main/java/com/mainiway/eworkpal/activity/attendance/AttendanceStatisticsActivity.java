package com.mainiway.eworkpal.activity.attendance;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.mainiway.eworkpal.R;
import com.mainiway.eworkpal.base.BaseTitleActivity;
import com.mainiway.eworkpal.listener.OnClickFastListener;


/**
 * Created by zhangbiao on 2016/12/13 00013.
 * 考勤统计
 */

public class AttendanceStatisticsActivity extends BaseTitleActivity {
    //迟到 早退 未签到 未签退 外勤 设备异常
    private TextView late_tv, leave_early_tv, un_sign_in_tv, un_sign_out_tv, field_tv, equipment_abnorma_tv;
    //请假 加班 出差
    private TextView leave_tv, over_time_tv, business_travel_tv;
    //打卡状态
    private String SIGN_TYPE;
    //申请类型
    private String APPLICATION_TYPE;
    private MyOnclick myOnclick = new MyOnclick();

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
        late_tv.setOnClickListener(myOnclick);

        leave_early_tv = findView(R.id.leave_early_tv);
        leave_early_tv.setOnClickListener(myOnclick);

        un_sign_in_tv = findView(R.id.un_sign_in_tv);
        un_sign_in_tv.setOnClickListener(myOnclick);

        un_sign_out_tv = findView(R.id.un_sign_out_tv);
        un_sign_out_tv.setOnClickListener(myOnclick);

        field_tv = findView(R.id.field_tv);
        field_tv.setOnClickListener(myOnclick);

        equipment_abnorma_tv = findView(R.id.equipment_abnorma_tv);
        equipment_abnorma_tv.setOnClickListener(myOnclick);

        leave_tv = findView(R.id.leave_tv);
        leave_tv.setOnClickListener(myOnclick);

        over_time_tv = findView(R.id.over_time_tv);
        over_time_tv.setOnClickListener(myOnclick);

        business_travel_tv = findView(R.id.business_travel_tv);
        business_travel_tv.setOnClickListener(myOnclick);

    }

    private class MyOnclick extends OnClickFastListener {

        @Override
        public void onFastClick(View v) {
            Intent intent = null;
            APPLICATION_TYPE = "";
            SIGN_TYPE = "";
            switch (v.getId()) {
                case R.id.late_tv:
                    SIGN_TYPE = "迟到";
                    break;
                case R.id.leave_early_tv:
                    SIGN_TYPE = "早退";
                    break;
                case R.id.un_sign_in_tv:
                    SIGN_TYPE = "未签到";
                    break;
                case R.id.un_sign_out_tv:
                    SIGN_TYPE = "未签退";
                    break;
                case R.id.field_tv:
                    SIGN_TYPE = "外勤";
                    break;
                case R.id.equipment_abnorma_tv:
                    SIGN_TYPE = "设备异常";
                    break;
                case R.id.leave_tv:
                    APPLICATION_TYPE = "请假";
                    break;
                case R.id.over_time_tv:
                    APPLICATION_TYPE = "加班";
                    break;
                case R.id.business_travel_tv:
                    APPLICATION_TYPE = "出差";
                    break;
            }
            if (!TextUtils.isEmpty(SIGN_TYPE)) {
                intent = new Intent(AttendanceStatisticsActivity.this, AttendanceLateListActivity.class);
                intent.putExtra("SIGN_TYPE", SIGN_TYPE);
                startActivity(intent);
                return;
            }
            if (!TextUtils.isEmpty(APPLICATION_TYPE)) {
                intent = new Intent(AttendanceStatisticsActivity.this, AttendanceLeaveListActivity.class);
                intent.putExtra("APPLICATION_TYPE", APPLICATION_TYPE);
                startActivity(intent);
                return;
            }
        }
    }
}
