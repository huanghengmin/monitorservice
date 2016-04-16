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
public class LowerSysabnormalDao {
    private static Logger logger = Logger.getLogger(DaoService.class);

    public LowerSysabnormalDao() {

    }

    public Pagination<LowerSysabnormalDataBean> list(int limit, int start) {
        Pagination<LowerSysabnormalDataBean> result = null;
        try {
            TransferUtil.registerClass(LowerSysabnormalDataBean.class);
            GenericDAO<LowerSysabnormalDataBean> genericDAO =
                    new GenericDaoImpl<LowerSysabnormalDataBean>( DaoService.getDaoService().getDataProvider().getDataFetcher());
            int countrows = genericDAO.countRows(LowerSysabnormalDataBean.class, "happentime=curdate()-1", "1=1");
            result = new Pagination(genericDAO.findAll(LowerSysabnormalDataBean.class, start, limit), countrows);

        } catch (Exception e) {
            logger.error(e);
        }
        return result;

    }

    public List<LowerSysabnormalDataBean> list() {
        List<LowerSysabnormalDataBean> result = null;
        try {
            TransferUtil.registerClass(LowerSysabnormalDataBean.class);
            GenericDAO<LowerSysabnormalDataBean> genericDAO =
                    new GenericDaoImpl<LowerSysabnormalDataBean>( DaoService.getDaoService().getDataProvider().getDataFetcher());
            int countrows = genericDAO.countRows(LowerSysabnormalDataBean.class, "happentime=curdate()-1", "1=1");
            result = genericDAO.findAll(LowerSysabnormalDataBean.class, 1,countrows);

        } catch (Exception e) {
            logger.error(e);
        }
        return result;

    }

    public static void main(String[] args){
        LowerSysabnormalDao dao = new LowerSysabnormalDao();
        List<LowerSysabnormalDataBean> list = dao.list();
        list.size();

    }
}
