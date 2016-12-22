package com.mainiway.eworkpal.constant;

/**
 * ===========================================
 * 作    者：zhsh
 * 版    本：1.0
 * 创建日期：2016/12/22.
 * 描    述：服务器返回的status值和要传的值
 * ===========================================
 */

public class ResultErrorCode {
    //状态编号
    public static int CODE_SUCCESS = 200;//成功
    public static int CODE_LOGICAL_FAILURE = 400;//逻辑失败
    public static int CODE_FORM_VALIDATION_ERROR = 401;//表单验证错误
    public static int CODE_LOGIN_THREE = 402;//登录超过3次，出现图形验证码时，返回的错误码
    public static int CODE_SEND_CODE_THREE = 403;//手机发送验证码超过3次，返回的错误码
    public static int CODE_NOT_AUTHORIZED = 404;//未授权
    public static int CODE_SERVER_ERROR = 500;//服务端错误
    public static int CODE_NOT_IMPLEMENTED = 501;//未实现

    //模块编号：
    public static int TYPE_SUBMIT_LOGIN = 0;//提交登录模块
    public static int TYPE_CREATE_ENTERPRISE = 1;//创建企业
    public static int TYPE_JOIN_ENTERPRISE = 2;//申请加入企业
    public static int TYPE_RETRIEVE_PASSWORD = 3;//找回密码[密码重置]
    public static int TYPE_TRANSFER_MANAGEMENT = 4;//移交管理


}
