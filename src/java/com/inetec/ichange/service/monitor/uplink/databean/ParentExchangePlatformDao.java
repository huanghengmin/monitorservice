package com.inetec.ichange.service.monitor.uplink.databean;

import com.avdy.p4j.jdbc.service.GenericDAO;
import com.avdy.p4j.jdbc.service.GenericDaoImpl;
import com.avdy.p4j.jdbc.transfer.TransferUtil;
import com.inetec.ichange.service.monitor.utils.DaoService;
import com.inetec.ichange.service.monitor.utils.Pagination;
import org.apache.log4j.Logger;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: qxp
 * Date: 12-2-7
 * Time: 3:42
 * To change this template use File | Settings | File Templates.
 */
public class ParentExchangePlatformDao {
    private static Logger logger = Logger.getLogger(ParentExchangePlatformDao.class);

    public ParentExchangePlatformDao() {

    }

    public Pagination<ParentExchangePlatformDataBean> list(int limit, int start) {
        Pagination<ParentExchangePlatformDataBean> result = null;
        try {
            TransferUtil.registerClass(ParentExchangePlatformDataBean.class);
            GenericDAO<ParentExchangePlatformDataBean> genericDAO =
                    new GenericDaoImpl<ParentExchangePlatformDataBean>(DaoService.getDaoService().getDataProvider().getDataFetcher());
            int countrows = genericDAO.countRows(ParentExchangePlatformDataBean.class, "1=1", "1=1");
            result = new Pagination(genericDAO.findAll(ParentExchangePlatformDataBean.class, start, limit), countrows);

        } catch (Exception e) {
            logger.error(e);
        }
        return result;

    }

    public List<ParentExchangePlatformDataBean> Ftplist() {
        List<ParentExchangePlatformDataBean> result = null;
        String sql = "select * from parent_exchange_platform where type='Ftp' and enablereport = '1'";
        try {
            TransferUtil.registerClass(ParentExchangePlatformDataBean.class);
            GenericDAO<ParentExchangePlatformDataBean> genericDAO =
                    new GenericDaoImpl<ParentExchangePlatformDataBean>(DaoService.getDaoService().getDataProvider().getDataFetcher());
            int countrows = genericDAO.countRows(ParentExchangePlatformDataBean.class, sql, null);
            result = genericDAO.findByQuery(ParentExchangePlatformDataBean.class, sql, 1, countrows);

        } catch (Exception e) {
            logger.error(e);
        }
        return result;

    }

    public List<ParentExchangePlatformDataBean> WebServicelist() {
        List<ParentExchangePlatformDataBean> result = null;
        String sql = "select * from parent_exchange_platform where type='Webservice' and enablereport = '1'";
        try {
            TransferUtil.registerClass(ParentExchangePlatformDataBean.class);
            GenericDAO<ParentExchangePlatformDataBean> genericDAO =
                    new GenericDaoImpl<ParentExchangePlatformDataBean>(DaoService.getDaoService().getDataProvider().getDataFetcher());
            int countrows = genericDAO.countRows(ParentExchangePlatformDataBean.class, sql, null);
            result = genericDAO.findByQuery(ParentExchangePlatformDataBean.class, sql, 1, countrows);

        } catch (Exception e) {
            logger.error(e);
        }
        return result;

    }

    public List<ParentExchangePlatformDataBean> list() {
        List<ParentExchangePlatformDataBean> result = null;
        try {
            TransferUtil.registerClass(ParentExchangePlatformDataBean.class);
            GenericDAO<ParentExchangePlatformDataBean> genericDAO =
                    new GenericDaoImpl<ParentExchangePlatformDataBean>(DaoService.getDaoService().getDataProvider().getDataFetcher());
            int countrows = genericDAO.countRows(ParentExchangePlatformDataBean.class, "1=1", "1=1");
            result = genericDAO.findAll(ParentExchangePlatformDataBean.class, 1, countrows);

        } catch (Exception e) {
            logger.error(e);
        }
        return result;

    }

    public ParentExchangePlatformDataBean findById(long id) {
        List<ParentExchangePlatformDataBean> result = null;
        try {
            TransferUtil.registerClass(ParentExchangePlatformDataBean.class);
            GenericDAO<ParentExchangePlatformDataBean> genericDAO =
                    new GenericDaoImpl<ParentExchangePlatformDataBean>(DaoService.getDaoService().getDataProvider().getDataFetcher());
            int countrows = genericDAO.countRows(ParentExchangePlatformDataBean.class, "1=1", "id=" + id);
            result = genericDAO.findAll(ParentExchangePlatformDataBean.class, 1, countrows);
            return result.get(0);
        } catch (Exception e) {
            logger.error(e);
        }
        return null;

    }

    public void save(ParentExchangePlatformDataBean bean) {
        try {
            TransferUtil.registerClass(ParentExchangePlatformDataBean.class);

            GenericDAO<ParentExchangePlatformDataBean> genericDAO = new GenericDaoImpl<ParentExchangePlatformDataBean>(
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

    public static void main(String[] args) {
        ParentExchangePlatformDao dao = new ParentExchangePlatformDao();
        System.out.println(dao.findById(2).getPlatform_ip());
        System.out.println(dao.WebServicelist().get(0).getPlatform_ip());
    }
}
