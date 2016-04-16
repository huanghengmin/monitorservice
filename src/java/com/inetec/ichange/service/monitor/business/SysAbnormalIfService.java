package com.inetec.ichange.service.monitor.business;

import com.inetec.ichange.service.Service;
import com.inetec.ichange.service.monitor.databean.*;
import com.inetec.ichange.service.monitor.http.McHttpClient;

import com.inetec.ichange.service.monitor.uplink.databean.SysabnormalDao;
import com.inetec.ichange.service.monitor.uplink.databean.SysabnormalDataBean;
import com.inetec.ichange.service.monitor.utils.Pagination;
import org.apache.log4j.Logger;


import java.sql.Timestamp;
import java.util.Iterator;
import java.util.List;

public class SysAbnormalIfService extends Thread {
    private static final Logger log = Logger.getLogger(SysAbnormalIfService.class);
    private boolean isRun = false;

    BusinessExceptAlertDao busdao = new BusinessExceptAlertDao();
    SysabnormalInfDao sysabnormalDao = new SysabnormalInfDao();
    SysabnormalDao sysbnormalDao = new SysabnormalDao();


    public static final int I_SleepTime = 1 * 60 * 1000;

    /**
     *
     */
    public boolean isRun() {
        return isRun;
    }

    public void setRun(boolean isRun) {
        this.isRun = isRun;
    }

    public void run() {
        isRun = true;
        while (isRun) {
            processSysabnormalIf();
            try {
                Thread.sleep(I_SleepTime);
            } catch (InterruptedException e) {
                // okay
            }
        }
        isRun = false;
    }

    private boolean processSysabnormalIf() {
        boolean result = false;
        log.info("SysabnormalIf process .");
        Pagination<Sysabnormalinf> normals = sysabnormalDao.listAbnormalInf(100, 1);
        try {
            if (normals != null && normals.getItems() != null && normals.getItems().size() > 0) {
                Iterator<Sysabnormalinf> norit = normals.getItems().iterator();
                while (norit.hasNext()) {
                    //okay
                    process(norit.next());

                }
            }
            log.info("SysabnormalIf process end.");
        } catch (Exception e) {
            // TODO Auto-generated catch block
            log.warn("SysabnormalIf process ", e);
        }
        result = true;
        return result;

    }

