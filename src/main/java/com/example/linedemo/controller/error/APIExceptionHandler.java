package com.example.linedemo.controller.error;

import com.example.linedemo.constant.ErrorCode;
import com.example.linedemo.dto.response.APIErrorResponse;
import com.example.linedemo.exception.GeneralException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.validation.ConstraintViolationException;

// RestController를 사용하는 모든 컨트롤러를 대상
// 응답이 ResponseBody 어노테이션 사용
@RestControllerAdvice(annotations = RestController.class)
public class APIExceptionHandler extends ResponseEntityExceptionHandler {
    //ResponseEntityExceptionHandler -> spring-boot 에서 일어나는 오류를 잡아주는 핸들링 클래스 상속

    // @Validation 어노테이션의 오류를 잡아주는 Exception Handler
    @ExceptionHandler
    public ResponseEntity<Object> general(ConstraintViolationException e, WebRequest request){
        ErrorCode errorCode = ErrorCode.VALIDATION_ERROR;
        HttpStatus status = HttpStatus.BAD_REQUEST;

        return super.handleExceptionInternal(
                e,
                APIErrorResponse.of(false,errorCode,errorCode.getMessage()),
                HttpHeaders.EMPTY,
                status,
                request
        );
    }

    //GeneralExceoption 이 발생했을 경우 잡아주는 핸들러
    @ExceptionHandler
    public ResponseEntity<Object> general(GeneralException e, WebRequest request){
        ErrorCode errorCode = e.getErrorCode();

        HttpStatus status = errorCode.isClientSideError() ?
                HttpStatus.BAD_REQUEST : HttpStatus.INTERNAL_SERVER_ERROR;


        return super.handleExceptionInternal(
                e,
                APIErrorResponse.of(false,errorCode,errorCode.getMessage(e)),
                HttpHeaders.EMPTY,
                status,
                request);
    }

    //Spring-boot 내에서 일어나는 오류를 핸들링 하는 핸들러
    @Override
    protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers, HttpStatus status, WebRequest request){
        ErrorCode errorCode = status.is4xxClientError() ?
                ErrorCode.SPRING_BAD_REQUEST : ErrorCode.SPRING_INTERNAL_ERROR;

        return super.handleExceptionInternal(
                ex,
                APIErrorResponse.of(false,errorCode,errorCode.getMessage(ex)),
                headers,
                status,
                request
        );
    }

    // 그 외 모든 Exception 에 대한 Exception Handler
    @ExceptionHandler
    public ResponseEntity<Object> exception(Exception e, WebRequest request){
        ErrorCode errorCode = ErrorCode.INTERNAL_ERROR;
        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;

        return super.handleExceptionInternal(
                e,
                APIErrorResponse.of(false,errorCode,errorCode.getMessage(e)),
                HttpHeaders.EMPTY,
                status,
                request
        );
    }
}
