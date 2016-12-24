package com.mainiway.eworkpal.activity.user;

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
 * 描    述：申请加入企业
 * ===========================================
 */

public class UserJoinEnterpriseActivity extends BaseTitleActivity {

    private View ll_join_enterprise_id;
    private View ll_join_enterprise;
    private View ll_join_enterprise_verify;
    private TextView tv_next_one, tv_next_two, tv_register_get_code;
    private EditText et_enterprise_id, et_name, et_department, et_position, et_phone_number, et_phone_code, et_picture_code;
    private RelativeLayout rl_picture_code_layout;
    private ImageView iv_picture_code;
    private boolean code_button_normal = true;//根据服务器返回的status值，判断获取验证码按钮是否正常，默认为true
    private int pass = 0;//判断是否进行过图片验证码验证，默认值为0。（0：未验证，1：验证）

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_join_enterprise);
        setTitle(getString(R.string.application_to_join_the_enterprise));
        showBackwardView(true);
        initView();
    }

    private void initView() {
        //输入企业ID布局
        ll_join_enterprise_id = findView(R.id.ll_join_enterprise_id);

        tv_next_one = findView(R.id.tv_next_one);
        tv_next_one.setOnClickListener(new FastClickListener());
        tv_next_one.setClickable(false);

        et_enterprise_id = findView(R.id.et_enterprise_id);
        et_enterprise_id.addTextChangedListener(textWatcher);

        //填写申请信息布局
        ll_join_enterprise = findView(R.id.ll_join_enterprise);

        tv_next_two = findView(R.id.tv_next_two);
        tv_next_two.setOnClickListener(new FastClickListener());
        tv_next_two.setClickable(false);

        et_name = findView(R.id.et_name);
        et_name.addTextChangedListener(textWatcher);

        et_department = findView(R.id.et_department);
        et_department.addTextChangedListener(textWatcher);

        et_position = findView(R.id.et_position);
        et_position.addTextChangedListener(textWatcher);

        et_phone_number = findView(R.id.et_phone_number);
        et_phone_number.addTextChangedListener(textWatcher);

        et_phone_code = findView(R.id.et_phone_code);
        et_phone_code.addTextChangedListener(textWatcher);

        tv_register_get_code = findView(R.id.tv_register_get_code);
        tv_register_get_code.setOnClickListener(new FastClickListener());
        tv_register_get_code.setClickable(false);

        rl_picture_code_layout = findView(R.id.rl_picture_code_layout);

        et_picture_code = findView(R.id.et_picture_code);
        et_picture_code.addTextChangedListener(textWatcher);

        iv_picture_code = findView(R.id.iv_picture_code);
        iv_picture_code.setImageBitmap(ImageCodeView.getInstance().createBitmap());
        iv_picture_code.setOnClickListener(new FastClickListener());

        //等待审批布局
        ll_join_enterprise_verify = findView(R.id.ll_join_enterprise_verify);
        findView(R.id.tv_next_three).setOnClickListener(new FastClickListener());


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
            //输入企业ID布局显示时
            if (ll_join_enterprise_id.getVisibility() == View.VISIBLE) {
                if (!TextUtils.isEmpty(et_enterprise_id.getText())) {
                    DealViewUtils.buttonState(tv_next_one, R.drawable.rectangle_27dp_blue_selected, true);
                } else {
                    DealViewUtils.buttonState(tv_next_one, R.drawable.rectangle_27dp_blue, false);
                }
            }

            //填写申请信息布局显示时
            if (ll_join_enterprise.getVisibility() == View.VISIBLE) {
                //如果手机号不为空，验证码按钮可点击
                if (!TextUtils.isEmpty(et_phone_number.getText())) {
                    DealViewUtils.buttonState(tv_register_get_code, R.drawable.rectangle_27dp_blue_selected, true);
                }

                //判断图片验证码布局是否显示
                if (rl_picture_code_layout.getVisibility() == View.VISIBLE) {
                    //显示图片验证码时
                    if (!TextUtils.isEmpty(et_name.getText()) && !TextUtils.isEmpty(et_department.getText()) && !TextUtils.isEmpty(et_position.getText())
                            && !TextUtils.isEmpty(et_phone_number.getText()) && !TextUtils.isEmpty(et_phone_code.getText()) && !TextUtils.isEmpty(et_picture_code.getText())) {
                        DealViewUtils.buttonState(tv_next_two, R.drawable.rectangle_27dp_blue_selected, true);
                    } else {
                        DealViewUtils.buttonState(tv_next_two, R.drawable.rectangle_27dp_blue, false);
                    }

                    //如果弹出图片验证码，则判断验图片证码正确，获取验证码按钮可点击
                    if (et_picture_code.getText().length() == 4) {
                        if (et_picture_code.getText().toString().equalsIgnoreCase(ImageCodeView.getInstance().getCode())) {
                            rl_picture_code_layout.setVisibility(View.GONE);
                            DealViewUtils.buttonState(tv_register_get_code, R.drawable.rectangle_27dp_blue_selected, true);
                            pass = 1;
                        } else {
                            ToastUtils.showToastShort(getString(R.string.image_verification_code_error));
                        }
                    }

                } else {
                    //不显示图片验证码时
                    if (!TextUtils.isEmpty(et_name.getText()) && !TextUtils.isEmpty(et_department.getText()) && !TextUtils.isEmpty(et_position.getText())
                            && !TextUtils.isEmpty(et_phone_number.getText()) && !TextUtils.isEmpty(et_phone_code.getText())) {
                        DealViewUtils.buttonState(tv_next_two, R.drawable.rectangle_27dp_blue_selected, true);
                    } else {
                        DealViewUtils.buttonState(tv_next_two, R.drawable.rectangle_27dp_blue, false);
                    }
                }

            }


        }
    };


    private class FastClickListener extends OnClickFastListener {

        @Override
        public void onFastClick(View v) {

            switch (v.getId()) {

                case R.id.tv_next_one://下一步

                    //verificationEnterpriseId();
                    ll_join_enterprise_id.setVisibility(View.GONE);
                    ll_join_enterprise.setVisibility(View.VISIBLE);

                    break;

                case R.id.tv_next_two://下一步

                    if (rl_picture_code_layout.getVisibility() == View.VISIBLE) {//返回值为0，visible,当显示图片验证码布局时
                        if (et_picture_code.getText().length() == 4) {
                            if (et_picture_code.getText().toString().equalsIgnoreCase(ImageCodeView.getInstance().getCode())) {
                                ll_join_enterprise.setVisibility(View.GONE);
                                ll_join_enterprise_verify.setVisibility(View.VISIBLE);
                                applicationToJoinTheEnterprise();
                            }
                        } else {
                            ToastUtils.showToastShort(getString(R.string.image_verification_code_error));
                        }
                    } else {
                        applicationToJoinTheEnterprise();
                        //到时删掉
                        ll_join_enterprise.setVisibility(View.GONE);
                        ll_join_enterprise_verify.setVisibility(View.VISIBLE);
                    }


                    break;

                case R.id.tv_next_three://返回
                    finish();
                    break;

                case R.id.tv_register_get_code://获取验证码

                    if (ValidateUtils.isMobile(et_phone_number.getText().toString())) {
                        setPhoneCode();
                    } else {
                        ToastUtils.showToastShort(getString(R.string.please_enter_the_correct_phone_number));
                        break;
                    }
                    if (code_button_normal) {

                        TimeCount timeCount = new TimeCount(3000, 1000);//60000, 1000
                        timeCount.setBtn(tv_register_get_code, getString(R.string.re_acquisition));
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
     * 申请加入企业验证企业ID
     */
    private void verificationEnterpriseId() {
        Map<String, Object> mapList = new HashMap<String, Object>();
        mapList.put("code", et_enterprise_id.getText().toString());
        String str = GsonConvertUtil.toJson(mapList);

        UserRequestManager.getInstance().setPhoneCode(this, str, new DialogCallback<BaseResponse<String>>(this) {
            @Override
            public void onSuccess(BaseResponse<String> responseData, Call call, Response response) {
                if (responseData.successed) {
                    ll_join_enterprise_id.setVisibility(View.GONE);
                    ll_join_enterprise.setVisibility(View.VISIBLE);
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
     * 发送手机短信码
     */
    private void setPhoneCode() {
        Map<String, Object> mapList = new HashMap<String, Object>();
        mapList.put("phone", et_phone_number.getText().toString());
        mapList.put("pass", pass);
        mapList.put("type", ResultErrorCode.TYPE_JOIN_ENTERPRISE);
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
                        DealViewUtils.buttonState(tv_register_get_code, R.drawable.rectangle_27dp_blue, false);
                        //下一步不可点击
                        DealViewUtils.buttonState(tv_next_two, R.drawable.rectangle_27dp_blue, false);
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


    /**
     * 申请加入企业
     */
    private void applicationToJoinTheEnterprise() {
        Map<String, Object> mapList = new HashMap<String, Object>();
        mapList.put("companyID", et_enterprise_id.getText().toString());
        mapList.put("name", et_name.getText().toString());
        mapList.put("departmentName", et_department.getText().toString());
        mapList.put("positionName", et_position.getText().toString());
        mapList.put("phone", et_phone_number.getText().toString());

        String str = GsonConvertUtil.toJson(mapList);

        UserRequestManager.getInstance().setPhoneCode(this, str, new DialogCallback<BaseResponse<String>>(this) {
            @Override
            public void onSuccess(BaseResponse<String> responseData, Call call, Response response) {
                if (responseData.successed) {
                    ToastUtils.showToastShort(responseData.message.get(0).msg);
                    ll_join_enterprise.setVisibility(View.GONE);
                    ll_join_enterprise_verify.setVisibility(View.VISIBLE);
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
    protected void onDestroy() {
        super.onDestroy();
    }

}
