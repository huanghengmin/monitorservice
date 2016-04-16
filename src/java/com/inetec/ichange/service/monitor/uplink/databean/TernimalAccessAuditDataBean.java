package com.inetec.ichange.service.monitor.uplink.databean;

import com.avdy.p4j.jdbc.model.Column;
import com.avdy.p4j.jdbc.model.Entity;

import java.util.Date;

/**
 * Created by IntelliJ IDEA.
 * User: Ç®ÏþÅÎ
 * Date: 12-2-7
 * Time: ÏÂÎç3:41
 * To change this template use File | Settings | File Templates.
 */
@Entity(schema = "jksys", table = "ternimal_access_audit")
public class TernimalAccessAuditDataBean {
     @Column(isPrimaryKey = true)
    private int id;
    @Column
    private String card_type;
    @Column
    private String police_id;
    @Column
    private String police_name;
    @Column
    private Date access_time;
    @Column
    private String message_level;
    @Column
    private String userid;
    @Column
    private String userdepart;

    @Column
    private int flux;
    @Column
    private String userzone;
    @Column
    private String busname;
    @Column
    private int count;

    @Column
    private String desc;


    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getUserdepart() {
        return userdepart;
    }

    public void setUserdepart(String userdepart) {
        this.userdepart = userdepart;
    }

    public long getFlux() {
        return flux;
    }

    public void setFlux(int flux) {
        this.flux = flux;
    }

    public String getUserzone() {
        return userzone;
    }

    public void setUserzone(String userzone) {
        this.userzone = userzone;
    }

    public String getBusname() {
        return busname;
    }

    public void setBusname(String busname) {
        this.busname = busname;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCard_type() {
        return card_type;
    }

    public void setCard_type(String card_type) {
        this.card_type = card_type;
    }

    public String getPolice_id() {
        return police_id;
    }

    public void setPolice_id(String police_id) {
        this.police_id = police_id;
    }

    public String getPolice_name() {
        return police_name;
    }

    public void setPolice_name(String police_name) {
        this.police_name = police_name;
    }

    public Date getAccess_time() {
        return access_time;
    }

    public void setAccess_time(Date access_time) {
        this.access_time = access_time;
    }

    public String getMessage_level() {
        return message_level;
    }

    public void setMessage_level(String message_level) {
        this.message_level = message_level;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
