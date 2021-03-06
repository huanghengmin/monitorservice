package com.inetec.ichange.service.monitor.snmp;

import com.inetec.ichange.service.monitor.databean.DeviceDataBean;
import org.apache.log4j.Logger;
import org.snmp4j.CommunityTarget;
import org.snmp4j.PDU;
import org.snmp4j.Snmp;
import org.snmp4j.TransportMapping;
import org.snmp4j.event.ResponseEvent;
import org.snmp4j.event.ResponseListener;
import org.snmp4j.mp.SnmpConstants;
import org.snmp4j.smi.*;
import org.snmp4j.transport.DefaultTcpTransportMapping;
import org.snmp4j.transport.DefaultUdpTransportMapping;

import java.io.IOException;

import static java.lang.Thread.sleep;


public class SnmpProcessV2Imp implements ISnmpProcess {
    private static final Logger log = Logger.getLogger(SnmpProcessV2Imp.class);
    private boolean isRun = false;
    private int deviceStatus = DeviceDataBean.I_Status_OK;
    public boolean isUdp = false;
    private int deviceid;
    private String devicename;
    ResponseListener listener;
    /**
     * ��ʼ��
     */
    Address targetAddress;
    Address tcptargetAddress;
    TransportMapping transport;
    TransportMapping tcpTargetsport;
    CommunityTarget target;
    CommunityTarget tcptarget;
    PDU pdu;
    Snmp snmp = new Snmp(transport);
    Snmp tcpSnmp = new Snmp(tcpTargetsport);
    OID equname = new OID(new OID(new int[]{1, 3, 6, 1, 4, 1, 3000, 2, 1}));
    OID cpu = new OID(new int[]{1, 3, 6, 1, 4, 1, 3000, 2, 2});
    OID memtotal = new OID(new int[]{1, 3, 6, 1, 4, 1, 3000, 2, 3});
    OID memfree = new OID(new int[]{1, 3, 6, 1, 4, 1, 3000, 2, 4});
    OID curconnect = new OID(new int[]{1, 3, 6, 1, 4, 1, 3000, 2, 5});
    OID disktotal = new OID(new int[]{1, 3, 6, 1, 4, 1, 3000, 2, 9});
    OID diskfree = new OID(new int[]{1, 3, 6, 1, 4, 1, 3000, 2, 10});

    public void init(String name, String host, int port) {
        // TODO Auto-generated method stub
        devicename = name;
        try {
            transport = new DefaultUdpTransportMapping();
            tcpTargetsport = new DefaultTcpTransportMapping();
            snmp = new Snmp(transport);
            tcpSnmp = new Snmp(tcpTargetsport);
            tcpSnmp.listen();
            // deviceid=Integer.parseInt(bean.getId());
            snmp.listen();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        targetAddress = GenericAddress.parse("udp:" + host
                + "/" + port);
        tcptargetAddress = GenericAddress.parse("tcp:" + host
                + "/" + port);

        pdu = new PDU();
        // devicename
        pdu.add(new VariableBinding(equname, new OctetString(devicename)));
        // memtotal


        pdu.setType(PDU.GET);
        target = new CommunityTarget();
        tcptarget = new CommunityTarget();
        target.setCommunity(new OctetString("public"));
        tcptarget.setCommunity(new OctetString("public"));
        target.setAddress(targetAddress);
        tcptarget.setAddress(tcptargetAddress);
        target.setRetries(2);
        tcptarget.setRetries(2);
        target.setTimeout(60000);
        tcptarget.setTimeout(60000);
        // tcptarget.setMaxSizeRequestPDU(484);
        target.setVersion(SnmpConstants.version2c);
        tcptarget.setVersion(SnmpConstants.version2c);
        ;


    }


    public boolean isRun() {
        // TODO Auto-generated method stub
        return isRun;
    }

    public void run() {
        isRun = true;

        while (isRun) {
            try {
                monitorDevice();
                sleep(20 * 1000);

            } catch (Exception e) {
                deviceStatus = DeviceDataBean.I_Status_Error;
                DeviceDataBean bean = SnmpMonitorService.dataset
                        .getDeviceDataBeanByID(devicename);

                bean.setStatus(deviceStatus);
                SnmpMonitorService.dataset.returnDeviceDataBean(devicename,
                        bean);

                log.warn("Monitor Device error:(Device ip:"
                        + targetAddress.toString() + ")", e);
                try {
                    sleep(3 * 60 * 1000);
                } catch (InterruptedException e1) {
                    //okay
                }
            }
        }

    }

    public void monitorDevice() throws Exception {


        // creating PDU

        // sending request
        ResponseEvent event;
        if (isUdp)
            event = snmp.send(pdu, target);
        else
            event = tcpSnmp.send(pdu, tcptarget, tcpTargetsport);
        if (event != null && event.getResponse() != null) {

            DeviceDataBean bean = SnmpMonitorService.dataset
                    .getDeviceDataBeanByID(devicename);
            bean.setCpu(Integer.parseInt(event.getResponse().get(1).getVariable().toString()));
            bean.setMem_total(Float.parseFloat(event.getResponse().get(2).getVariable()
                    .toString()));
            bean.setMem(Float.parseFloat(event.getResponse().get(3).getVariable().toString()));
            bean.setCurrentcon(Integer.parseInt(event.getResponse().get(4).getVariable()
                    .toString()));
            bean.setDisk_total(Double.parseDouble(event.getResponse().get(5).getVariable()
                    .toString()));
            bean.setDisk(Double.parseDouble(event.getResponse().get(6).getVariable().toString()));
            double count = 0;
            count = bean.getCpu() + bean.getCurrentcon() + bean.getDisk() + bean.getDisk_total() + bean.getMem_total() + bean.getMem();
            if (count == 0) {
                deviceStatus = DeviceDataBean.I_Status_Error;
            } else {
                deviceStatus = DeviceDataBean.I_Status_OK;
            }
            bean.setStatus(deviceStatus);

            SnmpMonitorService.dataset.returnDeviceDataBean(devicename,
                    bean);
            System.out.println(event.getResponse());
            log.info(event.getResponse());
        } else {
            deviceStatus = DeviceDataBean.I_Status_Error;
            DeviceDataBean bean = SnmpMonitorService.dataset
                    .getDeviceDataBeanByID(devicename);
            bean.setStatus(deviceStatus);
            if (isUdp)
                log.warn("Monitor Device error:(Device name:"
                        + devicename + "  is not link):udp:" + targetAddress.toString());
            else {
                log.warn("Monitor Device error:(Device name:"
                        + devicename + "  is not link):tcp:" + tcptargetAddress.toString());
            }
        }
    }


    public void close() {
        // TODO Auto-generated method stub
        isRun = false;
        try {
            snmp.close();
            tcpSnmp.close();
        } catch (IOException e) {
            log.warn("snmp close error:", e);
        }
    }

    public static void main(String arg[]) throws Exception {
        SnmpProcessV2Imp process = new SnmpProcessV2Imp();
        process.init("2122", "localhost", 1161);
        new Thread(process).start();

        SnmpProcessV2Imp process2 = new SnmpProcessV2Imp();
        process2.init("222", "localhost", 1161);
        new Thread(process2).start();
        while (true) {
            sleep(1000);
        }
        //System.out.print(((double )808960/1024000)*100);
    }

}
