package com.inetec.ichange.service.monitor.snmp;

import com.inetec.ichange.service.monitor.databean.Equipment;
import com.inetec.ichange.service.monitor.databean.SnmpOid;

/**
 * Created by IntelliJ IDEA.
 * User: Administrator
 * Date: 12-11-28
 * Time: обнГ4:12
 * To change this template use File | Settings | File Templates.
 */
public interface IInSnmpProcess extends Runnable {
    public static final int I_SleepTime=10*1000;

    public void init(Equipment bean,SnmpOid snmpoidbean);
    public boolean isRun();
    public void close();
}
