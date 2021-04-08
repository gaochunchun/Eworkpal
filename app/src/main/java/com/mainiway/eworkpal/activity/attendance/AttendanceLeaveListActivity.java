package com.mainiway.eworkpal.activity.attendance;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.mainiway.eworkpal.R;
import com.mainiway.eworkpal.adapter.AttendanceLeaveAdapter;
import com.mainiway.eworkpal.base.BaseTitleActivity;
import com.mainiway.eworkpal.model.AttendanceLeaveModle;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhangbiao on 2016/12/15 00015.
 * 考勤-统计-请假列表
 */

public class AttendanceLeaveListActivity extends BaseTitleActivity {
    private RecyclerView mRecycleView;
    private AttendanceLeaveAdapter mAdapter;
    private List<AttendanceLeaveModle> data;
    private String APPLICATION_TYPE;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attendance_leave_list);
        APPLICATION_TYPE = getIntent().getStringExtra("APPLICATION_TYPE");
        setTitle(APPLICATION_TYPE);
        showBackwardView(true);
        init();
    }

    private void init() {
        data = new ArrayList<>();
        data.add(new AttendanceLeaveModle("事假", 8, "2016-12-15 09:30", "2017-01-01 17:30"));
        data.add(new AttendanceLeaveModle("事假", 8, "2016-12-15 09:30", "2017-01-01 17:30"));
        data.add(new AttendanceLeaveModle("事假", 8, "2016-12-15 09:30", "2017-01-01 17:30"));
        mRecycleView = findView(R.id.mRecycleView);
        mRecycleView.setLayoutManager(new LinearLayoutManager(this));
        mAdapter = new AttendanceLeaveAdapter(R.layout.item_statistics_leave, data);
        mRecycleView.setAdapter(mAdapter);
    }
}
