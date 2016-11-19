/*******************************************************************************
 *
 * Copyright (c) Weaver Info Tech Co. Ltd
 *
 * MainActivity
 *
 * app.ui.MainActivity.java
 * TODO: File description or class description.
 *
 * @author: Administrator
 * @since:  2014-4-13
 * @version: 1.0.0
 *
 * @changeLogs:
 *     1.0.0: First created this class.
 *
 ******************************************************************************/
package com.mainiway.eworkpal.activity;

import android.os.Bundle;

import com.mainiway.eworkpal.base.BaseActivity;

/**
 * @author gao_chun
 * 描述：判断程序是否为第一次启动
 */
public class IndexActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //根据版本号来判断是否进入引导页面
       /* int versionCode = ValidateUtils.getVersionCode(getApplicationContext());

        int code = (int)ConfigManager.getInstance().get(Constants.PREF_NAME_VERSIONCODE,0);

        if (code < versionCode) {
            // 第一次进入程序，进入引导页，否则直接进入欢迎界面
            ConfigManager.getInstance().put(Constants.PREF_NAME_VERSIONCODE,versionCode);
            startActivity(new Intent(this, GuideActivity.class));
        } else {
            startActivity(new Intent(this, SplashActivity.class));
        }
        finish();*/

    }
}
