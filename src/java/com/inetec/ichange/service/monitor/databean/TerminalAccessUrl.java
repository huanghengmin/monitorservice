package com.inetec.ichange.service.monitor.databean;

import com.avdy.p4j.jdbc.model.Column;
import com.avdy.p4j.jdbc.model.Entity;

import java.sql.Timestamp;

/**
 *  终端用户访问URL信息
 * Created by IntelliJ IDEA.
 * User: bluesky
 * Date: 2010-9-5
 * Time: 10:10:35
 * To change this template use File | Settings | File Templates.
 */

@Entity(schema = "jksys", table = "terminal_access_url")
public class TerminalAccessUrl extends BaseDataBean {
    @Column(isPrimaryKey = true)
    private int id;
    @Column
    private String busName;
    @Column
    private String accessUrl;
    @Column
    private String resourceRegx;
    @Column
    private String action;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBusName() {
        return busName;
    }

    public void setBusName(String busName) {
        this.busName = busName;
    }



    public String getAccessUrl() {
        return accessUrl;
    }

    public void setAccessUrl(String accessUrl) {
        this.accessUrl = accessUrl;
    }

    public String getResourceRegx() {
        return resourceRegx;
    }

    public void setResourceRegx(String resourceRegx) {
        this.resourceRegx = resourceRegx;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }
}
