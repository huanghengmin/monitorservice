package com.inetec.ichange.service.monitor.uplink.job;

import com.inetec.ichange.service.monitor.uplink.databean.ParentExchangePlatformDao;
import com.inetec.ichange.service.monitor.uplink.databean.ParentExchangePlatformDataBean;
import org.apache.log4j.Logger;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: bluesky
 * Date: 12-4-26
 * Time: ÏÂÎç9:48
 * To change this template use File | Settings | File Templates.
 */
public class WebServiceUplinkCronTriggerRunner {
    private static final Logger log = Logger.getLogger(WebServiceUplinkCronTriggerRunner.class);

    public static SchedulerFactory schedulerFactory = new StdSchedulerFactory();
    public static Scheduler scheduler;
    // Retrieve a scheduler from schedule factory
    public static WebServiceUplinkCronTriggerRunner object = new WebServiceUplinkCronTriggerRunner();

    private WebServiceUplinkCronTriggerRunner() {

    }

    public static WebServiceUplinkCronTriggerRunner getObject() {
        return object;
    }

    public void task(String timeType) throws SchedulerException {
        // Initiate a Schedule Factory
        if (scheduler == null) {
            scheduler = schedulerFactory.getScheduler();
        } else {
            scheduler.shutdown();
            scheduler = schedulerFactory.getScheduler();
        }

        // current time


        // Initiate JobDetail with job name, job group, and executable job class
        JobDetail jobDetail = new JobDetail("WebServiceJob_uplink_day", WebServiceDayJobImp.class);
        // Initiate CronTrigger with its name and group name
        SimpleTrigger cronTrigger = new SimpleTrigger("Trigger_WebServiceJob_uplink_day");
        try {
            // setup CronExpression


            if (timeType.equalsIgnoreCase("24")) {
                cronTrigger.setRepeatInterval(24 * 60 * 60 * 1000);
            }
            if (timeType.equalsIgnoreCase("12")) {
                cronTrigger.setRepeatInterval(12 * 60 * 60 * 1000);
            }
            if (timeType.equalsIgnoreCase("4")) {
                cronTrigger.setRepeatInterval(4 * 60 * 60 * 1000);
            }
            if (timeType.equalsIgnoreCase("2")) {
                cronTrigger.setRepeatInterval( 2 * 60 * 60 * 1000);
            }
//            // Assign the CronExpression to CronTrigger
//            cronTrigger.setCronExpression(cexp);
        } catch (Exception e) {
            log.warn("WebServiceUplinkCronTriggerRunner run erro:", e);
        }
        // schedule a job with JobDetail and Trigger

        //scheduler.addJob(jobDetail,false);
         scheduler.scheduleJob(jobDetail, cronTrigger);

        // start the scheduler
        scheduler.start();
    }

    public static void start() {

        ParentExchangePlatformDao dao = new ParentExchangePlatformDao();
        List<ParentExchangePlatformDataBean> list =dao.WebServicelist();
        if (dao.list() == null && list.size() > 0){
            for(ParentExchangePlatformDataBean dataBean :list){
                try {
                    WebServiceUplinkCronTriggerRunner.getObject().task(dataBean.getTimeType());
                } catch (SchedulerException e) {
                    log.warn("WebServiceUplinkCronTriggerRunner run erro:", e);
                }
            }

        }
    }

    public static void main(String arg[]) throws Exception {
        WebServiceUplinkCronTriggerRunner.start();
        Thread.sleep(2000 * 1000);
    }


}
