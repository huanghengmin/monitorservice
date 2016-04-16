package com.inetec.ichange.service.monitor.uplink.databean;

import com.avdy.p4j.jdbc.model.Column;
import com.avdy.p4j.jdbc.model.Entity;

import java.sql.Timestamp;
import java.util.Date;

/**
 * Created by IntelliJ IDEA.
 * User: Ç®ÏþÅÎ
 * Date: 12-2-7
 * Time: ÏÂÎç3:41
 * To change this template use File | Settings | File Templates.
 */
@Entity(schema = "jksys", table = "sysbizstatus")
public class SysbizstatusDataBean {
     @Column(isPrimaryKey = true)
    private int id;
    @Column
    private String idsystem;
    @Column
    private int Idbiz ;
    @Column
    private String bizname;
    @Column
    private int access;
    @Column
    private int terminalnum;
    @Column
    private int influx;
    @Column
    private int outflux;
    @Column
    private int accesssum;
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

    public int getIdbiz() {
        return Idbiz;
    }

    public void setIdbiz(int idbiz) {
        this.Idbiz = idbiz;
    }

    public String getBizname() {
        return bizname;
    }

    public void setBizname(String bizname) {
        this.bizname = bizname;
    }

    public int getAccess() {
        return access;
    }

    public void setAccess(int access) {
        this.access = access;
    }

    public int  getTerminalnum() {
        return terminalnum;
    }

    public void setTerminalnum(int terminalnum) {
        this.terminalnum = terminalnum;
    }

    public int getInflux() {
        return influx;
    }

    public void setInflux(int influx) {
        this.influx = influx;
    }

    public int getOutflux() {
        return outflux;
    }

    public void setOutflux(int outflux) {
        this.outflux = outflux;
    }

    public int getAccesssum() {
        return accesssum;
    }

    public void setAccesssum(int accesssum) {
        this.accesssum = accesssum;
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
