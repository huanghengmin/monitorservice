package com.inetec.ichange.service.monitor.uplink.webservice;

import com.crgs.entities.Sysstatus;
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
 * Time: 上午10:15
 * To change this template use File | Settings | File Templates.
 * 下级平台系统运行情况(lower_sysstatus)
 * 使用Webservice方式上报，每天24：00-2：00之间上报一次
 */
public class LowerSysstatusProcess {
    private static Logger logger = Logger.getLogger(LowerSysstatusProcess.class);

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

        List<Sysstatus> lowerSysstatuses = infoProcess(tplatdatabean);
        for ( Sysstatus lowerSysstatus : lowerSysstatuses) {
            String reuslt = "-1";
            try {
//                reuslt = service.saveLowerSysstatus(lowerSysstatus);
            } catch (XFireRuntimeException e) {
                logger.info("uplink-- LowerSysstatus up report error! ", e);
            }
            if (reuslt.equalsIgnoreCase("1")) {
                logger.info("uplink-- LowerSysstatus up report okay! ");
            } else {
                logger.info("uplink-- LowerSysstatus  up report faild! " + reuslt);
            }
        }
    }

    private List<Sysstatus> infoProcess(TPlatinfDataBean databean) {
        List<Sysstatus> lowerSysstatuses = new ArrayList<Sysstatus>();
        LowerSysstatusDao dao = new LowerSysstatusDao();
        List<LowerSysstatusDataBean> sdbs = dao.list();
        for (LowerSysstatusDataBean sdb : sdbs){
            Sysstatus lowerSysstatus = new Sysstatus();
            lowerSysstatus.setIdsystem(databean.getPlatform_id());
            lowerSysstatus.setAccess( sdb.getAccess());
            lowerSysstatus.setBizsum( sdb.getBizsum());
            lowerSysstatus.setInflux( sdb.getInflux());
            lowerSysstatus.setOutflux( sdb.getOutflux());
            lowerSysstatus.setAccesssum(sdb.getAccesssum());
            lowerSysstatus.setStarttime(sdb.getStarttime());//UpFileUtils.getFoundDayStartDate()
            lowerSysstatus.setEndtime(sdb.getEndtime()); //UpFileUtils.getFoundDayStartDate()
            lowerSysstatus.setTerminalnum( sdb.getTerminalnum());
            lowerSysstatuses.add(lowerSysstatus);
        }

        return lowerSysstatuses;
    }
}
