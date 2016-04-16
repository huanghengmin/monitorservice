package com.inetec.ichange.service.monitor.syslog.format;

import java.io.UnsupportedEncodingException;
import java.sql.Timestamp;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.inetec.ichange.service.Service;
import com.inetec.ichange.service.monitor.databean.*;
import com.inetec.ichange.service.monitor.uplink.databean.TOutlinkinfDataBean;
import com.inetec.ichange.service.monitor.uplink.databean.TenimalOperationAuditDataBean;
import com.inetec.ichange.service.monitor.uplink.databean.TernimalAccessAuditDataBean;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

public class KoalVpnLog implements ILogFormat {
    private static final Logger logger = Logger.getLogger(KoalVpnLog.class);
    private static final String S_Logflag = "logflag=\"TBSGS\"";
    private static final String S_ServiceID = "serviceid=\"3\"";
    private static final String S_Separator_Keys = " ";
    private static final String S_Separator_KeyValue = "=";
    private static SysTerminalStatusRunDao dao = new SysTerminalStatusRunDao();

    private String log;
    private String logflag;
    private String userip;
    private String accessurl;
    private String orgcode;
    private String username;
    private String identity;
    private String accessreturn;
    private String reason;
    private String tbsgip;
    private String proxycn;
    private String terminalid;
    private String time;
    private String bytes;
    private String upbytes;
    private String serviceid;
    private String level;
    /**
     * id
     */
    public String equid;
    /*
     *ip地址
     */
    public String ip;

    public String getLogflag() {
        return logflag;
    }

    public void setLogflag(String logflag) {
        this.logflag = logflag;
    }

    public String getUserip() {
        return userip;
    }

    public void setUserip(String userip) {
        this.userip = userip;
    }

    public String getAccessurl() {
        return accessurl;
    }

    public void setAccessurl(String accessurl) {
        this.accessurl = accessurl;
    }

    public String getOrgcode() {
        return orgcode;
    }

