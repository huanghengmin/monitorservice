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
@Entity(schema = "jksys", table = "equipment")
public class EquipmentDataBean {
     @Column(isPrimaryKey = true)
    private int id;
    @Column
    private String equ_name;
    @Column
    private int is_key_device;
    @Column
    private String equ_type;
    @Column
    private String equ_icon_code;
    @Column
    private String net_station;
    @Column
    private String monitor_used;
    @Column
    private String ip;
    @Column
    private String other_ip;
    @Column
    private String mac;
    @Column
    private String subnet_mask;
    @Column
    private String int_or_ext;
    @Column
    private String link_name;
    @Column
    private String equ_station;
    @Column
    private String equ_info;
    @Column
    private String manufacturer;
    @Column
    private String model;
    @Column
    private String provider;
    @Column
    private String equ_phone;
    @Column
    private String other_phone;
    @Column
    private Date buy_day;
    @Column
    private Date unrepair_day;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEqu_name() {
        return equ_name;
    }

    public void setEqu_name(String equ_name) {
        this.equ_name = equ_name;
    }

    public int getKeyDevice() {
        return is_key_device;
    }

    public void setKeyDevice(int keyDevice) {
        is_key_device = keyDevice;
    }

    public String getEqu_type() {
        return equ_type;
    }

    public void setEqu_type(String equ_type) {
        this.equ_type = equ_type;
    }

    public String getEqu_icon_code() {
        return equ_icon_code;
    }

    public void setEqu_icon_code(String equ_icon_code) {
        this.equ_icon_code = equ_icon_code;
    }

    public String getNet_station() {
        return net_station;
    }

    public void setNet_station(String net_station) {
        this.net_station = net_station;
    }

    public String getMonitor_used() {
        return monitor_used;
    }

    public void setMonitor_used(String monitor_used) {
        this.monitor_used = monitor_used;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getOther_ip() {
        return other_ip;
    }

    public void setOther_ip(String other_ip) {
        this.other_ip = other_ip;
    }

    public String getMac() {
        return mac;
    }

    public void setMac(String mac) {
        this.mac = mac;
    }

    public String getSubnet_mask() {
        return subnet_mask;
    }

    public void setSubnet_mask(String subnet_mask) {
        this.subnet_mask = subnet_mask;
    }

    public String getInt_or_ext() {
        return int_or_ext;
    }

    public void setInt_or_ext(String int_or_ext) {
        this.int_or_ext = int_or_ext;
    }

    public String getLink_name() {
        return link_name;
    }

    public void setLink_name(String link_name) {
        this.link_name = link_name;
    }

    public String getEqu_station() {
        return equ_station;
    }

    public void setEqu_station(String equ_station) {
        this.equ_station = equ_station;
    }

    public String getEqu_info() {
        return equ_info;
    }

    public void setEqu_info(String equ_info) {
        this.equ_info = equ_info;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getProvider() {
        return provider;
    }

    public void setProvider(String provider) {
        this.provider = provider;
    }

    public String getEqu_phone() {
        return equ_phone;
    }

    public void setEqu_phone(String equ_phone) {
        this.equ_phone = equ_phone;
    }

    public String getOther_phone() {
        return other_phone;
    }

    public void setOther_phone(String other_phone) {
        this.other_phone = other_phone;
    }

    public Date getBuy_day() {
        return buy_day;
    }

    public void setBuy_day(Date buy_day) {
        this.buy_day = buy_day;
    }

    public Date getUnrepair_day() {
        return unrepair_day;
    }

    public void setUnrepair_day(Date unrepair_day) {
        this.unrepair_day = unrepair_day;
    }
}
