package com.ichange.service;

import com.ichange.common.utils.AuditClient;
import com.inetec.common.exception.Ex;
import com.inetec.ichange.logs.service.LogServiceThread;
import com.inetec.ichange.service.monitor.business.BusinessLogZDImp;
import com.inetec.ichange.service.monitor.syslog.format.LogFormatFactory;
import junit.framework.TestCase;
import org.apache.log4j.Logger;
import org.productivity.java.syslog4j.Syslog;
import org.productivity.java.syslog4j.SyslogIF;

import java.io.IOException;
import java.sql.Timestamp;

/**
 * Created by IntelliJ IDEA.
 * User: bluesky
 * Date: 2007-12-25
 * Time: 14:19:31
 * To change this template use File | Settings | File Templates.
 */
public class TestSyslogProcess extends TestCase {
    private final static Logger m_log = Logger.getLogger(TestSyslogProcess.class);
    LogServiceThread service = new LogServiceThread();


    public TestSyslogProcess() {

    }
    public void testSyslog(){
        BusinessLogZDImp ydjwbusinessLogImp=new BusinessLogZDImp();
        ydjwbusinessLogImp.setPlatformName("test");
        String syslog="deviceid=vpn ip=192.168.20.20 KSSL-MAIN SSL-HRP[18042]: [Thu Apr 12 11:00:08 2012] [info] Connection to child 8 established (server www.mysite.com:4443, client 171.168.1.1)";
        ydjwbusinessLogImp.processILogFormat(LogFormatFactory.getLogFormat(syslog, "info"));

        System.out.println("TimeStamp:"+ Timestamp.valueOf("2012-04-12 11:49:27"));

    }
    public void testSyslogOut(){
        SyslogIF log= Syslog.getInstance("udp");
        log.getConfig().setHost("127.0.0.1");
        log.getConfig().setPort(1514);
        log.getConfig().setCharSet("gbk");
        String syslog="deviceid=vpn ip=192.168.20.20 KSSL-MAIN SSL-HRP[18042]: [Thu Apr 12 11:00:08 2012] [info] Connection to child 8 established (server www.mysite.com:4443, client 171.168.1.1)";
        for(int i=0;i<10;i++){
            log.info(syslog);
            log.flush();
        }


    }


}
