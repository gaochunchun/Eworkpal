package com.mainiway.eworkpal.activity.user;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemChildClickListener;
import com.mainiway.eworkpal.R;
import com.mainiway.eworkpal.activity.test.AnimationUseActivity;
import com.mainiway.eworkpal.adapter.EnterpriseListAdapter;
import com.mainiway.eworkpal.adapter.QuickAdapter;
import com.mainiway.eworkpal.base.BaseTitleActivity;
import com.mainiway.eworkpal.model.Status;
import com.mainiway.eworkpal.utils.ToastUtils;

/**
 * ===========================================
 * 作    者：gao_chun
 * 版    本：1.0
 * 创建日期：2016-11-29.
 * 描    述：选择企业列表界面
 * ===========================================
 */


public class EnterpriseListActivity extends BaseTitleActivity {

    private RecyclerView mRecyclerView;
    private EnterpriseListAdapter mEnterpriseListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_enterprise_list);

        setTitle(R.string.text_select_enterprise);
        showBackwardView(true);

        initView();
        initAdapter();
    }

    private void initView(){

        mRecyclerView = (RecyclerView) findViewById(R.id.rv_list);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

    }


    private void initAdapter() {

        mEnterpriseListAdapter = new EnterpriseListAdapter();
        mEnterpriseListAdapter.openLoadAnimation();
        mRecyclerView.addOnItemTouchListener(new OnItemChildClickListener() {
            @Override
            public void SimpleOnItemChildClick(BaseQuickAdapter adapter, View view, int position) {

                Status status = (Status) adapter.getItem(position);

                ToastUtils.showToastCenter(status.getUserName());
            }
        });
        mRecyclerView.setAdapter(mEnterpriseListAdapter);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

}
