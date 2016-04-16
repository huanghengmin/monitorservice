package com.inetec.ichange.service.monitor.databean;

import com.avdy.p4j.jdbc.service.GenericDAO;
import com.avdy.p4j.jdbc.service.GenericDaoImpl;
import com.avdy.p4j.jdbc.transfer.TransferUtil;
import com.inetec.ichange.service.monitor.utils.DaoService;
import com.inetec.ichange.service.monitor.utils.Pagination;
import org.apache.log4j.Logger;

public class SysabnormalInfDao {
    private static Logger logger = Logger.getLogger(SysabnormalInfDao.class);

    public SysabnormalInfDao() {

    }

    public Pagination<Sysabnormalinf> listAbnormalInf(int limit, int start) {
        Pagination<Sysabnormalinf> result = null;
        String sql = "select * from sysabnormalinf where status='1'";
        try {
            TransferUtil.registerClass(Sysabnormalinf.class);

            GenericDAO<Sysabnormalinf> genericDAO = new GenericDaoImpl<Sysabnormalinf>(
                    DaoService.getDaoService().getDataProvider()
                            .getDataFetcher());
            int countrows = genericDAO.countRows(Sysabnormalinf.class, sql, null);
            result = new Pagination(genericDAO.findByQuery(Sysabnormalinf.class, sql, start, limit), countrows);

        } catch (Exception e) {
            logger.error(e);
        }
        return result;

    }

    public void save(Sysabnormalinf bean) {
        try {
            TransferUtil.registerClass(Sysabnormalinf.class);

            GenericDAO<Sysabnormalinf> genericDAO = new GenericDaoImpl<Sysabnormalinf>(
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
