package com.inetec.ichange.service.monitor.uplink.databean;

import com.avdy.p4j.jdbc.model.Column;
import com.avdy.p4j.jdbc.model.Entity;

import java.util.Date;

/**
 * Created by IntelliJ IDEA.
 * User: qxp?
 * Date: 12-2-7
 * Time: pm 3:41
 * To change this template use File | Settings | File Templates.
 */
@Entity(schema = "jksys", table = "sysstrategyinf")
public class SysstrategyinfDataBean {
     @Column(isPrimaryKey = true)
    private int id;
    @Column
    private int id_strategy;
    @Column
    private String id_system;
    @Column
    private String strategy_desc;
    @Column
    private String strategy_protocal_type;
    @Column
    private String scr_ip;
    @Column
    private String dest_ip;
    @Column
    private String src_port;
    @Column
    private String dest_port;
    @Column
    private Date collect_time;
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_strategy() {
        return id_strategy;
    }

    public void setId_strategy(int id_strategy) {
        this.id_strategy = id_strategy;
    }

    public String getId_system() {
        return id_system;
    }

    public void setId_system(String id_system) {
        this.id_system = id_system;
    }

    public String getStrategy_desc() {
        return strategy_desc;
    }

    public void setStrategy_desc(String strategy_desc) {
        this.strategy_desc = strategy_desc;
    }

    public String getStrategy_protocal_type() {
        return strategy_protocal_type;
    }

    public void setStrategy_protocal_type(String strategy_protocal_type) {
        this.strategy_protocal_type = strategy_protocal_type;
    }

    public String getScr_ip() {
        return scr_ip;
    }

    public void setScr_ip(String scr_ip) {
        this.scr_ip = scr_ip;
    }

    public String getDest_ip() {
        return dest_ip;
    }

    public void setDest_ip(String dest_ip) {
        this.dest_ip = dest_ip;
    }

    public String getSrc_port() {
        return src_port;
    }

    public void setSrc_port(String src_port) {
        this.src_port = src_port;
    }

    public String getDest_port() {
        return dest_port;
    }

    public void setDest_port(String dest_port) {
        this.dest_port = dest_port;
    }

    public Date getCollect_time() {
        return collect_time;
    }

    public void setCollect_time(Date collect_time) {
        this.collect_time = collect_time;
    }
}
