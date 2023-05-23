package com.example.linedemo.exception;

import com.example.linedemo.constant.ErrorCode;
import lombok.Getter;

@Getter
public class GeneralException extends RuntimeException {
    private final ErrorCode errorCode;

    public GeneralException(){
        this.errorCode = ErrorCode.INTERNAL_ERROR;
    }

    public GeneralException(String message){
        super(message);
        this.errorCode = ErrorCode.INTERNAL_ERROR;
    }
    public GeneralException(String message, Throwable throwable){
        super(message,throwable);
        this.errorCode = ErrorCode.INTERNAL_ERROR;
    }

    public GeneralException(Throwable throwable){
        super(throwable);
        this.errorCode = ErrorCode.INTERNAL_ERROR;
    }

    public GeneralException(String message, Throwable throwable, boolean enableSuppression, boolean writableStackTrace){
        super(message,throwable,enableSuppression,writableStackTrace);
        this.errorCode = ErrorCode.INTERNAL_ERROR;
    }
    public GeneralException(ErrorCode errorCode) {
        this.errorCode = errorCode;
    }

    public GeneralException(ErrorCode errorCode, Throwable throwable){
        super(errorCode.getMessage(),throwable);
        this.errorCode = errorCode;
    }

    public GeneralException(ErrorCode errorCode, Throwable throwable, boolean enableSuppression, boolean writableStackTrace){
        super(errorCode.getMessage(),throwable,enableSuppression,writableStackTrace);
        this.errorCode = ErrorCode.INTERNAL_ERROR;
    }
}
