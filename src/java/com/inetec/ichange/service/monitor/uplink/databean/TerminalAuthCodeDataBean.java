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
@Entity(schema = "jksys", table = "terminal_auth_code")
public class TerminalAuthCodeDataBean {
     @Column(isPrimaryKey = true)
    private int id;
    @Column
    private String code;
    @Column
    private String code_desc;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getCode_desc() {
        return code_desc;
    }

    public void setCode_desc(String code_desc) {
        this.code_desc = code_desc;
    }
}
