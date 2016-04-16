package com.inetec.ichange.service.monitor.uplink.job;

import com.crgs.client.WebServiceClient;
import com.crgs.entities.Sysclientservice;
import com.crgs.entities.Sysstrategyinf;
import com.crgs.webservices.IReceiveData;
import com.inetec.ichange.main.api.Status;
import com.inetec.ichange.service.Service;
import com.inetec.ichange.service.monitor.databean.MonitorAgentDao;
import com.inetec.ichange.service.monitor.http.McHttpClient;
import com.inetec.ichange.service.monitor.uplink.databean.ParentExchangePlatformDao;
import com.inetec.ichange.service.monitor.uplink.databean.ParentExchangePlatformDataBean;
import com.inetec.ichange.service.monitor.uplink.webservice.*;
import com.inetec.ichange.service.utils.ServiceUtil;
import org.apache.log4j.Logger;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.SchedulerException;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: bluesky
 * Date: 11-4-29
 * Time: 上午9:14
 * To change this template use File | Settings | File Templates.
 */
public class WebServiceCommandJobImp implements Job {
    Logger logger = Logger.getLogger(WebServiceCommandJobImp.class);
    private Sysclientservice sysclientservice;

    public Sysclientservice getSysclientservice() {
        return sysclientservice;
    }

    public void setSysclientservice(Sysclientservice sysclientservice) {
        this.sysclientservice = sysclientservice;
    }

    public WebServiceCommandJobImp() {
    }

    public WebServiceCommandJobImp(Sysclientservice sysclientservice) {
        this.sysclientservice = sysclientservice;
    }

    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        ParentExchangePlatformDao dao = new ParentExchangePlatformDao();
        List<ParentExchangePlatformDataBean> dataBeans = dao.WebServicelist();
        if (dataBeans==null ){
            logger.warn("集控系统-上级级联平台(WebService)信息没有初始化！无法级联上报");
            return;
        }
        if(dataBeans.size() == 0) {
            logger.warn("集控系统-上级级联平台(WebService)没有选择允许上报！无法级联上报");
            return;
        }

        for(ParentExchangePlatformDataBean dataBean : dataBeans){
            String ip = dataBean.getPlatform_ip();
            int port = dataBean.getPlatform_port();
            String server = dataBean.getAddress();
            String url = "http://" + ip + ":" + port + server;
            String keyFilePath = dataBean.getCertificate_path();
            String keyPassword = dataBean.getCertificate_pwd();
            WebServiceClient client = new WebServiceClient();
            IReceiveData service = client.getServiceClient(url, keyFilePath, keyPassword);
            String tAction = sysclientservice.getTaction();

            logger.info("webservicecommandJobImp TAction:" + tAction);
            if (tAction.equals("0")) {       //为上报终端状态
                new SysterminalstatusProcess().process(service);
            } else if (tAction.equals("1")) {    //为禁止访问
                String userid=  Service.terminainfService.terminalCache.getUserIdByTermialId(sysclientservice.getIdterminal());
                McHttpClient mcclient = new McHttpClient();
                try {
                    mcclient.init(new MonitorAgentDao().getMcHost());
                    if (mcclient.vpnblock(userid, ip)) {
                        logger.info("Service monitor  vpn block call mc client end okay.");
                    } else {
                        logger.info("Service monitor  vpn block call mc client end failed.");
                    }
                    mcclient.close();
                } catch (Exception e) {
                    logger.warn("Service monitor all vpn block call mc client error.", e);
                }
            } else if (tAction.equals("2")) {     //为恢复访问
                String userid=  Service.terminainfService.terminalCache.getUserIdByTermialId(sysclientservice.getIdterminal());
                McHttpClient mcclient = new McHttpClient();
                try {
                    mcclient.init(new MonitorAgentDao().getMcHost());
                    if (mcclient.vpnblock(userid, ip)) {

                        logger.info("Service monitor  vpn noblock call mc client end okay.");
                    } else {
                        logger.info("Service monitor  vpn noblock call mc client end failed.");

                    }
                    mcclient.close();
                } catch (Exception e) {
                    logger.warn("Service monitor all vpn block call mc client error.", e);
                }
            } else if (tAction.equals("3")) {
                new SysreginfProcess().process(service);
            } else if (tAction.equals("4")) {
                new SysbizinfProcess().process(service);
            } else if (tAction.equals("5")) {
                new SysdeviceinfProcess().process(service);
            } else if (tAction.equals("6")) {
                new SysoutlinkinfProcess().process(service);
            } else if (tAction.equals("7")) {
                new SysstrategyinfProcess().process(service);
            } else if (tAction.equals("8")) {
                new SysterminalinfoProcess().process(service);
            } else if (tAction.equals("9")) {
                new SysabnormalProcess().process(service);
            } else if (tAction.equals("10")) {
                new SysstatusProcess().process(service);
            } else if (tAction.equals("11")) {    //为调整上报时间间隔到24小时报一次
                dataBean.setTimeType("24");
                dao.save(dataBean);
                logger.info("change to uplink time type 24.");
                WebServiceUplinkCronTriggerRunner.start();


            } else if (tAction.equals("12")) {   //为调整上报时间间隔到12小时报一次
                dataBean.setTimeType("12");
                logger.info("change to uplink time type 12.");
                dao.save(dataBean);
                WebServiceUplinkCronTriggerRunner.start();

            } else if (tAction.equals("13")) {   //为调整上报时间间隔到4小时报一次
                dataBean.setTimeType("4");
                logger.info("change to uplink time type 4.");
                dao.save(dataBean);
                WebServiceUplinkCronTriggerRunner.start();

            } else if (tAction.equals("14")) {   //为调整上报时间间隔到2小时报一次备
                dataBean.setTimeType("2");
                dao.save(dataBean);
                logger.info("change to uplink time type 2.");
                WebServiceUplinkCronTriggerRunner.start();

            }
        }

        logger.info("级联上报-监管中心指令下发-上报成功！");
        if(jobExecutionContext!=null){
            jobExecutionContext.setResult("级联上报-监管中心指令下发-上报成功！");
        }

    }

    public static void main(String arg[]) throws Exception {
        String url = "http://192.168.2.227:8080/Webservice/services/inceptData";
        String keyFilePath = "D:\\fartec\\ichange\\monitorservice\\src\\resources\\clientStore.jks";
        String keyPassword = "1qaz@wsxstorepass";
        WebServiceCommandJobImp job = new WebServiceCommandJobImp();
        Sysclientservice sysclientservice = new Sysclientservice();
        sysclientservice.setIdsystem("S41010001");
        sysclientservice.setIdterminal(null);
        sysclientservice.setTaction("13");
        job.setSysclientservice(sysclientservice);
        job.execute(null);
    }
}
