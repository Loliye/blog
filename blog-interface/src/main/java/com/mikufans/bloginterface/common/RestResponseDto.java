package com.mikufans.bloginterface.common;

import lombok.extern.slf4j.Slf4j;

@Slf4j

public class RestResponseDto<T> {

    /**
     * 服务器响应数据
     */
    private T payload;

    /**
     * 请求是否成功
     */
    private boolean success;

    /**
     * 错误信息
     */
    private String msg;

    /**
     * 状态码
     */
    private int code = -1;

    /**
     * 服务器响应时间
     */
    private long timestamp;

    public RestResponseDto() {
        this.timestamp = System.currentTimeMillis() / 1000;
    }

    public RestResponseDto(boolean success) {
        this.timestamp = System.currentTimeMillis() / 1000;
        this.success = success;
    }

    public RestResponseDto(boolean success, T payload) {
        this.timestamp = System.currentTimeMillis() / 1000;
        this.success = success;
        this.payload = payload;
    }

    public RestResponseDto(boolean success, T payload, int code) {
        this.timestamp = System.currentTimeMillis() / 1000;
        this.success = success;
        this.payload = payload;
        this.code = code;
    }

    public RestResponseDto(boolean success, String msg) {
        this.timestamp = System.currentTimeMillis() / 1000;
        this.success = success;
        this.msg = msg;
    }

    public RestResponseDto(boolean success, String msg, int code) {
        this.timestamp = System.currentTimeMillis() / 1000;
        this.success = success;
        this.msg = msg;
        this.code = code;
    }

    public T getPayload() {
        return payload;
    }

    public void setPayload(T payload) {
        this.payload = payload;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public static RestResponseDto ok() {
        return new RestResponseDto(true);
    }

    public static <T> RestResponseDto ok(T payload) {
        return new RestResponseDto(true, payload);
    }

    public static <T> RestResponseDto ok(int code) {
        return new RestResponseDto(true, null, code);
    }

    public static <T> RestResponseDto ok(T payload, int code) {
        return new RestResponseDto(true, payload, code);
    }

    public static RestResponseDto fail() {
        return new RestResponseDto(false);
    }

    public static RestResponseDto fail(String msg) {
        return new RestResponseDto(false, msg);
    }

    public static RestResponseDto fail(int code) {
        return new RestResponseDto(false, null, code);
    }

    public static RestResponseDto fail(int code, String msg) {
        return new RestResponseDto(false, msg, code);
    }

}