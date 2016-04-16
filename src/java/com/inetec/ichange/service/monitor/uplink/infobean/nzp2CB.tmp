package com.inetec.ichange.service.monitor.uplink.infobean;

import com.inetec.ichange.service.monitor.uplink.databean.TInlinkinfDataBean;
import com.inetec.ichange.service.monitor.uplink.utils.UpFileUtils;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: bluesky
 * Date: 11-4-28
 * Time: 下午4:16
 * To change this template use File | Settings | File Templates.
 */
public class TInlinkinfBean {
    private int Idconnlink;
    private String LinkName;
    private String Idplatform;
    private String Connectobjectcode;
    private String BizOperatestylecode;
    private int Bandwidth;
    private String Firewall;
    private String Tbsg;
    private String Gap;
    private String VPN;
    private String OtherSecurity;

    private Date Collecttime;

    private List<TInlinkinfBean> beanlist;

    public int getIdconnlink() {
        return Idconnlink;
    }

    public void setIdconnlink(int idconnlink) {
        Idconnlink = idconnlink;
    }

    public String getLinkName() {
        return LinkName;
    }

    public void setLinkName(String linkName) {
        LinkName = linkName;
    }

    public String getConnectobjectcode() {
        return Connectobjectcode;
    }

    public void setConnectobjectcode(String connectobjectcode) {
        Connectobjectcode = connectobjectcode;
    }

    public String getBizOperatestylecode() {
        return BizOperatestylecode;
    }

    public void setBizOperatestylecode(String bizOperatestylecode) {
        BizOperatestylecode = bizOperatestylecode;
    }

    public int getBandwidth() {
        return Bandwidth;
    }

    public void setBandwidth(int bandwidth) {
        Bandwidth = bandwidth;
    }

    public String getFirewall() {
        return Firewall;
    }

    public void setFirewall(String firewall) {
        Firewall = firewall;
    }

    public String getTbsg() {
        return Tbsg;
    }

    public void setTbsg(String tbsg) {
        Tbsg = tbsg;
    }

    public String getVPN() {
        return VPN;
    }

    public void setVPN(String VPN) {
        this.VPN = VPN;
    }

    public String getGap() {
        return Gap;
    }

    public void setGap(String gap) {
        Gap = gap;
    }

    public String getOtherSecurity() {
        return OtherSecurity;
    }

    public void setOtherSecurity(String otherSecurity) {
        OtherSecurity = otherSecurity;
    }

    public String getIdplatform() {
        return Idplatform;
    }

    public void setIdplatform(String idplatform) {
        Idplatform = idplatform;
    }


    public Date getCollecttime() {
        return Collecttime;
    }

    public void setCollecttime(Date collecttime) {
        Collecttime = collecttime;
    }

    public void dataProcess(List<TInlinkinfDataBean> databean, String idplatform) {
        this.Idplatform = idplatform;
        beanlist = new ArrayList<TInlinkinfBean>();
        Iterator<TInlinkinfDataBean> databeanIt = databean.iterator();
        while (databeanIt.hasNext()) {
            beanlist.add(dataProcessToBean(databeanIt.next(), idplatform));
        }


    }

