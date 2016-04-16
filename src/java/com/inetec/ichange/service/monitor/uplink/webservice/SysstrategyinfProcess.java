package com.inetec.ichange.service.monitor.uplink.webservice;

import com.crgs.entities.Sysstrategyinf;
import com.crgs.webservices.IReceiveData;
import com.inetec.ichange.service.monitor.http.VpnHttpclient;
import com.inetec.ichange.service.monitor.uplink.databean.SysstrategyinfDao;
import com.inetec.ichange.service.monitor.uplink.databean.SysstrategyinfDataBean;
import com.inetec.ichange.service.monitor.uplink.databean.TPlatinfDao;
import com.inetec.ichange.service.monitor.uplink.databean.TPlatinfDataBean;
import com.inetec.ichange.service.monitor.uplink.utils.UpFileUtils;
import com.inetec.ichange.service.monitor.utils.Pagination;
import org.apache.log4j.Logger;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: bluesky
 * Date: 11-3-16
 * Time: 上午10:17
 * To change this template use File | Settings | File Templates.
 * 移动警务系统安全策略注册信息(Sysstrategyinf)
 * 初始上报一次，以后当信息有变更时再进行上报
 */
public class SysstrategyinfProcess {
    private static Logger logger = Logger.getLogger(SysstrategyinfProcess.class);
    public String vpnurl = VpnHttpclient.url;

    /**
     * 上传处理
     */
    public void process(IReceiveData service) {

        logger.info("uplink-- Sysstrategyinf up report begin");
        TPlatinfDao dao = new TPlatinfDao();
        Pagination<TPlatinfDataBean> platinflist = dao.listPlatinf(1, 1);
        if (platinflist==null||platinflist.getTotalCount() != 1) {
            logger.warn("uplinksystem-platform info not  init ！not uplink!");
            return;
        }
        TPlatinfDataBean tplatdatabean = platinflist.getItems().get(0);
        //TSbpzDataBean tspz = sbpzlist.getItems().get(0);

        boolean result = systrategyinfProcess(tplatdatabean, service);
        if (result) {
            logger.info("uplink-- Sysstrategyinf up report okay! ");
        } else {
            logger.info("uplink-- Sysstrategyinf  up report faild! ");
        }
           logger.info("uplink-- Sysstrategyinf up report end.");

    }

    public boolean systrategyinfProcess(TPlatinfDataBean tplatdatabean, IReceiveData service) {
        boolean result = false;
        SysstrategyinfDao dao = new SysstrategyinfDao();
        List<SysstrategyinfDataBean> sdbs = dao.list();
        for(SysstrategyinfDataBean sdb : sdbs){
            Sysstrategyinf systrategyinf = new Sysstrategyinf();
            systrategyinf.setIdsystem(tplatdatabean.getPlatform_id());
            systrategyinf.setIdstrategy((long) sdb.getId());//2
            systrategyinf.setStrategydesc(sdb.getStrategy_desc());//"部消防局移动警务接入应用安全策略，终端用户组安全策略"
            systrategyinf.setStrategyprotocaltype(sdb.getStrategy_protocal_type());//"1003"
            systrategyinf.setSrcip(sdb.getScr_ip());//"171.0.0.0-171.0.255.255"
            systrategyinf.setDestip(sdb.getDest_ip());//"10.2.61.92"
            systrategyinf.setSrcport(sdb.getSrc_port()); //"0-65536"
            systrategyinf.setDestport(sdb.getDest_port()); //"8443"
            systrategyinf.setCollecttime(sdb.getCollect_time());//UpFileUtils.getCurrentDayDate()
            String reuslt = service.saveSysstrategyinf(systrategyinf);

            if (reuslt.equalsIgnoreCase("1")) {
                logger.info("uplink-- systrategyinf  up report okay!");
                result = true;
            } else {
                logger.info("uplink-- systrategyinf  up report faild! :" + result);
            }
        }
        return result;

    }


}
