package com.mainiway.eworkpal.activity.user;


import android.annotation.TargetApi;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.TextView;

import com.blankj.utilcode.util.NetworkUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.ccn.SmartPDA.OkHttpUtil;
import com.ccn.SmartPDA.callback.StringCallback;
import com.ccn.SmartPDA.model.Response;
import com.mainiway.eworkpal.R;
import com.mainiway.eworkpal.activity.attendance.AttendanceSignActivity;
import com.mainiway.eworkpal.activity.sign.GetSMCodeRequest;
import com.mainiway.eworkpal.base.BaseActivity;
import com.mainiway.eworkpal.base.BaseResponse;
import com.mainiway.eworkpal.callback.DialogCallback;
import com.mainiway.eworkpal.constant.AppConstant;
import com.mainiway.eworkpal.constant.ResultErrorCode;
import com.mainiway.eworkpal.listener.OnClickFastListener;
import com.mainiway.eworkpal.model.UserLoginModle;
import com.mainiway.eworkpal.model.UserLoginToModle;
import com.mainiway.eworkpal.request.UserRequestManager;
import com.mainiway.eworkpal.utils.DealViewUtils;
import com.mainiway.eworkpal.utils.GsonConvertUtil;
import com.mainiway.eworkpal.utils.ValidateUtils;
import com.mainiway.eworkpal.utils.keyboard.PreventKeyboardBlockUtil;
import com.mainiway.imagepicker.view.SystemBarTintManager;
import com.mainiway.okhttp.OkHttpUtils;
import com.mainiway.okhttp.utils.OkLogger;
import com.mainiway.svprogresshud.SVProgressHUD;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.Call;

/**
 * ===========================================
 * 版    本：1.0
 * 描    述：登录界面
 * ===========================================
 */

public class UserLoginActivity extends BaseActivity {

    private SVProgressHUD mSVProgressHUD;

    private SystemBarTintManager tintManager;   //单独设置登录界面bar的颜色
    //private ImageView iv_picture_code;
    private TextView tv_login, tv_get_code, tv_get_code_login;
    private EditText et_phone_number, et_password, et_phone, et_phone_code;
    private View view_phoneCode;
    private View ll_login_layout;
    private String companyID;//往选择企业接口传递的公司Id

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initWindow();
        mSVProgressHUD = new SVProgressHUD(this);

        setContentView(R.layout.activity_user_login);
        initView();

