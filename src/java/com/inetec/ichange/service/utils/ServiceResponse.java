package com.inetec.ichange.service.utils;

/**
 * Created by IntelliJ IDEA.
 * User: Administrator
 * Date: 12-11-29
 * Time: ����12:12
 * To change this template use File | Settings | File Templates.
 */
public class ServiceResponse {
    /**
     * ��Ӧ���룺200 400 500
     */
    int code;

    /**
     * json��Ӧ����
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
