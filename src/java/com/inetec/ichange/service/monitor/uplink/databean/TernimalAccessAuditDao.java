package com.inetec.ichange.service.monitor.uplink.databean;

import com.avdy.p4j.jdbc.service.GenericDAO;
import com.avdy.p4j.jdbc.service.GenericDaoImpl;
import com.avdy.p4j.jdbc.transfer.TransferUtil;
import com.inetec.ichange.service.monitor.databean.DeviceDataBean;
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
public class TernimalAccessAuditDao {
    private static Logger logger = Logger.getLogger(DaoService.class);

    public TernimalAccessAuditDao() {

    }
    public void save(TernimalAccessAuditDataBean bean) {
        try {
            TransferUtil.registerClass(TernimalAccessAuditDataBean.class);

            GenericDAO<TernimalAccessAuditDataBean> genericDAO = new GenericDaoImpl<TernimalAccessAuditDataBean>(
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
    public Pagination<TernimalAccessAuditDataBean> list(int limit, int start) {
        Pagination<TernimalAccessAuditDataBean> result = null;
        try {
            TransferUtil.registerClass(TernimalAccessAuditDataBean.class);
            GenericDAO<TernimalAccessAuditDataBean> genericDAO =
                    new GenericDaoImpl<TernimalAccessAuditDataBean>( DaoService.getDaoService().getDataProvider().getDataFetcher());
            int countrows = genericDAO.countRows(TernimalAccessAuditDataBean.class, "1=1", "1=1");
            result = new Pagination(genericDAO.findAll(TernimalAccessAuditDataBean.class, start, limit), countrows);

        } catch (Exception e) {
            logger.error(e);
        }
        return result;

    }

    public List<TernimalAccessAuditDataBean> list() {
        List<TernimalAccessAuditDataBean> result = null;
        try {
            TransferUtil.registerClass(TernimalAccessAuditDataBean.class);
            GenericDAO<TernimalAccessAuditDataBean> genericDAO =
                    new GenericDaoImpl<TernimalAccessAuditDataBean>( DaoService.getDaoService().getDataProvider().getDataFetcher());
            int countrows = genericDAO.countRows(TernimalAccessAuditDataBean.class, "1=1", "1=1");
            result = genericDAO.findAll(TernimalAccessAuditDataBean.class,1, countrows);

        } catch (Exception e) {
            logger.error(e);
        }
        return result;

    }
}
