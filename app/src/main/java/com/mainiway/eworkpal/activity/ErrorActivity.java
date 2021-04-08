package com.mainiway.eworkpal.activity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;


import com.mainiway.alertview.AlertView;
import com.mainiway.alertview.ItemClick;
import com.mainiway.eworkpal.R;
import com.mainiway.eworkpal.base.BaseTitleActivity;
import com.mainiway.eworkpal.utils.CrashManager;
import com.mainiway.eworkpal.utils.ToastUtils;

import java.util.ArrayList;


@SuppressLint("NewApi")
public class ErrorActivity extends BaseTitleActivity {

    private String errorInformation = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.error_default__activity);
        setTitle("错误提示");


        errorInformation = CrashManager.getAllErrorDetailsFromIntent(ErrorActivity.this, getIntent());

        Button btn_check = (Button) findViewById(R.id.btn_check);//查看
        btn_check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(ErrorActivity.this)
                        .setTitle("错误详情")
                        .setCancelable(true)
                        .setMessage(errorInformation)
                        .setPositiveButton("返回", null)
                        .setNeutralButton("复制", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                copyErrorToClipboard();
                                ToastUtils.showToastCenter("已复制到剪切板");
                            }
                        })
                        .show();
            }
        });

        Button moreInfoButton = (Button) findViewById(R.id.customactivityoncrash_error_activity_more_info_button);//上传
        if (CrashManager.isShowErrorDetailsFromIntent(getIntent())) {
            moreInfoButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    uploadErrorMessage();//上传信息
                }
            });
        } else {
            moreInfoButton.setVisibility(View.GONE);
        }

        int defaultErrorActivityDrawableId = CrashManager.getDefaultErrorActivityDrawableIdFromIntent(getIntent());
        ImageView errorImageView = ((ImageView) findViewById(R.id.customactivityoncrash_error_activity_image));

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            errorImageView.setImageDrawable(getResources().getDrawable(defaultErrorActivityDrawableId, getTheme()));
        } else {
            errorImageView.setImageDrawable(getResources().getDrawable(defaultErrorActivityDrawableId));
        }
    }


    /**
     * 复制到剪切板
     */
    private void copyErrorToClipboard() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            ClipboardManager clipboard = (ClipboardManager) getSystemService(CLIPBOARD_SERVICE);
            ClipData clip = ClipData.newPlainText(getString(R.string.customactivityoncrash_error_activity_error_details_clipboard_label), errorInformation);
            clipboard.setPrimaryClip(clip);
        } else {
            android.text.ClipboardManager clipboard = (android.text.ClipboardManager) getSystemService(CLIPBOARD_SERVICE);
            clipboard.setText(errorInformation);
        }
    }


    /**
     * 上传错误信息
     */
    void uploadErrorMessage() {
        final ProgressDialog md = new ProgressDialog(this);
        md.setMessage("正在上传，请稍后...");
        md.setCancelable(false);
        md.show();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                md.dismiss();
                ToastUtils.showToastCenter("上传成功");
            }
        }, 1500);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {

            new AlertView("提示", "是否确认退出 ？", "退出", new String[]{"重新启动"}, null,
                    this, AlertView.Style.Alert, new ItemClick() {

                @Override
                public void onItemClick(Object o, int position) {

                    //position等于-1则退出，否则重新启动
                    if (position == -1) {
                        finish();
                    } else {
                        final Class<? extends Activity> restartActivityClass = CrashManager.getRestartActivityClassFromIntent(getIntent());
                        if (restartActivityClass != null) {
                            Intent intent = new Intent(ErrorActivity.this, restartActivityClass);
                            CrashManager.restartApplicationWithIntent(ErrorActivity.this, intent);
                        } else {
                            CrashManager.closeApplication(ErrorActivity.this);
                        }
                    }
                }
            }).setCancelable(true).show();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
