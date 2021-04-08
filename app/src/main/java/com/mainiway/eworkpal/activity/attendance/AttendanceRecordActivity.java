package com.mainiway.eworkpal.activity.attendance;

import android.os.Bundle;

import com.mainiway.eworkpal.R;
import com.mainiway.eworkpal.base.BaseTitleActivity;

/**
 * ===========================================
 * 作    者：zhsh
 * 版    本：1.0
 * 创建日期：2016/12/15.
 * 描    述：考勤记录
 * ===========================================
 */

public class AttendanceRecordActivity extends BaseTitleActivity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attendance_record);
        setTitle("记录");
        showBackwardView(true);
    }
}
