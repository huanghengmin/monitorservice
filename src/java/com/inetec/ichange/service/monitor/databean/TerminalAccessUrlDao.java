package com.inetec.ichange.service.monitor.databean;

import com.avdy.p4j.jdbc.service.GenericDAO;
import com.avdy.p4j.jdbc.service.GenericDaoImpl;
import com.avdy.p4j.jdbc.transfer.TransferUtil;
import com.inetec.ichange.service.monitor.utils.DaoService;
import com.inetec.ichange.service.monitor.utils.Pagination;
import org.apache.log4j.Logger;

import java.util.List;

public class TerminalAccessUrlDao {
    private static Logger logger = Logger.getLogger(TerminalAccessUrlDao.class);




    public Pagination<TerminalAccessUrl> listAccessUrl(int limit, int start) {
        Pagination<TerminalAccessUrl> result = null;
        try {

            TransferUtil.registerClass(TerminalAccessUrl.class);

            GenericDAO<TerminalAccessUrl> genericDAO = new GenericDaoImpl<TerminalAccessUrl>(
                    DaoService.getDaoService().getDataProvider()
                            .getDataFetcher());
            int countrows = genericDAO.countRows(TerminalAccessUrl.class, "1=1", "1=1");
            result = new Pagination(genericDAO.findAll(TerminalAccessUrl.class, start, limit), countrows);

        } catch (Exception e) {
            logger.error(e);
        }
        return result;

    }
    public List<TerminalAccessUrl> listNoAccessUrl(int limit, int start) {
        List<TerminalAccessUrl> result = null;
        try {
            String sql="select * from terminal_access_url where action='1'" ;
            TransferUtil.registerClass(TerminalAccessUrl.class);

            GenericDAO<TerminalAccessUrl> genericDAO = new GenericDaoImpl<TerminalAccessUrl>(
                    DaoService.getDaoService().getDataProvider()
                            .getDataFetcher());
            int countrows = genericDAO.countRows(TerminalAccessUrl.class,sql, null);
            result = genericDAO.findByQuery(TerminalAccessUrl.class,sql, 1,countrows);

        } catch (Exception e) {
            logger.error(e);
        }
        return result;

    }

    public void save(TerminalAccessUrl bean) {
        try {
            TransferUtil.registerClass(TerminalAccessUrl.class);

            GenericDAO<TerminalAccessUrl> genericDAO = new GenericDaoImpl<TerminalAccessUrl>(
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
