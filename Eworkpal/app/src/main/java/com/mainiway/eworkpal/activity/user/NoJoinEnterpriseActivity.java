package com.mainiway.eworkpal.activity.user;

import android.os.Bundle;

import com.mainiway.eworkpal.R;
import com.mainiway.eworkpal.base.BaseTitleActivity;

/**
 * ===========================================
 * 作    者：gao_chun
 * 版    本：1.0
 * 创建日期：2016-11-29.
 * 描    述：未加入任何企业
 * ===========================================
 */


public class NoJoinEnterpriseActivity extends BaseTitleActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_no_enterprise);
        setTitle(R.string.text_prompt);
        showBackwardView(true);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