        //提示网络连接不可用
        //if (!ValidateUtils.isNetworkConnected(AppAplication.getContext())) {
        if (!NetworkUtils.isConnected()) {
            mSVProgressHUD.showInfoWithStatus("当前网络不可用！", SVProgressHUD.SVProgressHUDMaskType.None);
            //mSVProgressHUD.showErrorWithStatus("网络未连接",SVProgressHUD.SVProgressHUDMaskType.None);
        }
    }

    private void initView() {

        tv_login = findView(R.id.tv_login);
        tv_login.setOnClickListener(new FastClickListener());
        tv_login.setClickable(false);

        et_phone_number = findView(R.id.et_phone_number);
        et_phone_number.addTextChangedListener(textWatcher);
        et_phone_number.setSelection(et_phone_number.getText().length());
        //et_phone_number.setFocusable(false);

        et_password = findView(R.id.et_password);
        et_password.addTextChangedListener(textWatcher);
        //et_password.setFocusable(false);

        //--------------验证码登录
        et_phone = findView(R.id.et_phone);
        //et_phone.setFocusable(false);

        et_phone_code = findView(R.id.et_phone_code);
        et_phone_code.addTextChangedListener(textWatcher);
        //et_phone_code.setFocusable(false);

        tv_get_code = findView(R.id.tv_get_code);
        tv_get_code.setOnClickListener(new FastClickListener());

        tv_get_code_login = findView(R.id.tv_get_code_login);
        tv_get_code_login.setOnClickListener(new FastClickListener());

        findView(R.id.tv_accont_login).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ll_login_layout.setVisibility(View.VISIBLE);
                findView(R.id.iv1).setVisibility(View.VISIBLE);
                view_phoneCode.setVisibility(View.GONE);
                findView(R.id.iv2).setVisibility(View.INVISIBLE);
                if (ll_login_layout.getVisibility() == View.VISIBLE) {
                    et_phone_number.setFocusable(true);
                    et_phone_number.requestFocus();
                    et_phone_number.setSelection(et_phone_number.getText().length());
                }
            }
        });

        findView(R.id.tv_smscode_login).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ll_login_layout.setVisibility(View.GONE);
                findView(R.id.iv1).setVisibility(View.INVISIBLE);
                view_phoneCode.setVisibility(View.VISIBLE);
                findView(R.id.iv2).setVisibility(View.VISIBLE);
                if (view_phoneCode.getVisibility() == View.VISIBLE) {
                    et_phone.setFocusable(true);
                    et_phone.requestFocus();
                    et_phone.setSelection(et_phone.getText().length());
                }
            }
        });

        findView(R.id.tv_join_enterprise).setOnClickListener(new FastClickListener());
        findView(R.id.tv_forgetpwd).setOnClickListener(new FastClickListener());

        view_phoneCode = findView(R.id.view_phoneCode);
        ll_login_layout = findView(R.id.ll_login_layout);
    }


    private class FastClickListener extends OnClickFastListener {
        @Override
        public void onFastClick(View v) {

            switch (v.getId()) {

                case R.id.tv_get_code://获取验证码
                    if (ValidateUtils.isMobile(et_phone.getText().toString())) {
                        setPhoneCode();
                    } else {
                        ToastUtils.make().setGravity(Gravity.CENTER, 0, 0).setTextColor(Color.RED).setDurationIsLong(true).show(R.string.please_enter_the_correct_phone_number);
                        break;
                    }

                    break;
                case R.id.tv_get_code_login:
                    verifyPhoneNumber();
                    break;

               /*  case R.id.tv_smscode_login://短信验证码登录
                   Intent mIntentRegisterEnterprise = new Intent(UserLoginActivity.this, UserCommonPhoneCodeActivity.class);
                    mIntentRegisterEnterprise.putExtra(AppConstant.LABEL_PHONE_CODE, AppConstant.PHONE_CODE_ENTERPRISE);
                    startActivityForResult(mIntentRegisterEnterprise, AppConstant.VALUE_COMMON_PHONE_CODE_ACTIVITY);
                    overridePendingTransition(R.anim.push_right_in, R.anim.push_right_out);
                    break;
                case R.id.tv_accont_login://账号密码登录

                    break;*/

                case R.id.iv_picture_code://动态验证码图片
                    //.setImageBitmap(ImageCodeView.getInstance().createBitmap());
                    break;
                case R.id.tv_login://登录

//                    new AlertView("Eworkpal提示", "账号密码错误请重新输入", null, new String[]{"确定"}, null,UserLoginActivity.this,
//                            AlertView.Style.Alert,new ItemClick() {
//                        @Override
//                        public void onItemClick(Object o, int position) {
//
//                            //...点击取消按钮返回 －1，其他按钮从0开始算
//
//                        }
//                    }).show();


                    /*if (rl_picture_code_layout.getVisibility() == View.VISIBLE) {//弹出图片验证码时
                        if (et_picture_code.getText().toString().equalsIgnoreCase(ImageCodeView.getInstance().getCode()) && et_picture_code.getText().length() == 4) {

                        } else {
                            ToastUtils.showToastShort(getString(R.string.image_verification_code_error));
                        }
                    } else {//未弹出图片验证码时
                        if (ValidateUtils.isMobile(et_phone_number.getText().toString())) {

                           String mPhone = et_phone_number.getText().toString().trim();
                           String mPwd = et_password.getText().toString().trim();

                            loginGo(mPhone,mPwd);
                        } else {
                            ToastUtils.showToastShort(getString(R.string.please_enter_the_correct_phone_number));
                        }
                    }*/
                    //startActivity(new Intent(UserLoginActivity.this, MainActivity.class));
                    startActivity(new Intent(UserLoginActivity.this, AttendanceSignActivity.class));
                    finish();
                    break;

                case R.id.tv_join_enterprise:   //申请加入企业
                    //startActivity(new Intent(UserLoginActivity.this, UserJoinEnterpriseActivity.class));
                    break;

                case R.id.tv_forgetpwd: //找回密码
                    /*Intent mIntentFowgetPwd = new Intent(UserLoginActivity.this, UserCommonPhoneCodeActivity.class);
                    mIntentFowgetPwd.putExtra(AppConstant.LABEL_PHONE_CODE, AppConstant.PHONE_CODE_FORGET_PWD);
                    startActivityForResult(mIntentFowgetPwd, AppConstant.VALUE_COMMON_PHONE_CODE_ACTIVITY);
                    overridePendingTransition(R.anim.push_right_in, R.anim.push_right_out);*/
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
            if (ll_login_layout.getVisibility() == View.VISIBLE) {
                //账号登录
                if (!TextUtils.isEmpty(et_phone_number.getText()) && !TextUtils.isEmpty(et_password.getText())) {
                    DealViewUtils.buttonState(tv_login, R.drawable.rectangle_5dp_blue_selected, true);
                } else {
                    DealViewUtils.buttonState(tv_login, R.drawable.rectangle_5dp_blue, false);
                }
            } else {
                //验证码登录
                if (!TextUtils.isEmpty(et_phone.getText()) && !TextUtils.isEmpty(et_phone_code.getText())) {
                    DealViewUtils.buttonState(tv_get_code_login, R.drawable.rectangle_5dp_blue_selected, true);
                } else {
                    DealViewUtils.buttonState(tv_get_code_login, R.drawable.rectangle_5dp_blue, false);
                }
            }
        }
    };


    /**
     * 发送手机短信码
     */
    private void setPhoneCode() {

        String param = GetSMCodeRequest.getRequestParameter(et_phone.getText().toString());

        OkHttpUtil.<String>post(GetSMCodeRequest.URL_BuildValidateCode)
                .tag(this)
                .upString(param)
                .execute(new StringCallback() {

                    @Override
                    public void onSuccess(Response<String> response) {
                        OkLogger.e("------->" + response.body().toString());
                    }

                    @Override
                    public void onError(Response<String> response) {
                        OkLogger.e("error:" + response.body());
                    }
                });


        /*UserRequestManager.getInstance().setPhoneCode(this, str, new DialogCallback<BaseResponse<String>>(this) {
            @Override
            public void onSuccess(BaseResponse<String> responseData, Call call, Response response) {
                if (responseData.successed) {
                    TimeCount timeCount = new TimeCount(60000, 1000);//60000, 1000
                    timeCount.setBtn(tv_get_code, getString(R.string.re_acquisition));
                    timeCount.start();
                    //ToastUtils.make().setGravity(Gravity.CENTER, 0, 0).setTextColor(Color.RED).setDurationIsLong(true).show();
                    //com.mainiway.eworkpal.utils.ToastUtils.showToastShort(responseData.message.get(0).msg);
                } else if (responseData.status == ResultErrorCode.CODE_SEND_CODE_THREE) {
                    //获取验证码不可点击
                    DealViewUtils.buttonState(tv_get_code, R.drawable.rectangle_5dp_blue, false);
                    //下一步不可点击
                    //DealViewUtils.buttonState(tv_get_code_next, R.drawable.rectangle_5dp_blue, false);
                } else {
                    //ToastUtils.make().setGravity(Gravity.CENTER, 0, 0).setTextColor(Color.RED).setDurationIsLong(true).show();
                }
            }

            @Override
            public void onError(Call call, Response response, Exception e) {
                super.onError(call, response, e);
                OkLogger.e(e.toString());
            }
        });*/
    }

    /**
     * 验证手机号码(下一步或确认时候调用)
     */
    private void verifyPhoneNumber() {
        Map<String, Object> mapList = new HashMap<String, Object>();
        mapList.put("phone", et_phone_number.getText().toString());
        mapList.put("code", et_phone_code.getText().toString());
        mapList.put("company_id", 0);

        String str = GsonConvertUtil.toJson(mapList);

        UserRequestManager.getInstance().verifyPhoneNumber(this, str, new DialogCallback<BaseResponse<String>>(this) {
            @Override
            public void onSuccess(BaseResponse<String> responseData, Call call, okhttp3.Response response) {
                if (responseData.successed) {
                    Intent intent = new Intent();
                    intent.putExtra("mobile", et_phone_number.getText().toString());

                } else {
                    //ToastUtils.make().setGravity(Gravity.CENTER, 0, 0).setTextColor(Color.RED).setDurationIsLong(true).show();
                }
            }

            @Override
            public void onError(Call call, okhttp3.Response response, Exception e) {
                super.onError(call, response, e);
                OkLogger.e(e.toString());
            }
        });

    }


    /**
     * 提交登录,返回企业列表(企业Id,企业Name)
     */
    private void loginGo(String phone, String password) {

        Map<String, Object> mapList = new HashMap<String, Object>();
        mapList.put("phone", phone);
        mapList.put("password", password);
        mapList.put("type", ResultErrorCode.TYPE_LOGIN_TERMINAL);
        String str = GsonConvertUtil.toJson(mapList);

        UserRequestManager.getInstance().loginGo(this, str, new DialogCallback<BaseResponse<List<UserLoginToModle>>>(this) {
            @Override
            public void onSuccess(BaseResponse<List<UserLoginToModle>> baseResponse, Call call, okhttp3.Response response) {
                if (baseResponse.successed) {
                    if (baseResponse.data.size() == 0) {
                        startActivity(new Intent(UserLoginActivity.this, UserNoJoinEnterpriseActivity.class));

                    } else if (baseResponse.data.size() == 1) {
                        companyID = baseResponse.data.get(0).companyID;
                        login();
                    } else {
                        Intent intent = new Intent();
                        intent.putExtra("infoList", (Serializable) baseResponse.data);
                        intent.setClass(UserLoginActivity.this, UserEnterpriseListActivity.class);
                        startActivity(intent);
                    }

                } else if (baseResponse.status == ResultErrorCode.CODE_LOGIN_THREE) {
                    //如果服务器返回的status=402，显示图片验证码，否则不显示
                    //rl_picture_code_layout.setVisibility(View.VISIBLE);
                    tv_login.setBackgroundResource(R.drawable.rectangle_5dp_blue);
                    tv_login.setClickable(false);
                } else {
                    //ToastUtils.make().setGravity(Gravity.CENTER, 0, 0).setTextColor(Color.RED).setDurationIsLong(true).show();
                }
            }

            @Override
            public void onError(Call call, okhttp3.Response response, Exception e) {
                super.onError(call, response, e);
                OkLogger.e(e.toString());
            }
        });
    }

    /**
     * 登录,选择某个企业
     */
    private void login() {

        Map<String, Object> mapList = new HashMap<String, Object>();
        mapList.put("companyID", companyID);
        String str = GsonConvertUtil.toJson(mapList);

        UserRequestManager.getInstance().login(this, str, new DialogCallback<BaseResponse<UserLoginModle>>() {
            @Override
            public void onSuccess(BaseResponse<UserLoginModle> baseResponse, Call call, okhttp3.Response response) {
                if (baseResponse.successed) {
                    startActivity(new Intent(UserLoginActivity.this, MainActivity.class));
                    finish();
                } else {
                    //ToastUtils.make().setGravity(Gravity.CENTER, 0, 0).setTextColor(Color.RED).setDurationIsLong(true).show();
                }
            }

            @Override
            public void onError(Call call, okhttp3.Response response, Exception e) {
                super.onError(call, response, e);
                OkLogger.e(e.toString());
            }
        });
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case AppConstant.VALUE_COMMON_PHONE_CODE_ACTIVITY://找回密码界面（或注册界面）
                if (resultCode == RESULT_OK) {
                    finish();
                }
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        OkHttpUtils.getInstance().cancelTag(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (ll_login_layout.getVisibility() == View.VISIBLE) {
            PreventKeyboardBlockUtil.getInstance(this).setBtnView(tv_login).register();
        } else {

            PreventKeyboardBlockUtil.getInstance(this).setBtnView(tv_get_code_login).register();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        PreventKeyboardBlockUtil.getInstance(this).unRegister();
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
}
