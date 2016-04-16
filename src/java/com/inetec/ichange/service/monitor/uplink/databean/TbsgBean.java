package com.inetec.ichange.service.monitor.uplink.databean;

import com.avdy.p4j.jdbc.model.Column;
import com.avdy.p4j.jdbc.model.Entity;

import java.sql.Timestamp;

/**
 * Created by IntelliJ IDEA.
 * User: gdw
 * Date: 13-4-11
 * Time: ����3:41
 * To change this template use File | Settings | File Templates.
 */
@Entity(schema = "jksys", table = "tbsg")
public class TbsgBean {
    @Column(isPrimaryKey = true)
    private int id;
//    @Column
//    private String logflag; //�Ǳ�־λ
    @Column
    private String userip ; //���û�IP��ַ
    @Column
    private String accessurl ; //���û����ʵ�Ŀ���ַ
    @Column
    private String orgcode ; //����֯��������
    @Column
    private String username ; //���û���
    @Column
    private String identity ; //���û������֤
    @Column
    private String accessreturn ; //�Ƿ��ʽ�� ��N�� ��ʾ���ʲ��ɹ�, ��Y�� ��ʾ���ʳɹ�
    @Column
    private String reason ; //�ǶԷ��ʽ���Ľ���
    @Column
    private String tbsgip ; //��TBSG�����������IP
    @Column
    private String  proxycn ; //�Ǵ���֤���CN��
    @Column
    private String terminalid ; //���ն˵�Ӳ����Ϣ
    @Column
    private Timestamp time ; //�Ƿ���ʱ��
    @Column
    private String bytes ; //�������ֽ���
    @Column
    private String  upbytes ; //���ϴ��ֽ���
    @Column
    private String serviceid ; //�Ƿ�����
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
