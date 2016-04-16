package com.inetec.ichange.service.monitor.uplink.databean;

import com.avdy.p4j.jdbc.service.GenericDAO;
import com.avdy.p4j.jdbc.service.GenericDaoImpl;
import com.avdy.p4j.jdbc.transfer.TransferUtil;
import com.inetec.ichange.service.monitor.databean.Equipment;
import com.inetec.ichange.service.monitor.utils.DaoService;
import com.inetec.ichange.service.monitor.utils.Pagination;
import org.apache.log4j.Logger;

import java.util.List;

public class SysterminalstatusDao {
    private static Logger logger = Logger.getLogger(SysterminalstatusDao.class);

    public SysterminalstatusDao() {

    }

    public Pagination<SysterminalstatusDataBean> listPlatinf(int limit, int start) {
        Pagination<SysterminalstatusDataBean> result = null;
        try {
            TransferUtil.registerClass(SysterminalstatusDataBean.class);

            GenericDAO<SysterminalstatusDataBean> genericDAO = new GenericDaoImpl<SysterminalstatusDataBean>(
                    DaoService.getDaoService().getDataProvider()
                            .getDataFetcher());
            int countrows = genericDAO.countRows(SysterminalstatusDataBean.class, "1=1", "1=1");
            result = new Pagination(genericDAO.findAll(SysterminalstatusDataBean.class, start, limit), countrows);

        } catch (Exception e) {
            logger.error(e);
        }
        return result;

    }

    public List<SysterminalstatusDataBean> list() {
        List<SysterminalstatusDataBean> result = null;
       try {
            TransferUtil.registerClass(SysterminalstatusDataBean.class);

            GenericDAO<SysterminalstatusDataBean> genericDAO = new GenericDaoImpl<SysterminalstatusDataBean>(
                    DaoService.getDaoService().getDataProvider()
                            .getDataFetcher());
            int countrows = genericDAO.countRows(SysterminalstatusDataBean.class, "1=1", "1=1");
            result = genericDAO.findAll(SysterminalstatusDataBean.class, 1, countrows);

        } catch (Exception e) {
            logger.error(e);
        }
        return result;
    }

    public SysterminalstatusDataBean  getSysterminalInfByUserid(String userid) {
        SysterminalstatusDataBean result = null;
        try {
            TransferUtil.registerClass(SysterminalstatusDataBean.class);
            GenericDAO<SysterminalstatusDataBean> genericDAO =
                    new GenericDaoImpl<SysterminalstatusDataBean>( DaoService.getDaoService().getDataProvider().getDataFetcher());
            List<SysterminalstatusDataBean> equlist=genericDAO.findByQuery(SysterminalstatusDataBean.class, " select * from systerminalstatus where idterminal='" + userid + "'", 1, 1);
            if(equlist.size()>0){
                result=equlist.get(0);
            }

        } catch (Exception e) {
            logger.error(e);
        }
        return result;

    }

    public void save(SysterminalstatusDataBean bean) {
        try {
            TransferUtil.registerClass(SysterminalstatusDataBean.class);

            GenericDAO<SysterminalstatusDataBean> genericDAO = new GenericDaoImpl<SysterminalstatusDataBean>(
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
