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
public class WebSeviceMonthJobImp implements Job {
    Logger logger = Logger.getLogger(WebSeviceMonthJobImp.class);

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
       new TBizInfProcess().process();       //okay ������

       //TdeviceinfProcess
       new TdeviceinfProcess().process();    //okay*/

            //TbizproinfProcess
            //okay ������
        }

        logger.info("�����ϱ�-�±�����-�ϱ��ɹ���");
        if(jobExecutionContext!=null){
            jobExecutionContext.setResult("�����ϱ�-�±�����-�ϱ��ɹ���");
        }


    }

    public static void main(String arg[]) throws Exception {
        WebSeviceMonthJobImp job = new WebSeviceMonthJobImp();
        job.execute(null);
    }
}
