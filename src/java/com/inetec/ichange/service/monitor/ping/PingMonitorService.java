package com.inetec.ichange.service.monitor.ping;

import com.inetec.common.exception.Ex;
import com.inetec.common.net.Ping;
import com.inetec.ichange.service.monitor.databean.Equipment;
import com.inetec.ichange.service.monitor.databean.EquipmentDao;
import com.inetec.ichange.service.monitor.snmp.SnmpMonitorService;
import com.inetec.ichange.service.monitor.utils.Pagination;
import org.apache.log4j.Logger;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 * User: Administrator
 * Date: 12-11-13
 * Time: ????2:35
 * To change this template use File | Settings | File Templates.
 */
public class PingMonitorService extends Thread{
    private static final Logger logger = Logger.getLogger(PingMonitorService.class);
    public static Map ipMap = new HashMap();
    private static SnmpMonitorService snmpService;
    private static boolean isRun = false;
    private String str;


    public PingMonitorService() {

    }

    public void init(){

    }

    public boolean isRun() {
        return isRun;
    }

    public void run()  {
        isRun = true;


        while (isRun){
            try {
                logger.info("网络状态 ping检测 开始");
                EquipmentDao equ = new EquipmentDao();
                Pagination<Equipment> equpage = equ.listDevice(200, 1);
                List<Equipment> equs =equpage.getItems();
                for (int i = 0; i < equs.size(); i++) {
                    String ip = equs.get(i).getIp();
                    str = Ping.exec(ip, 1);
//                        logger.info("Ping"+ip+"结果为"+str);
                        if(str.indexOf("ttl")>-1|str.indexOf("TTL")>-1){
                            ipMap.put(ip,"true");
                        }else {
                            ipMap.put(ip,"false");
                        }
                }
                Thread.sleep(2*60*1000);
            } catch (InterruptedException e) {
                logger.info(e);
            } catch (Ex ex) {
                 logger.info(ex);
            }
        }
    }

}
