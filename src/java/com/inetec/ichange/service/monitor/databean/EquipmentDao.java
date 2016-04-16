package com.inetec.ichange.service.monitor.databean;

import com.avdy.p4j.jdbc.service.GenericDAO;
import com.avdy.p4j.jdbc.service.GenericDaoImpl;
import com.avdy.p4j.jdbc.transfer.TransferUtil;
import com.inetec.ichange.service.monitor.utils.DaoService;
import com.inetec.ichange.service.monitor.utils.Pagination;
import org.apache.log4j.Logger;

import java.util.List;

public class EquipmentDao {
    private static Logger logger = Logger.getLogger(EquipmentDao.class);
    private boolean isIpSecVpn = false;

    public boolean isIpSecVpn() {
        return isIpSecVpn;
    }


    public EquipmentDao() {

    }

    public Pagination<Equipment> listDevice(int limit, int start) {
        Pagination<Equipment> result = null;
        try {

            TransferUtil.registerClass(Equipment.class);

            GenericDAO<Equipment> genericDAO = new GenericDaoImpl<Equipment>(
                    DaoService.getDaoService().getDataProvider()
                            .getDataFetcher());
            int countrows = genericDAO.countRows(Equipment.class, "1=1", "1=1");
            result = new Pagination(genericDAO.findAll(Equipment.class, start, limit), countrows);

        } catch (Exception e) {
            logger.error(e);
        }
        return result;

    }

    public Pagination<Equipment> listDeviceByLinkName(int limit, int start, String name) {
        Pagination<Equipment> result = null;
        try {
            String sql = "select * from equipment where id>=1 and link_name='" + name + "'";
            TransferUtil.registerClass(Equipment.class);

            GenericDAO<Equipment> genericDAO = new GenericDaoImpl<Equipment>(
                    DaoService.getDaoService().getDataProvider()
                            .getDataFetcher());
            int countrows = genericDAO.countRows(Equipment.class, sql, null);
            result = new Pagination(genericDAO.findByQuery(Equipment.class, sql, start, limit), countrows);

        } catch (Exception e) {
            logger.error(e);
        }
        return result;

    }

    public Equipment listDeviceByEquType(String code) {
        Equipment result = null;
        try {
            String sql = "select * from equipment where equ_type='" + code + "'";
            TransferUtil.registerClass(Equipment.class);

            GenericDAO<Equipment> genericDAO = new GenericDaoImpl<Equipment>(
                    DaoService.getDaoService().getDataProvider()
                            .getDataFetcher());
            int countrows = genericDAO.countRows(Equipment.class, sql, null);
            List<Equipment> listequ = genericDAO.findByQuery(Equipment.class, sql, 1, 1);
            if (listequ.size() > 0) {
                result = listequ.get(0);
            }

        } catch (Exception e) {
            logger.error(e);
        }
        return result;

    }


    public Equipment listDeviceByDeviceName(String name) {
        Equipment result = null;
        try {

            TransferUtil.registerClass(Equipment.class);

            GenericDAO<Equipment> genericDAO = new GenericDaoImpl<Equipment>(
                    DaoService.getDaoService().getDataProvider()
                            .getDataFetcher());

            List<Equipment> equlist = genericDAO.findByQuery(Equipment.class, " select * from equipment where equ_name='" + name + "'", 1, 1);
            if (equlist.size() > 0) {
                result = equlist.get(0);
            }

        } catch (Exception e) {
            logger.error(e);
        }
        return result;

    }

    public void saveEquipment(Equipment bean) {
        try {
            TransferUtil.registerClass(Equipment.class);

            GenericDAO<Equipment> genericDAO = new GenericDaoImpl<Equipment>(
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

    /*
    * vpn网关地址
    */
    public String getVpnDeviceIp() {
        String code = "4001";
        String ipseccode = "4000";
        Equipment equ = listDeviceByEquType(code);
        if (equ == null) {

            equ = listDeviceByEquType(ipseccode);
            if (equ != null)
                isIpSecVpn = true;
        }
        if (equ != null) {
            if (isIpSecVpn) {
                isIpSecVpn = true;
            } else {
                isIpSecVpn = false;
            }
            return equ.getIp();
        }

        return "";

    }

    /**
     * 终端加固服务地址
     *
     * @return
     */
    public String getTSRSDeviceIp() {
//        String code = "1030";
        String code = "4008";
        /*Pagination<Equipment> agents = listDevice(3000, 1);
        String ip = "";
        if (agents != null && agents.getItems() != null) {
            Iterator<Equipment> agentit = agents.getItems().iterator();
            while (agentit.hasNext()) {
                Equipment device = agentit.next();
                if (device.getEqu_type() != null && device.getEqu_type().equalsIgnoreCase(code)) {
                    ip = device.getIp();
                    return ip;
                }
            }
        }*/
        Equipment equ = listDeviceByEquType(code);
        if (equ == null) {

        } else {
            return equ.getIp();
        }
        return "";

    }

    /**
     * 终端加固服务地址
     *
     * @return
     */
    public String getJBPGDeviceIp() {
        String code = "4008";
        Equipment equ = listDeviceByEquType(code);
        if (equ == null) {

        } else {
            return equ.getIp();
        }
        return "";

    }

    public String getIPSecVPnIp() {
        String code = "4000";
        Equipment equ = listDeviceByEquType(code);
        if (equ == null) {

        } else {
            return equ.getIp();
        }
        return null;

    }

    public List<Equipment> list() {
        List<Equipment> result = null;
        try {
            TransferUtil.registerClass(Equipment.class);

            GenericDAO<Equipment> genericDAO = new GenericDaoImpl<Equipment>(
                    DaoService.getDaoService().getDataProvider()
                            .getDataFetcher());
            int countrows = genericDAO.countRows(Equipment.class, "1=1", "1=1");
            result = genericDAO.findAll(Equipment.class, 1, countrows);

        } catch (Exception e) {
            logger.error(e);
        }
        return result;
    }

    public static void main(String arg[]) throws Exception {
        EquipmentDao dao = new EquipmentDao();
        Equipment equipment = dao.listDeviceByDeviceName("vpn");
        if (equipment != null) {
            System.out.println("equ_name:" + equipment.getEqu_name());
        } else {
            System.out.println("equipment is null.");
        }
        System.out.println(dao.getVpnDeviceIp());
        System.out.println(dao.getJBPGDeviceIp());
    }
}
