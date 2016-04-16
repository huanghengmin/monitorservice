package com.inetec.ichange.service.monitor.utils;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import com.inetec.ichange.service.monitor.databean.SysterminalinfDataBean;
import org.apache.log4j.Logger;


/**
 * 内存存储对象
 *
 * @author bluesky
 */
public class TerminalInfoCache {
    private static final Logger logger = Logger
            .getLogger(TerminalInfoCache.class);
    public static final String Str_JsonHeader = "[";
    public static final String Str_ObjectSpareChar = ",";
    public static final String Str_EndHeader = "]";
    public static String allList;
    public static String onlineList;

    public ConcurrentHashMap beanset = new ConcurrentHashMap();
    public ConcurrentHashMap userbeanset = new ConcurrentHashMap();

    public String getOnlineList(String beginno, String endno, int pagesize) {
        StringBuffer buff = new StringBuffer();
        logger.info("getOnline List begin");
        buff.append(Str_JsonHeader);
        int count = 0;
        Iterator<SysterminalinfDataBean> it = userbeanset.values().iterator();
        while (it.hasNext()) {
            SysterminalinfDataBean bean = it.next();
            if (bean.isOnline()) {
                buff.append(bean.toJsonString());
                buff.append(Str_ObjectSpareChar);
                count++;
            }
        }
        logger.info("getOnline List end.");
        buff.append(totalJson(count, beginno, endno, pagesize));
        buff.append(Str_EndHeader);
        return buff.toString();
    }
   // 用户流量违规
    public List<SysterminalinfDataBean> getOnlineListByFlux(long flux) {

        logger.info("getOnline ListBy flux begin.flux:" + flux);
        List<SysterminalinfDataBean> list = new ArrayList<SysterminalinfDataBean>();
        int count = 0;
        Iterator<SysterminalinfDataBean> it = userbeanset.values().iterator();
        while (it.hasNext()) {
            SysterminalinfDataBean bean = it.next();
            long temp = bean.getIn_flux() + bean.getOut_flux();
            logger.info("userbane userid:" + bean.getUserId() + " online and flux" + (bean.isOnline() && temp >= flux));
            if (bean.isOnline() && temp >= flux) {
                list.add(bean);
            }
        }
        logger.info("getOnline ListBy flux end.");
        return list;
    }
    // 获取在线‘URL违规’列表
    public List<SysterminalinfDataBean> getOnlineListByWarnCount(int number) {

        logger.info("getOnline ListBy warn count begin.warncount:" + number);
        List<SysterminalinfDataBean> list = new ArrayList<SysterminalinfDataBean>();
        int count = 0;
        Iterator<SysterminalinfDataBean> it = userbeanset.values().iterator();
        while (it.hasNext()) {
            SysterminalinfDataBean bean = it.next();

            logger.info("userbane userid:" + bean.getUserId() + " online and WarnCount" + (bean.isOnline() && bean.getWarncount() >= number));
            if (bean.isOnline() && bean.getWarncount() >= number) {
                list.add(bean);
            }
        }
        logger.info("getOnline ListBy warn count end.");
        return list;
    }
    // 获取在线‘其他违规’列表
    public List<SysterminalinfDataBean> getOnlineListByOtherCount(int number) {

        logger.info("getOnline List By warn count begin.othercount:" + number);
        List<SysterminalinfDataBean> list = new ArrayList<SysterminalinfDataBean>();
        int count = 0;
        Iterator<SysterminalinfDataBean> it = userbeanset.values().iterator();
        while (it.hasNext()) {
            SysterminalinfDataBean bean = it.next();

            logger.info("userbane userid:" + bean.getUserId() + " online and OtherCount" + (bean.isOnline() && bean.getOthercount() >= number));
            if (bean.isOnline() && bean.getWarncount() >= number) {
                list.add(bean);
            }
        }
        logger.info("getOnline ListBy Other count end.");
        return list;
    }
    // 获取在线‘网络违规’列表
    public List<SysterminalinfDataBean> getOnlineListByNetworkCount(int number) {

        logger.info("getOnline List By warn count begin.othercount:" + number);
        List<SysterminalinfDataBean> list = new ArrayList<SysterminalinfDataBean>();
        int count = 0;
        Iterator<SysterminalinfDataBean> it = userbeanset.values().iterator();
        while (it.hasNext()) {
            SysterminalinfDataBean bean = it.next();

            logger.info("userbane userid:" + bean.getUserId() + " online and NetworkCount" + (bean.isOnline() && bean.getNetworkcount() >= number));
            if (bean.isOnline() && bean.getWarncount() >= number) {
                list.add(bean);
            }
        }
        logger.info("getOnline ListBy Network count end.");
        return list;
    }
    // 获取在线‘进程违规’列表
    public List<SysterminalinfDataBean> getOnlineListByProcessCount(int number) {

        logger.info("getOnline List By warn count begin.othercount:" + number);
        List<SysterminalinfDataBean> list = new ArrayList<SysterminalinfDataBean>();
        int count = 0;
        Iterator<SysterminalinfDataBean> it = userbeanset.values().iterator();
        while (it.hasNext()) {
            SysterminalinfDataBean bean = it.next();

            logger.info("userbane userid:" + bean.getUserId() + " online and Processcount" + (bean.isOnline() && bean.getProcesscount() >= number));
            if (bean.isOnline() && bean.getWarncount() >= number) {
                list.add(bean);
            }
        }
        logger.info("getOnline ListBy Process count end.");
        return list;
    }
    // 获取在线‘机卡分离违规’列表
    public List<SysterminalinfDataBean> getOnlineListBySeparationCount(int number) {

        logger.info("getOnline List By warn count begin.othercount:" + number);
        List<SysterminalinfDataBean> list = new ArrayList<SysterminalinfDataBean>();
        int count = 0;
        Iterator<SysterminalinfDataBean> it = userbeanset.values().iterator();
        while (it.hasNext()) {
            SysterminalinfDataBean bean = it.next();

            logger.info("userbane userid:" + bean.getUserId() + " online and Separationcount" + (bean.isOnline() && bean.getSeparationcount() >= number));
            if (bean.isOnline() && bean.getWarncount() >= number) {
                list.add(bean);
            }
        }
        logger.info("getOnline ListBy Separation count end.");
        return list;
    }
    // 获取在线‘外设违规’列表
    public List<SysterminalinfDataBean> getOnlineListByPeripheralCount(int number) {

        logger.info("getOnline List By warn count begin.othercount:" + number);
        List<SysterminalinfDataBean> list = new ArrayList<SysterminalinfDataBean>();
        int count = 0;
        Iterator<SysterminalinfDataBean> it = userbeanset.values().iterator();
        while (it.hasNext()) {
            SysterminalinfDataBean bean = it.next();

            logger.info("userbane userid:" + bean.getUserId() + " online and Peripheralcount" + (bean.isOnline() && bean.getPeripheralcount() >= number));
            if (bean.isOnline() && bean.getWarncount() >= number) {
                list.add(bean);
            }
        }
        logger.info("getOnline ListBy Peripheral count end.");
        return list;
    }
    public String getAllList(String beginno, String endno, int pagesize) {
        logger.info("getALL List begin");
        StringBuffer buff = new StringBuffer();
        buff.append(Str_JsonHeader);
        Iterator<String> it = userbeanset.keySet().iterator();
        while (it.hasNext()) {
            SysterminalinfDataBean bean2 = (SysterminalinfDataBean) userbeanset
                    .get(it.next());
            //logger.info("SysterminalinfDatabean is:" + bean2.toJsonString());
            buff.append(bean2.toJsonString());
            buff.append(Str_ObjectSpareChar);
        }
        logger.info("getALL List end.");
        buff.append(totalJson(userbeanset.size(), beginno, endno, pagesize));
        buff.append(Str_EndHeader);
        return buff.toString();
    }

