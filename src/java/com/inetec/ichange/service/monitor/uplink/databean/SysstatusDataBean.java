package com.inetec.ichange.service.monitor.uplink.databean;

import com.avdy.p4j.jdbc.model.Column;
import com.avdy.p4j.jdbc.model.Entity;

import java.sql.Date;
import java.sql.Timestamp;

/**
 * Created by IntelliJ IDEA.
 * User: qxp?
 * Date: 12-2-7
 * Time: pm 3:41
 * To change this template use File | Settings | File Templates.
 */
@Entity(schema = "jksys", table = "sysstatus")
public class SysstatusDataBean {
     @Column(isPrimaryKey = true)
    private int id;
    @Column
    private String idsystem;
    @Column
    private int access;
    @Column
    private int terminalnum;
    @Column
    private long influx;
    @Column
    private long outflux;
    @Column
    private int accesssum;
    @Column
    private int bizsum;
    @Column
    private Timestamp starttime;
    @Column
    private Timestamp endtime;
    @Column
    private Timestamp writetime;

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

    public int getAccess() {
        return access;
    }

    public void setAccess(int access) {
        this.access = access;
    }

    public int getTerminalnum() {
        return terminalnum;
    }

    public void setTerminalnum(int terminalnum) {
        this.terminalnum = terminalnum;
    }

    public long getInflux() {
        return influx;
    }

    public void setInflux(long influx) {
        this.influx = influx;
    }

    public long getOutflux() {
        return outflux;
    }

    public void setOutflux(long outflux) {
        this.outflux = outflux;
    }

    public int getAccesssum() {
        return accesssum;
    }

    public void setAccesssum(int accesssum) {
        this.accesssum = accesssum;
    }

    public long getBizsum() {
        return bizsum;
    }

    public void setBizsum(int bizsum) {
        this.bizsum = bizsum;
    }

    public Timestamp getStarttime() {
        return starttime;
    }

    public void setStarttime(Timestamp starttime) {
        this.starttime = starttime;
    }

    public Timestamp getEndtime() {
        return endtime;
    }

    public void setEndtime(Timestamp endtime) {
        this.endtime = endtime;
    }

    public Timestamp getWritetime() {
        return writetime;
    }

    public void setWritetime(Timestamp writetime) {
        this.writetime = writetime;
    }
}
