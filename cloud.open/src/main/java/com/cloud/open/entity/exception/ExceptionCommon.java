package com.cloud.open.entity.exception;

import cn.egame.common.exception.ExceptionCommonBase;

public class ExceptionCommon extends ExceptionCommonBase {
    private static final long serialVersionUID = -3022530856069877446L;

    /**
     * @param errorCode
     */
    public ExceptionCommon(int errorCode) {
        super(errorCode);
    }

    public ExceptionCommon(int errorCode, String message) {
        super(errorCode, message);
    }

    public ExceptionCommon(int errorCode, String message, Throwable cause) {
        super(errorCode, message, cause);
    }

    public ExceptionCommon(int errorCode, Throwable cause) {
        super(errorCode, cause);
    }

    public static void throwExceptionCommon(Exception ex) throws ExceptionCommonBase {
        ExceptionCommonBase.throwExceptionCommonBase(ex);
    }
}
