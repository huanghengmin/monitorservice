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
@Entity(schema = "jksys", table = "systerminalinfo")
public class SysterminalinfDataBean {
     @Column(isPrimaryKey = true)
    private long id;
    @Column
    private String idTerminal;
    @Column
    private String terminalName;
    @Column
    private String terminalType;
    @Column
    private String termianlOutlink;
    @Column
    private String termianlos;
    @Column
    private String termianlBand;
    @Column
    private String cardType;
    @Column
    private String cardname;
    @Column
    private String card_version;
    @Column
    private String userId;
    @Column
    private String  userName;
    @Column
    private String userDepart;
    @Column
    private String userZone;
    @Column
    private String policeNumber;
    @Column
    private Date regTime;
    @Column
    private String ifcancel;
    @Column
    private String flag;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }



    public String getIdTerminal() {
        return idTerminal;
    }

    public void setIdTerminal(String idTerminal) {
        this.idTerminal = idTerminal;
    }

    public String getTerminalName() {
        return terminalName;
    }

    public void setTerminalName(String terminalName) {
        this.terminalName = terminalName;
    }

    public String getTerminalType() {
        return terminalType;
    }

    public void setTerminalType(String terminalType) {
        this.terminalType = terminalType;
    }

    public String getTerminalOutLink() {
        return termianlOutlink;
    }

    public void setTerminalOutLink(String terminalOutLink) {
        this.termianlOutlink = terminalOutLink;
    }

    public String getTerminalOS() {
        return termianlos;
    }

    public void setTerminalOS(String terminalOS) {
        this.termianlos = terminalOS;
    }

    public String getTerminalBand() {
        return termianlBand;
    }

    public void setTerminalBand(String terminalBand) {
        this.termianlBand = terminalBand;
    }

    public String getCardType() {
        return cardType;
    }

    public void setCardType(String cardType) {
        this.cardType = cardType;
    }

    public String getCardName() {
        return cardname;
    }

    public void setCardName(String cardName) {
        this.cardname = cardName;
    }

    public String getCard_version() {
        return card_version;
    }

    public void setCard_version(String card_version) {
        this.card_version = card_version;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
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

    public Date getRegTime() {
        return regTime;
    }

    public void setRegTime(Date regTime) {
        this.regTime = regTime;
    }

    public String getIfcancel() {
        return ifcancel;
    }

    public void setIfcancel(String ifcancel) {
        this.ifcancel = ifcancel;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }
}
