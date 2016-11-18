package com.mainiway.eworkpal.activity.user;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.mainiway.eworkpal.R;
import com.mainiway.eworkpal.base.BaseTitleActivity;
import com.mainiway.eworkpal.listener.OnClickFastListener;
import com.mainiway.eworkpal.widgets.TimeCount;

/**
 * ===========================================
 * 作    者：zhsh
 * 版    本：1.0
 * 创建日期：2016/11/18.
 * 描    述：注册企业
 * ===========================================
 */

public class RegisterActivity extends BaseTitleActivity{

    private TextView tv_register_get_code;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_register);
        setTitle("注册");
        showBackwardView(true);
        initView();
    }

    private void initView() {
        findView(R.id.tv_register_next).setOnClickListener(new FastClickListener());
        tv_register_get_code = findView(R.id.tv_register_get_code);
        tv_register_get_code.setOnClickListener(new FastClickListener());

    }
    private class FastClickListener extends OnClickFastListener {

        @Override
        public void onFastClick(View v) {

            switch (v.getId()) {

                case R.id.tv_register_next://下一步
                    startActivity(new Intent(RegisterActivity.this,CreateEnterpriseActivity.class));
                    break;

                case R.id.tv_register_get_code://获取验证码
                    TimeCount timeCount = new TimeCount(60000, 1000);//60000, 1000
                    timeCount.setBtn(tv_register_get_code, "重新获取");
                    timeCount.start();
                    break;

            }
        }
    }
}
