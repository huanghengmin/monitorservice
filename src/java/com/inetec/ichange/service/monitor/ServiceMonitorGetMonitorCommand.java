package com.inetec.ichange.service.monitor;

import com.inetec.common.exception.Ex;
import com.inetec.ichange.main.api.DataAttributes;
import com.inetec.ichange.main.api.Status;
import com.inetec.ichange.service.IServiceCommondProcess;
import com.inetec.ichange.service.monitor.uplink.job.WebServiceLowerCommandJobImp;
import com.inetec.ichange.service.monitor.uplink.job.WebServiceUpdateJobImp;
import com.inetec.ichange.service.utils.ServiceUtil;
import org.quartz.JobExecutionException;

import java.io.InputStream;

/**
 * 接收监管中心的命令
 * User: qxp
 * Date: 2010-9-5
 * Time: 9:54:27
 * To change this template use File | Settings | File Templates.
 */
public class ServiceMonitorGetMonitorCommand implements IServiceCommondProcess {
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

        String reportCommand = dataAttributes.getProperty(ServiceUtil.Str_Monitor_Lower_UpLink_Types, "");    //下级需要上报的内容

        String status = "200";
        if (!reportCommand.equalsIgnoreCase("")){
            WebServiceLowerCommandJobImp job = new WebServiceLowerCommandJobImp(reportCommand);      // 下级监管系统向监管中心上报
//            JobExecutionContext jobExecutionContext = new JobExecutionContext(null,null,null);
            try {
                job.execute(null);
            } catch (JobExecutionException e) {
                e.printStackTrace();
            }
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
