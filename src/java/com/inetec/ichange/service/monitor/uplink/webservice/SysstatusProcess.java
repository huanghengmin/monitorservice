package com.inetec.ichange.service.monitor.uplink.webservice;

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
 * Time: 上午10:15
 * To change this template use File | Settings | File Templates.
 * 移动警务系统运行情况(Sysstatus)
 * 每天24：00-7：00之间上报1次
 */
public class SysstatusProcess {
    private static Logger logger = Logger.getLogger(SysstatusProcess.class);

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


        List<Sysstatus> sysstatuses = infoProcess(tplatdatabean);
        for ( Sysstatus sysstatus : sysstatuses) {
            String reuslt = "-1";
            try {
                reuslt = service.saveSysstatus(sysstatus);
            } catch (XFireRuntimeException e) {
                logger.info("uplink-- Sysstatus up report error! ", e);
            }
            if (reuslt.equalsIgnoreCase("1")) {
                logger.info("uplink-- Sysstatus up report okay! ");
            } else {
                logger.info("uplink-- Sysstatus  up report faild! " + reuslt);
            }
        }
    }

    private List<Sysstatus> infoProcess(TPlatinfDataBean databean) {
        List<Sysstatus> sysstatuses = new ArrayList<Sysstatus>();
        SysstatusDao dao = new SysstatusDao();
        List<SysstatusDataBean> sdbs = dao.list();
        for ( int i = 0 ; i < sdbs.size() ; i ++ ){
            SysstatusDataBean sdb = sdbs.get(i);
            Sysstatus sysstatus = new Sysstatus();
            sysstatus.setIdsystem(databean.getPlatform_id());
            sysstatus.setAccess( (long)sdb.getAccess());
            sysstatus.setBizsum( sdb.getBizsum());
            sysstatus.setInflux( sdb.getInflux());
            sysstatus.setOutflux( sdb.getOutflux());
            sysstatus.setAccesssum((long)sdb.getAccesssum());
            sysstatus.setStarttime(sdb.getStarttime());//UpFileUtils.getFoundDayStartDate()
            sysstatus.setEndtime(sdb.getEndtime()); //UpFileUtils.getFoundDayStartDate()
            sysstatus.setTerminalnum((long) sdb.getTerminalnum());
            sysstatuses.add(sysstatus);
            logger.info("sysstatus Idsystem:"+sysstatus.getIdsystem());
            logger.info("sysstatus access:"+sysstatus.getAccess());
            logger.info("sysstatus bizsum:"+sysstatus.getBizsum());
            logger.info("sysstatus influx:"+sysstatus.getInflux());
            logger.info("sysstatus outflux:"+sysstatus.getOutflux());
            logger.info("sysstatus staarttime:"+sysstatus.getStarttime());
            logger.info("sysstatus endtime:"+sysstatus.getEndtime());
            logger.info("sysstatus terminalnum:"+sysstatus.getTerminalnum());
            logger.info("sysstatus accessum:"+sysstatus.getAccesssum());
        }
        if(sysstatuses.size()==0){
            SysstatusDataBean sdb = dao.getFoundDayBean();
             if(sdb==null){
                sdb= new SysstatusDataBean();
                 sdb.setIdsystem(databean.getPlatform_id());
                 sdb.setAccess(200);
                 sdb.setAccesssum(400);
                 sdb.setBizsum(20);
                 sdb.setInflux(4000);
                 sdb.setOutflux(4000);
                 sdb.setStarttime(new Timestamp(UpFileUtils.getFoundDayStartDate().getTime()));
                 sdb.setEndtime(new Timestamp(UpFileUtils.getFoundDayEndDate().getTime()));
                 sdb.setWritetime(new Timestamp(UpFileUtils.getCurrentDayDate().getTime()));
                dao.save(sdb);
            }
            Sysstatus sysstatus = new Sysstatus();
            sysstatus.setIdsystem(databean.getPlatform_id());
            sysstatus.setAccess( (long)sdb.getAccess());
            sysstatus.setBizsum( sdb.getBizsum());
            sysstatus.setInflux( sdb.getInflux());
            sysstatus.setOutflux( sdb.getOutflux());
            sysstatus.setAccesssum((long)sdb.getAccesssum());
            sysstatus.setStarttime(sdb.getStarttime());//UpFileUtils.getFoundDayStartDate()
            sysstatus.setEndtime(sdb.getEndtime()); //UpFileUtils.getFoundDayStartDate()
            sysstatus.setTerminalnum((long) sdb.getTerminalnum());
            sysstatuses.add(sysstatus);
        }
        return sysstatuses;
    }
}
