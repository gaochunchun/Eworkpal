package com.mainiway.eworkpal.widgets;

import android.os.CountDownTimer;
import android.widget.TextView;


/**
 * ===========================================
 * 作    者：zhsh
 * 版    本：1.0
 * 创建日期：2016/11/18.
 * 描    述：计时器
 * ===========================================
 */

public class TimeCount extends CountDownTimer {

    private TextView btn;
    private String finishText;

    public void setBtn(TextView btn, String finishText) {
        this.btn = btn;
        this.finishText = finishText;
        this.start();
    }

    public TimeCount(long millisInFuture, long countDownInterval) {
        super(millisInFuture, countDownInterval);
    }

    @Override
    public void onTick(long millisUntilFinished) {
        btn.setClickable(false);
        btn.setText(millisUntilFinished / 1000 + "s");

    }

    @Override
    public void onFinish() {
        btn.setText(finishText);
        btn.setClickable(true);
    }
}
