package com.inetec.ichange.service.monitor;

import com.inetec.common.exception.Ex;
import com.inetec.ichange.main.api.DataAttributes;
import com.inetec.ichange.main.api.Status;
import com.inetec.ichange.service.IServiceCommondProcess;
import com.inetec.ichange.service.Service;
import com.inetec.ichange.service.monitor.databean.DeviceDataBean;
import com.inetec.ichange.service.monitor.ping.PingMonitorService;
import com.inetec.ichange.service.monitor.snmp.InSnmpMonitorService;
import com.inetec.ichange.service.utils.ServiceResponse;
import com.inetec.ichange.service.utils.ServiceUtil;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 * User: bluesky
 * Date: 2010-9-5
 * Time: 9:56:48
 * To change this template use File | Settings | File Templates.
 */
public class ServiceMonitorDeviceMonitor implements IServiceCommondProcess {
    private static Logger logger = Logger.getLogger(ServiceMonitorDeviceMonitor.class);
    private static Map ipMap = PingMonitorService.ipMap;


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
        String id = dataAttributes.getProperty(ServiceUtil.Str_Monitor_deviceid, "");
        String neistation = dataAttributes.getProperty(ServiceUtil.Str_Monitor_netstation, "");
        String snmpCommond = dataAttributes.getProperty(ServiceUtil.Str_Monitor_SnmpCommand, "");
        String status = "200";
        //判断内外网
        if (neistation.equalsIgnoreCase("i")) {
            if (!ip.equalsIgnoreCase("") && !id.equalsIgnoreCase("")) {
                DeviceDataBean deviceDataBean = InSnmpMonitorService.dataset.getDeviceDataBean(id);
//            if (bean != null && bean.getStatus() == DeviceDataBean.I_Status_OK) {
//                String result = bean.toJsonString()+",ip'"+ipMap.get(ip)+"'}";
//                dataAttributes.setResultData(result.getBytes());
//                status = String.valueOf(bean.getStatus());
//            } else {
//                status = "503";
//            }
                if (deviceDataBean.getMaxcon() != 0 || deviceDataBean.getCurrentcon() != 0 || deviceDataBean.getCpu() != 0 || deviceDataBean.getMem() != 0 || deviceDataBean.getMem_total() != 0 || deviceDataBean.getDisk() != 0 || deviceDataBean.getDisk_total() != 0) {
                    String result = deviceDataBean.toJsonString();
                    result = result.substring(0, result.length() - 1);
                    result = result + ",'ipPing':" + ipMap.get(ip) + "}";
                    status = "200";
                    dataAttributes.setResultData(result.getBytes());
                } else {
                    String result = deviceDataBean.toJsonString();
                    result = result.substring(0, result.length() - 1);
                    result = result + ",'ipPing':" + ipMap.get(ip) + "}";
                    status = "503";
                    dataAttributes.setResultData(result.getBytes());
                }
            } else
                status = "503";
        } else if (neistation.equalsIgnoreCase("e")) {
            byte[] data = null;
            String[][] params = new String[][]{
                    {"SERVICEREQUESTTYPE", "SERVICECONTROLPOST"},
                    {"Command", "devicemonitor"},
                    {"deviceip", ip}};
            ServiceResponse responseMc = null;
            try {
                responseMc = ServiceUtil.callService(params);
            } catch (IOException e) {
                e.printStackTrace();
            }
            String result = responseMc.getData();
            status = String.valueOf(responseMc.getCode());
            dataAttributes.setResultData(result.getBytes());
        } //重启snmp服务
        else if ("snmpRestart".equalsIgnoreCase(snmpCommond)) {
            InSnmpMonitorService.isRun = false;
            Service.inSnmpIsRun = false;
            Service.inSnmpService = null;
            Service.runInSnmpMonitorService();
        } else
            status = "503";
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
