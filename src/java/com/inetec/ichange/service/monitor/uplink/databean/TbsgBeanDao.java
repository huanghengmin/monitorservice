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
 * User: Administrator
 * Date: 13-4-11
 * Time: ÏÂÎç5:43
 * To change this template use File | Settings | File Templates.
 */
public class TbsgBeanDao {
    private static Logger logger = Logger.getLogger(TbsgBeanDao.class);

    public TbsgBeanDao() {

    }

    public void save(TbsgBean bean) {
        try {
            TransferUtil.registerClass(TbsgBean.class);

            GenericDAO<TbsgBean> genericDAO = new GenericDaoImpl<TbsgBean>(
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