    /**
     * 违规处理方法
     *
     * @param bean
     */
    public void process(Sysabnormalinf bean) {

        if (bean.getAction().equalsIgnoreCase("0")) { //不处理
            return;
        }
        //其他违规
        if (bean.getAbnormalTypeCode().equalsIgnoreCase("0000") && bean.getConnectObjectCode().equalsIgnoreCase("002")) {
            userOtherProcess(bean);
        }
         //网络违规
        if (bean.getAbnormalTypeCode().equalsIgnoreCase("0001") && bean.getConnectObjectCode().equalsIgnoreCase("002")) {
//            userAccessProcess(bean);
        }
        //进程违规
        if (bean.getAbnormalTypeCode().equalsIgnoreCase("0002") && bean.getConnectObjectCode().equalsIgnoreCase("002")) {
//            userAccessProcess(bean);
        }
        //外设违规
        if (bean.getAbnormalTypeCode().equalsIgnoreCase("0003") && bean.getConnectObjectCode().equalsIgnoreCase("002")) {
//            userAccessProcess(bean);
        }
        //用户URL违规
        if (bean.getAbnormalTypeCode().equalsIgnoreCase("0004") && bean.getConnectObjectCode().equalsIgnoreCase("002")) {
            userAccessProcess(bean);
        }
        //用户流量违规

        if (bean.getAbnormalTypeCode().equalsIgnoreCase("0005") && bean.getConnectObjectCode().equalsIgnoreCase("002")) {
            userFluxProcess(bean);
        }
        //机卡分离违规
        if (bean.getAbnormalTypeCode().equalsIgnoreCase("0006") && bean.getConnectObjectCode().equalsIgnoreCase("002")) {
            userSeparationProcess(bean);
        }

    }
    /**
     * 用户机卡分离违规处理
     */
    private void userSeparationProcess(Sysabnormalinf bean) {
        int count = 0;
        String exceptionIf = bean.getExceptionIf();
        if (exceptionIf.startsWith(">=")) {
            exceptionIf = exceptionIf.substring(2);
        } else {
            if (exceptionIf.startsWith(">")) {
                exceptionIf = exceptionIf.substring(1);
            }
        }
        exceptionIf.toUpperCase();
        count = Integer.parseInt(exceptionIf);
        List<SysterminalinfDataBean> syses = Service.terminainfService.terminalCache.getOnlineListBySeparationCount(count);
        if (syses.isEmpty()) {
            log.info("机卡分离违规用户列表为空");
            return;
        }
        Iterator<SysterminalinfDataBean> syseit = syses.iterator();
        while (syseit.hasNext()) {
            SysterminalinfDataBean sysbean = syseit.next();
            if (bean.getAction().equalsIgnoreCase("1")) {  //阻断
                cancel(sysbean.getUserId(), sysbean.getIp());
            }
            if (bean.getAction().equalsIgnoreCase("2")) {  //阻断
                block(sysbean.getUserId(), sysbean.getIp());
                log.info("SysabnormalIf process terminal user Othercount exception block process userid:" + sysbean.getUserId());
                alarmBusLog(sysbean.getUserId(), sysbean.getIp(), "bsquery", bean.getAbnormalTypeCode(), bean.getExceptionDesc(), "001");
            }
            if (bean.getAction().equalsIgnoreCase("3")) {  //报警
                alarmBusLog(sysbean.getUserId(), sysbean.getIp(), "bsquery", bean.getAbnormalTypeCode(), bean.getExceptionDesc(), "001");
                log.info("SysabnormalIf process terminal user warncount exception alarm process userid:" + sysbean.getUserId());
                sysbean.setSeparationcount(0);
                Service.terminainfService.terminalCache.updateBean(sysbean.getUserId(), sysbean);
            }
        }
    }
    /**
     * 用户外设违规处理
     */
    private void userPeripheralProcess(Sysabnormalinf bean) {

    }
    /**
     * 用户进程违规处理
     */
    private void userProcess(Sysabnormalinf bean) {

    }
    /**
     * 用户网络违规处理
     */
    private void userNetworkProcess(Sysabnormalinf bean) {

    }
    /**
     * 用户其他违规处理
     */
    private void userOtherProcess(Sysabnormalinf bean) {
        int count = 0;
        String exceptionIf = bean.getExceptionIf();
        if (exceptionIf.startsWith(">=")) {
            exceptionIf = exceptionIf.substring(2);
        } else {
            if (exceptionIf.startsWith(">")) {
                exceptionIf = exceptionIf.substring(1);
            }
        }
        exceptionIf.toUpperCase();
        count = Integer.parseInt(exceptionIf);
        List<SysterminalinfDataBean> syses = Service.terminainfService.terminalCache.getOnlineListByOtherCount(count);
        if (syses.isEmpty()) {
            log.info("其他违规用户列表为空");
            return;
        }
        Iterator<SysterminalinfDataBean> syseit = syses.iterator();
        while (syseit.hasNext()) {
            SysterminalinfDataBean sysbean = syseit.next();
            if (bean.getAction().equalsIgnoreCase("1")) {  //阻断
                cancel(sysbean.getUserId(), sysbean.getIp());
            }
            if (bean.getAction().equalsIgnoreCase("2")) {  //阻断
                block(sysbean.getUserId(), sysbean.getIp());
                log.info("SysabnormalIf process terminal user Othercount exception block process userid:" + sysbean.getUserId());
                alarmBusLog(sysbean.getUserId(), sysbean.getIp(), "bsquery", bean.getAbnormalTypeCode(), bean.getExceptionDesc(), "001");
            }
            if (bean.getAction().equalsIgnoreCase("3")) {  //报警
                alarmBusLog(sysbean.getUserId(), sysbean.getIp(), "bsquery", bean.getAbnormalTypeCode(), bean.getExceptionDesc(), "001");
                log.info("SysabnormalIf process terminal user warncount exception alarm process userid:" + sysbean.getUserId());
                sysbean.setOthercount(0);
                Service.terminainfService.terminalCache.updateBean(sysbean.getUserId(), sysbean);
            }
        }
    }
    /**
     * 用户流量违规
     */
    private void userFluxProcess(Sysabnormalinf bean) {
        long flux = 1024 * 1024;  //1兆字节
        String exceptionIf = bean.getExceptionIf();
        if (exceptionIf.startsWith(">=")) {
            exceptionIf = exceptionIf.substring(2);
        } else {
            if (exceptionIf.startsWith(">")) {
                exceptionIf = exceptionIf.substring(1);
            }
        }
        exceptionIf.toUpperCase();
        if (exceptionIf.endsWith("M")) {
            flux = flux * Integer.parseInt(exceptionIf.substring(0, exceptionIf.length() - 1));
        }
        if (exceptionIf.endsWith("G")) {
            flux = flux * 1024 * Integer.parseInt(exceptionIf.substring(0, exceptionIf.length() - 1));
        }
        List<SysterminalinfDataBean> syses = Service.terminainfService.terminalCache.getOnlineListByFlux(flux);
        if (syses.isEmpty()) {
            log.info("流量违规用户列表为空");
            return;
        }
        Iterator<SysterminalinfDataBean> syseit = syses.iterator();
        while (syseit.hasNext()) {
            SysterminalinfDataBean sysbean = syseit.next();
            if (bean.getAction().equalsIgnoreCase("1")) {  //阻断
                cancel(sysbean.getUserId(), sysbean.getIp());
            }
            if (bean.getAction().equalsIgnoreCase("2")) {  //阻断
                block(sysbean.getUserId(), sysbean.getIp());
                log.info("SysabnormalIf process terminal user flux exception block process userid:" + sysbean.getUserId());
                alarmBusLog(sysbean.getUserId(), sysbean.getIp(), "bsquery", bean.getAbnormalTypeCode(), bean.getExceptionDesc(), "001");
            }
            if (bean.getAction().equalsIgnoreCase("3")) {  //报警
                alarmBusLog(sysbean.getUserId(), sysbean.getIp(), "bsquery", bean.getAbnormalTypeCode(), bean.getExceptionDesc(), "001");
                log.info("SysabnormalIf process terminal user flux exception alarm process userid:" + sysbean.getUserId());
            }
        }
    }

