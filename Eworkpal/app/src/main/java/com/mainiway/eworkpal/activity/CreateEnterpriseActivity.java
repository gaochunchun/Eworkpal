package com.mainiway.eworkpal.activity;

import android.os.Bundle;

import com.mainiway.eworkpal.R;
import com.mainiway.eworkpal.base.BaseTitleActivity;

/**
 * ===========================================
 * 作    者：zhsh
 * 版    本：1.0
 * 创建日期：2016/11/18.
 * 描    述：创建企业
 * ===========================================
 */

public class CreateEnterpriseActivity extends BaseTitleActivity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_create_enterprise);
        setTitle("注册");
        showBackwardView(true);
    }
}
