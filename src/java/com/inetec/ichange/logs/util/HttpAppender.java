package com.inetec.ichange.logs.util;

import com.inetec.ichange.logs.Appender;

import java.io.InputStream;

/**
 * Created by IntelliJ IDEA.
 * User: wxh
 * Date: 2005-10-20
 * Time: 22:49:48
 * To change this template use File | Settings | File Templates.
 */
public class HttpAppender implements Appender {
    private LogNetWorkAppender m_LoggerDisposer;

    public void config(String path) {


    }

    public boolean configurated() {
        return false;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public void appender(InputStream in) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    public void appender(byte[] buff) {
        //To change body of implemented methods use File | Settings | File Templates.
    }
}
