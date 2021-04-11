package com.ccn.SmartPDA.adapter;

import com.ccn.SmartPDA.callback.Callback;
import com.ccn.SmartPDA.request.base.Request;
import com.ccn.SmartPDA.model.Response;

/**
 * ================================================
 * 描    述：请求的包装类
 * 修订历史：
 * ================================================
 */
public interface Call<T> {
    /** 同步执行 */
    Response<T> execute() throws Exception;

    /** 异步回调执行 */
    void execute(Callback<T> callback);

    /** 是否已经执行 */
    boolean isExecuted();

    /** 取消 */
    void cancel();

    /** 是否取消 */
    boolean isCanceled();

    Call<T> clone();

    Request getRequest();
}
