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
@Entity(schema = "jksys", table = "lower_sysbizstatus")
public class LowerSysbizstatusDataBean {
     @Column(isPrimaryKey = true)
    private int id;
    @Column
    private String idsystem;
    @Column
    private int idbiz ;
    @Column
    private String bizname;
    @Column
    private long access;
    @Column
    private long terminalnum;
    @Column
    private long influx;
    @Column
    private long outflux;
    @Column
    private long accesssum;
    @Column
    private Date starttime;
    @Column
    private Date endtime;
    @Column
    private Date writetime;

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
        return idbiz;
    }

    public void setIdbiz(int idbiz) {
        this.idbiz = idbiz;
    }

    public String getBizname() {
        return bizname;
    }

    public void setBizname(String bizname) {
        this.bizname = bizname;
    }

    public long getAccess() {
        return access;
    }

    public void setAccess(long access) {
        this.access = access;
    }

    public long getTerminalnum() {
        return terminalnum;
    }

    public void setTerminalnum(long terminalnum) {
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

    public long getAccesssum() {
        return accesssum;
    }

    public void setAccesssum(long accesssum) {
        this.accesssum = accesssum;
    }

    public Date getStarttime() {
        return starttime;
    }

    public void setStarttime(Date starttime) {
        this.starttime = starttime;
    }

    public Date getEndtime() {
        return endtime;
    }

    public void setEndtime(Date endtime) {
        this.endtime = endtime;
    }

    public Date getWritetime() {
        return writetime;
    }

    public void setWritetime(Date writetime) {
        this.writetime = writetime;
    }
}
