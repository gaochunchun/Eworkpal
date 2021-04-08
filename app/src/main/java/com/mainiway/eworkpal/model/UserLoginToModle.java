package com.mainiway.eworkpal.model;

import java.io.Serializable;

/**
 * ===========================================
 * 作    者：zhsh
 * 版    本：1.0
 * 创建日期：2016/12/20.
 * 描    述：提交登录，返回企业列表Model
 * ===========================================
 */

public class UserLoginToModle implements Serializable{

    private static final long serialVersionUID = -9031929025134507229L;
    public String companyID;//
    public String name;//

    @Override
    public String toString() {
        return "UserLoginToModle{" +
                "companyID='" + companyID + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
