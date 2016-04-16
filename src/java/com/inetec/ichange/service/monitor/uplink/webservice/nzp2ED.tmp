package com.inetec.ichange.service.monitor.uplink.webservice;

import com.crgs.entities.Sysbizstatus;
import com.crgs.webservices.IReceiveData;
import com.inetec.ichange.service.monitor.databean.BusinessRegister;
import com.inetec.ichange.service.monitor.uplink.databean.SysbizstatusDao;
import com.inetec.ichange.service.monitor.uplink.databean.SysbizstatusDataBean;
import com.inetec.ichange.service.monitor.uplink.databean.TPlatinfDao;
import com.inetec.ichange.service.monitor.uplink.databean.TPlatinfDataBean;
import com.inetec.ichange.service.monitor.utils.Pagination;
import org.apache.log4j.Logger;
import org.codehaus.xfire.XFireRuntimeException;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: bluesky
 * Date: 11-3-16
 * Time: 上午10:16
 * To change this template use File | Settings | File Templates.
 * 移动警务接入应用运行情况(Sysbizstatus)
 * 每天24：00-7：00之间上报1次
 */
public class SysbizstatusProcess {
    private static Logger logger = Logger.getLogger(SysbizstatusProcess.class);

    /**
     * 上传处理
     */
    public void process(IReceiveData service) {

        TPlatinfDao dao = new TPlatinfDao();
        Pagination<TPlatinfDataBean> platinflist = dao.listPlatinf(1, 1);
        if (platinflist.getTotalCount() != 1) {
            logger.warn("uplinksystem-platform info not  init ！not uplink!");
            return;
        }
        TPlatinfDataBean tplatdatabean = platinflist.getItems().get(0);

        List<Sysbizstatus> sysbizstatuses = infoProcess(tplatdatabean.getPlatform_id(), new BusinessRegister());
        for(Sysbizstatus sysbizstatus : sysbizstatuses){
            String reuslt = "-1";
            try {
                reuslt = service.saveSysbizstatus(sysbizstatus);
            } catch (XFireRuntimeException e) {
                logger.info("uplink-- Sysbizstatus up report error! ", e);
            }
            if (reuslt.equalsIgnoreCase("1")) {
                logger.info("uplink--  Sysbizstatus up report okay! by bizid:"+sysbizstatus.getIdbiz());
            } else {
                logger.info("uplink--  Sysbizstatus  up report faild! " + reuslt);
            }
        }
        logger.info("uplink--  Sysbizstatus up report all okay ! ");
    }

    private List<Sysbizstatus> infoProcess(String platformid, BusinessRegister businessRegister) {
        List<Sysbizstatus> sysbizstatuses = new ArrayList<Sysbizstatus>();
        SysbizstatusDao dao = new SysbizstatusDao();
        List<SysbizstatusDataBean> sdbs = dao.list();
        for ( SysbizstatusDataBean sdb : sdbs){
            Sysbizstatus sysbizstatus = new Sysbizstatus();
            sysbizstatus.setIdsystem(platformid);
            sysbizstatus.setIdbiz(sdb.getIdbiz());
            sysbizstatus.setAccess((long)sdb.getAccess());
            sysbizstatus.setBizname(sdb.getBizname());
            sysbizstatus.setInflux((long)sdb.getInflux());
            sysbizstatus.setOutflux((long)sdb.getOutflux());
            sysbizstatus.setStarttime(sdb.getStarttime());//UpFileUtils.getFoundDayStartDate()
            sysbizstatus.setEndtime(sdb.getEndtime());//UpFileUtils.getFoundDayEndDate()
            sysbizstatus.setTerminalnum((long)sdb.getTerminalnum());
            sysbizstatus.setAccesssum((long)sdb.getAccesssum());
            sysbizstatuses.add(sysbizstatus);
            logger.info("sysbizstatus bizid:"+sysbizstatus.getIdbiz());
            logger.info("sysbizstatus access:"+sysbizstatus.getAccess());
            logger.info("sysbizstatus bizname:"+sysbizstatus.getBizname());
            logger.info("sysbizstatus influx:"+sysbizstatus.getInflux());
            logger.info("sysbizstatus outflux:"+sysbizstatus.getOutflux());
            logger.info("sysbizstatus staarttime:"+sysbizstatus.getStarttime());
            logger.info("sysbizstatus endtime:"+sysbizstatus.getEndtime());
            logger.info("sysbizstatus terminalnum:"+sysbizstatus.getTerminalnum());
            logger.info("sysbizstatus accessum:"+sysbizstatus.getAccesssum());

        }

        return sysbizstatuses;
    }
}
