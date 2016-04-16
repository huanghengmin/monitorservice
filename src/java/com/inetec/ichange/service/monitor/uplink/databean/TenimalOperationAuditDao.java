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
 * Time: pm 3:42
 * To change this template use File | Settings | File Templates.
 */
public class TenimalOperationAuditDao {
    private static Logger logger = Logger.getLogger(DaoService.class);

    public TenimalOperationAuditDao() {

    }
    public void save(TenimalOperationAuditDataBean bean) {
        try {
            TransferUtil.registerClass(TenimalOperationAuditDataBean.class);

            GenericDAO<TenimalOperationAuditDataBean> genericDAO = new GenericDaoImpl<TenimalOperationAuditDataBean>(
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
    public Pagination<TenimalOperationAuditDataBean> list(int limit, int start) {
        Pagination<TenimalOperationAuditDataBean> result = null;
        try {
            TransferUtil.registerClass(TenimalOperationAuditDataBean.class);
            GenericDAO<TenimalOperationAuditDataBean> genericDAO =
                    new GenericDaoImpl<TenimalOperationAuditDataBean>( DaoService.getDaoService().getDataProvider().getDataFetcher());
            int countrows = genericDAO.countRows(TenimalOperationAuditDataBean.class, "1=1", "1=1");
            result = new Pagination(genericDAO.findAll(TenimalOperationAuditDataBean.class, start, limit), countrows);

        } catch (Exception e) {
            logger.error(e);
        }
        return result;

    }

    public List<TenimalOperationAuditDataBean> list() {
        List<TenimalOperationAuditDataBean> result = null;
        try {
            TransferUtil.registerClass(TenimalOperationAuditDataBean.class);
            GenericDAO<TenimalOperationAuditDataBean> genericDAO =
                    new GenericDaoImpl<TenimalOperationAuditDataBean>( DaoService.getDaoService().getDataProvider().getDataFetcher());
            int countrows = genericDAO.countRows(TenimalOperationAuditDataBean.class, "1=1", "1=1");
            result = genericDAO.findAll(TenimalOperationAuditDataBean.class, 1, countrows);

        } catch (Exception e) {
            logger.error(e);
        }
        return result;

    }
}
