package com.mainiway.library.adapter.base.listener;

import android.view.View;

import com.mainiway.library.adapter.base.BaseQuickAdapter;


public abstract class OnItemChildClickListener extends SimpleClickListener {

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {

    }

    @Override
    public void onItemLongClick(BaseQuickAdapter adapter, View view, int position) {

    }

    @Override
    public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
        onSimpleItemChildClick(adapter, view, position);
    }

    @Override
    public void onItemChildLongClick(BaseQuickAdapter adapter, View view, int position) {

    }

    public  abstract void onSimpleItemChildClick(BaseQuickAdapter adapter, View view, int position);

}
