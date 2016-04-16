package com.inetec.ichange.service.monitor.uplink.webservice;

import com.crgs.entities.Sysreginf;
import com.crgs.webservices.IReceiveData;
import com.inetec.ichange.service.monitor.uplink.databean.TPlatinfDao;
import com.inetec.ichange.service.monitor.uplink.databean.TPlatinfDataBean;
import com.inetec.ichange.service.monitor.uplink.utils.UpFileUtils;
import com.inetec.ichange.service.monitor.utils.Pagination;
import org.apache.log4j.Logger;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;

/**
 * Created by IntelliJ IDEA.
 * User: bluesky
 * Date: 11-3-16
 * Time: 上午10:12
 * To change this template use File | Settings | File Templates.
 * 移动警务系统注册信息(Sysreginf)
 * 初始上报一次，以后当信息有变更时再进行上报
 */
public class SysreginfProcess {
    private static Logger logger = Logger.getLogger(SysreginfProcess.class);

    /**
     * 上传处理
     */
    public void process(IReceiveData service) {

        TPlatinfDao dao = new TPlatinfDao();
        Pagination<TPlatinfDataBean> platinflist = dao.listPlatinf(1, 1);
        if (platinflist == null || platinflist.getTotalCount() != 1) {
            logger.warn("uplinksystem-platform info not  init ！not uplink!");
            return;
        }
        TPlatinfDataBean tplatdatabean = platinflist.getItems().get(0);

        Sysreginf reginf = infoProcess(tplatdatabean);
        String reuslt = service.saveSysreginf(reginf);
        if (reuslt.equalsIgnoreCase("1")) {
            logger.info("uplink-- Sysreginf up report okay! ");
        } else {
            logger.info("uplink-- Sysreginf  up report faild! " + reuslt);
        }
        if (reginf.getApproveMaterial() != null) {
            DataHandler handler = new DataHandler(new FileDataSource(UpFileUtils.getDataPath() + reginf.getApproveMaterial()));

            reuslt = service.uploadFile(tplatdatabean.getPlatform_id(), reginf.getApproveMaterial(), handler);
            if (reuslt.equalsIgnoreCase("1")) {
                logger.info("uplink-- Sysreginf ApproveMaterial upload okay! ");
            } else {
                logger.info("uplink-- Sysreginf  ApproveMaterial upload faild! " + reuslt);
            }
        }
        if (reginf.getSecurityProject() != null) {
            DataHandler handler = new DataHandler(new FileDataSource(UpFileUtils.getDataPath() + reginf.getSecurityProject()));

            reuslt = service.uploadFile(tplatdatabean.getPlatform_id(), reginf.getSecurityProject(), handler);
            if (reuslt.equalsIgnoreCase("1")) {
                logger.info("uplink-- Sysreginf SecurityProject upload okay! ");
            } else {
                logger.info("uplink-- Sysreginf SecurityProject upload failed! " + reuslt);
            }
        }

        if (reginf.getSecrecyProtocol() != null) {
            DataHandler handler = new DataHandler(new FileDataSource(UpFileUtils.getDataPath() + reginf.getSecrecyProtocol()));

            reuslt = service.uploadFile(tplatdatabean.getPlatform_id(), reginf.getSecrecyProtocol(), handler);
            if (reuslt.equalsIgnoreCase("1")) {
                logger.info("uplink-- Sysreginf SecrecyProtocol upload okay! ");
            } else {
                logger.info("uplink-- Sysreginf SecrecyProtocol upload failed! " + reuslt);
            }
        }
        if (reginf.getSystemMap() != null) {
            DataHandler handler = new DataHandler(new FileDataSource(UpFileUtils.getDataPath() + reginf.getSystemMap()));

            reuslt = service.uploadFile(tplatdatabean.getPlatform_id(), reginf.getSystemMap(), handler);
            if (reuslt.equalsIgnoreCase("1")) {
                logger.info("uplink-- Sysreginf SystemMap upload okay! ");
            } else {
                logger.info("uplink-- Sysreginf SystemMap upload fialed! " + reuslt);
            }
        }
    }

