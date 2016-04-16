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
public class SysruntimeDao {
    private static Logger logger = Logger.getLogger(DaoService.class);

    public SysruntimeDao() {

    }

    public Pagination<SysruntimeDataBean> list(int limit, int start) {
        Pagination<SysruntimeDataBean> result = null;
        try {
            TransferUtil.registerClass(SysruntimeDataBean.class);
            GenericDAO<SysruntimeDataBean> genericDAO =
                    new GenericDaoImpl<SysruntimeDataBean>(DaoService.getDaoService().getDataProvider().getDataFetcher());
            int countrows = genericDAO.countRows(SysruntimeDataBean.class, "select * from sysruntime where date(starttime)=date(curdate()-1)",null);
            result = new Pagination(genericDAO.findByQuery(SysruntimeDataBean.class, "select * from sysruntime where date(starttime)=date(curdate()-1)", start, limit), countrows);

        } catch (Exception e) {
            logger.error(e);
        }
        return result;

    }
    public SysruntimeDataBean getCurrDayBean() {
        SysruntimeDataBean result = null;
        try {
            TransferUtil.registerClass(SysruntimeDataBean.class);
            GenericDAO<SysruntimeDataBean> genericDAO =
                    new GenericDaoImpl<SysruntimeDataBean>(DaoService.getDaoService().getDataProvider().getDataFetcher());
            int countrows = genericDAO.countRows(SysruntimeDataBean.class, " select * from sysruntime where date(starttime)=date(curdate())", null);
            List<SysruntimeDataBean> equlist = genericDAO.findByQuery(SysruntimeDataBean.class, "select * from sysruntime where date(starttime)=date(curdate())", 1, countrows);
            if (equlist.size() > 0) {
                result = equlist.get(0);
            }
        } catch (Exception e) {
            logger.error(e);
        }
        return result;
    }
    public SysruntimeDataBean getFoundDayBean() {
        SysruntimeDataBean result = null;
        try {
            TransferUtil.registerClass(SysruntimeDataBean.class);
            GenericDAO<SysruntimeDataBean> genericDAO =
                    new GenericDaoImpl<SysruntimeDataBean>(DaoService.getDaoService().getDataProvider().getDataFetcher());
            int countrows = genericDAO.countRows(SysruntimeDataBean.class, "select * from sysruntime where date(starttime)=date_sub(curdate(),interval 1 day)", null);
            List<SysruntimeDataBean> equlist = genericDAO.findByQuery(SysruntimeDataBean.class, "select * from sysruntime where date(starttime)=date_sub(curdate(),interval 1 day)", 1, countrows);
            if (equlist.size() > 0) {
                result = equlist.get(0);
            }
        } catch (Exception e) {
            logger.error(e);
        }
        return result;
    }
    public List<SysruntimeDataBean> list() {
        List<SysruntimeDataBean> result = null;
        try {
            TransferUtil.registerClass(SysruntimeDataBean.class);
            GenericDAO<SysruntimeDataBean> genericDAO =
                    new GenericDaoImpl<SysruntimeDataBean>(DaoService.getDaoService().getDataProvider().getDataFetcher());
            int countrows = genericDAO.countRows(SysruntimeDataBean.class, "select * from sysruntime where date(starttime)=date_sub(curdate(),interval 1 day)", null);
            result = genericDAO.findByQuery(SysruntimeDataBean.class, "select * from sysruntime where date(starttime)=date_sub(curdate(),interval 1 day)", 1, countrows);


        } catch (Exception e) {
            logger.error(e);
        }
        return result;
    }

    public void save(SysruntimeDataBean bean) {
        try {
            TransferUtil.registerClass(SysruntimeDataBean.class);

            GenericDAO<SysruntimeDataBean> genericDAO = new GenericDaoImpl<SysruntimeDataBean>(
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
        SysruntimeDao dao = new SysruntimeDao();
        List<SysruntimeDataBean> list = dao.list();
        int length = list.size();
       System.out.println("current day:"+dao.getCurrDayBean().getStarttime());
        System.out.println(length);

    }
}
