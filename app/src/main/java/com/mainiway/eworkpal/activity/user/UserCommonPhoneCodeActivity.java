package com.mainiway.eworkpal.activity.user;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.mainiway.eworkpal.R;
import com.mainiway.eworkpal.base.BaseResponse;
import com.mainiway.eworkpal.base.BaseTitleActivity;
import com.mainiway.eworkpal.callback.DialogCallback;
import com.mainiway.eworkpal.constant.AppConstant;
import com.mainiway.eworkpal.constant.ResultErrorCode;
import com.mainiway.eworkpal.listener.OnClickFastListener;
import com.mainiway.eworkpal.request.UserRequestManager;
import com.mainiway.eworkpal.utils.DealViewUtils;
import com.mainiway.eworkpal.utils.GsonConvertUtil;
import com.mainiway.eworkpal.utils.TimeCount;
import com.mainiway.eworkpal.utils.ToastUtils;
import com.mainiway.eworkpal.utils.ValidateUtils;
import com.mainiway.eworkpal.widgets.ImageCodeView;
import com.mainiway.okhttp.OkHttpUtils;
import com.mainiway.okhttp.utils.OkLogger;

import java.util.HashMap;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Response;

/**
 * ===========================================
 * 版    本：1.0
 * 描    述：公用的获取手机验证码
 * ===========================================
 */
public class UserCommonPhoneCodeActivity extends BaseTitleActivity {

