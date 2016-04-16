package com.inetec.ichange.service.monitor.uplink.databean;

import com.avdy.p4j.jdbc.model.Column;
import com.avdy.p4j.jdbc.model.Entity;

import java.sql.Timestamp;
import java.util.Date;

/**
 * Created by IntelliJ IDEA.
 * User: Ç®ÏþÅÎ
 * Date: 12-2-7
 * Time: ÏÂÎç3:41
 * To change this template use File | Settings | File Templates.
 */
@Entity(schema = "jksys", table = "business_register")
public class SysbizRegisterDataBean {
     @Column(isPrimaryKey = true)
    private int id;
    @Column
    private String business_name;
    @Column
    private String business_depart;
     @Column
    private String business_code;
     @Column
    private String link_name;
     @Column
    private String business_exch_model;
     @Column
    private String business_admin;
    @Column
    private String admin_phone;
    @Column
    private String admin_email;
    @Column
    private String admin_other_phone;
     @Column
    private String auth_depart;
     @Column
    private Timestamp auth_date;
     @Column
    private String auth_serial;
     @Column
    private byte[] auth_material; //
     @Column
    private Timestamp register_date;
     @Column
    private String exchange_direction;
     @Column
    private String exch_protocol;
    @Column
    private String is_realtime;
    @Column
    private int day_datavolume;
    @Column
    private String is_approved;
    @Column
    private String approved_depart;
    @Column
    private byte[] tp_graph;
    @Column
    private String business_protocol;
    @Column
    private String protocol_desc;
    @Column
    private String s_ip_range;
    @Column
    private String s_port_range;
    @Column
    private String d_ip_range;
    @Column
    private String d_port_range;
    @Column
    private String use_depart;
    @Column
    private String use_depart_type;
    @Column
    private String use_depart_address;
    @Column
    private String use_depart_admin_name;
    @Column
    private String use_depart_admin_phone;
    @Column
    private String use_depart_admin_email;
    @Column
    private String use_depart_admin_other_phone;
    @Column
    private int terminal_amount;
    @Column
    private int user_amount;
    @Column
    private String business_desc;
    @Column
    private String auth_material_file_name;
    @Column
    private String tp_graph_file_name;
    @Column
    private String enablereport;

    public String getEnablereport() {
        return enablereport;
    }

