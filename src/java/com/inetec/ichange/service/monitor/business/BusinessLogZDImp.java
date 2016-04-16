package com.inetec.ichange.service.monitor.business;

import com.inetec.common.logs.LogHelper;
import com.inetec.ichange.service.Service;
import com.inetec.ichange.service.monitor.alarm.AlarmService;
import com.inetec.ichange.service.monitor.databean.*;
import com.inetec.ichange.service.monitor.databean.EquipmentDao;
import com.inetec.ichange.service.monitor.syslog.format.ILogFormat;
import com.inetec.ichange.service.monitor.syslog.format.KoalTbsgLog;
import com.inetec.ichange.service.monitor.uplink.databean.*;
import com.inetec.ichange.service.monitor.uplink.databean.SysterminalinfDataBean;
import net.sf.json.JSONObject;
import org.apache.log4j.Logger;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * 移动警务业务应用日志处理
 */

public class BusinessLogZDImp implements Runnable {
    private static final Logger m_log = Logger.getLogger(BusinessLogZDImp.class);
    private boolean isRun = false;
    private byte[] buff;
    private ByteArrayOutputStream buffs = new ByteArrayOutputStream();
    private String platformName;
    private BusinessLogDao dao = new BusinessLogDao();
    private EquipmentLogDao equlogDAO = new EquipmentLogDao();
    private EquipmentDao equDAO = new EquipmentDao();
    private TOutlinkinfDao outlinkDao = new TOutlinkinfDao();
    private TernimalAccessAuditDao terniAcceAuditDao = new TernimalAccessAuditDao();
    private TenimalOperationAuditDao terniAccOperationAuditDao = new TenimalOperationAuditDao();
    private SysreginfDao sysreginfDao = new SysreginfDao();
    private SysabnormalDao sysabnormalDao = new SysabnormalDao();
    private SysterminalinfDao systerminalinfDao = new SysterminalinfDao();
    private IntherateDao intherateDao = new IntherateDao();

    public boolean isRun() {

        return isRun;
    }

    public void setPlatformName(String name) {
        this.platformName = name;
    }

    public BusinessLogZDImp() {

    }


    /* public void processLog(byte[] logs) {
        if (buff != null) {
            try {
                buffs.write(logs);
                buffs.flush();
            } catch (IOException e) {
                m_log.warn("业务日志缓存出错!", e);
            }
        }
        buff = logs;
    }*/

