package com.mainiway.eworkpal.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.mainiway.eworkpal.R;
import com.mainiway.eworkpal.activity.test.DataServer;
import com.mainiway.eworkpal.model.Status;

public class EnterpriseListAdapter extends BaseQuickAdapter<Status, BaseViewHolder> {

    public EnterpriseListAdapter() {
        super(R.layout.item_enterprise_list, DataServer.getSampleData(8));
    }

    @Override
    protected void convert(BaseViewHolder helper, Status item) {

        helper.setText(R.id.tv_company, item.getUserName());

    }


}
