package com.inetec.ichange.service.monitor.databean;

import com.avdy.p4j.jdbc.model.Column;
import com.avdy.p4j.jdbc.model.Entity;

import java.sql.Time;
import java.sql.Timestamp;

/**
 * Created by IntelliJ IDEA.
 * User: bluesky
 * Date: 2010-9-5
 * Time: 10:10:35
 * To change this template use File | Settings | File Templates.
 */

@Entity(schema = "jksys", table = "equipment_log")
public class EquipmentLog extends BaseDataBean {
    @Column(isPrimaryKey = true)
    private int id;
    @Column
    private Timestamp log_time;
    @Column
    private String level;
    @Column
    private String link_name;
    @Column
    private String equipment_name;
    @Column
    private String log_info;
    @Column
    private String link_property;
    @Column
    private String link_type;
    @Column
    private String link_Corp;
    @Column
    private String link_security;
    @Column
    private long link_amount;
    @Column
    private long link_bandwidth;
    @Column
    private String other_security;

    private String code="";

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    private String ip;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Timestamp getLog_time() {
        return log_time;
    }

    public void setLog_time(Timestamp log_time) {
        this.log_time = log_time;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getEquipment_name() {
        return equipment_name;
    }

    public void setEquipment_name(String equipment_name) {
        this.equipment_name = equipment_name;
    }

    public String getLink_name() {
        return link_name;
    }

    public void setLink_name(String link_name) {
        this.link_name = link_name;
    }

    public String getLink_property() {
        return link_property;
    }

    public void setLink_property(String link_property) {
        this.link_property = link_property;
    }

    public String getLog_info() {
        return log_info;
    }

    public void setLog_info(String log_info) {
        this.log_info = log_info;
    }

    public String getLink_type() {
        return link_type;
    }

    public void setLink_type(String link_type) {
        this.link_type = link_type;
    }

    public String getLink_Corp() {
        return link_Corp;
    }

    public void setLink_Corp(String link_Corp) {
        this.link_Corp = link_Corp;
    }

    public String getLink_security() {
        return link_security;
    }

    public void setLink_security(String link_security) {
        this.link_security = link_security;
    }

    public long getLink_amount() {
        return link_amount;
    }

    public void setLink_amount(long link_amount) {
        this.link_amount = link_amount;
    }

    public long getLink_bandwidth() {
        return link_bandwidth;
    }

    public void setLink_bandwidth(long link_bandwidth) {
        this.link_bandwidth = link_bandwidth;
    }

    public String getOther_security() {
        return other_security;
    }

    public void setOther_security(String other_security) {
        this.other_security = other_security;
    }


}
