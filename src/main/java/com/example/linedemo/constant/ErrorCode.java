package com.example.linedemo.constant;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Optional;
import java.util.function.Predicate;

@Getter
@RequiredArgsConstructor
public enum ErrorCode {
     OK(0,ErrorCategory.NORMAL,"OK"),

    BAD_REQUEST(10000,ErrorCategory.CLIENT_SIDE,"bad request"),
    SPRING_BAD_REQUEST(10001,ErrorCategory.CLIENT_SIDE,"Spring-detected bad request"),
    VALIDATION_ERROR(10002,ErrorCategory.CLIENT_SIDE,"Validation Error"),

    INTERNAL_ERROR(20000,ErrorCategory.SERVER_SIDE,"internal Error"),
    SPRING_INTERNAL_ERROR(20001,ErrorCategory.SERVER_SIDE,"Spring-detected internal error");

    private final Integer code;
    private final ErrorCategory errorCategory;
    private final String message;

    public String getMessage(Exception e){
        return getMessage(e.getMessage());
    }

    public String getMessage(String message) {
        return Optional.ofNullable(message)
                .filter(Predicate.not(String::isBlank))
                .orElse(getMessage());
    }

    //클라이언트의 오류인가?
    public boolean isClientSideError(){
        return this.getErrorCategory() == ErrorCategory.CLIENT_SIDE;
    }

    //서버의 오류인가?
    public boolean isServerSideError(){
        return this.getErrorCategory() == ErrorCategory.SERVER_SIDE;
    }

    @Override
    public String toString(){
        return String.format("%s (%d)",name(),this.getCode());
    }

    public enum ErrorCategory{
        NORMAL,CLIENT_SIDE,SERVER_SIDE;
    }
}
