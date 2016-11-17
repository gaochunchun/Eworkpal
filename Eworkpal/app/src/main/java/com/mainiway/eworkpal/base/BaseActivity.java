package com.mainiway.eworkpal.base;

import android.app.Activity;
import android.os.Bundle;

public  class BaseActivity extends Activity {

    //private SystemBarTintManager tintManager;
    //public static SVProgressHUD mSVProgressHUD;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //mSVProgressHUD = new SVProgressHUD(this);
        //initWindow();
    }


    /*@TargetApi(19)
    private void initWindow(){

        //Build.VERSION.SDK_INT  判断当前版本号是否大于19
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT){
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);

            tintManager = new SystemBarTintManager(this);
            //tintManager.setTintColor(Color.parseColor("#FF9900"));	//仅设置任务栏颜色
            tintManager.setStatusBarTintColor(getResources().getColor(R.color.blue));
            //tintManager.setStatusBarTintColor(Color.parseColor("#3272DE"));
            tintManager.setStatusBarTintEnabled(true);
            //tintManager.setNavigationBarTintEnabled(true);
        }
    }*/



    @Override
    protected void onDestroy()
    {
        super.onDestroy();
        //OkHttpUtils.getInstance().cancelTag(AppAplication.getContext());
    }
}
