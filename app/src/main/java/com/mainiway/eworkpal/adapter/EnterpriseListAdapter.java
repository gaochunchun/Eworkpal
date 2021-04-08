package com.mainiway.eworkpal.adapter;

import com.mainiway.eworkpal.model.UserLoginToModle;
import com.mainiway.library.adapter.base.BaseQuickAdapter;
import com.mainiway.library.adapter.base.BaseViewHolder;
import com.mainiway.eworkpal.R;

import java.util.List;

/**
 * ===========================================
 * 作    者：gao_chun
 * 版    本：1.0
 * 创建日期：2016-11-29.
 * 描    述：选择企业列表Adapter
 * ===========================================
 */
public class EnterpriseListAdapter extends BaseQuickAdapter<UserLoginToModle, BaseViewHolder> {

    public EnterpriseListAdapter(List<UserLoginToModle> list) {
        super(R.layout.item_enterprise_list, list);
    }

    @Override
    protected void convert(BaseViewHolder helper, UserLoginToModle item) {

        helper.setText(R.id.tv_company, item.name);

    }


}
