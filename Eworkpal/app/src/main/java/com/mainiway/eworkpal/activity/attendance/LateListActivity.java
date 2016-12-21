package com.mainiway.eworkpal.activity.attendance;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.mainiway.library.adapter.base.BaseQuickAdapter;
import com.mainiway.library.adapter.base.listener.OnItemChildClickListener;
import com.mainiway.eworkpal.R;
import com.mainiway.eworkpal.adapter.AttendanceLateAdapter;
import com.mainiway.eworkpal.base.BaseTitleActivity;
import com.mainiway.eworkpal.model.AttendanceLateModle;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhangbiao on 2016/12/16 00016.
 * 统计-迟到列表
 * <p>
 * 估计用于 迟到 早退 未签到 未签退 设备异常 外勤(待定)
 * 名字暂时不改了
 */

public class LateListActivity extends BaseTitleActivity {
    private RecyclerView mRecycleView;
    private AttendanceLateAdapter mAdapter;
    private List<AttendanceLateModle> data;
    private String SIGN_TYPE;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attendance_late_list);
        SIGN_TYPE=getIntent().getStringExtra("SIGN_TYPE");
        setTitle(SIGN_TYPE);
        showBackwardView(true);
        init();
    }

    private void init() {
        data = new ArrayList<>();
        data.add(new AttendanceLateModle("", "", "", "", 5, "签到", "未申诉"));
        data.add(new AttendanceLateModle("", "", "", "", 5, "签到", "处理中"));
        data.add(new AttendanceLateModle("", "", "", "", 5, "签到", "驳回"));

        mRecycleView = findView(R.id.mRecycleView);
        mRecycleView.setLayoutManager(new LinearLayoutManager(this));
        mAdapter = new AttendanceLateAdapter(data);
        mRecycleView.setAdapter(mAdapter);

        mRecycleView.addOnItemTouchListener(new OnItemChildClickListener() {
            @Override
            public void SimpleOnItemChildClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
                if (view.getId() == R.id.appeal_tv) {
                    Toast.makeText(LateListActivity.this, "点我干嘛", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}
