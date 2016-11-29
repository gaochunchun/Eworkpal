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
import com.mainiway.eworkpal.utils.DealViewUtils;

/**
 * ===========================================
 * 作    者：zhsh
 * 版    本：1.0
 * 创建日期：2016/11/18.
 * 描    述：创建企业
 * ===========================================
 */

public class CreateEnterpriseActivity extends BaseTitleActivity {

    private TextView tv_create_enterprise;
    private EditText et_enterprise_name, et_name, et_password, et_confirm_password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_create_enterprise);
        setTitle("注册");
        showBackwardView(true);
        initView();
    }

    private void initView() {
        tv_create_enterprise = findView(R.id.tv_create_enterprise);
        tv_create_enterprise.setOnClickListener(new FastClickListener());
        tv_create_enterprise.setClickable(false);

        et_enterprise_name = findView(R.id.et_enterprise_name);
        et_enterprise_name.addTextChangedListener(textWatcher);

        et_name = findView(R.id.et_name);
        et_name.addTextChangedListener(textWatcher);

        et_password = findView(R.id.et_password);
        et_password.addTextChangedListener(textWatcher);

        et_confirm_password = findView(R.id.et_confirm_password);
        et_confirm_password.addTextChangedListener(textWatcher);

    }

    private class FastClickListener extends OnClickFastListener {

        @Override
        public void onFastClick(View v) {
            switch (v.getId()) {
                case R.id.tv_create_enterprise://创建企业
                    startActivity(new Intent(CreateEnterpriseActivity.this, LoginActivity.class));
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

            if (!TextUtils.isEmpty(et_enterprise_name.getText()) && !TextUtils.isEmpty(et_name.getText()) && !TextUtils.isEmpty(et_password.getText()) && !TextUtils.isEmpty(et_confirm_password.getText())) {

                DealViewUtils.buttonState(tv_create_enterprise, R.drawable.rectangle_27dp_blue_selected, true);

            }
        }
    };


}
