package com.inetec.ichange.service.monitor.uplink.databean;

import com.avdy.p4j.jdbc.service.GenericDAO;
import com.avdy.p4j.jdbc.service.GenericDaoImpl;
import com.avdy.p4j.jdbc.transfer.TransferUtil;
import com.inetec.ichange.service.monitor.utils.DaoService;
import com.inetec.ichange.service.monitor.utils.Pagination;
import org.apache.log4j.Logger;

import java.util.List;

public class SyscontrolrulesinfDao {
    private static Logger logger = Logger.getLogger(SyscontrolrulesinfDao.class);

    public SyscontrolrulesinfDao() {

    }

    public Pagination<SyscontrolrulesinfDataBean> listPlatinf(int limit, int start) {
        Pagination<SyscontrolrulesinfDataBean> result = null;
        try {
            TransferUtil.registerClass(SyscontrolrulesinfDataBean.class);

            GenericDAO<SyscontrolrulesinfDataBean> genericDAO = new GenericDaoImpl<SyscontrolrulesinfDataBean>(
                    DaoService.getDaoService().getDataProvider().getDataFetcher());
            int countrows = genericDAO.countRows(SyscontrolrulesinfDataBean.class, "1=1", "1=1");
            result = new Pagination(genericDAO.findAll(SyscontrolrulesinfDataBean.class, start, limit), countrows);

        } catch (Exception e) {
            logger.error(e);
        }
        return result;

    }
    public List<SyscontrolrulesinfDataBean> list() {
        List<SyscontrolrulesinfDataBean> result = null;
        try {
            TransferUtil.registerClass(SyscontrolrulesinfDataBean.class);

            GenericDAO<SyscontrolrulesinfDataBean> genericDAO = new GenericDaoImpl<SyscontrolrulesinfDataBean>(
                    DaoService.getDaoService().getDataProvider().getDataFetcher());
            int countrows = genericDAO.countRows(SyscontrolrulesinfDataBean.class, "1=1", "1=1");
            result = genericDAO.findAll(SyscontrolrulesinfDataBean.class, 1, countrows);

        } catch (Exception e) {
            logger.error(e);
        }
        return result;

    }
}
