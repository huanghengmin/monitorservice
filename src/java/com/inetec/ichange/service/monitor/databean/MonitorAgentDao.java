package com.inetec.ichange.service.monitor.databean;

import com.avdy.p4j.jdbc.service.GenericDAO;
import com.avdy.p4j.jdbc.service.GenericDaoImpl;
import com.avdy.p4j.jdbc.transfer.TransferUtil;
import com.inetec.ichange.service.monitor.utils.DaoService;
import org.apache.log4j.Logger;

import java.util.List;

public class MonitorAgentDao {
    private static Logger logger = Logger.getLogger(MonitorAgentDao.class);

    public MonitorAgentDao() {

    }

    public List<MonitorAgent> listMonitorAgent(int limit, int start) {
        List<MonitorAgent> result = null;
        try {
            TransferUtil.registerClass(MonitorAgent.class);

            GenericDAO<MonitorAgent> genericDAO = new GenericDaoImpl<MonitorAgent>(
                    DaoService.getDaoService().getDataProvider()
                            .getDataFetcher());
            int countrows = genericDAO.countRows(MonitorAgent.class, "1=1", "1=1");
//            result = new Pagination(genericDAO.findAll(MonitorAgent.class, start, limit), countrows);
            result = genericDAO.findAll(MonitorAgent.class, start, limit);

        } catch (Exception e) {
            logger.error(e);
        }
        return result;

    }

    public void saveMonitorAgent(MonitorAgent bean) {
        try {
            TransferUtil.registerClass(MonitorAgent.class);

            GenericDAO<MonitorAgent> genericDAO = new GenericDaoImpl<MonitorAgent>(
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

    public String getMcHost() {
        List<MonitorAgent> agents = listMonitorAgent(200, 1);
        String host = "";

        if (agents != null && agents.size()>0) {
            MonitorAgent agent=agents.get(0);
            host=agent.getAgent_ip();

        }
        return host;
    }
}
