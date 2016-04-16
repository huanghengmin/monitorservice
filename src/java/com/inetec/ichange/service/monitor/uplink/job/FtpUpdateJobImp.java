package com.inetec.ichange.service.monitor.uplink.job;

import com.inetec.ichange.service.monitor.uplink.databean.ParentExchangePlatformDao;
import com.inetec.ichange.service.monitor.uplink.databean.ParentExchangePlatformDataBean;
import com.inetec.ichange.service.monitor.uplink.ftp.*;
import com.inetec.ichange.service.monitor.uplink.webservice.*;
import org.apache.log4j.Logger;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: bluesky
 * Date: 11-4-29
 * Time: ����9:14
 * To change this template use File | Settings | File Templates.
 * FTP�ֶ��ϱ�
 */
public class FtpUpdateJobImp implements Job {
    private static final Logger logger = Logger.getLogger(FtpUpdateJobImp.class);
    private static final String reportType_sysbizinf = "sysbizinf";
    private static final String reportType_syscontrolrulesinf = "syscontrolrulesinf";
    private static final String reportType_sysdeviceinf = "sysdeviceinf";
    private static final String reportType_sysoutlinkinf = "sysoutlinkinf";
    private static final String reportType_sysreginf = "sysreginf";
    private static final String reportType_sysstrategyinf = "sysstrategyinf";
    private static final String reportType_systerminalinf = "systerminalinf";
    private String uplinkTypes;
    private long id;
    private List<String> reportTypes = new ArrayList<String>();

    public FtpUpdateJobImp(String uplinkTypes, long id) {
        this.uplinkTypes = uplinkTypes;
        this.id = id;
    }

    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {

        ParentExchangePlatformDao dao = new ParentExchangePlatformDao();

        ParentExchangePlatformDataBean dataBean = dao.findById(id);
        if(dataBean==null){
            logger.warn("����ϵͳ-�ϼ�����ƽ̨(FTP)��Ϣû�г�ʼ�����޷������ϱ�");
            return;
        }
        String ip = dataBean.getPlatform_ip();
        int port = dataBean.getPlatform_port();

        FtpClient upFtpClient = new FtpClient();
        upFtpClient.init(ip, port, dataBean.getName(), dataBean.getPass());
        reportTypes = toList(uplinkTypes);

        for (String reportType : reportTypes) {
            if (reportType.equals(reportType_sysbizinf)) {
                new TBizInfProcess().process(upFtpClient);   //okay ������
                logger.info("�����ϱ�-�߽����Ӧ��ע����Ϣ-�������-�ϱ��ɹ���");
            } else if (reportType.equals(reportType_syscontrolrulesinf)) {

                logger.info("�����ϱ�-�߽����ϵͳ���Ʋ���ע����Ϣ-�������-�ϱ�δ��ͨ��");
            } else if (reportType.equals(reportType_sysdeviceinf)) {
                new TdeviceinfProcess().process(upFtpClient);
                logger.info("�����ϱ�-�߽����ϵͳ�����豸ע����Ϣ-�������-�ϱ��ɹ���");
            } else if (reportType.equals(reportType_sysoutlinkinf)) {
                new ToutlinkinfProcess().process(upFtpClient);
                logger.info("�����ϱ�-�߽����ϵͳ�ⲿ��·ע����Ϣ-�������-�ϱ��ɹ���");
            } else if (reportType.equals(reportType_sysreginf)) {
                new TPlatreginfProcess().process(upFtpClient);
                logger.info("�����ϱ�-�߽����ϵͳע����Ϣ-�������-�ϱ��ɹ���");
            } else if (reportType.equals(reportType_sysstrategyinf)) {

                logger.info("�����ϱ�-�߽����ϵͳ��ȫ����ע����Ϣ-�������-�ϱ�δ��ͨ��");
            } else if (reportType.equals(reportType_systerminalinf)) {

                logger.info("�����ϱ�-�߽��������ն���Ϣ-�������-�ϱ�δ��ͨ��");
            } else {
                logger.info("�����ϱ�-���ϱ����Ͳ����ڣ�");
            }
        }

    }

    private List<String> toList(String uplinkTypes) {
        if (uplinkTypes != null) {
            String[] strs = uplinkTypes.split(":");
            if (strs.length > 1) {
                for (int i = 0; i < strs.length; i++) {
                    reportTypes.add(strs[i]);
                }
            } else {
                reportTypes.add(uplinkTypes);
            }
            return reportTypes;
        }
        return null;
    }

    public static void main(String arg[]) throws Exception {
        FtpMonthJobImp job = new FtpMonthJobImp();
        job.execute(null);
    }

}