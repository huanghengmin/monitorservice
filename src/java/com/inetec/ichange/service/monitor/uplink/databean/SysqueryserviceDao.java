package com.inetec.ichange.service.monitor.uplink.databean;

import com.avdy.p4j.jdbc.service.GenericDAO;
import com.avdy.p4j.jdbc.service.GenericDaoImpl;
import com.avdy.p4j.jdbc.transfer.TransferUtil;
import com.inetec.ichange.service.monitor.utils.DaoService;
import com.inetec.ichange.service.monitor.utils.Pagination;
import org.apache.log4j.Logger;

import java.util.List;

public class SysqueryserviceDao {
    private static Logger logger = Logger.getLogger(SysqueryserviceDao.class);

    public SysqueryserviceDao() {

    }

    public Pagination<SysqueryserviceDataBean> listPlatinf(int limit, int start) {
        Pagination<SysqueryserviceDataBean> result = null;
        try {
            TransferUtil.registerClass(SysqueryserviceDataBean.class);

            GenericDAO<SysqueryserviceDataBean> genericDAO = new GenericDaoImpl<SysqueryserviceDataBean>(
                    DaoService.getDaoService().getDataProvider()
                            .getDataFetcher());
            int countrows = genericDAO.countRows(SysqueryserviceDataBean.class, "1=1", "1=1");
            result = new Pagination(genericDAO.findAll(SysqueryserviceDataBean.class, start, limit), countrows);

        } catch (Exception e) {
            logger.error(e);
        }
        return result;

    }

    public List<SysqueryserviceDataBean> list() {
        List<SysqueryserviceDataBean> result = null;
        try {
            TransferUtil.registerClass(SysqueryserviceDataBean.class);

            GenericDAO<SysqueryserviceDataBean> genericDAO = new GenericDaoImpl<SysqueryserviceDataBean>(
                    DaoService.getDaoService().getDataProvider()
                            .getDataFetcher());
            int countrows = genericDAO.countRows(SysqueryserviceDataBean.class, "1=1", "1=1");
            result = genericDAO.findAll(SysqueryserviceDataBean.class, 1, countrows);

        } catch (Exception e) {
            logger.error(e);
        }
        return result;
    }

    public void insert(SysqueryserviceDataBean sdb)  {
        try {
            TransferUtil.registerClass(SysqueryserviceDataBean.class);
            GenericDAO<SysqueryserviceDataBean> genericDAO = new GenericDaoImpl<SysqueryserviceDataBean>(
                    DaoService.getDaoService().getDataProvider()
                            .getDataFetcher());
            genericDAO.createEntity(sdb);
        } catch (Exception e) {
            logger.error("查询入库", e);
        }

    }
}
