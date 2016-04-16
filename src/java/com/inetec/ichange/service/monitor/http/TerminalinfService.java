package com.inetec.ichange.service.monitor.http;

import com.inetec.ichange.service.monitor.databean.MonitorAgentDao;
import com.inetec.ichange.service.monitor.databean.SysterminalinfDataBean;
import com.inetec.ichange.service.monitor.utils.TerminalInfoCache;
import org.apache.log4j.Logger;

public class TerminalinfService extends Thread {
    private static final Logger log = Logger.getLogger(TerminalinfService.class);
    private boolean isRun = false;
    private String host = "192.168.20.22";
    public static final int I_SleepTime = 1 * 60 * 1000;
    public static TerminalInfoCache terminalCache = new TerminalInfoCache();

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
            processTerminalInfo();
            try {
                Thread.sleep(I_SleepTime);
            } catch (InterruptedException e) {
                // okay
            }
        }
        isRun = false;
    }

    private boolean processTerminalInfo() {
        boolean result = false;
        log.info("TerminalInfo process is start");
        McHttpClient client = new McHttpClient();
        try {
            client.init(new MonitorAgentDao().getMcHost());
            byte[] temp = client.vpnAll("0", "0", 5000);
            if (temp != null)
                terminalCache.init(SysterminalinfDataBean.stringToBeansForMc(new String(temp)));
        } catch (Exception e) {
            // TODO Auto-generated catch block
            log.warn("TerminalInfo process is start", e);
        }
        result = true;
        return result;

    }

    public void close() {
        isRun = false;
    }

    public static void main(String arg[]) throws Exception {
        TerminalinfService server = new TerminalinfService();
        server.start();
        Thread.sleep(6 * 1000);
        System.out.println("allvpn data:" + TerminalinfService.terminalCache.getAllList("0", "00", 500));
    }

}
