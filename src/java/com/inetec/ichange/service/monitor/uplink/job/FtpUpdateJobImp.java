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
 * Time: 上午9:14
 * To change this template use File | Settings | File Templates.
 * FTP手动上报
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
            logger.warn("集控系统-上级级联平台(FTP)信息没有初始化！无法级联上报");
            return;
        }
        String ip = dataBean.getPlatform_ip();
        int port = dataBean.getPlatform_port();

        FtpClient upFtpClient = new FtpClient();
        upFtpClient.init(ip, port, dataBean.getName(), dataBean.getPass());
        reportTypes = toList(uplinkTypes);

        for (String reportType : reportTypes) {
            if (reportType.equals(reportType_sysbizinf)) {
                new TBizInfProcess().process(upFtpClient);   //okay 待测试
                logger.info("级联上报-边界接入应用注册信息-变更操作-上报成功！");
            } else if (reportType.equals(reportType_syscontrolrulesinf)) {

                logger.info("级联上报-边界接入系统控制策略注册信息-变更操作-上报未开通！");
            } else if (reportType.equals(reportType_sysdeviceinf)) {
                new TdeviceinfProcess().process(upFtpClient);
                logger.info("级联上报-边界接入系统核心设备注册信息-变更操作-上报成功！");
            } else if (reportType.equals(reportType_sysoutlinkinf)) {
                new ToutlinkinfProcess().process(upFtpClient);
                logger.info("级联上报-边界接入系统外部链路注册信息-变更操作-上报成功！");
            } else if (reportType.equals(reportType_sysreginf)) {
                new TPlatreginfProcess().process(upFtpClient);
                logger.info("级联上报-边界接入系统注册信息-变更操作-上报成功！");
            } else if (reportType.equals(reportType_sysstrategyinf)) {

                logger.info("级联上报-边界接入系统安全策略注册信息-变更操作-上报未开通！");
            } else if (reportType.equals(reportType_systerminalinf)) {

                logger.info("级联上报-边界接入接入终端信息-变更操作-上报未开通！");
            } else {
                logger.info("级联上报-该上报类型不存在！");
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
