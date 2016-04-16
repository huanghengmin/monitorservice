package com.inetec.ichange.service.monitor.uplink.databean;

import com.avdy.p4j.jdbc.model.Column;
import com.avdy.p4j.jdbc.model.Entity;

import java.sql.Timestamp;

/**
 * Created by IntelliJ IDEA.
 * User: gdw
 * Date: 13-4-11
 * Time: 下午3:41
 * To change this template use File | Settings | File Templates.
 */
@Entity(schema = "jksys", table = "tbsg")
public class TbsgBean {
    @Column(isPrimaryKey = true)
    private int id;
//    @Column
//    private String logflag; //是标志位
    @Column
    private String userip ; //是用户IP地址
    @Column
    private String accessurl ; //是用户访问的目标地址
    @Column
    private String orgcode ; //是组织机构代码
    @Column
    private String username ; //是用户名
    @Column
    private String identity ; //是用户的身份证
    @Column
    private String accessreturn ; //是访问结果 “N” 表示访问不成功, “Y” 表示访问成功
    @Column
    private String reason ; //是对访问结果的解释
    @Column
    private String tbsgip ; //是TBSG服务器自身的IP
    @Column
    private String  proxycn ; //是代理证书的CN项
    @Column
    private String terminalid ; //是终端的硬件信息
    @Column
    private Timestamp time ; //是访问时间
    @Column
    private String bytes ; //是下载字节数
    @Column
    private String  upbytes ; //是上传字节数
    @Column
    private String serviceid ; //是服务编号
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public Timestamp getTime() {
        return time;
    }

    public void setTime(Timestamp time) {
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
}
