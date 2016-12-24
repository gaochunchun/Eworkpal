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
 * 作    者：gao_chun
 * 版    本：1.0
 * 创建日期：2016-11-19.
 * 描    述：找回密码
 * ===========================================
 */

public class UserForgetPwdActivity extends BaseTitleActivity {

    private EditText et_password, et_confirm_password;
    private TextView tv_commit;
    private String mobile;//往服务器传的手机号码

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_forget_pwd);
        setTitle(R.string.retrieve_password);
        showBackwardView(true);
        initView();
        initData();
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

    private void initData() {
        if (getIntent() != null) {
            mobile = getIntent().getStringExtra("mobile");
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
            if (!TextUtils.isEmpty(et_password.getText()) && !TextUtils.isEmpty(et_confirm_password.getText())) {
                DealViewUtils.buttonState(tv_commit, R.drawable.rectangle_27dp_blue_selected, true);
            } else {
                DealViewUtils.buttonState(tv_commit, R.drawable.rectangle_27dp_blue, false);
            }

        }
    };


    private class FastClickListener extends OnClickFastListener {

        @Override
        public void onFastClick(View v) {

            switch (v.getId()) {
                case R.id.tv_commit://提交
                    if (et_password.getText().toString().equals(et_confirm_password.getText().toString())) {
                        retrievePassword();
                    } else {
                        ToastUtils.showToastShort(getString(R.string.the_two_password_is_not_uniform_please_re_enter));
                    }
                    break;
            }
        }
    }


    /**
     * 找回密码（密码重置）
     */
    private void retrievePassword() {
        Map<String, Object> mapList = new HashMap<String, Object>();
        mapList.put("phone", mobile);
        mapList.put("password", et_password.getText().toString());
        String str = GsonConvertUtil.toJson(mapList);

        UserRequestManager.getInstance().retrievePassword(this, str, new DialogCallback<BaseResponse<String>>(this) {
            @Override
            public void onSuccess(BaseResponse<String> responseData, Call call, Response response) {
                if (responseData.successed) {
                    ToastUtils.showToastShort(responseData.message.get(0).msg);
                    setResult(RESULT_OK);
                    startActivity(new Intent(UserForgetPwdActivity.this, UserLoginActivity.class));
                    overridePendingTransition(R.anim.push_right_in, R.anim.push_right_out);
                    finish();
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



