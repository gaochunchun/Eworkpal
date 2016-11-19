package com.mainiway.eworkpal.activity.user;

import android.os.Bundle;

import com.mainiway.eworkpal.R;
import com.mainiway.eworkpal.base.BaseTitleActivity;

/**
 * ===========================================
 * 作    者：gao_chun
 * 版    本：1.0
 * 创建日期：2016-11-19.
 * 描    述：找回密码
 * ===========================================
 */


public class ForgetPwdActivity extends BaseTitleActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_forget_pwd);
        setTitle("找回密码");
        showBackwardView(true);
        initView();
    }

    private void initView() {
    }
}
