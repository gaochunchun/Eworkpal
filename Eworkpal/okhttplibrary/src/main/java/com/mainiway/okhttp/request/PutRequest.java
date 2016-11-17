package com.mainiway.okhttp.request;

import com.mainiway.okhttp.model.HttpHeaders;
import com.mainiway.okhttp.utils.HttpUtils;
import com.mainiway.okhttp.utils.OkLogger;

import java.io.IOException;

import okhttp3.Request;
import okhttp3.RequestBody;

public class PutRequest extends BaseBodyRequest<PutRequest> {

    public PutRequest(String url) {
        super(url);
    }

    @Override
    public Request generateRequest(RequestBody requestBody) {
        try {
            headers.put(HttpHeaders.HEAD_KEY_CONTENT_LENGTH, String.valueOf(requestBody.contentLength()));
        } catch (IOException e) {
            OkLogger.e(e);
        }
        Request.Builder requestBuilder = HttpUtils.appendHeaders(headers);
        return requestBuilder.put(requestBody).url(url).tag(tag).build();
    }
}