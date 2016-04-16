package com.inetec.ichange.service.monitor.databean;

import com.avdy.p4j.jdbc.model.Column;
import com.avdy.p4j.jdbc.model.Entity;

import java.sql.Date;
import java.sql.Timestamp;

/**
 * Created by IntelliJ IDEA.
 * User: bluesky
 * Date: 2010-11-14
 * Time: 20:09:11
 * To change this template use File | Settings | File Templates.
 */
@Entity(schema = "jksys", table = "business_except_alert")
public class BusinessExceptAlert extends BaseDataBean {
    @Column(isPrimaryKey = true)
    private int id;
    @Column
    private String business_name;
    @Column
    private Timestamp alert_time;
    @Column
    private String except_code;
    @Column
    private String alert_info;
    @Column
    private String isread = "N";
    @Column
    private String ip;
    @Column
    private String user_name;

    public String getIsread() {
        return isread;
    }

    public void setIsread(String isread) {
        this.isread = isread;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBusiness_name() {
        return business_name;
    }

    public void setBusiness_name(String business_name) {
        this.business_name = business_name;
    }

    public Timestamp getAlert_time() {
        return alert_time;
    }

    public void setAlert_time(Timestamp alert_time) {
        this.alert_time = alert_time;
    }

    public String getExcept_code() {
        return except_code;
    }

    public void setExcept_code(String except_code) {
        this.except_code = except_code;
    }

    public String getAlert_info() {
        return alert_info;
    }

    public void setAlert_info(String alert_info) {
        this.alert_info = alert_info;
    }
}