    private Sysreginf infoProcess(TPlatinfDataBean databean) {
        Sysreginf sysreginf = new Sysreginf();
        sysreginf.setSystemname(databean.getPlatform_name());
        sysreginf.setIdsystem(databean.getPlatform_id());
        sysreginf.setSystemclass(databean.getSystem_class());
        sysreginf.setAddress(databean.getJksys_mac());
        sysreginf.setManager(databean.getFzr_name());
        sysreginf.setManagerPhone(databean.getFzr_phone());
        sysreginf.setManagerMail(databean.getFzr_email());
        sysreginf.setManagerOtherlink(databean.getFzr_other_phone());
        sysreginf.setRemoteaccessIp(databean.getJksys_ip());
        if (databean.getJsdw() != null && databean.getJsdw().length() < 12) {
            String temp = databean.getJsdw();
            for (int i = databean.getJsdw().length(); i < 12; i++) {
                temp = temp + "0";
            }
            databean.setJsdw(temp);
        }
        sysreginf.setConstructUnit(databean.getJsdw());

        sysreginf.setBuildingUnitCode(databean.getZycjdw());
        sysreginf.setBuildingTime(databean.getJs_day());
        if (databean.getBmxy().equalsIgnoreCase("Y"))
            sysreginf.setSignSecrecyprotocol("1");
        else
            sysreginf.setSignSecrecyprotocol("0");
        sysreginf.setApproveUnit(databean.getSpdw());
        sysreginf.setApproveTime(databean.getSp_day());
        sysreginf.setApproveNo(databean.getSpph());
        sysreginf.setApproveMaterial(databean.getTechnology_solution_file_name());//"001.jpg"

        String oldApproveMaterial = sysreginf.getApproveMaterial();

        sysreginf.setApproveMaterial(databean.getPlatform_id() + "-" + UpFileUtils.getDateFormatUp(System.currentTimeMillis()) + "-001-0.zip");
        new UpFileUtils().zipProcess(sysreginf.getApproveMaterial(), oldApproveMaterial);
        sysreginf.setSecurityProject(databean.getSpcl_file_name()); //002.doc
        String oldSecurityProject = sysreginf.getSecurityProject();
        sysreginf.setSecurityProject(databean.getPlatform_id() + "-" + UpFileUtils.getDateFormatUp(System.currentTimeMillis()) + "-002-0.zip");

        new UpFileUtils().zipProcess(sysreginf.getSecurityProject(), oldSecurityProject);
        sysreginf.setSecrecyProtocol(databean.getConfidential_agreement_file_name());//003.rar
        String oldSecrecyProtocol = sysreginf.getSecrecyProtocol();

        sysreginf.setSecrecyProtocol(databean.getPlatform_id() + "-" + UpFileUtils.getDateFormatUp(System.currentTimeMillis()) + "-003-0.zip");
        new UpFileUtils().zipProcess(sysreginf.getSecrecyProtocol(), oldSecrecyProtocol);

        sysreginf.setMaintainUnit(databean.getMain_comp());
        sysreginf.setMaintainManager(databean.getMaintainer_name());
        sysreginf.setMaintainManagerPhone(databean.getMaintainer_phone());
        sysreginf.setMaintainManagerMail(databean.getMaintainer_email());
        sysreginf.setMaintainManagerLink(databean.getMaintainer_other_phone());
        sysreginf.setSystemMap(databean.getPlatform_tp_file_name());  //004.jpg
        String oldPlatformMap = sysreginf.getSystemMap();
        sysreginf.setSystemMap(databean.getPlatform_id() + "-" + UpFileUtils.getDateFormatUp(System.currentTimeMillis()) + "-004-0.zip");
        new UpFileUtils().zipProcess(sysreginf.getSystemMap(), oldPlatformMap);

        sysreginf.setCollecttime(UpFileUtils.getCurrentDayDate());
        return sysreginf;
    }
}
