package com.mw.eworkpal.model;

import java.io.Serializable;

public class ResultResponse<T> implements Serializable {

    private static final long serialVersionUID = 5213230387175987834L;

    public int code;
    public String msg;
    public T data;
}