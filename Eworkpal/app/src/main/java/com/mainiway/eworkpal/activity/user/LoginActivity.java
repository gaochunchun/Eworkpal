package com.mainiway.eworkpal.activity.user;


import android.annotation.TargetApi;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.mainiway.eworkpal.R;
import com.mainiway.eworkpal.base.BaseActivity;
import com.mainiway.eworkpal.base.BaseResponse;
import com.mainiway.eworkpal.callback.DialogCallback;
import com.mainiway.eworkpal.constant.Constants;
import com.mainiway.eworkpal.listener.OnClickFastListener;
import com.mainiway.eworkpal.model.UserLoginModle;
import com.mainiway.eworkpal.request.UserRequestManager;
import com.mainiway.eworkpal.utils.DealViewUtils;
import com.mainiway.eworkpal.utils.GsonConvertUtil;
import com.mainiway.eworkpal.utils.KeyboardUtils;
import com.mainiway.eworkpal.utils.ToastUtils;
import com.mainiway.eworkpal.widgets.ImageCodeView;
import com.mainiway.eworkpal.widgets.SecretTextView;
import com.mainiway.imagepicker.view.SystemBarTintManager;
import com.mainiway.okhttp.model.HttpParams;
import com.mainiway.okhttp.utils.OkLogger;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

