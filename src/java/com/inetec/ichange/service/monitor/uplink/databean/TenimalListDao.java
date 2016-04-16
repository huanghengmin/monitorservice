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
public class TenimalListDao {
    private static Logger logger = Logger.getLogger(DaoService.class);

    public TenimalListDao() {

    }

    public Pagination<TenimalListDataBean> list(int limit, int start) {
        Pagination<TenimalListDataBean> result = null;
        try {
            TransferUtil.registerClass(TenimalListDataBean.class);
            GenericDAO<TenimalListDataBean> genericDAO =
                    new GenericDaoImpl<TenimalListDataBean>( DaoService.getDaoService().getDataProvider().getDataFetcher());
            int countrows = genericDAO.countRows(TenimalListDataBean.class, "1=1", "1=1");
            result = new Pagination(genericDAO.findAll(TenimalListDataBean.class, start, limit), countrows);

        } catch (Exception e) {
            logger.error(e);
        }
        return result;

    }

    public List<TenimalListDataBean> list() {
        List<TenimalListDataBean> result = null;
        try {
            TransferUtil.registerClass(TenimalListDataBean.class);
            GenericDAO<TenimalListDataBean> genericDAO =
                    new GenericDaoImpl<TenimalListDataBean>( DaoService.getDaoService().getDataProvider().getDataFetcher());
            int countrows = genericDAO.countRows(TenimalListDataBean.class, "1=1", "1=1");
            result = genericDAO.findAll(TenimalListDataBean.class, 1, countrows);

        } catch (Exception e) {
            logger.error(e);
        }
        return result;

    }
    public int totalTenimal() {
        int countrows=0;
        try {
            TransferUtil.registerClass(TenimalListDataBean.class);
            GenericDAO<TenimalListDataBean> genericDAO =
                    new GenericDaoImpl<TenimalListDataBean>( DaoService.getDaoService().getDataProvider().getDataFetcher());
             countrows = genericDAO.countRows(TenimalListDataBean.class, "1=1", null);


        } catch (Exception e) {
            logger.error(e);
        }
        return  countrows;

    }
}
