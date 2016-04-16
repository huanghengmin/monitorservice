package com.inetec.ichange.service.utils;

import com.inetec.ichange.service.IServiceCommondProcess;
import com.inetec.ichange.service.ServiceMonitorPause;
import com.inetec.common.exception.Ex;
import com.inetec.common.exception.E;
import com.inetec.common.i18n.Message;

/**
 * Created by IntelliJ IDEA.
 * User: wxh
 * Date: 2005-8-17
 * Time: 20:32:25
 * To change this template use File | Settings | File Templates.
 */
public class ServiceCommonFactory {

    private static final String STR_ServiceCommonClassName_Audit = com.inetec.ichange.service.ServiceAudit.class.getName();
    private static final String STR_ServiceCommonClassName_Config = com.inetec.ichange.service.ServiceConfig.class.getName();
    private static final String STR_ServiceCommonClassName_Stop = com.inetec.ichange.service.ServiceStop.class.getName();
    private static final String STR_ServiceCommonClassName_Start = com.inetec.ichange.service.ServiceStart.class.getName();
    private static final String STR_ServiceCommonClassName_ReStart = com.inetec.ichange.service.ServiceReStart.class.getName();
    private static final String STR_ServiceCommonClassName_Init = com.inetec.ichange.service.ServiceInit.class.getName();
    private static final String STR_ServiceCommonClassName_Monitor = com.inetec.ichange.service.ServiceMonitor.class.getName();
    private static final String STR_ServiceCommonClassName_OSMonitor = com.inetec.ichange.service.ServiceOSMonitor.class.getName();

    private static final String STR_ServiceCommonClassName_GetStatus = com.inetec.ichange.service.ServiceGetStatus.class.getName();
    private static final String STR_ServiceCommonClassName_Log = com.inetec.ichange.service.ServiceLog.class.getName();

    private static final String STR_ServiceTestConfigClassName = com.inetec.ichange.service.ServiceTestConfig.class.getName();


    //add by wxh time 2009-04-12  ????????��???

    private static final String STR_ServiceCommonClassName_MonitorStop = com.inetec.ichange.service.ServiceMonitorStop.class.getName();
    private static final String STR_ServiceCommonClassName_MonitorStart = com.inetec.ichange.service.ServiceMonitorStart.class.getName();
    private static final String STR_ServiceCommonClassName_MonitorGetStatus = com.inetec.ichange.service.ServiceMonitorGetStatus.class.getName();

    private static final String STR_ServiceCommonClassName_AppMonitor = com.inetec.ichange.service.ServiceAppMonitor.class.getName();
    private static final String STR_ServiceCommonClassName_MonitorPause = com.inetec.ichange.service.ServiceMonitorPause.class.getName();

