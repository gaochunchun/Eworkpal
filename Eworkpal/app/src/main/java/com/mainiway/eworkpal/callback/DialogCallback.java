package com.mainiway.eworkpal.callback;

import android.app.Activity;
import android.support.annotation.Nullable;

import com.mainiway.okhttp.request.BaseRequest;
import com.mainiway.okhttp.utils.OkLogger;
import com.mainiway.svprogresshud.SVProgressHUD;


/**
 * 描  述：对于网络请求是否需要弹出Dialog 。
 *
 * 注：当界面中存在两个请求同时调用 或 第一个请求先，
 * 第二个请求后 时，可以只显示第一个请求Dialog，第二个不显示
 */
public abstract class DialogCallback<T> extends JsonCallback<T> {

    //private ProgressDialog dialog;
    private SVProgressHUD dialog;

    private void initDialog(Activity activity) {

        /*dialog = new ProgressDialog(activity);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCanceledOnTouchOutside(false);
        dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        dialog.setMessage("请求中...");*/

        if (dialog == null) {
            dialog = new SVProgressHUD(activity);
        }
    }

    public DialogCallback(Activity activity) {
        super();
        //初始化Dialog
        if (activity != null) {
            initDialog(activity);
        }

    }

    public DialogCallback() {
    }

    @Override
    public void onBefore(BaseRequest request) {
        super.onBefore(request);
        //网络请求前显示对话框
        if (dialog != null && !dialog.isShowing()) {
            //dialog.show();
            dialog.showWithStatus("加载中...");
        }
    }

    @Override
    public void onAfter(@Nullable T t, @Nullable Exception e) {
        super.onAfter(t, e);
        //网络请求结束后关闭对话框
        if (dialog != null && dialog.isShowing()) {
            dialog.dismiss();
        }
        dialog = null;
    }
}
