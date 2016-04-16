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
 * Time: ����9:14
 * To change this template use File | Settings | File Templates.
 */
public class WebServiceDayJobImp implements Job {
    Logger logger = Logger.getLogger(WebServiceDayJobImp.class);

    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        ParentExchangePlatformDao dao = new ParentExchangePlatformDao();
        List<ParentExchangePlatformDataBean> dataBeans = dao.WebServicelist();
        if (dataBeans==null ){
            logger.warn("����ϵͳ-�ϼ�����ƽ̨(WebService)��Ϣû�г�ʼ�����޷������ϱ�");
            return;
        }
        if(dataBeans.size() == 0) {
            logger.warn("����ϵͳ-�ϼ�����ƽ̨(WebService)û��ѡ�������ϱ����޷������ϱ�");
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
             * �ϱ�ǰ���ݴ���
             */
            try{
                new UplinkRunTimeProcess().process();
            }catch(RuntimeException e){
                logger.warn("�����ϱ�-�ձ�����-�ϱ�ǰ���ݴ����쳣��.",e);
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
        //�¼��ϱ�����δ��ͨ
        new LowerSysabnormalProcess().process(service);

        new LowerSysbizstatusProcess().process(service);

        new LowerSysstatusProcess().process(service);

*/        logger.info("�����ϱ�-�ձ�����-�ϱ��ɹ���.");
        if(jobExecutionContext!=null){
            jobExecutionContext.setResult("�����ϱ�-�ձ�����-�ϱ��ɹ���");
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
