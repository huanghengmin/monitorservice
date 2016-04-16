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
public class LowerSysruntimeDao {
    private static Logger logger = Logger.getLogger(DaoService.class);

    public LowerSysruntimeDao() {

    }

    public Pagination<LowerSysruntimeDataBean> list(int limit, int start) {
        Pagination<LowerSysruntimeDataBean> result = null;
        try {
            TransferUtil.registerClass(LowerSysruntimeDataBean.class);
            GenericDAO<LowerSysruntimeDataBean> genericDAO =
                    new GenericDaoImpl<LowerSysruntimeDataBean>( DaoService.getDaoService().getDataProvider().getDataFetcher());
            int countrows = genericDAO.countRows(LowerSysruntimeDataBean.class, "starttime=curdate()-1", "1=1");
            result = new Pagination(genericDAO.findAll(LowerSysruntimeDataBean.class, start, limit), countrows);

        } catch (Exception e) {
            logger.error(e);
        }
        return result;

    }

    public List<LowerSysruntimeDataBean> list() {
        List<LowerSysruntimeDataBean> result = null;
        try {
            TransferUtil.registerClass(LowerSysruntimeDataBean.class);
            GenericDAO<LowerSysruntimeDataBean> genericDAO =
                    new GenericDaoImpl<LowerSysruntimeDataBean>( DaoService.getDaoService().getDataProvider().getDataFetcher());
            int countrows = genericDAO.countRows(LowerSysruntimeDataBean.class, "starttime=curdate()-1", "1=1");
            result = genericDAO.findAll(LowerSysruntimeDataBean.class, 1, countrows);

        } catch (Exception e) {
            logger.error(e);
        }
        return result;

    }

    public static void main(String[] args){
        LowerSysruntimeDao dao = new LowerSysruntimeDao();
        List<LowerSysruntimeDataBean> list = dao.list();
        list.size();
    }
}
