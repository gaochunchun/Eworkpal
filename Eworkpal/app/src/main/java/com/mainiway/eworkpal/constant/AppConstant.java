/*******************************************************************************
 * Copyright (c) Weaver Info Tech Co. Ltd
 * <p/>
 * AppConstant
 * <p/>
 * app.backend.manager.AppConstant.java
 * TODO: File description or class description.
 *
 * @author: gao_chun
 * @since: 2015-3-6
 * @version: 1.0.0
 * @changeLogs: 1.0.0: First created this class.
 ******************************************************************************/

package com.mainiway.eworkpal.constant;

/**
 * ===========================================
 * 作    者：gao_chun
 * 版    本：1.0
 * 创建日期：2016-11-19.
 * 描    述：常量类
 * ===========================================
 */
public class AppConstant {

    //保存的配置信息
    public static final String PREF_NAME_TOKEN = "token";    //登录成功保存Token
    public static final String PREF_NAME_USERID = "userid";
    public static final String PREF_NAME_USERNAME = "username";
    public static final String PREF_NAME_PHONE = "phone";


    public static final String PREF_NAME_VERSIONCODE = "versionCode";    //版本号
    public static final String PREF_NAME_LAST_VERSION_CODE = "last_version_code";//上次安装的版本编号


    /*********
     * Intent 常量信息传递
     *********/

    public static final String LABEL_PHONE_CODE = "phoneCodeLabel";
    public static final String PHONE_CODE_ENTERPRISE = "create_enterprise";
    public static final String PHONE_CODE_FORGET_PWD = "forgetpwd";

    /*********
     * startActivityForResult 常量值
     *********/
    public static final int VALUE_FORGET_PWD_ACTIVITY = 10001;//忘记密码界面
    public static final int VALUE_COMMON_PHONE_CODE_ACTIVITY = 10002;//注册（找回密码）界面
    public static final int VALUE_CREATE_ENTERPRISE= 10002;//注册企业界面


}
