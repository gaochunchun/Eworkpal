package com.mainiway.eworkpal.activity.user;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.mainiway.eworkpal.R;
import com.mainiway.eworkpal.base.BaseTitleActivity;
import com.mainiway.eworkpal.constant.Constants;
import com.mainiway.eworkpal.listener.OnClickFastListener;
import com.mainiway.eworkpal.utils.DealViewUtils;
import com.mainiway.eworkpal.utils.TimeCount;
import com.mainiway.eworkpal.utils.ToastUtils;
import com.mainiway.eworkpal.widgets.ImageCodeView;

import static com.mainiway.eworkpal.R.id.tv_register_get_code;

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
    private TextView tv_get_code, tv_getcode_next;
    private RelativeLayout rl_picture_code_layout;
    private ImageView iv_picture_code;
    private EditText et_picture_code, et_phone_number, et_phone_code;
    private int count;//测试用的点击验证码次数的变量

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_common_phonecode);
        handlerIntent();
        if (label.equals(Constants.PHONE_CODE_ENTERPRISE)) {
            setTitle("注册");
        } else if (label.equals(Constants.PHONE_CODE_FORGET_PWD)) {
            setTitle("找回密码");
        }
        showBackwardView(true);
        initView();

    }

    //获取Intent传递的数据
    private void handlerIntent() {

        if (getIntent() != null) {
            label = getIntent().getStringExtra(Constants.LABEL_PHONE_CODE);
        }
    }

    private void initView() {
        tv_getcode_next = findView(R.id.tv_getcode_next);
        tv_getcode_next.setOnClickListener(new FastClickListener());
        tv_getcode_next.setClickable(false);

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

                case R.id.tv_getcode_next:  //下一步
                    if (label.equals(Constants.PHONE_CODE_ENTERPRISE)) {
                        int visibility = rl_picture_code_layout.getVisibility();
                        if (visibility == 0) {//返回值为0，visible,当显示图片验证码布局时
                            if (et_picture_code.getText().length() == 4) {
                                if (et_picture_code.getText().toString().equalsIgnoreCase(ImageCodeView.getInstance().getCode())) {
                                    //此处跳转到企业创建界面，可能需要携带参数
                                    startActivity(new Intent(CommonPhoneCodeActivity.this, CreateEnterpriseActivity.class));
                                    overridePendingTransition(R.anim.push_right_in, R.anim.push_right_out);
                                }
                            } else {
                                ToastUtils.showToastShort("图片验证码错误");
                            }
                        } else {
                            //此处跳转到企业创建界面，可能需要携带参数
                            startActivity(new Intent(CommonPhoneCodeActivity.this, CreateEnterpriseActivity.class));
                            overridePendingTransition(R.anim.push_right_in, R.anim.push_right_out);
                        }


                    } else if (label.equals(Constants.PHONE_CODE_FORGET_PWD)) {

                        int visibility = rl_picture_code_layout.getVisibility();
                        if (visibility == 0) {//返回值为0，visible,当显示图片验证码布局时
                            if (et_picture_code.getText().length() == 4) {
                                if (et_picture_code.getText().toString().equalsIgnoreCase(ImageCodeView.getInstance().getCode())) {
                                    //此处跳转到找回密码界面，可能需要携带参数
                                    startActivity(new Intent(CommonPhoneCodeActivity.this, ForgetPwdActivity.class));
                                    overridePendingTransition(R.anim.push_right_in, R.anim.push_right_out);
                                }
                            } else {
                                ToastUtils.showToastShort("图片验证码错误");
                            }
                        } else {
                            //此处跳转到找回密码界面，可能需要携带参数
                            startActivity(new Intent(CommonPhoneCodeActivity.this, ForgetPwdActivity.class));
                            overridePendingTransition(R.anim.push_right_in, R.anim.push_right_out);
                        }


                    }

                    break;

                case R.id.tv_get_code://获取验证码

                    count++;
                    if (count > 3) {
                        rl_picture_code_layout.setVisibility(View.VISIBLE);
                        //获取验证码不可点击
                        DealViewUtils.buttonState(tv_get_code, R.drawable.rectangle_27dp_blue, false);
                        //下一步不可点击
                        DealViewUtils.buttonState(tv_getcode_next, R.drawable.rectangle_27dp_blue, false);
                    } else {

                        TimeCount timeCount = new TimeCount(3000, 1000);//60000, 1000
                        timeCount.setBtn(tv_get_code, "重新获取");
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

            int visibility = rl_picture_code_layout.getVisibility();
            if (visibility == 0) {//返回值为0，visible,弹出图片验证码布局

                //显示图片验证码时，判断手机号、验证码、图片验证码是否为空
                if (!TextUtils.isEmpty(et_phone_number.getText()) && !TextUtils.isEmpty(et_phone_code.getText()) && !TextUtils.isEmpty(et_picture_code.getText())) {
                    DealViewUtils.buttonState(tv_getcode_next, R.drawable.rectangle_27dp_blue_selected, true);
                } else {
                    DealViewUtils.buttonState(tv_getcode_next, R.drawable.rectangle_27dp_blue, false);
                }


                //如果弹出图片验证码，则判断验图片证码正确，获取验证码按钮可点击
                if (et_picture_code.getText().length() == 4) {
                    if (et_picture_code.getText().toString().equalsIgnoreCase(ImageCodeView.getInstance().getCode())) {
                        rl_picture_code_layout.setVisibility(View.GONE);
                        DealViewUtils.buttonState(tv_get_code, R.drawable.rectangle_27dp_blue_selected, true);
                        count = 0;
                    } else {
                        ToastUtils.showToastShort("图片验证码错误");
                    }
                }


            } else {//不显示图片验证码时，只判断手机号、验证码是否为空
                if (!TextUtils.isEmpty(et_phone_number.getText())) {
                    DealViewUtils.buttonState(tv_get_code, R.drawable.rectangle_27dp_blue_selected, true);
                } else {
                    DealViewUtils.buttonState(tv_get_code, R.drawable.rectangle_27dp_blue, false);
                }

                if (!TextUtils.isEmpty(et_phone_number.getText()) && !TextUtils.isEmpty(et_phone_code.getText())) {
                    DealViewUtils.buttonState(tv_getcode_next, R.drawable.rectangle_27dp_blue_selected, true);
                } else {
                    DealViewUtils.buttonState(tv_getcode_next, R.drawable.rectangle_27dp_blue, false);
                }
            }

        }

    };


}
