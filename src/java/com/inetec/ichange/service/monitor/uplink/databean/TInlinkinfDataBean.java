package com.inetec.ichange.service.monitor.uplink.databean;

import com.avdy.p4j.jdbc.model.Column;
import com.avdy.p4j.jdbc.model.Entity;

import java.sql.Date;

/**
 * Created by IntelliJ IDEA.
 * User: bluesky
 * Date: 11-4-29
 * Time: 上午11:31
 * To change this template use File | Settings | File Templates.
 */
@Entity(schema = "jksys", table = "int_link")
public class TInlinkinfDataBean {
    @Column(isPrimaryKey = true)
    private int id;
    @Column
    private String link_name;
    @Column
    private String jrdx;
    @Column
    private String exchange_mode;
    @Column
    private int link_bandwidth;
    @Column
    private String FW_used;
    @Column
    private String sec_gateway_used;
    @Column
    private String gap_used;
    @Column
    private String VPN_used;
    @Column
    private String  other_security;


    public int getId() {
        return id;
    }

    public String getLink_name() {
        return link_name;
    }

    public void setLink_name(String link_name) {
        this.link_name = link_name;
    }

    public String getJrdx() {
        return jrdx;
    }

    public void setJrdx(String jrdx) {
        this.jrdx = jrdx;
    }

    public String getExchange_mode() {
        return exchange_mode;
    }

    public void setExchange_mode(String exchange_mode) {
        this.exchange_mode = exchange_mode;
    }

    public int getLink_bandwidth() {
        return link_bandwidth;
    }

    public void setLink_bandwidth(int link_bandwidth) {
        this.link_bandwidth = link_bandwidth;
    }

    public String getFW_used() {
        return FW_used;
    }

    public void setFW_used(String FW_used) {
        this.FW_used = FW_used;
    }

    public String getSec_gateway_used() {
        return sec_gateway_used;
    }

    public void setSec_gateway_used(String sec_gateway_used) {
        this.sec_gateway_used = sec_gateway_used;
    }

    public String getGap_used() {
        return gap_used;
    }

    public void setGap_used(String gap_used) {
        this.gap_used = gap_used;
    }

    public String getVPN_used() {
        return VPN_used;
    }

    public void setVPN_used(String VPN_used) {
        this.VPN_used = VPN_used;
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
