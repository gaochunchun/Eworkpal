package com.mainiway.eworkpal.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.mainiway.eworkpal.R;
import com.mainiway.eworkpal.base.BaseActivity;

import static com.mainiway.eworkpal.R.id.toolbar;

public class MainActivity extends BaseActivity {

    Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = (Toolbar)findView(R.id.toolbar);
        initToolBar(toolbar, false, "");
    }

    @Override
    protected boolean translucentStatusBar() {
        return true;
    }
}
