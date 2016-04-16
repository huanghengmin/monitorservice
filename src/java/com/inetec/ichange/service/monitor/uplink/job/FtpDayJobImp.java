package com.inetec.ichange.service.monitor.uplink.job;

import com.inetec.ichange.service.monitor.uplink.databean.ParentExchangePlatformDao;
import com.inetec.ichange.service.monitor.uplink.databean.ParentExchangePlatformDataBean;
import com.inetec.ichange.service.monitor.uplink.ftp.*;
import com.inetec.ichange.service.monitor.uplink.webservice.UplinkRunTimeProcess;
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
public class FtpDayJobImp implements Job {
    public static org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(FtpDayJobImp.class);

    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        ParentExchangePlatformDao dao = new ParentExchangePlatformDao();

        List<ParentExchangePlatformDataBean> dataBeans = dao.Ftplist();
        if (dataBeans==null ){
            logger.warn("����ϵͳ-�ϼ�����ƽ̨(FTP)��Ϣû�г�ʼ�����޷������ϱ�");
            return;
        }
        if(dataBeans.size() == 0) {
            logger.warn("����ϵͳ-�ϼ�����ƽ̨(FTP)û��ѡ�������ϱ����޷������ϱ�");
            return;
        }

        for(ParentExchangePlatformDataBean dataBean : dataBeans){
            String ip = dataBean.getPlatform_ip();
            int port = dataBean.getPlatform_port();

//            UpFtpClient upFtpClient = new UpFtpClient();
            FtpClient ftpClient = new FtpClient();
            ftpClient.init(ip, port, dataBean.getName(), dataBean.getPass());
            //TPlatreginfProcess
            /**
             * �ϱ�ǰ���ݴ���
             */
            try{
                new UplinkRunTimeProcess().process();
            }catch(RuntimeException e){
                logger.warn("�����ϱ�-�ձ�����-�ϱ�ǰ���ݴ����쳣��.",e);
            }
            new TPlatreginfProcess().process(ftpClient);   //okay ������

            //5.2.6������Ӧ���������
            new TbizrunstateProcess().process(ftpClient);  //okay ������

            //5.2.5 ϵͳ�������
            new TPlatstatinfProcess().process(ftpClient);    //okay ������              sysstatus

            //5.2.3 ϵͳ״̬
            new TPlatrunstateProcess().process(ftpClient); //okay ������

            //TinlinkstatinfProcess
            new TinlinkstatinfProcess().process(ftpClient); //okay ������

            //TinlinkrunstateProcess
            new TinlinkrunstateProcess().process(ftpClient);  //okay ������

            //TdevicerunstateProcess
            new TdevicerunstateProcess().process(ftpClient);   //okay ������

//            new TPlatalertinfProcess().process(ftpClient);
        }

        //TPlatalertinfProcess
        logger.info("�����ϱ�-�ձ�����-�ϱ��ɹ���");
        if(jobExecutionContext!=null){
           jobExecutionContext.setResult("�����ϱ�-�ձ�����-�ϱ��ɹ���");
        }
    }

    public static void main(String arg[]) throws Exception {
        FtpDayJobImp job = new FtpDayJobImp();
        job.execute(null);
    }

}
