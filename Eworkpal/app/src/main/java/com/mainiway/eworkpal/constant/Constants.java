/*******************************************************************************
 * Copyright (c) Weaver Info Tech Co. Ltd
 * <p/>
 * Constants
 * <p/>
 * app.backend.manager.Constants.java
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
public class Constants {

    //保存的配置信息
    public static final String PREF_NAME_TOKEN = "token";	//登录成功保存Token
    public static final String PREF_NAME_USERID = "userid";
    public static final String PREF_NAME_USERNAME = "username";
    public static final String PREF_NAME_SEX = "sex";
    public static final String PREF_NAME_DEPTID = "deptid";
    public static final String PREF_NAME_DEPTNAME = "deptname";
    public static final String PREF_NAME_POSTID = "postid";
    public static final String PREF_NAME_POSTNAME = "postname";
    public static final String PREF_NAME_PHONE = "phone";
    public static final String PREF_NAME_EMAIL = "email";
    public static final String PREF_NAME_PIC = "pic";
    public static final String PREF_NAME_COMPANY = "company";
    public static final String PREF_NAME_PWD = "password";  //记住密码

    public static final String PREF_NAME_VERSIONCODE = "versionCode";	//版本号
    public static final String PREF_NAME_LAST_VERSION_CODE = "last_version_code";//上次安装的版本编号


    /********* Intent 常量信息传递 *********/

    public static final String LABEL_PHONE_CODE = "phoneCodeLabel";
    public static final String PHONE_CODE_ENTERPRISE = "create_enterprise";
    public static final String PHONE_CODE_FORGET_PWD = "forgetpwd";


    //动态模块常亮
    public static final String DYNAMIC = "dynamic";

    //动态模块文件上传权限
    public static final String DYNAMIC_SOURCE = "12";
    public static final String DYNAMIC_IS_PUBLIC = "0";

    //文档模块常亮
    public static final String DOCUMENT = "document";
    public static final String DOCUMENT_ALL = "document_all";

    public static final String DOCUMENT_UPLOAD = "document_upload";
    public static final String DOCUMENT_SHARE = "document_share";
    public static final String DOCUMENT_PUBLIC = "document_public";

}
