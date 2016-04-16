package com.inetec.ichange.service.monitor.databean;

import com.avdy.p4j.jdbc.model.Column;
import com.avdy.p4j.jdbc.model.Entity;

import java.sql.Timestamp;

/**
 * Created by IntelliJ IDEA.
 * User: bluesky
 * Date: 2010-11-14
 * Time: 13:40:39
 * To change this template use File | Settings | File Templates.
 */
@Entity(schema = "jksys", table = "business_log")

public class BusinessLog extends BaseDataBean {
    @Column(isPrimaryKey = true)
    private int id;
    @Column
    private String level;
    @Column
    private Timestamp log_time;
    @Column
    private String business_name;
    @Column
    private String platform_name;
    @Column
    private String audit_info;
    @Column
    private String source_ip;
    @Column
    private String source_dest;
    @Column
    private String dest_ip;
    @Column
    private String dest_port;
    @Column
    private String user_name;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Column

    private String operation;
    private String code="0004";

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getBusiness_name() {
        return business_name;
    }

    public void setBusiness_name(String business_name) {
        this.business_name = business_name;
    }

    public Timestamp getLog_time() {
        return log_time;
    }

    public void setLog_time(Timestamp log_time) {
        this.log_time = log_time;
    }

    public String getAudit_info() {
        return audit_info;
    }

    public void setAudit_info(String audit_info) {
        this.audit_info = audit_info;
    }

    public String getPlatform_name() {
        return platform_name;
    }

    public void setPlatform_name(String platform_name) {
        this.platform_name = platform_name;
    }

    public String getSource_ip() {
        return source_ip;
    }

    public void setSource_ip(String source_ip) {
        this.source_ip = source_ip;
    }

    public String getSource_dest() {
        return source_dest;
    }

    public void setSource_dest(String source_dest) {
        this.source_dest = source_dest;
    }

    public String getDest_port() {
        return dest_port;
    }

    public void setDest_port(String dest_port) {
        this.dest_port = dest_port;
    }

    public String getDest_ip() {
        return dest_ip;
    }

    public void setDest_ip(String dest_ip) {
        this.dest_ip = dest_ip;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }
}
