package com.inetec.ichange.service.monitor.uplink.job;

import com.crgs.client.WebServiceClient;
import com.crgs.webservices.IReceiveData;
import com.inetec.ichange.service.monitor.uplink.databean.ParentExchangePlatformDao;
import com.inetec.ichange.service.monitor.uplink.databean.ParentExchangePlatformDataBean;
import com.inetec.ichange.service.monitor.uplink.webservice.SysdelserviceProcess;
import org.apache.log4j.Logger;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: bluesky
 * Date: 12-4-26
 * Time: 上午9:48
 * To change this template use File | Settings | File Templates.
 */
public class WebServiceDelJobImp {
    private static final Logger log = Logger.getLogger(WebServiceDelJobImp.class);

    private long platformId;

    public WebServiceDelJobImp() {

    }

    public WebServiceDelJobImp(long platformId) {
        this.platformId = platformId;
    }
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {

        ParentExchangePlatformDao dao = new ParentExchangePlatformDao();
        ParentExchangePlatformDataBean dataBean = dao.findById(platformId);
        if(dataBean==null){
            log.warn("集控系统-上级级联平台(WebService)信息没有初始化！无法级联上报");
            return;
        }
        String ip = dataBean.getPlatform_ip();
        int port = dataBean.getPlatform_port();
        String server = dataBean.getAddress();
        String url =  "http://"+ip+":"+port+server;
//                    "http://192.168.2.227:8080/Webservice/services/inceptData";

        String keyFilePath = dataBean.getCertificate_path();
        String keyPassword = dataBean.getCertificate_pwd();

        WebServiceClient client = new WebServiceClient();
        IReceiveData service = client.getServiceClient(url,keyFilePath,keyPassword);
        new SysdelserviceProcess().process(service);
    }

    public static void main(String arg[]) throws Exception {

    }

    public long getPlatformId() {
        return platformId;
    }

    public void setPlatformId(long platformId) {
        this.platformId = platformId;
    }
}
