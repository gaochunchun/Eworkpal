package com.mainiway.eworkpal.activity.sign;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.ccn.SmartPDA.OkHttpUtil;
import com.ccn.SmartPDA.callback.StringCallback;
import com.ccn.SmartPDA.cookie.store.CookieStore;
import com.ccn.SmartPDA.model.Response;
import com.ccn.SmartPDA.utils.OkLogger;
import com.mainiway.eworkpal.R;

import okhttp3.Cookie;
import okhttp3.HttpUrl;

public class CookieActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.activity_test2);
    }

    //String xmlData = "<?xml version=\"1.0\"?><FHE><Tickets/><PostId>3950243730</PostId><Data DataType=\"Json/P\">{\"M10\" : \"2021-04-03\",\"M11\" : 1,\"M4\" : 1}</Data></FHE>";


    public void onClick(View v) {
        if (v.getId() == R.id.btn_test) {
            String URL_METHOD = "https://www.fxiaoke.com/FHE/EM1AKaoQinV2/dataAppService/getDailyInfo/iOS.741001?_vn=741001&_ov=14.3&traceId=E-E.577382.1371-33C42ACF-3CB5-4FA9-9A1C-D14A0113D72B&versionName=7.4.1&_postid=-634889882";
            HttpUrl httpUrl = HttpUrl.parse(URL_METHOD);
            Cookie.Builder builder = new Cookie.Builder();
            Cookie cookie = builder.name("Cookie").value("FSAuthXC=0G4WkhQuvm800003dkOG0O31vUJUmyRC29v9MLUhXNyaMctdcVVjUZASfLFbqXzYGB9ivooSg44oWtpS3uWFP0XGyhgLR5T5K2TYTgVdyylkba7rdOKhgfEA2YzYtzhT82zBB0VaentSQsQBATjUKZS4fN2vb9EyleIY0CVeYyLuBwYWr6P271G5NTC9tdzq1Edhe5sBsGqarxoW33yDf5g2M5KXlxJXl6abDv7wKwi6LkCBMI3OMikpZBp1; LoginId=LOGIN_ID_514f9a0d-5e2c-4889-82e6-7d32d233946c; fs_token=Op0tOpCqC6GjOJavC2qqOcPZBJavE3SjDMLbDZ1cPcPZOp5a; FSAuthX=0G4WkhQuvm800003dkOG0O31vUJUmyRC29v9MLUhXNyaMctdcVVjUZASfLFbqXzYGB9ivooSg44oWtpS3uWFP0XGyhgLR5T5K2TYTgVdyylkba7rdOKhgfEA2YzYtzhT82zBB0VaentSQsQBATjUKZS4fN2vb9EyleIY0CVeYyLuBwYWr6P271G5NTC9tdzq1Edhe5sBsGqarxoW33yDf5g2M5KXlxJXl6abDv7wKwi6LkCBMI3OMikpZBp1; sso_token=3c1d76a4-8a27-43ce-bf59-6627a0fc25f9").
                    domain(httpUrl.host()).build();
            CookieStore cookieStore = OkHttpUtil.getInstance().getCookieJar().getCookieStore();
            cookieStore.saveCookie(httpUrl, cookie);


            String date = "2021-04-09";
            String param = GetDailyInfoRequest.getRequestParameter(date);
            OkHttpUtil.<String>post(URL_METHOD)//
                    .tag(this)
                    //.upString(xmlData, MediaType.parse("application/xml"))
                    .upString(param)
                    .execute(new StringCallback() {
                        @Override
                        public void onSuccess(Response<String> response) {
                            OkLogger.e(response.body());
                        }

                        @Override
                        public void onError(Response<String> response) {
                            OkLogger.e("error:" + response.body());
                        }
                    });
        } else if (v.getId() == R.id.btn_cookie) {

           /* CookieStore cookieStore = OkHttpUtil.getInstance().getCookieJar().getCookieStore();
            HttpUrl httpUrl = HttpUrl.parse(URL_METHOD);
            List<Cookie> cookies = cookieStore.getCookie(httpUrl);
            OkLogger.e(httpUrl.host() + "对应的cookie如下：" + cookies.toString() + '\n');


            CookieStore cookieStore2 = OkHttpUtil.getInstance().getCookieJar().getCookieStore();
            List<Cookie> allCookie = cookieStore2.getAllCookie();
            OkLogger.e("所有cookie如下：" + allCookie.toString());*/
        } else if (v.getId() == R.id.btn_login) {


            String URL_METHOD2 = "https://www.fxiaoke.com/FHE/EM1AKaoQinV2/dataAppService/createCheckins/iOS.741001?_vn=741001&_ov=14.3&traceId=E-E.577382.1371-3FB330FB-5E7A-4342-85AE-78593F1B329C&versionName=7.4.1&_postid=-933325967";

            HttpUrl httpUrl = HttpUrl.parse(URL_METHOD2);
            Cookie.Builder builder = new Cookie.Builder();
            Cookie cookie = builder.name("Cookie").value("FSAuthXC=0G4WkhQuvm800003dkOG0O31vUJUmyRC29v9MLUhXNyaMctdcVVjUZASfLFbqXzYGB9ivooSg44oWtpS3uWFP0XGyhgLR5T5K2TYTgVdyylkba7rdOKhgfEA2YzYtzhT82zBB0VaentSQsQBATjUKZS4fN2vb9EyleIY0CVeYyLuBwYWr6P271G5NTC9tdzq1Edhe5sBsGqarxoW33yDf5g2M5KXlxJXl6abDv7wKwi6LkCBMI3OMikpZBp1; LoginId=LOGIN_ID_514f9a0d-5e2c-4889-82e6-7d32d233946c; fs_token=Op0tOpCqC6GjOJavC2qqOcPZBJavE3SjDMLbDZ1cPcPZOp5a; FSAuthX=0G4WkhQuvm800003dkOG0O31vUJUmyRC29v9MLUhXNyaMctdcVVjUZASfLFbqXzYGB9ivooSg44oWtpS3uWFP0XGyhgLR5T5K2TYTgVdyylkba7rdOKhgfEA2YzYtzhT82zBB0VaentSQsQBATjUKZS4fN2vb9EyleIY0CVeYyLuBwYWr6P271G5NTC9tdzq1Edhe5sBsGqarxoW33yDf5g2M5KXlxJXl6abDv7wKwi6LkCBMI3OMikpZBp1; sso_token=3c1d76a4-8a27-43ce-bf59-6627a0fc25f9").
                    domain(httpUrl.host()).build();
            CookieStore cookieStore = OkHttpUtil.getInstance().getCookieJar().getCookieStore();
            cookieStore.saveCookie(httpUrl, cookie);


            String date = "2021-04-09";
            String param = CreateCheckinsRequest.getRequestParameter(date);

            /*OkHttpUtil.<String>post(URL_METHOD2)//
                    .tag(this)
                    //.upString(xmlData, MediaType.parse("application/xml"))
                    .upString(param)
                    .execute(new StringCallback() {
                        @Override
                        public void onSuccess(Response<String> response) {
                            OkLogger.e("------->" + response.body());
                        }

                        @Override
                        public void onError(Response<String> response) {
                            OkLogger.e("error:" + response.body());
                        }
                    });*/
        }
    }


    /*@Override
    protected void onDestroy() {
        super.onDestroy();
        //Activity销毁时，取消网络请求
        OkGo.getInstance().cancelTag(this);
    }

    @OnClick(R.id.getCookie)
    public void getCookie(View view) {
        //一般手动取出cookie的目的只是交给 webview 等等，非必要情况不要自己操作
        CookieStore cookieStore = OkGo.getInstance().getCookieJar().getCookieStore();
        HttpUrl httpUrl = HttpUrl.parse(Urls.URL_METHOD);
        List<Cookie> cookies = cookieStore.getCookie(httpUrl);
        showToast(httpUrl.host() + "对应的cookie如下：" + cookies.toString());
    }

    @OnClick(R.id.getAllCookie)
    public void getAllCookie(View view) {
        //一般手动取出cookie的目的只是交给 webview 等等，非必要情况不要自己操作
        CookieStore cookieStore = OkGo.getInstance().getCookieJar().getCookieStore();
        List<Cookie> allCookie = cookieStore.getAllCookie();
        showToast("所有cookie如下：" + allCookie.toString());
    }

    @OnClick(R.id.addCookie)
    public void addCookie(View view) {

        HttpUrl httpUrl = HttpUrl.parse(Urls.URL_METHOD);
        Cookie.Builder builder = new Cookie.Builder();
        Cookie cookie = builder.name("myCookieKey1").value("myCookieValue1").domain(httpUrl.host()).build();
        CookieStore cookieStore = OkGo.getInstance().getCookieJar().getCookieStore();
        cookieStore.saveCookie(httpUrl, cookie);

        showToast("详细添加cookie的代码，请看demo的代码");

        OkHttpUtil.<String>post(Urls.URL_TEXT_UPLOAD)//
                .tag(this)//
                .execute(new StringDialogCallback(this) {
                    @Override
                    public void onSuccess(Response<String> response) {
                        handleResponse(response);
                    }

                    @Override
                    public void onError(Response<String> response) {
                        handleError(response);
                    }
                });
    }

    @OnClick(R.id.removeCookie)
    public void removeCookie(View view) {
        HttpUrl httpUrl = HttpUrl.parse(Urls.URL_METHOD);
        CookieStore cookieStore = OkGo.getInstance().getCookieJar().getCookieStore();
        cookieStore.removeCookie(httpUrl);

        showToast("详细移除cookie的代码，请看demo的代码");
    }

    @OnClick(R.id.updateCookie)
    public void updateCookie(View view) {
        showToast("暂时未实现，可以先移除再添加，效果一样");
    }*/



   /* OkHttpUtil.<LzyResponse<ServerModel>>post(Urls.URL_METHOD)//
                        .tag(this)//
                        .headers("header1", "headerValue1")//
                        .params("param1", "paramValue1")//
                        .params("param2", "paramValue2")//
                        .params("param3", "paramValue3")//
                        .isMultipart(true)         //强制使用 multipart/form-data 表单上传（只是演示，不需要的话不要设置。默认就是false）
                        .execute(new DialogCallback<LzyResponse<ServerModel>>(this) {
        @Override
        public void onSuccess(Response<LzyResponse<ServerModel>> response) {
            handleResponse(response);
        }

        @Override
        public void onError(Response<LzyResponse<ServerModel>> response) {
            handleError(response);
        }
    });*/

    /**
     * 解析javabean对象
     */
    /*@OnClick(R.id.requestJson)
    public void requestJson(View view) {
        OkHttpUtil.<LzyResponse<ServerModel>>get(Urls.URL_JSONOBJECT)//
                .tag(this)//
                .headers("header1", "headerValue1")//
                .params("param1", "paramValue1")//
                .execute(new DialogCallback<LzyResponse<ServerModel>>(this) {

                    @Override
                    public void onSuccess(Response<LzyResponse<ServerModel>> response) {
                        handleResponse(response);
                    }

                    @Override
                    public void onError(Response<LzyResponse<ServerModel>> response) {
                        handleError(response);
                    }
                });
    }*/

    /**
     * 解析集合对象
     */
    /*@OnClick(R.id.requestJsonArray)
    public void requestJsonArray(View view) {
        OkHttpUtil.<LzyResponse<List<ServerModel>>>get(Urls.URL_JSONARRAY)//
                .tag(this)//
                .headers("header1", "headerValue1")//
                .params("param1", "paramValue1")//
                .execute(new DialogCallback<LzyResponse<List<ServerModel>>>(this) {
                    @Override
                    public void onSuccess(Response<LzyResponse<List<ServerModel>>> response) {
                        handleResponse(response);
                    }

                    @Override
                    public void onError(Response<LzyResponse<List<ServerModel>>> response) {
                        handleError(response);
                    }
                });
    }*/
}
