package com.inetec.ichange.service.monitor.alarm;

import com.inetec.ichange.service.monitor.databean.*;
import org.apache.log4j.Logger;

import java.sql.Timestamp;

/**
 * ?????????
 * Created by IntelliJ IDEA.
 * User: bluesky
 * Date: 2010-9-7
 * Time: 16:23:40
 * To change this template use File | Settings | File Templates.
 */
public class DbOperatorAlarmProcessImp implements IAlarmProcess {
    private final static Logger m_log = Logger.getLogger(AlarmService.class);
    BusinessExceptAlertDao busdao = new BusinessExceptAlertDao();
    EquipmentAlertDao equdao = new EquipmentAlertDao();
    SecurityEventAlertDao securitydao = new SecurityEventAlertDao();
    BusinessExceptAlert busalert = new BusinessExceptAlert();
    EquipmentAlert equAlert = new EquipmentAlert();
    SecurityEventAlert securityAlert = new SecurityEventAlert();

    public void init(String type, String name) {

    }

    public void process(String type, AlertDataBean bean) {

        if (type.equalsIgnoreCase(AlarmService.AlertType_Business)) {
            busalert.setAlert_info(bean.getAlert_info());
            busalert.setBusiness_name(bean.getName());
            busalert.setAlert_time(bean.getAlert_time());
            busalert.setExcept_code(bean.getCode());
            busalert.setUser_name(bean.getUsername());
            busalert.setIp(bean.getIp());
            busdao.saveBusinessExceptAlert(busalert);

        }
        if (type.equalsIgnoreCase(AlarmService.AlertType_Security)) {
            securityAlert.setAlert_info(bean.getAlert_info());
            securityAlert.setEqu_name(bean.getName());
            securityAlert.setAlert_time(bean.getAlert_time());
            securityAlert.setIp(bean.getIp());
            securityAlert.setEvent_code(bean.getCode());
            securitydao.saveSecurityEventAlert(securityAlert);
        }
        if (type.equalsIgnoreCase(AlarmService.AlertType_Equipment)) {

            equAlert.setAlert_info(bean.getAlert_info());
            equAlert.setEqu_name(bean.getName());
            equAlert.setAlert_time(bean.getAlert_time());
            equAlert.setIp(bean.getIp());
            equdao.saveEquipmentAlert(equAlert);
        }


    }

    public void close() {

    }

    public static void main(String arg[]) throws Exception {
        DbOperatorAlarmProcessImp db = new DbOperatorAlarmProcessImp();
        AlertDataBean alert = new AlertDataBean();
        alert.setAlert_time(new Timestamp(System.currentTimeMillis()));
        alert.setAlert_info("数据交换业务数据库异常.");
        alert.setCode("220");
        alert.setIp("192.168.1.102");
        alert.setName("test1");
        alert.setUsername("user1");
        db.process(AlarmService.AlertType_Business, alert);
        db.process(AlarmService.AlertType_Equipment, alert);
        db.process(AlarmService.AlertType_Security, alert);
    }
}
