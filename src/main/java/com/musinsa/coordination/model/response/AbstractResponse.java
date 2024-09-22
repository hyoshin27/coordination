package com.musinsa.coordination.model.response;

import com.musinsa.coordination.type.ResponseCode;
import lombok.Getter;

@Getter
public abstract class AbstractResponse {

    private final ResponseCode code;

    AbstractResponse(ResponseCode code) {
        this.code = code;
    }
}
