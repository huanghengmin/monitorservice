package com.inetec.ichange.service.monitor;

import com.inetec.common.exception.Ex;
import com.inetec.ichange.main.api.DataAttributes;
import com.inetec.ichange.main.api.Status;
import com.inetec.ichange.service.IServiceCommondProcess;
import com.inetec.ichange.service.monitor.uplink.databean.ParentExchangePlatformDao;
import com.inetec.ichange.service.monitor.uplink.databean.ParentExchangePlatformDataBean;
import com.inetec.ichange.service.monitor.uplink.job.FtpSysDelJobImp;
import com.inetec.ichange.service.monitor.uplink.job.WebServiceDelJobImp;
import com.inetec.ichange.service.utils.ServiceUtil;
import org.apache.log4j.Logger;
import org.quartz.JobExecutionException;

import java.io.InputStream;

/**
 *  删除 -- 已上报项查询
 * Created by IntelliJ IDEA.
 * User: bluesky
 * Date: 2010-9-5
 * Time: 9:54:27
 * To change this template use File | Settings | File Templates.
 */
public class ServiceMonitorSysDelService implements IServiceCommondProcess {
    private static Logger logger = Logger.getLogger(ServiceMonitorSysDelService.class);
    /**
     * @param input
     * @param dataAttributes
     * @throws com.inetec.common.exception.Ex
     */
    public DataAttributes process(InputStream input, DataAttributes dataAttributes) throws Ex {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    /**
     * @param input
     * @throws com.inetec.common.exception.Ex
     */
    public DataAttributes process(InputStream input) throws Ex {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    /**
     * @param fileName
     * @param dataAttributes
     * @throws com.inetec.common.exception.Ex
     */
    public DataAttributes process(String fileName, DataAttributes dataAttributes) throws Ex {
        long platformId = Long.parseLong(dataAttributes.getProperty(ServiceUtil.Str_Monitor_PlatformId, ""));
        ParentExchangePlatformDao dao = new ParentExchangePlatformDao();
        ParentExchangePlatformDataBean dataBean = dao.findById(platformId);
        String reportType = dataBean.getType();//dataBean
        String status = "200";
        if (platformId != 0) {
            logger.info("Service monitor  sysDelService call mmsc client begin.");
            try {
                //TODO report by ftp or webservice
                if(reportType.equals("Webservice")){
                    WebServiceDelJobImp job = new WebServiceDelJobImp(platformId);
                    try {
                        job.execute(null);
                        String json = "";
                        dataAttributes.setResultData(json.getBytes());
                    } catch (JobExecutionException e) {
                        e.printStackTrace();
                    }
                } else if(reportType.equals("Ftp")){
                    FtpSysDelJobImp job = new FtpSysDelJobImp(platformId);
                    try {
                        job.execute(null);
                    } catch (JobExecutionException e) {
                        e.printStackTrace();
                    }
                } else {
                    status = "500";
                    logger.info("Service monitor  sysDelService call mmsc client end failed.");
                    dataAttributes.setStatus(Status.S_Success);
                }
                logger.info("Service monitor  sysDelService call mmsc client end okay.");
            } catch (Exception e) {
                logger.warn("Service monitor all sysDelService call mmsc client error.", e);
            }
        } else
            status = "400";
        dataAttributes.setProperty(ServiceUtil.Str_ResponseProcessStatus, status);
        dataAttributes.setStatus(Status.S_Success);
        return dataAttributes;  //To
    }

    /**
     * @param fileName
     * @throws com.inetec.common.exception.Ex
     */
    public DataAttributes process(String fileName) throws Ex {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public int getProcessgetCapabilitie() {
        return 0;  //To change body of implemented methods use File | Settings | File Templates.
    }
}
