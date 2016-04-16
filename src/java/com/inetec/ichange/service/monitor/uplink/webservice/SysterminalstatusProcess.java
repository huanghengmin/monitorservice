package com.inetec.ichange.service.monitor.uplink.webservice;

import com.crgs.client.WebServiceClient;
import com.crgs.entities.Systerminalstatus;
import com.crgs.webservices.IReceiveData;
import com.inetec.ichange.service.monitor.uplink.databean.*;
import com.inetec.ichange.service.monitor.uplink.utils.UpFileUtils;
import com.inetec.ichange.service.monitor.utils.Pagination;
import org.apache.log4j.Logger;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: bluesky
 * Date: 11-3-16
 * Time: 上午10:21
 * To change this template use File | Settings | File Templates.\
 * 接入终端状态采取请求上报模式，即先由监管中心向下级监控系统发出请求指令
 * （包含系统标识、接入终端标识、请求内容等等），然后下级监控系统再向监管中
 * 心返回请求的数据
 */
public class SysterminalstatusProcess {
    private static Logger logger = Logger.getLogger(SysterminalstatusProcess.class);

    /**
     * report process
     */
    public void process(IReceiveData service) {

        logger.info("uplink-- Systerminalstatus up report begin! ");
        TPlatinfDao dao = new TPlatinfDao();
        Pagination<TPlatinfDataBean> platinflist = dao.listPlatinf(1, 1);
        if (platinflist == null || platinflist.getTotalCount() != 1) {
            logger.warn("uplinksystem-platform info not  init ！not uplink!");
            return;
        }
        TPlatinfDataBean tplatdatabean = platinflist.getItems().get(0);

        List<Systerminalstatus> systerminalstatuss = infoProcess(tplatdatabean);
        for(Systerminalstatus systerminalstatus : systerminalstatuss){
            String reuslt = service.saveSysterminalstatus(systerminalstatus);
            if (reuslt.equalsIgnoreCase("1")) {
                logger.info("uplink-- Systerminalstatus up report okay! ");
            } else {
                logger.info("uplink-- Systerminalstatus  up report faild! " + reuslt);
            }
           /* if (systerminalstatus.getTprintscreen() != null) {
                DataHandler handler = new DataHandler(new FileDataSource(UpFileUtils.getDataPath() + systerminalstatus.getTprintscreen()));

                reuslt = service.uploadFile(tplatdatabean.getPlatform_id(), systerminalstatus.getTprintscreen(), handler);
                if (reuslt.equalsIgnoreCase("1")) {
                    logger.info("uplink-- Systerminalstatus Tprintscreen upload okay! ");
                } else {
                    logger.info("uplink-- Systerminalstatus  Tprintscreen upload faild! " + reuslt);
                }
            }*/
        }
        logger.info("uplink-- Systerminalstatus up report end. ");

    }

    private  List<Systerminalstatus> infoProcess(TPlatinfDataBean databean) {
        List<Systerminalstatus>  systerminalstatuss = new ArrayList<Systerminalstatus>();
        SysterminalstatusDao dao = new SysterminalstatusDao();
        List<SysterminalstatusDataBean> sdbs = dao.list();
        for(SysterminalstatusDataBean sdb :sdbs){
            Systerminalstatus systerminalstatus = new Systerminalstatus();
            systerminalstatus.setIdsystem(databean.getPlatform_id());
            //systerminalstatus.setId(sdb.getId());
            systerminalstatus.setIdterminal(sdb.getIdTermianl());
            systerminalstatus.setIsonline(sdb.getOnline());
            systerminalstatus.setTprintscreen(sdb.getTprintScreen());
            systerminalstatus.setTscreentime(sdb.gettScreenTime());
            systerminalstatus.setAccessurl(sdb.getAccessUrl());
            systerminalstatuss.add(systerminalstatus);
        }
        return systerminalstatuss;
    }

}
