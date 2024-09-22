package com.musinsa.coordination.exception;

import com.musinsa.coordination.type.ResponseCode;

public class CategoryNotFoundException extends AbstractException {

    public CategoryNotFoundException(String message) {
        super(ResponseCode.FAILURE, message);
    }
}
