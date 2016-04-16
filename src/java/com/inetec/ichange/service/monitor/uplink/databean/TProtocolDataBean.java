package com.inetec.ichange.service.monitor.uplink.databean;

import com.avdy.p4j.jdbc.model.Column;
import com.avdy.p4j.jdbc.model.Entity;

/**
 * Created by IntelliJ IDEA.
 * User: bluesky
 * Date: 11-4-29
 * Time: 上午11:31
 * To change this template use File | Settings | File Templates.
 */
@Entity(schema = "jksys", table = "protocol_type")
public class TProtocolDataBean {
    @Column(isPrimaryKey = true)
    private int id;
    @Column
    private String protocol_code;
    @Column
    private String protocol_name;

    public String getProtocol_name() {
        return protocol_name;
    }

    public void setProtocol_name(String protocol_name) {
        this.protocol_name = protocol_name;
    }

    public String getProtocol_code() {
        return protocol_code;
    }

    public void setProtocol_code(String protocol_code) {
        this.protocol_code = protocol_code;
    }

    public int getId() {
        return id;
    }


}
