package com.inetec.ichange.service.monitor.uplink.webservice;

import com.crgs.entities.Sysclientservice;
import com.crgs.webservices.IReceiveData;
import com.crgs.webservices.ISysClient;
import com.inetec.ichange.service.monitor.uplink.databean.SysclientserviceDao;
import com.inetec.ichange.service.monitor.uplink.databean.SysclientserviceDataBean;
import com.inetec.ichange.service.monitor.uplink.databean.TPlatinfDao;
import com.inetec.ichange.service.monitor.uplink.databean.TPlatinfDataBean;
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
 * 移动警务客户端服务(Sysclientservice)
 * 本客户端服务负责接收监管中心发出的请求与指令，即先由监管中心向下级监控
 * 系统发出指令（内容包含系统标识、接入终端标识、请求动作等等），然后下级
 * 监控系统再向监管中心返回请求的数据
 * 服务的URL必须为：http://ip:port/mmcs/services/sysclient_wbs?wsdl
 * 接口名称和方法名称为：ISysClient.saveSysclientservice(Sysclientservice s)
 */
public class SysclientserviceProcess {
    private static Logger logger = Logger.getLogger(SysclientserviceProcess.class);

    /**
     * up report
     */
    public void process(ISysClient client) {

        logger.info("uplink-- Sysclientservice up report begin! ");
        TPlatinfDao dao = new TPlatinfDao();
        Pagination<TPlatinfDataBean> platinflist = dao.listPlatinf(1, 1);
        if (platinflist == null || platinflist.getTotalCount() != 1) {
            logger.warn("uplinksystem-platform info not  init ！not uplink!");
            return;
        }
        TPlatinfDataBean tplatdatabean = platinflist.getItems().get(0);
        //TSbpzDataBean tspz = sbpzlist.getItems().get(0);

        List<Sysclientservice> sysclientservices = infoProcess(tplatdatabean);
        for(Sysclientservice sysclientservice : sysclientservices){
            String reuslt = client.saveSysclientservice(sysclientservice);
            if (reuslt.equalsIgnoreCase("1")) {
                logger.info("uplink-- Sysclientservice up report okay! ");
            } else {
                logger.info("uplink-- Sysclientservice  up report faild! " + reuslt);
            }
        }
        logger.info("uplink-- Sysclientservice up report end. ");

    }

    private  List<Sysclientservice> infoProcess(TPlatinfDataBean databean) {
        List<Sysclientservice>  sysclientservices = new ArrayList<Sysclientservice>();
        SysclientserviceDao dao = new SysclientserviceDao();
        List<SysclientserviceDataBean> sdbs = dao.list();
        for(int i = 0;i< sdbs.size();i++){
            SysclientserviceDataBean sdb = sdbs.get(i);
            Sysclientservice sysclientservice = new Sysclientservice();
            sysclientservice.setIdsystem(databean.getPlatform_id());
            sysclientservice.setIdterminal((long) sdb.getIdTerminal());
            sysclientservice.setTaction(sdb.getTaction());
            sysclientservices.add(sysclientservice);
        }
        return sysclientservices;
    }
}
