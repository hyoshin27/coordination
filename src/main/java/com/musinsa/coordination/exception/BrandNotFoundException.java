package com.musinsa.coordination.exception;

import com.musinsa.coordination.type.ResponseCode;

public class BrandNotFoundException extends AbstractException {

    public BrandNotFoundException(String message) {
        super(ResponseCode.FAILURE, message);
    }
}
