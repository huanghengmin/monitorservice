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
public class LowerSysstatusDao {
    private static Logger logger = Logger.getLogger(DaoService.class);

    public LowerSysstatusDao() {

    }

    public Pagination<LowerSysstatusDataBean> list(int limit, int start) {
        Pagination<LowerSysstatusDataBean> result = null;
        try {
            TransferUtil.registerClass(LowerSysstatusDataBean.class);
            GenericDAO<LowerSysstatusDataBean> genericDAO =
                    new GenericDaoImpl<LowerSysstatusDataBean>( DaoService.getDaoService().getDataProvider().getDataFetcher());
            int countrows = genericDAO.countRows(LowerSysstatusDataBean.class, "starttime=curdate()-1", "1=1");
            result = new Pagination(genericDAO.findAll(LowerSysstatusDataBean.class, start, limit), countrows);

        } catch (Exception e) {
            logger.error(e);
        }
        return result;

    }

    public List<LowerSysstatusDataBean> list() {
        List<LowerSysstatusDataBean> result = null;
        try {
            TransferUtil.registerClass(LowerSysstatusDataBean.class);
            GenericDAO<LowerSysstatusDataBean> genericDAO =
                    new GenericDaoImpl<LowerSysstatusDataBean>( DaoService.getDaoService().getDataProvider().getDataFetcher());
            int countrows = genericDAO.countRows(LowerSysstatusDataBean.class, "starttime=curdate()-1", "1=1");
            result = genericDAO.findAll(LowerSysstatusDataBean.class, 1, countrows);

        } catch (Exception e) {
            logger.error(e);
        }
        return result;
    }

    public static void main(String[] args){
        LowerSysstatusDao dao = new LowerSysstatusDao();
        List<LowerSysstatusDataBean> list = dao.list();
        list.size();
    }
}
