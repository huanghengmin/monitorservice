package com.inetec.ichange.service.monitor.snmp;

import com.inetec.ichange.service.monitor.databean.Equipment;
import com.inetec.ichange.service.monitor.databean.MonitorAgent;
import com.inetec.ichange.service.monitor.utils.DeviceDataBeanSet;

import java.util.ArrayList;
import java.util.List;

/**
 * Snmp ?????
 * Created by IntelliJ IDEA.
 * User: bluesky
 * Date: 2010-9-7
 * Time: 14:43:58
 * To change this template use File | Settings | File Templates.
 */
public class SnmpMonitorService extends Thread {
    private boolean isRun = true;
    public static DeviceDataBeanSet dataset = new DeviceDataBeanSet();
    private List<ISnmpProcess> snmps = new ArrayList<ISnmpProcess>();
    private String mchost;
    private String mcsnmpport;

    public SnmpMonitorService() {

    }

    public void init(List<Equipment> equs, List<MonitorAgent> agents) {

        dataset.init(equs);
        for (int i = 0; i < equs.size(); i++) {
            if (equs.get(i).getMonitor_used().equalsIgnoreCase("Y")) {
                ISnmpProcess snmp = new SnmpProcessV2Imp();
                snmp.init(equs.get(i).getEqu_name(), getMcHost(agents, equs.get(i).getLink_name()), getMcPort(agents, equs.get(i).getLink_name()));
                snmps.add(snmp);

            }
        }

    }

    public boolean isRun() {
        return isRun;
    }

    public void run() {
        isRun = true;
        snmpProcessRun();
        while (isRun) {
            try {
                Thread.sleep(2 * 1000);
            } catch (InterruptedException e) {
                //okay
            }
        }
    }

    /**
     *
     */
    public void close() {
        isRun = false;
        snmpProcessClose();

    }

    private void snmpProcessRun() {
        for (int i = 0; i < snmps.size(); i++) {
            new Thread(snmps.get(i)).start();
        }
    }

    private void snmpProcessClose() {
        for (int i = 0; i < snmps.size(); i++) {
            snmps.get(i).close();
        }
    }

    private String getMcHost(List<MonitorAgent> agents, String link_name) {
        String host = "";
        for (int i = 0; i < agents.size(); i++) {
            if (agents.get(i).getLink_name().equalsIgnoreCase(link_name)) {
                return agents.get(i).getAgent_ip();
            }
        }
        return host;
    }

    private int getMcPort(List<MonitorAgent> agents, String link_name) {
        int port = 1161;
        for (int i = 0; i < agents.size(); i++) {
            if (agents.get(i).getLink_name().equalsIgnoreCase(link_name)) {
                return agents.get(i).getAgent_port();
            }
        }
        return port;

    }


}
