package com.mainiway.eworkpal.callback;


import com.mainiway.okhttp.callback.AbsCallback;
import com.mainiway.okhttp.request.BaseRequest;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import okhttp3.Response;

/**
 * ================================================
 * 描述：默认将返回的数据解析成需要的Bean,可以是 BaseBean，String，List，Map
 * ================================================
 */
public abstract class JsonCallback<T> extends AbsCallback<T> {

    @Override
    public void onBefore(BaseRequest request) {
        super.onBefore(request);
        //主要用于在所有请求之前添加公共的请求头或请求参数，例如登录授权的 token,使用的设备信息等,可以随意添加,也可以什么都不传
       /* request.headers("header1", "HeaderValue1")//
                .params("params1", "ParamsValue1")//
                .params("token", "3215sdf13ad1f65asd4f3ads1f");*/
    }

    /**
     * 该方法是子线程处理，不能做ui相关的工作
     * 主要作用是解析网络返回的 response 对象,生产onSuccess回调中需要的数据对象
     */
    @Override
    public T convertSuccess(Response response) throws Exception {

        //以下代码是通过泛型解析实际参数,泛型必须传

        //getClass().getGenericSuperclass()返回表示此 Class 所表示的实体（类、接口、基本类型或 void）的直接超类的 Type
        Type genType = getClass().getGenericSuperclass();

        //然后将其转换ParameterizedType       getActualTypeArguments()返回表示此类型实际类型参数的 Type 对象的数组。
        //简而言之就是获得超类的泛型参数的实际类型
        Type[] params = ((ParameterizedType) genType).getActualTypeArguments();

        JsonConvert<T> convert = new JsonConvert<>();
        convert.setType(params[0]);
        T t = convert.convertSuccess(response);
        response.close();
        return t;
    }
}