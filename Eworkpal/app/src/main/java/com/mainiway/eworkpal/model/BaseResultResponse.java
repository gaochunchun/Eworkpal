package com.mainiway.eworkpal.model;

import java.io.Serializable;
import java.util.List;

public class BaseResultResponse implements Serializable {

    private static final long serialVersionUID = -1477609349345966116L;

    public boolean successed;
    public List<BaseResponse.MsgInfo> message;
    public int status;

    public BaseResponse toJsonResponse() {
        BaseResponse jsonResponse = new BaseResponse();
        jsonResponse.successed = successed;
        jsonResponse.message = message;
        jsonResponse.status = status;
        return jsonResponse;
    }

    @Override
    public String toString() {
        return "BaseResultResponse{" +
                "successed=" + successed +
                ", message=" + message +
                ", status=" + status +
                '}';
    }
}