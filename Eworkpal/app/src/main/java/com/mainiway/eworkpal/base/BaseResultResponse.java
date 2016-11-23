package com.mainiway.eworkpal.base;

import java.io.Serializable;
import java.util.List;

/**
 * ===========================================
 * 作    者：gao_chun
 * 版    本：1.0
 * 创建日期：2016-11-19.
 * 描    述：服务器返回的Base模型（data以外的外层数据）
 * ===========================================
 */
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