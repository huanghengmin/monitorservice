package com.inetec.ichange.service.monitor.uplink.databean;

import com.avdy.p4j.jdbc.model.Column;
import com.avdy.p4j.jdbc.model.Entity;

import java.sql.Timestamp;

/**
 * Created by IntelliJ IDEA.
 * User: Administrator
 * Date: 13-1-29
 * Time: ÏÂÎç3:44
 * To change this template use File | Settings | File Templates.
 */
@Entity(schema = "jksys", table = "intherate")
public class IntherateDateBean {
    @Column(isPrimaryKey = true)
    private int id;
    @Column
    private String idsystem;
    @Column
    private Timestamp intime;
    @Column
    private String user;
    @Column
    private String province;
    @Column
    private String userid;
    @Column
    private String city;
    @Column
    private String organization;
    @Column
    private String institutions;
    @Column
    private String serialnumber;
    @Column
    private int    loginnum;

    public int getLoginnum() {
        return loginnum;
    }

    public void setLoginnum(int loginnum) {
        this.loginnum = loginnum;
    }

    public String getSerialnumber() {
        return serialnumber;
    }

    public void setSerialnumber(String serialnumber) {
        this.serialnumber = serialnumber;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIdsystem() {
        return idsystem;
    }

    public void setIdsystem(String idsystem) {
        this.idsystem = idsystem;
    }

    public Timestamp getIntime() {
        return intime;
    }

    public void setIntime(Timestamp intime) {
        this.intime = intime;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getOrganization() {
        return organization;
    }

    public void setOrganization(String organization) {
        this.organization = organization;
    }

    public String getInstitutions() {
        return institutions;
    }

    public void setInstitutions(String institutions) {
        this.institutions = institutions;
    }
}
