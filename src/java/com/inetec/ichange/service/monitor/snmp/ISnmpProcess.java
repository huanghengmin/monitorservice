package com.inetec.ichange.service.monitor.snmp;


/**
 * Created by IntelliJ IDEA.
 * User: bluesky
 * Date: 2010-10-31
 * Time: 10:05:47
 * To change this template use File | Settings | File Templates.
 */
public interface ISnmpProcess extends Runnable {

    public void init(String name, String host, int port);

    public boolean isRun();

    public void close();

}
