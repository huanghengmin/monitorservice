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
 * Time: 上午9:14
 * To change this template use File | Settings | File Templates.
 */
public class FtpDayJobImp implements Job {
    public static org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(FtpDayJobImp.class);

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

//            UpFtpClient upFtpClient = new UpFtpClient();
            FtpClient ftpClient = new FtpClient();
            ftpClient.init(ip, port, dataBean.getName(), dataBean.getPass());
            //TPlatreginfProcess
            /**
             * 上报前数据处理
             */
            try{
                new UplinkRunTimeProcess().process();
            }catch(RuntimeException e){
                logger.warn("级联上报-日报操作-上报前数据处理异常！.",e);
            }
            new TPlatreginfProcess().process(ftpClient);   //okay 待测试

            //5.2.6　接入应用运行情况
            new TbizrunstateProcess().process(ftpClient);  //okay 待测试

            //5.2.5 系统运行情况
            new TPlatstatinfProcess().process(ftpClient);    //okay 待测试              sysstatus

            //5.2.3 系统状态
            new TPlatrunstateProcess().process(ftpClient); //okay 待测试

            //TinlinkstatinfProcess
            new TinlinkstatinfProcess().process(ftpClient); //okay 待测试

            //TinlinkrunstateProcess
            new TinlinkrunstateProcess().process(ftpClient);  //okay 待测试

            //TdevicerunstateProcess
            new TdevicerunstateProcess().process(ftpClient);   //okay 待测试

//            new TPlatalertinfProcess().process(ftpClient);
        }

        //TPlatalertinfProcess
        logger.info("级联上报-日报操作-上报成功！");
        if(jobExecutionContext!=null){
           jobExecutionContext.setResult("级联上报-日报操作-上报成功！");
        }
    }

    public static void main(String arg[]) throws Exception {
        FtpDayJobImp job = new FtpDayJobImp();
        job.execute(null);
    }

}
