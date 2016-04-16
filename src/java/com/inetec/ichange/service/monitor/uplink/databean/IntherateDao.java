package com.inetec.ichange.service.monitor.uplink.databean;

import com.avdy.p4j.jdbc.service.GenericDAO;
import com.avdy.p4j.jdbc.service.GenericDaoImpl;
import com.avdy.p4j.jdbc.transfer.TransferUtil;
import com.inetec.ichange.service.monitor.utils.DaoService;
import com.inetec.ichange.service.monitor.utils.Pagination;
import org.apache.log4j.Logger;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Administrator
 * Date: 13-1-29
 * Time: ÏÂÎç3:57
 * To change this template use File | Settings | File Templates.
 */
public class IntherateDao {

    private static Logger logger = Logger.getLogger(IntherateDao.class);

    public IntherateDao() {

    }

    public Pagination<IntherateDateBean> list(int limit, int start) {
        Pagination<IntherateDateBean> result = null;
        try {
            TransferUtil.registerClass(IntherateDateBean.class);
            GenericDAO<IntherateDateBean> genericDAO =
                    new GenericDaoImpl<IntherateDateBean>(DaoService.getDaoService().getDataProvider().getDataFetcher());
            int countrows = genericDAO.countRows(IntherateDateBean.class, "1=1", "1=1");
            result = new Pagination(genericDAO.findAll(IntherateDateBean.class, start, limit), countrows);

        } catch (Exception e) {
            logger.error(e);
        }
        return result;

    }

    public List<IntherateDateBean> list() {
        List<IntherateDateBean> result = null;
        try {
            TransferUtil.registerClass(IntherateDateBean.class);
            GenericDAO<IntherateDateBean> genericDAO =
                    new GenericDaoImpl<IntherateDateBean>(DaoService.getDaoService().getDataProvider().getDataFetcher());
            int countrows = genericDAO.countRows(IntherateDateBean.class, "1=1", "1=1");
            result = genericDAO.findAll(IntherateDateBean.class, 1, countrows);

        } catch (Exception e) {
            logger.error(e);
        }
        return result;

    }

    public IntherateDateBean getIntherateDateBeanByUserid(String userid,Date date) {
        IntherateDateBean result = null;
        String start_time =dateToStr(date,
                "yyyy-MM-dd 00:00:00");
        String end_time = dateToStr(date,
                "yyyy-MM-dd 23:59:59");
        try {
            TransferUtil.registerClass(IntherateDateBean.class);
            GenericDAO<IntherateDateBean> genericDAO =
                    new GenericDaoImpl<IntherateDateBean>(DaoService.getDaoService().getDataProvider().getDataFetcher());
            List<IntherateDateBean> equlist = genericDAO.findByQuery(IntherateDateBean.class, " select * from intherate where userid='" + userid + "'"+ "and intime <= '" + end_time + "'"+ "and intime >= '" + start_time + "'", 1, 1);
            if (equlist.size() > 0) {
                result = (IntherateDateBean) equlist.get(0);
            } else {
                logger.warn("select * from intherate where userid=" + userid);
            }


        } catch (Exception e) {
            logger.error(e);
        }
        return result;

    }

    public String dateToStr(Date ts, String formatPattern) {
        String result = null;
        try
        {
            SimpleDateFormat dateFormat = new SimpleDateFormat(formatPattern);
            result = dateFormat.format(ts);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return result;
    }

    public void saveIntherateDateBean(IntherateDateBean bean) {
        //TransactionService ts = DaoService.getDaoService().getDataProvider().getTransactionService();
        try {
            TransferUtil.registerClass(IntherateDateBean .class);

            GenericDAO<IntherateDateBean> genericDAO = new GenericDaoImpl<IntherateDateBean>(
                    DaoService.getDaoService().getDataProvider()
                            .getDataFetcher());
            //ts.beginTransaction();
            if (genericDAO.isEntityExists(bean)) {

                genericDAO.deleteEntity(bean);
            }
            genericDAO.createEntity(bean);

            // ts.commitTransaction();
        } catch (Exception e) {
            logger.error(e);
            //ts.rollbackTransaction();
        }

    }
}
