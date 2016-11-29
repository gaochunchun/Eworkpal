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
import com.mainiway.eworkpal.base.BaseTitleActivity;
import com.mainiway.eworkpal.listener.OnClickFastListener;
import com.mainiway.eworkpal.utils.DealViewUtils;
import com.mainiway.eworkpal.utils.TimeCount;
import com.mainiway.eworkpal.utils.ToastUtils;
import com.mainiway.eworkpal.widgets.ImageCodeView;

/**
 * ===========================================
 * 作    者：gao_chun
 * 版    本：1.0
 * 创建日期：2016-11-19.
 * 描    述：申请加入企业
 * ===========================================
 */

public class JoinEnterpriseActivity extends BaseTitleActivity {

    private View ll_join_enterprise_id;
    private View ll_join_enterprise;
    private View ll_join_enterprise_verify;
    private TextView tv_next_one, tv_next_two, tv_register_get_code;
    private EditText et_enterprise_id, et_name, et_department, et_position, et_phone_number, et_phone_code, et_picture_code;
    private RelativeLayout rl_picture_code_layout;
    private ImageView iv_picture_code;
    private int count;//测试用的点击验证码次数的变量

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_join_enterprise);
        setTitle("申请加入企业");
        showBackwardView(true);
        initView();
    }

    private void initView() {
        //输入企业ID布局
        ll_join_enterprise_id = (View) findView(R.id.ll_join_enterprise_id);

        tv_next_one = findView(R.id.tv_next_one);
        tv_next_one.setOnClickListener(new FastClickListener());
        tv_next_one.setClickable(false);

        et_enterprise_id = findView(R.id.et_enterprise_id);
        et_enterprise_id.addTextChangedListener(textWatcher);

        //填写申请信息布局
        ll_join_enterprise = (View) findView(R.id.ll_join_enterprise);

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
        ll_join_enterprise_verify = (View) findView(R.id.ll_join_enterprise_verify);
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
            int visibility_one = ll_join_enterprise_id.getVisibility();
            if (visibility_one == 0) {
                if (!TextUtils.isEmpty(et_enterprise_id.getText())) {
                    DealViewUtils.buttonState(tv_next_one, R.drawable.rectangle_27dp_blue_selected, true);
                } else {
                    DealViewUtils.buttonState(tv_next_one, R.drawable.rectangle_27dp_blue, false);
                }
            }

            //填写申请信息布局显示时
            int visibility_two = ll_join_enterprise.getVisibility();
            if (visibility_two == 0) {
                //如果手机号不为空，验证码按钮可点击
                if (!TextUtils.isEmpty(et_phone_number.getText())) {
                    DealViewUtils.buttonState(tv_register_get_code, R.drawable.rectangle_27dp_blue_selected, true);
                }

                //判断图片验证码布局是否显示
                int visibility_picture = rl_picture_code_layout.getVisibility();
                if (visibility_picture == 0) {
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
                            count = 0;
                        } else {
                            ToastUtils.showToastShort("图片验证码错误");
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
                    ll_join_enterprise_id.setVisibility(View.GONE);
                    ll_join_enterprise.setVisibility(View.VISIBLE);

                    break;

                case R.id.tv_next_two://下一步

                    int visibility = rl_picture_code_layout.getVisibility();
                    if (visibility == 0) {//返回值为0，visible,当显示图片验证码布局时
                        if (et_picture_code.getText().length() == 4) {
                            if (et_picture_code.getText().toString().equalsIgnoreCase(ImageCodeView.getInstance().getCode())) {
                                ll_join_enterprise.setVisibility(View.GONE);
                                ll_join_enterprise_verify.setVisibility(View.VISIBLE);
                            }
                        } else {
                            ToastUtils.showToastShort("图片验证码错误");
                        }
                    } else {
                        ll_join_enterprise.setVisibility(View.GONE);
                        ll_join_enterprise_verify.setVisibility(View.VISIBLE);
                    }


                    break;

                case R.id.tv_next_three://返回
                    finish();
                    break;

                case R.id.tv_register_get_code://获取验证码

                    count++;
                    if (count > 3) {
                        rl_picture_code_layout.setVisibility(View.VISIBLE);
                        //获取验证码不可点击
                        DealViewUtils.buttonState(tv_register_get_code, R.drawable.rectangle_27dp_blue, false);
                        //下一步不可点击
                        DealViewUtils.buttonState(tv_next_two, R.drawable.rectangle_27dp_blue, false);
                    } else {

                        TimeCount timeCount = new TimeCount(3000, 1000);//60000, 1000
                        timeCount.setBtn(tv_register_get_code, "重新获取");
                        timeCount.start();
                    }

                    break;

                case R.id.iv_picture_code://动态验证码图片
                    iv_picture_code.setImageBitmap(ImageCodeView.getInstance().createBitmap());
                    break;

            }
        }
    }
}
