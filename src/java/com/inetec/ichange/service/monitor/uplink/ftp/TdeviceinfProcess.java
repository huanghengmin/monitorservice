package com.inetec.ichange.service.monitor.uplink.ftp;

import com.inetec.ichange.service.monitor.databean.Equipment;
import com.inetec.ichange.service.monitor.databean.EquipmentDao;
import com.inetec.ichange.service.monitor.uplink.databean.*;
import com.inetec.ichange.service.monitor.uplink.infobean.TDeviceinfBean;
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
public class TdeviceinfProcess {
    private static Logger logger = Logger.getLogger(TdeviceinfProcess.class);


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
        EquipmentDao deviceInfoDao = new EquipmentDao();
        Pagination<Equipment> deviceInfoDataBeanPagination = deviceInfoDao.listDeviceByLinkName(20, 1, "企事业单位对象");
        if (deviceInfoDataBeanPagination.getTotalCount() <= 0) {
            logger.warn("集控系统-边界接入平台设备信息没有初始化！无法级联上报");
            return;
        }
        TPlatinfDataBean tplatdatabean = platinflist.getItems().get(0);

        TDeviceinfBean tplatbean = new TDeviceinfBean();

        tplatbean.dataProcess(deviceInfoDataBeanPagination.getItems(), tplatdatabean.getPlatform_id());
        String filename = tplatbean.processFile("U");

        ftpclient.upfile(
                UpFileUtils.Str_FtpDir, filename);

        ftpclient.upfile(
                UpFileUtils.Str_FtpDir, filename + ".finished");

        logger.info("级联上报--Tdeviceinf 项上报成功! ");

    }
}
