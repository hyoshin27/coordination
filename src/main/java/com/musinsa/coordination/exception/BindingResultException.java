package com.musinsa.coordination.exception;

import com.musinsa.coordination.type.ResponseCode;

public class BindingResultException extends AbstractException {

    public BindingResultException(String message) {
        super(ResponseCode.FAILURE, message);
    }
}
