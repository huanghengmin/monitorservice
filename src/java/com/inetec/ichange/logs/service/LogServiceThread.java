package com.inetec.ichange.logs.service;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by IntelliJ IDEA.
 * User: wxh
 * Date: 2005-10-5
 * Time: 17:01:29
 * To change this template use File | Settings | File Templates.
 */
public class LogServiceThread {
    private SysLogService m_serServer = null;

    public LogServiceThread() {
        m_serServer = new SysLogService();

    }

    public void init() throws IOException {

        m_serServer.start();
    }

    public void init(int port) throws IOException {
        m_serServer = new SysLogService();
        m_serServer.start();
    }

    public void config(String userid, String password, int maxsize, int maxday) {
        //m_serServer.config(userid, password, maxsize, maxday);
    }

    public void addLog(byte[] log) throws IOException {
        m_serServer.addLog(log);
        // m_serServer.sendData();
    }

    public void addLog(InputStream in) throws IOException {
        m_serServer.addLog(in);

    }

}
