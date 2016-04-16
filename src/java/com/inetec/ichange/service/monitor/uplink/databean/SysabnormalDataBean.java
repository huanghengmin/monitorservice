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
@Entity(schema = "jksys", table = "sysabnormal")
public class SysabnormalDataBean {
     @Column(isPrimaryKey = true)
    private int id;
    @Column
    private int idalertmatter;
    @Column
    private String abnormaltypecode;
    @Column
    private String connectobjectcode;
    @Column
    private String exceptiondesc;
    @Column
    private Timestamp happentime;
    @Column
    private Timestamp treattime;
    @Column
    private String treadresult;
    @Column
    private Timestamp writetime;
    @Column
    private String idsystem;
    @Column
    private String username;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public void setHappentime(Timestamp happentime) {
        this.happentime = happentime;
    }

    public Timestamp getTreattime() {
        return treattime;
    }

    public void setTreattime(Timestamp treattime) {
        this.treattime = treattime;
    }

    public String getTreadresult() {
        return treadresult;
    }

    public void setTreadresult(String treadresult) {
        this.treadresult = treadresult;
    }

    public Timestamp getWritetime() {
        return writetime;
    }

    public void setWritetime(Timestamp writetime) {
        this.writetime = writetime;
    }

    public String getIdsystem() {
        return idsystem;
    }

    public void setIdsystem(String idsystem) {
        this.idsystem = idsystem;
    }
}
