package com.mainiway.eworkpal.activity.test;

import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.mainiway.eworkpal.R;
import com.mainiway.library.adapter.base.BaseQuickAdapter;
import com.mainiway.library.adapter.base.BaseViewHolder;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static android.R.attr.label;

public class ContactAdapter2 extends BaseQuickAdapter<Contact, BaseViewHolder> {

    public boolean label = true;    //该标记用于处理选中后再次进入时选中状态

    private List<Contact> contacts;

    private List<Contact> mTempList = null;

    //CheckBox 是否选择的存储集合,key 是 position , value 是该position是否选中
    private Map<Integer, Boolean> isCheckMap = new HashMap<Integer, Boolean>();

    /*public ContactAdapter2(List<Contact> contacts) {
        super(R.layout.test_item_contacts, contacts);
        this.contacts = contacts;
    }*/

    public ContactAdapter2(List<Contact> contacts, List<Contact> mTempList) {
        super(R.layout.test_item_contacts, contacts);
        this.contacts = contacts;
        this.mTempList = mTempList;
        // 初始化,默认都没有选中
        //configCheckMap(false);
    }


    @Override
    protected void convert(BaseViewHolder helper, Contact contact) {

        final int position = helper.getAdapterPosition();
        if (position == 0 || !contacts.get(position - 1).getIndex().equals(contact.getIndex())) {
            helper.setVisible(R.id.tv_index, true);
            helper.setText(R.id.tv_index, contact.getIndex());
        } else {
            helper.setVisible(R.id.tv_index, false);
        }

        //设置每一个item的文本
        TextView tvTitle = helper.getView(R.id.tv_name);
        tvTitle.setText(contact.getName());

        //获得该item 是否允许删除
        //boolean canRemove = bean.isCanRemove();

        CheckBox cbCheck = helper.getView(R.id.cbCheckBox);

        /*
         * 设置单选按钮的选中
         */
        cbCheck.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                //将选择项加载到map里面寄存
                isCheckMap.put(position, isChecked);
            }
        });


        /*if (!canRemove) {
            // 隐藏单选按钮,因为是不可删除的
            cbCheck.setVisibility(View.GONE);
            cbCheck.setChecked(false);
        } else {
            cbCheck.setVisibility(View.VISIBLE);*/

        if (isCheckMap.get(position) == null) {
            isCheckMap.put(position, false);
        }

        //保存Tag
        ViewHolder holder = new ViewHolder();
        holder.cbCheck = cbCheck;
        holder.tvTitle = tvTitle;
        helper.getConvertView().setTag(holder);

        if (label){
            if (mTempList.size() > 0) {
                for (int i = 0; i < mTempList.size(); i++) {
                    if (contact.getName().contains(mTempList.get(i).getName())) {
                        isCheckMap.put(position, true);
                    }
                }
            }
        }
        cbCheck.setChecked(isCheckMap.get(position));

    }


    /**
     * 首先,默认情况下,所有项目都是没有选中的.这里进行初始化
     */
    public void configCheckMap(boolean bool) {
        for (int i = 0; i < contacts.size(); i++) {
            isCheckMap.put(i, bool);
        }
    }

    // 移除一个项目的时候
    public void remove(int position) {
        this.contacts.remove(position);
    }

    //点击item选中CheckBox
/*    public void setSelectItem(int position) {
        //对当前状态取反
        if (isCheckMap.get(position)) {
            isCheckMap.put(position, false);
        } else {
            isCheckMap.put(position, true);
        }
        notifyItemChanged(position);
    }*/

    public Map<Integer, Boolean> getCheckMap() {
        return this.isCheckMap;
    }

    public static class ViewHolder {
        public TextView tvTitle = null;
        public CheckBox cbCheck = null;
    }

    /*public List<DemoBean> getDatas() {
        return datas;
    }*/

}
