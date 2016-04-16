package com.inetec.ichange.service.monitor.business;

import com.inetec.ichange.service.monitor.databean.*;
import com.inetec.ichange.service.monitor.uplink.utils.UpFileUtils;
import org.apache.log4j.Logger;

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
public class BusinessHourTask extends TimerTask {
    private static final Logger log = Logger.getLogger(BusinessLogImp.class);
    private String equ_name;
    BusinessHourReport hourReport = new BusinessHourReport();

    public void init(String name) {
        this.equ_name = name;
        hourReport.setBusiness_name(equ_name);
    }

    BusinessHourReportDao dao = new BusinessHourReportDao();

    /**
     * The action to be performed by this timer task.
     */
    @Override
    public void run() {
        try {
            BusinessDataBean bean = BusinessPlatformService.dataset.getBusinessDataBeanByID(equ_name);

            hourReport.setReport_date(new Date(System.currentTimeMillis()));
            hourReport.setReport_hour(UpFileUtils.geCurrentHour());
            hourReport.setAlert_count(bean.getAlert_count());
            hourReport.setExt_dataflow(bean.getXt_dataflow());
            hourReport.setInt_dataflow(bean.getXt_dataflow());
            hourReport.setExt_record_count((int) bean.getRecord_count());
            hourReport.setInt_record_count((int) bean.getRecord_count());
            int bean1 = dao.getBusinessHourReportMax(equ_name, UpFileUtils.getCurrentDay(), UpFileUtils.geCurrentHour());
            if (bean1 > 0) {

                log.warn("Business Hour Report object is exist.");
                return;
            }
            dao.saveBusinessHourReport(hourReport);
        } catch (RuntimeException e) {
            log.warn("Business Hour Report insert database error.", e);
        } catch (Exception e) {
            log.warn("Business Hour Report insert database error.", e);

        }

    }

    public static void main(String arg[]) throws Exception {
        BusinessHourTask task = new BusinessHourTask();
        //System.out.println(task.dao.getBusinessHourReportMax("h3c80"));

    }
}
