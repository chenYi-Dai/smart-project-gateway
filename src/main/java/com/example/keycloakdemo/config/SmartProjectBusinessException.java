package com.example.keycloakdemo.config;

import com.example.keycloakdemo.enums.SmartProjectBusinessExceptionEnum;

import java.io.Serializable;

/**
 * @author: 石恒
 * @DateTime: 2019-09-04 17:34
 * @qq: 374696376
 * @Description:
 */

public class SmartProjectBusinessException extends RuntimeException implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 异常
     */
    private SmartProjectBusinessExceptionEnum exception;


    public SmartProjectBusinessException(String message) {
        super(message);
    }

    public SmartProjectBusinessException(SmartProjectBusinessExceptionEnum exception) {
        super(exception.getMsg());
        this.exception = exception;
    }

    public SmartProjectBusinessException(SmartProjectBusinessExceptionEnum exception, String msg) {
        super(msg);
        this.exception = exception;
    }


    public SmartProjectBusinessExceptionEnum getException() {
        return exception;
    }

    public void setException(SmartProjectBusinessExceptionEnum exception) {
        this.exception = exception;
    }


}
