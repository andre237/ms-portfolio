package com.andre.training.gateway;

import com.andre.training.gateway.response.RestResult;

public abstract class AbstractRestController {

    public <T> RestResult<T> getRestResult(T data) {
        return new RestResult<>(data);
    }

}
