package com.mainiway.eworkpal.activity.user;

import android.os.Bundle;
import android.view.View;

import com.mainiway.eworkpal.R;
import com.mainiway.eworkpal.base.BaseTitleActivity;
import com.mainiway.eworkpal.listener.OnClickFastListener;

/**
 * ===========================================
 * 作    者：gao_chun
 * 版    本：1.0
 * 创建日期：2016-11-19.
 * 描    述：申请加入企业
 * ===========================================
 */

public class JoinEnterpriseActivity extends BaseTitleActivity {

    private View ll_join_enterprise_id;
    private View ll_join_enterprise;
    private View ll_join_enterprise_verify;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_join_enterprise);
        setTitle("申请加入企业");
        showBackwardView(true);
        initView();
    }

    private void initView (){
        //输入企业ID布局
        ll_join_enterprise_id = (View)findView(R.id.ll_join_enterprise_id);
        findView(R.id.tv_next_one).setOnClickListener(new FastClickListener());


        //填写申请信息布局
        ll_join_enterprise = (View)findView(R.id.ll_join_enterprise);
        findView(R.id.tv_next_two).setOnClickListener(new FastClickListener());

        //等待审批布局
        ll_join_enterprise_verify = (View)findView(R.id.ll_join_enterprise_verify);
        findView(R.id.tv_next_three).setOnClickListener(new FastClickListener());
    }


    private class FastClickListener extends OnClickFastListener {

        @Override
        public void onFastClick(View v) {

            switch (v.getId()) {

                case R.id.tv_next_one://下一步
                    ll_join_enterprise_id.setVisibility(View.GONE);
                    ll_join_enterprise.setVisibility(View.VISIBLE);

                    break;

                case R.id.tv_next_two://下一步
                    ll_join_enterprise.setVisibility(View.GONE);
                    ll_join_enterprise_verify.setVisibility(View.VISIBLE);

                    break;

                case R.id.tv_next_three://返回
                    finish();
                    break;

            }
        }
    }
}
