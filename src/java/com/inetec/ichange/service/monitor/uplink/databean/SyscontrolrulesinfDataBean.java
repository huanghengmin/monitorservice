package com.inetec.ichange.service.monitor.uplink.databean;

import com.avdy.p4j.jdbc.model.Column;
import com.avdy.p4j.jdbc.model.Entity;

import java.sql.Timestamp;
import java.util.Date;

/**
 * Created by IntelliJ IDEA.
 * User: qxp
 * Date: 12-2-7
 * Time: am 3:41
 * To change this template use File | Settings | File Templates.
 */
@Entity(schema = "jksys", table = "syscontrolrulesinf")
public class SyscontrolrulesinfDataBean {
    @Column(isPrimaryKey = true)
    private int id;
    @Column
    private String idSystem;
    @Column
    private Long idControlRules;
    @Column
    private String filename;
    @Column
    private String controldesc;
    @Column
    private Timestamp collectTime;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIdSystem() {
        return idSystem;
    }

    public void setIdSystem(String idSystem) {
        this.idSystem = idSystem;
    }

    public String getFileName() {
        return filename;
    }

    public void setFileName(String fileName) {
        this.filename = fileName;
    }

    public String getDesc() {
        return controldesc;
    }

    public void setDesc(String desc) {
        this.controldesc = desc;
    }

    public Timestamp getCollectTime() {
        return collectTime;
    }

    public void setCollectTime(Timestamp collectTime) {
        this.collectTime = collectTime;
    }

    public Long getIdControlRules() {
        return idControlRules;
    }

    public void setIdControlRules(Long idControlRules) {
        this.idControlRules = idControlRules;
    }
}
