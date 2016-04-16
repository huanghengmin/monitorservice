package com.inetec.ichange.logs.util;

import com.inetec.ichange.logs.Appender;

import java.io.InputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.log4j.Logger;

/**
 * Created by IntelliJ IDEA.
 * User: wxh
 * Date: 2005-10-20
 * Time: 22:49:08
 * To change this template use File | Settings | File Templates.
 */
public class FileAppender implements Appender {
    private Logger m_log = Logger.getLogger(FileAppender.class);
    /**
     * 日志存放路径
     */
    private String m_path = "";
    /**
     * 日志文件名
     */
    private String m_fileName = "";
    /**
     * 最大容量
     */
    private int m_max = 10;

    private boolean m_bConfigurated = false;
    private File m_file;
    private FileOutputStream m_fileStream = null;

    public void config(String path) {
        //todo：读取配置信息

    }

    public boolean configurated() {
        return m_bConfigurated;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public void appender(InputStream in) {
        //To change body of implemented methods use File | Settings | File Templates.
        int count = 1024;
        byte[] buff;
        try {
            if (in.available() > 1024)
                buff = new byte[1024];
            else
                buff = new byte[in.available()];
            while (in.read(buff) > 0) {
                appender(buff);
                if (in.available() > 1024)
                    buff = new byte[1024];
                else
                    buff = new byte[in.available()];
            }

        } catch (IOException e) {
            m_log.warn("读邓日志文件出错．");
        }


    }

    public void appender(byte[] buff) {

        //To change body of implemented methods use File | Settings | File Templates.
        if (m_file.length() > m_max * 1024 * 1024)
            try {
                m_fileStream.write(buff);
                m_fileStream.flush();
            } catch (IOException e) {
                m_log.warn("写日志文件出错．");
            }
    }
}
