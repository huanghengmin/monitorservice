package com.inetec.ichange.service.monitor;

import com.crgs.entities.Sysqueryservice;
import com.inetec.common.exception.Ex;
import com.inetec.ichange.main.api.DataAttributes;
import com.inetec.ichange.main.api.Status;
import com.inetec.ichange.service.IServiceCommondProcess;
import com.inetec.ichange.service.monitor.databean.MonitorAgentDao;
import com.inetec.ichange.service.monitor.http.McHttpClient;
import com.inetec.ichange.service.monitor.uplink.databean.*;
import com.inetec.ichange.service.monitor.uplink.job.FtpUpdateJobImp;
import com.inetec.ichange.service.monitor.uplink.job.WebServiceQueryJobImp;
import com.inetec.ichange.service.monitor.uplink.job.WebServiceUpdateJobImp;
import com.inetec.ichange.service.utils.ServiceUtil;
import org.apache.log4j.Logger;
import org.quartz.JobExecutionException;

import java.io.InputStream;
import java.util.List;

/**
 * 已上报项查询
 * Created by IntelliJ IDEA.
 * User: bluesky
 * Date: 2010-9-5
 * Time: 9:54:27
 * To change this template use File | Settings | File Templates.
 */
public class ServiceMonitorSysQueryService implements IServiceCommondProcess {
    private static Logger logger = Logger.getLogger(ServiceMonitorSysQueryService.class);

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

        String objectName = dataAttributes.getProperty(ServiceUtil.Str_Monitor_ObjectName); //policeno
        long platformId = Long.parseLong(dataAttributes.getProperty(ServiceUtil.Str_Monitor_PlatformIdS, ""));
        ParentExchangePlatformDao dao = new ParentExchangePlatformDao();
        ParentExchangePlatformDataBean dataBean = dao.findById(platformId);
        String reportType = dataBean.getType();//dataBean
        String status = "200";
        logger.info("objectName:" + objectName);
        logger.info("Platformids:" + platformId);
        if (!objectName.equalsIgnoreCase("") && platformId != 0) {
            logger.info("Service monitor  sysQueryService call mmsc client begin.");
            try {
                if (reportType.equals("Webservice")) {
                    WebServiceQueryJobImp job = new WebServiceQueryJobImp(platformId, objectName);
                    job.execute(null);
                } else if (reportType.equals("Ftp")) {

                } else {
                    status = "500";
                    logger.info("Service monitor  sysQueryService call mmsc client end failed.");
                }
                dataAttributes.setStatus(Status.S_Success);
                logger.info("Service monitor  sysQueryService call mmsc client end okay.");
            } catch (Exception e) {
                logger.warn("Service monitor all sysQueryService call mmsc client error.", e);
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
