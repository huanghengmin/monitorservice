package com.inetec.ichange.service.monitor.databean;

import com.avdy.p4j.jdbc.service.GenericDAO;
import com.avdy.p4j.jdbc.service.GenericDaoImpl;
import com.avdy.p4j.jdbc.transfer.TransferUtil;
import com.inetec.ichange.service.monitor.utils.DaoService;
import com.inetec.ichange.service.monitor.utils.Pagination;
import org.apache.log4j.Logger;

public class EquipmentDayReportDao {
    private static Logger logger = Logger.getLogger(EquipmentDayReportDao.class);

    public EquipmentDayReportDao() {

    }

    public Pagination<EquipmentDayReport> listDeviceDayReport(int limit, int start, String param) {
        Pagination<EquipmentDayReport> result = null;
        try {
            TransferUtil.registerClass(EquipmentDayReport.class);
            String sql = "select * from equipment_day_report where report_date='" + param + "'";
            GenericDAO<EquipmentDayReport> genericDAO = new GenericDaoImpl<EquipmentDayReport>(
                    DaoService.getDaoService().getDataProvider()
                            .getDataFetcher());
            int countrows = genericDAO.countRows(EquipmentDayReport.class, sql, null);
            result = new Pagination(genericDAO.findByQuery(EquipmentDayReport.class, sql, start, limit), countrows);

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
