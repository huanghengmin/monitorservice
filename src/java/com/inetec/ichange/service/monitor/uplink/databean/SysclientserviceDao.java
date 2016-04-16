package com.inetec.ichange.service.monitor.uplink.databean;

import com.avdy.p4j.jdbc.service.GenericDAO;
import com.avdy.p4j.jdbc.service.GenericDaoImpl;
import com.avdy.p4j.jdbc.transfer.TransferUtil;
import com.inetec.ichange.service.monitor.utils.DaoService;
import com.inetec.ichange.service.monitor.utils.Pagination;
import org.apache.log4j.Logger;

import java.util.List;

public class SysclientserviceDao {
    private static Logger logger = Logger.getLogger(DaoService.class);

    public SysclientserviceDao() {

    }

    public Pagination<SysclientserviceDataBean> listPlatinf(int limit, int start) {
        Pagination<SysclientserviceDataBean> result = null;
        try {
            TransferUtil.registerClass(SysclientserviceDataBean.class);

            GenericDAO<SysclientserviceDataBean> genericDAO = new GenericDaoImpl<SysclientserviceDataBean>(
                    DaoService.getDaoService().getDataProvider()
                            .getDataFetcher());
            int countrows = genericDAO.countRows(SysclientserviceDataBean.class, "1=1", "1=1");
            result = new Pagination(genericDAO.findAll(SysclientserviceDataBean.class, start, limit), countrows);

        } catch (Exception e) {
            logger.error(e);
        }
        return result;

    }



    public List<SysclientserviceDataBean> list() {
        List<SysclientserviceDataBean> result = null;
        try {
            TransferUtil.registerClass(SysclientserviceDataBean.class);

            GenericDAO<SysclientserviceDataBean> genericDAO = new GenericDaoImpl<SysclientserviceDataBean>(
                    DaoService.getDaoService().getDataProvider()
                            .getDataFetcher());
            int countrows = genericDAO.countRows(SysclientserviceDataBean.class, "1=1", "1=1");
            result = genericDAO.findAll(SysclientserviceDataBean.class, 1, countrows);

        } catch (Exception e) {
            logger.error(e);
        }
        return result;
    }
}
