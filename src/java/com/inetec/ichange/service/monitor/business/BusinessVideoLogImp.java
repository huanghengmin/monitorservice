package com.inetec.ichange.service.monitor.business;

import com.inetec.common.logs.LogHelper;
import com.inetec.ichange.service.Service;
import com.inetec.ichange.service.monitor.alarm.AlarmService;
import com.inetec.ichange.service.monitor.databean.AlertDataBean;
import com.inetec.ichange.service.monitor.databean.BusinessDataBean;
import com.inetec.ichange.service.monitor.databean.BusinessLog;
import com.inetec.ichange.service.monitor.databean.BusinessLogDao;
import org.apache.log4j.Logger;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.sql.Timestamp;

/**
 * 视频业务日志处理
 * User: 郭达望
 * Date: 13-4-11
 * Time: 上午10:35
 * To change this template use File | Settings | File Templates.
 */
public class BusinessVideoLogImp implements Runnable {
    private static final Logger m_log = Logger.getLogger(BusinessVideoLogImp.class);
    private boolean isRun = false;
    private byte[] buff;
    private ByteArrayOutputStream buffs = new ByteArrayOutputStream();
    private String platformName;
    private BusinessLogDao dao = new BusinessLogDao();

    public void processLog(byte[] logs) {
        if (buff != null) {
            try {
                buffs.write(logs);
                buffs.flush();
            } catch (IOException e) {
                m_log.warn("业务日志缓存出错!", e);
            }
        }
        buff = logs;
    }



    public void run() {
        isRun = true;

        while (isRun) {
            try {
                businessLog();
                Thread.sleep(1 * 1000);
            } catch (InterruptedException e) {
                //okay
            }
        }

    }
    public void businessLog() {
        if (buff != null) {
            if (buff.length < 3) {
                return;
            }
            try {
                LogHelper[] logs = LogHelper.getLogHelper(buff);
                for (int i = 0; i < logs.length; i++) {
                    //日志入库

                    dao.saveBusinessLog(getBusinessLogByLogHelper(logs[i]));

                    //业务状态统计

                }

            } catch (RuntimeException r) {
                m_log.warn("log parser erro!", r);

            } catch (Exception e) {
                m_log.warn("业务日志入库失败!", e);
            }
            buff = null;
        } else {
            buff = buffs.toByteArray();
            try {
                buffs.close();
                buffs = new ByteArrayOutputStream();
            } catch (IOException e) {
                m_log.warn("业务日志入库失败!", e);
            }
        }

    }

    public BusinessLog getBusinessLogByLogHelper(LogHelper log) {
        BusinessLog blog = new BusinessLog();
        blog.setBusiness_name(log.getAppName());
        blog.setPlatform_name(platformName);
        blog.setDest_ip(log.getDest_ip());
        blog.setDest_port(log.getDest_port());
        blog.setLevel(log.getLevel());
        blog.setLog_time(Timestamp.valueOf(log.getDate()));
        blog.setOperation(log.getOperate());
        blog.setSource_dest(log.getSource_port());
        blog.setSource_ip(log.getSource_ip());
        blog.setStatus(Integer.parseInt(log.getStatusCode()));
        blog.setUser_name(log.getUserName());
        blog.setAudit_info(log.getMessage());
        BusinessDataBean bean = BusinessPlatformService.dataset.getBusinessDataBeanByID(log.getAppName());
        bean.setRecord_count(bean.getRecord_count() + Integer.parseInt(log.getRecordCount()));
        m_log.info("业务日志类型" + log.getLevel());
        if (log.getLevel().equalsIgnoreCase("warn")) {
            bean.setAlert_count(bean.getAlert_count() + 1);
            //todo:添加报警处理
            bean.setStatus(BusinessDataBean.I_Status_Alert);
            Service.alert.process(AlarmService.AlertType_Business, getAlertBean(blog));
            Service.alert.process(AlarmService.AlertType_Security, getAlertBean(blog));

        }
        if (log.getLevel().equalsIgnoreCase("error")) {
            bean.setAlert_count(bean.getAlert_count() + 1);
            //todo:添加报警处理
            Service.alert.process(AlarmService.AlertType_Business, getAlertBean(blog));
            Service.alert.process(AlarmService.AlertType_Security, getAlertBean(blog));
            bean.setStatus(BusinessDataBean.I_Status_Error);

        }
        bean.setConnect_count(Integer.parseInt(log.getCurconnect()));
        if (log.getFlux() == null || log.getFlux().equalsIgnoreCase("null") || log.getFlux().equalsIgnoreCase("")) {
            log.setFlux("0");
        }

        bean.setXt_dataflow(bean.getXt_dataflow() + Float.parseFloat(log.getFlux()));
        BusinessPlatformService.dataset.returnBusinessDataBean(log.getAppName(), bean);
        return blog;

    }
    public AlertDataBean getAlertBean(BusinessLog log) {
        AlertDataBean result = new AlertDataBean();
        if (log.getDest_ip() != null && !log.getDest_ip().equalsIgnoreCase(""))
            result.setIp(log.getDest_ip());
        if (log.getSource_ip() != null && !log.getSource_ip().equalsIgnoreCase(""))
            result.setIp(log.getSource_ip());
        result.setName(log.getBusiness_name());
        result.setUsername(log.getUser_name());
        result.setAlert_time(log.getLog_time());
        result.setAlert_info(log.getAudit_info());

        return result;

    }
}
