package com.inetec.ichange.service.monitor.uplink.ftp;

import com.inetec.ichange.service.monitor.uplink.databean.TPlatinfDao;
import com.inetec.ichange.service.monitor.uplink.databean.TPlatinfDataBean;
import com.inetec.ichange.service.monitor.uplink.databean.TSbpzDao;
import com.inetec.ichange.service.monitor.uplink.databean.TSbpzDataBean;
import com.inetec.ichange.service.monitor.uplink.infobean.TPlatrunstateBean;
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
public class TPlatrunstateProcess {
    private static Logger logger = Logger.getLogger(TPlatrunstateProcess.class);

    public void process(FtpClient ftpclient) {
        TSbpzDao sbpzDao = new TSbpzDao();
        Pagination<TSbpzDataBean> sbpzlist = sbpzDao.listPlatinf(1, 1);
        if (sbpzlist.getTotalCount() != 1) {
            logger.warn("����ϵͳ-����ƽ̨��Ϣû�г�ʼ�����޷������ϱ�");
            return;
        }
        TPlatinfDao dao = new TPlatinfDao();
        Pagination<TPlatinfDataBean> platinflist = dao.listPlatinf(1, 1);
        if (platinflist.getTotalCount() != 1) {
            logger.warn("����ϵͳ-�߽����ƽ̨��Ϣû�г�ʼ�����޷������ϱ�");
            return;
        }
        TPlatinfDataBean tplatdatabean = platinflist.getItems().get(0);

        TPlatrunstateBean tplatbean = new TPlatrunstateBean();
        tplatbean.dataProcess(tplatdatabean);
        String filename = tplatbean.processFile("A");

        ftpclient.upfile(
                UpFileUtils.Str_FtpDir, filename);

        ftpclient.upfile(
                UpFileUtils.Str_FtpDir, filename + ".finished");
        logger.info("�����ϱ�--TPlatrunstate ���ϱ��ɹ�! ");

    }
}