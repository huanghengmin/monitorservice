package com.inetec.ichange.service.monitor.uplink.ftp;

import com.inetec.ichange.service.monitor.uplink.databean.TPlatinfDao;
import com.inetec.ichange.service.monitor.uplink.databean.TPlatinfDataBean;
import com.inetec.ichange.service.monitor.uplink.databean.TSbpzDao;
import com.inetec.ichange.service.monitor.uplink.databean.TSbpzDataBean;
import com.inetec.ichange.service.monitor.uplink.infobean.TPlatreginfBean;
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
public class TPlatreginfProcess {
    private static Logger logger = Logger.getLogger(TPlatreginfProcess.class);

    /**
     * 上传处理
     */
    public void process(FtpClient ftpclient) {
        /* TSbpzDao sbpzDao = new TSbpzDao();
        Pagination<TSbpzDataBean> sbpzlist = sbpzDao.listPlatinf(1, 1);
        if (sbpzlist.getTotalCount() != 1) {
            logger.warn("集控系统-级联平台信息没有初始化！无法级联上报");
            return;
        }*/
        TPlatinfDao dao = new TPlatinfDao();
        Pagination<TPlatinfDataBean> platinflist = dao.listPlatinf(1, 1);
        if (platinflist.getTotalCount() != 1) {
            logger.warn("集控系统-边界接入平台信息没有初始化！无法级联上报");
            return;
        }
        TPlatinfDataBean tplatdatabean = platinflist.getItems().get(0);
        //TSbpzDataBean tspz = sbpzlist.getItems().get(0);
        TPlatreginfBean tplatbean = new TPlatreginfBean();
        tplatbean.dataProcess(tplatdatabean);
        String filename = tplatbean.processFile("A");

        ftpclient.upfile(
                UpFileUtils.Str_FtpDir, filename);

        ftpclient.upfile(
                UpFileUtils.Str_FtpDir, filename + ".finished");
        logger.info("级联上报--TPlatreginf 项上报成功! ");
    }
}
