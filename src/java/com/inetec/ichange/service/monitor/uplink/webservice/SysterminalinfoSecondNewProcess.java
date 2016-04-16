package com.inetec.ichange.service.monitor.uplink.webservice;

import com.crgs.entities.SysterminalBean;
import com.crgs.webservices.IReceiveData;
import com.inetec.ichange.service.monitor.http.VpnHttpclient;
import com.inetec.ichange.service.monitor.uplink.databean.TPlatinfDao;
import com.inetec.ichange.service.monitor.uplink.databean.TPlatinfDataBean;
import com.inetec.ichange.service.monitor.utils.Pagination;
import org.apache.log4j.Logger;

/**
 * Created by IntelliJ IDEA.
 * User: bluesky
 * Date: 11-3-16
 * Time: 上午10:17
 * To change this template use File | Settings | File Templates.
 * 接入终端信息采用实时上报模式，即终端初次接入系统时上报信息一次，如果以后
 * 该终端的信息发生变更，再上报更新部分的信息即可。
 * 移动警务接入终端信息
 */
public class SysterminalinfoSecondNewProcess {
    private static Logger logger = Logger.getLogger(SysterminalinfoSecondNewProcess.class);
    public String vpnurl = VpnHttpclient.url;

    /**
     * 上传处理
     */
    public void process(IReceiveData service) {
        /*  TSbpzDao sbpzDao = new TSbpzDao();
        Pagination<TSbpzDataBean> sbpzlist = sbpzDao.listPlatinf(1, 1);
        if (sbpzlist.getTotalCount() != 1) {
            logger.warn("集控系统-级联平台信息没有初始化！无法级联上报");
            return;
        }*/
        TPlatinfDao dao = new TPlatinfDao();
        Pagination<TPlatinfDataBean> platinflist = dao.listPlatinf(1, 1);
        if (platinflist.getTotalCount() != 1) {
            logger.warn("uplinksystem-platform info not  init ！not uplink!");
            return;
        }
        boolean result = systerminalProcess(service);
        if (result) {
            logger.info("uplink-- SysterminalinfoSecondNew up report okay! ");
        } else {
            logger.info("uplink-- SysterminalinfoSecondNew  up report faild! ");
        }
    }

    public boolean systerminalProcess(IReceiveData service) {
//        SysclientserviceDao dao = new SysclientserviceDao();
//        SysclientserviceDataBean sdb = dao.listPlatinf(10,1).getItems().get(0);
        boolean result = false;
        VpnHttpclient vpn = new VpnHttpclient();
        try {
            vpn.init(vpnurl);
//            vpn.vpnNew("10001", "10020", 30);//"10001", "10020", 30
//            vpn.vpnBlock("10001","192.168.1.1","5");
//            vpn.vpnReleaseBlock("10001","192.168.1.1");
//            vpn.vpnPrintScreen("10001","192.168.1.1");
//            vpn.vpnRevokeCert("10001","192.168.1.1");
        } catch (Exception e) {
            logger.warn("get vpn new terminalInfo error", e);
        }
        SysterminalBean systerminalBean = new SysterminalBean();
        String reuslt = service.saveSysterminalinfo(systerminalBean);
        return result;
    }

}
