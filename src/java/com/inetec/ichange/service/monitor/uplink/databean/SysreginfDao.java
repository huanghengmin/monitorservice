package com.inetec.ichange.service.monitor.uplink.databean;

import com.avdy.p4j.jdbc.service.GenericDAO;
import com.avdy.p4j.jdbc.service.GenericDaoImpl;
import com.avdy.p4j.jdbc.transfer.TransferUtil;
import com.inetec.ichange.service.monitor.utils.DaoService;
import com.inetec.ichange.service.monitor.utils.Pagination;
import org.apache.log4j.Logger;

import java.util.List;

public class SysreginfDao {
    private static Logger logger = Logger.getLogger(DaoService.class);

    public SysreginfDao() {

    }

    public Pagination<SysreginfDataBean> listPlatinf(int limit, int start) {
        Pagination<SysreginfDataBean> result = null;
        try {
            TransferUtil.registerClass(SysreginfDataBean.class);

            GenericDAO<SysreginfDataBean> genericDAO = new GenericDaoImpl<SysreginfDataBean>(
                    DaoService.getDaoService().getDataProvider()
                            .getDataFetcher());
            int countrows = genericDAO.countRows(SysreginfDataBean.class, "1=1", "1=1");
            result = new Pagination(genericDAO.findAll(SysreginfDataBean.class, start, limit), countrows);

        } catch (Exception e) {
            logger.error(e);
        }
        return result;

    }

    public List<SysreginfDataBean> list() {
        List<SysreginfDataBean> result = null;
        try {
            TransferUtil.registerClass(SysreginfDataBean.class);
            GenericDAO<SysreginfDataBean> genericDAO = new GenericDaoImpl<SysreginfDataBean>(
                    DaoService.getDaoService().getDataProvider()
                            .getDataFetcher());
            int countrows = genericDAO.countRows(SysreginfDataBean.class, "1=1", "1=1");
            result = genericDAO.findAll(SysreginfDataBean.class, 1, countrows);
        } catch (Exception e) {
            logger.error(e);
        }
        return result;
    }

    public static void main(String[] args){
        SysreginfDao dao = new SysreginfDao();
        List<SysreginfDataBean> list =  dao.list();
        System.out.println(list.size());
        for(SysreginfDataBean edb : list){
            System.out.println(edb.getPlatform_name());
        }
    }
}
