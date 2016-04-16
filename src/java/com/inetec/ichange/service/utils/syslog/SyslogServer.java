package com.inetec.ichange.service.utils.syslog;


import com.inetec.ichange.service.monitor.business.BusinessLogImp;
import com.inetec.ichange.service.monitor.business.BusinessLogZDImp;
import com.inetec.ichange.service.monitor.syslog.format.ILogFormat;
import com.inetec.ichange.service.monitor.syslog.format.LogFormatFactory;
import com.inetec.ichange.service.utils.syslog.code.SyslogCodecFactory;
import org.apache.log4j.Logger;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.executor.ExecutorFilter;
import org.apache.mina.filter.executor.OrderedThreadPoolExecutor;
import org.apache.mina.transport.socket.DatagramSessionConfig;
import org.apache.mina.transport.socket.nio.NioDatagramAcceptor;
import org.productivity.java.syslog4j.Syslog;
import org.productivity.java.syslog4j.SyslogIF;
import org.productivity.java.syslog4j.util.SyslogUtility;

import java.net.InetSocketAddress;
import java.util.concurrent.ExecutorService;

/**
 * Created by IntelliJ IDEA.
 * User: bluesky
 * Date: 2009-11-7
 * Time: 23:39:55
 * To change this template use File | Settings | File Templates.
 */
public class SyslogServer extends Thread {
    public static final Logger logger = Logger.getLogger(SyslogServer.class);

    private int timeout = 1;
    private boolean m_runing = false;
    private NioDatagramAcceptor acceptor;

    private SyslogHandler handler;
    private String charset;
    private ExecutorService filterExecutor;
    private ExecutorService acceptExecutor;
    private String sysloghost;
    private int syslogport;
    private String cmsip;
    private int cmsPort;
    private SyslogIF syslogClient;

    protected BusinessLogImp businessLogImp;
    protected BusinessLogZDImp ydjwbusinessLogImp;
    protected boolean isprivate = false;

    public void setPlatform(String platform) {
        businessLogImp = new BusinessLogImp();
        ydjwbusinessLogImp = new BusinessLogZDImp();
        if (System.getProperty("privatenetwork") != null && System.getProperty("privatenetwork").equalsIgnoreCase("true")) {
            isprivate = true;


        }
        businessLogImp.setPlatformName(platform);
        ydjwbusinessLogImp.setPlatformName(platform);

    }

    public boolean isRun() {
        return m_runing;
    }

    public void setRemtoeServer(String cmsip, int cmsPort) {
        this.cmsip = cmsip;
        this.cmsPort = cmsPort;
        syslogClient = Syslog.getInstance("udp");
        syslogClient.getConfig().setHost(cmsip);
        syslogClient.getConfig().setPort(cmsPort);
        syslogClient.getConfig().setCharSet("GBK");

    }

    public void processSyslog(SyslogMessage syslog) {

        String str3 = SyslogUtility.getLevelString(syslog.getServerty());
        String host = syslog.getHostName();
//        logger.info(syslog.message);
        if (str3.equalsIgnoreCase("UNKNOWN")) {
            logger.info("syslog level is unkonwn");
            return;
        }
        try {
            ILogFormat logFormat = LogFormatFactory.getLogFormat(syslog.getMessage(), str3);
            //logger.info("Factory create logFormat");
            ydjwbusinessLogImp.processILogFormat(logFormat);
        } catch (RuntimeException e) {
            logger.info("Syslog recv process error.", e);
        }

        return;
    }

    public void close() {
        m_runing = false;
        if (acceptor != null) {
            if (sysloghost != null)
                acceptor.unbind(new InetSocketAddress(sysloghost, syslogport));
            else {
                acceptor.unbind(new InetSocketAddress(syslogport));
            }

            acceptor.dispose();
            if (filterExecutor != null)
                filterExecutor.shutdown();
            if (acceptExecutor != null) {
                acceptExecutor.shutdown();

            }
        }

        logger.info("Syslog service Run stop.port:" + syslogport);
    }


    public void config(String host, int port, String charset) {


        sysloghost = host;

        syslogport = port;
        this.filterExecutor = new OrderedThreadPoolExecutor(Runtime.getRuntime().availableProcessors() + 1);
        this.acceptExecutor = new OrderedThreadPoolExecutor(Runtime.getRuntime().availableProcessors() + 1);


    }


    public void run() {
        m_runing = true;
        try {
            startSyslog();
        } catch (Exception e) {
            logger.warn("Syslog  service Run error.port:" + syslogport, e);
        }
    }

    private void startSyslog() throws Exception {
        // Create UDP/IP acceptor.
        acceptor = new NioDatagramAcceptor();
        DatagramSessionConfig dcfg = acceptor.getSessionConfig();
        dcfg.setReuseAddress(true);

        acceptor.getFilterChain().addLast("threadPool", new ExecutorFilter(filterExecutor));//
        acceptor.getFilterChain().addLast("codec",
                new ProtocolCodecFilter(new SyslogCodecFactory()));
        //acceptor.getSessionConfig().setKeepAlive(true);
        handler = new SyslogHandler(this);
        acceptor.setHandler(handler);
        acceptor.getSessionConfig().setBothIdleTime(timeout * 60);


        //acceptor.getSessionConfig().setSoLinger(0);

        //acceptor.setReuseAddress(true);


        if (sysloghost != null)
            acceptor.bind(new InetSocketAddress(sysloghost, syslogport));
        else
            acceptor.bind(new InetSocketAddress(syslogport));
    }


    public static void main(String arg[]) throws Exception {
        SyslogServer syslog = new SyslogServer();
        syslog.config(null, 1514, "GBK");
        syslog.setPlatform("YDJW");
        //syslog.setRemtoeServer("127.0.0.1", 1514);
        syslog.start();
        while (true) {
            Thread.sleep(60 * 1000);
        }
    }
}