    public void setOrgcode(String orgcode) {
        this.orgcode = orgcode;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getIdentity() {
        return identity;
    }

    public void setIdentity(String identity) {
        this.identity = identity;
    }

    public String getAccessreturn() {
        return accessreturn;
    }

    public void setAccessreturn(String accessreturn) {
        this.accessreturn = accessreturn;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getTbsgip() {
        return tbsgip;
    }

    public void setTbsgip(String tbsgip) {
        this.tbsgip = tbsgip;
    }

    public String getProxycn() {
        return proxycn;
    }

    public void setProxycn(String proxycn) {
        this.proxycn = proxycn;
    }

    public String getTerminalid() {
        return terminalid;
    }

    @Override
    public int getDelay() {
        return 0;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public void setTerminalid(String terminalid) {
        this.terminalid = terminalid;
    }

    public String getTime() {
        if (time.endsWith("" +
                "\""))
            time = time.substring(0, time.length() - 1);
        if (time.startsWith("\"")) {
            time = time.substring(1);
        }
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getBytes() {
        return bytes;
    }

    public void setBytes(String bytes) {
        this.bytes = bytes;
    }

    public String getUpbytes() {
        return upbytes;
    }

    public void setUpbytes(String upbytes) {
        this.upbytes = upbytes;
    }

    public String getServiceid() {
        return serviceid;
    }

    public void setServiceid(String serviceid) {
        this.serviceid = serviceid;
    }

    public void process(String log) {
        // TODO Auto-generated method stub
        this.log = log;
        if (validate(log)) {

            String[] keyvalues = log.split(S_Separator_Keys);
            for (int i = 0; i < keyvalues.length; i++) {
                if (keyvalues[i].startsWith("deviceid=")) {
                    equid = keyvalues[i].substring("deviceid=".length());
                }
                if (keyvalues[i].startsWith("ip=")) {
                    ip = keyvalues[i].substring("ip=".length());
                }
                if (keyvalues[i].startsWith("logflag=")) {
                    logflag = keyvalues[i].substring("logflag=".length());
                    if (logflag.endsWith("" +
                            "\""))
                        logflag = logflag.substring(0, logflag.length() - 1);
                    if (logflag.startsWith("\"")) {
                        logflag = logflag.substring(1);
                    }
                }
                if (keyvalues[i].startsWith("userip=")) {
                    userip = keyvalues[i].substring("userip=".length());
                    if (userip.endsWith("" +
                            "\""))
                        userip = userip.substring(0, userip.length() - 1);
                    if (userip.startsWith("\"")) {
                        userip = userip.substring(1);
                    }
                }
                if (keyvalues[i].startsWith("accessurl=")) {
                    accessurl = keyvalues[i].substring("accessurl=".length());
                    if (accessurl.endsWith("" +
                            "\""))
                        accessurl = accessurl.substring(0, accessurl.length() - 1);
                    if (accessurl.startsWith("\"")) {
                        accessurl = accessurl.substring(1);
                    }
                }
                if (keyvalues[i].startsWith("orgcode=")) {
                    orgcode = keyvalues[i].substring("orgcode=".length());
                    if (orgcode.endsWith("" +
                            "\""))
                        orgcode = orgcode.substring(0, orgcode.length() - 1);
                    if (orgcode.startsWith("\"")) {
                        orgcode = orgcode.substring(1);
                    }
                }
                if (keyvalues[i].startsWith("username=")) {
                    username = keyvalues[i].substring("username=".length());
                    if (username.endsWith("" +
                            "\""))
                        username = username.substring(0, username.length() - 1);
                    if (username.startsWith("\"")) {
                        username = username.substring(1);
                    }
                }
                if (keyvalues[i].startsWith("identity=")) {
                    identity = keyvalues[i].substring("identity=".length());
                    if (identity.endsWith("" +
                            "\""))
                        identity = identity.substring(0, identity.length() - 1);
                    if (identity.startsWith("\"")) {
                        identity = identity.substring(1);
                    }

                }
                if (keyvalues[i].startsWith("accessreturn=")) {
                    accessreturn = keyvalues[i].substring("accessreturn="
                            .length());
                    if (accessreturn.endsWith("" +
                            "\""))
                        accessreturn = accessreturn.substring(0, accessreturn.length() - 1);
                    if (accessreturn.startsWith("\"")) {
                        accessreturn = accessreturn.substring(1);
                    }
                }
                if (keyvalues[i].startsWith("reason=")) {
                    reason = keyvalues[i].substring("reason=".length());
                    if (reason.endsWith("" +
                            "\""))
                        reason = reason.substring(0, reason.length() - 1);
                    if (reason.startsWith("\"")) {
                        reason = reason.substring(1);
                    }
                }
                if (keyvalues[i].startsWith("tbsgip=")) {
                    tbsgip = keyvalues[i].substring("tbsgip=".length());
                    if (tbsgip.endsWith("" +
                            "\""))
                        tbsgip = tbsgip.substring(0, tbsgip.length() - 1);
                    if (tbsgip.startsWith("\"")) {
                        tbsgip = tbsgip.substring(1);
                    }
                }
                if (keyvalues[i].startsWith("proxycn=")) {
                    proxycn = keyvalues[i].substring("proxycn=".length());
                    if (proxycn.endsWith("" +
                            "\""))
                        proxycn = proxycn.substring(0, proxycn.length() - 1);
                    if (proxycn.startsWith("\"")) {
                        proxycn = proxycn.substring(1);
                    }
                }
                if (keyvalues[i].startsWith("terminalid=")) {
                    terminalid = keyvalues[i].substring("terminalid=".length());
                    if (terminalid.endsWith("" +
                            "\""))
                        terminalid = terminalid.substring(0, terminalid.length() - 1);
                    if (terminalid.startsWith("\"")) {
                        terminalid = terminalid.substring(1);
                    }
                }
                if (keyvalues[i].startsWith("time=")) {

//                    time = (keyvalues[i].substring("time=".length()) + " " + keyvalues[i + 1]);
//                    i++;
                    time = (keyvalues[i].substring("time=".length()) );
                    if (time.endsWith("" +
                            "\""))
                        time = time.substring(0, time.length() - 1);
                    if (time.startsWith("\"")) {
                        time = time.substring(1);
                    }
                }
                if (keyvalues[i].startsWith("bytes=")) {
                    bytes = keyvalues[i].substring("bytes="
                            .length());
                    if (bytes.endsWith("" +
                            "\""))
                        bytes = bytes.substring(0, bytes.length() - 1);
                    if (bytes.startsWith("\"")) {
                        bytes = bytes.substring(1);
                    }

                }
                if (keyvalues[i].startsWith("upbytes=")) {
                    upbytes = keyvalues[i].substring("upbytes="
                            .length());
                    if (upbytes.endsWith("" +
                            "\""))
                        upbytes = upbytes.substring(0, upbytes.length() - 1);
                    if (upbytes.startsWith("\"")) {
                        upbytes = upbytes.substring(1);
                    }
                }
                if (keyvalues[i].startsWith("serviceid=")) {
                    serviceid = keyvalues[i].substring("serviceid=".length());
                    if (serviceid.endsWith("" +
                            "\""))
                        serviceid = serviceid.substring(0, serviceid.length() - 1);
                    if (serviceid.startsWith("\"")) {
                        serviceid = serviceid.substring(1);
                    }
                }

            }
        }

    }

    @Override
    public void process(String log, String level) {
        this.level = level;
        process(log);
        //terminalAccessURL process
        List<TerminalAccessUrl> list = new TerminalAccessUrlDao().listNoAccessUrl(200, 1);
        logger.info("terminalAccessUrl list:" + list.size());
        for (int i = 0; i < list.size(); i++) {
            TerminalAccessUrl turl = list.get(i);
            logger.info("terminalAccessUrl is:" + turl.getAccessUrl());
            logger.info("user request is:" + getAccessurl());
            if (getAccessurl().startsWith(turl.getAccessUrl())) {
                SysterminalinfDataBean bean = Service.terminainfService.terminalCache.getBeanByUserId(identity);
                bean.setWarncount(bean.getWarncount() + 1);
                logger.info("user warncount is:" + bean.getWarncount() + " userid:" + identity);
                Service.terminainfService.terminalCache.updateBean(identity, bean);
            }
        }
    }

    @Override
    public boolean validate(String log) {
        boolean result = false;


        if (StringUtils.containsIgnoreCase(log, S_Logflag)
                && StringUtils.containsIgnoreCase(log, S_ServiceID))
            result = true;
        return result;
    }

    @Override
    public BusinessLog getBussinessLog() {
        BusinessLog buslog = new BusinessLog();
        buslog.setBusiness_name("bsquery");
        buslog.setSource_ip(getUserip());
        buslog.setUser_name(getUsername());
        buslog.setDest_ip(getAccessurl());
        buslog.setLevel(level);
        if(time.contains("+")){
            buslog.setLog_time(Timestamp.valueOf(getTime().substring(0,10)+" "+getTime().substring(11,19)));
        }else {
            buslog.setLog_time(Timestamp.valueOf(getTime()));
        }
//        buslog.setLog_time(Timestamp.valueOf(getTime()));
        buslog.setOperation("访问");

        if (getAccessreturn().equalsIgnoreCase("Y")||getAccessreturn().equalsIgnoreCase("200")) {
            buslog.setAudit_info("访问成功:" + getReason());
        } else {
            if (getReason().startsWith("未修改")) {
                buslog.setAudit_info("访问成功:" + getReason());
            } else {
                buslog.setLevel("warn");
                buslog.setAudit_info("访问失败:" + getReason());
                buslog.setCode("0004");
            }
        }
        buslog.setStatus(0);

        return buslog;
    }

    public long getIn_Flux() {
        if (bytes != null && !bytes.equalsIgnoreCase(""))
            return Long.parseLong(bytes);
        return 0;
    }

    public long getOut_Flux() {
        if (upbytes != null && !upbytes.equalsIgnoreCase(""))
            return Long.parseLong(upbytes);
        return 0;
    }

    @Override
    public EquipmentLog getEquipmentLog() {
        EquipmentLog log = new EquipmentLog();
        log.setEquipment_name(equid);
        if(time.contains("+")){          //time 的格式String dateStr = "2013-03-29T11:37:31+08:00";
            log.setLog_time(Timestamp.valueOf(getTime().substring(0,10)+" "+getTime().substring(11,19)));
        }else {
            log.setLog_time(Timestamp.valueOf(getTime()));
        }
//        log.setLog_time(Timestamp.valueOf(getTime()));
        log.setLog_info(this.log);
        log.setIp(ip);
        log.setLevel(level);
        if (level.equalsIgnoreCase("warn") || level.equalsIgnoreCase("error"))
            return log;
        else
            return log;

    }

    @Override
    public Equipment getEquipment() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public TOutlinkinfDataBean getOutlink() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public TenimalOperationAuditDataBean getTenimalOperationLog() {
        /* TenimalOperationAuditDataBean buslog = new TenimalOperationAuditDataBean();
       buslog.setPolice_name(getUsername());
       buslog.setOperate_time(Timestamp.valueOf(getTime()));
       buslog.setOperate_type("访问:" + getReason());
       return buslog;*/
        return null;
    }

    @Override
    public TernimalAccessAuditDataBean getTenimalAccessLog() {
        TernimalAccessAuditDataBean buslog = new TernimalAccessAuditDataBean();
        SysTerminalStatusRunBean bean1 = new SysTerminalStatusRunBean();
        SysterminalinfDataBean bean = Service.terminainfService.terminalCache.getBeanByUserId(identity);
        if (bean != null) {
            buslog.setAccess_time(Timestamp.valueOf(getTime()));
            buslog.setMessage_level(level);
            if (bean.getPoliceNumber() != null)
                buslog.setPolice_id(bean.getPoliceNumber());
            else
                buslog.setPolice_id(bean.getUserId());
            if (bean.getUserName() != null)
                buslog.setPolice_name(bean.getUserName());
            else {
                buslog.setPolice_name(" ");
            }
            buslog.setCount(1);
            buslog.setUserdepart(bean.getUserDepart());
            buslog.setUserzone(bean.getUserZone());
            buslog.setBusname("bsquery");
            buslog.setFlux((int) getIn_Flux());
            buslog.setUserid(identity);
            buslog.setDesc(getReason());
            buslog.setCard_type(bean.getCardType());
            bean.setOut_flux(bean.getOut_flux() + getOut_Flux());
            bean.setIn_flux(bean.getIn_flux() + getIn_Flux());
            bean.setCount(bean.getCount() + 1);
            bean1.setAccessUrl(getAccessurl());
            bean1.setAuditDate(new Timestamp(System.currentTimeMillis()));
            bean1.setBusName("bsquery");
            bean1.setCardType(bean.getCardType());
            bean1.setFlux((int) (bean.getOut_flux() + getOut_Flux()));
            bean1.setCount(bean.getCount());
            bean1.setIdTerminal((int) bean.getIdTerminal());
            bean1.setUserdepart(bean.getUserDepart());
            bean1.setUserZone(bean.getUserZone());
            bean1.setUserId(identity);
            bean1.setPoliceNumber(bean.getPoliceNumber());
            dao.save(bean1);
            Service.terminainfService.terminalCache.updateBean(identity, bean);
        } else {
            return null;
        }
        return buslog;
    }


    public static void main(String arg[]) throws Exception {
        String logs = "deviceid=vpn ip=192.168.20.20 logflag=\"TBSGS\" userip=\"171.168.1.1\" accessurl=\"http://192.168.30.30/\" orgcode=\"-\" username=\"????1\" identity=\"123456789012345678\" accessreturn=\"Y\" reason=\"?????????????\" tbsgip=\"192.168.20.20\" proxycn=\"-\" terminalid=\"-\" time=\"2012-04-12 11:49:27\" bytes=\"333\" upbytes=\"2222\" serviceid=\"3\" ";
        KoalVpnLog log = new KoalVpnLog();
        log.process(logs, "info");
        SysTerminalStatusRunBean bean1 = new SysTerminalStatusRunBean();
        bean1.setAccessUrl(log.getAccessurl());
        bean1.setAuditDate(new Timestamp(System.currentTimeMillis()));
        bean1.setBusName("bsquery");
        bean1.setCardType("111");
        bean1.setFlux(10);
        bean1.setCount(1);
        bean1.setIdTerminal(10);
        bean1.setUserdepart("14000");
        bean1.setUserZone("310000");
        bean1.setUserId("777777777888888888");
        bean1.setPoliceNumber("1111");
        dao.save(bean1);

         System.out.println("logflag=" + log.getLogflag() + " time="  + log.getTime());


    }

}
