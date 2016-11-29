package com.mainiway.eworkpal.activity.user;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.mainiway.eworkpal.R;
import com.mainiway.eworkpal.base.BaseTitleActivity;
import com.mainiway.eworkpal.listener.OnClickFastListener;
import com.mainiway.eworkpal.utils.ToastUtils;

/**
 * ===========================================
 * 作    者：gao_chun
 * 版    本：1.0
 * 创建日期：2016-11-19.
 * 描    述：找回密码
 * ===========================================
 */

public class ForgetPwdActivity extends BaseTitleActivity {

    private EditText et_password, et_confirm_password;
    private TextView tv_commit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_forget_pwd);
        setTitle("找回密码");
        showBackwardView(true);
        initView();
    }

    private void initView() {
        et_password = findView(R.id.et_password);
        et_password.addTextChangedListener(textWatcher);

        et_confirm_password = findView(R.id.et_confirm_password);
        et_confirm_password.addTextChangedListener(textWatcher);

        tv_commit = findView(R.id.tv_commit);
        tv_commit.setClickable(false);
        tv_commit.setOnClickListener(new FastClickListener());
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
            if (!TextUtils.isEmpty(et_password.getText()) && !TextUtils.isEmpty(et_confirm_password.getText())) {
                tv_commit.setBackgroundResource(R.drawable.rectangle_27dp_blue_selected);
                tv_commit.setClickable(true);
            } else {
                tv_commit.setBackgroundResource(R.drawable.rectangle_27dp_blue);
                tv_commit.setClickable(false);
            }

        }
    };


    private class FastClickListener extends OnClickFastListener {

        @Override
        public void onFastClick(View v) {

            switch (v.getId()) {
                case R.id.tv_commit://提交
                    if (et_password.getText().toString().equals(et_confirm_password.getText().toString())) {
                        startActivity(new Intent(ForgetPwdActivity.this, LoginActivity.class));
                        overridePendingTransition(R.anim.push_right_in, R.anim.push_right_out);
                    } else {
                        ToastUtils.showToastShort("两次输入的密码不一致");
                    }
                    break;
            }
        }
    }


}



