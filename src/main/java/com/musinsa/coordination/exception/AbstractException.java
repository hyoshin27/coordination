package com.musinsa.coordination.exception;

import com.musinsa.coordination.model.response.ErrorResponse;
import com.musinsa.coordination.type.ResponseCode;

public abstract class AbstractException extends RuntimeException {
    private final ResponseCode responseCode;

    AbstractException(ResponseCode responseCode, String message) {
        super(message);
        this.responseCode = responseCode;
    }

    public ErrorResponse getErrorResponse() {
        return ErrorResponse.from(responseCode, getMessage());
    }
}
