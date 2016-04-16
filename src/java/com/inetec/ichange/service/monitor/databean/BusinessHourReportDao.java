package com.inetec.ichange.service.monitor.databean;

import com.avdy.p4j.jdbc.service.GenericDAO;
import com.avdy.p4j.jdbc.service.GenericDaoImpl;
import com.avdy.p4j.jdbc.transfer.TransferUtil;
import com.inetec.ichange.service.monitor.utils.DaoService;
import com.inetec.ichange.service.monitor.utils.Pagination;
import org.apache.log4j.Logger;

import java.sql.Date;
import java.util.List;

public class BusinessHourReportDao {
    private static Logger logger = Logger.getLogger(BusinessHourReportDao.class);

    public BusinessHourReportDao() {

    }

    public Pagination<BusinessHourReport> listBusinessHourReport(int limit, int start) {
        Pagination<BusinessHourReport> result = null;
        try {
            TransferUtil.registerClass(BusinessHourReport.class);

            GenericDAO<BusinessHourReport> genericDAO = new GenericDaoImpl<BusinessHourReport>(
                    DaoService.getDaoService().getDataProvider()
                            .getDataFetcher());
            int countrows = genericDAO.countRows(BusinessHourReport.class, "1=1", "1=1");
            result = new Pagination(genericDAO.findAll(BusinessHourReport.class, start, limit), countrows);

        } catch (Exception e) {
            logger.error(e);
        }
        return result;

    }

    public int getBusinessHourReportMax(String name, String date, int hour) {
        int countrows = 0;
        String sql = "select * from business_hour_report where report_hour=" + hour + " and report_date='" + date + "' and business_name='" + name + "' ";
        try {
            TransferUtil.registerClass(BusinessHourReport.class);

            GenericDAO<BusinessHourReport> genericDAO = new GenericDaoImpl<BusinessHourReport>(
                    DaoService.getDaoService().getDataProvider()
                            .getDataFetcher());
            List<BusinessHourReport> temp = genericDAO.findByQuery(BusinessHourReport.class, sql, 1, 1);
            countrows = temp.size();
            logger.info(sql + "count £º" + temp.size());
        } catch (Exception e) {
            logger.error(e);
        }
        return countrows;

    }

    public void saveBusinessHourReport(BusinessHourReport bean) {
        try {
            TransferUtil.registerClass(BusinessHourReport.class);

            GenericDAO<BusinessHourReport> genericDAO = new GenericDaoImpl<BusinessHourReport>(
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
