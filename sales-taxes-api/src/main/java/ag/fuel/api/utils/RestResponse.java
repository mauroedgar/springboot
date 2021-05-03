/*
 * Copyright (c) 2021 Fuel.ag.
 * All rights reserved.
 * https://fuel.ag
 */
package ag.fuel.api.utils;

import org.springframework.http.HttpStatus;

/**
 * @author Mauro Sousa
 */
public class RestResponse {

    int HttpCode;
    HttpStatus HttpStatus;
    Object data;
    String Message;


    public RestResponse() {
    }

    public RestResponse(Object data) {
        this.data = data;
    }

    public RestResponse(Object data, HttpStatus status) {
        this.data = data;
        this.HttpStatus = status;
    }

    public RestResponse(Object data, HttpStatus status, int code, String message) {
        this.data = data;
        this.HttpStatus = status;
        this.HttpCode = code;
        this.Message = message;
    }

    public int getHttpCode() {
        return HttpCode;
    }

    public void setHttpCode(int httpCode) {
        this.HttpCode = httpCode;
    }

    public HttpStatus getHttpStatus() {
        return HttpStatus;
    }

    public void setHttpStatus(HttpStatus httpStatus) {
        this.HttpStatus = httpStatus;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public String getMessage() {
        return Message;
    }

    public void setMessage(String message) {
        Message = message;
    }
}