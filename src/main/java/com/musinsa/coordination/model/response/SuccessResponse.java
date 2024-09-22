package com.musinsa.coordination.model.response;

import com.musinsa.coordination.type.ResponseCode;
import lombok.Getter;

@Getter
public class SuccessResponse extends AbstractResponse {

    private final String msg;

    SuccessResponse(ResponseCode code, String message) {
        super(code);
        this.msg = message;
    }

    public static SuccessResponse from(String message) {

        return new SuccessResponse(ResponseCode.SUCCESS, message);
    }
}
