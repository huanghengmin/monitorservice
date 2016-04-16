package com.inetec.ichange.service.monitor.uplink.databean;

import com.avdy.p4j.jdbc.service.GenericDAO;
import com.avdy.p4j.jdbc.service.GenericDaoImpl;
import com.avdy.p4j.jdbc.transfer.TransferUtil;
import com.inetec.ichange.service.monitor.utils.DaoService;
import com.inetec.ichange.service.monitor.utils.Pagination;
import org.apache.log4j.Logger;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Ç®ÏþÅÎ
 * Date: 12-2-7
 * Time: ÏÂÎç3:42
 * To change this template use File | Settings | File Templates.
 */
public class TerminalAuthCodeDao {
    private static Logger logger = Logger.getLogger(DaoService.class);

    public TerminalAuthCodeDao() {

    }

    public Pagination<TerminalAuthCodeDataBean> list(int limit, int start) {
        Pagination<TerminalAuthCodeDataBean> result = null;
        try {
            TransferUtil.registerClass(TerminalAuthCodeDataBean.class);
            GenericDAO<TerminalAuthCodeDataBean> genericDAO =
                    new GenericDaoImpl<TerminalAuthCodeDataBean>( DaoService.getDaoService().getDataProvider().getDataFetcher());
            int countrows = genericDAO.countRows(TerminalAuthCodeDataBean.class, "1=1", "1=1");
            result = new Pagination(genericDAO.findAll(TerminalAuthCodeDataBean.class, start, limit), countrows);

        } catch (Exception e) {
            logger.error(e);
        }
        return result;

    }

    public List<TerminalAuthCodeDataBean> list() {
        List<TerminalAuthCodeDataBean> result = null;
        try {
            TransferUtil.registerClass(TerminalAuthCodeDataBean.class);
            GenericDAO<TerminalAuthCodeDataBean> genericDAO =
                    new GenericDaoImpl<TerminalAuthCodeDataBean>( DaoService.getDaoService().getDataProvider().getDataFetcher());
            int countrows = genericDAO.countRows(TerminalAuthCodeDataBean.class, "1=1", "1=1");
            result = genericDAO.findAll(TerminalAuthCodeDataBean.class, 1, countrows);

        } catch (Exception e) {
            logger.error(e);
        }
        return result;

    }
}
