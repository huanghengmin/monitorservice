package com.inetec.ichange.service.monitor.uplink.databean;

import com.avdy.p4j.jdbc.service.GenericDAO;
import com.avdy.p4j.jdbc.service.GenericDaoImpl;
import com.avdy.p4j.jdbc.transfer.TransferUtil;

import com.inetec.ichange.service.monitor.utils.DaoService;
import com.inetec.ichange.service.monitor.utils.Pagination;
import org.apache.log4j.Logger;

import java.util.List;

public class TOutlinkinfDao {
    private static Logger logger = Logger.getLogger(DaoService.class);

    public TOutlinkinfDao() {

    }
    public void saveOutlink(TOutlinkinfDataBean bean) {
        try {
            TransferUtil.registerClass(TOutlinkinfDataBean.class);

            GenericDAO<TOutlinkinfDataBean> genericDAO = new GenericDaoImpl<TOutlinkinfDataBean>(
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
    public Pagination<TOutlinkinfDataBean> list(int limit, int start) {
        Pagination<TOutlinkinfDataBean> result = null;
        try {
            TransferUtil.registerClass(TOutlinkinfDataBean.class);

            GenericDAO<TOutlinkinfDataBean> genericDAO = new GenericDaoImpl<TOutlinkinfDataBean>(
                    DaoService.getDaoService().getDataProvider()
                            .getDataFetcher());
            int countrows = genericDAO.countRows(TOutlinkinfDataBean.class, "1=1", "1=1");
            result = new Pagination(genericDAO.findAll(TOutlinkinfDataBean.class, start, limit), countrows);

        } catch (Exception e) {
            logger.error(e);
        }
        return result;

    }

    public List<TOutlinkinfDataBean> list() {
        List<TOutlinkinfDataBean> result = null;
        try {
            TransferUtil.registerClass(TOutlinkinfDataBean.class);

            GenericDAO<TOutlinkinfDataBean> genericDAO = new GenericDaoImpl<TOutlinkinfDataBean>(
                    DaoService.getDaoService().getDataProvider()
                            .getDataFetcher());
            int countrows = genericDAO.countRows(TOutlinkinfDataBean.class, "1=1", "1=1");
            result = genericDAO.findAll(TOutlinkinfDataBean.class, 1, countrows);

        } catch (Exception e) {
            logger.error(e);
        }
        return result;
    }
    public TOutlinkinfDataBean getOutlinkByLinkType(String type) {
        TOutlinkinfDataBean result = null;
        try {

            TransferUtil.registerClass(TOutlinkinfDataBean.class);

            GenericDAO<TOutlinkinfDataBean> genericDAO = new GenericDaoImpl<TOutlinkinfDataBean>(
                    DaoService.getDaoService().getDataProvider()
                            .getDataFetcher());

            List<TOutlinkinfDataBean> equlist=genericDAO.findByQuery(TOutlinkinfDataBean.class,"select * from ext_link where link_Corp="+type,1,1);
            if(equlist.size()>0){
                result=equlist.get(0);
            }

        } catch (Exception e) {
            logger.error(e);
        }
        return result;

    }
}
