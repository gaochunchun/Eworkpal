package com.mainiway.okhttp.adapter;

import com.mainiway.okhttp.callback.AbsCallback;
import com.mainiway.okhttp.model.Response;
import com.mainiway.okhttp.request.BaseRequest;

/**
 * ================================================
 * 描    述：请求的包装类
 * ================================================
 */
public interface Call<T> {
    /** 同步执行 */
    Response<T> execute() throws Exception;

    /** 异步回调执行 */
    void execute(AbsCallback<T> callback);

    /** 是否已经执行 */
    boolean isExecuted();

    /** 取消 */
    void cancel();

    /** 是否取消 */
    boolean isCanceled();

    Call<T> clone();

    BaseRequest getBaseRequest();
}