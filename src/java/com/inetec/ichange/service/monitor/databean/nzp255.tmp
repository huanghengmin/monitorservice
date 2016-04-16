package com.inetec.ichange.service.monitor.databean;

import com.avdy.p4j.jdbc.service.GenericDAO;
import com.avdy.p4j.jdbc.service.GenericDaoImpl;
import com.avdy.p4j.jdbc.transfer.TransferUtil;
import com.inetec.ichange.service.monitor.utils.DaoService;
import com.inetec.ichange.service.monitor.utils.Pagination;
import org.apache.log4j.Logger;

public class EquipmentHourReportDao {
    private static Logger logger = Logger.getLogger(EquipmentHourReportDao.class);

    public EquipmentHourReportDao() {

    }

    public Pagination<EquipmentHourReport> listDevice(int limit, int start) {
        Pagination<EquipmentHourReport> result = null;
        try {
            TransferUtil.registerClass(EquipmentHourReport.class);

            GenericDAO<EquipmentHourReport> genericDAO = new GenericDaoImpl<EquipmentHourReport>(
                    DaoService.getDaoService().getDataProvider()
                            .getDataFetcher());
            int countrows = genericDAO.countRows(EquipmentHourReport.class, "1=1", "1=1");
            result = new Pagination(genericDAO.findAll(EquipmentHourReport.class, start, limit), countrows);

        } catch (Exception e) {
            logger.error(e);
        }
        return result;

    }

    public void saveEquipmentHourReport(EquipmentHourReport bean) {
        try {
            TransferUtil.registerClass(EquipmentHourReport.class);

            GenericDAO<EquipmentHourReport> genericDAO = new GenericDaoImpl<EquipmentHourReport>(
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
