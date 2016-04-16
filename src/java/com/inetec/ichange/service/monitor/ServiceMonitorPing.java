package com.inetec.ichange.service.monitor;

import com.inetec.common.exception.Ex;
import com.inetec.ichange.main.api.DataAttributes;
import com.inetec.ichange.main.api.Status;
import com.inetec.ichange.service.IServiceCommondProcess;
import com.inetec.ichange.service.monitor.ping.PingMonitorService;
import com.inetec.ichange.service.utils.ServiceResponse;
import com.inetec.ichange.service.utils.ServiceUtil;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 * User: bluesky
 * Date: 2010-9-5
 * Time: 9:50:23
 * To change this template use File | Settings | File Templates.
 */
public class ServiceMonitorPing implements IServiceCommondProcess {
    private static Map ipMap = PingMonitorService.ipMap;
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
        String ip = dataAttributes.getProperty(ServiceUtil.Str_Monitor_deviceip, "");
        String neistation =  dataAttributes.getProperty(ServiceUtil.Str_Monitor_netstation,"");
        String status = "200";
        if (!ip.equalsIgnoreCase("")&&neistation.equalsIgnoreCase("i")){
            String result = String.valueOf(ipMap.get(ip));
            dataAttributes.setResultData(result.getBytes());
            status = "200";
        } else if(!ip.equalsIgnoreCase("")&&neistation.equalsIgnoreCase("e")){
            byte[] data = null;
            String[][] params = new String[][] {
                    { "SERVICEREQUESTTYPE", "SERVICECONTROLPOST" },
                    { "Command", "ipping" },
                    { "deviceip", ip } };
            ServiceResponse responseMc = null;
            try {
                responseMc = ServiceUtil.callService(params);
            } catch (IOException e) {
                e.printStackTrace();
            }
            String result = responseMc.getData();
            status = String.valueOf(responseMc.getCode());
            dataAttributes.setResultData(result.getBytes());        }
        else
            status = "400";
        dataAttributes.setProperty(ServiceUtil.Str_ResponseProcessStatus, status);
        dataAttributes.setStatus(Status.S_Success);
        return dataAttributes;  //To change body of implemented methods use File | Settings | 
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
