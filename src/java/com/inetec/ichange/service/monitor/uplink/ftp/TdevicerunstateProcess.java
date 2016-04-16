package com.inetec.ichange.service.monitor.uplink.ftp;

import com.inetec.ichange.service.monitor.databean.Equipment;
import com.inetec.ichange.service.monitor.databean.EquipmentDao;
import com.inetec.ichange.service.monitor.uplink.databean.TPlatinfDao;
import com.inetec.ichange.service.monitor.uplink.databean.TPlatinfDataBean;
import com.inetec.ichange.service.monitor.uplink.databean.TSbpzDao;
import com.inetec.ichange.service.monitor.uplink.databean.TSbpzDataBean;
import com.inetec.ichange.service.monitor.uplink.infobean.TDevicerunstateBean;
import com.inetec.ichange.service.monitor.uplink.utils.UpFileUtils;
import com.inetec.ichange.service.monitor.utils.Pagination;
import org.apache.log4j.Logger;

/**
 * Created by IntelliJ IDEA.
 * User: bluesky
 * Date: 11-4-29
 * Time: ����9:22
 * To change this template use File | Settings | File Templates.
 */
public class TdevicerunstateProcess {
    private static Logger logger = Logger.getLogger(TdevicerunstateProcess.class);

    /**
     * �ϴ�����
     */
    public void process(FtpClient ftpclient) {

        TPlatinfDao dao = new TPlatinfDao();
        Pagination<TPlatinfDataBean> platinflist = dao.listPlatinf(1, 1);
        if (platinflist.getTotalCount() != 1) {
            logger.warn("����ϵͳ-�߽����ƽ̨��Ϣû�г�ʼ�����޷������ϱ�");
            return;
        }
        EquipmentDao deviceInfoDao = new EquipmentDao();
        Pagination<Equipment> deviceInfoDataBeanPagination = deviceInfoDao.listDevice(20, 1);
        if (deviceInfoDataBeanPagination.getTotalCount() <= 0) {
            logger.warn("����ϵͳ-�߽����ƽ̨�豸��Ϣû�г�ʼ�����޷������ϱ�");
            return;
        }
        TPlatinfDataBean tplatdatabean = platinflist.getItems().get(0);

        TDevicerunstateBean tplatbean = new TDevicerunstateBean();

        tplatbean.dataProcess(deviceInfoDataBeanPagination.getItems(), tplatdatabean.getPlatform_id());
        String filename = tplatbean.processFile("A");

        ftpclient.upfile(
                UpFileUtils.Str_FtpDir, filename);

        ftpclient.upfile(
                UpFileUtils.Str_FtpDir, filename + ".finished");
        tplatbean.dataProcess(deviceInfoDataBeanPagination.getItems(), tplatdatabean.getPlatform_id());
/*
        String filename2 = tplatbean.processFile("U");
        ftpclient.upfile(tspz.getIp(), port, tspz.getUsername(), tspz.getPassword(),
                UpFileUtils.Str_FtpDir, filename2);
        ftpclient.upfile(tspz.getIp(), port, tspz.getUsername(), tspz.getPassword(),
                UpFileUtils.Str_FtpDir, filename2 + ".finished");*/

        logger.info("�����ϱ�--Tdevicerunstate ���ϱ��ɹ�! ");
    }
}