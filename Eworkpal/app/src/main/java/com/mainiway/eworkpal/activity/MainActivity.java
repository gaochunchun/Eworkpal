package com.mainiway.eworkpal.activity;

import android.os.Bundle;

import com.mainiway.eworkpal.R;
import com.mainiway.eworkpal.base.BaseActivity;


public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);
        setContentView(R.layout.activity_main_layout);
        //TextView textView = findView(R.id.appbar);
    }

}
