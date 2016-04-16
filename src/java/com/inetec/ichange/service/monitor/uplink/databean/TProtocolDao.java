package com.inetec.ichange.service.monitor.uplink.databean;

import com.avdy.p4j.jdbc.service.GenericDAO;
import com.avdy.p4j.jdbc.service.GenericDaoImpl;
import com.avdy.p4j.jdbc.transfer.TransferUtil;
import com.inetec.ichange.service.monitor.utils.DaoService;
import com.inetec.ichange.service.monitor.utils.Pagination;
import org.apache.log4j.Logger;

import java.util.List;

public class TProtocolDao {
    private static Logger logger = Logger.getLogger(DaoService.class);

    public TProtocolDao() {

    }

    public Pagination<TProtocolDataBean> listProtocol(int limit, int start) {
        Pagination<TProtocolDataBean> result = null;
        try {
            TransferUtil.registerClass(TProtocolDataBean.class);

            GenericDAO<TProtocolDataBean> genericDAO = new GenericDaoImpl<TProtocolDataBean>(
                    DaoService.getDaoService().getDataProvider()
                            .getDataFetcher());
            int countrows = genericDAO.countRows(TProtocolDataBean.class, "1=1", "1=1");
            result = new Pagination(genericDAO.findAll(TProtocolDataBean.class, start, limit), countrows);

        } catch (Exception e) {
            logger.error(e);
        }
        return result;

    }

    public Pagination<TProtocolDataBean> listProtocolByCode(int limit, int start, String code) {
        Pagination<TProtocolDataBean> result = null;
        try {
            TransferUtil.registerClass(TProtocolDataBean.class);
            String sql = "SELECT * FROM protocol_type where  protocol_code = '" + code + "'";
            GenericDAO<TProtocolDataBean> genericDAO = new GenericDaoImpl<TProtocolDataBean>(
                    DaoService.getDaoService().getDataProvider()
                            .getDataFetcher());
            int countrows = genericDAO.countRows(TProtocolDataBean.class, sql, null);
            result = new Pagination(genericDAO.findByQuery(TProtocolDataBean.class, sql, start, limit), countrows);

        } catch (Exception e) {
            logger.error(e);
        }
        return result;

    }

    public List<TProtocolDataBean> list() {
        List<TProtocolDataBean> result = null;
        try {
            TransferUtil.registerClass(TProtocolDataBean.class);

            GenericDAO<TProtocolDataBean> genericDAO = new GenericDaoImpl<TProtocolDataBean>(
                    DaoService.getDaoService().getDataProvider()
                            .getDataFetcher());
            int countrows = genericDAO.countRows(TProtocolDataBean.class, "1=1", "1=1");
            result = genericDAO.findAll(TProtocolDataBean.class, 1, countrows);

        } catch (Exception e) {
            logger.error(e);
        }
        return result;

    }
}
