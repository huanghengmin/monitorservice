package com.inetec.ichange.service.monitor.utils;

import com.inetec.ichange.service.monitor.databean.DeviceDataBean;
import com.inetec.ichange.service.monitor.databean.Equipment;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;


/**
 * Created by IntelliJ IDEA.
 * User: bluesky
 * Date: 2010-9-5
 * Time: 13:20:27
 * To change this template use File | Settings | File Templates.
 */
public class DeviceDataBeanSet {
    public ConcurrentHashMap beanset = new ConcurrentHashMap();
    public ConcurrentHashMap beanset2 = new ConcurrentHashMap();

    public void init(List<Equipment> list) {
        for (int i = 0; i < list.size(); i++) {
            beanset.put(list.get(i).getEqu_name(), initBeanByEquipment(list.get(i)));
            beanset2.put(String.valueOf(list.get(i).getId()), list.get(i).getEqu_name());
        }
    }

    public DeviceDataBean getDeviceDataBeanByIP(String id) {
        if (beanset.containsKey(id)) {
            return (DeviceDataBean) beanset.get(id);
        }
        return null;
    }

    public DeviceDataBean getDeviceDataBeanByID(String id) {
        if (beanset.containsKey(id)) {
            DeviceDataBean bean1 = (DeviceDataBean) beanset.get(id);
            beanset.put(id, bean1);
            return bean1;
        }
        return initBeanByEquipment(id);
    }

    public DeviceDataBean getDeviceDataBean(String id) {
        String name = "";
        if (beanset2.containsKey(id)) {
            name = (String) beanset2.get(id);
            beanset2.put(id, name);
        }
        if (beanset.containsKey(name)) {
            DeviceDataBean bean1 = (DeviceDataBean) beanset.get(name);
            beanset.put(name, bean1);
            return bean1;
        }
        return initBeanByEquipment(name);
    }

    public String getDeviceDataBeanByIDToJsonString(String id) {
        if (beanset.containsKey(id)) {
            return ((DeviceDataBean) beanset.get(id)).toJsonString();
        } else {
            return initBeanByEquipment(id).toJsonString();
        }

    }

    public void returnDeviceDataBean(String key, DeviceDataBean bean) {
        beanset.put(key, bean);
    }

    protected DeviceDataBean initBeanByEquipment(Equipment equ) {
        DeviceDataBean bean = new DeviceDataBean();
        if (equ.getMonitor_used().equalsIgnoreCase("N")) {
            bean.setStatus(DeviceDataBean.I_Status_OK);
        } else {
            bean.setStatus(DeviceDataBean.I_Status_Error);
        }
        //bean.setEqu_id(Integer.parseInt(getId()));
        bean.setEqu_name(equ.getEqu_name());
        bean.setEqu_id(equ.getId());
        bean.setCpu(0);
        bean.setCurrentcon(0);
        bean.setDisk(0);
        bean.setDisk_total(0);
        bean.setMaxcon(0);
        bean.setMem(0);
        bean.setMem_total(0);
        bean.setVpn(0);
        return bean;
    }

    protected DeviceDataBean initBeanByEquipment(String name) {
        DeviceDataBean bean = new DeviceDataBean();
        bean.setStatus(DeviceDataBean.I_Status_Error);
        //bean.setEqu_id(Integer.parseInt(getId()));
        bean.setEqu_name(name);
        //bean.setEqu_id(equ.getId());
        bean.setCpu(0);
        bean.setCurrentcon(0);
        bean.setDisk(0);
        bean.setDisk_total(0);
        bean.setMaxcon(0);
        bean.setMem(0);
        bean.setMem_total(0);
        bean.setVpn(0);
        return bean;
    }
}
