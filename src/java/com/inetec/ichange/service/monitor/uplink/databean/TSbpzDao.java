package com.inetec.ichange.service.monitor.uplink.databean;

import com.avdy.p4j.jdbc.service.GenericDAO;
import com.avdy.p4j.jdbc.service.GenericDaoImpl;
import com.avdy.p4j.jdbc.transfer.TransferUtil;
import com.inetec.ichange.service.monitor.databean.DeviceDataBean;
import com.inetec.ichange.service.monitor.utils.DaoService;
import com.inetec.ichange.service.monitor.utils.Pagination;
import org.apache.log4j.Logger;

import java.util.List;

public class TSbpzDao {
    private static Logger logger = Logger.getLogger(DaoService.class);

    public TSbpzDao() {

    }

    public Pagination<TSbpzDataBean> listPlatinf(int limit, int start) {
        Pagination<TSbpzDataBean> result = null;
        try {
            TransferUtil.registerClass(TSbpzDataBean.class);

            GenericDAO<TSbpzDataBean> genericDAO = new GenericDaoImpl<TSbpzDataBean>(
                    DaoService.getDaoService().getDataProvider()
                            .getDataFetcher());
            int countrows = genericDAO.countRows(TSbpzDataBean.class, "1=1", "1=1");
            result = new Pagination(genericDAO.findAll(TSbpzDataBean.class, start, limit), countrows);

        } catch (Exception e) {
            logger.error(e);
        }
        return result;

    }

    public List<TSbpzDataBean> list() {
        List<TSbpzDataBean> result = null;
        try {
            TransferUtil.registerClass(TSbpzDataBean.class);

            GenericDAO<TSbpzDataBean> genericDAO = new GenericDaoImpl<TSbpzDataBean>(
                    DaoService.getDaoService().getDataProvider()
                            .getDataFetcher());
            int countrows = genericDAO.countRows(TSbpzDataBean.class, "1=1", "1=1");
            result = genericDAO.findAll(TSbpzDataBean.class, 1, countrows);

        } catch (Exception e) {
            logger.error(e);
        }
        return result;

    }
}
