package com.inetec.ichange.service.monitor.business;

import com.inetec.common.exception.Ex;
import com.inetec.ichange.audit.client.AuditClient;
import com.inetec.ichange.service.monitor.databean.BusinessRegister;
import com.inetec.ichange.service.monitor.utils.BusinessDataBeanSet;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

/**
 * Busness ?????
 * Created by IntelliJ IDEA.
 * User: bluesky
 * Date: 2010-9-7
 * Time: 14:43:58
 * To change this template use File | Settings | File Templates.
 */
public class BusinessMonitorTypeService extends Thread {
    private final static Logger m_log = Logger.getLogger(BusinessMonitorTypeService.class);
    private boolean isRun = true;
    public static BusinessDataBeanSet dataset = new BusinessDataBeanSet();
    private List<IBusinessProcess> busiess = new ArrayList<IBusinessProcess>();
    private String platfromhost;
    private int platformport;
    private String auditaccount;
    private String auditpassword;
    private AuditClient client = new AuditClient();
    private String platformname = "";
    private BusinessLogImp buslog = new BusinessLogImp();
    private boolean isInitClient;
    private BusinessDbOperatorService dbservice = new BusinessDbOperatorService();
    private boolean isRunDbService = false;

    public BusinessMonitorTypeService() {

    }

    public void init(List<BusinessRegister> equs, String platformname, String host, int port, String account, String password) {
        this.platfromhost = host;
        this.platformport = port;
        dataset.init(equs);
        this.auditaccount = account;
        this.auditpassword = password;
        this.platformname = platformname;
        buslog.setPlatformName(platformname);
        dbservice.init(equs);

    }

    public boolean isRun() {
        return isRun;
    }

    public void run() {
        isRun = true;
        if (!buslog.isRun()) {
            busLogRun();
        }
        if (!isInitClient) {
            try {
                client.init(platfromhost, platformport);
                isInitClient = true;
            } catch (Ex ex) {
//                m_log.warn("audit Client init error.", ex);
            }

        }
        if (!isRunDbService) {
            dbservice.start();
        }
        while (isRun) {
            try {
                businessProcessRun();
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
        busiessProcessClose();

    }

    private void businessProcessRun() throws InterruptedException {

        try {
            if (client.isLogon()) {
                byte[] tem = client.readAuditLog();
                if (tem != null && tem.length > 2) {
                    buslog.processLog(tem);
                }
            } else {
                client.init(platfromhost, platformport);
                client.logon(auditaccount, auditpassword);
            }
        } catch (Ex ex) {
            m_log.warn("Audit Client Read Log error.", ex);
            Thread.sleep(30 * 1000);
        }
    }

    public void busLogRun() {
        new Thread(buslog).start();
    }

    private void busiessProcessClose() {
        client.close();
        buslog.close();
    }


}