    protected TInlinkinfBean dataProcessToBean(TInlinkinfDataBean databean, String idplatform) {
        TInlinkinfBean bean = new TInlinkinfBean();
        bean.setIdplatform(idplatform);
        bean.setIdconnlink(databean.getId());
        bean.setLinkName(databean.getLink_name());
        //this.LinkName = databean.getLink_name();
        bean.setConnectobjectcode("01");
        //this.Connectobjectcode = databean.getJrdx();
        bean.setBizOperatestylecode("0100");
        //this.BizOperatestylecode = databean.getExchange_mode();
        bean.setBandwidth(databean.getLink_bandwidth());
        //this.Bandwidth = databean.getLink_bandwidth();
        if (databean.getFW_used().equalsIgnoreCase("n")) {
            //this.Firewall = "无";
            bean.setFirewall("无");
        } else {
            bean.setFirewall("联想网御Power V-4226");
        }
        if (databean.getSec_gateway_used().equalsIgnoreCase("n")) {
            bean.setTbsg("无");
            // this.Tbsg = "无";
        } else {
            bean.setTbsg("中宇万通NBS-4020");
        }
        if (databean.getVPN_used().equalsIgnoreCase("n")) {
            bean.setVPN("无");
            //this.VPN = "无";
        }
        if (databean.getGap_used().equalsIgnoreCase("n")) {
            bean.setGap("无");
        } else {
            bean.setGap("联想网御SIS-3000-GE11");
        }
        if (bean.getOtherSecurity() != null)
            bean.setOtherSecurity(databean.getOther_security());
        //this.OtherSecurity = databean.getOther_security();

        Date collecttime = new Date();
        collecttime.setYear(111);
        collecttime.setMonth(3);
        collecttime.setDate(1);
        bean.setCollecttime(collecttime);
        return bean;
    }

    /**
     * 生成上报数据文件
     *
     * @return 文件名，包含全路径。
     */
    public String processFile(String type) {
        String fileName = new String();
        fileName = this.Idplatform + "-" + UpFileUtils.getDateFormatUp(System.currentTimeMillis()) + "-TInlinkinf-" + type.toUpperCase();
        StringBuffer buffer = new StringBuffer();
        StringBuffer temp = new StringBuffer();
        Iterator<TInlinkinfBean> it = beanlist.iterator();
        while (it.hasNext()) {
            buffer.append(beanToString(it.next()));
        }
        temp.append(UpFileUtils.getlength(beanlist.size()));
        temp.append("-");
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

    public static void main(String arg[]) throws Exception {
        TInlinkinfBean bean = new TInlinkinfBean();
        System.setProperty(UpFileUtils.Str_monitor_Home, "c:\\");

        bean.testData(bean);
        bean.processFile("A");
        bean.testData(bean);
        bean.processFile("U");

    }

    protected StringBuffer beanToString(TInlinkinfBean bean) {

        StringBuffer buffer = new StringBuffer();

        buffer.append(Integer.toHexString(bean.Idconnlink));
        buffer.append(UpFileUtils.dataSpilt);
        buffer.append(bean.LinkName);
        buffer.append(UpFileUtils.dataSpilt);
        buffer.append(bean.Idplatform);
        buffer.append(UpFileUtils.dataSpilt);
        buffer.append(bean.Connectobjectcode);
        buffer.append(UpFileUtils.dataSpilt);
        buffer.append(bean.BizOperatestylecode);
        buffer.append(UpFileUtils.dataSpilt);
        buffer.append(bean.Bandwidth);
        buffer.append(UpFileUtils.dataSpilt);
        buffer.append(bean.Firewall);
        buffer.append(UpFileUtils.dataSpilt);
        buffer.append(bean.Tbsg);
        buffer.append(UpFileUtils.dataSpilt);
        buffer.append(bean.Gap);
        buffer.append(UpFileUtils.dataSpilt);
        buffer.append(bean.VPN);
        buffer.append(UpFileUtils.dataSpilt);
        if (bean.OtherSecurity != null && !bean.OtherSecurity.equalsIgnoreCase(""))
            buffer.append(bean.OtherSecurity);
        buffer.append(UpFileUtils.dataSpilt);
        buffer.append(UpFileUtils.getDateFormat(bean.Collecttime));
        buffer.append("\n");
        return buffer;
    }


    protected TInlinkinfBean testData(TInlinkinfBean bean) {
        bean.setIdplatform("P51160001");
        bean.setLinkName("内部链路");
        bean.setIdconnlink(2);
        bean.setBandwidth(10);
        bean.setConnectobjectcode("01000");
        bean.setBizOperatestylecode("0001");
        bean.setFirewall("无");
        bean.setTbsg("无");
        bean.setGap("无");
        bean.setVPN("无");
        Date collecttime = new Date();
        collecttime.setYear(111);
        collecttime.setMonth(3);
        collecttime.setDate(1);
        this.Collecttime = collecttime;
        return bean;
    }
}
