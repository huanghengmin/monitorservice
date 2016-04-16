package com.inetec.ichange.service.monitor.databean;

import com.avdy.p4j.jdbc.model.Column;
import com.avdy.p4j.jdbc.model.Entity;

/**
 * Created by IntelliJ IDEA.
 * User: bluesky
 * Date: 2010-11-14
 * Time: 14:07:33
 * To change this template use File | Settings | File Templates.
 */

@Entity(schema = "jksys", table = "exchange_platform")
public class ExchangePlatform extends BaseDataBean {
    @Column(isPrimaryKey = true)
    private int id;
    @Column
    private String platform_name;
    @Column
    private String platform_ip;
    @Column
    private int platform_port;

    @Column
    private String is_encrypted;

    @Column
    private String link_name;
    @Column
    private String certificate_path;
    @Column
    private String certificate_pwd;
    @Column
    private String memo;

    public String getPlatform_name() {
        return platform_name;
    }

    public void setPlatform_name(String platform_name) {
        this.platform_name = platform_name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIs_encrypted() {
        return is_encrypted;
    }

    public void setIs_encrypted(String is_encrypted) {
        this.is_encrypted = is_encrypted;
    }

    public int getPlatform_port() {
        return platform_port;
    }

    public void setPlatform_port(int platform_port) {
        this.platform_port = platform_port;
    }

    public String getPlatform_ip() {
        return platform_ip;
    }

    public void setPlatform_ip(String platform_ip) {
        this.platform_ip = platform_ip;
    }

    public String getLink_name() {
        return link_name;
    }

    public void setLink_name(String link_name) {
        this.link_name = link_name;
    }

    public String getCertificate_path() {
        return certificate_path;
    }

    public void setCertificate_path(String certificate_path) {
        this.certificate_path = certificate_path;
    }

    public String getCertificate_pwd() {
        return certificate_pwd;
    }

    public void setCertificate_pwd(String certificate_pwd) {
        this.certificate_pwd = certificate_pwd;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }
}