    private String label;
    private TextView tv_get_code, tv_get_code_next;
    private RelativeLayout rl_picture_code_layout;
    private ImageView iv_picture_code;
    private EditText et_picture_code, et_phone_number, et_phone_code;
    private int pass = 0;//判断是否进行过图片验证码验证，默认值为0。（0：未验证，1：验证）
    private int type;//往服务器传的模块名称

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_common_phonecode);
        handlerIntent();
        if (label.equals(AppConstant.PHONE_CODE_ENTERPRISE)) {
            setTitle(getString(R.string.register));
        } else if (label.equals(AppConstant.PHONE_CODE_FORGET_PWD)) {
            setTitle(getString(R.string.retrieve_password));
        }
        showBackwardView(true);
        initView();

    }

    //获取Intent传递的数据
    private void handlerIntent() {

        if (getIntent() != null) {
            label = getIntent().getStringExtra(AppConstant.LABEL_PHONE_CODE);


            if (label.equals(AppConstant.PHONE_CODE_ENTERPRISE)) {//创建企业
                type = ResultErrorCode.TYPE_CREATE_ENTERPRISE;
            } else if (label.equals(AppConstant.PHONE_CODE_FORGET_PWD)) {//找回密码
                type = ResultErrorCode.TYPE_RETRIEVE_PASSWORD;
            }
        }
    }

    private void initView() {
        tv_get_code_next = findView(R.id.tv_get_code_next);
        tv_get_code_next.setOnClickListener(new FastClickListener());
        tv_get_code_next.setClickable(false);

        tv_get_code = findView(R.id.tv_get_code);
        tv_get_code.setOnClickListener(new FastClickListener());
        tv_get_code.setClickable(false);
        rl_picture_code_layout = findView(R.id.rl_picture_code_layout);

        et_phone_number = findView(R.id.et_phone_number);
        et_phone_number.addTextChangedListener(textWatcher);

        et_phone_code = findView(R.id.et_phone_code);
        et_phone_code.addTextChangedListener(textWatcher);

        et_picture_code = findView(R.id.et_picture_code);
        et_picture_code.addTextChangedListener(textWatcher);

        iv_picture_code = findView(R.id.iv_picture_code);
        iv_picture_code.setImageBitmap(ImageCodeView.getInstance().createBitmap());
        iv_picture_code.setOnClickListener(new UserCommonPhoneCodeActivity.FastClickListener());

    }


    private class FastClickListener extends OnClickFastListener {

        @Override
        public void onFastClick(View v) {

            switch (v.getId()) {

                case R.id.tv_get_code_next:  //下一步
                    if (label.equals(AppConstant.PHONE_CODE_ENTERPRISE)) {//创建企业
                        if (rl_picture_code_layout.getVisibility() == View.VISIBLE) {//visible,当显示图片验证码布局时
                            if (!et_picture_code.getText().toString().equalsIgnoreCase(ImageCodeView.getInstance().getCode())) {
                                ToastUtils.showToastShort(getString(R.string.image_verification_code_error));
                            }
                        } else {
                            verifyPhoneNumber();
                        }

                    } else if (label.equals(AppConstant.PHONE_CODE_FORGET_PWD)) {//找回密码

                        if (rl_picture_code_layout.getVisibility() == View.VISIBLE) {//当显示图片验证码布局时
                            if (!et_picture_code.getText().toString().equalsIgnoreCase(ImageCodeView.getInstance().getCode())) {
                                ToastUtils.showToastShort(getString(R.string.image_verification_code_error));
                            }
                        } else {
                            verifyPhoneNumber();
                        }
                    }

                    break;

                case R.id.tv_get_code://获取验证码
                    if (ValidateUtils.isMobile(et_phone_number.getText().toString())) {
                        setPhoneCode();
                    } else {
                        ToastUtils.showToastShort(getString(R.string.please_enter_the_correct_phone_number));
                        break;
                    }

                    break;
                case R.id.iv_picture_code://动态验证码图片
                    iv_picture_code.setImageBitmap(ImageCodeView.getInstance().createBitmap());
                    break;

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

            if (rl_picture_code_layout.getVisibility() == View.VISIBLE) {//弹出图片验证码布局

                //显示图片验证码时，判断手机号、验证码、图片验证码是否为空
                if (!TextUtils.isEmpty(et_phone_number.getText()) && !TextUtils.isEmpty(et_phone_code.getText()) && !TextUtils.isEmpty(et_picture_code.getText())) {
                    DealViewUtils.buttonState(tv_get_code_next, R.drawable.rectangle_5dp_blue_selected, true);
                } else {
                    DealViewUtils.buttonState(tv_get_code_next, R.drawable.rectangle_5dp_blue, false);
                }


                //如果弹出图片验证码，则判断验图片证码正确与否，设置获取验证码按钮状态
                if (et_picture_code.getText().length() == 4) {
                    if (et_picture_code.getText().toString().equalsIgnoreCase(ImageCodeView.getInstance().getCode())) {
                        rl_picture_code_layout.setVisibility(View.GONE);
                        DealViewUtils.buttonState(tv_get_code, R.drawable.rectangle_5dp_blue_selected, true);
                        pass = 1;
                    } else {
                        ToastUtils.showToastShort(getString(R.string.image_verification_code_error));
                    }
                }


            } else {//不显示图片验证码时，只判断手机号、验证码是否为空
                //获取验证码按钮的状态
                if (!TextUtils.isEmpty(et_phone_number.getText())) {
                    DealViewUtils.buttonState(tv_get_code, R.drawable.rectangle_5dp_blue_selected, true);
                } else {
                    DealViewUtils.buttonState(tv_get_code, R.drawable.rectangle_5dp_blue, false);
                }
                //下一步按钮的状态
                if (!TextUtils.isEmpty(et_phone_number.getText()) && !TextUtils.isEmpty(et_phone_code.getText())) {
                    DealViewUtils.buttonState(tv_get_code_next, R.drawable.rectangle_5dp_blue_selected, true);
                } else {
                    DealViewUtils.buttonState(tv_get_code_next, R.drawable.rectangle_5dp_blue, false);
                }
            }

        }

    };

    /**
     * 发送手机短信码
     */
    private void setPhoneCode() {
        Map<String, Object> mapList = new HashMap<String, Object>();
        mapList.put("phone", et_phone_number.getText().toString());
        mapList.put("pass", pass);
        mapList.put("type", type);
        mapList.put("company_id", 0);

        String str = GsonConvertUtil.toJson(mapList);

        UserRequestManager.getInstance().setPhoneCode(this, str, new DialogCallback<BaseResponse<String>>(this) {
            @Override
            public void onSuccess(BaseResponse<String> responseData, Call call, Response response) {
                pass = 0;
                if (responseData.successed) {
                    TimeCount timeCount = new TimeCount(60000, 1000);//60000, 1000
                    timeCount.setBtn(tv_get_code, getString(R.string.re_acquisition));
                    timeCount.start();
                    ToastUtils.showToastShort(responseData.message.get(0).msg);
                } else if (responseData.status == ResultErrorCode.CODE_SEND_CODE_THREE) {
                    //如果服务器返回的status=403，显示图片验证码
                    rl_picture_code_layout.setVisibility(View.VISIBLE);
                    //获取验证码不可点击
                    DealViewUtils.buttonState(tv_get_code, R.drawable.rectangle_5dp_blue, false);
                    //下一步不可点击
                    DealViewUtils.buttonState(tv_get_code_next, R.drawable.rectangle_5dp_blue, false);
                } else {
                    ToastUtils.showToastShort(responseData.message.get(0).msg);
                }
            }

            @Override
            public void onError(Call call, Response response, Exception e) {
                super.onError(call, response, e);
                OkLogger.e(e.toString());
            }
        });

    }


    /**
     * 验证手机号码(下一步或确认时候调用)
     */
    private void verifyPhoneNumber() {
        Map<String, Object> mapList = new HashMap<String, Object>();
        mapList.put("phone", et_phone_number.getText().toString());
        mapList.put("code", et_phone_code.getText().toString());
        mapList.put("type", type);
        mapList.put("company_id", 0);

        String str = GsonConvertUtil.toJson(mapList);

        UserRequestManager.getInstance().verifyPhoneNumber(this, str, new DialogCallback<BaseResponse<String>>(this) {
            @Override
            public void onSuccess(BaseResponse<String> responseData, Call call, Response response) {
                if (responseData.successed) {
                    Intent intent = new Intent();
                    intent.putExtra("mobile", et_phone_number.getText().toString());
                    if (label.equals(AppConstant.PHONE_CODE_ENTERPRISE)) {//创建企业
                        intent.setClass(UserCommonPhoneCodeActivity.this, UserCreateEnterpriseActivity.class);
                        startActivityForResult(intent, AppConstant.VALUE_CREATE_ENTERPRISE);
                    } else if (label.equals(AppConstant.PHONE_CODE_FORGET_PWD)) {//找回密码
                        intent.setClass(UserCommonPhoneCodeActivity.this, UserForgetPwdActivity.class);
                        startActivityForResult(intent, AppConstant.VALUE_FORGET_PWD_ACTIVITY);
                    }
                    overridePendingTransition(R.anim.push_right_in, R.anim.push_right_out);

                } else {
                    ToastUtils.showToastShort(responseData.message.get(0).msg);
                }
            }

            @Override
            public void onError(Call call, Response response, Exception e) {
                super.onError(call, response, e);
                OkLogger.e(e.toString());
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {

            case AppConstant.VALUE_CREATE_ENTERPRISE://创建企业
                if (resultCode == RESULT_OK) {
                    setResult(RESULT_OK);
                    finish();
                }
            case AppConstant.VALUE_FORGET_PWD_ACTIVITY://找回密码界面返回的
                if (resultCode == RESULT_OK) {
                    setResult(RESULT_OK);
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
}
