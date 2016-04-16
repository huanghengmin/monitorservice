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
 * User: qxp
 * Date: 12-2-7
 * Time: pm 3:42
 * To change this template use File | Settings | File Templates.
 */
public class SysterminalinfDao {
    private static Logger logger = Logger.getLogger(SysterminalinfDao.class);

    public SysterminalinfDao() {

    }

    public Pagination<SysterminalinfDataBean> list(int limit, int start) {
        Pagination<SysterminalinfDataBean> result = null;
        try {
            TransferUtil.registerClass(SysterminalinfDataBean.class);
            GenericDAO<SysterminalinfDataBean> genericDAO =
                    new GenericDaoImpl<SysterminalinfDataBean>(DaoService.getDaoService().getDataProvider().getDataFetcher());
            int countrows = genericDAO.countRows(SysterminalinfDataBean.class, "1=1", "1=1");
            result = new Pagination(genericDAO.findAll(SysterminalinfDataBean.class, start, limit), countrows);

        } catch (Exception e) {
            logger.error(e);
        }
        return result;

    }

    public List<SysterminalinfDataBean> list() {
        List<SysterminalinfDataBean> result = null;
        try {
            TransferUtil.registerClass(SysterminalinfDataBean.class);
            GenericDAO<SysterminalinfDataBean> genericDAO =
                    new GenericDaoImpl<SysterminalinfDataBean>(DaoService.getDaoService().getDataProvider().getDataFetcher());
            int countrows = genericDAO.countRows(SysterminalinfDataBean.class, "1=1", "1=1");
            result = genericDAO.findAll(SysterminalinfDataBean.class, 1, countrows);

        } catch (Exception e) {
            logger.error(e);
        }
        return result;

    }

    public SysterminalinfDataBean getSysterminalInfByUserid(String userid) {
        SysterminalinfDataBean result = null;
        try {
            TransferUtil.registerClass(SysterminalinfDataBean.class);
            GenericDAO<SysterminalinfDataBean> genericDAO =
                    new GenericDaoImpl<SysterminalinfDataBean>(DaoService.getDaoService().getDataProvider().getDataFetcher());
            List<SysterminalinfDataBean> equlist = genericDAO.findByQuery(SysterminalinfDataBean.class, " select * from systerminalinfo where userid='" + userid + "'", 1, 1);
            if (equlist.size() > 0) {
                result = (SysterminalinfDataBean) equlist.get(0);
            } else {
                logger.warn("SysterminalinfDao.getSysterminalInfByUserid error  ≤È—Øuserid=" + userid);
            }


        } catch (Exception e) {
            logger.error(e);
        }
        return result;

    }
}
