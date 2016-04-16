package com.inetec.ichange.service.monitor.dboperat;

import com.inetec.ichange.service.monitor.databean.EquipmentHourReport;
import com.inetec.ichange.service.monitor.databean.EquipmentHourReportDao;

import java.sql.Date;
import java.sql.Time;
import java.util.TimerTask;

/**
 * Created by IntelliJ IDEA.
 * User: bluesky
 * Date: 2010-11-2
 * Time: 22:17:47
 * To change this template use File | Settings | File Templates.
 */
public class EquipmentHourTask extends TimerTask {

    private String equ_name;
    EquipmentHourReport hourReport = new EquipmentHourReport();

    public void init(String name) {
        this.equ_name = name;
        hourReport.setEqu_name(equ_name);
    }

    EquipmentHourReportDao dao = new EquipmentHourReportDao();

    /**
     * The action to be performed by this timer task.
     */
    @Override
    public void run() {
        hourReport.setReport_date(new Date(System.currentTimeMillis()));
        hourReport.setReport_time(1);
        hourReport.setAlert_count(0);
        hourReport.setBreakdown_count(0);
        dao.saveEquipmentHourReport(hourReport);

    }
}
