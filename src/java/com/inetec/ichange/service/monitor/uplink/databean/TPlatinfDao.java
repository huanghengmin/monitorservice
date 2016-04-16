package com.inetec.ichange.service.monitor.uplink.databean;

import com.avdy.p4j.jdbc.service.GenericDAO;
import com.avdy.p4j.jdbc.service.GenericDaoImpl;
import com.avdy.p4j.jdbc.transfer.TransferUtil;
import com.inetec.ichange.service.monitor.databean.DeviceDataBean;
import com.inetec.ichange.service.monitor.utils.DaoService;
import com.inetec.ichange.service.monitor.utils.Pagination;
import org.apache.log4j.Logger;

import java.util.List;

public class TPlatinfDao {
    private static Logger logger = Logger.getLogger(DaoService.class);

    public TPlatinfDao() {

    }

    public Pagination<TPlatinfDataBean> listPlatinf(int limit, int start) {
        Pagination<TPlatinfDataBean> result = null;
        try {
            TransferUtil.registerClass(TPlatinfDataBean.class);

            GenericDAO<TPlatinfDataBean> genericDAO = new GenericDaoImpl<TPlatinfDataBean>(
                    DaoService.getDaoService().getDataProvider()
                            .getDataFetcher());
            int countrows = genericDAO.countRows(TPlatinfDataBean.class, "1=1", "1=1");
            result = new Pagination(genericDAO.findAll(TPlatinfDataBean.class, start, limit), countrows);

        } catch (Exception e) {
            logger.error(e);
        }
        return result;

    }

    public List<TPlatinfDataBean> list() {
        List<TPlatinfDataBean> result = null;
        try {
            TransferUtil.registerClass(TPlatinfDataBean.class);

            GenericDAO<TPlatinfDataBean> genericDAO = new GenericDaoImpl<TPlatinfDataBean>(
                    DaoService.getDaoService().getDataProvider()
                            .getDataFetcher());
            int countrows = genericDAO.countRows(TPlatinfDataBean.class, "1=1", "1=1");
            result = genericDAO.findAll(TPlatinfDataBean.class, 1, countrows);

        } catch (Exception e) {
            logger.error(e);
        }
        return result;
    }

    public static void main(String[] args){
        TPlatinfDao dao = new TPlatinfDao();
        System.out.println(dao.list().size());
    }
}
