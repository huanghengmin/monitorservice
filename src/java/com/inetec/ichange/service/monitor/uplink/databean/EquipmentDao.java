package com.inetec.ichange.service.monitor.uplink.databean;

import com.avdy.p4j.jdbc.service.GenericDAO;
import com.avdy.p4j.jdbc.service.GenericDaoImpl;
import com.avdy.p4j.jdbc.transfer.TransferUtil;
import com.inetec.ichange.service.monitor.utils.DaoService;
import com.inetec.ichange.service.monitor.utils.Pagination;
import org.apache.log4j.Logger;

import java.util.List;

public class EquipmentDao {
    private static Logger logger = Logger.getLogger(EquipmentDao.class);

    public EquipmentDao() {

    }

    public Pagination<EquipmentDataBean> listPlatinf(int limit, int start) {
        Pagination<EquipmentDataBean> result = null;
        try {
            TransferUtil.registerClass(EquipmentDataBean.class);

            GenericDAO<EquipmentDataBean> genericDAO = new GenericDaoImpl<EquipmentDataBean>(
                    DaoService.getDaoService().getDataProvider()
                            .getDataFetcher());
            int countrows = genericDAO.countRows(EquipmentDataBean.class, "1=1", "1=1");
            result = new Pagination(genericDAO.findAll(EquipmentDataBean.class, start, limit), countrows);

        } catch (Exception e) {
            logger.error(e);
        }
        return result;

    }

    public List<EquipmentDataBean> list() {
        List<EquipmentDataBean> result = null;
        try {
            TransferUtil.registerClass(EquipmentDataBean.class);

            GenericDAO<EquipmentDataBean> genericDAO = new GenericDaoImpl<EquipmentDataBean>(
                    DaoService.getDaoService().getDataProvider()
                            .getDataFetcher());
            int countrows = genericDAO.countRows(EquipmentDataBean.class, "1=1", "1=1");
            result = genericDAO.findAll(EquipmentDataBean.class, 1, countrows);

        } catch (Exception e) {
            logger.error(e);
        }
        return result;
    }

    public static void main(String[] args){
        EquipmentDao dao = new EquipmentDao();
        List<EquipmentDataBean> list =  dao.list();

        System.out.println(list.size());
        for(EquipmentDataBean edb : list){
            System.out.println(edb.getEqu_station());
        }
    }
}
