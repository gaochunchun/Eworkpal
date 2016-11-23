package com.mainiway.eworkpal.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;

import com.mainiway.eworkpal.R;
import com.mainiway.eworkpal.activity.user.LoginActivity;
import com.mainiway.eworkpal.base.BaseActivity;

/**
 * 欢迎页面
 * @author gao_chun
 *
 */
public class SplashActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        final View view = View.inflate(this, R.layout.activity_splash,null);
        setContentView(view);
        AlphaAnimation mAa = new AlphaAnimation(0.2f, 1.0f);
        mAa.setDuration(1900);
        view.startAnimation(mAa);
        mAa.setAnimationListener(new AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                // TODO Auto-generated method stub
            }
            @Override
            public void onAnimationRepeat(Animation animation) {
                // TODO Auto-generated method stub
            }
            @Override
            public void onAnimationEnd(Animation animation) {
                redirectTo();
            }
        });

    }

    private void redirectTo() {

        //描述：判断第一次进入时是否存在UserId，无则跳转到登录界面，存在则直接进入主页。
        //此处不考虑Cookie是否有效，用户在主页发起请求时若Cookie无效则根据服务器返回的标示跳转至重新登录
        //String mUserId = (String) ConfigManager.getInstance().get(Constants.PREF_NAME_USERID,"");

        Intent mIntent = new Intent();
        //if(!TextUtils.isEmpty(mUserId)){
         //mIntent.setClass(this, MainActivity.class);
        // }else{
        mIntent.setClass(this, LoginActivity.class);
        //mIntent.setClass(this, TestActivity.class);
        //mIntent.setClass(this, RightPositionActivity.class);
        // }
        startActivity(mIntent);
        finish();
    }

}
