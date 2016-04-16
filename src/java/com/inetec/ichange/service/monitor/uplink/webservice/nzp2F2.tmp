package com.inetec.ichange.service.monitor.uplink.webservice;

import com.crgs.entities.Sysoutlinkinf;
import com.crgs.webservices.IReceiveData;
import com.inetec.ichange.service.monitor.uplink.databean.TOutlinkinfDao;
import com.inetec.ichange.service.monitor.uplink.databean.TOutlinkinfDataBean;
import com.inetec.ichange.service.monitor.uplink.databean.TPlatinfDao;
import com.inetec.ichange.service.monitor.uplink.databean.TPlatinfDataBean;
import com.inetec.ichange.service.monitor.uplink.utils.UpFileUtils;
import com.inetec.ichange.service.monitor.utils.Pagination;
import org.apache.log4j.Logger;
import org.codehaus.xfire.XFireRuntimeException;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: bluesky
 * Date: 11-3-16
 * Time: 上午10:12
 * To change this template use File | Settings | File Templates.
 * 移动警务系统外部链路注册信息(Sysoutlinkinf)
 * 初始上报一次，以后当信息有变更时再进行上报
 */
public class SysoutlinkinfProcess {
    private static Logger logger = Logger.getLogger(SysoutlinkinfProcess.class);

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


        List<Sysoutlinkinf> sysoutlinkinfs = infoProcess(tplatdatabean);
        for (Sysoutlinkinf sysoutlinkinf : sysoutlinkinfs){
            String reuslt = "-1";
            try {
                reuslt = service.saveSysoutlinkinf(sysoutlinkinf);
            } catch (XFireRuntimeException e) {
                logger.info("uplink-- Sysoutlinkinf up report error! ", e);
            }
            if (reuslt.equalsIgnoreCase("1")) {
                logger.info("uplink-- Sysoutlinkinf up report okay! ");
            } else {
                logger.info("uplink-- Sysoutlinkinf  up report faild! " + reuslt);
            }
        }
    }

    private List<Sysoutlinkinf> infoProcess(TPlatinfDataBean databean) {
        List<Sysoutlinkinf> sysoutlinkinfs = new ArrayList<Sysoutlinkinf>();
        TOutlinkinfDao dao = new TOutlinkinfDao();
        List<TOutlinkinfDataBean> todbs = dao.list();
        for (int i = 0 ; i<todbs.size();i++){
            TOutlinkinfDataBean todb = todbs.get(i);
            Sysoutlinkinf sysoutlinkinf = new Sysoutlinkinf();
            sysoutlinkinf.setIdsystem(databean.getPlatform_id());
            sysoutlinkinf.setIdoutconnlink(todb.getId());
            sysoutlinkinf.setOutlinkvender(todb.getLink_Corp());
            sysoutlinkinf.setOutlinkdisc(todb.getLink_name());
            sysoutlinkinf.setOutlinkbandwidth(todb.getLink_bandwidth());
            sysoutlinkinf.setCollecttime(UpFileUtils.getCurrentDayDate());
            sysoutlinkinfs.add(sysoutlinkinf);
        }
        return sysoutlinkinfs;
    }
}
