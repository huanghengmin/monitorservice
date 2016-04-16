package com.inetec.ichange.service.monitor.uplink.databean;

import com.avdy.p4j.jdbc.service.GenericDAO;
import com.avdy.p4j.jdbc.service.GenericDaoImpl;
import com.avdy.p4j.jdbc.transfer.TransferUtil;
import com.inetec.ichange.service.monitor.utils.DaoService;
import com.inetec.ichange.service.monitor.utils.Pagination;
import org.apache.log4j.Logger;

import java.util.List;

public class SysdelserviceDao {
    private static Logger logger = Logger.getLogger(SysdelserviceDao.class);

    public SysdelserviceDao() {

    }

    public Pagination<SysdelserviceDataBean> listPlatinf(int limit, int start) {
        Pagination<SysdelserviceDataBean> result = null;
        try {
            TransferUtil.registerClass(SysdelserviceDataBean.class);

            GenericDAO<SysdelserviceDataBean> genericDAO = new GenericDaoImpl<SysdelserviceDataBean>(
                    DaoService.getDaoService().getDataProvider().getDataFetcher());
            int countrows = genericDAO.countRows(SysdelserviceDataBean.class, "1=1", "1=1");
            result = new Pagination(genericDAO.findAll(SysdelserviceDataBean.class, start, limit), countrows);

        } catch (Exception e) {
            logger.error(e);
        }
        return result;

    }

    public List<SysdelserviceDataBean> list() {
        List<SysdelserviceDataBean> result = null;
        try {
            TransferUtil.registerClass(SysdelserviceDataBean.class);

            GenericDAO<SysdelserviceDataBean> genericDAO = new GenericDaoImpl<SysdelserviceDataBean>(
                    DaoService.getDaoService().getDataProvider().getDataFetcher());
            int countrows = genericDAO.countRows(SysdelserviceDataBean.class, "1=1", "1=1");
            result = genericDAO.findAll(SysdelserviceDataBean.class, 1, countrows);

        } catch (Exception e) {
            logger.error(e);
        }
        return result;
    }
}
