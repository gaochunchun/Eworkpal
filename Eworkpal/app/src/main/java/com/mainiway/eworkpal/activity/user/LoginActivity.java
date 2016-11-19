package com.mainiway.eworkpal.activity.user;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.mainiway.alertview.AlertView;
import com.mainiway.alertview.ItemClick;
import com.mainiway.eworkpal.AppAplication;
import com.mainiway.eworkpal.R;
import com.mainiway.eworkpal.activity.MainActivity;
import com.mainiway.eworkpal.base.BaseActivity;
import com.mainiway.eworkpal.listener.OnClickFastListener;

/**
 * ===========================================
 * 作    者：zhsh
 * 版    本：1.0
 * 创建日期：2016/11/18.
 * 描    述：登录界面
 * ===========================================
 */

public class LoginActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_login);
        initView();
    }


    private void initView() {
        findView(R.id.tv_register_enterprises).setOnClickListener(new FastClickListener());
        findView(R.id.tv_login).setOnClickListener(new FastClickListener());
        findView(R.id.tv_join_enterprise).setOnClickListener(new FastClickListener());
        findView(R.id.tv_forgetpwd).setOnClickListener(new FastClickListener());
    }


    private class FastClickListener extends OnClickFastListener {

        @Override
        public void onFastClick(View v) {

            switch (v.getId()) {

                case R.id.tv_register_enterprises://注册
                     startActivity(new Intent(LoginActivity.this,RegisterActivity.class));
                    break;

                case R.id.tv_login:

                    new AlertView("Eworkpal提示", "账号密码错误请重新输入", null, new String[]{"确定"}, null,LoginActivity.this,
                            AlertView.Style.Alert,new ItemClick() {
                        @Override
                        public void onItemClick(Object o, int position) {

                            //...点击取消按钮返回 －1，其他按钮从0开始算

                        }
                    }).show();
                    break;

                case R.id.tv_join_enterprise:   //申请加入企业
                    startActivity(new Intent(LoginActivity.this,JoinEnterpriseActivity.class));
                    break;

                case R.id.tv_forgetpwd:
                    //startActivity(new Intent(LoginActivity.this,JoinEnterpriseActivity.class));
                    break;
            }
        }
    }
}
