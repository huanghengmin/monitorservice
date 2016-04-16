package com.inetec.ichange.service.monitor.syslog;

import org.productivity.java.syslog4j.server.SyslogServer;
import org.productivity.java.syslog4j.server.SyslogServerConfigIF;
import org.productivity.java.syslog4j.server.SyslogServerEventHandlerIF;
import org.productivity.java.syslog4j.server.SyslogServerIF;

/**
 * Created by IntelliJ IDEA.
 * User: bluesky
 * Date: 2010-9-7
 * Time: 14:43:42
 * To change this template use File | Settings | File Templates.
 */
public class SysLogMonitorService extends Thread {

    private boolean isRun = true;
    private SyslogServerIF udpsyslogserver;
    private String host;
    private int port;
    private String charset;
    private SyslogServerConfigIF syslogServerConfig;
    SysLogRecevieServerEventHandler handler;
    private String platformname;


    public SysLogMonitorService() {


    }

    public boolean isRun() {
        return isRun;
    }


    public void init(String host, int port, String charset, String platformname) {
        this.host = host;
        this.port = port;

        SysLogRecevieServerEventHandler handler = new SysLogRecevieServerEventHandler();
        udpsyslogserver = SyslogServer.getThreadedInstance(SyslogServer.UDP);
        syslogServerConfig = udpsyslogserver.getConfig();
        syslogServerConfig.setHost(this.host);
        syslogServerConfig.setPort(this.port);
        syslogServerConfig.setCharSet(charset);
        this.platformname = platformname;
        handler.setPlatform(platformname);
        syslogServerConfig.addEventHandler((SyslogServerEventHandlerIF) handler);
       //syslogServerConfig.setUseDaemonThread(true);


    }

    public void run() {
        //syslogserver = SyslogServer.createThreadedInstance("tcpsyslog", config);s
        udpsyslogserver.run();
        while (isRun) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                //okay
            }
        }

    }

    /**
     *
     */
    public void close() {
        isRun = false;

        if (udpsyslogserver != null) {
            udpsyslogserver.shutdown();
        }

    }

    public static void main(String arg[]) throws Exception {
        SysLogMonitorService syslog = new SysLogMonitorService();
        System.setProperty("privatenetwork", "true");
        syslog.init("0.0.0.0", 1514, "gbk", "");

        //syslog("192.168.1.86", 514);
        syslog.start();
        while (true) {
            Thread.sleep(100);
        }
    }


}
