package com.inetec.ichange.service.monitor;

import com.inetec.common.exception.Ex;
import com.inetec.ichange.main.api.DataAttributes;
import com.inetec.ichange.main.api.Status;
import com.inetec.ichange.service.IServiceCommondProcess;
import com.inetec.ichange.service.monitor.databean.MonitorAgentDao;
import com.inetec.ichange.service.monitor.http.McHttpClient;
import com.inetec.ichange.service.utils.ServiceUtil;
import org.apache.log4j.Logger;

import java.io.InputStream;

/**
 *  终端系统信息查看 调用接口
 * Created by IntelliJ IDEA.
 * User: bluesky
 * Date: 2010-9-5
 * Time: 9:54:27
 * To change this template use File | Settings | File Templates.
 */
public class ServiceMonitorTerminalOsQuery implements IServiceCommondProcess {
    private static Logger logger = Logger.getLogger(ServiceMonitorTerminalOsQuery.class);
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

        String reportCommand = dataAttributes.getProperty("command", "");    //viewVPN
        String policeno = dataAttributes.getProperty(ServiceUtil.Str_Monitor_PoliceNo); //policeno
        String ip = dataAttributes.getProperty(ServiceUtil.Str_Monitor_IP); //ip

        String status = "200";
        if (!reportCommand.equalsIgnoreCase("")){
            McHttpClient mcclient = new McHttpClient();
            mcclient.init(new MonitorAgentDao().getMcHost());
            try {
                dataAttributes.setResultData(mcclient.osquery(policeno, ip));
                status = "200";
            } catch (Exception e) {
                logger.warn("Service monitor Print Screen call mc client error.", e);
            }
            status = "200";
        }else
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
