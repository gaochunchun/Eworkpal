package com.mainiway.eworkpal.model;

import java.io.Serializable;
import java.util.List;

public class BaseResponse<T> implements Serializable {

    private static final long serialVersionUID = 5213230387175987834L;

    public boolean successed;
    public List<MsgInfo> message;
    public int status;
    public T data;

    public class  MsgInfo implements Serializable {
        public String msg;

        @Override
        public String toString() {
            return "MsgInfo{" +
                    "msg='" + msg + '\'' +
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