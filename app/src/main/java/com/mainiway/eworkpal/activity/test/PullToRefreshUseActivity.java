package com.mainiway.eworkpal.activity.test;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.mainiway.eworkpal.R;
import com.mainiway.eworkpal.utils.ToastUtils;
import com.mainiway.library.adapter.base.BaseQuickAdapter;


/**
 * ===========================================
 * 作    者：gao_chun
 * 版    本：1.0
 * 创建日期：2016-11-21.
 * 描    述：下拉刷新
 * ===========================================
 */
public class PullToRefreshUseActivity extends Activity implements BaseQuickAdapter.RequestLoadMoreListener, SwipeRefreshLayout.OnRefreshListener {

    private RecyclerView mRecyclerView;
    private QuickAdapter mQuickAdapter;
    private SwipeRefreshLayout mSwipeRefreshLayout;

    private static final int TOTAL_COUNTER = 18;

    private static final int PAGE_SIZE = 6;

    private int delayMillis = 1000;

    private int mCurrentCounter = 0;

    private boolean isErr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test_activity_refresh);
        mRecyclerView = (RecyclerView) findViewById(R.id.rv_list);
        mSwipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipeLayout);
        mSwipeRefreshLayout.setOnRefreshListener(this);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        initAdapter();
        addHeadView();
    }

    private void addHeadView() {
        View headView = getLayoutInflater().inflate(R.layout.test_head_view, (ViewGroup) mRecyclerView.getParent(), false);
        ((TextView) headView.findViewById(R.id.tv)).setText("click use custom loading view");
        headView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mQuickAdapter.setLoadMoreView(new CustomLoadMoreView());
                mRecyclerView.setAdapter(mQuickAdapter);
                Toast.makeText(PullToRefreshUseActivity.this, "use ok!", Toast.LENGTH_LONG).show();
            }
        });
        mQuickAdapter.addHeaderView(headView);
    }

    @Override
    public void onLoadMoreRequested() {
        mSwipeRefreshLayout.setEnabled(false);
        mRecyclerView.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (mCurrentCounter >= TOTAL_COUNTER) {
                    mQuickAdapter.loadMoreEnd();
                } else {
                    if (isErr) {
                        mQuickAdapter.addData(DataServer.getSampleData(PAGE_SIZE));
                        mCurrentCounter = mQuickAdapter.getData().size();
                        mQuickAdapter.loadMoreComplete();
                    } else {
                        isErr = true;
                        Toast.makeText(PullToRefreshUseActivity.this, "Simulation network error", Toast.LENGTH_LONG).show();
                        mQuickAdapter.loadMoreFail();

                    }
                }
                mSwipeRefreshLayout.setEnabled(true);
            }

        },delayMillis);
    }

    @Override
    public void onRefresh() {
        mQuickAdapter.setEnableLoadMore(false);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                mQuickAdapter.setNewData(DataServer.getSampleData(PAGE_SIZE));
                mCurrentCounter = PAGE_SIZE;
                mSwipeRefreshLayout.setRefreshing(false);
                isErr = false;
                mQuickAdapter.setEnableLoadMore(true);
            }
        }, delayMillis);
    }

    private void initAdapter() {
        mQuickAdapter = new QuickAdapter(PAGE_SIZE);
        mQuickAdapter.setOnLoadMoreListener(this);
        mQuickAdapter.openLoadAnimation(BaseQuickAdapter.SLIDEIN_LEFT);
//        mQuickAdapter.setAutoLoadMoreSize(3);
        mRecyclerView.setAdapter(mQuickAdapter);
        mCurrentCounter = mQuickAdapter.getData().size();

        mQuickAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                ToastUtils.showToastCenter("onItemClick" + position);
                //Toast.makeText(PullToRefreshUseActivity.this, "onItemClick" + position, Toast.LENGTH_SHORT).show();
            }
        });


        /*mRecyclerView.addOnItemTouchListener(new OnItemClickListener(){

            @Override
            public void onSimpleItemClick(BaseQuickAdapter adapter, View view, int position) {
                Toast.makeText(PullToRefreshUseActivity.this, "" + Integer.toString(position), Toast.LENGTH_SHORT).show();

            }
        });*/



        //名字修改
        /*mRecyclerView.addOnItemTouchListener(new OnItemClickListener() {
            @Override
            public void SimpleOnItemClick(BaseQuickAdapter adapter, View view, int position) {
                Toast.makeText(PullToRefreshUseActivity.this, Integer.toString(position), Toast.LENGTH_LONG).show();
            }
        });*/


    }


}
