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
public class LowerSysbizstatusDao {
    private static Logger logger = Logger.getLogger(DaoService.class);

    public LowerSysbizstatusDao() {

    }

    public Pagination<LowerSysbizstatusDataBean> list(int limit, int start) {
        Pagination<LowerSysbizstatusDataBean> result = null;
        try {
            TransferUtil.registerClass(LowerSysbizstatusDataBean.class);
            GenericDAO<LowerSysbizstatusDataBean> genericDAO =
                    new GenericDaoImpl<LowerSysbizstatusDataBean>( DaoService.getDaoService().getDataProvider().getDataFetcher());
            int countrows = genericDAO.countRows(LowerSysbizstatusDataBean.class, "starttime=curdate()-1", "1=1");
            result = new Pagination(genericDAO.findAll(LowerSysbizstatusDataBean.class, start, limit), countrows);

        } catch (Exception e) {
            logger.error(e);
        }
        return result;

    }

    public List<LowerSysbizstatusDataBean> list() {
        List<LowerSysbizstatusDataBean> result = null;
        try {
            TransferUtil.registerClass(LowerSysbizstatusDataBean.class);
            GenericDAO<LowerSysbizstatusDataBean> genericDAO =
                    new GenericDaoImpl<LowerSysbizstatusDataBean>( DaoService.getDaoService().getDataProvider().getDataFetcher());
            int countrows = genericDAO.countRows(LowerSysbizstatusDataBean.class, "starttime=curdate()-1", "1=1");
            result = genericDAO.findAll(LowerSysbizstatusDataBean.class,1,countrows);

        } catch (Exception e) {
            logger.error(e);
        }
        return result;
    }

    public static void main(String[] args){
        LowerSysbizstatusDao dao = new LowerSysbizstatusDao();
        List<LowerSysbizstatusDataBean> list = dao.list();
        list.size();
    }
}
