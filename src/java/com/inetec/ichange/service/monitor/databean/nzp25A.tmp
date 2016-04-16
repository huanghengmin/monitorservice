package com.inetec.ichange.service.monitor.databean;

import com.avdy.p4j.jdbc.model.Column;
import com.avdy.p4j.jdbc.model.Entity;

/**
 * Syslog??????
 * Created by IntelliJ IDEA.
 * User: bluesky
 * Date: 2010-9-5
 * Time: 10:10:35
 * To change this template use File | Settings | File Templates.
 */

@Entity(schema = "jksys", table = "monitor_agent")
public class MonitorAgent extends BaseDataBean {
    @Column(isPrimaryKey = true)
    private int id;
    @Column
    private String agent_name;
    @Column
    private String agent_ip;
    @Column
    private String link_name;

    @Column
    private int agent_port;

    public int getAgent_port() {
        return agent_port;
    }

    public void setAgent_port(int agent_port) {
        this.agent_port = agent_port;
    }

    public String getLink_name() {
        return link_name;
    }

    public void setLink_name(String link_name) {
        this.link_name = link_name;
    }

    public String getAgent_ip() {
        return agent_ip;
    }

    public void setAgent_ip(String agent_ip) {
        this.agent_ip = agent_ip;
    }


    public int getId() {
        return id;
    }

    public String getAgent_name() {
        return agent_name;
    }

    public void setAgent_name(String agent_name) {
        this.agent_name = agent_name;
    }


    public void setId(int id) {
        this.id = id;
    }


}
