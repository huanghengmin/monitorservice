package com.inetec.ichange.service.monitor.databean;

import com.avdy.p4j.jdbc.service.GenericDAO;
import com.avdy.p4j.jdbc.service.GenericDaoImpl;
import com.avdy.p4j.jdbc.transfer.TransferUtil;
import com.inetec.ichange.service.monitor.utils.DaoService;
import com.inetec.ichange.service.monitor.utils.Pagination;
import org.apache.log4j.Logger;

public class BusinessExceptAlertDao {
    private static Logger logger = Logger.getLogger(BusinessExceptAlertDao.class);

    public BusinessExceptAlertDao() {

    }

    public Pagination<BusinessExceptAlert> listBusinessHourReport(int limit, int start) {
        Pagination<BusinessExceptAlert> result = null;
        try {
            TransferUtil.registerClass(BusinessExceptAlert.class);

            GenericDAO<BusinessExceptAlert> genericDAO = new GenericDaoImpl<BusinessExceptAlert>(
                    DaoService.getDaoService().getDataProvider()
                            .getDataFetcher());
            int countrows = genericDAO.countRows(BusinessExceptAlert.class, "1=1", "1=1");
            result = new Pagination(genericDAO.findAll(BusinessExceptAlert.class, start, limit), countrows);

        } catch (Exception e) {
            logger.error(e);
        }
        return result;

    }

    public void saveBusinessExceptAlert(BusinessExceptAlert bean) {
        try {
            TransferUtil.registerClass(BusinessExceptAlert.class);

            GenericDAO<BusinessExceptAlert> genericDAO = new GenericDaoImpl<BusinessExceptAlert>(
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
