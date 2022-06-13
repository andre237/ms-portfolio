package com.andre.training.gateway.response;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class RestResult<T> implements Serializable {

    private Date timestamp;
    private T data;
    private String error;

    public RestResult() {
        this.timestamp = new Date();
    }

    public RestResult(T data) {
        this();
        this.data = data;
    }

}
