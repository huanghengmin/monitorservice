package com.ichange.service;

import com.inetec.ichange.service.monitor.databean.*;

import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;

/**
 * Created by IntelliJ IDEA.
 * User: bluesky
 * Date: 2010-10-27
 * Time: 20:19:23
 * To change this template use File | Settings | File Templates.
 */
public class TestEquipmentDbOpera {

    public void testEquipmentHourReport() {
        EquipmentHourReportDao dao = new EquipmentHourReportDao();
        EquipmentHourReport report = new EquipmentHourReport();
        System.out.println("begin time:" + new Date(System.currentTimeMillis()).toGMTString());
        for (int i = 0; i < 3000; i++) {
            report.setEqu_name("VPN");
            report.setAlert_count(i);
            report.setBreakdown_count(100 * i);
            report.setReport_time(1);
            report.setReport_date(new Date(System.currentTimeMillis()));
            report.setStatus(BaseDataBean.I_Status_OK);
            dao.saveEquipmentHourReport(report);
        }
        System.out.println("end time:" + new Date(System.currentTimeMillis()).toGMTString());
    }

    public void testEquipmentLog() {
        EquipmentLogDao dao = new EquipmentLogDao();
        EquipmentLog equlog = new EquipmentLog();
        System.out.println("begin time:" + new Date(System.currentTimeMillis()).toGMTString());
        //for (int i = 0; i < 3000; i++) {
        equlog.setEquipment_name("VPN");
        equlog.setLevel("INFO");
        equlog.setLink_amount(1);
        equlog.setLink_bandwidth(6);
        equlog.setLink_Corp("corp");
        equlog.setLink_name("移动警务");
        equlog.setLog_info("移动警务1111111111");
        equlog.setLink_security("222");
        equlog.setLog_time(new Timestamp(System.currentTimeMillis()));
        dao.saveEquipmentLog(equlog);
        // }
        System.out.println("end time:" + new Date(System.currentTimeMillis()).toGMTString());
    }

    public void testEquipmentAlert() {
        EquipmentAlertDao dao = new EquipmentAlertDao();
        EquipmentAlert alert = new EquipmentAlert();
        alert.setEqu_name("VPN");
        alert.setAlert_info("alert");
        alert.setAlert_time(new Timestamp(System.currentTimeMillis()));

        System.out.println("begin time:" + new Date(System.currentTimeMillis()).toGMTString());
        dao.saveEquipmentAlert(alert);
        // }
        System.out.println("end time:" + new Date(System.currentTimeMillis()).toGMTString());
    }

    public static void main(String arg[]) throws Exception {
        TestEquipmentDbOpera test = new TestEquipmentDbOpera();
        test.testEquipmentHourReport();
        test.testEquipmentLog();
        test.testEquipmentAlert();
    }
}
