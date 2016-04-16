package com.inetec.ichange.service.monitor;

import com.crgs.entities.Sysclientservice;
import com.inetec.common.exception.Ex;
import com.inetec.ichange.main.api.DataAttributes;
import com.inetec.ichange.main.api.Status;
import com.inetec.ichange.service.IServiceCommondProcess;
import com.inetec.ichange.service.monitor.uplink.job.WebServiceCommandJobImp;
import com.inetec.ichange.service.monitor.uplink.job.WebServiceLowerCommandJobImp;
import com.inetec.ichange.service.utils.ServiceUtil;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import java.io.IOException;
import java.io.InputStream;

/**
 * ���ռ�����ĵ�����
 * User: qxp
 * Date: 2010-9-5
 * Time: 9:54:27
 * To change this template use File | Settings | File Templates.
 */
public class ServiceMonitorWebServiceCommand implements IServiceCommondProcess {
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


        String reportCommand = dataAttributes.getProperty("command", "");//webservicecommand
        //���������Ҫ�ϱ�������
        String idSystem = dataAttributes.getProperty(ServiceUtil.Str_Monitor_Web_IdSystem);// idSysterm ָ������
        String idTerminal = dataAttributes.getProperty(ServiceUtil.Str_Monitor_Web_IdTerminal);// idTerminal ָ������
        String tAction = dataAttributes.getProperty(ServiceUtil.Str_Monitor_Web_Taction);// Taction ָ������
        Sysclientservice s = new Sysclientservice();
        s.setIdsystem(idSystem);

        if (idTerminal == null || idTerminal.equalsIgnoreCase("")) {
            idTerminal = "0";
        }

        s.setIdterminal(Long.valueOf(idTerminal));
        s.setTaction(tAction);
        String status = "200";
        if (!reportCommand.equalsIgnoreCase("")) {
            WebServiceCommandJobImp job = new WebServiceCommandJobImp(s);      // �¼����ϵͳ���������ϱ�
            try {
                job.execute(null);
            } catch (JobExecutionException e) {
                e.printStackTrace();
            }
            status = "200";
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
