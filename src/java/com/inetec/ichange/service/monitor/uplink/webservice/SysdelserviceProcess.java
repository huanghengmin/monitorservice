package com.inetec.ichange.service.monitor.uplink.webservice;

import com.crgs.entities.Sysdelservice;
import com.crgs.webservices.IReceiveData;
import com.inetec.ichange.service.monitor.uplink.databean.SysdelserviceDao;
import com.inetec.ichange.service.monitor.uplink.databean.SysdelserviceDataBean;
import com.inetec.ichange.service.monitor.uplink.databean.TPlatinfDao;
import com.inetec.ichange.service.monitor.uplink.databean.TPlatinfDataBean;
import com.inetec.ichange.service.monitor.utils.Pagination;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: qxp
 * Date: 12-3-15
 * Time: am 10:41
 * 删除已上报内容的服务(Sysdelservice)
 * 用于下级监控系统向移动警务监管中心发送删除请求，以便删除之前已上报但不再使用的
 * 对象，比如接入应用，终端，外部链路，安全策略等注册信息。
 */
public class SysdelserviceProcess {
    private static Logger logger = Logger.getLogger(SysdelserviceProcess.class);

    /**
     * report process
     */
    public void process(IReceiveData service) {
        logger.info("uplink-- Sysdeleservice up report begin! ");
        TPlatinfDao dao = new TPlatinfDao();
        Pagination<TPlatinfDataBean> platinflist = dao.listPlatinf(1, 1);
        if (platinflist == null || platinflist.getTotalCount() != 1) {
            logger.warn("uplinksystem-platform info not  init ！not uplink!");
            return;
        }
        TPlatinfDataBean tplatdatabean = platinflist.getItems().get(0);

        List<Sysdelservice> delserviceinfs = infoProcess(tplatdatabean);
        for(Sysdelservice delserviceinf : delserviceinfs){
            String reuslt = service.saveSysdelservice(delserviceinf);
            if (reuslt.equalsIgnoreCase("1")) {
                logger.info("uplink-- Sysdeleservice up report okay! ");
            } else {
                logger.info("uplink-- Sysdeleservice  up report faild! " + reuslt);
            }
        }
        logger.info("uplink-- Sysdeleservice up report end. ");

    }

    private  List<Sysdelservice> infoProcess(TPlatinfDataBean databean) {
        List<Sysdelservice>  sysdeleservices = new ArrayList<Sysdelservice>();
        SysdelserviceDao dao = new SysdelserviceDao();
        List<SysdelserviceDataBean> sdbs = dao.list();
        for(int i = 0;i< sdbs.size();i++){
            Sysdelservice sysdelservice = new Sysdelservice();
            SysdelserviceDataBean sdb = sdbs.get(i);
            sysdelservice.setIdsystem(databean.getPlatform_id());
            sysdelservice.setObjectname(sdb.getObjectname());
            String idsStr = sdb.getIds();
            String[] idsStrs = idsStr.split(",");
            long[] ids = new long[idsStrs.length];
            for(int k = 0 ; k < idsStrs.length ; k ++){
                ids[i] = Long.parseLong(idsStrs[i]);
            }
            sysdelservice.setIdobject(ids);
            sysdeleservices.add(sysdelservice);
        }
        return sysdeleservices;
    }
}
