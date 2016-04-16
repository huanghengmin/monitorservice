package com.inetec.ichange.service.monitor;

import com.inetec.common.exception.Ex;
import com.inetec.ichange.main.api.DataAttributes;
import com.inetec.ichange.main.api.Status;
import com.inetec.ichange.service.IServiceCommondProcess;
import com.inetec.ichange.service.monitor.uplink.databean.ParentExchangePlatformDao;
import com.inetec.ichange.service.monitor.uplink.databean.ParentExchangePlatformDataBean;
import com.inetec.ichange.service.monitor.uplink.job.FtpUpdateJobImp;
import com.inetec.ichange.service.monitor.uplink.job.WebServiceUpdateJobImp;
import com.inetec.ichange.service.utils.ServiceUtil;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import java.io.InputStream;

/**
 * Created by IntelliJ IDEA.
 * User: bluesky
 * Date: 2010-9-5
 * Time: 9:54:27
 * To change this template use File | Settings | File Templates.
 */
public class ServiceMonitorUplinkProcess implements IServiceCommondProcess {
    public static org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(ServiceMonitorUplinkProcess.class);

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

        String uplinkTypes = dataAttributes.getProperty(ServiceUtil.Str_Monitor_UplinkTypes, "");
//        logger.info("ServiceMonitorUpLink receive " + ServiceUtil.Str_Monitor_PlatformId + ":" + dataAttributes.getProperty(ServiceUtil.Str_Monitor_PlatformId, "0"));
        long platformId = Long.parseLong(dataAttributes.getProperty(ServiceUtil.Str_Monitor_PlatformId, "-1"));
        String status = "200";
        if (!uplinkTypes.equalsIgnoreCase("")&&platformId>-1) {
            ParentExchangePlatformDao dao = new ParentExchangePlatformDao();
            ParentExchangePlatformDataBean dataBean = dao.findById(platformId);
            String reportType = dataBean.getType();//dataBean
            //TODO report by ftp or webservice
            if (reportType.equals("Webservice")) {
                WebServiceUpdateJobImp job = new WebServiceUpdateJobImp(uplinkTypes, platformId);
                try {
                    job.execute(null);
                } catch (JobExecutionException e) {
                    logger.error("webservice 上报错误",e);
                }
                status = "200";
            } else if (reportType.equals("Ftp")) {
                FtpUpdateJobImp job = new FtpUpdateJobImp(uplinkTypes, platformId);
                try {
                    job.execute(null);
                } catch (JobExecutionException e) {
                    logger.error("ftp 上报错误",e);
                }
                status = "200";
            }
        } else {
            status = "400";
            logger.info("ServiceMonitorUpLink receive null!");
        }
        dataAttributes.setProperty(ServiceUtil.Str_ResponseProcessStatus, status);
        dataAttributes.setStatus(Status.S_Success);
        return dataAttributes;  //To change body of implemented methods use File | Settings | File Templates.
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
