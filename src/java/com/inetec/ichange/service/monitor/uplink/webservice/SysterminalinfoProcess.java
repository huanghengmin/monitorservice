package com.inetec.ichange.service.monitor.uplink.webservice;

import com.crgs.entities.SysterminalBean;
import com.crgs.entities.Systerminalinfo;
import com.crgs.webservices.IReceiveData;
import com.inetec.ichange.service.monitor.http.VpnHttpclient;
import com.inetec.ichange.service.monitor.uplink.databean.*;
import com.inetec.ichange.service.monitor.uplink.utils.UpFileUtils;
import com.inetec.ichange.service.monitor.utils.Pagination;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

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
public class SysterminalinfoProcess {
    private static Logger logger = Logger.getLogger(SysterminalinfoProcess.class);
    public String vpnurl = VpnHttpclient.url;

    /**
     * 上传处理
     */
    public void process(IReceiveData service) {

        TPlatinfDao dao = new TPlatinfDao();
        Pagination<TPlatinfDataBean> platinflist = dao.listPlatinf(1, 1);
        if (platinflist == null || platinflist.getTotalCount() != 1) {
            logger.warn("uplinksystem-platform info not  init ！not uplink!");
            return;
        }
        TPlatinfDataBean tplatdatabean = platinflist.getItems().get(0);
        List<Systerminalinfo> systerminalinfos = infoProcess(tplatdatabean);

        SysterminalBean systerminalBean = new SysterminalBean();
        systerminalBean.setIdsystem(tplatdatabean.getPlatform_id());
        systerminalBean.setTerminalList(systerminalinfos);

        String reuslt = service.saveSysterminalinfo(systerminalBean);
        if (reuslt.equalsIgnoreCase("1")) {
            logger.info("uplink-- Systerminalinfo up report okay! ");
        } else {
            logger.info("uplink-- Systerminalinfo  up report faild! " + reuslt);
        }
        logger.info("uplink-- Systerminalinfo up report end. ");
    }

    private List<Systerminalinfo> infoProcess(TPlatinfDataBean tplatdatabean) {
        List<Systerminalinfo> result = new ArrayList<Systerminalinfo>();
        SysterminalinfDao dao = new SysterminalinfDao();
        List<SysterminalinfDataBean> tdbs = dao.list();
        for (SysterminalinfDataBean sdb : tdbs) {
            Systerminalinfo terminalinfo = new Systerminalinfo();
            //terminalinfo.setId(sdb.getId());
            terminalinfo.setIdsystem(tplatdatabean.getPlatform_id());
            terminalinfo.setIdterminal(sdb.getId());
            terminalinfo.setTerminalName(sdb.getTerminalName());//
            terminalinfo.setTerminaltype(sdb.getTerminalType()); //"003"
            terminalinfo.setTermianlOutlink(sdb.getTerminalOutLink());//"002"
            terminalinfo.setTermianlos(sdb.getTerminalOS()); //"005"
            terminalinfo.setTermianlband(sdb.getTerminalBand()); //"003"
            terminalinfo.setCardtype(sdb.getCardType()); //"001"
            terminalinfo.setCardname(sdb.getCardName()); //""
            terminalinfo.setCardversion(sdb.getCard_version());  //"v2.0"
            terminalinfo.setUsername(sdb.getUserName());//"" + i
            terminalinfo.setUserdepart(sdb.getUserDepart()); //tplatdatabean.getJsdw()
            terminalinfo.setUserzone(sdb.getUserZone());  //tplatdatabean.getJsdw()
            terminalinfo.setPolicenumber(sdb.getPoliceNumber());//"222222222"
            terminalinfo.setUserid(sdb.getUserId()); //"ja20091201256"
            if (sdb.getRegTime() == null)
                terminalinfo.setRegtime(UpFileUtils.getCurrentDayDate());
            else

                terminalinfo.setRegtime(sdb.getRegTime());  //UpFileUtils.getCurrentDayDate()
            terminalinfo.setIfcancel(sdb.getIfcancel()); //"0"
            result.add(terminalinfo);
        }
        return result;
    }

}
