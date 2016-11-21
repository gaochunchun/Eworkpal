package com.mainiway.eworkpal.activity.attendance;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.mainiway.eworkpal.R;
import com.mainiway.eworkpal.adapter.ContactsAdapter;
import com.mainiway.eworkpal.base.BaseTitleActivity;
import com.mainiway.eworkpal.model.Contact;
import com.mainiway.eworkpal.widgets.SideBarView;

import java.util.ArrayList;

/**
 * ===========================================
 * 作    者：gao_chun
 * 版    本：1.0
 * 创建日期：2016-11-21.
 * 描    述：
 * ===========================================
 */
public class RightPositionActivity extends BaseTitleActivity {

    private RecyclerView rvContacts;
    private SideBarView sideBar;

    private ArrayList<Contact> contacts = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setTitle("联系人");
        initData();
        initView();
    }

    private void initView() {
        setContentView(R.layout.activity_contacts);
        rvContacts = (RecyclerView) findViewById(R.id.rv_contacts);
        rvContacts.setLayoutManager(new LinearLayoutManager(this));
        rvContacts.setAdapter(new ContactsAdapter(contacts, R.layout.item_contacts));
        sideBar = (SideBarView) findViewById(R.id.side_bar);
        sideBar.setOnSelectIndexItemListener(new SideBarView.OnSelectIndexItemListener() {
            @Override
            public void onSelectIndexItem(String index) {
                for (int i=0; i<contacts.size(); i++) {
                    if (contacts.get(i).getIndex().equals(index)) {
                        ((LinearLayoutManager) rvContacts.getLayoutManager()).scrollToPositionWithOffset(i, 0);
                        return;
                    }
                }
            }
        });
    }

    private void initData() {
        contacts.addAll(Contact.getEnglishContacts());
    }
}
