package com.ccn.SmartPDA.exception;

/**
 * ================================================
 * 描    述：
 * 修订历史：
 * ================================================
 */
public class OkHttpUtilException extends Exception {
    private static final long serialVersionUID = -8641198158155821498L;

    public OkHttpUtilException(String detailMessage) {
        super(detailMessage);
    }

    public static OkHttpUtilException UNKNOWN() {
        return new OkHttpUtilException("unknown exception!");
    }

    public static OkHttpUtilException BREAKPOINT_NOT_EXIST() {
        return new OkHttpUtilException("breakpoint file does not exist!");
    }

    public static OkHttpUtilException BREAKPOINT_EXPIRED() {
        return new OkHttpUtilException("breakpoint file has expired!");
    }
}
