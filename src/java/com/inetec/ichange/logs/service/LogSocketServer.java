package com.inetec.ichange.logs.service;

import com.inetec.ichange.logs.util.MegeByteArrayOutputStream;
import com.inetec.ichange.main.api.DataAttributes;
import com.inetec.common.exception.Ex;

import java.net.ServerSocket;
import java.net.Socket;
import java.io.*;
import java.util.ArrayList;

import org.apache.commons.io.IOUtils;
import org.apache.commons.codec.binary.Base64;
import org.apache.log4j.Logger;

/**
 * Created by IntelliJ IDEA.
 * User: wxh
 * Date: 2005-10-5
 * Time: 15:46:47
 * To change this template use File | Settings | File Templates.
 */
public class LogSocketServer extends Thread {
    public static final Logger m_logger = Logger.getLogger(LogSysCacheProcess.class);
    private ServerSocket m_sokeServer = null;
    private ByteArrayOutputStream m_out = null;
    private ArrayList m_list = new ArrayList();
    private final Object m_lock = new Object();
    public static final int I_MaxSize = 10 * 1024;
    private final int m_port = 8090;
    private final LogSysCacheProcess logSysCache = new LogSysCacheProcess();

    public LogSocketServer() throws IOException {
        m_out = new ByteArrayOutputStream();
        m_sokeServer = new ServerSocket(m_port);

    }

    public LogSocketServer(int port) throws IOException {
        m_out = new ByteArrayOutputStream(I_MaxSize);
        if (port > 0)
            m_sokeServer = new ServerSocket(port);
        else
            m_sokeServer = new ServerSocket(m_port);
    }

    public void config(String userid, String password, int logmax, int logday) {
        logSysCache.config(userid, password, logmax, logday);
    }

    public void run() {
        while (true) {
            try {
                Socket socket = m_sokeServer.accept();
                socket.setTcpNoDelay(true);
                //socket.setSoTimeout(10 * 1000);
                socket.setSendBufferSize(100 * 1024);
                socket.setReceiveBufferSize(100 * 1024);
                BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream(), "utf-8"));
                String userdata = reader.readLine();
                if (socketValidaion(userdata)) {
                    socket.getOutputStream().write("200: audit user validation succeed.\r\n".getBytes());
                    socket.getOutputStream().flush();
                    m_list.add(socket);
                } else {
                    socket.getOutputStream().write("500: audit user validation fail.\r\n".getBytes());
                    socket.getOutputStream().flush();
                    socket.shutdownOutput();
                    socket.shutdownInput();
                    socket.close();
                }
            } catch (Exception e) {
                m_logger.warn("Exception:");
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                //okay
            }
        }

    }

    public void sendData() {

        Socket socket = null;
        byte buff[] = null;
        synchronized (m_lock) {
            buff = m_out.toByteArray();
            try {
                m_out.close();
                m_out = null;
                m_out = new ByteArrayOutputStream();
            } catch (IOException e) {
                //okay
            }
        }
        m_out = new ByteArrayOutputStream(I_MaxSize);
        //System.out.println("Socket client Size:" + m_list.size());
        if (buff == null || buff.length == 0) {
            //System.out.println("Log is null.");
            return;
        }
        for (int i = 0; i < m_list.size(); i++) {
            socket = (Socket) m_list.get(i);
            try {
                if (socket.isClosed()) {
                    if (!m_list.isEmpty())
                        m_list.remove(i);
                    socket = null;
                    continue;
                }
                if (socket.isConnected()) {
                    socket.getOutputStream().write(Base64.encodeBase64(buff));
                    socket.getOutputStream().flush();
                }

            } catch (Exception e) {
                //e.printStackTrace();
                if (!m_list.isEmpty())
                    m_list.remove(i);
            }

        }
        if (m_list.size() == 0) {
            if (m_out.size() + buff.length < I_MaxSize) {
                try {
                    addLog(buff);
                } catch (IOException e) {
                    //okay
                }
            }
        }
        buff = null;

    }

    public void addLog(byte[] log) throws IOException {
        if (m_out != null) {
            //System.out.println("Log data:"+new String(log));
            //synchronized (m_lock) {
            if (m_out.size() + log.length > I_MaxSize) {
                if (m_list.isEmpty()) {
                    try {
                        logSysCache.cache(m_out.toByteArray());
                    } catch (Ex ex) {
                        m_logger.warn("Cache audit Data Exception :", ex);
                    }
                }
                m_out.close();
                m_out = new ByteArrayOutputStream();
            }
            m_out.write(log);
            m_out.flush();
            if (logSysCache.isUseCache() && !m_list.isEmpty()) {
                try {
                    m_out.write(logSysCache.readCache());
                } catch (Ex ex) {
                    m_logger.warn("Read Cache Data IOException: ", ex);
                }
                m_out.flush();
            }
        }
    }

    public void addLog(InputStream in) throws IOException {
        addLog(IOUtils.toByteArray(in));
        in.close();
    }

    private boolean socketValidaion(String userdata) throws Ex {
        return logSysCache.isValidationUser(userdata);
    }


}
