package com.mainiway.eworkpal.activity.test;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.mainiway.eworkpal.R;
import com.mainiway.eworkpal.activity.attendance.RightPositionActivity;
import com.mainiway.eworkpal.base.BaseActivity;
import com.mainiway.eworkpal.base.BaseTitleActivity;
import com.mainiway.eworkpal.utils.ToastUtils;

/**
 * 测试主页
 */

public class MainTestActivity extends BaseTitleActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test_activity_main);
        setTitle("功能测试界面");
        showBackwardView(true);
        showForwardView(R.mipmap.ic_back,R.mipmap.ic_back);
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()){
            case R.id.btn_network:
                startActivity(new Intent(this,TestActivity.class));
                break;

            case R.id.btn_rv:
                startActivity(new Intent(this,AnimationUseActivity.class));
                break;

            case R.id.btn_contact:
                startActivity(new Intent(this,RightPositionActivity.class));
                break;

            case R.id.btn_refresh:
                startActivity(new Intent(this,PullToRefreshUseActivity.class));
                break;
        }
    }

    @Override
    protected void onForward(View forwardView) {
        super.onForward(forwardView);
        ToastUtils.showToastCenter("第一个图标");
    }

    @Override
    protected void onForward2(View forwardView2) {
        super.onForward2(forwardView2);
        ToastUtils.showToastCenter("第二个图标");
    }
}
