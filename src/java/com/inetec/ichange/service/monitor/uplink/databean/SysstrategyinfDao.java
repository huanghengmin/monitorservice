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
 * User: qxp?
 * Date: 12-2-7
 * Time: pm3:42
 * To change this template use File | Settings | File Templates.
 */
public class SysstrategyinfDao {
    private static Logger logger = Logger.getLogger(DaoService.class);

    public SysstrategyinfDao() {

    }

    public Pagination<SysstrategyinfDataBean> list(int limit, int start) {
        Pagination<SysstrategyinfDataBean> result = null;
        try {
            TransferUtil.registerClass(SysstrategyinfDataBean.class);
            GenericDAO<SysstrategyinfDataBean> genericDAO =
                    new GenericDaoImpl<SysstrategyinfDataBean>( DaoService.getDaoService().getDataProvider().getDataFetcher());
            int countrows = genericDAO.countRows(SysstrategyinfDataBean.class, "1=1", "1=1");
            result = new Pagination(genericDAO.findAll(SysstrategyinfDataBean.class, start, limit), countrows);

        } catch (Exception e) {
            logger.error(e);
        }
        return result;

    }

    public List<SysstrategyinfDataBean> list() {
        List<SysstrategyinfDataBean> result = null;
        try {
            TransferUtil.registerClass(SysstrategyinfDataBean.class);
            GenericDAO<SysstrategyinfDataBean> genericDAO =
                    new GenericDaoImpl<SysstrategyinfDataBean>( DaoService.getDaoService().getDataProvider().getDataFetcher());
            int countrows = genericDAO.countRows(SysstrategyinfDataBean.class, "1=1", "1=1");
            result = genericDAO.findAll(SysstrategyinfDataBean.class, 1,countrows);

        } catch (Exception e) {
            logger.error(e);
        }
        return result;

    }
}
