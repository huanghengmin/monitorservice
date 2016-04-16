package com.inetec.ichange.service.monitor.dboperat;

import com.inetec.ichange.service.monitor.databean.Equipment;
import com.inetec.ichange.service.monitor.snmp.ISnmpProcess;
import com.inetec.ichange.service.monitor.snmp.SnmpProcessV2Imp;
import com.inetec.ichange.service.monitor.utils.DeviceDataBeanSet;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: bluesky
 * Date: 2010-11-2
 * Time: 22:26:06
 * To change this template use File | Settings | File Templates.
 */
public class EquDbOperatorService extends Thread {
    private boolean isRun = true;
    private List<EquipmentHourTask> equHourTasks = new ArrayList<EquipmentHourTask>();

    public EquDbOperatorService() {

    }

    public void init(List<Equipment> equs) {
        for (int i = 0; i < equs.size(); i++) {
            if (equs.get(i).getMonitor_used().equalsIgnoreCase("Y")) {
                EquipmentHourTask task = new EquipmentHourTask();
                task.init(equs.get(i).getEqu_name());
                equHourTasks.add(task);

            }
        }

    }

    public boolean isRun() {
        return isRun;
    }

    public void run() {
        isRun = true;
        equHourTaskRun();
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
        equHourTaskClose();

    }

    private void equHourTaskRun() {
        for (int i = 0; i < equHourTasks.size(); i++) {
            new Thread(equHourTasks.get(i)).start();
        }
    }

    private void equHourTaskClose() {
        for (int i = 0; i < equHourTasks.size(); i++) {

        }
    }
}
