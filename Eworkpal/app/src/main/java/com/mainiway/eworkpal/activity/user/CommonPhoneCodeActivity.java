package com.mainiway.eworkpal.activity.user;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.mainiway.eworkpal.R;
import com.mainiway.eworkpal.base.BaseTitleActivity;
import com.mainiway.eworkpal.constant.Constants;
import com.mainiway.eworkpal.listener.OnClickFastListener;
import com.mainiway.eworkpal.utils.TimeCount;

import static com.mainiway.eworkpal.R.id.tv_register_get_code;

/**
 * ===========================================
 * 作    者：zhsh
 * 版    本：1.0
 * 创建日期：2016/11/18.
 * 描    述：公用的获取手机验证码（需根据传递的Intent信息设置Title）
 * ===========================================
 */

public class CommonPhoneCodeActivity extends BaseTitleActivity{

    private String label;
    private TextView tv_get_code;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_common_phonecode);
        handlerIntent();
        if (label.equals(Constants.PHONE_CODE_ENTERPRISE)){
            setTitle("注册");
        }else if (label.equals(Constants.PHONE_CODE_FORGET_PWD)){
            setTitle("找回密码");
        }
        showBackwardView(true);
        initView();

    }

    //获取Intent传递的数据
    private void handlerIntent(){

        if(getIntent() != null){
            label = getIntent().getStringExtra(Constants.LABEL_PHONE_CODE);
        }
    }

    private void initView() {
        findView(R.id.tv_getcode_next).setOnClickListener(new FastClickListener());
        tv_get_code = findView(R.id.tv_get_code);
        tv_get_code.setOnClickListener(new FastClickListener());

    }


    private class FastClickListener extends OnClickFastListener {

        @Override
        public void onFastClick(View v) {

            switch (v.getId()) {

                case R.id.tv_getcode_next:  //下一步

                    if (label.equals(Constants.PHONE_CODE_ENTERPRISE)){
                        //此处跳转到企业创建界面，可能需要携带参数
                        startActivity(new Intent(CommonPhoneCodeActivity.this,CreateEnterpriseActivity.class));

                    }else if (label.equals(Constants.PHONE_CODE_FORGET_PWD)){

                        //此处跳转到找回密码界面，可能需要携带参数
                        startActivity(new Intent(CommonPhoneCodeActivity.this,ForgetPwdActivity.class));

                    }

                    break;

                case R.id.tv_get_code://获取验证码
                    TimeCount timeCount = new TimeCount(60000, 1000);//60000, 1000
                    timeCount.setBtn(tv_get_code, "重新获取");
                    timeCount.start();
                    break;

            }
        }
    }
}
