package com.inetec.ichange.service.monitor.uplink.webservice;

import com.crgs.entities.Sysabnormal;
import com.crgs.webservices.IReceiveData;
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
 * Time: 上午10:15
 * To change this template use File | Settings | File Templates.
 * 移动警务系统违规情况(Sysabnormal)
 * 每天24：00-7：00之间上报1次
 */
public class SysabnormalProcess {
    private static Logger logger = Logger.getLogger(SysabnormalProcess.class);

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
         logger.info("uplink-- Sysabnormal up report begin! ");
        TPlatinfDao dao = new TPlatinfDao();
        Pagination<TPlatinfDataBean> platinflist = dao.listPlatinf(1, 1);
        if (platinflist == null || platinflist.getTotalCount() != 1) {
            logger.warn("uplinksystem-platform info not  init ！not uplink!");
            return;
        }
        TPlatinfDataBean tplatdatabean = platinflist.getItems().get(0);
        //TSbpzDataBean tspz = sbpzlist.getItems().get(0);

        List<Sysabnormal> reginfs = infoProcess(tplatdatabean);
        for(Sysabnormal reginf : reginfs){
            String reuslt = service.saveSysabnormal(reginf);
            if (reuslt.equalsIgnoreCase("1")) {
                logger.info("uplink-- Sysabnormal up report okay! ");
            } else {
                logger.info("uplink-- Sysabnormal  up report faild! " + reuslt);
            }
        }
        logger.info("uplink-- Sysabnormal up report end. ");

    }

    private  List<Sysabnormal> infoProcess(TPlatinfDataBean databean) {
        List<Sysabnormal>  sysreginfs = new ArrayList<Sysabnormal>();
        SysabnormalDao dao = new SysabnormalDao();
        List<SysabnormalDataBean> sdbs = dao.list();
        for(int i = 0;i< sdbs.size();i++){
            SysabnormalDataBean sdb = sdbs.get(i);
            Sysabnormal sysreginf = new Sysabnormal();
            sysreginf.setIdsystem(databean.getPlatform_id());
            sysreginf.setIdalertmatter((long) sdb.getIdalertmatter());
            sysreginf.setAbnormaltypecode(sdb.getAbnormaltypecode());
            sysreginf.setConnectobjectcode(sdb.getConnectobjectcode());
            sysreginf.setExceptiondesc(sdb.getExceptiondesc());
            sysreginf.setHappentime(sdb.getHappentime());
            sysreginf.setTreattime(sdb.getTreattime());
            sysreginf.setTreadresult(sdb.getTreadresult());
            sysreginfs.add(sysreginf);
        }
        return sysreginfs;
    }
}
