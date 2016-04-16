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
 * User: qxp
 * Date: 11-4-29
 * Time: 12:49
 * To change this template use File | Settings | File Templates.
 */
public class WebServiceDay2JobImp implements Job {
    private static final Logger logger = Logger.getLogger(WebServiceDay2JobImp.class);
    public WebServiceDay2JobImp() {
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
    //        String server = "/Webservice/services/inceptData";
            String url =  "http://"+ip+":"+port+server;
    //                    "http://192.168.2.227:8080/Webservice/services/inceptData";
            String keyFilePath = dataBean.getCertificate_path();
    //                          "D:\\fartec\\ichange\\monitorservice\\src\\resources\\clientStore.jks";
            String keyPassword = dataBean.getCertificate_pwd();
    //                          "1qaz@wsxstorepass";
            WebServiceClient client = new WebServiceClient();
            IReceiveData service = client.getServiceClient(url,keyFilePath,keyPassword);

            new SysruntimeProcess().process(service);
        }

/*
        //下级上报服务未开通
        new LowerSysruntimeProcess().process(service);

*/
        logger.info("级联上报-日报2次操作-上报成功！");
        if(jobExecutionContext!=null){
            jobExecutionContext.setResult("级联上报-日报2次操作-上报成功！");
        }

    }

    public static void main(String arg[]) throws Exception {
        String url = "http://192.168.2.227:8080/Webservice/services/inceptData";
        String keyFilePath = "D:\\fartec\\ichange\\monitorservice\\src\\resources\\clientStore.jks";
        String keyPassword = "1qaz@wsxstorepass";
        WebServiceDay2JobImp job = new WebServiceDay2JobImp();
        job.execute(null);
    }
}
