package com.mainiway.eworkpal.request;

import android.app.Activity;

import com.mainiway.eworkpal.constant.Urls;
import com.mainiway.okhttp.GenericRequestManager;
import com.mainiway.okhttp.RequestTypes;
import com.mainiway.okhttp.callback.AbsCallback;
import com.mainiway.okhttp.model.HttpParams;

public class UserRequestManager {

    private static UserRequestManager sInstance;

    public static UserRequestManager getInstance() {

        if (sInstance == null) {

            sInstance = new UserRequestManager();

        } // else ignored.
        return sInstance;
    }


    /**
     * 功能：测试，返回JsonObject对象
     */

    public <T> void testReturnJsonObject(Activity activity, AbsCallback<T> mCallback) {

        GenericRequestManager.getInstance().dataRequestByParamsOrNull(RequestTypes.GET, activity, Urls.URL_JsonObject, null, mCallback);

    }

    /**
     * 功能：测试，返回JsonArray数组
     */
    public <T> void testReturnJsonArray(Activity activity, AbsCallback<T> mCallback) {

        GenericRequestManager.getInstance().dataRequestByParamsOrNull(RequestTypes.GET, activity, Urls.URL_JsonArray, null, mCallback);

    }


    /**
     * 忘记密码
     * @param activity
     * @param mobile
     * @param newPwd
     * @param mCallback
     */
    /*public <T> void resetPwd(Activity activity,String mobile,String newPwd,AbsCallback<T> mCallback) {

        Map<String,Object>  map = new HashMap<String,Object>();
        map.put("MobilePhone",mobile);
        map.put("NewPassword",newPwd);

        GenericDataManager.getInstance().dataRequest(Constants.REQUEST_CODE_0,RequestType.TYPE_JSON, activity,Urls.URL_CREATE_RESETPASSWORD, null,JSON.toJSONString(map), mCallback);
    }*/

    /**
     * 功能：提交登录,返回企业列表(企业Id,企业Name)   界面:LoginActivity.java
     */

    public <T> void loginGo(Activity activity, String mParams, AbsCallback<T> mCallback) {

        GenericRequestManager.getInstance().dataRequestByJsonOrStr(RequestTypes.UP_JSON, activity, Urls.URL_LOGIN_GO, mParams, mCallback);

    }

    /**
     * 功能：登录,选择某个企业   界面:LoginActivity.java
     */

    public <T> void login(Activity activity, String mParams, AbsCallback<T> mCallback) {

        GenericRequestManager.getInstance().dataRequestByJsonOrStr(RequestTypes.UP_JSON, activity, Urls.URL_LOGIN, mParams, mCallback);

    }

    /**
     * 功能：发送手机短信码         页面:CommonPhoneCodeActivity.java
     */

    public <T> void setPhoneCode(Activity activity, String mParams, AbsCallback<T> mCallback) {

        GenericRequestManager.getInstance().dataRequestByJsonOrStr(RequestTypes.UP_JSON, activity, Urls.URL_SET_PHONE_CODE, mParams, mCallback);

    }


    /**
     * 功能：创建企业         页面:CreateEnterpriseActivity.java
     */

    public <T> void createEnterprise(Activity activity, String mParams, AbsCallback<T> mCallback) {

        GenericRequestManager.getInstance().dataRequestByJsonOrStr(RequestTypes.UP_JSON, activity, Urls.URL_CREATE_ENTERPRISE, mParams, mCallback);

    }
}
