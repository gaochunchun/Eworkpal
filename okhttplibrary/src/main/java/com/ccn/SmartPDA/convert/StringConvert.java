package com.ccn.SmartPDA.convert;

import okhttp3.Response;
import okhttp3.ResponseBody;

/**
 * ================================================
 * 描    述：字符串的转换器
 * 修订历史：
 * ================================================
 */
public class StringConvert implements Converter<String> {

    @Override
    public String convertResponse(Response response) throws Throwable {
        ResponseBody body = response.body();
        if (body == null) return null;
        return body.string();
    }
}
