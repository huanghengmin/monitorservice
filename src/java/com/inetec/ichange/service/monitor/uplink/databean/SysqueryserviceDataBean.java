package com.inetec.ichange.service.monitor.uplink.databean;

import com.avdy.p4j.jdbc.model.Column;
import com.avdy.p4j.jdbc.model.Entity;

import java.util.Date;

/**
 * Created by IntelliJ IDEA.
 * User: qxp
 * Date: 12-2-7
 * Time: am 3:41
 * To change this template use File | Settings | File Templates.
 */
@Entity(schema = "jksys", table = "sysqueryservice")
public class SysqueryserviceDataBean {
    @Column(isPrimaryKey = true)
    private long id;
    @Column
    private String idsystem;
    @Column
    private String objectname;
    @Column
    private String ids;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getIdsystem() {
        return idsystem;
    }

    public void setIdsystem(String idsystem) {
        this.idsystem = idsystem;
    }

    public String getObjectname() {
        return objectname;
    }

    public void setObjectname(String objectname) {
        this.objectname = objectname;
    }

    public String getIds() {
        return ids;
    }

    public void setIds(String ids) {
        this.ids = ids;
    }
}