    public void processILogFormat(ILogFormat log)  {
        // m_log.info("process log");




        BusinessLog blog = log.getBussinessLog();
        if (blog != null) {
            blog.setPlatform_name(platformName);
            BusinessDataBean bean = BusinessPlatformService.dataset.getBusinessDataBeanByID(blog.getBusiness_name());
            bean.setRecord_count(bean.getRecord_count() + 1);
            m_log.info("业务日志类型" + blog.getLevel());
            if (blog.getLevel().equalsIgnoreCase("warn")) {
                bean.setAlert_count(bean.getAlert_count() + 1);
                //todo:添加报警处理
                bean.setStatus(BusinessDataBean.I_Status_Alert);
                Service.alert.process(AlarmService.AlertType_Business, getAlertBean(blog));
                Service.alert.process(AlarmService.AlertType_Security, getAlertBean(blog));

            }
            if (blog.getLevel().equalsIgnoreCase("error")) {
                bean.setAlert_count(bean.getAlert_count() + 1);
                //todo:添加报警处理
                Service.alert.process(AlarmService.AlertType_Business, getAlertBean(blog));
                Service.alert.process(AlarmService.AlertType_Security, getAlertBean(blog));
                bean.setStatus(BusinessDataBean.I_Status_Error);

            }
            bean.setConnect_count(bean.getConnect_count() + 1);
            float temp=(float)((bean.getXt_dataflow()*1024*1024)+log.getIn_Flux()+log.getOut_Flux())/(float)(1024*1024);


            bean.setXt_dataflow(temp);
            BusinessPlatformService.dataset.returnBusinessDataBean(blog.getBusiness_name(), bean);
            dao.saveBusinessLog(blog);
        }

        EquipmentLog elog = log.getEquipmentLog();
        if (elog != null) {
            //m_log.info("业务日志类型" + elog.getLevel());
            if (elog.getLevel().equalsIgnoreCase("warn")) {
                Service.alert.process(AlarmService.AlertType_Equipment, getAlertBeanByEquement(elog));
            }
            if (elog.getLevel().equalsIgnoreCase("error")) {
                //todo:添加报警处理
                Service.alert.process(AlarmService.AlertType_Equipment, getAlertBeanByEquement(elog));
            }
            if(elog.getIp()!=null&&!"".equalsIgnoreCase(elog.getIp())){
                equlogDAO.saveEquipmentLog(elog);
            }
            System.out.println(elog.getLog_info());
            if(elog.getLog_info().contains("logflag=\"TBSGS\"")){      //B/S日志
                KoalTbsgLog logs = new KoalTbsgLog();
                TbsgBeanDao tbsgBeanDao=new TbsgBeanDao();
                logs.process(elog.getLog_info());
                TbsgBean tbsg=new TbsgBean();
                tbsg.setUserip(logs.getUserip());
                tbsg.setAccessurl(logs.getAccessurl());
                tbsg.setOrgcode(logs.getOrgcode());
                tbsg.setUsername(logs.getUsername());
                tbsg.setIdentity(logs.getIdentity());
                tbsg.setAccessreturn(logs.getAccessreturn());
                tbsg.setReason(logs.getReason());
                tbsg.setTbsgip(logs.getTbsgip());
                tbsg.setProxycn(logs.getProxycn());
                tbsg.setTerminalid(logs.getTerminalid());
                if(logs.getTime().contains("+")){
                    tbsg.setTime(Timestamp.valueOf(logs.getTime().substring(0,10)+" "+logs.getTime().substring(11,19)));
                }else {
                    tbsg.setTime(Timestamp.valueOf(logs.getTime()));
                }
//                tbsg.setTime(Timestamp.valueOf(logs.getTime()));
                tbsg.setBytes(logs.getBytes());
                tbsg.setUpbytes(logs.getUpbytes());
                tbsg.setServiceid(logs.getServiceid());
                tbsgBeanDao.save(tbsg);
            }
            //终端设备
            if(elog.getLog_info().contains("logFlag")){
                String json = elog.getLog_info().substring(elog.getLog_info().indexOf("{"),elog.getLog_info().length());
//                if(!(elog.getLog_info().substring(elog.getLog_info().length()-1,elog.getLog_info().length())).equalsIgnoreCase("}")){
//                        json+="}";
//                }
                if(!elog.getLog_info().endsWith("\"}")){
                    json+="\"}";
                }
                System.out.println("json=="+json);
//                String json = "{\"accessFlag\":false,\"accessUrls\":[{\"count\":0,\"url\":\"http://wapp.baidu.com\"},{\"count\":0,\"url\":\"http://m.baidu.com\"}],\"city\":\"浙江省杭州市滨江区\",\"date\":\"1359440775322\",\"institutions\":\"研发部\",\"logFlag\":\"OEILG\",\"msg\":\"机卡分离,服务器不存在拥有此证书用户!\",\"organization\":\"子1022\",\"phone\":\"信息不全\",\"phonenetid\":\"460023883904668\",\"province\":\"浙江省\",\"serialnumber\":\"11068606988550442764\",\"user\":\"信息不全\",\"userid\":\"111236547856322114\"}";
                JSONObject jsonObject = JSONObject.fromObject(json);
//                SysreginfDao dao = new SysreginfDao();
//                List<SysreginfDataBean> list =  dao.list();
//                SysreginfDataBean sysreginfDataBean = (SysreginfDataBean) sysreginfDao.list().get(0);
                SysterminalinfDataBean systerminalinfDataBean = systerminalinfDao.getSysterminalInfByUserid(jsonObject.getString("userid"));
                Date date = new Date(Long.parseLong(jsonObject.getString("date")));
                String logFlag = jsonObject.getString("logFlag");

                if(logFlag.equalsIgnoreCase("MCSLG")){      //机卡分离
                    SysabnormalDataBean sysabnormalDataBean = new SysabnormalDataBean();

//                    sysabnormalDataBean.setIdsystem(sysreginfDataBean.getPlatform_id());
                    sysabnormalDataBean.setIdsystem("22010001");
                    sysabnormalDataBean.setHappentime(new Timestamp(date.getTime()));
                    sysabnormalDataBean.setAbnormaltypecode("0006");
                    if(systerminalinfDataBean!=null){
                        sysabnormalDataBean.setIdalertmatter(Integer.parseInt(String.valueOf(systerminalinfDataBean.getId())));
                    }else{
                        sysabnormalDataBean.setIdalertmatter(000);
                    }
                    sysabnormalDataBean.setConnectobjectcode("logFlag001");
                    sysabnormalDataBean.setUsername(jsonObject.getString("username"));
                    sysabnormalDataBean.setExceptiondesc("终端用户"+jsonObject.getString("username")+"机卡分离," +
                            "手机串号为:"+jsonObject.getString("phonenetid")+",手机号码为:"+jsonObject.getString("phone")+
                            ",证书号位:"+jsonObject.getString("serialnumber")+",用户号码为:"+jsonObject.getString("userid"));

                    sysabnormalDataBean.setTreadresult("002");
                    sysabnormalDataBean.setWritetime(new Timestamp(new Date().getTime()));
                    sysabnormalDao.save(sysabnormalDataBean);
                }
                if(logFlag.equalsIgnoreCase("OEILG")){      //非法外联
                    SysabnormalDataBean sysabnormalDataBean = new SysabnormalDataBean();
                    sysabnormalDataBean.setHappentime(new Timestamp(date.getTime()));
//                    sysabnormalDataBean.setIdsystem(sysreginfDataBean.getPlatform_id());
                    sysabnormalDataBean.setIdsystem("22010001");
                    sysabnormalDataBean.setAbnormaltypecode("0004");
                    if(systerminalinfDataBean!=null){
                        sysabnormalDataBean.setIdalertmatter(Integer.parseInt(String.valueOf(systerminalinfDataBean.getId())));
                    }else{
                        sysabnormalDataBean.setIdalertmatter(000);
                    }
                    sysabnormalDataBean.setConnectobjectcode("001");
                    sysabnormalDataBean.setExceptiondesc("终端用户"+jsonObject.getString("user")+"非法外联," +
                            "手机串号为:"+jsonObject.getString("phonenetid")+",手机号码为:"+jsonObject.getString("phone")+
                            ",证书号位:"+jsonObject.getString("serialnumber")+",用户号码为:"+jsonObject.getString("userid")+",非法外联的url为:"+jsonObject.getString("accessUrls"));

                    sysabnormalDataBean.setTreadresult("002");
                    sysabnormalDataBean.setWritetime(new Timestamp(new Date().getTime()));
                    sysabnormalDao.save(sysabnormalDataBean);
                }
                if(logFlag.equalsIgnoreCase("OLRLG")){     //上线率
                    String userid = jsonObject.getString("userid");
                    IntherateDateBean intherateDateBean = intherateDao.getIntherateDateBeanByUserid(userid,date);
                    if(intherateDateBean==null){
                        intherateDateBean = new IntherateDateBean();
//                        intherateDateBean.setIdsystem(sysreginfDataBean.getPlatform_id());
                        intherateDateBean.setIdsystem("22010001");
                        intherateDateBean.setCity(jsonObject.getString("city"));
                        intherateDateBean.setInstitutions(jsonObject.getString("institutions"));
                        intherateDateBean.setIntime(new Timestamp(date.getTime()));
                        intherateDateBean.setLoginnum(1);
                        intherateDateBean.setOrganization(jsonObject.getString("organization"));
                        intherateDateBean.setProvince(jsonObject.getString("province"));
                        intherateDateBean.setSerialnumber(jsonObject.getString("serialnumber"));
//                        intherateDateBean.setUser(jsonObject.getString("user"));
                        intherateDateBean.setUser(jsonObject.getString("username"));
                        intherateDateBean.setUserid(jsonObject.getString("userid"));
                        intherateDao.saveIntherateDateBean(intherateDateBean);
                    }else{
                        int sum = intherateDateBean.getLoginnum()+1;
                        intherateDateBean.setLoginnum(sum);
                        intherateDao.saveIntherateDateBean(intherateDateBean);
                    }
                }
            }

            if(elog.getLog_info().contains("apptype")){
                String json = elog.getLog_info().substring(elog.getLog_info().indexOf("{"),elog.getLog_info().length());
                if(!elog.getLog_info().endsWith("\"}")){
                    json+="\"}";
                }
                System.out.println("json=="+json);
                JSONObject jsonObject = JSONObject.fromObject(json);
                Timestamp date =   Timestamp.valueOf(jsonObject.getString("date"));
                String logFlag = jsonObject.getString("apptype");
                if(logFlag.equalsIgnoreCase("sipproxy")){      //视频业务日志
                    BusinessLog businessLog=new BusinessLog();
                    businessLog.setBusiness_name(jsonObject.getString("appname"));
                    businessLog.setDest_ip(jsonObject.getString("destip"));
                    businessLog.setDest_port(jsonObject.getString("destport"));
                    businessLog.setLevel(jsonObject.getString("level"));
                    businessLog.setLog_time(date);
                    businessLog.setOperation(jsonObject.getString("opertion"));
//                    businessLog.setPlatform_name(jsonObject.getString("platformname"));
                    businessLog.setSource_dest(jsonObject.getString("sourceport"));
                    businessLog.setSource_ip(jsonObject.getString("sourceip"));
                    businessLog.setUser_name(jsonObject.getString("username"));
                    dao.saveBusinessLog(businessLog);

                    BusinessDataBean databean = BusinessPlatformService.datavideoset.getBusinessDataBeanByID(jsonObject.getString("appname"));
                    databean.setRecord_count( databean.getRecord_count() + 1);
                    databean.setXt_dataflow( databean.getXt_dataflow() + elog.getLog_info().length() );
                    if (jsonObject.getString("level").equalsIgnoreCase("warn")){
                        databean.setAlert_count( databean.getAlert_count() + 1);
                    }else if(jsonObject.getString("level").equalsIgnoreCase("error")) {
                        databean.setAlert_count( databean.getAlert_count() + 1);
                    }
                    databean.setConnect_count(Integer.parseInt(jsonObject.getString("curconnect")) );
//                    databean.setAction_time( databean.getAction_time() + Integer.parseInt(logs[i].getCurconnect()) );
                    BusinessPlatformService.datavideoset.returnBusinessDataBean(jsonObject.getString("appname"), databean);
                }
            }
        }
        TernimalAccessAuditDataBean ternimalAcceeAudit = log.getTenimalAccessLog();
        if (ternimalAcceeAudit != null) {
            terniAcceAuditDao.save(ternimalAcceeAudit);
        }
        TenimalOperationAuditDataBean ternimalOperationAudit = log.getTenimalOperationLog();
        if (ternimalOperationAudit != null) {
            terniAccOperationAuditDao.save(ternimalOperationAudit);
        }
        Equipment equipment = log.getEquipment();
        if (equipment != null) {
            equDAO.saveEquipment(equipment);
        }
        TOutlinkinfDataBean toutlink = log.getOutlink();
        if (toutlink != null) {
            BusinessPlatformService.dataset.I_Action_Time = log.getDelay();
            outlinkDao.saveOutlink(toutlink);
        }
    }

    public void processTerminalILogFormat(ILogFormat log) {
        TernimalAccessAuditDataBean blog = log.getTenimalAccessLog();
        if (blog != null) {

        }
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


    public void close() {

        isRun = false;
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


    public static void main(String arg[]) throws Exception {
        BusinessLogZDImp process = new BusinessLogZDImp();
        new Thread(process).start();
        while (true) {
            Thread.sleep(1000);
        }
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
        result.setCode(log.getCode());
        return result;

    }

    public AlertDataBean getAlertBeanByEquement(EquipmentLog log) {
        AlertDataBean result = new AlertDataBean();
        result.setCode(log.getCode());
        result.setIp(log.getIp());
        result.setName(log.getEquipment_name());
        result.setUsername(log.getEquipment_name());
        result.setAlert_time(log.getLog_time());
        result.setAlert_info(log.getLog_info());
        return result;

    }

}
