package com.mainiway.eworkpal.activity.user;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.mainiway.eworkpal.R;
import com.mainiway.eworkpal.activity.attendance.AttendanceSignActivity;
import com.mainiway.eworkpal.base.BaseTitleActivity;
import com.mainiway.eworkpal.listener.OnClickFastListener;

/**
 * ===========================================
 * 作    者：zhsh
 * 版    本：1.0
 * 创建日期：2016/12/9.
 * 描    述：应用中心
 * ===========================================
 */

public class ApplicationCenterActivity extends BaseTitleActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_application_center);
        setTitle(R.string.application_center);
        showBackwardView(true);
        initView();
    }

    private void initView() {
        findView(R.id.ll_attendance).setOnClickListener(new FastClickListener());
    }


    private class FastClickListener extends OnClickFastListener {

        @Override
        public void onFastClick(View v) {
            switch (v.getId()) {
                case R.id.ll_attendance://考勤
                    startActivity(new Intent(ApplicationCenterActivity.this, AttendanceSignActivity.class));
                    break;
            }
        }
    }


}
