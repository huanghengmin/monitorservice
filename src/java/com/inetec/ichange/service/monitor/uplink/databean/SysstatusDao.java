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
public class SysstatusDao {
    private static Logger logger = Logger.getLogger(DaoService.class);

    public SysstatusDao() {

    }

    public Pagination<SysstatusDataBean> list(int limit, int start) {
        Pagination<SysstatusDataBean> result = null;
        try {
            TransferUtil.registerClass(SysstatusDataBean.class);
            GenericDAO<SysstatusDataBean> genericDAO =
                    new GenericDaoImpl<SysstatusDataBean>(DaoService.getDaoService().getDataProvider().getDataFetcher());
            int countrows = genericDAO.countRows(SysstatusDataBean.class, "select * from sysstatus where date(starttime)=date_sub(curdate(),interval 1 day)", null);
            result = new Pagination(genericDAO.findByQuery(SysstatusDataBean.class, "select * from sysstatus where date(starttime)=date_sub(curdate(),interval 1 day)", start, limit), countrows);

        } catch (Exception e) {
            logger.error(e);
        }
        return result;

    }

    public List<SysstatusDataBean> list() {
        List<SysstatusDataBean> result = null;
        try {
            TransferUtil.registerClass(SysstatusDataBean.class);
            GenericDAO<SysstatusDataBean> genericDAO =
                    new GenericDaoImpl<SysstatusDataBean>(DaoService.getDaoService().getDataProvider().getDataFetcher());
            int countrows = genericDAO.countRows(SysstatusDataBean.class, "select * from sysstatus where date(starttime)=date_sub(curdate(),interval 1 day)",null);
            result = genericDAO.findByQuery(SysstatusDataBean.class, "select * from sysstatus where date(starttime)=date_sub(curdate(),interval 1 day) ", 1, countrows);

        } catch (Exception e) {
            logger.error(e);
        }
        return result;
    }

    public List<SysstatusDataBean> listCurrent() {
        List<SysstatusDataBean> result = null;
        try {
            TransferUtil.registerClass(SysstatusDataBean.class);
            GenericDAO<SysstatusDataBean> genericDAO =
                    new GenericDaoImpl<SysstatusDataBean>(DaoService.getDaoService().getDataProvider().getDataFetcher());
            int countrows = genericDAO.countRows(SysstatusDataBean.class, "select * from sysstatus where date(starttime)=date(curdate())",null);
            result = genericDAO.findByQuery(SysstatusDataBean.class, "select * from sysstatus where date(starttime)=date(curdate()) ", 1, countrows);

        } catch (Exception e) {
            logger.error(e);
        }
        return result;
    }
    public SysstatusDataBean getCurrDayBean() {
        SysstatusDataBean result = null;
        try {
            TransferUtil.registerClass(SysstatusDataBean.class);
            GenericDAO<SysstatusDataBean> genericDAO =
                    new GenericDaoImpl<SysstatusDataBean>(DaoService.getDaoService().getDataProvider().getDataFetcher());
            int countrows = genericDAO.countRows(SysstatusDataBean.class, "select * from sysstatus where dae(starttime)=date(curdate()) ", null);
            List<SysstatusDataBean> equlist = genericDAO.findByQuery(SysstatusDataBean.class, "select * from sysstatus where date(starttime)=date(curdate()) ", 1, countrows);
            if (equlist.size() > 0) {
                result = equlist.get(0);
            }
        } catch (Exception e) {
            logger.error(e);
        }
        return result;
    }
    public SysstatusDataBean getFoundDayBean() {
        SysstatusDataBean result = null;
        try {
            TransferUtil.registerClass(SysstatusDataBean.class);
            GenericDAO<SysstatusDataBean> genericDAO =
                    new GenericDaoImpl<SysstatusDataBean>(DaoService.getDaoService().getDataProvider().getDataFetcher());
            int countrows = genericDAO.countRows(SysstatusDataBean.class, "select * from sysstatus where date(starttime)=date_sub(curdate(),interval 1 day) ", null);
            List<SysstatusDataBean> equlist = genericDAO.findByQuery(SysstatusDataBean.class, "select * from sysstatus where date(starttime)=date_sub(curdate(),interval 1 day) ", 1, countrows);
            if (equlist.size() > 0) {
                result = equlist.get(0);
            }
        } catch (Exception e) {
            logger.error(e);
        }
        return result;
    }

    public void save(SysstatusDataBean bean) {
        try {
            TransferUtil.registerClass(SysstatusDataBean.class);

            GenericDAO<SysstatusDataBean> genericDAO = new GenericDaoImpl<SysstatusDataBean>(
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

    public static void main(String[] args) {
        SysstatusDao dao = new SysstatusDao();
        List<SysstatusDataBean> list = dao.list();
        int length = list.size();
        System.out.println(length);

    }
}
