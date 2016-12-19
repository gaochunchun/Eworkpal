package com.mainiway.eworkpal.activity.attendance;

import android.os.Bundle;

import com.mainiway.eworkpal.R;
import com.mainiway.eworkpal.base.BaseTitleActivity;

/**
 * ===========================================
 * 作    者：zhsh
 * 版    本：1.0
 * 创建日期：2016/12/19.
 * 描    述：申诉界面
 * ===========================================
 */

public class AttendanceAppealActivity extends BaseTitleActivity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attendance_appeal);
        setTitle("申诉");
        showBackwardView(true);

    }
}
