package com.inetec.ichange.service.monitor.databean;

import com.avdy.p4j.jdbc.service.GenericDAO;
import com.avdy.p4j.jdbc.service.GenericDaoImpl;
import com.avdy.p4j.jdbc.transfer.TransferUtil;
import com.inetec.ichange.service.monitor.utils.DaoService;
import com.inetec.ichange.service.monitor.utils.Pagination;
import org.apache.log4j.Logger;

public class SysTerminalStatusRunDao {
    private static Logger logger = Logger.getLogger(SysTerminalStatusRunDao.class);

    public SysTerminalStatusRunDao() {

    }



    public void save(SysTerminalStatusRunBean bean) {
        try {
            TransferUtil.registerClass(SysTerminalStatusRunBean.class);

            GenericDAO<SysTerminalStatusRunBean> genericDAO = new GenericDaoImpl<SysTerminalStatusRunBean>(
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


}
