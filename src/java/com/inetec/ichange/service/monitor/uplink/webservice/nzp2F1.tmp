package com.inetec.ichange.service.monitor.uplink.webservice;

import com.crgs.entities.Sysdeviceinf;
import com.crgs.webservices.IReceiveData;
import com.inetec.ichange.service.monitor.databean.Equipment;
import com.inetec.ichange.service.monitor.databean.EquipmentDao;
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
 * 移动警务系统核心设备注册信息(Sysdeviceinf)
 * 初始上报一次，以后当信息有变更时再进行上报
 */
public class SysdeviceinfProcess {
    private static Logger logger = Logger.getLogger(SysdeviceinfProcess.class);

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
        if (platinflist == null || platinflist.getTotalCount() != 1) {
            logger.warn("uplinksystem-platform info not  init ！not uplink!");
            return;
        }
        TPlatinfDataBean tplatdatabean = platinflist.getItems().get(0);
        //TSbpzDataBean tspz = sbpzlist.getItems().get(0);


        List<Sysdeviceinf> sysdeviceinfs = infoProcess(tplatdatabean);
        for (Sysdeviceinf sysdeviceinf : sysdeviceinfs) {
            String reuslt = "-1";
            try {
                logger.info("brandtyoe:" + sysdeviceinf.getBrandtyoe());
                logger.info("devicedesc:" + sysdeviceinf.getDevicedesc());
                logger.info("deviceip:" + sysdeviceinf.getDeviceIp());
                logger.info("devicetypecode:" + sysdeviceinf.getDevicetypecode());
                logger.info("idsystem:" + sysdeviceinf.getIdsystem());
                logger.info("iddevice:" + sysdeviceinf.getIddevice());
                logger.info("Collectime:" + sysdeviceinf.getCollecttime());

                reuslt = service.saveSysdeviceinf(sysdeviceinf);
            } catch (XFireRuntimeException e) {
                logger.info("uplink-- Sysdeviceinf up report error!: " + reuslt, e);
            }
            if (reuslt.equalsIgnoreCase("1")) {
                logger.info("uplink-- Sysdeviceinf up report okay! ");
            } else {
                logger.info("uplink-- Sysdeviceinf  up report faild! " + reuslt);
            }
        }
    }

    private List<Sysdeviceinf> infoProcess(TPlatinfDataBean databean) {
        List<Sysdeviceinf> sysdeviceinfs = new ArrayList<Sysdeviceinf>();
        EquipmentDao dao = new EquipmentDao();
        List<Equipment> edbs = dao.list();
        for (int i = 0; i < edbs.size(); i++) {
            Equipment edb = edbs.get(i);
            if (edb.getKeyDevice().equalsIgnoreCase("1")) {    //0 false,1 true 是核心设备注册信息
                Sysdeviceinf sysdeviceinf = new Sysdeviceinf();
                sysdeviceinf.setIdsystem(databean.getPlatform_id());
                sysdeviceinf.setIddevice(edb.getId());
                if (edb.getModel() == null||edb.getModel().equalsIgnoreCase(""))
                    sysdeviceinf.setDevicedesc("接入设备");//"中网防火墙"
                else
                    sysdeviceinf.setDevicedesc(edb.getModel());//"中网防火墙"
                if (edb.getEqu_type() == null)
                    sysdeviceinf.setDevicetypecode("0000");
                else
                    sysdeviceinf.setDevicetypecode(edb.getEqu_type());
                sysdeviceinf.setDeviceIp(edb.getIp());
                if (edb.getManufacturer() != null)
                    sysdeviceinf.setBrandtyoe(edb.getManufacturer());
                else
                    sysdeviceinf.setBrandtyoe("yszd");

                sysdeviceinf.setCollecttime(UpFileUtils.getCurrentDayDate());
                if (sysdeviceinf.getDevicedesc() == null) {
                    sysdeviceinf.setDevicedesc("接入设备");
                }
                sysdeviceinfs.add(sysdeviceinf);
            }
        }
        return sysdeviceinfs;
    }
}
