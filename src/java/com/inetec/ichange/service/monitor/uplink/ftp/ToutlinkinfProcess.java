package com.inetec.ichange.service.monitor.uplink.ftp;

import com.inetec.ichange.service.monitor.uplink.databean.*;
import com.inetec.ichange.service.monitor.uplink.infobean.TOutlinkinfBean;
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
public class ToutlinkinfProcess {
    private static Logger logger = Logger.getLogger(ToutlinkinfProcess.class);

    /**
     * �ϴ�����
     */
    public void process(FtpClient ftpclient) {
        /*  TSbpzDao sbpzDao = new TSbpzDao();
        Pagination<TSbpzDataBean> sbpzlist = sbpzDao.listPlatinf(1, 1);
        if (sbpzlist.getTotalCount() != 1) {
            logger.warn("����ϵͳ-����ƽ̨��Ϣû�г�ʼ�����޷������ϱ�");
            return;
        }*/
        TPlatinfDao dao = new TPlatinfDao();
        Pagination<TPlatinfDataBean> platinflist = dao.listPlatinf(1, 1);
        if (platinflist.getTotalCount() != 1) {
            logger.warn("����ϵͳ-�߽����ƽ̨��Ϣû�г�ʼ�����޷������ϱ�");
            return;
        }
        TOutlinkinfDao outlinkinfDao = new TOutlinkinfDao();
        Pagination<TOutlinkinfDataBean> toutlinklist = outlinkinfDao.list(10, 1);
        if (toutlinklist.getTotalCount() <= 0) {
            logger.warn("����ϵͳ-�߽����ƽ̨�ⲿ��·��Ϣû�г�ʼ�����޷������ϱ�");
            return;
        }

        TInlinkinfDao inlinkinfDao = new TInlinkinfDao();
        Pagination<TInlinkinfDataBean> tinlinklist = inlinkinfDao.list(10, 1);
        if (tinlinklist.getTotalCount() <= 0) {
            logger.warn("����ϵͳ-�߽����ƽ̨�ڲ���·��Ϣû�г�ʼ�����޷������ϱ�");
            return;
        }
        TPlatinfDataBean tplatdatabean = platinflist.getItems().get(0);

        TOutlinkinfBean tplatbean = new TOutlinkinfBean();

        tplatbean.dataProcess(toutlinklist.getItems(), 2, tplatdatabean.getPlatform_id());
        String filename = tplatbean.processFile("A");

        ftpclient.upfile(
                UpFileUtils.Str_FtpDir, filename);

        ftpclient.upfile(
                UpFileUtils.Str_FtpDir, filename + ".finished");
        tplatbean.dataProcess(toutlinklist.getItems(), 2, tplatdatabean.getPlatform_id());
        String filename2 = tplatbean.processFile("U");
        ftpclient.upfile(
                UpFileUtils.Str_FtpDir, filename2);
        ftpclient.upfile(
                UpFileUtils.Str_FtpDir, filename2 + ".finished");
        logger.info("�����ϱ�--Toutlinkinf ���ϱ��ɹ�! ");

    }
}