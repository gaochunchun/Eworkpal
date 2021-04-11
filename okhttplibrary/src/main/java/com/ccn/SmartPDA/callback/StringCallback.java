package com.ccn.SmartPDA.callback;

import com.ccn.SmartPDA.convert.StringConvert;

import okhttp3.Response;

/**
 * ================================================
 * 版    本：1.0
 * 描    述：返回字符串类型的数据
 * 修订历史：
 * ================================================
 */
public abstract class StringCallback extends AbsCallback<String> {

    private StringConvert convert;

    public StringCallback() {
        convert = new StringConvert();
    }

    @Override
    public String convertResponse(Response response) throws Throwable {
        String s = convert.convertResponse(response);
        response.close();
        return s;
    }
}