    /**
     * 用户访问URL违规处理
     */
    private void userAccessProcess(Sysabnormalinf bean) {
        int count = 0;
        String exceptionIf = bean.getExceptionIf();
        if (exceptionIf.startsWith(">=")) {
            exceptionIf = exceptionIf.substring(2);
        } else {
            if (exceptionIf.startsWith(">")) {
                exceptionIf = exceptionIf.substring(1);
            }
        }
        exceptionIf.toUpperCase();
        count = Integer.parseInt(exceptionIf);
        List<SysterminalinfDataBean> syses = Service.terminainfService.terminalCache.getOnlineListByWarnCount(count);
        if (syses.isEmpty()) {
            log.info("URL访问违规用户列表为空");
            return;
        }
        Iterator<SysterminalinfDataBean> syseit = syses.iterator();
        while (syseit.hasNext()) {
            SysterminalinfDataBean sysbean = syseit.next();
            if (bean.getAction().equalsIgnoreCase("1")) {  //阻断
                cancel(sysbean.getUserId(), sysbean.getIp());
            }
            if (bean.getAction().equalsIgnoreCase("2")) {  //阻断
                block(sysbean.getUserId(), sysbean.getIp());
                log.info("SysabnormalIf process terminal user warncount exception block process userid:" + sysbean.getUserId());
                alarmBusLog(sysbean.getUserId(), sysbean.getIp(), "bsquery", bean.getAbnormalTypeCode(), bean.getExceptionDesc(), "001");
            }
            if (bean.getAction().equalsIgnoreCase("3")) {  //报警
                alarmBusLog(sysbean.getUserId(), sysbean.getIp(), "bsquery", bean.getAbnormalTypeCode(), bean.getExceptionDesc(), "001");
                log.info("SysabnormalIf process terminal user warncount exception alarm process userid:" + sysbean.getUserId());
                sysbean.setWarncount(0);
                Service.terminainfService.terminalCache.updateBean(sysbean.getUserId(), sysbean);
            }
        }
    }

    private void alarmBusLog(String userid, String ip, String type, String code, String message, String result) {
        BusinessExceptAlert busalert = new BusinessExceptAlert();
        busalert.setAlert_info(message);
        busalert.setBusiness_name(type);
        busalert.setAlert_time(new Timestamp(System.currentTimeMillis()));
        busalert.setExcept_code(code);
        busalert.setUser_name(userid);
        busalert.setIp(ip);
        busdao.saveBusinessExceptAlert(busalert);
        SysabnormalDataBean bean = new SysabnormalDataBean();
        bean.setAbnormaltypecode(code);
        bean.setTreadresult(result);
        bean.setConnectobjectcode("002");
        bean.setExceptiondesc(message);
        bean.setTreattime(new Timestamp(System.currentTimeMillis()));
        bean.setHappentime(new Timestamp(System.currentTimeMillis()));
        bean.setWritetime(new Timestamp(System.currentTimeMillis()));
        sysbnormalDao.save(bean);
    }

    public boolean block(String userid, String ip) {
        boolean result = false;
        McHttpClient client = new McHttpClient();
        try {
            client.init(new MonitorAgentDao().getMcHost());
            result = client.vpnblock(userid, ip);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            log.warn("TerminalInfo process is start", e);
        }
        return result;
    }

    public boolean cancel(String userid, String ip) {
        boolean result = false;
        McHttpClient client = new McHttpClient();
        try {
            client.init(new MonitorAgentDao().getMcHost());
            result = client.vpnblock(userid, ip);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            log.warn("TerminalInfo process is start", e);
        }
        return result;
    }

    public void close() {
        isRun = false;
    }

    public static void main(String arg[]) throws Exception {
        SysAbnormalIfService server = new SysAbnormalIfService();
        server.start();
        Thread.sleep(6 * 1000);
    }

}
