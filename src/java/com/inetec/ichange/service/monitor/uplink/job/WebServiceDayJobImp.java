package com.inetec.ichange.service.monitor.uplink.job;

import com.crgs.client.WebServiceClient;
import com.crgs.webservices.IReceiveData;
import com.inetec.ichange.service.monitor.uplink.databean.ParentExchangePlatformDao;
import com.inetec.ichange.service.monitor.uplink.databean.ParentExchangePlatformDataBean;
import com.inetec.ichange.service.monitor.uplink.webservice.*;
import org.apache.log4j.Logger;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: bluesky
 * Date: 11-4-29
 * Time: 上午9:14
 * To change this template use File | Settings | File Templates.
 */
public class WebServiceDayJobImp implements Job {
    Logger logger = Logger.getLogger(WebServiceDayJobImp.class);

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


//            new SysabnormalProcess().process(service);
            /**
             * 上报前数据处理
             */
            try{
                new UplinkRunTimeProcess().process();
            }catch(RuntimeException e){
                logger.warn("级联上报-日报操作-上报前数据处理异常！.",e);
            }
//            new SysterminalstatusProcess().process(service);
            new SysbizstatusProcess().process(service);
            new SysstatusProcess().process(service);
            new SysruntimeProcess().process(service);

        }
        /**
         *
        new SysstatusProcess().process(service);
        new SysruntimeProcess().process(service);
        **/
/*
        //下级上报服务未开通
        new LowerSysabnormalProcess().process(service);

        new LowerSysbizstatusProcess().process(service);

        new LowerSysstatusProcess().process(service);

*/        logger.info("级联上报-日报操作-上报成功！.");
        if(jobExecutionContext!=null){
            jobExecutionContext.setResult("级联上报-日报操作-上报成功！");
        }

    }

    public static void main(String arg[]) throws Exception {
        String url = "http://172.16.2.2:8080/Webservice/services/inceptDataService";
        String keyFilePath = "D:\\fartec\\ichange\\monitorservice\\src\\resources\\clientStore.jks";
        String keyPassword = "1qaz@wsxstorepass";
        WebServiceDayJobImp job = new WebServiceDayJobImp();
        job.execute(null);
    }
}
