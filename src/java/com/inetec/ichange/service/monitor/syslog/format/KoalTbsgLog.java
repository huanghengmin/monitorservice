package com.inetec.ichange.service.monitor.syslog.format;

import com.inetec.ichange.service.monitor.databean.BusinessLog;
import com.inetec.ichange.service.monitor.databean.Equipment;
import com.inetec.ichange.service.monitor.databean.EquipmentLog;
import com.inetec.ichange.service.monitor.uplink.databean.*;
import org.apache.commons.lang.StringUtils;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author bluesky
 */
public class KoalTbsgLog implements ILogFormat {
    private static final String S_Logflag = "logflag=\"TBSGS\"";
    private static final String S_ServiceID = "serviceid=\"1\"";
    private static final String S_Separator_Keys = " ";
    private static final String S_Separator_KeyValue = "=";
    private static TbsgBeanDao dao = new TbsgBeanDao();
    /**
     * id
     */
    public String equid;
    /*
     *ip地址
     */
    public String ip;
    public String log;
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
    private String requrl;
    private String bytes;
    private String upbytes;
    private String serviceid;
    private String level;

    public String getRequrl() {
        return requrl;
    }

    public void setRequrl(String requrl) {
        this.requrl = requrl;
    }

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

    public void setTerminalid(String terminalid) {
        this.terminalid = terminalid;
    }

    public String getTime() {
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
                    time = (keyvalues[i].substring("time=".length()));

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
    public int getDelay() {
        return 0;  //To change body of implemented methods use File | Settings | File Templates.
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
            buslog.setLog_time(Timestamp.valueOf(time.substring(0,10)+" "+time.substring(11,19)));
        }else {
            buslog.setLog_time(Timestamp.valueOf(time));
        }
        buslog.setOperation("访问");
        if (getAccessreturn().equalsIgnoreCase("Y")) {
            buslog.setAudit_info("访问成功:"+getReason());
            return null;
        } else {
            buslog.setLevel("warn");
            buslog.setAudit_info("访问失败:"+getReason());
        }
        buslog.setStatus(0);

        return buslog;
    }

    public long getIn_Flux() {
        if (bytes != null && bytes.equalsIgnoreCase(""))
            return Long.parseLong(bytes);
        return 0;
    }

    public long getOut_Flux() {
        if (bytes != null && bytes.equalsIgnoreCase(""))
            return Long.parseLong(bytes);
        return 0;
    }

    @Override
    public EquipmentLog getEquipmentLog() {
        EquipmentLog log = new EquipmentLog();
        log.setEquipment_name(equid);
        log.setLog_time(new Timestamp(System.currentTimeMillis()));
        log.setLog_info(this.log);
        log.setLevel(level);
        log.setIp(ip);
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
        /*  TenimalOperationAuditDataBean buslog = new TenimalOperationAuditDataBean();
       buslog.setPolice_name(getUsername());
       buslog.setOperate_time(Date.valueOf(getTime()));
       buslog.setOperate_type("访问");
       return buslog;*/
        return null;
    }

    @Override
    public TernimalAccessAuditDataBean getTenimalAccessLog() {
        /* TernimalAccessAuditDataBean buslog = new TernimalAccessAuditDataBean();
       buslog.setAccess_time(Date.valueOf(getTime()));
       buslog.setMessage_level(level);
       buslog.setPolice_id(getIdentity());
       buslog.setPolice_name(getUsername());

       return buslog;*/
        return null;
    }

    public static void main(String arg[]) throws Exception{
       /* String logs = "logflag=\"TBSGS\" userip=\"191.168.191.7\" accessurl=\"http://192.168.1.8/\" orgcode=\"-\" username=\"-\" " +
                "identity=\"-\" accessreturn=\"Y\" reason=\"鎴愬姛\" tbsgip=\"192.168.190.7\" proxycn=\"-\" " +
                "terminalid=\"-\" time=\"2012-03-19 03:33:36\" bytes=\"8560\" upbytes=\"0\" serviceid=\"1\"";
//        String logs = "logflag=TBSGS"+ "userip=191.168.191.7" accessurl=\"http://192.168.1.8/\" orgcode=\"-\" username=\"-\" identity=\"-\" accessreturn=\"Y\" reason=\"鎴愬姛\" tbsgip=\"192.168.190.7\" proxycn=\"-\" terminalid=\"-\" time=\"2012-03-19 03:33:36\" bytes=\"8560\" upbytes=\"0\" serviceid=\"3\"";
        KoalTbsgLog log = new KoalTbsgLog();
        log.process(logs);
        TbsgBean tbsg=new TbsgBean();
        tbsg.setUserip(log.getUserip());
        tbsg.setAccessurl(log.getAccessurl());
        tbsg.setOrgcode(log.getOrgcode());
        tbsg.setUsername(log.getUsername());
        tbsg.setIdentity(log.getIdentity());
        tbsg.setAccessreturn(log.getAccessreturn());
        tbsg.setReason(log.getReason());
        tbsg.setTbsgip(log.getTbsgip());
        tbsg.setProxycn(log.getProxycn());
        tbsg.setTerminalid(log.getTerminalid());
        tbsg.setTime(Timestamp.valueOf(log.getTime()));
        tbsg.setBytes(log.getBytes());
        tbsg.setUpbytes(log.getUpbytes());
        tbsg.setServiceid(log.getServiceid());
        dao.save(tbsg);
        System.out.println("logflag=" + log.getLogflag() + " time="
                + log.getTime());
*/
//        Date date=new Date();
//        java.sql.Date date1=new java.sql.Date();
//        String time=(date).toString();
//        System.out.println("date=" + date + " time="
//                + time+"date1=" + date1 );
//        String dateStr = "2013-03-29T11:37:31+08:00";
//        System.out.println(dateStr.substring(0,10)+" "+dateStr.substring(11,19));
        String s="Apr 12 11:19:09 soft-dev006 logflag=\"TBSGS\" userip=\"192.168.1.214\" time=\"2013-04-12T11:02:52+08:00\" requrl=\"http://192.168.1.232:81/stp/login.jsp\" accessurl=\"http://10.0.0.2:81/stp/login.jsp\" orgcode=\"-\" username=\"-\" identity=\"-\" accessreturn=\"200\" reason=\"\" tbsgip=\"-\" proxycn=\"-\" terminalid=\"-\" status = \"200\" bytes=\"1298\" upbytes=\"213\" serviceid=\"3\" request=\"GET /stp/login.jsp HTTP/1.1\" http_host=\"192.168.1.232:81\" upstream_status=\"200\" http_referer=\"-\"  ssl_protocol=\"-\" ssl_cipher=\"-\" upstream_addr=\"10.0.0.2:81\" request_time=\"0.007\" upstream_response_time=\"0.007\" ";
        System.out.println(s.contains("logflag=\"TBSGS\""));;
    }




}
