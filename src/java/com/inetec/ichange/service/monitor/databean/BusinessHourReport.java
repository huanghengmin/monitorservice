package com.inetec.ichange.service.monitor.databean;

import com.avdy.p4j.jdbc.model.Column;
import com.avdy.p4j.jdbc.model.Entity;

import java.sql.Date;

/**
 * Created by IntelliJ IDEA.
 * User: bluesky
 * Date: 2010-11-14
 * Time: 13:42:24
 * To change this template use File | Settings | File Templates.
 */
@Entity(schema = "jksys", table = "business_hour_report")
public class BusinessHourReport extends BaseDataBean {
    @Column(isPrimaryKey = true)
    private int id;
    @Column
    private String business_name;
    @Column(isPrimaryKey = true)
    private Date report_date;
    @Column(isPrimaryKey = true)
    private int report_hour;
    @Column
    private Float ext_dataflow = new Float(0);
    @Column
    private Float int_dataflow = new Float(0);
    @Column
    private int ext_record_count;
    @Column
    private int int_record_count;
    @Column
    private int alert_count;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBusiness_name() {
        return business_name;
    }

    public void setBusiness_name(String business_name) {
        this.business_name = business_name;
    }

    public Date getReport_date() {
        return report_date;
    }

    public void setReport_date(Date report_date) {
        this.report_date = report_date;
    }

    public int getReport_hour() {
        return report_hour;
    }

    public void setReport_hour(int report_hour) {
        this.report_hour = report_hour;
    }

    public Float getExt_dataflow() {
        return ext_dataflow;
    }

    public void setExt_dataflow(Float ext_dataflow) {
        this.ext_dataflow = ext_dataflow;
    }

    public int getExt_record_count() {
        return ext_record_count;
    }

    public void setExt_record_count(int ext_record_count) {
        this.ext_record_count = ext_record_count;
    }

    public Float getInt_dataflow() {
        return int_dataflow;
    }

    public void setInt_dataflow(Float int_dataflow) {
        this.int_dataflow = int_dataflow;
    }

    public int getInt_record_count() {
        return int_record_count;
    }

    public void setInt_record_count(int int_record_count) {
        this.int_record_count = int_record_count;
    }

    public int getAlert_count() {
        return alert_count;
    }

    public void setAlert_count(int alert_count) {
        this.alert_count = alert_count;
    }


}
