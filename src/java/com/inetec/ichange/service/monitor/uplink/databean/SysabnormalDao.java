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
public class SysabnormalDao {
    private static Logger logger = Logger.getLogger(DaoService.class);

    public SysabnormalDao() {

    }

    public Pagination<SysabnormalDataBean> list(int limit, int start) {
        Pagination<SysabnormalDataBean> result = null;
        try {
            TransferUtil.registerClass(SysabnormalDataBean.class);
            GenericDAO<SysabnormalDataBean> genericDAO =
                    new GenericDaoImpl<SysabnormalDataBean>( DaoService.getDaoService().getDataProvider().getDataFetcher());
            int countrows = genericDAO.countRows(SysabnormalDataBean.class, "happentime=curdate()-1", "1=1");
            result = new Pagination(genericDAO.findAll(SysabnormalDataBean.class, start, limit), countrows);

        } catch (Exception e) {
            logger.error(e);
        }
        return result;

    }


    public List<SysabnormalDataBean> list() {
        List<SysabnormalDataBean> result = null;
        try {
            TransferUtil.registerClass(SysabnormalDataBean.class);
            GenericDAO<SysabnormalDataBean> genericDAO =
                    new GenericDaoImpl<SysabnormalDataBean>( DaoService.getDaoService().getDataProvider().getDataFetcher());
            int countrows = genericDAO.countRows(SysabnormalDataBean.class, "happentime=curdate()-1", "1=1");
            result = genericDAO.findAll(SysabnormalDataBean.class, 1, countrows);

        } catch (Exception e) {
            logger.error(e);
        }
        return result;
    }

    public void save(SysabnormalDataBean bean) {
        try {
            TransferUtil.registerClass(SysabnormalDataBean.class);

            GenericDAO<SysabnormalDataBean> genericDAO = new GenericDaoImpl<SysabnormalDataBean>(
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
