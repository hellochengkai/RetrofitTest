package com.thunder.ktv.retrofittest.net;

public class ResultModel<T> {

    private String status;//200成功
    private String message;
    private T data;

    public String getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    public T getData() {
        return data;
    }
}
