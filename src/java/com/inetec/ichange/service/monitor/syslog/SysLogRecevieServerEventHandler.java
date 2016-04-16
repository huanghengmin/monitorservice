package com.inetec.ichange.service.monitor.syslog;

import com.inetec.ichange.service.monitor.business.BusinessLogImp;

import com.inetec.ichange.service.monitor.business.BusinessLogZDImp;
import com.inetec.ichange.service.monitor.syslog.format.ILogFormat;
import com.inetec.ichange.service.monitor.syslog.format.LogFormatFactory;
import org.apache.log4j.Logger;
import org.productivity.java.syslog4j.server.SyslogServerEventHandlerIF;
import org.productivity.java.syslog4j.server.SyslogServerEventIF;
import org.productivity.java.syslog4j.server.SyslogServerIF;
import org.productivity.java.syslog4j.util.SyslogUtility;

import java.io.UnsupportedEncodingException;
import java.net.SocketAddress;
import java.util.Date;

/**
 * SysLog??????????????
 * Created by IntelliJ IDEA.
 * User: bluesky
 * Date: 2010-9-7
 * Time: 15:22:03
 * To change this template use File | Settings | File Templates.
 */
public class SysLogRecevieServerEventHandler implements SyslogServerEventHandlerIF {
    private static final long serialVersionUID = 6036415838696050746L;
    private static final Logger logger = Logger.getLogger(SysLogRecevieServerEventHandler.class);

    protected BusinessLogImp businessLogImp;
    protected BusinessLogZDImp ydjwbusinessLogImp;
    protected boolean isprivate = false;

    public void setPlatform(String platform) {
        businessLogImp.setPlatformName(platform);
        ydjwbusinessLogImp.setPlatformName(platform);
    }


    public SysLogRecevieServerEventHandler() {
        businessLogImp = new BusinessLogImp();
        ydjwbusinessLogImp = new BusinessLogZDImp();
        if (System.getProperty("privatenetwork") != null && System.getProperty("privatenetwork").equalsIgnoreCase("true")) {
            isprivate = true;


        }
        //businessLogImp.setPlatformName("zdjr");

    }

    public Object sessionOpened(SyslogServerIF syslogServerIF, SocketAddress socketAddress) {
        return null;

    }


    public void event(Object paramObject, SyslogServerIF paramSyslogServerIF, SocketAddress paramSocketAddress, SyslogServerEventIF paramSyslogServerEventIF) {
        String str1 = (paramSyslogServerEventIF.getDate() == null ? new Date() : paramSyslogServerEventIF.getDate()).toString();
        String str2 = SyslogUtility.getFacilityString(paramSyslogServerEventIF.getFacility());
        String str3 = SyslogUtility.getLevelString(paramSyslogServerEventIF.getLevel());
        /*  if (isprivate && paramSyslogServerEventIF.getHost().equalsIgnoreCase("127.0.0.1")) {

System.out.println(str1 + " " + str2 + "" + str3 + " " + paramSyslogServerEventIF.getMessage());*/
        String message = "";

        message = new String(paramSyslogServerEventIF.getRaw());
        if (message.lastIndexOf(paramSyslogServerEventIF.getMessage()) >= 0)
            message = message.substring(message.lastIndexOf(paramSyslogServerEventIF.getMessage()));

        logger.info(str1 + " " + str2 + "" + str3 + " " + message);
        if (str3.equalsIgnoreCase("UNKNOWN")) {
            logger.info("syslog level is unkonwn");
            return;
        }
        try {
            ILogFormat logFormat = LogFormatFactory.getLogFormat(message, str3);
            //logger.info("Factory create logFormat");
            ydjwbusinessLogImp.processILogFormat(logFormat);
        } catch (RuntimeException e) {
            logger.info("Syslog recv process error.", e);
        }

        return;
        /*  } else {
            System.out.println(str1 + " " + str2 + "" + str3 + " " + paramSyslogServerEventIF.getMessage());
            logger.info(str1 + " " + str2 + "" + str3 + " " + paramSyslogServerEventIF.getMessage());
            return;
            //EquipmentLog device = new EquipmentLog();
        }*/


    }

    public void event(SyslogServerIF ss, SyslogServerEventIF ss1) {

    }

    public void exception(Object o, SyslogServerIF syslogServerIF, SocketAddress socketAddress, Exception e) {
        //To change body of implemented methods use File | Settings | File Templates.
        logger.warn("Syslog recv process:" + e.toString());
        System.out.println("Session exception:" + e.toString());
    }

    public void sessionClosed(Object o, SyslogServerIF syslogServerIF, SocketAddress socketAddress) {
        //To change body of implemented methods use File | Settings | File Templates
        // logger.warn("Syslog recv process:"+e.toString());
        logger.warn("Syslog recv process session closed:");
        System.out.println("session Closed.");
    }
}
