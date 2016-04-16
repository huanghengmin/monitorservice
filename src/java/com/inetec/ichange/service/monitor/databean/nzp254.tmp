package com.inetec.ichange.service.monitor.databean;

import com.avdy.p4j.jdbc.model.Column;
import com.avdy.p4j.jdbc.model.Entity;

import java.sql.Date;
import java.sql.Timestamp;

/**
 * Created by IntelliJ IDEA.
 * User: bluesky
 * Date: 2010-9-5
 * Time: 10:10:35
 * To change this template use File | Settings | File Templates.
 */

@Entity(schema = "jksys", table = "equipment_hour_report")
public class EquipmentHourReport extends BaseDataBean {
    @Column(isPrimaryKey = true)
    private int id;
    @Column
    private Date report_date;
    @Column
    private int report_time;
    @Column
    private String equ_name;

    @Column
    private int breakdown_count;
    @Column
    private int alert_count;

    public Date getReport_date() {
        return report_date;
    }

    public void setReport_date(Date report_date) {
        this.report_date = report_date;
    }

    public int getReport_time() {
        return report_time;
    }

    public void setReport_time(int report_time) {
        this.report_time = report_time;
    }

    public int getBreakdown_count() {
        return breakdown_count;
    }

    public void setBreakdown_count(int breakdown_count) {
        this.breakdown_count = breakdown_count;
    }

    public String getEqu_name() {
        return equ_name;
    }

    public void setEqu_name(String equ_name) {
        this.equ_name = equ_name;
    }

    public int getAlert_count() {
        return alert_count;
    }

    public void setAlert_count(int alert_count) {
        this.alert_count = alert_count;
    }


}
