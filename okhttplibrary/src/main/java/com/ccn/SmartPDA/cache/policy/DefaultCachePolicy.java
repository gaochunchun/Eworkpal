package com.ccn.SmartPDA.cache.policy;

import com.ccn.SmartPDA.callback.Callback;
import com.ccn.SmartPDA.model.Response;
import com.ccn.SmartPDA.request.base.Request;
import com.ccn.SmartPDA.cache.CacheEntity;
import com.ccn.SmartPDA.exception.CacheException;

import okhttp3.Call;

/**
 * ================================================
 * 描    述：
 * 修订历史：
 * ================================================
 */
public class DefaultCachePolicy<T> extends BaseCachePolicy<T> {

    public DefaultCachePolicy(Request<T, ? extends Request> request) {
        super(request);
    }

    @Override
    public void onSuccess(final Response<T> success) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                mCallback.onSuccess(success);
                mCallback.onFinish();
            }
        });
    }

    @Override
    public void onError(final Response<T> error) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                mCallback.onError(error);
                mCallback.onFinish();
            }
        });
    }

    @Override
    public boolean onAnalysisResponse(final Call call, final okhttp3.Response response) {
        if (response.code() != 304) return false;

        if (cacheEntity == null) {
            final Response<T> error = Response.error(true, call, response, CacheException.NON_AND_304(request.getCacheKey()));
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    mCallback.onError(error);
                    mCallback.onFinish();
                }
            });
        } else {
            final Response<T> success = Response.success(true, cacheEntity.getData(), call, response);
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    mCallback.onCacheSuccess(success);
                    mCallback.onFinish();
                }
            });
        }
        return true;
    }

    @Override
    public Response<T> requestSync(CacheEntity<T> cacheEntity) {
        try {
            prepareRawCall();
        } catch (Throwable throwable) {
            return Response.error(false, rawCall, null, throwable);
        }
        Response<T> response = requestNetworkSync();
        //HTTP cache protocol
        if (response.isSuccessful() && response.code() == 304) {
            if (cacheEntity == null) {
                response = Response.error(true, rawCall, response.getRawResponse(), CacheException.NON_AND_304(request.getCacheKey()));
            } else {
                response = Response.success(true, cacheEntity.getData(), rawCall, response.getRawResponse());
            }
        }
        return response;
    }

    @Override
    public void requestAsync(CacheEntity<T> cacheEntity, Callback<T> callback) {
        mCallback = callback;
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                mCallback.onStart(request);

                try {
                    prepareRawCall();
                } catch (Throwable throwable) {
                    Response<T> error = Response.error(false, rawCall, null, throwable);
                    mCallback.onError(error);
                    return;
                }
                requestNetworkAsync();
            }
        });
    }
}
