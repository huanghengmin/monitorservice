package com.inetec.ichange.service.monitor.uplink.ftp;

import com.inetec.ichange.service.monitor.databean.BusinessRegister;
import com.inetec.ichange.service.monitor.databean.BusinessRegisterDao;
import com.inetec.ichange.service.monitor.uplink.databean.*;
import com.inetec.ichange.service.monitor.uplink.infobean.TBizinfBean;
import com.inetec.ichange.service.monitor.uplink.infobean.TSysdelserviceBean;
import com.inetec.ichange.service.monitor.uplink.utils.UpFileUtils;
import com.inetec.ichange.service.monitor.utils.Pagination;
import org.apache.log4j.Logger;

/**
 * Created by IntelliJ IDEA.
 * User: Ǯ����
 * Date: 12-8-10
 * Time: ����3:39
 * To change this template use File | Settings | File Templates.
 */
public class TSysDelServiceProcess {
    private static Logger logger = Logger.getLogger(TSysDelServiceProcess.class);

    /**
     * �ϴ�����
     */
    public void process(FtpClient ftpclient) {
        /*   TSbpzDao sbpzDao = new TSbpzDao();
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
        SysdelserviceDao sysdelserviceDao = new SysdelserviceDao();
        Pagination<SysdelserviceDataBean> pagination = sysdelserviceDao.listPlatinf(10, 1);
        if (pagination.getTotalCount() <= 0) {
            logger.warn("����ϵͳ-�߽����ƽ̨Ӧ����Ϣû�г�ʼ�����޷������ϱ�");
            return;
        }
        TPlatinfDataBean tplatdatabean = platinflist.getItems().get(0);

//        TBizinfBean tplatbean = new TBizinfBean();
        TSysdelserviceBean tSysdelserviceBean = new TSysdelserviceBean();
//        tplatbean.dataProcess(pagination.getItems(), tplatdatabean.getPlatform_id(), tspz);
//        String filename = tplatbean.processFile("A");


        ftpclient.upfile(
                UpFileUtils.Str_FtpDir, "");
//        ftpclient.upfile(tspz.getIp(), port, tspz.getUsername(), tspz.getPassword(),
//                UpFileUtils.Str_FtpDir, filename);

//        ftpclient.upfile(tspz.getIp(), port, tspz.getUsername(), tspz.getPassword(),
//                UpFileUtils.Str_FtpDir, filename + ".finished");
//       tplatbean.dataProcess(pagination.getItems(), tplatdatabean.getPlatform_id(), tspz);
//        String filename2 = tplatbean.processFile("U");
//        ftpclient.upfile(tspz.getIp(), port, tspz.getUsername(), tspz.getPassword(),
//                UpFileUtils.Str_FtpDir, filename2);
//        ftpclient.upfile(tspz.getIp(), port, tspz.getUsername(), tspz.getPassword(),
//                UpFileUtils.Str_FtpDir, filename2 + ".finished");

        logger.info("�����ϱ�--TSysdelserviceBean-- ���ϱ��ɹ�! ");
        //new TSysDelServiceProcess().process();
        /* //�����ļ��ϴ�
        if (tplatbean.getPlatformMap() != null && !tplatbean.getPlatformMap().equalsIgnoreCase("")) {
            ftpclient.upfile(tspz.getIp(), port, tspz.getUsername(), tspz.getPassword(),
                    UpFileUtils.Str_FtpDir, tplatbean.getPlatformMap());
            ftpclient.upfile(tspz.getIp(), port, tspz.getUsername(), tspz.getPassword(),
                    UpFileUtils.Str_FtpDir, tplatbean.getPlatformMap() + ".finished");
        }*/
    }
}
