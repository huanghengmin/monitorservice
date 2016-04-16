package com.inetec.ichange.service.monitor.snmp;

import com.inetec.ichange.service.monitor.databean.Equipment;
import com.inetec.ichange.service.monitor.databean.SnmpOid;
import com.inetec.ichange.service.monitor.utils.DeviceDataBeanSet;
import com.inetec.ichange.service.monitor.utils.InSnmpProcessFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Administrator
 * Date: 12-11-28
 * Time: 下午2:23
 * To change this template use File | Settings | File Templates.
 */
public class InSnmpMonitorService extends Thread  {
    public static boolean isRun = false;
    private boolean isInit = false;
    public static DeviceDataBeanSet dataset = new DeviceDataBeanSet();
    public static SnmpAgentService snmpAgent = new SnmpAgentService();
    public List<IInSnmpProcess> snmps;
    private List<SnmpOid> snmpOIDS;


    public InSnmpMonitorService() {

    }

    public boolean isRun() {
        return isRun;
    }

    public void init(List<Equipment> beans, List<SnmpOid> snmpoid) {
        if (beans == null) {
            isInit = false;
            return;
        }
        dataset.init(beans);
        snmpOIDS = snmpoid;
        snmps = new ArrayList();

        for (int i = 0; i < beans.size(); i++) {
            try {
                String deviceName = beans.get(i).getEqu_name();
                String deviceIp = beans.get(i).getIp();
                if (beans.get(i).getMonitor_used().equalsIgnoreCase("Y")&&beans.get(i).getNet_station().equalsIgnoreCase("i")) {
                    SnmpOid bean=getSnmpOIDByName(
                            beans.get(i).getEqu_oidname());
                    IInSnmpProcess process =  InSnmpProcessFactory
                            .getSnmpProcessByVer(bean.getSnmpver(),bean.getCompany(),bean.getType());
                    process.init(beans.get(i), bean);
                    snmps.add(process);
                }
            } catch (RuntimeException e) {
                System.out.print(e);
            } catch (Exception e) {
                System.out.print(e);
            }

        }
        isInit = true;
    }


    public void run() {
        isRun = true;
        if (isInit) {
            snmpProcessRun();
            snmpAgent.start();
            while (isRun) {
                try {
                    Thread.sleep(10*60 * 1000);
                } catch (InterruptedException e) {
                    // okays
                }
            }
        }
    }

    public void close() {
        isRun = false;
        snmpAgent.close();
        snmpProcessClose();

    }

    private void snmpProcessRun() {
        for (int i = 0; i < snmps.size(); i++) {
            new Thread(snmps.get(i)).start();
        }
    }


    public SnmpOid getSnmpOIDByName(String name) {
        SnmpOid result = null;
        for (int i = 0; i < snmpOIDS.size(); i++) {
            if (snmpOIDS.get(i).getName().equalsIgnoreCase(name)) {
                result = snmpOIDS.get(i);
            }
        }
        return result;
    }

    private void snmpProcessClose() {
        for (int i = 0; i < snmps.size(); i++) {
            snmps.get(i).close();
        }
    }

}
