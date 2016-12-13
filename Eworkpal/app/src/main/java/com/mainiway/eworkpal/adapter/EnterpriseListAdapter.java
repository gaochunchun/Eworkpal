package com.mainiway.eworkpal.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.mainiway.eworkpal.R;
import com.mainiway.eworkpal.activity.test.DataServer;
import com.mainiway.eworkpal.model.Status;

/**
 * ===========================================
 * 作    者：gao_chun
 * 版    本：1.0
 * 创建日期：2016-11-29.
 * 描    述：选择企业列表Adapter
 * ===========================================
 */
public class EnterpriseListAdapter extends BaseQuickAdapter<Status, BaseViewHolder> {

    public EnterpriseListAdapter() {
        super(R.layout.item_enterprise_list, DataServer.getSampleData(8));
    }

    @Override
    protected void convert(BaseViewHolder helper, Status item) {

        helper.setText(R.id.tv_company, item.getUserName());

    }


}
