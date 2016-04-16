package com.inetec.ichange.service.monitor.uplink.databean;

import com.avdy.p4j.jdbc.model.Column;
import com.avdy.p4j.jdbc.model.Entity;

import java.util.Date;

/**
 * Created by IntelliJ IDEA.
 * User: qxp
 * Date: 12-2-7
 * Time: pm 3:41
 * To change this template use File | Settings | File Templates.
 */
@Entity(schema = "jksys", table = "tenimal_list")
public class TenimalListDataBean {
     @Column(isPrimaryKey = true)
    private int id;
    @Column
    private String card_id;
    @Column
    private String cardType;
    @Column
    private String card_model;
    @Column
    private String card_version;
    @Column
    private Date card_certid_date;
    @Column
    private String userDepart;
    @Column
    private String userZone;
    @Column
    private String policeNumber;
    @Column
    private String police_name;
    @Column
    private String cert_id;
    @Column
    private Date certcreate_date;
    @Column
    private Date login_time;
    @Column
    private String block_type;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCard_id() {
        return card_id;
    }

    public void setCard_id(String card_id) {
        this.card_id = card_id;
    }

    public String getCardType() {
        return cardType;
    }

    public void setCardType(String cardType) {
        this.cardType = cardType;
    }

    public String getCard_model() {
        return card_model;
    }

    public void setCard_model(String card_model) {
        this.card_model = card_model;
    }

    public String getCard_version() {
        return card_version;
    }

    public void setCard_version(String card_version) {
        this.card_version = card_version;
    }

    public Date getCard_certid_date() {
        return card_certid_date;
    }

    public void setCard_certid_date(Date card_certid_date) {
        this.card_certid_date = card_certid_date;
    }

    public String getUserDepart() {
        return userDepart;
    }

    public void setUserDepart(String userDepart) {
        this.userDepart = userDepart;
    }

    public String getUserZone() {
        return userZone;
    }

    public void setUserZone(String userZone) {
        this.userZone = userZone;
    }

    public String getPoliceNumber() {
        return policeNumber;
    }

    public void setPoliceNumber(String policeNumber) {
        this.policeNumber = policeNumber;
    }

    public String getPolice_name() {
        return police_name;
    }

    public void setPolice_name(String police_name) {
        this.police_name = police_name;
    }

    public String getCert_id() {
        return cert_id;
    }

    public void setCert_id(String cert_id) {
        this.cert_id = cert_id;
    }

    public Date getCertcreate_date() {
        return certcreate_date;
    }

    public void setCertcreate_date(Date certcreate_date) {
        this.certcreate_date = certcreate_date;
    }

    public Date getLogin_time() {
        return login_time;
    }

    public void setLogin_time(Date login_time) {
        this.login_time = login_time;
    }

    public String getBlock_type() {
        return block_type;
    }

    public void setBlock_type(String block_type) {
        this.block_type = block_type;
    }
}
