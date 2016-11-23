package com.mainiway.eworkpal.base;

import java.io.Serializable;
import java.util.List;

/**
 * ===========================================
 * 作    者：gao_chun
 * 版    本：1.0
 * 创建日期：2016-11-19.
 * 描    述：服务器返回的Base模型（包含data）
 * ===========================================
 */
public class BaseResponse<T> implements Serializable {

    private static final long serialVersionUID = 5213230387175987834L;

    public boolean successed;
    public List<MsgInfo> message;
    public int status;
    public T data;

    public class  MsgInfo implements Serializable {

        public String key;
        public String msg;

        @Override
        public String toString() {
            return "MsgInfo{" +
                    "key='" + key + '\'' +
                    ", msg='" + msg + '\'' +
                    '}';
        }
    }


    @Override
    public String toString() {
        return "BaseResponse{" +
                "successed=" + successed +
                ", message=" + message +
                ", status=" + status +
                ", data=" + data +
                '}';
    }
}