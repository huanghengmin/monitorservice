package com.inetec.ichange.service.monitor.databean;

import com.avdy.p4j.jdbc.service.GenericDAO;
import com.avdy.p4j.jdbc.service.GenericDaoImpl;
import com.avdy.p4j.jdbc.transfer.TransferUtil;
import com.inetec.ichange.service.monitor.utils.DaoService;
import com.inetec.ichange.service.monitor.utils.Pagination;
import org.apache.log4j.Logger;

public class BusinessLogDao {
    private static Logger logger = Logger.getLogger(BusinessLogDao.class);

    public BusinessLogDao() {

    }

    public Pagination<BusinessLog> listBusinessLog(int limit, int start) {
        Pagination<BusinessLog> result = null;
        try {
            TransferUtil.registerClass(BusinessLog.class);

            GenericDAO<BusinessLog> genericDAO = new GenericDaoImpl<BusinessLog>(
                    DaoService.getDaoService().getDataProvider()
                            .getDataFetcher());
            int countrows = genericDAO.countRows(BusinessLog.class, "1=1", "1=1");
            result = new Pagination(genericDAO.findAll(BusinessLog.class, start, limit), countrows);

        } catch (Exception e) {
            logger.error(e);
        }
        return result;

    }

    public void saveBusinessLog(BusinessLog bean) {
        try {
            TransferUtil.registerClass(BusinessLog.class);

            GenericDAO<BusinessLog> genericDAO = new GenericDaoImpl<BusinessLog>(
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
