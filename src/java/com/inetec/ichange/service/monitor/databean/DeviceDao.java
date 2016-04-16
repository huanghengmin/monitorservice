package com.inetec.ichange.service.monitor.databean;

import java.util.List;

import com.inetec.ichange.service.monitor.utils.DaoService;
import com.inetec.ichange.service.monitor.utils.Pagination;
import org.apache.log4j.Logger;

import com.avdy.p4j.jdbc.PersistenceConfig;
import com.avdy.p4j.jdbc.dbms.DataProviderFactoryImpl;
import com.avdy.p4j.jdbc.service.DataProvider;
import com.avdy.p4j.jdbc.service.DataProviderFactory;
import com.avdy.p4j.jdbc.service.GenericDAO;
import com.avdy.p4j.jdbc.service.GenericDaoImpl;
import com.avdy.p4j.jdbc.transfer.TransferUtil;

public class DeviceDao {
    private static Logger logger = Logger.getLogger(DaoService.class);

    public DeviceDao() {

    }

    public Pagination<DeviceDataBean> listDevice(int limit, int start) {
        Pagination<DeviceDataBean> result = null;
        try {
            TransferUtil.registerClass(DeviceDataBean.class);

            GenericDAO<DeviceDataBean> genericDAO = new GenericDaoImpl<DeviceDataBean>(
                    DaoService.getDaoService().getDataProvider()
                            .getDataFetcher());
            int countrows = genericDAO.countRows(DeviceDataBean.class, "1=1", "1=1");
            result = new Pagination(genericDAO.findAll(DeviceDataBean.class, start, limit), countrows);

        } catch (Exception e) {
            logger.error(e);
        }
        return result;

    }

    public void saveDevice(DeviceDataBean bean) {
        try {
            TransferUtil.registerClass(DeviceDataBean.class);

            GenericDAO<DeviceDataBean> genericDAO = new GenericDaoImpl<DeviceDataBean>(
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
