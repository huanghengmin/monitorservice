package com.inetec.ichange.service.monitor.uplink.webservice;

import com.crgs.entities.Sysqueryservice;
import com.crgs.webservices.IReceiveData;
import com.inetec.ichange.service.monitor.uplink.databean.SysqueryserviceDao;
import com.inetec.ichange.service.monitor.uplink.databean.SysqueryserviceDataBean;
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
 * 查询已上报内容的对象标识(Sysqueryservice)
 * 用于下级监控系统向移动警务监管中心查询已上报的对象标识，比如接入应用
 * 终端，外部链路，安全策略等对象的标识，该服务的返回值是一个整型数组。
 */
public class SysqueryserviceProcess {
    private static Logger logger = Logger.getLogger(SysqueryserviceProcess.class);
    private String objectName;

    public String getObjectName() {
        return objectName;
    }

    public void setObjectName(String objectName) {
        this.objectName = objectName;
    }

    public SysqueryserviceProcess(String objectName) {
        this.objectName = objectName;
    }

    /**
     * report process
     */
    public void process(IReceiveData service) {
        logger.info("uplink-- Sysqueryservice up report begin! ");
        TPlatinfDao dao = new TPlatinfDao();
        Pagination<TPlatinfDataBean> platinflist = dao.listPlatinf(1, 1);
        if (platinflist == null || platinflist.getTotalCount() != 1) {
            logger.warn("uplinksystem-platform info not  init ！not uplink!");
            return;
        }
        TPlatinfDataBean tplatdatabean = platinflist.getItems().get(0);

        //List<Sysqueryservice> queryserviceinfs = infoProcess(tplatdatabean);
        /* for(Sysqueryservice queryserviceinf : queryserviceinfs){
      if(objectName!=null){
          if(objectName.equals(queryserviceinf.getObjectname())){
              long[] reuslt = service.querySysqueryservice(queryserviceinf);
              if (reuslt.length>1) {
                  String ids = setString(reuslt);
                  SysqueryserviceDataBean sdb = new SysqueryserviceDataBean();
                  sdb.setIds(ids);
                  sdb.setIdsystem(queryserviceinf.getIdsystem());
                  sdb.setObjectname(queryserviceinf.getObjectname());
                  new SysqueryserviceDao().insert(sdb);
                  logger.info("uplink-- Sysqueryservice insert object into database 'sysqueryservice' okay! ");
              } else {
                  logger.info("uplink-- Sysqueryservice  insert object into database 'sysqueryservice' faild! " + reuslt);
              }
          }
      } else {*/
        Sysqueryservice queryserviceinf = new Sysqueryservice();
        queryserviceinf.setIdsystem(tplatdatabean.getPlatform_id());
        queryserviceinf.setObjectname(objectName);
        long[] reuslt = service.querySysqueryservice(queryserviceinf);
        if (reuslt.length > 0) {
            String ids = setString(reuslt);
            SysqueryserviceDataBean sdb = new SysqueryserviceDataBean();
            sdb.setIds(ids);
            sdb.setIdsystem(queryserviceinf.getIdsystem());
            sdb.setObjectname(queryserviceinf.getObjectname());
            new SysqueryserviceDao().insert(sdb);
            logger.info("uplink-- Sysqueryservice insert object into database 'sysqueryservice' okay! ");
        } else {
            String results = new String();
            for (int i = 0; i < reuslt.length; i++) {
                results = results + "," + reuslt[i];
            }
            logger.info("uplink-- Sysqueryservice  webservice query report entry  faild! " + results);
        }
        //  }
        // }
        logger.info("uplink-- Sysqueryservice up report end. ");

    }

    private String setString(long[] reuslt) {
        String ids = "";
        for (int i = 0; i < reuslt.length - 1; i++) {
            ids += reuslt[i] + ",";
        }
        ids += reuslt[reuslt.length - 1];
        return ids;
    }

    private List<Sysqueryservice> infoProcess(TPlatinfDataBean databean) {
        List<Sysqueryservice> Sysqueryservices = new ArrayList<Sysqueryservice>();
        SysqueryserviceDao dao = new SysqueryserviceDao();
        List<SysqueryserviceDataBean> sdbs = dao.list();
        for (SysqueryserviceDataBean sdb : sdbs) {
            Sysqueryservice sysqueryservice = new Sysqueryservice();
            sysqueryservice.setIdsystem(sdb.getIdsystem());
            sysqueryservice.setObjectname(sdb.getObjectname());
            Sysqueryservices.add(sysqueryservice);
        }
        return Sysqueryservices;
    }
}
