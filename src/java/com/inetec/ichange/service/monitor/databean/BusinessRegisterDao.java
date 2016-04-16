package com.inetec.ichange.service.monitor.databean;

import com.avdy.p4j.jdbc.service.GenericDAO;
import com.avdy.p4j.jdbc.service.GenericDaoImpl;
import com.avdy.p4j.jdbc.transfer.TransferUtil;
import com.inetec.ichange.service.monitor.uplink.databean.SysbizRegisterDataBean;
import com.inetec.ichange.service.monitor.utils.DaoService;
import com.inetec.ichange.service.monitor.utils.Pagination;
import org.apache.log4j.Logger;

import java.util.List;

public class BusinessRegisterDao {
    private static Logger logger = Logger.getLogger(BusinessRegisterDao.class);

    public BusinessRegisterDao() {

    }
    public List<BusinessRegister> list() {
        List<BusinessRegister> result = null;
        try {
            TransferUtil.registerClass(BusinessRegister.class);
            GenericDAO<BusinessRegister> genericDAO =
                    new GenericDaoImpl<BusinessRegister>( DaoService.getDaoService().getDataProvider().getDataFetcher());
            int countrows = genericDAO.countRows(BusinessRegister.class, "1=1", null);
            result = genericDAO.findAll(BusinessRegister.class, 1, countrows);

        } catch (Exception e) {
            logger.error(e);
        }
        return result;
    }
    public Pagination<BusinessRegister> listBusinessRegister(int limit, int start, String platformname) {
        Pagination<BusinessRegister> result = null;
        try {
            TransferUtil.registerClass(BusinessRegister.class);

            GenericDAO<BusinessRegister> genericDAO = new GenericDaoImpl<BusinessRegister>(
                    DaoService.getDaoService().getDataProvider()
                            .getDataFetcher());
            int countrows = genericDAO.countRows(BusinessRegister.class, "business_name", platformname);
            result = new Pagination(genericDAO.findAll(BusinessRegister.class, start, limit), countrows);

        } catch (Exception e) {
            logger.error(e);
        }
        return result;

    }

    public Pagination<BusinessRegister> listBusinessRegister(int limit, int start) {
        Pagination<BusinessRegister> result = null;
        try {
            TransferUtil.registerClass(BusinessRegister.class);

            GenericDAO<BusinessRegister> genericDAO = new GenericDaoImpl<BusinessRegister>(
                    DaoService.getDaoService().getDataProvider()
                            .getDataFetcher());
            int countrows = genericDAO.countRows(BusinessRegister.class, "1=1", "1=1");
            result = new Pagination(genericDAO.findAll(BusinessRegister.class, start, limit), countrows);

        } catch (Exception e) {
            logger.error(e);
        }
        return result;

    }

    public void saveBusinessRegister(BusinessRegister bean) {
        try {
            TransferUtil.registerClass(BusinessRegister.class);

            GenericDAO<BusinessRegister> genericDAO = new GenericDaoImpl<BusinessRegister>(
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

    public BusinessRegister findByName(String business_name) {
        try {
            TransferUtil.registerClass(BusinessRegister.class);

            GenericDAO<BusinessRegister> genericDAO = new GenericDaoImpl<BusinessRegister>(
                    DaoService.getDaoService().getDataProvider().getDataFetcher());

            int countrows = genericDAO.countRows(BusinessRegister.class, "1=1", "business_name=" + business_name);
            List<BusinessRegister> result = genericDAO.findAll(BusinessRegister.class, 1, countrows);
            return result.get(0);
        } catch (Exception e) {
            logger.error(e);
        }
        return null;
    }
}
