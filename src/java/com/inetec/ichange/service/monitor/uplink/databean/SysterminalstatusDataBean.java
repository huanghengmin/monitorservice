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
@Entity(schema = "jksys", table = "systerminalstatus")
public class SysterminalstatusDataBean {
     @Column(isPrimaryKey = true)
    private Long idTermianl;
    @Column
    private String idSystem;
    @Column
    private String isOnline;
    @Column
    private String tprintScreen;
    @Column
    private Date tScreenTime;
    @Column
    private String accessUrl;

    public Long getIdTermianl() {
        return idTermianl;
    }

    public void setIdTermianl(Long idTermianl) {
        this.idTermianl = idTermianl;
    }

    public String getIdSystem() {
        return idSystem;
    }

    public void setIdSystem(String idSystem) {
        this.idSystem = idSystem;
    }

    public String getOnline() {
        return isOnline;
    }

    public void setOnline(String online) {
        isOnline = online;
    }

    public String getTprintScreen() {
        return tprintScreen;
    }

    public void setTprintScreen(String tprintScreen) {
        this.tprintScreen = tprintScreen;
    }

    public Date gettScreenTime() {
        return tScreenTime;
    }

    public void settScreenTime(Date tScreenTime) {
        this.tScreenTime = tScreenTime;
    }

    public String getAccessUrl() {
        return accessUrl;
    }

    public void setAccessUrl(String accessUrl) {
        this.accessUrl = accessUrl;
    }
}
