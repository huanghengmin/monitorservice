package com.inetec.ichange.service.monitor.uplink.databean;

import com.avdy.p4j.jdbc.service.GenericDAO;
import com.avdy.p4j.jdbc.service.GenericDaoImpl;
import com.avdy.p4j.jdbc.transfer.TransferUtil;
import com.inetec.ichange.service.monitor.databean.DeviceDataBean;
import com.inetec.ichange.service.monitor.utils.DaoService;
import com.inetec.ichange.service.monitor.utils.Pagination;
import org.apache.log4j.Logger;

import java.util.List;

public class TInlinkinfDao {
    private static Logger logger = Logger.getLogger(DaoService.class);

    public TInlinkinfDao() {

    }

    public Pagination<TInlinkinfDataBean> list(int limit, int start) {
        Pagination<TInlinkinfDataBean> result = null;
        try {
            TransferUtil.registerClass(TInlinkinfDataBean.class);

            GenericDAO<TInlinkinfDataBean> genericDAO = new GenericDaoImpl<TInlinkinfDataBean>(
                    DaoService.getDaoService().getDataProvider()
                            .getDataFetcher());
            int countrows = genericDAO.countRows(TInlinkinfDataBean.class, "1=1", "1=1");
            result = new Pagination(genericDAO.findAll(TInlinkinfDataBean.class, start, limit), countrows);

        } catch (Exception e) {
            logger.error(e);
        }
        return result;

    }

    public List<TInlinkinfDataBean> list() {
        List<TInlinkinfDataBean> result = null;
        try {
            TransferUtil.registerClass(TInlinkinfDataBean.class);

            GenericDAO<TInlinkinfDataBean> genericDAO = new GenericDaoImpl<TInlinkinfDataBean>(
                    DaoService.getDaoService().getDataProvider()
                            .getDataFetcher());
            int countrows = genericDAO.countRows(TInlinkinfDataBean.class, "1=1", "1=1");
            result = genericDAO.findAll(TInlinkinfDataBean.class, 1, countrows);

        } catch (Exception e) {
            logger.error(e);
        }
        return result;

    }
}
