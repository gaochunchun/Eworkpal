package com.ccn.SmartPDA.callback;

import com.ccn.SmartPDA.request.base.Request;
import com.ccn.SmartPDA.model.Progress;
import com.ccn.SmartPDA.model.Response;
import com.ccn.SmartPDA.utils.OkLogger;

/**
 * ================================================
 * 描   述：抽象的回调接口
 * 修订历史：
 * ================================================
 */
public abstract class AbsCallback<T> implements Callback<T> {

    @Override
    public void onStart(Request<T, ? extends Request> request) {
    }

    @Override
    public void onCacheSuccess(Response<T> response) {
    }

    @Override
    public void onError(Response<T> response) {
        OkLogger.printStackTrace(response.getException());
    }

    @Override
    public void onFinish() {
    }

    @Override
    public void uploadProgress(Progress progress) {
    }

    @Override
    public void downloadProgress(Progress progress) {
    }
}
