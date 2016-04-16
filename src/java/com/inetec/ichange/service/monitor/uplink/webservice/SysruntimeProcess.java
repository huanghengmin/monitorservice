package com.inetec.ichange.service.monitor.uplink.webservice;

import com.crgs.entities.Sysruntime;
import com.crgs.entities.Sysstatus;
import com.crgs.webservices.IReceiveData;
import com.inetec.ichange.service.monitor.uplink.databean.*;
import com.inetec.ichange.service.monitor.uplink.utils.UpFileUtils;
import com.inetec.ichange.service.monitor.utils.Pagination;
import org.apache.log4j.Logger;
import org.codehaus.xfire.XFireRuntimeException;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: bluesky
 * Date: 11-3-16
 * Time: 上午10:14
 * To change this template use File | Settings | File Templates.
 * 移动警务系统状态(Sysruntime)
 * 每天24：00-7：00之间上报1次
 */
public class SysruntimeProcess {
    private static Logger logger = Logger.getLogger(SysruntimeProcess.class);

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
        if (platinflist==null||platinflist.getTotalCount() != 1) {
            logger.warn("uplinksystem-platform info not  init ！not uplink!");
            return;
        }
        TPlatinfDataBean tplatdatabean = platinflist.getItems().get(0);
        //TSbpzDataBean tspz = sbpzlist.getItems().get(0);


        List<Sysruntime> sysruntimes = infoProcess(tplatdatabean);
        for(Sysruntime sysruntime : sysruntimes){
            String result = "-1";
            try {
                result = service.saveSysruntime(sysruntime);
            } catch (XFireRuntimeException e) {
                logger.info("uplink-- Sysruntime up report error! ", e);
            }
            if (result.equalsIgnoreCase("1")) {
                logger.info("uplink-- Sysruntime up report okay! ");
            } else {
                logger.info("uplink-- Sysruntime  up report faild! " + result);
            }
        }
    }

    private List<Sysruntime> infoProcess(TPlatinfDataBean databean) {
        List<Sysruntime> sysreginfs = new ArrayList<Sysruntime>();
        SysruntimeDao dao = new SysruntimeDao();
        List<SysruntimeDataBean> sdbs = dao.list();
        for(SysruntimeDataBean sdb:sdbs){
            Sysruntime sysruntime = new Sysruntime();
            sysruntime.setIdsystem(databean.getPlatform_id());
            sysruntime.setRunstatecode(sdb.getRunstatecode());
            sysruntime.setDesc(sdb.getDesc());
            sysruntime.setStarttime(sdb.getStarttime());
            sysruntime.setEndtime(sdb.getEndtime());
            sysreginfs.add(sysruntime);
            logger.info("sysruntime Idsystem:"+sysruntime.getIdsystem());
            logger.info("sysruntime Runstatecode:"+sysruntime.getRunstatecode());
            logger.info("sysruntime Desc:"+sysruntime.getDesc());
            logger.info("sysruntime StartTime:"+sysruntime.getStarttime());
            logger.info("sysruntime EndTime:"+sysruntime.getEndtime());

        }
        if(sysreginfs.size()==0){
            SysruntimeDataBean sdb=dao.getFoundDayBean();
            if(sdb==null){
                sdb= new SysruntimeDataBean();
                sdb.setRunstatecode("001");
                sdb.setDesc("正常");
                sdb.setIdsystem(databean.getPlatform_id());
                sdb.setStarttime(new Timestamp(UpFileUtils.getFoundDayStartDate().getTime()));
                sdb.setEndtime(new Timestamp(UpFileUtils.getFoundDayEndDate().getTime()));
                sdb.setWritetime(new Timestamp(UpFileUtils.getCurrentDayDate().getTime()));
                dao.save(sdb);
            }
            Sysruntime sysreginf = new Sysruntime();
            sysreginf.setIdsystem(sdb.getIdsystem());
            sysreginf.setRunstatecode(sdb.getRunstatecode());
            sysreginf.setDesc(sdb.getDesc());
            sysreginf.setStarttime(sdb.getStarttime());
            sysreginf.setEndtime(sdb.getEndtime());
            sysreginfs.add(sysreginf);
        }
        return sysreginfs;
    }
}
