package com.mainiway.eworkpal.activity.test;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.mainiway.eworkpal.R;
import com.mainiway.eworkpal.base.BaseActivity;
import com.mainiway.eworkpal.base.BaseTitleActivity;
import com.mainiway.eworkpal.manager.ConfigManager;
import com.mainiway.eworkpal.utils.ToastUtils;

/**
 * 测试主页
 */

public class CActivity extends BaseTitleActivity {

    TextView name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test_cactivity);
        setTitle("通讯录单选多选");
        showBackwardView(true);
        showForwardView(R.mipmap.ic_back, R.mipmap.ic_back);
        name = findView(R.id.name);
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.btn_1:    //单选
                Intent intent = new Intent(this, CheckActivity2.class);
                intent.putExtra("t", name.getText());
                startActivityForResult(intent, 1);
                break;

            case R.id.btn_2:    //多选
                /*Intent intent2 = new Intent(this, CheckActivity.class);
                //learnIntent.putExtra(BaseActivity.EXTRA_LEARN, mLearning.getText());
                intent2.putExtra("v", name.getText());
                startActivityForResult(intent2, 2);*/
                break;

        }
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        //final UserConfigManager mUserConfigManager = UserConfigManager.getInstance();

        switch (requestCode) {
            case 1:
                if (resultCode == Activity.RESULT_OK) {
                    final String name = data.getExtras().getString("one");
                    ((TextView) findView(R.id.name)).setText(name);

                }
                break;
            case 2:
                if (resultCode == Activity.RESULT_OK) {
                    final String more = data.getExtras().getString("more");
                    ((TextView) findView(R.id.name)).setText(more);
                    //mUserConfigManager.setLearn(learn);

                }
                break;

            default:
                break;
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    protected void onForward(View forwardView) {
        super.onForward(forwardView);
        ToastUtils.showToastCenter("第一个图标");
    }

    @Override
    protected void onForwardRight(View forwardView) {
        super.onForwardRight(forwardView);
        ToastUtils.showToastCenter("第二个图标");
    }
}
