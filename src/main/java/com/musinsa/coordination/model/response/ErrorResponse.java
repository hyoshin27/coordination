package com.musinsa.coordination.model.response;

import com.musinsa.coordination.type.ResponseCode;
import lombok.Getter;

@Getter
public class ErrorResponse extends AbstractResponse {

    private final String errorMsg;

    ErrorResponse(ResponseCode code, String error) {
        super(code);
        this.errorMsg = error;
    }

    public static ErrorResponse from(ResponseCode code, String error) {

        return new ErrorResponse(code, error);
    }
}
