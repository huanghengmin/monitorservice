package com.inetec.ichange.service.monitor.databean;

import com.avdy.p4j.jdbc.service.GenericDAO;
import com.avdy.p4j.jdbc.service.GenericDaoImpl;
import com.avdy.p4j.jdbc.transfer.TransferUtil;
import com.inetec.ichange.service.monitor.utils.DaoService;
import com.inetec.ichange.service.monitor.utils.Pagination;
import org.apache.log4j.Logger;

public class SecurityEventAlertDao {
    private static Logger logger = Logger.getLogger(SecurityEventAlertDao.class);

    public SecurityEventAlertDao() {

    }

    public Pagination<SecurityEventAlert> listSecurityEventAlert(int limit, int start) {
        Pagination<SecurityEventAlert> result = null;
        try {
            TransferUtil.registerClass(SecurityEventAlert.class);

            GenericDAO<SecurityEventAlert> genericDAO = new GenericDaoImpl<SecurityEventAlert>(
                    DaoService.getDaoService().getDataProvider()
                            .getDataFetcher());
            int countrows = genericDAO.countRows(SecurityEventAlert.class, "1=1", "1=1");
            result = new Pagination(genericDAO.findAll(SecurityEventAlert.class, start, limit), countrows);

        } catch (Exception e) {
            logger.error(e);
        }
        return result;

    }

    public void saveSecurityEventAlert(SecurityEventAlert bean) {
        try {
            TransferUtil.registerClass(SecurityEventAlert.class);

            GenericDAO<SecurityEventAlert> genericDAO = new GenericDaoImpl<SecurityEventAlert>(
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
