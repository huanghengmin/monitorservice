package com.inetec.ichange.service.monitor.databean;

import com.avdy.p4j.jdbc.service.GenericDAO;
import com.avdy.p4j.jdbc.service.GenericDaoImpl;
import com.avdy.p4j.jdbc.transfer.TransferUtil;
import com.inetec.ichange.service.monitor.utils.DaoService;
import com.inetec.ichange.service.monitor.utils.Pagination;
import org.apache.log4j.Logger;

public class ExchangePlatformDao {
    private static Logger logger = Logger.getLogger(ExchangePlatformDao.class);

    public ExchangePlatformDao() {

    }

    public Pagination<ExchangePlatform> listExchangePlatform(int limit, int start) {
        Pagination<ExchangePlatform> result = null;
        try {
            TransferUtil.registerClass(ExchangePlatform.class);

            GenericDAO<ExchangePlatform> genericDAO = new GenericDaoImpl<ExchangePlatform>(
                    DaoService.getDaoService().getDataProvider()
                            .getDataFetcher());
            int countrows = genericDAO.countRows(ExchangePlatform.class, "1=1", "1=1");
            result = new Pagination(genericDAO.findAll(ExchangePlatform.class, start, limit), countrows);

        } catch (Exception e) {
            logger.error(e);
        }
        return result;

    }

    public void saveExchangePlatform(ExchangePlatform bean) {
        try {
            TransferUtil.registerClass(ExchangePlatform.class);

            GenericDAO<ExchangePlatform> genericDAO = new GenericDaoImpl<ExchangePlatform>(
                    DaoService.getDaoService().getDataProvider()
                            .getDataFetcher());

            if (genericDAO.isEntityExists(bean)) {

                genericDAO.saveOrUpdate(bean);
            } else {
                genericDAO.createEntity(bean);
            }
        } catch (Exception e) {
            logger.error(e);
        }

    }
}