    public static IServiceCommondProcess createServiceCommon(String common) throws Ex {
        IServiceCommondProcess object = null;
        if (common == null || common == "") {
            throw new Ex().set(E.E_InvalidArgument, new Message("Commond is null."));
        }

        if (ServiceUtil.STR_ServiceData_Audit.equalsIgnoreCase(common)) {
            //object = (IServiceCommondProcess) m_map.get(STR_ServiceCommonClassName_Audit);
            if (object == null) {
                object = (IServiceCommondProcess) ServiceUtil.newObjectByClass(STR_ServiceCommonClassName_Audit, IServiceCommondProcess.class);
                // m_map.put(STR_ServiceCommonClassName_Audit, object);
            }
        }
        if (ServiceUtil.STR_ServiceData_Config.equalsIgnoreCase(common)) {
            //object = (IServiceCommondProcess) m_map.get(STR_ServiceCommonClassName_Config);
            if (object == null) {
                object = (IServiceCommondProcess) ServiceUtil.newObjectByClass(STR_ServiceCommonClassName_Config, IServiceCommondProcess.class);
                //m_map.put(STR_ServiceCommonClassName_Config, object);
            }
        }

        if (ServiceUtil.STR_ServiceData_Stop.equalsIgnoreCase(common)) {
            //object = (IServiceCommondProcess) m_map.get(STR_ServiceCommonClassName_Stop);
            if (object == null) {
                object = (IServiceCommondProcess) ServiceUtil.newObjectByClass(STR_ServiceCommonClassName_Stop, IServiceCommondProcess.class);
                //m_map.put(STR_ServiceCommonClassName_Stop, object);
            }
        }
        if (ServiceUtil.STR_ServiceData_Start.equalsIgnoreCase(common)) {
            //object = (IServiceCommondProcess) m_map.get(STR_ServiceCommonClassName_Start);
            if (object == null) {
                object = (IServiceCommondProcess) ServiceUtil.newObjectByClass(STR_ServiceCommonClassName_Start, IServiceCommondProcess.class);
                //m_map.put(STR_ServiceCommonClassName_Start, object);
            }
        }

        if (ServiceUtil.STR_ServiceData_ReStart.equalsIgnoreCase(common)) {
            //object = (IServiceCommondProcess) m_map.get(STR_ServiceCommonClassName_ReStart);
            if (object == null) {
                object = (IServiceCommondProcess) ServiceUtil.newObjectByClass(STR_ServiceCommonClassName_ReStart, IServiceCommondProcess.class);
                //m_map.put(STR_ServiceCommonClassName_ReStart, object);
            }
        }
        if (ServiceUtil.STR_ServiceData_Init.equalsIgnoreCase(common)) {
            //object = (IServiceCommondProcess) m_map.get(STR_ServiceCommonClassName_Init);
            if (object == null) {
                object = (IServiceCommondProcess) ServiceUtil.newObjectByClass(STR_ServiceCommonClassName_Init, IServiceCommondProcess.class);
                //m_map.put(STR_ServiceCommonClassName_Init, object);
            }
        }


        if (ServiceUtil.STR_ServiceData_Monitor.equalsIgnoreCase(common)) {
            //object = (IServiceCommondProcess) m_map.get(STR_ServiceCommonClassName_DbCreateTempTable);
            if (object == null) {
                object = (IServiceCommondProcess) ServiceUtil.newObjectByClass(STR_ServiceCommonClassName_Monitor, IServiceCommondProcess.class);
                //m_map.put(STR_ServiceCommonClassName_DbCreateTempTable, object);
            }
        }
        if (ServiceUtil.STR_ServiceData_OSMonitor.equalsIgnoreCase(common)) {
            //object = (IServiceCommondProcess) m_map.get(STR_ServiceCommonClassName_DbCreateTempTable);
            if (object == null) {
                object = (IServiceCommondProcess) ServiceUtil.newObjectByClass(STR_ServiceCommonClassName_OSMonitor, IServiceCommondProcess.class);
                //m_map.put(STR_ServiceCommonClassName_DbCreateTempTable, object);
            }
        }
        if (ServiceUtil.STR_ServiceData_TypeStatus.equalsIgnoreCase(common)) {
            //object = (IServiceCommondProcess) m_map.get(STR_ServiceCommonClassName_DbCreateTempTable);
            if (object == null) {
                object = (IServiceCommondProcess) ServiceUtil.newObjectByClass(STR_ServiceCommonClassName_GetStatus, IServiceCommondProcess.class);
                //m_map.put(STR_ServiceCommonClassName_DbCreateTempTable, object);
            }
        }
        if (ServiceUtil.STR_Test_Config.equalsIgnoreCase(common)) {
            //object = (IServiceCommondProcess) m_map.get(STR_ServiceCommonClassName_DbCreateTempTable);
            if (object == null) {
                object = (IServiceCommondProcess) ServiceUtil.newObjectByClass(STR_ServiceTestConfigClassName, IServiceCommondProcess.class);
                //m_map.put(STR_ServiceCommonClassName_DbCreateTempTable, object);
            }
        }

        if (ServiceUtil.STR_ServiceData_Log.equalsIgnoreCase(common)) {
            //object = (IServiceCommondProcess) m_map.get(STR_ServiceCommonClassName_DbCreateTempTable);
            if (object == null) {
                object = (IServiceCommondProcess) ServiceUtil.newObjectByClass(STR_ServiceCommonClassName_Log, IServiceCommondProcess.class);
                //m_map.put(STR_ServiceCommonClassName_DbCreateTempTable, object);
            }
        }


        //add by wxh 2009-04-13
        if (ServiceUtil.STR_ServiceAppPause.equalsIgnoreCase(common)) {
            //object = (IServiceCommondProcess) m_map.get(STR_ServiceCommonClassName_DbCreateTempTable);
            if (object == null) {
                object = (IServiceCommondProcess) ServiceUtil.newObjectByClass(STR_ServiceCommonClassName_MonitorPause, IServiceCommondProcess.class);
                //m_map.put(STR_ServiceCommonClassName_DbCreateTempTable, object);
            }
        }
        if (ServiceUtil.STR_ServiceAppStop.equalsIgnoreCase(common)) {
            //object = (IServiceCommondProcess) m_map.get(STR_ServiceCommonClassName_DbCreateTempTable);
            if (object == null) {
                object = (IServiceCommondProcess) ServiceUtil.newObjectByClass(STR_ServiceCommonClassName_MonitorStop, IServiceCommondProcess.class);
                //m_map.put(STR_ServiceCommonClassName_DbCreateTempTable, object);
            }
        }
        if (ServiceUtil.STR_ServiceAppStart.equalsIgnoreCase(common)) {
            //object = (IServiceCommondProcess) m_map.get(STR_ServiceCommonClassName_DbCreateTempTable);
            if (object == null) {
                object = (IServiceCommondProcess) ServiceUtil.newObjectByClass(STR_ServiceCommonClassName_MonitorStart, IServiceCommondProcess.class);
                //m_map.put(STR_ServiceCommonClassName_DbCreateTempTable, object);
            }
        }
        if (ServiceUtil.STR_ServiceApp_Monitor.equalsIgnoreCase(common)) {
            //object = (IServiceCommondProcess) m_map.get(STR_ServiceCommonClassName_DbCreateTempTable);
            if (object == null) {
                object = (IServiceCommondProcess) ServiceUtil.newObjectByClass(STR_ServiceCommonClassName_AppMonitor, IServiceCommondProcess.class);
                //m_map.put(STR_ServiceCommonClassName_DbCreateTempTable, object);
            }
        }
        if (ServiceUtil.STR_ServiceGetAppStart.equalsIgnoreCase(common)) {
            //object = (IServiceCommondProcess) m_map.get(STR_ServiceCommonClassName_DbCreateTempTable);
            if (object == null) {
                object = (IServiceCommondProcess) ServiceUtil.newObjectByClass(STR_ServiceCommonClassName_MonitorGetStatus, IServiceCommondProcess.class);
                //m_map.put(STR_ServiceCommonClassName_DbCreateTempTable, object);
            }
        }
        if (object == null) {
            throw new Ex().set(E.E_OperationFailed, new Message("{0} Common is undefined.", common));
        }

        return object;

    }
}
