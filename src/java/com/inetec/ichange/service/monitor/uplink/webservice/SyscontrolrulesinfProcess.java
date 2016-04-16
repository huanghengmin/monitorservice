package com.inetec.ichange.service.monitor.uplink.webservice;

import com.crgs.entities.Syscontrolrulesinf;
import com.crgs.webservices.IReceiveData;
import com.inetec.ichange.service.monitor.uplink.databean.SyscontrolrulesinfDao;
import com.inetec.ichange.service.monitor.uplink.databean.SyscontrolrulesinfDataBean;
import com.inetec.ichange.service.monitor.uplink.databean.TPlatinfDao;
import com.inetec.ichange.service.monitor.uplink.databean.TPlatinfDataBean;
import com.inetec.ichange.service.monitor.uplink.utils.UpFileUtils;
import com.inetec.ichange.service.monitor.utils.Pagination;
import org.apache.log4j.Logger;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: qxp
 * Date: 12-3-15
 * Time: am 10:50
 * 移动警务系统控制策略注册信息(Syscontrolrulesinf[根据移动警务测评规范，新增Syscontrlrulesinf接口，用来上传控制策略文件及其注册信息])
 * 该接口用于上报本级的系统控制策略文件（文件可以通过附件上传服务Filetransports上报，
 * 控制策略的注册信息通过Syscontrolrulesinf上报），文件初始上报一次，以后当信息有变
 * 更时再进行上报，
 */
public class SyscontrolrulesinfProcess {
    private static Logger logger = Logger.getLogger(SyscontrolrulesinfProcess.class);

    /**
     * report process
     */
    public void process(IReceiveData service) {
        logger.info("uplink-- Syscontrolrulesinf up report begin! ");
        TPlatinfDao dao = new TPlatinfDao();
        Pagination<TPlatinfDataBean> platinflist = dao.listPlatinf(1, 1);
        if (platinflist == null || platinflist.getTotalCount() != 1) {
            logger.warn("uplinksystem-platform info not  init ！not uplink!");
            return;
        }
        TPlatinfDataBean tplatdatabean = platinflist.getItems().get(0);

        List<Syscontrolrulesinf> controlrulesinfs = infoProcess(tplatdatabean);
        for (Syscontrolrulesinf controlrulesinf : controlrulesinfs) {
            String reuslt=null;

            if (controlrulesinf.getControlrulesFilename() != null) {
                DataHandler handler = new DataHandler(new FileDataSource(UpFileUtils.getDataPath() + controlrulesinf.getControlrulesFilename()));
                try{
                    reuslt = service.uploadFile(tplatdatabean.getPlatform_id(), controlrulesinf.getControlrulesFilename(), handler);
                } catch(Exception e){
                    logger.error("上报控制策略文件不存在");
                }
                if (reuslt.equalsIgnoreCase("1")) {
                    logger.info("uplink-- Syscontrolrulesinf FileName upload okay! ");
                } else {
                    logger.info("uplink-- Syscontrolrulesinf  FileName upload faild! " + reuslt);
                }
                logger.info("uplink-- Syscontrolrulesinf id:" + controlrulesinf.getIdsystem() + " cid:" + controlrulesinf.getIdcontrolrules() + " desc:" + controlrulesinf.getControlrulesDesc());
                try{
                    reuslt = service.saveSyscontrolrulesinf(controlrulesinf);
                }   catch (Exception e){
                         logger.error("result error:"+e);
                }
                if (reuslt.equalsIgnoreCase("1")) {
                    logger.info("uplink-- Syscontrolrulesinf up report okay! ");
                } else {
                    logger.info("uplink-- Syscontrolrulesinf  up report faild! " + reuslt);
                }
            }


        }
        logger.info("uplink-- Syscontrolrulesinf up report end. ");

    }

    private List<Syscontrolrulesinf> infoProcess(TPlatinfDataBean databean) {
        List<Syscontrolrulesinf> syscontrolrulesinfs = new ArrayList<Syscontrolrulesinf>();
        SyscontrolrulesinfDao dao = new SyscontrolrulesinfDao();
        List<SyscontrolrulesinfDataBean> sdbs = dao.list();
        for (int i = 0; i < sdbs.size(); i++) {
            SyscontrolrulesinfDataBean sdb = sdbs.get(i);
            Syscontrolrulesinf syscontrolrulesinf = new Syscontrolrulesinf();
            syscontrolrulesinf.setIdsystem(databean.getPlatform_id());
            syscontrolrulesinf.setIdcontrolrules(sdb.getId());
            logger.info("Syscontrolrulesinf id:" + sdb.getId() + " date:" + sdb.getCollectTime().toString());
            syscontrolrulesinf.setCollecttime(sdb.getCollectTime());
            syscontrolrulesinf.setControlrulesFilename(sdb.getFileName());
            if (sdb.getDesc() == null)
                syscontrolrulesinf.setControlrulesDesc(" ");
            else
                syscontrolrulesinf.setControlrulesDesc(sdb.getDesc());
            syscontrolrulesinfs.add(syscontrolrulesinf);
        }
        return syscontrolrulesinfs;
    }
}
