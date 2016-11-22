package com.mainiway.eworkpal.activity.user;

import android.annotation.TargetApi;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.mainiway.eworkpal.R;
import com.mainiway.eworkpal.base.BaseActivity;
import com.mainiway.eworkpal.constant.Constants;
import com.mainiway.eworkpal.listener.OnClickFastListener;
import com.mainiway.eworkpal.widgets.SystemBarTintManager;
import com.mainiway.eworkpal.widgets.ImageCodeView;
import com.mainiway.okhttp.utils.OkLogger;

/**
 * ===========================================
 * 作    者：zhsh
 * 版    本：1.0
 * 创建日期：2016/11/18.
 * 描    述：登录界面
 * ===========================================
 */

public class LoginActivity extends BaseActivity {

    private SystemBarTintManager tintManager;   //单独设置登录界面bar的颜色

    private ImageView iv_picture_code;
    private TextView tv_login;
    private EditText et_phone_number,et_password,et_picture_code;
    private RelativeLayout rl_picture_code_layout;
    private int count=0;//测试用的计量点击登录按钮次数的变量，以后删掉
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initWindow();

        setContentView(R.layout.activity_user_login);
        initView();
    }



    @TargetApi(19)
    private void initWindow(){

        //Build.VERSION.SDK_INT  判断当前版本号是否大于19
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT){
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            //getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);

            tintManager = new SystemBarTintManager(this);
            //tintManager.setTintColor(Color.parseColor("#444764"));
            tintManager.setStatusBarTintColor(Color.parseColor("#354361"));
            tintManager.setStatusBarTintEnabled(true);
            //tintManager.setNavigationBarTintEnabled(true);
        }
    }



    private void initView() {

        tv_login = findView(R.id.tv_login);
        tv_login.setOnClickListener(new FastClickListener());
        tv_login.setClickable(false);

        et_phone_number = findView(R.id.et_phone_number);
        et_phone_number.addTextChangedListener(textWatcher);

        et_password = findView(R.id.et_password);
        et_password.addTextChangedListener(textWatcher);

        et_picture_code = findView(R.id.et_picture_code);
        et_picture_code.addTextChangedListener(textWatcher);

        findView(R.id.tv_register_enterprises).setOnClickListener(new FastClickListener());
        findView(R.id.tv_join_enterprise).setOnClickListener(new FastClickListener());
        findView(R.id.tv_forgetpwd).setOnClickListener(new FastClickListener());

        iv_picture_code = findView(R.id.iv_picture_code);
        iv_picture_code.setImageBitmap(ImageCodeView.getInstance().createBitmap());
        iv_picture_code.setOnClickListener(new FastClickListener());

        rl_picture_code_layout = findView(R.id.rl_picture_code_layout);

    }

    /**
     * Edittext监听事件
     */
    private TextWatcher textWatcher=new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {
            if(!TextUtils.isEmpty(et_phone_number.getText()) && !TextUtils.isEmpty(et_password.getText())){
                tv_login.setBackgroundResource(R.drawable.rectangle_27dp_blue_selected);
                tv_login.setClickable(true);
            }else{
                tv_login.setBackgroundResource(R.drawable.rectangle_27dp_blue);
                tv_login.setClickable(false);
            }

            if(et_picture_code.getText().toString().equalsIgnoreCase(ImageCodeView.getInstance().getCode())){
                rl_picture_code_layout.setVisibility(View.GONE);
                tv_login.setBackgroundResource(R.drawable.rectangle_27dp_blue_selected);
                tv_login.setClickable(true);
                count=0;
            }
        }
    };

    private class FastClickListener extends OnClickFastListener {

        @Override
        public void onFastClick(View v) {

            switch (v.getId()) {

                case R.id.tv_register_enterprises://注册企业
                    Intent mIntentRegisterEnterprise = new Intent(LoginActivity.this,CommonPhoneCodeActivity.class);
                    mIntentRegisterEnterprise.putExtra(Constants.LABEL_PHONE_CODE,Constants.PHONE_CODE_ENTERPRISE);
                    startActivity(mIntentRegisterEnterprise);
                    overridePendingTransition(R.anim.push_right_in,R.anim.push_right_out);
                    break;

                case R.id.iv_picture_code://动态验证码图片
                    iv_picture_code.setImageBitmap(ImageCodeView.getInstance().createBitmap());
                    break;
                case R.id.tv_login://登录

//                    new AlertView("Eworkpal提示", "账号密码错误请重新输入", null, new String[]{"确定"}, null,LoginActivity.this,
//                            AlertView.Style.Alert,new ItemClick() {
//                        @Override
//                        public void onItemClick(Object o, int position) {
//
//                            //...点击取消按钮返回 －1，其他按钮从0开始算
//
//                        }
//                    }).show();
                    count++;
                    if(count>3){
                        rl_picture_code_layout.setVisibility(View.VISIBLE);
                        tv_login.setBackgroundResource(R.drawable.rectangle_27dp_blue);
                        tv_login.setClickable(false);
                    }
                    break;

                case R.id.tv_join_enterprise:   //申请加入企业
                    startActivity(new Intent(LoginActivity.this,JoinEnterpriseActivity.class));
                    break;

                case R.id.tv_forgetpwd: //找回密码
                    Intent mIntentFowgetPwd = new Intent(LoginActivity.this,CommonPhoneCodeActivity.class);
                    mIntentFowgetPwd.putExtra(Constants.LABEL_PHONE_CODE,Constants.PHONE_CODE_FORGET_PWD);
                    startActivity(mIntentFowgetPwd);
                    break;
            }
        }
    }
}
