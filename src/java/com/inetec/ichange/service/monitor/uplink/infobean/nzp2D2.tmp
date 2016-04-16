package com.inetec.ichange.service.monitor.uplink.infobean;

import com.inetec.ichange.service.monitor.uplink.databean.TPlatinfDataBean;
import com.inetec.ichange.service.monitor.uplink.utils.UpFileUtils;

import java.io.UnsupportedEncodingException;
import java.util.Date;

/**
 * Created by IntelliJ IDEA.
 * User: bluesky
 * Date: 11-4-28
 * Time: 下午4:16
 * To change this template use File | Settings | File Templates.
 */
public class TPlatreginfBean {

    private int InnerLinkNum;
    private String Idplatform;
    private int OutLinkNum;
    private int BizNum;
    private int BizTypeNum;
    private int Bandwidth;
    private int UnitNum;
    private int TerminalNum;
    private int NodeNum;
    private int DBNum;
    private Date Starttime;

    public int getInnerLinkNum() {
        return InnerLinkNum;
    }

    public void setInnerLinkNum(int innerLinkNum) {
        InnerLinkNum = innerLinkNum;
    }

    public String getIdplatform() {
        return Idplatform;
    }

    public void setIdplatform(String idplatform) {
        Idplatform = idplatform;
    }

    public int getOutLinkNum() {
        return OutLinkNum;
    }

    public void setOutLinkNum(int outLinkNum) {
        OutLinkNum = outLinkNum;
    }

    public int getBizNum() {
        return BizNum;
    }

    public void setBizNum(int bizNum) {
        BizNum = bizNum;
    }

    public int getBizTypeNum() {
        return BizTypeNum;
    }

    public void setBizTypeNum(int bizTypeNum) {
        BizTypeNum = bizTypeNum;
    }

    public int getBandwidth() {
        return Bandwidth;
    }

    public void setBandwidth(int bandwidth) {
        Bandwidth = bandwidth;
    }

    public int getUnitNum() {
        return UnitNum;
    }

    public void setUnitNum(int unitNum) {
        UnitNum = unitNum;
    }

    public int getTerminalNum() {
        return TerminalNum;
    }

    public void setTerminalNum(int terminalNum) {
        TerminalNum = terminalNum;
    }

    public int getNodeNum() {
        return NodeNum;
    }

    public void setNodeNum(int nodeNum) {
        NodeNum = nodeNum;
    }

    public int getDBNum() {
        return DBNum;
    }

    public void setDBNum(int DBNum) {
        this.DBNum = DBNum;
    }

    public Date getStarttime() {
        return Starttime;
    }

    public void setStarttime(Date starttime) {
        Starttime = starttime;
    }

    public Date getEndtime() {
        return Endtime;
    }

    public void setEndtime(Date endtime) {
        Endtime = endtime;
    }

    private Date Endtime;

    public void dataProcess(TPlatinfDataBean bean) {
        this.Idplatform = bean.getPlatform_id();
        this.InnerLinkNum = 2;
        this.OutLinkNum = 2;
        this.BizNum = 2;
        this.BizTypeNum = 2;
        this.UnitNum = 2;
        this.TerminalNum = 1;
        this.NodeNum = 11;
        this.DBNum = 0;

    }

    public String processFile(String type) {
        String fileName = new String();
        fileName = this.Idplatform + "-" + UpFileUtils.getDateFormatUp(System.currentTimeMillis()) + "-TPlatreginf-" + type.toUpperCase();

        StringBuffer buffer = new StringBuffer();
        buffer.append(this.Idplatform);
        buffer.append(UpFileUtils.dataSpilt);
        buffer.append(Integer.toHexString(InnerLinkNum));
        buffer.append(UpFileUtils.dataSpilt);
        buffer.append(Integer.toHexString(this.OutLinkNum));
        buffer.append(UpFileUtils.dataSpilt);
        buffer.append(Integer.toHexString(this.BizNum));
        buffer.append(UpFileUtils.dataSpilt);
        buffer.append(Integer.toHexString(this.BizTypeNum));
        buffer.append(UpFileUtils.dataSpilt);
        buffer.append(Integer.toHexString(this.UnitNum));
        buffer.append(UpFileUtils.dataSpilt);
        buffer.append(Integer.toHexString(this.TerminalNum));
        buffer.append(UpFileUtils.dataSpilt);
        buffer.append(Integer.toHexString(this.NodeNum));
        buffer.append(UpFileUtils.dataSpilt);
        buffer.append(Integer.toHexString(this.DBNum));
        buffer.append(UpFileUtils.dataSpilt);
        buffer.append(UpFileUtils.getFoundDayStart());
        buffer.append(UpFileUtils.dataSpilt);
        buffer.append(UpFileUtils.getFoundDayEnd());

        buffer.append("\n");

        StringBuffer temp = new StringBuffer();
        temp.append("00000001-");
        try {
            temp.append(UpFileUtils.getlength(buffer.toString().getBytes(UpFileUtils.charset).length));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
        temp.append("-" + type.toUpperCase() + "\n");
        temp.append(buffer);
        temp.append(UpFileUtils.EndDataString);
        new UpFileUtils().process(fileName, temp);
        new UpFileUtils().processFinish(fileName + ".finished");
        return UpFileUtils.getDataPath() + fileName;
    }
}
