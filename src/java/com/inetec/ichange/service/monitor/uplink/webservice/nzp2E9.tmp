package com.inetec.ichange.service.monitor.uplink.webservice;

import com.crgs.entities.Sysruntime;
import com.crgs.webservices.IReceiveData;
import com.inetec.ichange.service.monitor.uplink.databean.*;
import com.inetec.ichange.service.monitor.utils.Pagination;
import org.apache.log4j.Logger;
import org.codehaus.xfire.XFireRuntimeException;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: bluesky
 * Date: 11-3-16
 * Time: 上午10:14
 * To change this template use File | Settings | File Templates.
 * 下级平台系统状态(Lower_sysruntime)
 * 使用Webservice方式上报，每天12：00-14：00之间；24：00-2：00之间上报两次
 */
public class LowerSysruntimeProcess {
    private static Logger logger = Logger.getLogger(LowerSysruntimeProcess.class);

    /**
     * 上传处理
     */
    public void process(IReceiveData service) {

        TPlatinfDao dao = new TPlatinfDao();
        Pagination<TPlatinfDataBean> platinflist = dao.listPlatinf(1, 1);
        if (platinflist==null||platinflist.getTotalCount() != 1) {
            logger.warn("uplinksystem-platform info not  init ！not uplink!");
            return;
        }
        TPlatinfDataBean tplatdatabean = platinflist.getItems().get(0);

        List<Sysruntime> lowerSysruntimes = infoProcess(tplatdatabean);
        for(Sysruntime lowerSysruntime : lowerSysruntimes){
            String result = "-1";
            try {
//                result = service.saveLowerSysruntime(lowerSysruntime);
            } catch (XFireRuntimeException e) {
                logger.info("uplink-- LowerSysruntime up report error! ", e);
            }
            if (result.equalsIgnoreCase("1")) {
                logger.info("uplink-- LowerSysruntime up report okay! ");
            } else {
                logger.info("uplink-- LowerSysruntime  up report faild! " + result);
            }
        }
    }

    private List<Sysruntime> infoProcess(TPlatinfDataBean databean) {
        List<Sysruntime> lowerSysruntimes = new ArrayList<Sysruntime>();
        LowerSysruntimeDao dao = new LowerSysruntimeDao();
        List<LowerSysruntimeDataBean> sdbs = dao.list();
        for(LowerSysruntimeDataBean sdb:sdbs){
            Sysruntime lowerSysruntime = new Sysruntime();
            lowerSysruntime.setIdsystem(databean.getPlatform_id());
            lowerSysruntime.setRunstatecode(sdb.getRunstatecode());
            lowerSysruntime.setDesc(sdb.getDesc());
            lowerSysruntime.setStarttime(sdb.getStarttime());
            lowerSysruntime.setEndtime(sdb.getEndtime());
            lowerSysruntimes.add(lowerSysruntime);
        }
        return lowerSysruntimes;
    }
}
