package com.mainiway.eworkpal.activity.test;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.mainiway.eworkpal.R;
import com.mainiway.eworkpal.activity.user.UserEnterpriseListActivity;
import com.mainiway.eworkpal.base.BaseTitleActivity;
import com.mainiway.eworkpal.utils.ToastUtils;
import com.mainiway.eworkpal.widgets.TimeSelector;

/**
 * 测试主页
 */

public class MainTestActivity extends BaseTitleActivity {

    private TimeSelector timeSelector;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test_activity_main);
        setTitle("功能测试界面");
        showBackwardView(true);
        showForwardView(R.mipmap.ic_back, R.mipmap.ic_back);
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.btn_network:
                startActivity(new Intent(this, TestActivity.class));
                break;

            case R.id.btn_rv:
                startActivity(new Intent(this, AnimationUseActivity.class));
                break;

            case R.id.btn_contact:
                startActivity(new Intent(this, CActivity.class));
                break;

            case R.id.btn_refresh:
                startActivity(new Intent(this, PullToRefreshUseActivity.class));
                break;
            case R.id.btn_timeselect:
                timeSelector = new TimeSelector(this, new TimeSelector.ResultHandler() {
                    @Override
                    public void handle(String time) {
                        Button btn = findView(R.id.btn_timeselect);
                        btn.setText(time);
                    }
                }, "2000-01-01 00:00", "2050-12-31 00:00");

                //timeSelector.setIsLoop(false);
                //timeSelector.disScrollUnit(TimeSelector.SCROLLTYPE.HOUR, TimeSelector.SCROLLTYPE.MINUTE);
                timeSelector.setMode(TimeSelector.MODE.YMD);
                //timeSelector.setMode(TimeSelector.MODE.YMDHM);
                //timeSelector.setMode(TimeSelector.MODE.HM);
                //timeSelector.setMode(TimeSelector.MODE.YM);
                timeSelector.show();
                break;

            case R.id.btn_1:
                startActivity(new Intent(this, UserEnterpriseListActivity.class));
                break;

            case R.id.btn_2:
                //startActivity(new Intent(this, EnterpriseListActivity2.class));
                break;
        }
    }

    @Override
    protected void onForward(View forwardView) {
        super.onForward(forwardView);
        ToastUtils.showToastCenter("第一个图标");
    }

    @Override
    protected void onForwardRight(View forwardView) {
        super.onForwardRight(forwardView);
        ToastUtils.showToastCenter("第二个图标");
    }
}
