package com.ichange.service;

import com.ichange.common.utils.OSMonitSystemInfo.NegativeCPUTime;
import com.inetec.ichange.logs.service.LogServiceThread;
import org.apache.log4j.Logger;

public class Test {

    /**
     * @param args
     * @throws NegativeCPUTime
     */
    private final static Logger m_log = Logger.getLogger(TestLogsService.class);
    static LogServiceThread service = new LogServiceThread();

    public static void main(String[] args) throws Exception {
        /*   *//*  *//**//*while (true) {
            System.out.println(new OSSystemInfo().toString(true));
            try {
                Thread.sleep(700);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }*//**//*
        try {
            System.setProperty("ichange.home", "C:");
            service.init(8090);
            service.config("audit", "f0KEEW7G/nM=", 10, 2);
            int n = 0;
            while (true) {
                service.addLog(("{'appName':'Test','appType':'db','network':'f','auditLevel':'info','ip':'localhost','date':" +
                        "'2007-12-26 16:38:31','sourceDest':'d','databaseName':'db1','tableName':'table1','statusCode':0," +
                        "'recordcount':0,'appInfo':'','destUrl':'http://192.168.0.100:8060/console','userName':'test1'," +
                        "'operType':'access url','fileName':'c:\\download\\����.data','pkId':'pk;111:sss'},##{'appName':'Test'," +
                        "'appType':'db','network':'f','auditLevel':'info','ip':'localhost','date':'2007-12-26 16:38:31','sourceDest':'d'," +
                        "'databaseName':'db1','tableName':'table1','statusCode':0,'recordcount':0,'appInfo':'','destUrl':" +
                        "'http://192.168.0.100:8060/console','userName':'test1','operType':'access url','fileName':" +
                        "'c:\\download\\����.data','pkId':'pk;111:sss'},##").getBytes());
                n++;
                Thread.sleep(1000);
            }
        } catch (IOException e) {
            m_log.warn("IOException :", e);
        } catch (InterruptedException e) {
            //okay
        }*//*
        *//*String tmp="AIRLINES,DUI,ID;flights,DUI,flight_seq";
        DbInitBean db=new DbInitBean();
        db.setTriggerBeansForString(tmp);
        System.out.println(db);*//*
        OSSystemInfo out = new OSSystemInfo();
        OSSystemInfo in = new OSSystemInfo();
        in.init();
        out.init();
        *//* Service.sipchange.setAppstatus(1);
        Service.sipchange.setCurrent_connect(100);
        Service.sipchange.setMax_connect(200);
        Service.sipchange.setTotal_flux(1024);
        Service.sipchange.setTotal_requests(100);*//*

        Service.siptypeSet.AddResource(true, in);
        Service.siptypeSet.AddResource(false, out);
        //Service.siptypeSet.setTypeStatus(Service.sipchange);
        System.out.println(new String(Service.siptypeSet.getByte()));*/
        new Test().test();


    }

    public void test() {
//        IoBuffer buf = IoBuffer.allocate(10);
//        buf.setAutoExpand(true);
//        IoBuffer devd = IoBuffer.allocate(32);
//        devd.put("10000000".getBytes());
//        buf.put(devd);
//        System.out.println(buf.toString());

    }


}