    public void init(List<SysterminalinfDataBean> list) {

        for (int i = 0; i < list.size(); i++) {
            beanset.put(list.get(i).getIp(), list.get(i));
            userbeanset.put(list.get(i).getUserId(), list.get(i));
        }

    }

    /**
     * 更新在线状态
     *
     * @param ip
     * @param userid
     */
    public void updateOnlineStatus(String ip, String userid, long influx, long outflux) {
        if (userbeanset.containsKey(userid)) {
            SysterminalinfDataBean bean1 = (SysterminalinfDataBean) userbeanset
                    .get(userid);
            bean1.setLastDate(System.currentTimeMillis());
            bean1.setStatus(1);
            bean1.setIn_flux(bean1.getIn_flux() + influx);
            bean1.setOut_flux(bean1.getOut_flux() + outflux);
            bean1.setIp(ip);
            userbeanset.replace(userid, bean1);
            if (beanset.containsKey(ip) && !ip.equalsIgnoreCase("0.0.0.0")) {
                beanset.replace(ip, bean1);
            } else {
                beanset.put(ip, bean1);
            }
            logger.info("userid update onlinestatus:" + userid + " ip:" + ip + " influx:" + influx + " outflux:" + outflux);

        }
    }

    public String getCnByUserId(String id) {
        if (userbeanset.containsKey(id)) {
            SysterminalinfDataBean bean1 = (SysterminalinfDataBean) userbeanset
                    .get(id);
            return bean1.getCn();

        }
        return null;
    }

