package com.inetec.ichange.service.monitor.databean;

import com.avdy.p4j.jdbc.model.Column;
import com.avdy.p4j.jdbc.model.Entity;

import java.sql.Timestamp;

/**
 * ?υτ??????????
 * Created by IntelliJ IDEA.
 * User: bluesky
 * Date: 2010-9-5
 * Time: 10:10:35
 * To change this template use File | Settings | File Template
 */

@Entity(schema = "jksys", table = "sysabnormalinf")
public class Sysabnormalinf {
    @Column(isPrimaryKey = true)
    private long id;
    @Column
    private String abnormalTypeCode;
    @Column
    private String connectObjectCode;
    @Column
    private String exceptionIf;
    @Column
    private String exceptionDesc;
    @Column
    private String action;
    @Column
    private String status;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getAbnormalTypeCode() {
        return abnormalTypeCode;
    }

    public void setAbnormalTypeCode(String abnormalTypeCode) {
        this.abnormalTypeCode = abnormalTypeCode;
    }

    public String getConnectObjectCode() {
        return connectObjectCode;
    }

    public void setConnectObjectCode(String connectObjectCode) {
        this.connectObjectCode = connectObjectCode;
    }

    public String getExceptionIf() {
        return exceptionIf;
    }

    public void setExceptionIf(String exceptionIf) {
        this.exceptionIf = exceptionIf;
    }

    public String getExceptionDesc() {
        return exceptionDesc;
    }

    public void setExceptionDesc(String exceptionDesc) {
        this.exceptionDesc = exceptionDesc;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
