package com.inetec.ichange.service.monitor;

import com.inetec.common.exception.Ex;
import com.inetec.ichange.main.api.DataAttributes;
import com.inetec.ichange.main.api.Status;
import com.inetec.ichange.service.IServiceCommondProcess;
import com.inetec.ichange.service.monitor.uplink.job.WebServiceUpdateJobImp;
import com.inetec.ichange.service.utils.ServiceUtil;
import net.sf.jftp.net.BasicConnection;
import net.sf.jftp.net.ConnectionHandler;
import net.sf.jftp.net.ConnectionListener;
import net.sf.jftp.net.FtpConnection;
import net.sf.jftp.system.logging.Log;
import net.sf.jftp.system.logging.Logger;
import org.quartz.JobExecutionException;

import java.io.InputStream;

/**
 * Created by IntelliJ IDEA.
 * User:
 * Date: 2012-6-6
 * Time: 13:22:27
 * To change this template use File | Settings | File Templates.
 */
public class FtpMonitorUplinkProcess implements Logger, ConnectionListener {
    public static org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(FtpMonitorUplinkProcess.class);
    private boolean isThere = false;
    private String type = "upload ";
    private String filaname = "";

    private ConnectionHandler handler = new ConnectionHandler();


    public void getUpLinkCommand(String host, int port, String user, String password, String dir, String file) {
        Log.setLogger(this);

        FtpConnection con = new FtpConnection(host, port, dir);

        con.addConnectionListener(this);

        con.setConnectionHandler(handler);

        con.login(user, password);

        while (!isThere) {
            try {
                Thread.sleep(10);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }

//        con.chdir(dir);
        String line = con.getLine("incoming");

        con.upload(file);
        filaname = file;
    }

    public static void main(String argv[]) {
        if (argv.length == 3) {
            new FtpMonitorUplinkProcess().getUpLinkCommand(argv[0], 21, "", "", argv[2], argv[1]);
        } else {

            new FtpMonitorUplinkProcess().getUpLinkCommand("localhost", 21, "root", "root", "3", "F:\\Temp\\filelist_smb.xml");
        }
    }

    public void updateRemoteDirectory(BasicConnection con) {
        debug("ftp remote dir is:" + con.getPWD());
    }

    public void connectionInitialized(BasicConnection con) {
        isThere = true;
        debug("connection initialized sucess.");
    }

    public void updateProgress(String file, String type, long bytes) {
        debug(" ftp upload" + file + " progess type:" + type + "bytes:" + bytes);
    }

    public void connectionFailed(BasicConnection con, String why) {
        warn("connection failed! " + why);
    }

    public void actionFinished(BasicConnection con) {
        info(" ftp action: " + type + " object is:" + filaname + " finished.");
    }

    public void debug(String msg) {
        logger.debug(msg);
    }

    public void debugRaw(String msg) {
        logger.debug(msg);
    }

    public void debug(String msg, Throwable throwable) {
        logger.debug(msg, throwable);
    }

    public void warn(String msg) {
        logger.warn(msg);
    }

    public void warn(String msg, Throwable throwable) {
        logger.warn(msg, throwable);
    }

    public void error(String msg) {
        logger.error(msg);
    }

    public void error(String msg, Throwable throwable) {
        logger.error(msg, throwable);
    }

    public void info(String msg) {
        logger.info(msg);
    }

    public void info(String msg, Throwable throwable) {
        logger.info(msg, throwable);
    }

    public void fatal(String msg) {
        logger.fatal(msg);
    }

    public void fatal(String msg, Throwable throwable) {
        logger.fatal(msg, throwable);
    }
}
