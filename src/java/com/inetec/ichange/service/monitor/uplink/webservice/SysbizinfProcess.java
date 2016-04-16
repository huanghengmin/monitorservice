package com.inetec.ichange.service.monitor.uplink.webservice;

import com.crgs.entities.Sysbizinf;
import com.crgs.webservices.IReceiveData;
import com.inetec.ichange.service.monitor.databean.BusinessRegister;
import com.inetec.ichange.service.monitor.databean.BusinessRegisterDao;
import com.inetec.ichange.service.monitor.uplink.databean.TPlatinfDao;
import com.inetec.ichange.service.monitor.uplink.databean.TPlatinfDataBean;
import com.inetec.ichange.service.monitor.uplink.utils.UpFileUtils;
import com.inetec.ichange.service.monitor.utils.Pagination;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.codehaus.xfire.XFireRuntimeException;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: bluesky
 * Date: 11-3-16
 * Time: 上午10:12
 * To change this template use File | Settings | File Templates.
 * 移动警务接入应用注册信息(Sysbizinf)
 * 初始上报一次，以后当信息有变更时再进行上报
 */
public class SysbizinfProcess {
    private static Logger logger = Logger.getLogger(SysbizinfProcess.class);

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
            logger.warn("uplinksystem-Sysbizinf info not  init ！not uplink!");
            return;
        }
        TPlatinfDataBean tplatdatabean = platinflist.getItems().get(0);
        //TSbpzDataBean tspz = sbpzlist.getItems().get(0);


        List<Sysbizinf> bizinfs = infoProcess(tplatdatabean, new BusinessRegister());
        for (Sysbizinf bizinf : bizinfs) {
            String reuslt = "-1";
            try {
                reuslt = service.saveSysbizinf(bizinf);
            } catch (XFireRuntimeException e) {
                logger.info("uplink-- Sysbizinf up report error! ", e);
            }
            if (reuslt.equalsIgnoreCase("1")) {
                logger.info("uplink-- Sysbizinf up report okay! ");
            } else {
                logger.info("uplink-- Sysbizinf  up report faild! " + reuslt);
            }
            if (bizinf.getApproveMaterial() != null) {
                DataHandler handler = new DataHandler(new FileDataSource(UpFileUtils.getDataPath() + bizinf.getApproveMaterial()));

                reuslt = service.uploadFile(tplatdatabean.getPlatform_id(), bizinf.getApproveMaterial(), handler);
                if (reuslt.equalsIgnoreCase("1")) {
                    logger.info("uplink-- Sysbizinf ApproveMaterial upload okay! ");
                } else {
                    logger.info("uplink-- Sysbizinf  ApproveMaterial upload faild! " + reuslt);
                }
            }
            if (bizinf.getTopologyMap() != null) {
                DataHandler handler = new DataHandler(new FileDataSource(UpFileUtils.getDataPath() + bizinf.getTopologyMap()));

                reuslt = service.uploadFile(tplatdatabean.getPlatform_id(), bizinf.getTopologyMap(), handler);
                if (reuslt.equalsIgnoreCase("1")) {
                    logger.info("uplink-- Sysbizinf TopologyMap upload okay! ");
                } else {
                    logger.info("uplink-- Sysbizinf  TopologyMap upload faild! " + reuslt);
                }
            }
        }


    }

    private List<Sysbizinf> infoProcess(TPlatinfDataBean tplatdatabean, BusinessRegister businessRegister) {
        List<Sysbizinf> sysbizinfs = new ArrayList<Sysbizinf>();
        BusinessRegisterDao dao = new BusinessRegisterDao();
        List<BusinessRegister> srdbs = dao.list();
        for (int i = 0; i < srdbs.size(); i++) {

            BusinessRegister sdb = srdbs.get(i);

            Sysbizinf sysbizinf = new Sysbizinf();
            sysbizinf.setIdbiz(sdb.getId());
            sysbizinf.setIdsystem(tplatdatabean.getPlatform_id());
            //代码
            sysbizinf.setBizManagedepart(sdb.getBusiness_depart());
            sysbizinf.setBizType(sdb.getBusiness_code());
            sysbizinf.setBizMode(sdb.getBusiness_exch_model());
            sysbizinf.setBizfunctype("0001&0002&0003&0004");
            sysbizinf.setBizTerminal("001&002&003&004");

            //代码
            sysbizinf.setManagedepartManager(sdb.getBusiness_admin());
            sysbizinf.setManagedepartPhone(sdb.getAdmin_phone());
            sysbizinf.setManagedepartMail(sdb.getAdmin_email());
            sysbizinf.setManagedepartLink(sdb.getAdmin_other_phone());
            //代码
            sysbizinf.setApproveUnit(sdb.getAuth_depart());
            sysbizinf.setApproveTime(sdb.getAuth_date());
            sysbizinf.setApproveNo(sdb.getAuth_serial());
            sysbizinf.setApproveMaterial(sdb.getAuth_material_file_name());
            try {
                File file = new File(UpFileUtils.getDataPath() + sdb.getAuth_material_file_name());
                if (!file.exists()) {
                    file.createNewFile();
                } else {
                    file.delete();
                    file.createNewFile();
                }
                if (sdb.getTp_graph() != null && sdb.getTp_graph().length > 0)
                    FileUtils.writeByteArrayToFile(file, sdb.getTp_graph());
                else
                    FileUtils.writeByteArrayToFile(file, "".getBytes());
            } catch (IOException e) {
                logger.warn("Sysbizinf Process save approveMaterial to file error", e);
            }
            String oldApproveMaterial = sysbizinf.getApproveMaterial();

            sysbizinf.setApproveMaterial(tplatdatabean.getPlatform_id() + "-" + UpFileUtils.getDateFormatUp(System.currentTimeMillis()) + "-101-1.zip");
            new UpFileUtils().zipProcess(sysbizinf.getApproveMaterial(), UpFileUtils.getDataPath() + oldApproveMaterial);
            sysbizinf.setRegisterTime(sdb.getRegister_date());
            sysbizinf.setRealtime(sdb.getIs_realtime());
            sysbizinf.setDateexchangeDateflux(String.valueOf(sdb.getDay_datavolume()) + "MB");
            sysbizinf.setIsBackup(sdb.getIs_approved());
            //代码
//            sysbizinf.setBackupUnitname(tplatdatabean.getZycjdw());
            sysbizinf.setBackupUnitname(sdb.getApproved_depart());
            sysbizinf.setTopologyMap(sdb.getTp_graph_file_name()); //拓扑图
            String oldPlatformMap = sysbizinf.getTopologyMap();
            try {
                File file = new File(UpFileUtils.getDataPath() + sdb.getTp_graph_file_name());
                if (!file.exists()) {
                    file.createNewFile();
                } else {
                    file.delete();
                    file.createNewFile();
                }
                if (sdb.getTp_graph_file_name() != null && sdb.getTp_graph_file_name().length() > 0)
                    FileUtils.writeByteArrayToFile(file, sdb.getTp_graph());
                else
                    FileUtils.writeByteArrayToFile(file, "".getBytes());
            } catch (IOException e) {
                logger.warn("Sysbizinf Process save Tp_graph to file error", e);
            }
            sysbizinf.setTopologyMap(tplatdatabean.getPlatform_id() + "-" + UpFileUtils.getDateFormatUp(System.currentTimeMillis()) + "-102-1.zip");
            new UpFileUtils().zipProcess(sysbizinf.getTopologyMap(), UpFileUtils.getDataPath() + oldPlatformMap);
            sysbizinf.setCollecttime(UpFileUtils.getCurrentDayDate());
            logger.info("Sysbizinf object to Stirng" + sysbizinf.getApproveNo());


            sysbizinfs.add(sysbizinf);
        }

        return sysbizinfs;
    }
}
