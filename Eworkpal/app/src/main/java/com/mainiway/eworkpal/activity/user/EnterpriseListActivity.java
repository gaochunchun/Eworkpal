package com.mainiway.eworkpal.activity.user;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.mainiway.eworkpal.R;
import com.mainiway.eworkpal.adapter.EnterpriseListAdapter;
import com.mainiway.eworkpal.base.BaseResponse;
import com.mainiway.eworkpal.base.BaseTitleActivity;
import com.mainiway.eworkpal.callback.DialogCallback;
import com.mainiway.eworkpal.model.UserLoginModle;
import com.mainiway.eworkpal.model.UserLoginToModle;
import com.mainiway.eworkpal.request.UserRequestManager;
import com.mainiway.eworkpal.utils.GsonConvertUtil;
import com.mainiway.eworkpal.utils.ToastUtils;
import com.mainiway.library.adapter.base.BaseQuickAdapter;
import com.mainiway.library.adapter.base.listener.OnItemClickListener;
import com.mainiway.okhttp.utils.OkLogger;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Response;


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
    private List<UserLoginToModle> infoList;//登录界面传过来的个人信息集合（公司Id,公司名）

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_enterprise_list);

        setTitle(R.string.text_select_enterprise);
        showBackwardView(true);

        initView();
        initData();
        initAdapter();
    }

    private void initView() {

        mRecyclerView = (RecyclerView) findViewById(R.id.rv_list);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

    }

    private void initData() {
        if (getIntent() != null) {
            infoList = (List<UserLoginToModle>) getIntent().getSerializableExtra("infoList");
        }
    }

    private void initAdapter() {

        mEnterpriseListAdapter = new EnterpriseListAdapter(infoList);
        mEnterpriseListAdapter.openLoadAnimation();
        mRecyclerView.addOnItemTouchListener(new OnItemClickListener() {
            @Override
            public void SimpleOnItemClick(BaseQuickAdapter adapter, View view, int position) {

                UserLoginToModle userLoginModle = (UserLoginToModle) adapter.getItem(position);
                login(userLoginModle.companyID);
            }

        });
        mRecyclerView.setAdapter(mEnterpriseListAdapter);
    }

    /**
     * 登录,选择某个企业
     */
    private void login(String companyID) {

        Map<String, Object> mapList = new HashMap<String, Object>();
        mapList.put("companyID", companyID);
        String str = GsonConvertUtil.toJson(mapList);

        UserRequestManager.getInstance().login(this, str, new DialogCallback<BaseResponse<UserLoginModle>>(this) {
            @Override
            public void onSuccess(BaseResponse<UserLoginModle> baseResponse, Call call, Response response) {
                if (baseResponse.successed) {
                    startActivity(new Intent(EnterpriseListActivity.this, MainActivity.class));
                    finish();
                } else {
                    ToastUtils.showToastShort(baseResponse.message.get(0).msg);
                }

            }

            @Override
            public void onError(Call call, Response response, Exception e) {
                super.onError(call, response, e);
                OkLogger.e(e.toString());
                ToastUtils.showToastShort(e.toString());
            }
        });
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

}
