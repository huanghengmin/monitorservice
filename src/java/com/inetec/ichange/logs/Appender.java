package com.inetec.ichange.logs;

import java.io.InputStream;

/**
 * 日志输出功能接口:
 * 实现远程输出功能,本地保存文件功能.
 * Created by IntelliJ IDEA.
 * User: wxh
 * Date: 2005-10-19
 * Time: 22:46:17
 * To change this template use File | Settings | File Templates.
 */
public interface Appender {

    public void config(String path);

    public boolean configurated();

    public void appender(InputStream in);

    public void appender(byte[] buff);
}
