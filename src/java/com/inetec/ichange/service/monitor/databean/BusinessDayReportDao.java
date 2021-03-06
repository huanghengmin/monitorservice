package com.inetec.ichange.service.monitor.databean;

import com.avdy.p4j.jdbc.service.GenericDAO;
import com.avdy.p4j.jdbc.service.GenericDaoImpl;
import com.avdy.p4j.jdbc.transfer.TransferUtil;
import com.inetec.ichange.service.monitor.uplink.utils.UpFileUtils;
import com.inetec.ichange.service.monitor.utils.DaoService;
import com.inetec.ichange.service.monitor.utils.Pagination;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

public class BusinessDayReportDao {
    private static Logger logger = Logger.getLogger(BusinessDayReportDao.class);

    public BusinessDayReportDao() {

    }

    public Pagination<BusinessDayReport> listBusinessDayReportByDay(int limit, int start, String param) {
        Pagination<BusinessDayReport> result = null;
        try {
            TransferUtil.registerClass(BusinessDayReport.class);
            String sql = "select * from business_day_report where report_date='" + param+"'";
            GenericDAO<BusinessDayReport> genericDAO = new GenericDaoImpl<BusinessDayReport>(
                    DaoService.getDaoService().getDataProvider()
                            .getDataFetcher());
            int countrows = genericDAO.countRows(BusinessDayReport.class, sql, null);
            result = new Pagination(genericDAO.findByQuery(BusinessDayReport.class, sql, start, limit), countrows);

        } catch (Exception e) {
            logger.error(e);
        }
        return result;

    }
    public Pagination<BusinessDayReport> listBusinessDayReportByDay( String param) {
        Pagination<BusinessDayReport> result = null;
        try {
            TransferUtil.registerClass(BusinessDayReport.class);
            String sql = "select * from business_day_report where date(report_date)=date("+UpFileUtils.getFoundDayStartDate()+")";
            GenericDAO<BusinessDayReport> genericDAO = new GenericDaoImpl<BusinessDayReport>(
                    DaoService.getDaoService().getDataProvider()
                            .getDataFetcher());
            int countrows = genericDAO.countRows(BusinessDayReport.class, sql, null);
            result = new Pagination(genericDAO.findByQuery(BusinessDayReport.class, sql, 1, 600), countrows);

        } catch (Exception e) {
            logger.error(e);
        }
        return result;

    }
    public List<BusinessDayReport> listBusinessDayReportByNameAndDay(int limit, int start, String param) {
        List<BusinessDayReport> result = new ArrayList<BusinessDayReport>();
        try {
            TransferUtil.registerClass(BusinessDayReport.class);
            String sql = "select * from business_day_report where date_format(report_date,'%Y-%m-%d')='" + UpFileUtils.getCurrentDay().trim() + "' and business_name='" +
                    param + "'";
            GenericDAO<BusinessDayReport> genericDAO = new GenericDaoImpl<BusinessDayReport>(
                    DaoService.getDaoService().getDataProvider()
                            .getDataFetcher());
            result = genericDAO.findByQuery(BusinessDayReport.class, sql, start, limit);

        } catch (Exception e) {
            logger.error(e);
        }
        return result;

    }
    public Pagination<BusinessDayReport> listBusinessDayReportByNameAndDay(String name) {
        Pagination<BusinessDayReport> result = null;
        try {
            TransferUtil.registerClass(BusinessDayReport.class);
            String sql = "select * from business_day_report where date(report_date)=date(curdate()-1) and business_name='" +
                    name + "'";
            GenericDAO<BusinessDayReport> genericDAO = new GenericDaoImpl<BusinessDayReport>(
                    DaoService.getDaoService().getDataProvider()
                            .getDataFetcher());
            int countrows = genericDAO.countRows(BusinessDayReport.class, sql, null);
            result = new Pagination(genericDAO.findByQuery(BusinessDayReport.class, sql, 1, 1), countrows);

        } catch (Exception e) {
            logger.error(e);
        }
        return result;

    }
    public BusinessDayReport getBusinessDayReportByNameAndDay(String name) {
        BusinessDayReport result = null;
        try {
            TransferUtil.registerClass(BusinessDayReport.class);
            String sql = "select * from business_day_report where date(report_date)=date_sub(curdate(),interval 1 day) and business_name='" +
                    name + "'";
            GenericDAO<BusinessDayReport> genericDAO = new GenericDaoImpl<BusinessDayReport>(
                    DaoService.getDaoService().getDataProvider()
                            .getDataFetcher());
            int countrows = genericDAO.countRows(BusinessDayReport.class, sql, null);
            List<BusinessDayReport> result1=genericDAO.findByQuery(BusinessDayReport.class, sql, 1, countrows);
            if(result1.size()>0){
                result=result1.get(0);
            }

        } catch (Exception e) {
            logger.error(e);
        }
        return result;

    }

    public static void main(String[] args) {
        BusinessDayReportDao dao = new BusinessDayReportDao();
        dao.listBusinessDayReportByNameAndDay(1,1,"001");
    }

}
