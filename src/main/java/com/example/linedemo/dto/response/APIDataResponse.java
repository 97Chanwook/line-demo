package com.example.linedemo.dto.response;

import com.example.linedemo.constant.ErrorCode;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@EqualsAndHashCode(callSuper = true)
public class APIDataResponse<T> extends APIErrorResponse {
    private T data;

    private APIDataResponse(T data){
        super(true, ErrorCode.OK.getCode(),ErrorCode.OK.getMessage());
        this.data = data;
    }

    public static <T> APIDataResponse<T> of(T data){
        return new APIDataResponse<> (data);
    }
}