    public void setEnablereport(String enablereport) {
        this.enablereport = enablereport;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBusiness_name() {
        return business_name;
    }

    public void setBusiness_name(String business_name) {
        this.business_name = business_name;
    }

    public String getBusiness_depart() {
        return business_depart;
    }

    public void setBusiness_depart(String business_depart) {
        this.business_depart = business_depart;
    }

    public String getBusiness_code() {
        return business_code;
    }

    public void setBusiness_code(String business_code) {
        this.business_code = business_code;
    }

    public String getLink_name() {
        return link_name;
    }

    public void setLink_name(String link_name) {
        this.link_name = link_name;
    }

    public String getBusiness_exch_model() {
        return business_exch_model;
    }

    public void setBusiness_exch_model(String business_exch_model) {
        this.business_exch_model = business_exch_model;
    }

    public String getBusiness_admin() {
        return business_admin;
    }

    public void setBusiness_admin(String business_admin) {
        this.business_admin = business_admin;
    }

    public String getAdmin_phone() {
        return admin_phone;
    }

    public void setAdmin_phone(String admin_phone) {
        this.admin_phone = admin_phone;
    }

    public String getAuth_depart() {
        return auth_depart;
    }

    public void setAuth_depart(String auth_depart) {
        this.auth_depart = auth_depart;
    }

    public Timestamp getAuth_date() {
        return auth_date;
    }

    public void setAuth_date(Timestamp auth_date) {
        this.auth_date = auth_date;
    }

    public String getAuth_serial() {
        return auth_serial;
    }

    public void setAuth_serial(String auth_serial) {
        this.auth_serial = auth_serial;
    }

    public byte[] getAuth_material() {
        return auth_material;
    }

    public void setAuth_material(byte[] auth_material) {
        this.auth_material = auth_material;
    }

    public Timestamp getRegister_date() {
        return register_date;
    }

    public void setRegister_date(Timestamp register_date) {
        this.register_date = register_date;
    }

    public String getExchange_direction() {
        return exchange_direction;
    }

    public void setExchange_direction(String exchange_direction) {
        this.exchange_direction = exchange_direction;
    }

    public String getExch_protocol() {
        return exch_protocol;
    }

    public void setExch_protocol(String exch_protocol) {
        this.exch_protocol = exch_protocol;
    }

    public String getIs_realtime() {
        return is_realtime;
    }

    public void setIs_realtime(String is_realtime) {
        this.is_realtime = is_realtime;
    }

    public int getDay_datavolume() {
        return day_datavolume;
    }

    public void setDay_datavolume(int day_datavolume) {
        this.day_datavolume = day_datavolume;
    }

    public String getIs_approved() {
        return is_approved;
    }

    public void setIs_approved(String is_approved) {
        this.is_approved = is_approved;
    }

    public String getApproved_depart() {
        return approved_depart;
    }

    public void setApproved_depart(String approved_depart) {
        this.approved_depart = approved_depart;
    }

    public byte[] getTp_graph() {
        return tp_graph;
    }

    public void setTp_graph(byte[] tp_graph) {
        this.tp_graph = tp_graph;
    }

    public String getBusiness_protocol() {
        return business_protocol;
    }

    public void setBusiness_protocol(String business_protocol) {
        this.business_protocol = business_protocol;
    }

    public String getProtocol_desc() {
        return protocol_desc;
    }

    public void setProtocol_desc(String protocol_desc) {
        this.protocol_desc = protocol_desc;
    }

    public String getS_ip_range() {
        return s_ip_range;
    }

    public void setS_ip_range(String s_ip_range) {
        this.s_ip_range = s_ip_range;
    }

    public String getS_port_range() {
        return s_port_range;
    }

    public void setS_port_range(String s_port_range) {
        this.s_port_range = s_port_range;
    }

    public String getD_ip_range() {
        return d_ip_range;
    }

    public void setD_ip_range(String d_ip_range) {
        this.d_ip_range = d_ip_range;
    }

    public String getD_port_range() {
        return d_port_range;
    }

    public void setD_port_range(String d_port_range) {
        this.d_port_range = d_port_range;
    }

    public String getUse_depart() {
        return use_depart;
    }

    public void setUse_depart(String use_depart) {
        this.use_depart = use_depart;
    }

    public String getUse_depart_type() {
        return use_depart_type;
    }

    public void setUse_depart_type(String use_depart_type) {
        this.use_depart_type = use_depart_type;
    }

    public String getUse_depart_address() {
        return use_depart_address;
    }

    public void setUse_depart_address(String use_depart_address) {
        this.use_depart_address = use_depart_address;
    }

    public String getUse_depart_admin_name() {
        return use_depart_admin_name;
    }

    public void setUse_depart_admin_name(String use_depart_admin_name) {
        this.use_depart_admin_name = use_depart_admin_name;
    }

    public String getUse_depart_admin_phone() {
        return use_depart_admin_phone;
    }

    public void setUse_depart_admin_phone(String use_depart_admin_phone) {
        this.use_depart_admin_phone = use_depart_admin_phone;
    }

    public String getUse_depart_admin_email() {
        return use_depart_admin_email;
    }

    public void setUse_depart_admin_email(String use_depart_admin_email) {
        this.use_depart_admin_email = use_depart_admin_email;
    }
    public int getTerminal_amount() {
        return terminal_amount;
    }

    public void setTerminal_amount(int terminal_amount) {
        this.terminal_amount = terminal_amount;
    }

    public int getUser_amount() {
        return user_amount;
    }

    public void setUser_amount(int user_amount) {
        this.user_amount = user_amount;
    }

    public String getBusiness_desc() {
        return business_desc;
    }

    public void setBusiness_desc(String business_desc) {
        this.business_desc = business_desc;
    }

    public String getAuth_material_file_name() {
        return auth_material_file_name;
    }

    public void setAuth_material_file_name(String auth_material_file_name) {
        this.auth_material_file_name = auth_material_file_name;
    }

    public String getTp_graph_file_name() {
        return tp_graph_file_name;
    }

    public void setTp_graph_file_name(String tp_graph_file_name) {
        this.tp_graph_file_name = tp_graph_file_name;
    }

    public String getAdmin_email() {
        return admin_email;
    }

    public void setAdmin_email(String admin_email) {
        this.admin_email = admin_email;
    }

    public String getAdmin_other_phone() {
        return admin_other_phone;
    }

    public void setAdmin_other_phone(String admin_other_phone) {
        this.admin_other_phone = admin_other_phone;
    }

    public String getUse_depart_admin_other_phone() {
        return use_depart_admin_other_phone;
    }

    public void setUse_depart_admin_other_phone(String use_depart_admin_other_phone) {
        this.use_depart_admin_other_phone = use_depart_admin_other_phone;
    }
}
