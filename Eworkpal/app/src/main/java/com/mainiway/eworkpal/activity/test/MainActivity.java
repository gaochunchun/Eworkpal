package com.mainiway.eworkpal.activity.test;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.mainiway.eworkpal.R;
import com.mainiway.eworkpal.activity.attendance.RightPositionActivity;
import com.mainiway.eworkpal.base.BaseActivity;

/**
 * 测试主页
 */

public class MainActivity extends BaseActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test_activity_main);

    }

    @Override
    public void onClick(View v) {

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
}
