package com.mainiway.eworkpal.activity.user;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.mainiway.eworkpal.R;
import com.mainiway.eworkpal.activity.attendance.AttendanceSignActivity;
import com.mainiway.eworkpal.activity.attendance.StatisticsActivity;
import com.mainiway.eworkpal.base.BaseActivity;
import com.mainiway.eworkpal.listener.OnClickFastListener;

/**
 * ===========================================
 * 作    者：gao_chun
 * 版    本：1.0
 * 创建日期：2016-11-29.
 * 描    述：主页
 * ===========================================
 */


public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_layout);
        initView();
    }

    private void initView() {
        findView(R.id.tv_application_center).setOnClickListener(new FastClickListener());
        findView(R.id.ll_my_attendance).setOnClickListener(new FastClickListener());
        findView(R.id.tv_sign).setOnClickListener(new FastClickListener());
    }


    private class FastClickListener extends OnClickFastListener {

        @Override
        public void onFastClick(View v) {
            switch (v.getId()) {
                case R.id.tv_sign://签到按钮
                    startActivity(new Intent(MainActivity.this, AttendanceSignActivity.class));
                    break;
                case R.id.ll_my_attendance://我的考勤
                    startActivity(new Intent(MainActivity.this, StatisticsActivity.class));
                    break;
                case R.id.tv_application_center://应用中心
                    startActivity(new Intent(MainActivity.this, ApplicationCenterActivity.class));
                    break;
            }
        }
    }


}
