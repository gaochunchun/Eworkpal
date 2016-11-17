/*******************************************************************************
 *
 * Copyright (c) Weaver Info Tech Co. Ltd
 *
 * RequestConstant
 *
 * app.backend.manager.RequestConstant.java
 * TODO: File description or class description.
 *
 * @author: gao_chun
 * @since:  2016-1-12
 * @version: 1.0.0
 *
 * @changeLogs:
 *     1.0.0: First created this class.
 *
 ******************************************************************************/
package com.mw.eworkpal.constant;



/**
 * @author gao_chun
 * 描述：接口URL地址
 */
public class Urls {


    public static String URL_JsonObject = "test";
    public static String URL_JsonArray = "testJsonArray";



    //用户模块
    public static String URL_LOGIN = "api/account/login";                       //用户登录
    public static String URL_CODE = "api/validcode/mobile/send/enterprise-found";//发送手机验证码，在企业注册时
    public static String URL_FIND_PAS_CODE="api/validcode/mobile/send/password-found";//发送手机验证码，找回密码
    public static String URL_PHONE_CODE = "api/validcode/mobile/checking";      //验证手机验证码
    public static String URL_VERIFIY_CODE = "api/platsys/isverificationcode";   //验证图片验证码
    public static String URL_CREATE_ENTERPRISE = "api/login/enterprise-found";  //创建企业
    public static String URL_CREATE_RESETPASSWORD = "api/account/reset-password";  //重置密码


    //签到模块
    public static String URL_GET_SIGNRECORD = "api/sign/getsignrecord-list";//获取签到信息
    public static String URL_ADD_APPSIGN = "api/sign/addappsign";//签到
    public static String URL_GETDAY_LIST = "api/attendance/getday-list";//获取当天考勤详情
    public static String URL_GETBYDAY_LIST = "api/attendance/getbyday-list";//获取当天考勤详情-需要参数
    //public static String URL_UPDATE_APPEAL_TYPE ="api/attendance/update-type";//处理签到申述
    public static String URL_GET_ATTENDANCE_LIST = "api/attendance/getperson-list";//个人考勤模块- 我的申述
    public static String URL_ATTENDANCE="api/attendance/personappeal";//个人中心 -- 考勤申述
    public static String URL_LEAVE_THE_QUERY="api/holiday/query";//查询调休、年假

    //通讯录模块
    public static String URL_GET_ORGANIZATION = "api/organization/company-org";//组织架构
    public static String URL_GET_MYDEPARTMENT = "api/organization/dept-users";//我的部门
    public static String URL_GET_CONTACT_USERINFO = "api/user/info";//用户信息

}
