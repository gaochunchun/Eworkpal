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
import com.mainiway.eworkpal.base.BaseResponse;
import com.mainiway.eworkpal.base.BaseTitleActivity;
import com.mainiway.eworkpal.callback.DialogCallback;
import com.mainiway.eworkpal.listener.OnClickFastListener;
import com.mainiway.eworkpal.request.UserRequestManager;
import com.mainiway.eworkpal.utils.DealViewUtils;
import com.mainiway.eworkpal.utils.GsonConvertUtil;
import com.mainiway.eworkpal.utils.ToastUtils;
import com.mainiway.okhttp.utils.OkLogger;

import java.util.HashMap;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Response;

/**
 * ===========================================
 * 作    者：zhsh
 * 版    本：1.0
 * 创建日期：2016/11/18.
 * 描    述：创建企业
 * ===========================================
 */

public class UserCreateEnterpriseActivity extends BaseTitleActivity {

    private TextView tv_create_enterprise;
    private EditText et_enterprise_name, et_name, et_password, et_confirm_password;

    private String mobile;//往服务器传的手机号码

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_create_enterprise);
        setTitle(R.string.register);
        showBackwardView(true);
        initView();
        initData();
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


    private void initData() {
        if (getIntent() != null) {
            mobile = getIntent().getStringExtra("mobile");
        }
    }

    private class FastClickListener extends OnClickFastListener {

        @Override
        public void onFastClick(View v) {
            switch (v.getId()) {
                case R.id.tv_create_enterprise://创建企业
                    if (!et_password.getText().toString().equals(et_confirm_password.getText().toString())) {
                        ToastUtils.showToastShort(getString(R.string.the_two_password_is_not_uniform_please_re_enter));
                        break;
                    } else {
                        createEnterprise();
                    }
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

    /**
     * 创建企业网络请求
     */
    private void createEnterprise() {
        Map<String, Object> mapList = new HashMap<String, Object>();
        mapList.put("mobile", mobile);
        mapList.put("companyName", et_enterprise_name.getText().toString());
        mapList.put("name", et_name.getText().toString());
        mapList.put("password", et_password.getText().toString());

        String str = GsonConvertUtil.toJson(mapList);

        UserRequestManager.getInstance().createEnterprise(this, str, new DialogCallback<BaseResponse<String>>(UserCreateEnterpriseActivity.this) {
            @Override
            public void onSuccess(BaseResponse<String> responseData, Call call, Response response) {
                if (responseData.successed) {
                    ToastUtils.showToastShort(responseData.message.get(0).msg);
                    startActivity(new Intent(UserCreateEnterpriseActivity.this, UserLoginActivity.class));
                    finish();
                } else {
                    ToastUtils.showToastShort(responseData.message.get(0).msg);
                }
            }

            @Override
            public void onError(Call call, Response response, Exception e) {
                super.onError(call, response, e);
                OkLogger.e(e.toString());
                ToastUtils.showToastShort(e.toString());
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
