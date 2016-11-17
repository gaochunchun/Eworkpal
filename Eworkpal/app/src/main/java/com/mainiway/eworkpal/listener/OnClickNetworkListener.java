package com.mainiway.eworkpal.listener;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.view.View;

import com.mainiway.eworkpal.base.BaseClickListener;


/**
 * @author gao_chun
 *
 * 描述：击的View点时候判断网络
 */
public abstract class OnClickNetworkListener extends BaseClickListener {

    @Override
    public void onClick(View v) {
        boolean isNetworkOk = isNetworkConnected(v.getContext());

        if (isNetworkOk) {
            onNetworkClick(v);
        } else {
            onNoNetworkClick(v);
        }
    }

    // 点击事件--有网络
    public abstract void onNetworkClick(View v);

    // 点击事件--没有网络
    public abstract void onNoNetworkClick(View v);
    /**
     * 网络连接是否正常
     *
     * @param context
     * @return
     */
    public static boolean isNetworkConnected(Context context) {
        if (context != null) {
            ConnectivityManager mConnectivityManager = (ConnectivityManager) context
                    .getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo mNetworkInfo = mConnectivityManager.getActiveNetworkInfo();
            if (mNetworkInfo != null) {
                return mNetworkInfo.isAvailable();
            }
        }
        return false;
    }
}
