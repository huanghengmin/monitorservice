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
 * Time: 上午9:14
 * To change this template use File | Settings | File Templates.
 */
public class FtpMonthJobImp implements Job {
    public static org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(FtpMonthJobImp.class);

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

            // TPlatinfofProcess
            new TPlatinfofProcess().process(upFtpClient);   //okay

            //  TInlinkinfProcess
            new TInlinkinfProcess().process(upFtpClient);    //okay

            //ToutlinkinfProcess
            new ToutlinkinfProcess().process(upFtpClient);      //okay
            //  TBizInfProcess
            new TBizInfProcess().process(upFtpClient);       //okay 待测试

            //TdeviceinfProcess
            new TdeviceinfProcess().process(upFtpClient);    //okay
        }

        //TbizproinfProcess
        //okay 待测试
        logger.info("级联上报-月报操作-上报成功！");
        if(jobExecutionContext!=null){
            jobExecutionContext.setResult("级联上报-月报操作-上报成功！");
        }

    }

    public static void main(String arg[]) throws Exception {
        FtpMonthJobImp job = new FtpMonthJobImp();
        job.execute(null);
    }
}
