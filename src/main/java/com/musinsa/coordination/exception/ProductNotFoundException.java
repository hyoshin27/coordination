package com.musinsa.coordination.exception;

import com.musinsa.coordination.type.ResponseCode;

public class ProductNotFoundException extends AbstractException {

    public ProductNotFoundException(String message) {
        super(ResponseCode.FAILURE, message);
    }
}
