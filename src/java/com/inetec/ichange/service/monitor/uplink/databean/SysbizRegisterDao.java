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
public class SysbizRegisterDao {
    private static Logger logger = Logger.getLogger(SysbizRegisterDao.class);

    public SysbizRegisterDao() {

    }

    public Pagination<SysbizRegisterDataBean> list(int limit, int start) {
        Pagination<SysbizRegisterDataBean> result = null;
        try {
            TransferUtil.registerClass(SysbizRegisterDataBean.class);
            GenericDAO<SysbizRegisterDataBean> genericDAO =
                    new GenericDaoImpl<SysbizRegisterDataBean>( DaoService.getDaoService().getDataProvider().getDataFetcher());
            int countrows = genericDAO.countRows(SysbizRegisterDataBean.class, "1=1", "1=1");
            result = new Pagination(genericDAO.findAll(SysbizRegisterDataBean.class, start, limit), countrows);

        } catch (Exception e) {
            logger.error(e);
        }
        return result;

    }

    public List<SysbizRegisterDataBean> list() {
        List<SysbizRegisterDataBean> result = null;
        try {
            TransferUtil.registerClass(SysbizRegisterDataBean.class);
            GenericDAO<SysbizRegisterDataBean> genericDAO =
                    new GenericDaoImpl<SysbizRegisterDataBean>( DaoService.getDaoService().getDataProvider().getDataFetcher());
            int countrows = genericDAO.countRows(SysbizRegisterDataBean.class, "1=1", "1=1");
            result = genericDAO.findAll(SysbizRegisterDataBean.class, 1, countrows);

        } catch (Exception e) {
            logger.error(e);
        }
        return result;
    }
    public static void main(String[] args){
        SysbizRegisterDao dao = new SysbizRegisterDao();
        List<SysbizRegisterDataBean> list = dao.list();
        System.out.println(list.size());

    }
}