    public SysterminalinfDataBean getBeanByUserId(String id) {
        if (userbeanset.containsKey(id)) {
            SysterminalinfDataBean bean1 = (SysterminalinfDataBean) userbeanset
                    .get(id);
            return bean1;

        }
        return null;
    }

    public String getUserIdByTermialId(long id) {
        String result = null;
        logger.info("getUserId by TermialId begin.");
        Iterator<SysterminalinfDataBean> it = userbeanset.values().iterator();
        while (it.hasNext()) {
            SysterminalinfDataBean bean = it.next();

            if (bean.isOnline() && id == bean.getIdTerminal()) {
                result = bean.getUserId();
                break;

            }
        }
        logger.info("getUserId by TermialId end.");
        return result;
    }

    public void updateBean(String id, SysterminalinfDataBean bean) {
        bean.setLastDate(System.currentTimeMillis());
        if (userbeanset.containsKey(id)) {
            userbeanset.replace(id, bean);
        } else {
            userbeanset.put(id, bean);
        }


    }


    /**
     * 临时阻断当前用户
     *
     * @param ip
     * @param userid
     */
    public void tempblock(String ip, String userid) {
        if (userbeanset.containsKey(userid)) {
            SysterminalinfDataBean bean1 = (SysterminalinfDataBean) userbeanset
                    .get(userid);
            bean1.setLastDate(System.currentTimeMillis());
            bean1.setBlock(true);
            bean1.setStatus(1);
            bean1.setIp(ip);
            userbeanset.replace(userid, bean1);
            if (beanset.containsKey(ip)) {
                beanset.replace(ip, bean1);
            } else {
                beanset.put(ip, bean1);
            }
            logger.info("userid update tempblock:" + userid + " ip:" + ip);

        }
    }

    /**
     * 恢复用户
     *
     * @param ip
     * @param userid
     */
    public void noblock(String ip, String userid) {
        if (userbeanset.containsKey(userid)) {
            SysterminalinfDataBean bean1 = (SysterminalinfDataBean) userbeanset
                    .get(userid);
            bean1.setLastDate(System.currentTimeMillis());
            bean1.setBlock(false);
            bean1.setStatus(1);
            bean1.setIp(ip);
            userbeanset.replace(userid, bean1);
            if (beanset.containsKey(ip)) {
                beanset.replace(ip, bean1);
            } else {
                beanset.put(ip, bean1);
            }
            logger.info("userid update noblock:" + userid + " ip:" + ip);

        }
    }

    /**
     * 阻断当前用户
     *
     * @param ip
     * @param userid
     */
    public void block(String ip, String userid) {
        if (userbeanset.containsKey(userid)) {
            SysterminalinfDataBean bean1 = (SysterminalinfDataBean) userbeanset
                    .get(userid);
            bean1.setLastDate(System.currentTimeMillis());
            bean1.setBlock(true);
            bean1.setStatus(1);
            bean1.setIp(ip);
            userbeanset.replace(userid, bean1);
            if (beanset.containsKey(ip)) {
                beanset.replace(ip, bean1);
            } else {
                beanset.put(ip, bean1);
            }
            logger.info("userid update  block:" + userid + " ip:" + ip);

        }
    }

    public String totalJson(int total, String beginno, String endno,
                            int pagesize) {
        StringBuffer json = new StringBuffer();
        json.append("{total:" + total);
        json.append(",beginno:'" + beginno + "'");
        json.append(",endno:'" + endno + "'");
        json.append(",pagesize:" + pagesize + "}");
        return json.toString();
    }


}
