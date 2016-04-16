package com.inetec.ichange.service.monitor.uplink.databean;

import com.avdy.p4j.jdbc.model.Column;
import com.avdy.p4j.jdbc.model.Entity;
import com.inetec.ichange.service.monitor.databean.BaseDataBean;

import java.util.Date;


@Entity(schema = "jksys", table = "jbqk")
public class TPlatinfDataBean {
    @Column(isPrimaryKey = true)
    private int id;
    @Column
    private String platform_name;
    @Column
    private String platform_id;
    @Column
    private String system_class;
    @Column
    private String address;
    @Column
    private String fzr_name;
    @Column
    private String fzr_phone;
    @Column
    private String fzr_email;
    @Column
    private String fzr_other_phone;
    @Column
    private String jksys_ip;
    @Column
    private String jsdw;
    @Column
    private String zycjdw;
    @Column
    private Date js_day;
    @Column
    private String bmxy;
    @Column
    private String spdw;
    @Column
    private Date sp_day;
    @Column
    private String spph;
    @Column
    private byte[] spcl;
    @Column
    private byte[] technology_solution;
    @Column
    private byte[] Confidential_Agreement;
    @Column
    private String main_comp;
    @Column
    private String maintainer_name;
    @Column
    private String maintainer_email;
    @Column
    private String maintainer_phone;
    @Column
    private String maintainer_other_phone;
    @Column
    private byte[] platform_tp;
    @Column
    private String spcl_file_name;
    @Column
    private String technology_solution_file_name;
    @Column
    private String confidential_agreement_file_name;
    @Column
    private String platform_tp_file_name;
    @Column
    private String jksys_mac;

    public String getZycjdw() {
        return zycjdw;
    }

    public void setZycjdw(String zycjdw) {
        this.zycjdw = zycjdw;
    }

    public Date getJs_day() {
        return js_day;
    }

    public void setJs_day(Date js_day) {
        this.js_day = js_day;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPlatform_name() {
        return platform_name;
    }

    public void setPlatform_name(String platform_name) {
        this.platform_name = platform_name;
    }

    public String getPlatform_id() {
        return platform_id;
    }

    public void setPlatform_id(String platform_id) {
        this.platform_id = platform_id;
    }

    public String getAddress() {
        return address;
    }

    public String getFzr_name() {
        return fzr_name;
    }

    public void setFzr_name(String fzr_name) {
        this.fzr_name = fzr_name;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getManager() {
        return fzr_name;
    }

    public void setManager(String manager) {
        this.fzr_name = manager;
    }

    public String getFzr_phone() {
        return fzr_phone;
    }

    public void setFzr_phone(String fzr_phone) {
        this.fzr_phone = fzr_phone;
    }

    public String getFzr_email() {
        return fzr_email;
    }

    public void setFzr_email(String fzr_email) {
        this.fzr_email = fzr_email;
    }

    public String getFzr_other_phone() {
        return fzr_other_phone;
    }

    public void setFzr_other_phone(String fzr_other_phone) {
        this.fzr_other_phone = fzr_other_phone;
    }

    public String getJksys_ip() {
        return jksys_ip;
    }

    public void setJksys_ip(String jksys_ip) {
        this.jksys_ip = jksys_ip;
    }

    public String getJsdw() {
        return jsdw;
    }

    public void setJsdw(String jsdw) {
        this.jsdw = jsdw;
    }

    public String getBmxy() {
        return bmxy;
    }

    public void setBmxy(String bmxy) {
        this.bmxy = bmxy;
    }

    public Date getSp_day() {
        return sp_day;
    }

    public void setSp_day(Date sp_day) {
        this.sp_day = sp_day;
    }

    public String getSpdw() {
        return spdw;
    }

    public void setSpdw(String spdw) {
        this.spdw = spdw;
    }

    public String getSpph() {
        return spph;
    }

    public void setSpph(String spph) {
        this.spph = spph;
    }

    public String getMain_comp() {
        return main_comp;
    }

    public void setMain_comp(String main_comp) {
        this.main_comp = main_comp;
    }

    public String getMaintainer_name() {
        return maintainer_name;
    }

    public void setMaintainer_name(String maintainer_name) {
        this.maintainer_name = maintainer_name;
    }

    public String getMaintainer_phone() {
        return maintainer_phone;
    }

    public void setMaintainer_phone(String maintainer_phone) {
        this.maintainer_phone = maintainer_phone;
    }

    public String getMaintainer_email() {
        return maintainer_email;
    }

    public void setMaintainer_email(String maintainer_email) {
        this.maintainer_email = maintainer_email;
    }

    public String getMaintainer_other_phone() {
        return maintainer_other_phone;
    }

    public void setMaintainer_other_phone(String maintainer_other_phone) {
        this.maintainer_other_phone = maintainer_other_phone;
    }

    public String getSpcl_file_name() {
        return spcl_file_name;
    }

    public void setSpcl_file_name(String spcl_file_name) {
        this.spcl_file_name = spcl_file_name;
    }

    public String getTechnology_solution_file_name() {
        return technology_solution_file_name;
    }

    public void setTechnology_solution_file_name(String technology_solution_file_name) {
        this.technology_solution_file_name = technology_solution_file_name;
    }

    public String getConfidential_agreement_file_name() {
        return confidential_agreement_file_name;
    }

    public void setConfidential_agreement_file_name(String confidential_agreement_file_name) {
        this.confidential_agreement_file_name = confidential_agreement_file_name;
    }

    public String getPlatform_tp_file_name() {
        return platform_tp_file_name;
    }

    public void setPlatform_tp_file_name(String platform_tp_file_name) {
        this.platform_tp_file_name = platform_tp_file_name;
    }

    public byte[] getSpcl() {
        return spcl;
    }

    public void setSpcl(byte[] spcl) {
        this.spcl = spcl;
    }

    public byte[] getTechnology_solution() {
        return technology_solution;
    }

    public void setTechnology_solution(byte[] technology_solution) {
        this.technology_solution = technology_solution;
    }

    public byte[] getConfidential_Agreement() {
        return Confidential_Agreement;
    }

    public void setConfidential_Agreement(byte[] confidential_Agreement) {
        Confidential_Agreement = confidential_Agreement;
    }

    public byte[] getPlatform_tp() {
        return platform_tp;
    }

    public void setPlatform_tp(byte[] platform_tp) {
        this.platform_tp = platform_tp;
    }

    public String getJksys_mac() {
        return jksys_mac;
    }

    public void setJksys_mac(String jksys_mac) {
        this.jksys_mac = jksys_mac;
    }

    public String getSystem_class() {
        return system_class;
    }

    public void setSystem_class(String system_class) {
        this.system_class = system_class;
    }
}
