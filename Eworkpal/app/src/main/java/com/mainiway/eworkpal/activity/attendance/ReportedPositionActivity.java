package com.mainiway.eworkpal.activity.attendance;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.mainiway.eworkpal.R;
import com.mainiway.eworkpal.base.BaseTitleActivity;
import com.mainiway.eworkpal.listener.OnClickFastListener;
import com.mainiway.eworkpal.utils.DateUtil;

import java.util.Date;

/**
 * ===========================================
 * 作    者：zhsh
 * 版    本：1.0
 * 创建日期：2016/12/13.
 * 描    述：上报位置
 * ===========================================
 */

public class ReportedPositionActivity extends BaseTitleActivity {

    private TextView tv_position, tv_date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attendance_reported_position);
        setTitle("上报位置");
        showBackwardView(true);
        initView();
        initData();
    }

    private void initView() {
        tv_position = findView(R.id.tv_position);
        tv_date = findView(R.id.tv_date);

        findView(R.id.tv_submit).setOnClickListener(new FastClickListener());
    }

    private void initData() {
        //显示当前位置信息
        if (getIntent() != null) {
            Bundle bundle = getIntent().getExtras();
            tv_position.setText(bundle.getString("city") + bundle.getString("district") + bundle.getString("street") + bundle.getString("streetNum"));
        }
        //显示当前时间
        String[] date = DateUtil.getToday().split("-");//yyyy-MM-dd
        String[] time = DateUtil.getTime().split(":");//HH:mm:ss
        String week = DateUtil.getWeek(new Date());
        tv_date.setText(date[0] + "年" + date[1] + "月" + date[2] + "日" + "　　" + week + "　　" + time[0] + ":" + time[1]);
    }

    /**
     * 防止快速点击事件
     */
    private class FastClickListener extends OnClickFastListener {

        @Override
        public void onFastClick(View v) {
            switch (v.getId()) {
                case R.id.tv_submit://提交
                    startActivity(new Intent(ReportedPositionActivity.this, AttendanceRecordActivity.class));
                    break;

            }
        }
    }
}
