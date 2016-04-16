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
public class SysbizstatusDao {
    private static Logger logger = Logger.getLogger(SysbizstatusDao.class);

    public SysbizstatusDao() {

    }

    public Pagination<SysbizstatusDataBean> list(int limit, int start) {
        Pagination<SysbizstatusDataBean> result = null;
        String sql="select * from sysbizstatus where date(starttime)=date_sub(curdate(),interval 1 day) ";
        try {
            TransferUtil.registerClass(SysbizstatusDataBean.class);
            GenericDAO<SysbizstatusDataBean> genericDAO =
                    new GenericDaoImpl<SysbizstatusDataBean>( DaoService.getDaoService().getDataProvider().getDataFetcher());
            int countrows = genericDAO.countRows(SysbizstatusDataBean.class, sql, null);
            result = new Pagination(genericDAO.findByQuery(SysbizstatusDataBean.class,sql, start, limit), countrows);

        } catch (Exception e) {
            logger.error(e);
        }
        return result;

    }

    public List<SysbizstatusDataBean> list() {
        List<SysbizstatusDataBean> result = null;
        String sql="select * from sysbizstatus where date(starttime)=date_sub(curdate(),interval 1 day) ";
        try {
            TransferUtil.registerClass(SysbizstatusDataBean.class);
            GenericDAO<SysbizstatusDataBean> genericDAO =
                    new GenericDaoImpl<SysbizstatusDataBean>( DaoService.getDaoService().getDataProvider().getDataFetcher());
            int countrows = genericDAO.countRows(SysbizstatusDataBean.class, sql, null);
            result = genericDAO.findByQuery(SysbizstatusDataBean.class,sql ,1, countrows);

        } catch (Exception e) {
            logger.error(e);
        }
        return result;
    }
    public SysbizstatusDataBean getCurrDayBeanByBizId(int  name) {
        SysbizstatusDataBean result = null;
        String sql="select * from sysbizstatus where dae(starttime)>=date(curdate()) and Idbiz= "+name;
        try {
            TransferUtil.registerClass(SysbizstatusDataBean.class);
            GenericDAO<SysbizstatusDataBean> genericDAO =
                    new GenericDaoImpl<SysbizstatusDataBean>(DaoService.getDaoService().getDataProvider().getDataFetcher());
            int countrows = genericDAO.countRows(SysstatusDataBean.class, sql, null);
            List<SysbizstatusDataBean> equlist = genericDAO.findByQuery(SysstatusDataBean.class, sql, 1, countrows);
            if (equlist.size() > 0) {
                result = equlist.get(0);
            }
        } catch (Exception e) {
            logger.error(e);
        }
        return result;
    }
    public SysbizstatusDataBean getFoundDayBeanByBizId(int bizid) {
        SysbizstatusDataBean result = null;
        String sql="select * from sysbizstatus where date(starttime)>=date_sub(curdate(),interval 1 day) and Idbiz= "+bizid;
        try {
            TransferUtil.registerClass(SysbizstatusDataBean.class);
            GenericDAO<SysbizstatusDataBean> genericDAO =
                    new GenericDaoImpl<SysbizstatusDataBean>(DaoService.getDaoService().getDataProvider().getDataFetcher());
            int countrows = genericDAO.countRows(SysbizstatusDataBean.class, sql, null);
            List<SysbizstatusDataBean> equlist = genericDAO.findByQuery(SysbizstatusDataBean.class, sql, 1, countrows);
            if (equlist.size() > 0) {
                result = equlist.get(0);
            }
        } catch (Exception e) {
            logger.error(e);
        }
        return result;
    }
    public void save(SysbizstatusDataBean bean) {
        try {
            TransferUtil.registerClass(SysbizstatusDataBean.class);

            GenericDAO<SysbizstatusDataBean> genericDAO = new GenericDaoImpl<SysbizstatusDataBean>(
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
    public static void main(String[] args){
        SysbizstatusDao dao = new SysbizstatusDao();
        List<SysbizstatusDataBean> list = dao.list();
        int length = list.size();
        System.out.println(length);

    }
}
