package com.inetec.ichange.service.monitor;

import com.inetec.common.exception.Ex;
import com.inetec.ichange.main.api.DataAttributes;
import com.inetec.ichange.main.api.Status;
import com.inetec.ichange.service.IServiceCommondProcess;
import com.inetec.ichange.service.monitor.business.BusinessPlatformService;
import com.inetec.ichange.service.monitor.databean.BusinessDataBean;
import com.inetec.ichange.service.utils.ServiceUtil;
import org.apache.log4j.Logger;

import java.io.InputStream;

/**
 * Created by IntelliJ IDEA.
 * User: bluesky
 * Date: 2010-9-5
 * Time: 9:56:18
 * To change this template use File | Settings | File Templates.
 */
public class ServiceMonitorBusinessVideo implements IServiceCommondProcess {
    Logger logger = Logger.getLogger(ServiceMonitorBusinessVideo.class);

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
        String busname = dataAttributes.getProperty(ServiceUtil.Str_Monitor_businessname, "");
        logger.info("Business Type monitor:" + busname);
        String status = "200";
        if (!busname.equalsIgnoreCase("")) {
            BusinessDataBean bean = BusinessPlatformService.datavideoset.getBusinessDataBeanByID(busname);
            logger.info("Business Type monitor value:" + bean.toJsonString());
            if (bean != null) {
                dataAttributes.setResultData(bean.toJsonString().getBytes());
                status ="200";
            } else {
                status = "500";
            }


        } else
            status = "400";
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
