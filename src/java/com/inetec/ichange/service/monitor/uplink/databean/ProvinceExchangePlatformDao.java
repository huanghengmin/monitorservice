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
public class ProvinceExchangePlatformDao {
    private static Logger logger = Logger.getLogger(ProvinceExchangePlatformDao.class);

    public ProvinceExchangePlatformDao() {

    }

    public Pagination<ProvinceExchangePlatformDataBean> list(int limit, int start) {
        Pagination<ProvinceExchangePlatformDataBean> result = null;
        try {
            TransferUtil.registerClass(ProvinceExchangePlatformDataBean.class);
            GenericDAO<ProvinceExchangePlatformDataBean> genericDAO =
                    new GenericDaoImpl<ProvinceExchangePlatformDataBean>( DaoService.getDaoService().getDataProvider().getDataFetcher());
            int countrows = genericDAO.countRows(ProvinceExchangePlatformDataBean.class, "1=1", "1=1");
            result = new Pagination(genericDAO.findAll(ProvinceExchangePlatformDataBean.class, start, limit), countrows);

        } catch (Exception e) {
            logger.error(e);
        }
        return result;
    }

    public List<ProvinceExchangePlatformDataBean> list() {
        List<ProvinceExchangePlatformDataBean> result = null;
        try {
            TransferUtil.registerClass(ProvinceExchangePlatformDataBean.class);
            GenericDAO<ProvinceExchangePlatformDataBean> genericDAO =
                    new GenericDaoImpl<ProvinceExchangePlatformDataBean>( DaoService.getDaoService().getDataProvider().getDataFetcher());
            int countrows = genericDAO.countRows(ProvinceExchangePlatformDataBean.class, "1=1", "1=1");
            result = genericDAO.findAll(ProvinceExchangePlatformDataBean.class, 1, countrows);

        } catch (Exception e) {
            logger.error(e);
        }
        return result;
    }

}
