package com.mainiway.eworkpal.activity.attendance;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.mainiway.eworkpal.R;
import com.mainiway.eworkpal.base.BaseTitleActivity;
import com.mainiway.eworkpal.utils.DateUtils;
import com.mainiway.eworkpal.widgets.TimeSelector;

/**
 * ===========================================
 * 作    者：zhsh
 * 版    本：1.0
 * 创建日期：2016/12/19.
 * 描    述：申诉界面
 * ===========================================
 */

public class AttendanceAppealActivity extends BaseTitleActivity implements View.OnClickListener {
    private TextView tv_time;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attendance_appeal);
        setTitle("申诉");
        showBackwardView(true);
        initView();
        initData();
    }

    private void initView() {
        findView(R.id.iv_time_select_button).setOnClickListener(this);

        tv_time = findView(R.id.tv_time);

    }

    private void initData() {
        //显示当前时间
        String[] time = DateUtils.getTime().split(":");//HH:mm:ss
        tv_time.setText(time[0] + ":" + time[1]);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_time_select_button://选择时间按钮
                TimeSelector timeSelector = new TimeSelector(AttendanceAppealActivity.this, new TimeSelector.ResultHandler() {
                    @Override
                    public void handle(String time) {
                        tv_time.setText(time);
                    }
                }, "2000-01-01 00:00", "2050-12-31 00:00");

                //timeSelector.setIsLoop(false);
                //timeSelector.disScrollUnit(TimeSelector.SCROLLTYPE.HOUR, TimeSelector.SCROLLTYPE.MINUTE);
                //timeSelector.setMode(TimeSelector.MODE.YMD);
                //timeSelector.setMode(TimeSelector.MODE.YMDHM);
                timeSelector.setMode(TimeSelector.MODE.HM);
                //timeSelector.setMode(TimeSelector.MODE.YM);
                timeSelector.show();
                break;
        }
    }


}
