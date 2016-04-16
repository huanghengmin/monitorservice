package com.inetec.ichange.logs.util;

import org.apache.log4j.Logger;

import java.io.ByteArrayOutputStream;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * 日志暂存功能对象,实现日志的暂时缓存
 * Created by IntelliJ IDEA.
 * User: wxh
 * Date: 2005-10-19
 * Time: 22:03:03
 * To change this template use File | Settings | File Templates.
 */
public class LogCachce {
    private Logger m_log = Logger.getLogger(LogCachce.class);
    private ByteArrayOutputStream m_buff = new ByteArrayOutputStream();
    private ByteArrayInputStream m_value;
    private static final int I_max = 10;
    private int m_count = 1;

    public void cachceLog(byte[] log) {
        if (m_count > I_max) {
            m_value = new ByteArrayInputStream(m_buff.toByteArray());
            m_buff.reset();
            m_count = 1;
        }
        m_count++;
        try {
            m_buff.write(log);
        } catch (IOException e) {
            m_log.warn("暂存日志出错", e);
        }
    }

    public InputStream getValue() {
        return m_value;
    }
}
