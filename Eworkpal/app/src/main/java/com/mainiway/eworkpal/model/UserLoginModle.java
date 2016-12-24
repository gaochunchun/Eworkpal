package com.mainiway.eworkpal.model;

import java.io.Serializable;

/**
 * ===========================================
 * 作    者：zhsh
 * 版    本：1.0
 * 创建日期：2016/12/23.
 * 描    述：登录,选择某个企业Model
 * ===========================================
 */

public class UserLoginModle implements Serializable {
    private static final long serialVersionUID = -9031929025134507229L;
    public String token;
    public int userinfoID;
    public int userID;
    public String name;
    public int companyID;
    public String companyName;
    public String head;
    public int isManager;
    public int deptId;
    public int posionId;
    public int addressId;

    @Override
    public String toString() {
        return "UserLoginModle{" +
                "token='" + token + '\'' +
                ", userinfoID=" + userinfoID +
                ", userID=" + userID +
                ", name='" + name + '\'' +
                ", companyID=" + companyID +
                ", companyName='" + companyName + '\'' +
                ", head='" + head + '\'' +
                ", isManager=" + isManager +
                ", deptId=" + deptId +
                ", posionId=" + posionId +
                ", addressId=" + addressId +
                '}';
    }
}
