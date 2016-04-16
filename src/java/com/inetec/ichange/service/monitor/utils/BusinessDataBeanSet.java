package com.inetec.ichange.service.monitor.utils;

import com.inetec.ichange.service.monitor.databean.BusinessDataBean;
import com.inetec.ichange.service.monitor.databean.BusinessRegister;
import com.inetec.ichange.service.monitor.databean.DeviceDataBean;
import org.apache.log4j.Logger;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by IntelliJ IDEA.
 * User: bluesky
 * Date: 2010-9-5
 * Time: 13:20:11
 * To change this template use File | Settings | File Templates.
 */
public class BusinessDataBeanSet {
    public ConcurrentHashMap beanset = new ConcurrentHashMap();
    public int I_Action_Time = 600;
    Logger logger = Logger.getLogger(BusinessDataBeanSet.class);

    public void init(List<BusinessRegister> list) {
        for (int i = 0; i < list.size(); i++) {
            beanset.put(list.get(i).getBusiness_name(), initBeanByBusiness(list.get(i)));
            logger.info("init business type name:" + list.get(i).getBusiness_name());
        }
    }

    public BusinessDataBean getBusinessDataBeanByIP(String id) {
        if (beanset.containsKey(id)) {
            return (BusinessDataBean) beanset.get(id);
        }
        return null;
    }

    public BusinessDataBean getBusinessDataBeanByID(String id) {
        if (beanset.containsKey(id)) {
            BusinessDataBean bean1 = (BusinessDataBean) beanset.get(id);
            bean1.setAction_time(I_Action_Time);
            beanset.put(id, bean1);
            return bean1;
        }
        return initBeanByBusiness(id);
    }

    public String getBusinessDataBeanByIDToJsonString(String id) {
        if (beanset.containsKey(id)) {
            return ((BusinessDataBean) beanset.get(id)).toJsonString();
        } else {
            return initBeanByBusiness(id).toJsonString();
        }

    }

    public void returnBusinessDataBean(String key, BusinessDataBean bean) {
        if (beanset.containsKey(key)) {
            beanset.replace(key,bean);
        } else
            beanset.put(key, bean);
    }

    protected BusinessDataBean initBeanByBusiness(BusinessRegister equ) {
        BusinessDataBean bean = new BusinessDataBean();
        bean.setBusiness_id(equ.getId());
        bean.setBusiness_name(equ.getBusiness_name());
        bean.setStatus(BusinessDataBean.I_Status_OK);
        bean.setXt_dataflow(0);
        bean.setAction_time(I_Action_Time);
        bean.setConnect_count(0);
        bean.setAlert_count(0);
        bean.setRecord_count(0);
        return bean;
    }

    protected BusinessDataBean initBeanByBusiness(String name) {
        BusinessDataBean bean = new BusinessDataBean();
        bean.setBusiness_name(name);
        bean.setStatus(BusinessDataBean.I_Status_Error);
        bean.setXt_dataflow(0);
        bean.setAction_time(I_Action_Time);
        bean.setConnect_count(0);
        bean.setAlert_count(0);
        bean.setRecord_count(0);
        return bean;
    }
}
