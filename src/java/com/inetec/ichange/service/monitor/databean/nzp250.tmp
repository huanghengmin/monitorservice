package com.inetec.ichange.service.monitor.databean;

import com.avdy.p4j.jdbc.service.GenericDAO;
import com.avdy.p4j.jdbc.service.GenericDaoImpl;
import com.avdy.p4j.jdbc.transfer.TransferUtil;
import com.inetec.ichange.service.monitor.utils.DaoService;
import com.inetec.ichange.service.monitor.utils.Pagination;
import org.apache.log4j.Logger;

public class EquipmentAlertDao {
    private static Logger logger = Logger.getLogger(EquipmentAlertDao.class);

    public EquipmentAlertDao() {

    }

    public Pagination<EquipmentAlert> listDevice(int limit, int start) {
        Pagination<EquipmentAlert> result = null;
        try {
            TransferUtil.registerClass(EquipmentAlert.class);

            GenericDAO<EquipmentHourReport> genericDAO = new GenericDaoImpl<EquipmentHourReport>(
                    DaoService.getDaoService().getDataProvider()
                            .getDataFetcher());
            int countrows = genericDAO.countRows(EquipmentAlert.class, "1=1", "1=1");
            result = new Pagination(genericDAO.findAll(EquipmentAlert.class, start, limit), countrows);

        } catch (Exception e) {
            logger.error(e);
        }
        return result;

    }

    public void saveEquipmentAlert(EquipmentAlert bean) {
        try {
            TransferUtil.registerClass(EquipmentAlert.class);

            GenericDAO<EquipmentAlert> genericDAO = new GenericDaoImpl<EquipmentAlert>(
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
