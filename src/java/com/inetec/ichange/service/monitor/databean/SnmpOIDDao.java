package com.inetec.ichange.service.monitor.databean;

import com.avdy.p4j.jdbc.service.GenericDAO;
import com.avdy.p4j.jdbc.service.GenericDaoImpl;
import com.avdy.p4j.jdbc.transfer.TransferUtil;
import com.inetec.ichange.service.monitor.utils.DaoService;
import com.inetec.ichange.service.monitor.utils.Pagination;
import org.apache.log4j.Logger;

/**
 * Created by IntelliJ IDEA.
 * User: Administrator
 * Date: 12-11-28
 * Time: ÏÂÎç3:26
 * To change this template use File | Settings | File Templates.
 */
public class SnmpOIDDao {
    private static Logger logger = Logger.getLogger(SnmpOIDDao.class);

    public SnmpOIDDao() {

    }

    public SnmpOid getSnmpOID(String name) {
        SnmpOid result=null;
        try {
            TransferUtil.registerClass(SnmpOid.class);

            GenericDAO<SnmpOid> genericDAO = new GenericDaoImpl<SnmpOid>(
                    DaoService.getDaoService().getDataProvider()
                            .getDataFetcher());
            Object[] names=new Object[1];
            names[0]=name;
            result=genericDAO.findByPrimaryKey(SnmpOid.class,names);

        } catch (Exception e) {
            logger.error(e);
        }
        return result;

    }

    public Pagination<SnmpOid> listSnmpOID(int limit, int start) {
        Pagination<SnmpOid> result=null;
        try {
            TransferUtil.registerClass(SnmpOid.class);

            GenericDAO<SnmpOid> genericDAO = new GenericDaoImpl<SnmpOid>(
                    DaoService.getDaoService().getDataProvider()
                            .getDataFetcher());
            int countrows=genericDAO.countRows(SnmpOid.class, "1=1", "1=1");
            result=new Pagination(genericDAO.findAll(SnmpOid.class, start, limit),countrows);

        } catch (Exception e) {
            logger.error(e);
        }
        return result;

    }
    public void saveDevice(SnmpOid bean) {
        //TransactionService ts = DaoService.getDaoService().getDataProvider().getTransactionService();
        try {
            TransferUtil.registerClass(SnmpOid .class);

            GenericDAO<SnmpOid> genericDAO = new GenericDaoImpl<SnmpOid>(
                    DaoService.getDaoService().getDataProvider()
                            .getDataFetcher());
            //ts.beginTransaction();
            if (genericDAO.isEntityExists(bean)) {

                genericDAO.deleteEntity(bean);
            }
            genericDAO.createEntity(bean);

            // ts.commitTransaction();
        } catch (Exception e) {
            logger.error(e);
            //ts.rollbackTransaction();
        }

    }
    public void delSnmpOID(SnmpOid bean) {
        //TransactionService ts = DaoService.getDaoService().getDataProvider().getTransactionService();
        try {
            TransferUtil.registerClass(SnmpOid .class);

            GenericDAO<SnmpOid> genericDAO = new GenericDaoImpl<SnmpOid>(
                    DaoService.getDaoService().getDataProvider()
                            .getDataFetcher());
            //ts.beginTransaction();
            if(genericDAO.isEntityExists(bean))
                genericDAO.deleteEntity(bean);
            //ts.commitTransaction();



        } catch (Exception e) {
            logger.error(e);
            //ts.rollbackTransaction();
        }

    }
}