import okhttp3.Call;
import okhttp3.Response;

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

    SecretTextView secretTextView;

    private ImageView iv_picture_code;
    private TextView tv_login;
    private EditText et_phone_number, et_password, et_picture_code;
    private RelativeLayout rl_picture_code_layout;
    private View ll_login_layout;
    private int count = 0;//测试用的计量点击登录按钮次数的变量，以后删掉

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initWindow();

        setContentView(R.layout.activity_user_login);
        initView();
    }

    @TargetApi(19)
    private void initWindow() {

        //Build.VERSION.SDK_INT  判断当前版本号是否大于19
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
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

        //********************* 界面字体LOGO动画-暂定 *************************
        secretTextView = findView(R.id.secret_tv);
        secretTextView.setDuration(2000);
        //secretTextView.setIsVisible(true);
        secretTextView.show();
        //secretTextView.setOnClickListener(new FastClickListener());


        tv_login = findView(R.id.tv_login);
        tv_login.setOnClickListener(new FastClickListener());
        tv_login.setClickable(false);

        et_phone_number = findView(R.id.et_phone_number);
        et_phone_number.addTextChangedListener(textWatcher);
        et_phone_number.setFocusable(false);

        et_password = findView(R.id.et_password);
        et_password.addTextChangedListener(textWatcher);
        et_password.setFocusable(false);

        et_picture_code = findView(R.id.et_picture_code);
        et_picture_code.addTextChangedListener(textWatcher);

        findView(R.id.tv_register_enterprises).setOnClickListener(new FastClickListener());
        findView(R.id.tv_join_enterprise).setOnClickListener(new FastClickListener());
        findView(R.id.tv_forgetpwd).setOnClickListener(new FastClickListener());

        iv_picture_code = findView(R.id.iv_picture_code);
        iv_picture_code.setImageBitmap(ImageCodeView.getInstance().createBitmap());
        iv_picture_code.setOnClickListener(new FastClickListener());

        rl_picture_code_layout = findView(R.id.rl_picture_code_layout);

        ll_login_layout = findView(R.id.ll_login_layout);

        //设置一个定时器，处理软键盘上弹和TextView动画冲突的问题
        if (secretTextView.getIsVisible()) {
            Timer timer = new Timer();
            timer.schedule(task, 2000);
        }

    }

    //TextView动画显示完，设置输入框的状态和软键盘弹出动态计算高度
    TimerTask task = new TimerTask() {

        @Override
        public void run() {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    et_phone_number.setFocusable(true);
                    et_phone_number.setFocusableInTouchMode(true);
                    et_phone_number.requestFocus();
                    et_password.setFocusable(true);
                    et_password.setFocusableInTouchMode(true);
                    //处理弹出软键盘遮挡输入框的问题
                    KeyboardUtils.controlKeyboardLayout(ll_login_layout, et_password);
                }
            });

        }
    };
    /**
     * Edittext监听事件
     */
    private TextWatcher textWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {

            if (rl_picture_code_layout.getVisibility() == View.VISIBLE) {//若布局可见,弹出图片验证码布局
                //如果弹出图片验证码，则判断手机号、密码、图片验证码不为空，登录按钮可点击
                if (!TextUtils.isEmpty(et_phone_number.getText()) && !TextUtils.isEmpty(et_password.getText()) && !TextUtils.isEmpty(et_picture_code.getText())) {
                    DealViewUtils.buttonState(tv_login, R.drawable.rectangle_27dp_blue_selected, true);
                } else {
                    DealViewUtils.buttonState(tv_login, R.drawable.rectangle_27dp_blue, false);
                }
            } else {//只有用户名，密码,没有图片验证码布局
                if (!TextUtils.isEmpty(et_phone_number.getText()) && !TextUtils.isEmpty(et_password.getText())) {
                    DealViewUtils.buttonState(tv_login, R.drawable.rectangle_27dp_blue_selected, true);
                } else {
                    DealViewUtils.buttonState(tv_login, R.drawable.rectangle_27dp_blue, false);
                }
            }

        }
    };

    private class FastClickListener extends OnClickFastListener {

        @Override
        public void onFastClick(View v) {

            switch (v.getId()) {

                case R.id.tv_register_enterprises://注册企业
                    Intent mIntentRegisterEnterprise = new Intent(LoginActivity.this, CommonPhoneCodeActivity.class);
                    mIntentRegisterEnterprise.putExtra(Constants.LABEL_PHONE_CODE, Constants.PHONE_CODE_ENTERPRISE);
                    startActivity(mIntentRegisterEnterprise);
                    overridePendingTransition(R.anim.push_right_in, R.anim.push_right_out);
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


//                    int visibility = rl_picture_code_layout.getVisibility();
//                    if(visibility==0){//返回值为0，visible,弹出图片验证码布局
//                        if(et_picture_code.getText().toString().equalsIgnoreCase(ImageCodeView.getInstance().getCode())){
//                            rl_picture_code_layout.setVisibility(View.GONE);
//                            tv_login.setBackgroundResource(R.drawable.rectangle_27dp_blue_selected);
//                            tv_login.setClickable(true);
//                            count=0;
//                        }else{
//                            ToastUtils.showToastShort("图片验证码错误");
//                        }
//                    }else{
//                        count++;
//                        if(count>1){
//                            rl_picture_code_layout.setVisibility(View.VISIBLE);
//                            tv_login.setBackgroundResource(R.drawable.rectangle_27dp_blue);
//                            tv_login.setClickable(false);
//                        }
//                    }
                    //startActivity(new Intent(LoginActivity.this, NoJoinEnterpriseActivity.class));
                   startActivity(new Intent(LoginActivity.this, MainActivity.class));
                    //login();

                    break;

                case R.id.tv_join_enterprise:   //申请加入企业
                    startActivity(new Intent(LoginActivity.this, JoinEnterpriseActivity.class));
                    break;

                case R.id.tv_forgetpwd: //找回密码
                    Intent mIntentFowgetPwd = new Intent(LoginActivity.this, CommonPhoneCodeActivity.class);
                    mIntentFowgetPwd.putExtra(Constants.LABEL_PHONE_CODE, Constants.PHONE_CODE_FORGET_PWD);
                    startActivity(mIntentFowgetPwd);
                    break;

                //case R.id.secret_tv:    //Logo显示
                //secretTextView.toggle();
                //   secretTextView.show();
                //secretTextView.hide();
                //   break;
            }
        }
    }

    /**
     * 登录请求
     */
    private void login() {

        Map<String, Object> mapList = new HashMap<String, Object>();
        mapList.put("phone", "string");
        mapList.put("password", "123456");
        mapList.put("type", "1");
        mapList.put("ispass", "1");
        String str = GsonConvertUtil.toJson(mapList);

        UserRequestManager.getInstance().login(this, str, new DialogCallback<BaseResponse>(this) {
            @Override
            public void onSuccess(BaseResponse baseResponse, Call call, Response response) {
                OkLogger.e(baseResponse.status+" ---" + baseResponse.message);
            }

            @Override
            public void onError(Call call, Response response, Exception e) {
                super.onError(call, response, e);
                ToastUtils.showToastShort(e.toString());
            }
        });

    }

}
