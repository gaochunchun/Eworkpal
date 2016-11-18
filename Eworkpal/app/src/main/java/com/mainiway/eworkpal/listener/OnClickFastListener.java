package com.mainiway.eworkpal.listener;

import android.view.View;

import com.mainiway.eworkpal.base.BaseClickListener;
import com.mainiway.eworkpal.utils.ValidateUtils;

/**
 * @author gao_chun
 *
 * 描述：View点击的时候判断屏蔽快速点击事件
 */
public abstract class OnClickFastListener extends BaseClickListener {

    // 防止快速点击默认等待时长为1s
    private long DELAY_TIME = 1000;
    private static long lastClickTime;

    private boolean isFastDoubleClick() {
        long time = System.currentTimeMillis();
        long timeD = time - lastClickTime;
        if (0 < timeD && timeD < DELAY_TIME) {
            return true;
        }
        lastClickTime = time;
        return false;
    }

    @Override
    public void onClick(View v) {
        // 判断当前点击事件与前一次点击事件时间间隔是否小于阙值
        if (isFastDoubleClick()) {
            return;
        }

        boolean isNetworkOk = ValidateUtils.isNetworkConnected(v.getContext());
        if (!isNetworkOk) {
            return;
        }

        onFastClick(v);
    }

    /**
     * 设置默认快速点击事件时间间隔
     * @param delay_time
     * @return
     */
    public OnClickFastListener setLastClickTime(long delay_time) {
        this.DELAY_TIME = delay_time;
        return this;
    }

    /**
     * 快速点击事件回调方法
     * @param v
     */
    public abstract void onFastClick(View v);
}
