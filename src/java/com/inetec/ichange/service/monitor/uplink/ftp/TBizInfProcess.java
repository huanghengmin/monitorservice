package com.inetec.ichange.service.monitor.uplink.ftp;

import com.inetec.ichange.service.monitor.databean.BusinessRegister;
import com.inetec.ichange.service.monitor.databean.BusinessRegisterDao;
import com.inetec.ichange.service.monitor.uplink.databean.TPlatinfDao;
import com.inetec.ichange.service.monitor.uplink.databean.TPlatinfDataBean;
import com.inetec.ichange.service.monitor.uplink.infobean.TBizinfBean;
import com.inetec.ichange.service.monitor.uplink.utils.UpFileUtils;
import com.inetec.ichange.service.monitor.utils.Pagination;
import org.apache.log4j.Logger;

/**
 * Created by IntelliJ IDEA.
 * User: bluesky
 * Date: 11-4-29
 * Time: 上午9:22
 * To change this template use File | Settings | File Templates.
 */
public class TBizInfProcess {
    private static Logger logger = Logger.getLogger(TBizInfProcess.class);

    /**
     * 上传处理
     */
    public void process(FtpClient ftpclient) {

        TPlatinfDao dao = new TPlatinfDao();
        Pagination<TPlatinfDataBean> platinflist = dao.listPlatinf(1, 1);
        if (platinflist.getTotalCount() != 1) {
            logger.warn("集控系统-边界接入平台信息没有初始化！无法级联上报");
            return;
        }
        BusinessRegisterDao businessRegisterDao = new BusinessRegisterDao();
        Pagination<BusinessRegister> businessRegisterPagination = businessRegisterDao.listBusinessRegister(10, 1);
        if (businessRegisterPagination.getTotalCount() <= 0) {
            logger.warn("集控系统-边界接入平台应用信息没有初始化！无法级联上报");
            return;
        }
        TPlatinfDataBean tplatdatabean = platinflist.getItems().get(0);

        TBizinfBean tplatbean = new TBizinfBean();
        tplatbean.dataProcess(businessRegisterPagination.getItems(), tplatdatabean.getPlatform_id());
        String filename = tplatbean.processFile("A", ftpclient);


        ftpclient.upfile(
                UpFileUtils.Str_FtpDir, filename);

        ftpclient.upfile(
                UpFileUtils.Str_FtpDir, filename + ".finished");
        tplatbean.dataProcess(businessRegisterPagination.getItems(), tplatdatabean.getPlatform_id());
        String filename2 = tplatbean.processFile("U",ftpclient);
        ftpclient.upfile(
                UpFileUtils.Str_FtpDir, filename2);
        ftpclient.upfile(
                UpFileUtils.Str_FtpDir, filename2 + ".finished");

        logger.info("级联上报--TBizInf 项上报成功!");
        new TbizproinfProcess().process( ftpclient);
        /* //附件文件上传
        if (tplatbean.getPlatformMap() != null && !tplatbean.getPlatformMap().equalsIgnoreCase("")) {
            ftpclient.upfile(tspz.getIp(), port, tspz.getUsername(), tspz.getPassword(),
                    UpFileUtils.Str_FtpDir, tplatbean.getPlatformMap());
            ftpclient.upfile(tspz.getIp(), port, tspz.getUsername(), tspz.getPassword(),
                    UpFileUtils.Str_FtpDir, tplatbean.getPlatformMap() + ".finished");
        }*/
    }
}
