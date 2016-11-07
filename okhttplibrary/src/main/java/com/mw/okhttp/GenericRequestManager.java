/*******************************************************************************
 *
 * Copyright (c) Weaver Info Tech Co. Ltd
 *
 * DataManager
 *
 * app.backend.manager.GenericRequestManager.java
 * 用来访问服务器上的数据
 *
 * @author: gao_chun
 * @since:  Jul 23, 2014
 * @version: 1.0.0
 *
 * @changeLogs:
 *     1.0.0: First created this class.
 *
 ******************************************************************************/
package com.mw.okhttp;

import android.app.Activity;
import android.app.Application;
import android.text.TextUtils;

import com.mw.okhttp.callback.AbsCallback;
import com.mw.okhttp.cookie.store.PersistentCookieStore;
import com.mw.okhttp.model.HttpParams;
import com.mw.okhttp.utils.OkLogger;

import java.io.File;


/**
 * @author gao_chun
 * 通用数据通讯类，不涉及认证与授权的数据。
 * 这个类的任务：通过相应参数，解析并获取数据对象。
 */
public class GenericRequestManager {

    private static GenericRequestManager sInstance;
    private final String mServerHost;
    //private final Handler mHandler;

    public static GenericRequestManager getInstance() {
        if (sInstance == null) {
            OkLogger.e("ConfigManager.initiate method not called in the application.");
        } // else ignored.
        return sInstance;
    }

    public static void initialize(Application applicationContext) {

        /*HttpHeaders headers = new HttpHeaders();
        headers.put("commonHeaderKey1", "commonHeaderValue1");    //所有的 header 都 不支持 中文
        headers.put("commonHeaderKey2", "commonHeaderValue2");

        HttpParams params = new HttpParams();
        params.put("commonParamsKey1", "commonParamsValue1");     //所有的 params 都 支持 中文
        params.put("commonParamsKey2", "这里支持中文参数");*/

        //初始化网络请求库
        OkHttpUtils.init(applicationContext);

        //初始化全局配置信息（读取MetaData）
        GlobalConfigManager.initialize(applicationContext);

        //初始化网络对象及服务器配置地址
        sInstance = new GenericRequestManager(GlobalConfigManager.getInstance().getServerHost());

        //以下设置的所有参数是全局参数,同样的参数可在请求时再设置一遍,那么对于该请求来讲,请求中的参数会覆盖全局参数
        //优点是全局参数统一,特定请求可以特别定制参数

        OkHttpUtils.getInstance()
                .debug("OkHttp")                                          //是否打开调试

                //--Cookie管理
                //.setCookieStore(new MemoryCookieStore())                //cookie使用内存缓存（app退出后，cookie消失）
                .setCookieStore(new PersistentCookieStore())              //cookie持久化存储，如果cookie不过期，则一直有效

                //--超时设置
                .setConnectTimeout(OkHttpUtils.DEFAULT_MILLISECONDS / 6)               //全局的连接超时时间 (默认60s ，此处设置10s)
                .setReadTimeOut(OkHttpUtils.DEFAULT_MILLISECONDS / 6)                  //全局的读取超时时间
                .setWriteTimeOut(OkHttpUtils.DEFAULT_MILLISECONDS / 6);                //全局的写入超时时间
        //.setRetryCount(3)  //全局统一设置超时重连次数,默认为三次,那么最差的情况会请求4次(一次原始请求,三次重连请求),不需要可以设置为0

        //--缓存模式
        //.setCacheMode(CacheMode.NO_CACHE)               //全局统一设置缓存模式,默认是不使用缓存,可以不传
        //.setCacheTime(CacheEntity.CACHE_NEVER_EXPIRE)   //可以全局统一设置缓存时间,默认永不过期

        //--设置https的证书,方案根据需要设置,不需要不用设置
        //.setCertificates()                                  //方法一：信任所有证书
        //.setCertificates(getAssets().open("srca.cer"))      //方法二：也可以自己设置https证书
        //.setCertificates(getAssets().open("aaaa.bks"), "123456",getAssets().open("srca.cer"))//方法三：传入bks证书,密码,和cer证书,支持双向加密

        //可以添加全局拦截器,不会用的千万不要传,错误写法直接导致任何回调不执行
                /*.addInterceptor(new Interceptor() {
                    @Override
                    public Response intercept(Chain chain) throws IOException {
                        return chain.proceed(chain.request());
                    }
                })*/

        //--设置全局属性
        //.addCommonHeaders(headers)                          //设置全局公共头
        //.addCommonParams(params);                           //设置全局公共参数
    }


    private GenericRequestManager(String serverHost) {

        // 初始化Handler，用于在主线程中执行任务
        //mHandler = new Handler(Looper.getMainLooper());
        // 初始化服务器地址
        mServerHost = serverHost;
    }


    /**
     * GET/POST请求(若带有参数 -- 参数为具体的字段)
     * 描述：HttpParams 参数可为空
     * @param requestType	请求类型
     * @param activity 取消请求的Tag
     * @param mUrl	请求地址
     * @param mParams	请求参数(无可不传)
     * @param mCallback
     */
    public <T> void dataRequestByParamsOrNull(String requestType, Activity activity, String mUrl,
                                              HttpParams mParams,AbsCallback<T> mCallback){

        final String URL = mServerHost + File.separator + mUrl;	//URL

        switch (requestType) {

            case RequestTypes.GET:	//get
                if (mParams != null) {
                    OkHttpUtils.get(URL).tag(activity).params(mParams).execute(mCallback);
                }else {
                    OkHttpUtils.get(URL).tag(activity).execute(mCallback);
                }
                break;

            case RequestTypes.POST:	//post
                if (mParams != null) {
                    OkHttpUtils.post(URL).tag(activity).params(mParams).execute(mCallback);
                }else {
                    OkHttpUtils.post(URL).tag(activity).execute(mCallback);
                }
                break;
            //default ignored.
        }
    }


    /**
     * POST请求（若带有参数 -- 参数为Json或String）
     * @param requestType   请求类型
     * @param activity  取消请求的Tag
     * @param mUrl  请求地址
     * @param mJsonOrStr   不能为空
     * @param mCallback
     */
    public <T> void dataRequestByJsonOrStr(String requestType, Activity activity, String mUrl,
                                           String mJsonOrStr, AbsCallback<T> mCallback){

        if (!TextUtils.isEmpty(mJsonOrStr)) {
            return;
        }

        final String URL = mServerHost + File.separator + mUrl;	//URL

        switch (requestType) {

            case RequestTypes.UP_STRING:	//上传String串
                OkHttpUtils.post(URL).tag(activity).upString(mJsonOrStr).execute(mCallback);
                break;

            case RequestTypes.UP_JSON:	//上传Json串
                OkHttpUtils.post(URL).tag(activity).upJson(mJsonOrStr).execute(mCallback);
                break;
            //default ignored.
        }
    }


    /**
     * 文件下载
     * @param activity  取消请求的Tag
     * @param mUrl  请求地址
     * @param mParams   请求参数（没有可以不填）
     * @param mCallback
     */
    public <T> void downloadFile(Activity activity,String mUrl,HttpParams mParams,AbsCallback<T> mCallback)
    {
        final String URL = mServerHost + File.separator + mUrl;	//URL
        if (mParams != null){
            OkHttpUtils.get(URL).tag(activity).params(mParams).execute(mCallback);
        } else {
            OkHttpUtils.get(URL).tag(activity).execute(mCallback);
        }
    }

}
