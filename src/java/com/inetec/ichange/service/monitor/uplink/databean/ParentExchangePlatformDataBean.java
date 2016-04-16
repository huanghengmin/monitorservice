package com.inetec.ichange.service.monitor.uplink.databean;

import com.avdy.p4j.jdbc.model.Column;
import com.avdy.p4j.jdbc.model.Entity;

/**
 * Created by IntelliJ IDEA.
 * User: Ç®ÏþÅÎ
 * Date: 12-2-7
 * Time: ÏÂÎç3:41
 * To change this template use File | Settings | File Templates.
 */
@Entity(schema = "jksys", table = "parent_exchange_platform")
public class ParentExchangePlatformDataBean {
     @Column(isPrimaryKey = true)
    private Long id;
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
    @Column
    private String enablereport;
    @Column
    private String address;
    @Column
    private String hour;
    @Column
    private String minute;
    @Column
    private String second;
    @Column
    private String timeType;
    @Column
    private String platform_mac;
    @Column
    private String name;
    @Column
    private String pass;
    @Column
    private String type;

    public String getTimeType() {
        return timeType;
    }

    public void setTimeType(String timeType) {
        this.timeType = timeType;
    }

    public String getPlatform_mac() {
        return platform_mac;
    }

    public void setPlatform_mac(String platform_mac) {
        this.platform_mac = platform_mac;
    }

    public String getHour() {
        return hour;
    }

    public void setHour(String hour) {
        this.hour = hour;
    }

    public String getMinute() {
        return minute;
    }

    public void setMinute(String minute) {
        this.minute = minute;
    }

    public String getSecond() {
        return second;
    }

    public void setSecond(String second) {
        this.second = second;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPlatform_name() {
        return platform_name;
    }

    public void setPlatform_name(String platform_name) {
        this.platform_name = platform_name;
    }

    public String getPlatform_ip() {
        return platform_ip;
    }

    public void setPlatform_ip(String platform_ip) {
        this.platform_ip = platform_ip;
    }

    public int getPlatform_port() {
        return platform_port;
    }

    public void setPlatform_port(int platform_port) {
        this.platform_port = platform_port;
    }

    public String getIs_encrypted() {
        return is_encrypted;
    }

    public void setIs_encrypted(String is_encrypted) {
        this.is_encrypted = is_encrypted;
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

    public String isEnablereport() {
        return enablereport;
    }

    public void setEnablereport(String enablereport) {
        this.enablereport = enablereport;

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
