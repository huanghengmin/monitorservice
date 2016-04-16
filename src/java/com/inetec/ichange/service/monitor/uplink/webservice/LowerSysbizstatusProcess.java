package com.inetec.ichange.service.monitor.uplink.webservice;

import com.crgs.entities.Sysbizstatus;
import com.crgs.webservices.IReceiveData;
import com.inetec.ichange.service.monitor.databean.BusinessRegister;
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
 * Time: ����10:16
 * To change this template use File | Settings | File Templates.
 * �¼�ƽ̨����Ӧ���������(lower_sysbizstatus)
 * ʹ��Webservice��ʽ�ϱ���ÿ��24��00-2��00֮���ϱ�һ��
 */
public class LowerSysbizstatusProcess {
    private static Logger logger = Logger.getLogger(LowerSysbizstatusProcess.class);

    /**
     * �ϴ�����
     */
    public void process(IReceiveData service) {

        TPlatinfDao dao = new TPlatinfDao();
        Pagination<TPlatinfDataBean> platinflist = dao.listPlatinf(1, 1);
        if (platinflist.getTotalCount() != 1) {
            logger.warn("uplinksystem-platform info not  init ��not uplink!");
            return;
        }
        TPlatinfDataBean tplatdatabean = platinflist.getItems().get(0);

        List<Sysbizstatus> lowerSysbizstatuss = infoProcess(tplatdatabean.getPlatform_id(), new BusinessRegister());
        for(Sysbizstatus lowerSysbizstatus : lowerSysbizstatuss){
            String reuslt = "-1";
            try {
//                reuslt = service.saveLowerSysbizstatus(lowerSysbizstatus);
            } catch (XFireRuntimeException e) {
                logger.info("uplink-- LowerSysbizstatus up report error! ", e);
            }
            if (reuslt.equalsIgnoreCase("1")) {
                logger.info("uplink--  LowerSysbizstatus up report okay! ");
            } else {
                logger.info("uplink--  LowerSysbizstatus  up report faild! " + reuslt);
            }
        }
    }

    private List<Sysbizstatus> infoProcess(String platformid, BusinessRegister businessRegister) {
        List<Sysbizstatus> lowerSysbizstatuss = new ArrayList<Sysbizstatus>();
        LowerSysbizstatusDao dao = new LowerSysbizstatusDao();
        List<LowerSysbizstatusDataBean> lsdbs = dao.list();
        for (LowerSysbizstatusDataBean sdb : lsdbs){
            Sysbizstatus lowerSysbizstatus = new Sysbizstatus();
            lowerSysbizstatus.setIdsystem(platformid);
            lowerSysbizstatus.setIdbiz(sdb.getIdbiz());
            lowerSysbizstatus.setAccess(sdb.getAccess());
            lowerSysbizstatus.setBizname(sdb.getBizname());
            lowerSysbizstatus.setInflux(sdb.getInflux());
            lowerSysbizstatus.setOutflux(sdb.getOutflux());
            lowerSysbizstatus.setStarttime(sdb.getStarttime());//UpFileUtils.getFoundDayStartDate()
            lowerSysbizstatus.setEndtime(sdb.getEndtime());//UpFileUtils.getFoundDayEndDate()
            lowerSysbizstatus.setTerminalnum(sdb.getTerminalnum());
            lowerSysbizstatus.setAccesssum(sdb.getAccesssum());
            lowerSysbizstatuss.add(lowerSysbizstatus);
        }

        return lowerSysbizstatuss;
    }
}
