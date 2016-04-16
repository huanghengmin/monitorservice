package com.inetec.ichange.logs.service;


import com.inetec.common.config.ConfigLoadTask;
import com.inetec.common.config.nodes.HostPort;
import com.inetec.common.exception.Ex;
import com.inetec.common.logs.LogHelper;
import com.inetec.common.logs.LogHelperNew;
import com.inetec.common.security.License;
import com.inetec.ichange.service.monitor.business.BusinessLogImp;
import com.inetec.ichange.service.monitor.business.BusinessLogZDImp;
import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;
import org.productivity.java.syslog4j.Syslog;
import org.productivity.java.syslog4j.SyslogIF;

import java.io.*;
import java.util.HashMap;
import java.util.HashSet;

/**
 * Created by IntelliJ IDEA.
 * User: bluesky
 * Date: 12-2-21
 * Time: ÏÂÎç3:08
 * To change this template use File | Settings | File Templates.
 */
public class SysLogService extends Thread {
    public static final Logger m_logger = Logger.getLogger(SysLogService.class);
    private HashMap m_list = new HashMap();
    private final Object m_lock = new Object();
    public static final int I_MaxSize = 10 * 1024;
    private final int m_port = 8090;
    private boolean m_runing = false;
    private BusinessLogZDImp businessLogImp;

    public void run() {
        m_runing = true;
        m_logger.info("SysLogService start runing.");

        while (m_runing) {

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                //okay
            }
        }

    }

    public void sendData() {
    }

    public void close() {
        m_runing = false;
    }


    public void addLog(byte[] log) throws IOException {
        try {
            if (businessLogImp == null) {
                businessLogImp = new BusinessLogZDImp();
                businessLogImp.setPlatformName(License.getTpType());
                //new Thread(businessLogImp).start();
            }
            LogHelperNew logs = LogHelperNew.getLogHelperObject(log);


            HostPort[] syslogclients = ConfigLoadTask.getConfigTask().getRoot().getIchangeUtils().getSysLogClientSet();
            m_logger.info("Syslog Client size:" + syslogclients.length);
            //System.out.println("Syslog Client size:" + syslogclients.length);
            for (int i = 0; i < syslogclients.length; i++) {
                SyslogIF syslog = Syslog.getInstance("udp");
                syslog.getConfig().setHost(syslogclients[i].getHost());
                syslog.getConfig().setPort(syslogclients[i].getPort());
                syslog.getConfig().setCharSet("GBK");
                m_logger.info("Syslog message:" + logs.toByteArraysOfSyslog("GBK"));
                if (logs.level.equalsIgnoreCase("warn")) {
                    syslog.warn(logs.toByteArraysOfSyslog("GBK"));
                }
                if (logs.level.equalsIgnoreCase("info")) {
                    syslog.info(logs.toByteArraysOfSyslog("GBK"));
                }
                if (logs.level.equalsIgnoreCase("error")) {
                    syslog.error(logs.toByteArraysOfSyslog("GBK"));
                }
                if (logs.level.equalsIgnoreCase("debug")) {
                    syslog.warn(logs.toByteArraysOfSyslog("GBK"));
                }
                syslog.flush();
                syslog.shutdown();
            }

            //businessLogImp.processILogFormat();


        } catch (Ex ex) {
            m_logger.warn("Syslog send to Syslog Server error. ", ex);
        }
    }

    public void addLog(InputStream in) throws IOException {
        addLog(IOUtils.toByteArray(in));
        in.close();
    }

}
