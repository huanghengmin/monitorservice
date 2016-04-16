package com.inetec.ichange.service.monitor;

import com.inetec.common.exception.Ex;
import com.inetec.ichange.main.api.DataAttributes;
import com.inetec.ichange.main.api.Status;
import com.inetec.ichange.service.IServiceCommondProcess;
import com.inetec.ichange.service.monitor.uplink.job.WebServiceUpdateJobImp;
import com.inetec.ichange.service.monitor.uplink.utils.UpFileUtils;
import com.inetec.ichange.service.utils.ServiceUtil;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.quartz.JobExecutionException;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

/**
 * 接收设备配置文件
 * User: wxh
 * Date: 2012-04-10
 * Time: 9:54:27
 * To change this template use File | Settings | File Templates.
 */
public class ServiceMonitorDeviceConfig implements IServiceCommondProcess {
    Logger logger = Logger.getLogger(ServiceMonitorDeviceConfig.class);

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

        //String reportCommand = dataAttributes.getProperty(ServiceUtil.Str_MonitorCommond, "");//webservicecommand
        //监管中心需要上报的内容
        String filename = dataAttributes.getProperty(ServiceUtil.Str_Monitor_DeviceConfig_FileName, "");// deviceconfig filename 指令名称
        /* try {
            logger.info("recv dataAttribute:"+new String(dataAttributes.getContent()));
        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }*/
        logger.info("recv deviceconfig Upload command:" + filename);

        String status = "200";
        if (!filename.equalsIgnoreCase("")) {

            String files = UpFileUtils.getDataPath() + filename;
            File file = new File(files);

            try {
                if (!file.exists()) {
                    file.createNewFile();
                }
                logger.info("recv deviceconfig upload save filename:" + filename);
                if (dataAttributes.getResultData() != null) {
                    FileUtils.writeByteArrayToFile(file, DataAttributes.readInputStream(dataAttributes.getResultData()));
                }
                //new WebServiceUpdateJobImp(WebServiceUpdateJobImp.reportType_syscontrolrulesinf).execute(null);
            } catch (RuntimeException e) {
                e.printStackTrace();
            }catch (IOException e) {
                e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
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
