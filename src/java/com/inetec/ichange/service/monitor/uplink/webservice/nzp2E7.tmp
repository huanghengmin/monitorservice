package com.inetec.ichange.service.monitor.uplink.webservice;

import com.crgs.entities.Sysabnormal;
import com.crgs.webservices.IReceiveData;
import com.inetec.ichange.service.monitor.uplink.databean.*;
import com.inetec.ichange.service.monitor.utils.Pagination;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: bluesky
 * Date: 11-3-16
 * Time: 上午10:15
 * To change this template use File | Settings | File Templates.
 * 下级平台违规情况(lower_sysabnormal)
 * 使用Webservice方式上报，每天24：00-2：00之间上报一次
 */
public class LowerSysabnormalProcess {
    private static Logger logger = Logger.getLogger(LowerSysabnormalProcess.class);

    /**
     * 上传处理
     */
    public void process(IReceiveData service) {
        logger.info("uplink-- LowerSysabnormal up report begin! ");
        TPlatinfDao dao = new TPlatinfDao();
        Pagination<TPlatinfDataBean> platinflist = dao.listPlatinf(1, 1);
        if (platinflist == null || platinflist.getTotalCount() != 1) {
            logger.warn("uplinksystem-platform info not  init ！not uplink!");
            return;
        }
        TPlatinfDataBean tplatdatabean = platinflist.getItems().get(0);

        List<Sysabnormal> lowerSysabnormals = infoProcess(tplatdatabean);
        for(Sysabnormal lowerSysabnormal : lowerSysabnormals){
////            String reuslt = service.saveLowerSysabnormal(lowerSysabnormal);
//            if (reuslt.equalsIgnoreCase("1")) {
//                logger.info("uplink-- LowerSysabnormal up report okay! ");
//            } else {
//                logger.info("uplink-- LowerSysabnormal  up report faild! " + reuslt);
//            }
        }
        logger.info("uplink-- LowerSysabnormal up report end. ");

    }

    private  List<Sysabnormal> infoProcess(TPlatinfDataBean databean) {
        List<Sysabnormal>  lowerSysabnormals = new ArrayList<Sysabnormal>();
        LowerSysabnormalDao dao = new LowerSysabnormalDao();
        List<LowerSysabnormalDataBean> lsdbs = dao.list();
        for(LowerSysabnormalDataBean sdb :lsdbs){
            Sysabnormal lowerSysabnormal = new Sysabnormal();
            lowerSysabnormal.setIdsystem(databean.getPlatform_id());
            lowerSysabnormal.setIdalertmatter((long)sdb.getIdalertmatter());
            lowerSysabnormal.setAbnormaltypecode(sdb.getAbnormaltypecode());
            lowerSysabnormal.setConnectobjectcode(sdb.getConnectobjectcode());
            lowerSysabnormal.setExceptiondesc(sdb.getExceptiondesc());
            lowerSysabnormal.setHappentime(sdb.getHappentime());
            lowerSysabnormal.setTreattime(sdb.getTreattime());
            lowerSysabnormal.setTreadresult(sdb.getTreadresult());
            lowerSysabnormals.add(lowerSysabnormal);
        }
        return lowerSysabnormals;
    }
}
