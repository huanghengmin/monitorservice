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
 * cms�����ն��û�ȫ���б�
 * User: qxp
 * Date: 2010-9-5
 * Time: 9:54:27
 * To change this template use File | Settings | File Templates.
 */
public class ServiceMonitorAllVPN implements IServiceCommondProcess {
    private static Logger logger = Logger.getLogger(ServiceMonitorAllVPN.class);

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

        String beginno = dataAttributes.getProperty(ServiceUtil.Str_Monitor_BeginNo); //beginno
        String endno = dataAttributes.getProperty(ServiceUtil.Str_Monitor_EndNo); //endno
        String pagesize = dataAttributes.getProperty(ServiceUtil.Str_Monitor_PageSize); //pagesize
        String status = dataAttributes.getProperty(ServiceUtil.Str_Monitor_Status);//status
        String ifblock = dataAttributes.getProperty(ServiceUtil.Str_Monitor_IfBlock);//ifblock
        String cardType = dataAttributes.getProperty(ServiceUtil.Str_Monitor_CardType); //cardType   tf
        String policeValue = dataAttributes.getProperty(ServiceUtil.Str_Monitor_PoliceValue); //policeValue  10101 / ����
        String policeKey = dataAttributes.getProperty(ServiceUtil.Str_Monitor_PoliceKey); //policeKey    policeno  / policeName

        String _status = "200";
        logger.info("Service monitor all vpn call mc client begin.");
        if (!pagesize.equalsIgnoreCase("")) {
            logger.info("Service monitor all vpn call mc client begin.");
              McHttpClient mcclient = new McHttpClient();
            mcclient.init(new MonitorAgentDao().getMcHost());
            try {
                byte[] da = mcclient.vpnAll(beginno, endno, Integer.parseInt(pagesize));
                dataAttributes.setResultData(da);
                mcclient.close();
                String temp = new String(da);
                logger.info(temp);
                _status = "200";
                dataAttributes.setStatus(Status.S_Success);
            } catch (Exception e) {
                logger.warn("Service monitor all vpn call mc client error.", e);
            }
            //dataAttributes.setResultData(Service.terminainfService.terminalCache.getAllList(beginno, endno, Integer.parseInt(pagesize)).getBytes());

            _status = "200";
        } else
            _status = "400";
        dataAttributes.setProperty(ServiceUtil.Str_ResponseProcessStatus, _status);

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
