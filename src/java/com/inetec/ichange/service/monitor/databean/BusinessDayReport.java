package com.inetec.ichange.service.monitor.databean;

import com.avdy.p4j.jdbc.model.Column;
import com.avdy.p4j.jdbc.model.Entity;

import java.util.Date;

/**
 * Created by IntelliJ IDEA.
 * User: bluesky
 * Date: 11-5-1
 * Time: 下午3:23
 * To change this template use File | Settings | File Templates.
 */
@Entity(schema = "jksys", table = "business_day_report")
public class BusinessDayReport {
    @Column(isPrimaryKey = true)
    private Long id;
    @Column
    private String business_name;
    @Column
    private Date report_date;
    @Column
    private long ext_record_count;
    @Column
    private long int_record_count;
    @Column
    private Double ext_dataflow;
    @Column
    private Double int_dataflow;
    @Column
    private int alert_count;

    @Column
    private int report_year;
    @Column
    private int report_month;
    @Column
    private int report_day;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public long getExt_record_count() {
        return ext_record_count;
    }

    public void setExt_record_count(int ext_record_count) {
        this.ext_record_count = ext_record_count;
    }

    public long getInt_record_count() {
        return int_record_count;
    }

    public void setInt_record_count(int int_record_count) {
        this.int_record_count = int_record_count;
    }

    public Double getExt_dataflow() {
        return ext_dataflow;
    }

    public void setExt_dataflow(Double ext_dataflow) {
        this.ext_dataflow = ext_dataflow;
    }

    public Double getInt_dataflow() {
        return int_dataflow;
    }

    public void setInt_dataflow(Double int_dataflow) {
        this.int_dataflow = int_dataflow;
    }

    public long getAlert_count() {
        return alert_count;
    }

    public void setAlert_count(int alert_count) {
        this.alert_count = alert_count;
    }

    public int getReport_year() {
        return report_year;
    }

    public void setReport_year(int report_year) {
        this.report_year = report_year;
    }

    public int getReport_month() {
        return report_month;
    }

    public void setReport_month(int report_month) {
        this.report_month = report_month;
    }

    public int getReport_day() {
        return report_day;
    }

    public void setReport_day(int report_day) {
        this.report_day = report_day;
    }
}
