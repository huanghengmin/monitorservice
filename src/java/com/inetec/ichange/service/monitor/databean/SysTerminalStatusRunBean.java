package com.inetec.ichange.service.monitor.databean;

import com.avdy.p4j.jdbc.model.Column;
import com.avdy.p4j.jdbc.model.Entity;

import java.sql.Timestamp;

/**
 * 终端在线用户运行状态表。
 * Created by IntelliJ IDEA.
 * User: bluesky
 * Date: 2010-9-5
 * Time: 10:10:35
 * To change this template use File | Settings | File Templates.
 */

@Entity(schema = "jksys", table = "systerminalstatus_run")
public class SysTerminalStatusRunBean {
    @Column(isPrimaryKey = true)
    private long id;
    @Column
    private int idTerminal;
    @Column
    private String policeNumber;
    @Column
    private String userdepart;
    @Column
    private String userId;
    @Column
    private String userZone;

    @Column
    private String busName;
    @Column
    private String cardType;
    @Column
    private String accessUrl;
    @Column
    private int count;
    @Column
    private int flux;

    @Column
    private Timestamp auditDate;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getIdTerminal() {
        return idTerminal;
    }

    public void setIdTerminal(int idTerminal) {
        this.idTerminal = idTerminal;
    }

    public String getPoliceNumber() {
        return policeNumber;
    }

    public void setPoliceNumber(String policeNumber) {
        this.policeNumber = policeNumber;
    }

    public String getUserdepart() {
        return userdepart;
    }

    public void setUserdepart(String userdepart) {
        this.userdepart = userdepart;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserZone() {
        return userZone;
    }

    public void setUserZone(String userZone) {
        this.userZone = userZone;
    }

    public String getBusName() {
        return busName;
    }

    public void setBusName(String busName) {
        this.busName = busName;
    }

    public String getCardType() {
        return cardType;
    }

    public void setCardType(String cardType) {
        this.cardType = cardType;
    }

    public String getAccessUrl() {
        return accessUrl;
    }

    public void setAccessUrl(String accessUrl) {
        this.accessUrl = accessUrl;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getFlux() {
        return flux;
    }

    public void setFlux(int flux) {
        this.flux = flux;
    }

    public Timestamp getAuditDate() {
        return auditDate;
    }

    public void setAuditDate(Timestamp auditDate) {
        this.auditDate = auditDate;
    }
}
