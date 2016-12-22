package com.mainiway.eworkpal.activity.test;

import java.io.Serializable;

/**
 * Created by gao_chun on 2016-11-16.
 * 测试Model
 */

public class A implements Serializable {


    private static final long serialVersionUID = 3212079394312949760L;

    public String token;
    public String userID;
    public String name;
    public String head;


    @Override
    public String toString() {
        return "A{" +
                "token='" + token + '\'' +
                ", userID='" + userID + '\'' +
                ", name='" + name + '\'' +
                ", head='" + head + '\'' +
                '}';
    }
}
