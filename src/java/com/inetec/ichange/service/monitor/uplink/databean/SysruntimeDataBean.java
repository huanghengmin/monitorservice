package com.inetec.ichange.service.monitor.uplink.databean;

import com.avdy.p4j.jdbc.model.Column;
import com.avdy.p4j.jdbc.model.Entity;

import java.sql.Date;
import java.sql.Timestamp;

/**
 * Created by IntelliJ IDEA.
 * User: qxp?
 * Date: 12-2-7
 * Time: pm3:41
 * To change this template use File | Settings | File Templates.
 */
@Entity(schema = "jksys", table = "sysruntime")
public class SysruntimeDataBean {
     @Column(isPrimaryKey = true)
    private int id;
    @Column
    private String idsystem;
    @Column
    private String runstatecode;
    @Column
    private String desc;
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

    public String getRunstatecode() {
        return runstatecode;
    }

    public void setRunstatecode(String runstatecode) {
        this.runstatecode = runstatecode;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
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
