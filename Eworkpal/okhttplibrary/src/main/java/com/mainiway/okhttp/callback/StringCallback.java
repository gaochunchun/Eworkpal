package com.mainiway.okhttp.callback;

import com.mainiway.okhttp.convert.StringConvert;

import okhttp3.Response;

/**
 * ================================================
 * 描    述：返回字符串类型的数据
 * ================================================
 */
public abstract class StringCallback extends AbsCallback<String> {

    @Override
    public String convertSuccess(Response response) throws Exception {
        String s = StringConvert.create().convertSuccess(response);
        response.close();
        return s;
    }
}