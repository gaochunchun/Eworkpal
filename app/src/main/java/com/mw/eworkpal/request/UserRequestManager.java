package com.mw.eworkpal.request;

import android.app.Activity;

import com.mw.eworkpal.constant.Urls;
import com.mw.okhttp.GenericRequestManager;
import com.mw.okhttp.RequestTypes;
import com.mw.okhttp.callback.AbsCallback;

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

    public <T> void testReturnJsonObject(Activity activity,AbsCallback<T> mCallback) {

        GenericRequestManager.getInstance().dataRequestByParamsOrNull(RequestTypes.GET,activity, Urls.URL_JsonObject,null,mCallback);

    }

    public <T> void testReturnJsonArray (Activity activity , AbsCallback<T> mCallback) {

        GenericRequestManager.getInstance().dataRequestByParamsOrNull(RequestTypes.GET,activity, Urls.URL_jsonArray,null,mCallback);

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

        GenericDataManager.getInstance().dataRequest(AppConstant.REQUEST_CODE_0,RequestType.TYPE_JSON, activity,Urls.URL_CREATE_RESETPASSWORD, null,JSON.toJSONString(map), mCallback);
    }*/

}
