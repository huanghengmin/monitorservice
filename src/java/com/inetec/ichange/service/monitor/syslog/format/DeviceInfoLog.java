package com.inetec.ichange.service.monitor.syslog.format;

import com.inetec.ichange.service.monitor.databean.BusinessLog;
import com.inetec.ichange.service.monitor.databean.Equipment;
import com.inetec.ichange.service.monitor.databean.EquipmentDao;
import com.inetec.ichange.service.monitor.databean.EquipmentLog;
import com.inetec.ichange.service.monitor.uplink.databean.TOutlinkinfDataBean;
import com.inetec.ichange.service.monitor.uplink.databean.TenimalOperationAuditDataBean;
import com.inetec.ichange.service.monitor.uplink.databean.TernimalAccessAuditDataBean;
import org.apache.commons.lang.StringUtils;

import java.sql.Timestamp;

/**
 * 设备信息采集日志
 * User: bluesky
 * Date: 12-4-5
 * Time:
 * To change this template use File | Settings | File Templates.
 */
public class DeviceInfoLog implements ILogFormat {
    private static final String S_DeviceName = "DevName=";
    private static final String S_Separator_Keys = " ";
    private static final String S_Separator_KeyValue = "=";

    private String log;
    /**
     * id
     */
    public String equid;
    /*
     *ip地址
     */
    public String ip;
    private String level;
    private String DevName;
    private String Model;
    private String Factory;
    private String Manager;
    private String devip;
    private String Time;


    public long getIn_Flux() {

        return 0;
    }

    public long getOut_Flux() {

        return 0;
    }

    public void process(String log) {
        this.log = log;
        if (validate(log)) {

            String[] keyvalues = log.split(S_Separator_Keys);
            for (int i = 0; i < keyvalues.length; i++) {
                if (keyvalues[i].startsWith("deviceid=")) {
                    equid = keyvalues[i].substring("deviceid=".length());
                }
                if (keyvalues[i].startsWith("ip=")) {
                    ip = keyvalues[i].substring("ip=".length());
                }
                if (keyvalues[i].startsWith("DevName=")) {
                    DevName = keyvalues[i].substring("DevName=".length());
                    if (DevName.endsWith("" +
                            "\""))
                        DevName = DevName.substring(0, DevName.length() - 1);
                    if (DevName.startsWith("\"")) {
                        DevName = DevName.substring(1);
                    }
                }
                if (keyvalues[i].startsWith("Model=")) {
                    Model = keyvalues[i].substring("Model=".length());
                    if (Model.endsWith("" +
                            "\""))
                        Model = Model.substring(0, Model.length() - 1);
                    if (Model.startsWith("\"")) {
                        Model = Model.substring(1);
                    }
                }
                if (keyvalues[i].startsWith("Factory=")) {
                    Factory = keyvalues[i].substring("Factory=".length());

                    if (Factory.endsWith("" +
                            "\""))
                        Factory = Factory.substring(0, Factory.length() - 1);
                    if (Factory.startsWith("\"")) {
                        Factory = Factory.substring(1);
                    }
                }
                if (keyvalues[i].startsWith("Manager=")) {
                    Manager = keyvalues[i].substring("Manager=".length());
                    if (Manager.endsWith("" +
                            "\""))
                        Manager = Manager.substring(0, Manager.length() - 1);
                    if (Manager.startsWith("\"")) {
                        Manager = Manager.substring(1);
                    }
                }
                if (keyvalues[i].startsWith("IP=")) {
                    devip = keyvalues[i].substring("IP=".length());
                    if (devip.endsWith("" +
                            "\""))
                        devip = devip.substring(0, devip.length() - 1);
                    if (devip.startsWith("\"")) {
                        devip = devip.substring(1);
                    }
                }
                if (keyvalues[i].startsWith("Time=")) {
                    Time = keyvalues[i].substring("Time=".length());
                    if (Time.endsWith("" +
                            "\""))
                        Time = Time.substring(0, Time.length() - 1);
                    if (Time.startsWith("\"")) {
                        Time = Time.substring(1);
                    }
                }
                if (keyvalues[i].startsWith("OUTPUT_IP=")) {
                    String tempip = keyvalues[i].substring("OUTPUT_IP=".length());
                    if (tempip.endsWith("" +
                            "\""))
                        tempip = tempip.substring(0, tempip.length() - 1);
                    if (tempip.startsWith("\"")) {
                        tempip = tempip.substring(1);
                    }
                    if (devip == null) {
                        devip = tempip;
                    } else {
                        devip = devip + "," + tempip;
                    }

                }
                if (keyvalues[i].startsWith("INPUT_IP=")) {
                    String tempip = keyvalues[i].substring("OUTPUT_IP=".length());
                    if (tempip.endsWith("" +
                            "\""))
                        tempip = tempip.substring(0, tempip.length() - 1);
                    if (tempip.startsWith("\"")) {
                        tempip = tempip.substring(1);
                    }
                    if (devip == null) {
                        devip = tempip;
                    } else {
                        devip = devip + "," + tempip;
                    }
                }

            }

        }
    }

    @Override
    public void process(String log, String level) {
        //To change body of implemented methods use File | Settings | File Templates.
        this.level = level;
        process(log);
    }

    @Override
    public boolean validate(String log) {
        boolean result = false;


        if (StringUtils.containsIgnoreCase(log, S_DeviceName)
                && StringUtils.containsIgnoreCase(log, S_DeviceName))
            result = true;
        return result;
    }

    @Override
    public BusinessLog getBussinessLog() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public EquipmentLog getEquipmentLog() {
        return null;

    }

    @Override
    public Equipment getEquipment() {
        Equipment result = new EquipmentDao().listDeviceByDeviceName(equid);
        if (result != null) {
            result.setModel(Model);
            result.setManufacturer(Factory);
            result.setEqu_info(DevName);
            result.setIp(ip);
            result.setOther_ip(devip);
            result.setEquManagerDepart(Manager);
            result.setEquSysConfig("/usr/app/cms/data/" + equid + ".zip");
            //add 管理单位和配置文件地址
        }
        return result;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public TOutlinkinfDataBean getOutlink() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public TenimalOperationAuditDataBean getTenimalOperationLog() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public TernimalAccessAuditDataBean getTenimalAccessLog() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public int getDelay() {
        return 0;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public static void main(String arg[]) throws Exception {
        String log = "DevName=\"移动应用代理系统\" Model==\"4020\" Factory=\"中盾安全技术\" Manager=\"中盾\" IP=\"192.168.30.62\" Time=\"2012-04-18 16:44:00\"";
        ILogFormat logf = LogFormatFactory.getLogFormat(log, "info");
        System.out.println(logf.getEquipment().getEqu_name());
    }
}
