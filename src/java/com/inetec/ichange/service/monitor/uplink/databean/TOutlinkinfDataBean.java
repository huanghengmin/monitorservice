package com.inetec.ichange.service.monitor.uplink.databean;

import com.avdy.p4j.jdbc.model.Column;
import com.avdy.p4j.jdbc.model.Entity;

/**
 * Created by IntelliJ IDEA.
 * User: bluesky
 * Date: 11-4-29
 * Time: 上午11:31
 * To change this template use File | Settings | File Templates.
 */
@Entity(schema = "jksys", table = "ext_link")
public class TOutlinkinfDataBean {
    @Column(isPrimaryKey = true)
    private int id;
    @Column
    private String link_name;
    @Column
    private String link_property;
    @Column
    private String link_type;
    @Column
    private int link_bandwidth;
    @Column
    private String link_Corp;
    @Column
    private String link_security;
    @Column
    private int link_amount;
    @Column
    private String other_security;
    @Column
    private String ip;
    @Column
    private String mask;
    @Column
    private String gateway;

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getMask() {
        return mask;
    }

    public void setMask(String mask) {
        this.mask = mask;
    }

    public String getGateway() {
        return gateway;
    }

    public void setGateway(String gateway) {
        this.gateway = gateway;
    }

    public int getId() {
        return id;
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

    public String getLink_type() {
        return link_type;
    }

    public void setLink_type(String link_type) {
        this.link_type = link_type;
    }

    public int getLink_bandwidth() {
        return link_bandwidth;
    }

    public void setLink_bandwidth(int link_bandwidth) {
        this.link_bandwidth = link_bandwidth;
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

    public int getLink_amount() {
        return link_amount;
    }

    public void setLink_amount(int link_amount) {
        this.link_amount = link_amount;
    }

    public String getOther_security() {
        return other_security;
    }

    public void setOther_security(String other_security) {
        this.other_security = other_security;
    }

    public void setId(int id) {
        this.id = id;
    }

}
