package com.inetec.ichange.service.monitor.databean;

import com.avdy.p4j.jdbc.model.Column;
import com.avdy.p4j.jdbc.model.Entity;

/**
 * Created by IntelliJ IDEA.
 * User: Administrator
 * Date: 12-11-28
 * Time: ÏÂÎç3:22
 * To change this template use File | Settings | File Templates.
 */
@Entity(schema = "jksys", table = "snmpoid")
public class SnmpOid {
    @Column(isPrimaryKey = true)
    private int id;
    @Column
    private String name;
    @Column
    private String type;
    @Column
    private String company;
    @Column
    private String snmpver;
    @Column
    private String cpuuse;
    @Column
    private String disktotal;
    @Column
    private String diskuse;
    @Column
    private String memtotal;
    @Column
    private String memuse;
    @Column
    private String curconn;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMemuse() {
        return memuse;
    }

    public void setMemuse(String memuse) {
        this.memuse = memuse;
    }

    public String getCurconn() {
        return curconn;
    }

    public void setCurconn(String curconn) {
        this.curconn = curconn;
    }

    public String getMemtotal() {
        return memtotal;
    }

    public void setMemtotal(String memtotal) {
        this.memtotal = memtotal;
    }

    public String getDiskuse() {
        return diskuse;
    }

    public void setDiskuse(String diskuse) {
        this.diskuse = diskuse;
    }

    public String getDisktotal() {
        return disktotal;
    }

    public void setDisktotal(String disktotal) {
        this.disktotal = disktotal;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getSnmpver() {
        return snmpver;
    }

    public void setSnmpver(String snmpver) {
        this.snmpver = snmpver;
    }

    public String getCpuuse() {
        return cpuuse;
    }

    public void setCpuuse(String cpuuse) {
        this.cpuuse = cpuuse;
    }
}
