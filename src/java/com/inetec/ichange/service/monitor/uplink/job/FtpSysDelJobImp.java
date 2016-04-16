package com.inetec.ichange.service.monitor.uplink.job;

import com.inetec.ichange.service.monitor.uplink.databean.ParentExchangePlatformDao;
import com.inetec.ichange.service.monitor.uplink.databean.ParentExchangePlatformDataBean;
import com.inetec.ichange.service.monitor.uplink.ftp.*;
import org.apache.log4j.Logger;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Ǯ����
 * Date: 12-8-10
 * Time: ����3:39
 * To change this template use File | Settings | File Templates.
 * FTPɾ�����ϱ����ϱ�
 */
public class FtpSysDelJobImp implements Job {
    private static final Logger logger = Logger.getLogger(FtpSysDelJobImp.class);

    private long id;

    public FtpSysDelJobImp(long id) {
        this.id = id;
    }

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
            new TSysDelServiceProcess().process(upFtpClient);
        }
        logger.info("�����ϱ�-���ϱ���-ɾ������-�ϱ��ɹ���");
        if(jobExecutionContext!=null) {
            jobExecutionContext.setResult("�����ϱ�-���ϱ���-ɾ������-�ϱ��ɹ���");
        }

    }


    public static void main(String arg[]) throws Exception {
        FtpSysDelJobImp job = new FtpSysDelJobImp(121);
        job.execute(null);
    }

}
