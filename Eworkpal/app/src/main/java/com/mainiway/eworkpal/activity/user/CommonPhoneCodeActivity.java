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
import com.mainiway.okhttp.utils.OkLogger;

import java.util.HashMap;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Response;

/**
 * ===========================================
 * 作    者：gao_chun
 * 版    本：1.0
 * 创建日期：2016-11-19.
 * 描    述：公用的获取手机验证码（需根据传递的Intent信息设置Title）
 * ===========================================
 */
public class CommonPhoneCodeActivity extends BaseTitleActivity {

    private String label;
    private TextView tv_get_code, tv_get_code_next;
    private RelativeLayout rl_picture_code_layout;
    private ImageView iv_picture_code;
    private EditText et_picture_code, et_phone_number, et_phone_code;
    private boolean code_button_normal = true;//根据服务器返回的status值，判断获取验证码按钮是否正常，默认为true
    private int pass = 0;//判断是否进行过图片验证码验证，默认值为0。（0：未验证，1：验证）

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
        iv_picture_code.setOnClickListener(new CommonPhoneCodeActivity.FastClickListener());

    }


    private class FastClickListener extends OnClickFastListener {

        @Override
        public void onFastClick(View v) {

            switch (v.getId()) {

                case R.id.tv_get_code_next:  //下一步
                    if (label.equals(AppConstant.PHONE_CODE_ENTERPRISE)) {
                        if (rl_picture_code_layout.getVisibility() == View.VISIBLE) {//visible,当显示图片验证码布局时
                            if (et_picture_code.getText().length() == 4) {
                                if (et_picture_code.getText().toString().equalsIgnoreCase(ImageCodeView.getInstance().getCode())) {
                                    //此处跳转到企业创建界面，可能需要携带参数
                                    startActivity(new Intent(CommonPhoneCodeActivity.this, CreateEnterpriseActivity.class));
                                    overridePendingTransition(R.anim.push_right_in, R.anim.push_right_out);
                                }
                            } else {
                                ToastUtils.showToastShort(getString(R.string.image_verification_code_error));
                            }
                        } else {
                            //此处跳转到企业创建界面，可能需要携带参数
                            Intent intent = new Intent();
                            intent.putExtra("mobile", et_phone_number.getText().toString());
                            intent.setClass(CommonPhoneCodeActivity.this, CreateEnterpriseActivity.class);
                            startActivity(intent);
                            overridePendingTransition(R.anim.push_right_in, R.anim.push_right_out);
                        }


                    } else if (label.equals(AppConstant.PHONE_CODE_FORGET_PWD)) {

                        if (rl_picture_code_layout.getVisibility() == View.VISIBLE) {//当显示图片验证码布局时
                            if (et_picture_code.getText().length() == 4) {
                                if (et_picture_code.getText().toString().equalsIgnoreCase(ImageCodeView.getInstance().getCode())) {
                                    //此处跳转到找回密码界面
                                    Intent intent = new Intent();
                                    intent.putExtra("mobile", et_phone_number.getText().toString());
                                    intent.setClass(CommonPhoneCodeActivity.this, ForgetPwdActivity.class);
                                    startActivity(intent);
                                    overridePendingTransition(R.anim.push_right_in, R.anim.push_right_out);
                                }
                            } else {
                                ToastUtils.showToastShort(getString(R.string.image_verification_code_error));
                            }
                        } else {
                            //此处跳转到找回密码界面，可能需要携带参数
                            Intent intent = new Intent();
                            intent.putExtra("mobile", et_phone_number.getText().toString());
                            intent.setClass(CommonPhoneCodeActivity.this, ForgetPwdActivity.class);
                            startActivityForResult(intent, AppConstant.VALUE_FORGET_PWD_ACTIVITY);
                            overridePendingTransition(R.anim.push_right_in, R.anim.push_right_out);
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
                    if (code_button_normal) {
                        TimeCount timeCount = new TimeCount(3000, 1000);//60000, 1000
                        timeCount.setBtn(tv_get_code, getString(R.string.re_acquisition));
                        timeCount.start();
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
                    DealViewUtils.buttonState(tv_get_code_next, R.drawable.rectangle_27dp_blue_selected, true);
                } else {
                    DealViewUtils.buttonState(tv_get_code_next, R.drawable.rectangle_27dp_blue, false);
                }


                //如果弹出图片验证码，则判断验图片证码正确与否，获取验证码按钮可点击状态
                if (et_picture_code.getText().length() == 4) {
                    if (et_picture_code.getText().toString().equalsIgnoreCase(ImageCodeView.getInstance().getCode())) {
                        rl_picture_code_layout.setVisibility(View.GONE);
                        DealViewUtils.buttonState(tv_get_code, R.drawable.rectangle_27dp_blue_selected, true);
                        code_button_normal = true;
                        pass = 1;//表示已经验证过图片验证码
                    } else {
                        ToastUtils.showToastShort(getString(R.string.image_verification_code_error));
                        code_button_normal = false;
                    }
                }


            } else {//不显示图片验证码时，只判断手机号、验证码是否为空
                if (!TextUtils.isEmpty(et_phone_number.getText())) {
                    DealViewUtils.buttonState(tv_get_code, R.drawable.rectangle_27dp_blue_selected, true);
                } else {
                    DealViewUtils.buttonState(tv_get_code, R.drawable.rectangle_27dp_blue, false);
                }

                if (!TextUtils.isEmpty(et_phone_number.getText()) && !TextUtils.isEmpty(et_phone_code.getText())) {
                    DealViewUtils.buttonState(tv_get_code_next, R.drawable.rectangle_27dp_blue_selected, true);
                } else {
                    DealViewUtils.buttonState(tv_get_code_next, R.drawable.rectangle_27dp_blue, false);
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
        mapList.put("type", ResultErrorCode.TYPE_CREATE_ENTERPRISE);
        mapList.put("company_id", 0);

        String str = GsonConvertUtil.toJson(mapList);

        UserRequestManager.getInstance().setPhoneCode(this, str, new DialogCallback<BaseResponse<String>>(this) {
            @Override
            public void onSuccess(BaseResponse<String> responseData, Call call, Response response) {
                if (responseData.successed) {
                    pass = 0;
                    //如果服务器返回的status=403，显示图片验证码，否则不显示
                    if (responseData.status == ResultErrorCode.CODE_SEND_CODE_THREE) {
                        rl_picture_code_layout.setVisibility(View.VISIBLE);
                        //获取验证码不可点击
                        DealViewUtils.buttonState(tv_get_code, R.drawable.rectangle_27dp_blue, false);
                        //下一步不可点击
                        DealViewUtils.buttonState(tv_get_code_next, R.drawable.rectangle_27dp_blue, false);
                        code_button_normal = false;
                    } else {
                        code_button_normal = true;
                    }
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
            case AppConstant.VALUE_FORGET_PWD_ACTIVITY://找回密码界面返回的
                setResult(RESULT_OK);
                finish();
                break;
        }
    }
}
