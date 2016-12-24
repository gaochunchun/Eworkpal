/*******************************************************************************
 * Copyright (c) Weaver Info Tech Co. Ltd
 * <p>
 * RequestConstant
 * <p>
 * app.backend.manager.RequestConstant.java
 * TODO: File description or class description.
 *
 * @author: gao_chun
 * @since: 2016-1-12
 * @version: 1.0.0
 * @changeLogs: 1.0.0: First created this class.
 ******************************************************************************/
package com.mainiway.eworkpal.constant;


/**
 * @author gao_chun
 *         描述：接口URL地址
 */
public class Urls {


    private static final String VERSION_CODE = "v1.0";

    //测试使用  -- 待删除
    public static String URL_JsonObject = "testJsonObject";
    public static String URL_JsonArray = "testJsonArray";

    //登录
    public static String URL_LOGIN_GO = "Account/V1.0/loginGo";//提交登录,返回企业列表
    public static String URL_LOGIN = "Account/V1.0/company";//登录,选择某个企业
    public static String URL_SET_PHONE_CODE = "Mobile/" + VERSION_CODE + "/sendMobileCode";//发送手机短信码、
    public static String URL_CREATE_ENTERPRISE = "AccountCommon/V1.0/registCompany";//注册提交企业【Web端Mobile共用】
    public static String URL_RETRIEVE_PASSWORD = "AccountCommon/V1.0/resetPwd";//密码重置接口


}
