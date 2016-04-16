package com.inetec.ichange.logs;

import com.inetec.ichange.logs.util.LogCachce;

/**
 * Created by IntelliJ IDEA.
 * User: Õı–Àª·
 * Date: 2005-8-1
 * Time: 22:28:32
 * To change this template use File | Settings | File Templates.
 */
public class LogHelperFactory {
    private static boolean m_bPrivatenetwork = false;
    private static LogCachce m_cachce = new LogCachce();

    public static LogHelper createLogHelper() {
        LogHelper log = new LogHelper(m_bPrivatenetwork, m_cachce);
        return log;
    }

    public LogHelperFactory() {
        String privatenetwork = System.getProperty("privatenetwork");
        privatenetwork = privatenetwork.toUpperCase();
        if (privatenetwork.equalsIgnoreCase("TRUE")) {
            m_bPrivatenetwork = true;
        }
    }

}
