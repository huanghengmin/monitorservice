package com.inetec.ichange.service.utils;

/**
 * Created by IntelliJ IDEA.
 * User: Administrator
 * Date: 12-11-29
 * Time: 下午12:12
 * To change this template use File | Settings | File Templates.
 */
public class ServiceResponse {
    /**
     * 响应代码：200 400 500
     */
    int code;

    /**
     * json响应数据
     */
    String data;

    public ServiceResponse(int code, String data) {
        super();
        this.code = code;
        this.data = data;
    }

    public ServiceResponse() {

    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
