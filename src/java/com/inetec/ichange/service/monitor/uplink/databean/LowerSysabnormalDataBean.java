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
@Entity(schema = "jksys", table = "lower_sysabnormal")
public class LowerSysabnormalDataBean {
     @Column(isPrimaryKey = true)
    private int id;
    @Column
    private String idsystem;
    @Column
    private int idalertmatter;
    @Column
    private String abnormaltypecode;
    @Column
    private String connectobjectcode;
    @Column
    private String exceptiondesc;
    @Column
    private Date happentime;
    @Column
    private Date treattime;
    @Column
    private String treadresult;
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

    public int getIdalertmatter() {
        return idalertmatter;
    }

    public void setIdalertmatter(int idalertmatter) {
        this.idalertmatter = idalertmatter;
    }

    public String getAbnormaltypecode() {
        return abnormaltypecode;
    }

    public void setAbnormaltypecode(String abnormaltypecode) {
        this.abnormaltypecode = abnormaltypecode;
    }

    public String getConnectobjectcode() {
        return connectobjectcode;
    }

    public void setConnectobjectcode(String connectobjectcode) {
        this.connectobjectcode = connectobjectcode;
    }

    public String getExceptiondesc() {
        return exceptiondesc;
    }

    public void setExceptiondesc(String exceptiondesc) {
        this.exceptiondesc = exceptiondesc;
    }

    public Date getHappentime() {
        return happentime;
    }

    public void setHappentime(Date happentime) {
        this.happentime = happentime;
    }

    public Date getTreattime() {
        return treattime;
    }

    public void setTreattime(Date treattime) {
        this.treattime = treattime;
    }

    public String getTreadresult() {
        return treadresult;
    }

    public void setTreadresult(String treadresult) {
        this.treadresult = treadresult;
    }

    public Date getWritetime() {
        return writetime;
    }

    public void setWritetime(Date writetime) {
        this.writetime = writetime;
    }
}
