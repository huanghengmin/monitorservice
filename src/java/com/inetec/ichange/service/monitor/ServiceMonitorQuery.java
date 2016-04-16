package com.inetec.ichange.service.monitor;

import com.inetec.common.exception.Ex;
import com.inetec.ichange.main.api.DataAttributes;
import com.inetec.ichange.main.api.Status;
import com.inetec.ichange.service.IServiceCommondProcess;
import com.inetec.ichange.service.utils.ServiceUtil;

import java.io.InputStream;

/**
 *  进程查看 网络查看 操作系统查看 调用接口
 * Created by IntelliJ IDEA.
 * User: qxp
 * Date: 2012-04-05
 * Time: 10:21:27
 * To change this template use File | Settings | File Templates.
 */
public class ServiceMonitorQuery implements IServiceCommondProcess {
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

        String command = dataAttributes.getProperty("command", "");    //
        String userId = dataAttributes.getProperty(ServiceUtil.Str_Monitor_UserId); //userId
        String idTerminal = dataAttributes.getProperty(ServiceUtil.Str_Monitor_IdTerminal); //idTerminal


        String status = "200";
        if (!command.equalsIgnoreCase("")){
            if(command.equals("osQuery")){      //操作系统查看

            }else if(command.equals("netQuery")){   //网络查看

            }else if(command.equals("processQuery")){   //进程查看

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
