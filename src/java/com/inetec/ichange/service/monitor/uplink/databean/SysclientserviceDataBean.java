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
@Entity(schema = "jksys", table = "sysclientservice")
public class SysclientserviceDataBean {
     @Column(isPrimaryKey = true)
    private int id;
    @Column
    private int idTerminal;
    @Column
    private String idSystem;
    @Column
    private String taction;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdTerminal() {
        return idTerminal;
    }

    public void setIdTerminal(int idTerminal) {
        this.idTerminal = idTerminal;
    }

    public String getIdSystem() {
        return idSystem;
    }

    public void setIdSystem(String idSystem) {
        this.idSystem = idSystem;
    }

    public String getTaction() {
        return taction;
    }

    public void setTaction(String taction) {
        this.taction = taction;
    }
}
