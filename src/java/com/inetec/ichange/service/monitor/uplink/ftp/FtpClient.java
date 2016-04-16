package com.inetec.ichange.service.monitor.uplink.ftp;

import net.sf.jftp.net.BasicConnection;
import net.sf.jftp.system.logging.Logger;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;

import java.io.*;

/**
 * Created by IntelliJ IDEA.
 * User: bluesky
 * Date: 11-4-28
 * Time: 涓嬪崍1:46
 * To change this template use File | Settings | File Templates.
 */
public class FtpClient implements Logger {
    public static org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(FtpClient.class);
    private boolean isThere = false;
    private String type = "upload ";
    private String filaname = "";

    private String host;
    private int port;
    private String user;
    private String password;

    FTPClient client = new FTPClient();

    public void init(String host, int port, String userid, String password) {
        this.host = host;
        this.port = port;
        this.user = userid;
        this.password = password;
        logger.info("ftp connect:" + host + ":" + port + " userid: " + user + " pwd:" + password);
    }
    public boolean connect(String hostname,int port,String username,String password) throws IOException{
        client.connect(hostname, port);
        client.setControlEncoding("UTF-8");
        if(FTPReply.isPositiveCompletion(client.getReplyCode())){
            if(client.login(username, password)){
                client.enterLocalPassiveMode();
                return true;
            }
        }
        disconnect();
        return false;
    }

    public void disconnect() throws IOException{
        if(client.isConnected()){
            client.disconnect();
        }
    }
    /** *//**
     * 上传文件到服务器,新上传和断点续传
     * @param remoteDir 远程将服务器工作目录
     * @param localFileName 本地文件File绝对路径
     * @return
     * @throws IOException
     */
    public void upfile(String remoteDir,String localFileName){
        try{
            connect(host,port,user,password);
            File localFile = new File(localFileName);
            InputStream in = new FileInputStream(localFile);
//            FTPFile ftpFile = client.mlistFile(remoteDir);
//            if(ftpFile==null){
//                client.makeDirectory(remoteDir);
//            }
            OutputStream out = client.appendFileStream(localFile.getName());
            byte[] bytes = new byte[1024];
            int c;
            while((c = in.read(bytes))!= -1){
                out.write(bytes,0,c);
            }
            out.flush();
            in.close();
            out.close();
            disconnect();
        } catch (FileNotFoundException e) {
            logger.error("找不到要下载的文件",e);
        } catch (IOException e) {
            logger.error("上传I/O错误",e);
        }
    }

    public void downfile(String remoteDir,String localFileName) {
        try{
            connect(host,port,user,password);
            File localFile = new File(localFileName);
            FTPFile[] files = client.listFiles(new String(localFile.getName()));
            if(files.length != 1){
                System.out.println("远程文件不存在");
                return;
            }
            OutputStream out = new FileOutputStream(localFile);
            InputStream in= client.retrieveFileStream(localFile.getName());
            byte[] bytes = new byte[1024];
            int c;
            while((c = in.read(bytes))!= -1){
                out.write(bytes, 0, c);
            }
            in.close();
            out.close();
            disconnect();
        } catch (FileNotFoundException e) {
            logger.error("找不到要下载的文件",e);
        } catch (IOException e) {
            logger.error("下载I/O错误",e);
        }
    }

    public static void main(String argv[]) throws IOException {
         FtpClient client = new FtpClient();
         client.init("192.168.1.105",21,"172.16.2.6","123456");
         client.upfile("/incoming", "F:/test.log");
//         client.downfile("/incoming", "F:/test.log");
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

