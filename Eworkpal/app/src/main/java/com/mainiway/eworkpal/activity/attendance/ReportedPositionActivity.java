package com.mainiway.eworkpal.activity.attendance;

import android.os.Bundle;

import com.mainiway.eworkpal.R;
import com.mainiway.eworkpal.base.BaseTitleActivity;

/**
 * ===========================================
 * 作    者：zhsh
 * 版    本：1.0
 * 创建日期：2016/12/13.
 * 描    述：上报位置
 * ===========================================
 */

public class ReportedPositionActivity extends BaseTitleActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attendance_reported_position);
        setTitle("上报位置");
        showBackwardView(true);
    }
}
