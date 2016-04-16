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
public class WebSeviceMonthJobImp implements Job {
    Logger logger = Logger.getLogger(WebSeviceMonthJobImp.class);

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
            // SysreginfProcess
            new SysreginfProcess().process(service);   //okay
            //SysbizinfProcess
            new SysbizinfProcess().process(service);
//
//        //Sysdeviceinf
            new SysdeviceinfProcess().process(service);
//        //Sysoutlinkinf
            new SysoutlinkinfProcess().process(service);
//
            //  new SysterminalinfoProcess().process(service);

            new SysstrategyinfProcess().process(service);

            new SysabnormalProcess().process(service);

            /*  //  TInlinkinfProcess
       new TInlinkinfProcess().process();    //okay

       //ToutlinkinfProcess
       new ToutlinkinfProcess().process();      //okay
       //  TBizInfProcess
       new TBizInfProcess().process();       //okay 待测试

       //TdeviceinfProcess
       new TdeviceinfProcess().process();    //okay*/

            //TbizproinfProcess
            //okay 待测试
        }

        logger.info("级联上报-月报操作-上报成功！");
        if(jobExecutionContext!=null){
            jobExecutionContext.setResult("级联上报-月报操作-上报成功！");
        }


    }

    public static void main(String arg[]) throws Exception {
        WebSeviceMonthJobImp job = new WebSeviceMonthJobImp();
        job.execute(null);
    }
}
