package com.inetec.ichange.service.monitor.business;

import com.inetec.ichange.service.monitor.databean.BusinessDataBean;
import com.inetec.ichange.service.monitor.databean.DeviceDataBean;
import org.apache.log4j.Logger;


public class BusinessImp implements IBusinessProcess {
    private static final Logger log = Logger.getLogger(BusinessImp.class);
    private boolean isRun = false;
    private int businessStatus = BusinessDataBean.I_Status_OK;
    private int businessid;
    private String businessname;


    public void init(int id, String name, String host, String port) {

        /*  businessname = name;
      businessid = id;*/


    }


    public boolean isRun() {

        return isRun;
    }

    public void run() {
        isRun = true;

        while (isRun) {
            try {
                monitorBusiness();

                Thread.sleep(2 * 1000);
            } catch (Exception e) {

            }
        }

    }

    public void monitorBusiness() throws Exception {


    }


    public void close() {
        // TODO Auto-generated method stub
        isRun = false;
        /*try {
            //snmp.close();
        } catch (IOException e) {
            log.warn("snmp close error:", e);
        }*/
    }

    public static void main(String arg[]) throws Exception {
        BusinessImp process = new BusinessImp();
        process.init(1, "netchinagateway", "172.17.3.100", "1161");
        new Thread(process).start();
        while (true) {
            Thread.sleep(1000);
        }
    }

}
