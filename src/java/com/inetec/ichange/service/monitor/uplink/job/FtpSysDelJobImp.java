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
 * User: 钱晓盼
 * Date: 12-8-10
 * Time: 下午3:39
 * To change this template use File | Settings | File Templates.
 * FTP删除已上报项上报
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
            logger.warn("集控系统-上级级联平台(FTP)信息没有初始化！无法级联上报");
            return;
        }
        if(dataBeans.size() == 0) {
            logger.warn("集控系统-上级级联平台(FTP)没有选择允许上报！无法级联上报");
            return;
        }

        for(ParentExchangePlatformDataBean dataBean : dataBeans){
            String ip = dataBean.getPlatform_ip();
            int port = dataBean.getPlatform_port();

            FtpClient upFtpClient = new FtpClient();
            upFtpClient.init(ip, port, dataBean.getName(), dataBean.getPass());
            new TSysDelServiceProcess().process(upFtpClient);
        }
        logger.info("级联上报-已上报项-删除操作-上报成功！");
        if(jobExecutionContext!=null) {
            jobExecutionContext.setResult("级联上报-已上报项-删除操作-上报成功！");
        }

    }


    public static void main(String arg[]) throws Exception {
        FtpSysDelJobImp job = new FtpSysDelJobImp(121);
        job.execute(null);
    }

}
