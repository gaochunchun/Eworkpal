package com.mw.eworkpal.model;

import java.io.Serializable;

public class CommonResponse implements Serializable {

    private static final long serialVersionUID = -1477609349345966116L;

    public int code;
    public String msg;

    public ResultResponse toJsonResponse() {
        ResultResponse jsonResponse = new ResultResponse();
        jsonResponse.code = code;
        jsonResponse.msg = msg;
        return jsonResponse;
    }
}