package com.inetec.ichange.service.monitor.business;

import com.inetec.ichange.service.monitor.databean.BusinessRegister;
import com.inetec.ichange.service.monitor.databean.Equipment;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;

/**
 * Created by IntelliJ IDEA.
 * User: bluesky
 * Date: 2010-11-2
 * Time: 22:26:06
 * To change this template use File | Settings | File Templates.
 */
public class BusinessDbOperatorService extends Thread {
    private boolean isRun = true;
    private List<BusinessHourTask> equHourTasks = new ArrayList<BusinessHourTask>();
    Timer timer = new Timer();


    public BusinessDbOperatorService() {

    }

    public void init(List<BusinessRegister> equs) {
        for (int i = 0; i < equs.size(); i++) {
            BusinessHourTask task = new BusinessHourTask();
            task.init(equs.get(i).getBusiness_name());
            equHourTasks.add(task);
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
            //timer.schedule(equHourTasks.get(i), 1000, 2000);
            timer.scheduleAtFixedRate(equHourTasks.get(i), 1000, 1 * 60 * 60 * 1000);

        }
    }

    private void equHourTaskClose() {
        //for (int i = 0; i < equHourTasks.size(); i++) {
        timer.cancel();
        //}
    }
}
