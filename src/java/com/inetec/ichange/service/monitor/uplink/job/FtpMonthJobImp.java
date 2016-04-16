package com.inetec.ichange.service.monitor.uplink.job;

import com.inetec.ichange.service.monitor.uplink.databean.ParentExchangePlatformDao;
import com.inetec.ichange.service.monitor.uplink.databean.ParentExchangePlatformDataBean;
import com.inetec.ichange.service.monitor.uplink.ftp.*;
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
public class FtpMonthJobImp implements Job {
    public static org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(FtpMonthJobImp.class);

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

            FtpClient upFtpClient = new FtpClient();
            upFtpClient.init(ip, port, dataBean.getName(), dataBean.getPass());

            // TPlatinfofProcess
            new TPlatinfofProcess().process(upFtpClient);   //okay

            //  TInlinkinfProcess
            new TInlinkinfProcess().process(upFtpClient);    //okay

            //ToutlinkinfProcess
            new ToutlinkinfProcess().process(upFtpClient);      //okay
            //  TBizInfProcess
            new TBizInfProcess().process(upFtpClient);       //okay ������

            //TdeviceinfProcess
            new TdeviceinfProcess().process(upFtpClient);    //okay
        }

        //TbizproinfProcess
        //okay ������
        logger.info("�����ϱ�-�±�����-�ϱ��ɹ���");
        if(jobExecutionContext!=null){
            jobExecutionContext.setResult("�����ϱ�-�±�����-�ϱ��ɹ���");
        }

    }

    public static void main(String arg[]) throws Exception {
        FtpMonthJobImp job = new FtpMonthJobImp();
        job.execute(null);
    }
}
