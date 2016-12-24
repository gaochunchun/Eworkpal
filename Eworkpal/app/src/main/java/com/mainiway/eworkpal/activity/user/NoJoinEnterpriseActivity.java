package com.mainiway.eworkpal.activity.user;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.mainiway.eworkpal.R;
import com.mainiway.eworkpal.base.BaseTitleActivity;
import com.mainiway.eworkpal.listener.OnClickFastListener;

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
        initView();
    }

    private void initView() {
        findView(R.id.tv_join_enterprise).setOnClickListener(new FastClickListener());
        findView(R.id.tv_create_enterprise).setOnClickListener(new FastClickListener());
    }


    private class FastClickListener extends OnClickFastListener {

        @Override
        public void onFastClick(View v) {

            switch (v.getId()) {
                case R.id.tv_join_enterprise://申请加入企业
                    startActivity(new Intent(NoJoinEnterpriseActivity.this, JoinEnterpriseActivity.class));
                    break;
                case R.id.tv_create_enterprise://创建企业
                    startActivity(new Intent(NoJoinEnterpriseActivity.this, CreateEnterpriseActivity.class));
                    break;
            }
        }
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
