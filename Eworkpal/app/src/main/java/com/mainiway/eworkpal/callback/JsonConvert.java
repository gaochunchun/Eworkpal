package com.mainiway.eworkpal.callback;


import com.google.gson.stream.JsonReader;
import com.mainiway.eworkpal.base.BaseResponse;
import com.mainiway.eworkpal.base.BaseResultResponse;
import com.mainiway.eworkpal.utils.GsonConvertUtil;
import com.mainiway.okhttp.convert.Converter;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import okhttp3.Response;

/**
 * Json解析
 *
 * @param <T>
 */
public class JsonConvert<T> implements Converter<T> {

    private Type type;

    public void setType(Type type) {
        this.type = type;
    }

    @Override
    public T convertSuccess(Response response) throws Exception {
        JsonReader jsonReader = new JsonReader(response.body().charStream());

        //type为空判断。注：在JsonCallback中已通过setType(Type type)设置过一次
        if (type == null) {
            Type genType = getClass().getGenericSuperclass();
            Type[] params = ((ParameterizedType) genType).getActualTypeArguments();
            type = params[0];
        }

        if (!(type instanceof ParameterizedType))
            throw new IllegalStateException("没有填写泛型参数，请指定BaseResponse<String>");


        Type rawType = ((ParameterizedType) type).getRawType();
        //无指定数据类型 注：此处也需要传递String类型，由于后端返回的message为一个集合
        if (rawType == Void.class) {
            BaseResultResponse baseResultResponse = GsonConvertUtil.fromJson(jsonReader, BaseResultResponse.class);
            //noinspection unchecked
            return (T) baseResultResponse.toJsonResponse();
        }


        //有数据类型 注：对象 or  集合
        if (rawType == BaseResponse.class) {

            BaseResponse jsonResponse = GsonConvertUtil.fromJson(jsonReader, type);
            //boolean isSuccessed = jsonResponse.successed;

            //isSuccessed为true表示请求成功，false表示请求失败
            if (jsonResponse.successed) {
                return (T) jsonResponse;
            } else {
                //false时可能需要取status码来判断，如：4001表示token过期，处理跳入登录界面或其他
                if (jsonResponse.status == 4001) {

                }
                throw new IllegalStateException("错误码：" + jsonResponse.status);
            }


            /*int code = jsonResponse.code;
            if (code == 0) {
                //noinspection unchecked
                return (T) jsonResponse;
            } else if (code == 104) {
                //比如：用户授权信息无效，在此实现相应的逻辑，弹出对话或者跳转到其他页面等,该抛出错误，会在onError中回调。
                throw new IllegalStateException("用户授权信息无效");
            } else if (code == 105) {
                //比如：用户收取信息已过期，在此实现相应的逻辑，弹出对话或者跳转到其他页面等,该抛出错误，会在onError中回调。
                throw new IllegalStateException("用户收取信息已过期");
            } else if (code == 106) {
                //比如：用户账户被禁用，在此实现相应的逻辑，弹出对话或者跳转到其他页面等,该抛出错误，会在onError中回调。
                throw new IllegalStateException("用户账户被禁用");
            } else if (code == 300) {
                //比如：其他乱七八糟的等，在此实现相应的逻辑，弹出对话或者跳转到其他页面等,该抛出错误，会在onError中回调。
                throw new IllegalStateException("其他乱七八糟的等");
            } else {
                throw new IllegalStateException("错误代码：" + code + "，错误信息：" + jsonResponse.msg);
            }*/
        }
        throw new IllegalStateException("基类错误无法解析!");
    }
}