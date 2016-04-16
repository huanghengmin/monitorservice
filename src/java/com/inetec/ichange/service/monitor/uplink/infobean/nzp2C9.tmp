package com.inetec.ichange.service.monitor.uplink.infobean;

import com.inetec.ichange.service.monitor.databean.Equipment;
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
public class TDeviceinfBean {
    private String Idplatform;
    private int Idconnlink;
    private String areacode;
    private int Iddevice;
    private String devicedesc;
    private String devicetypecode;
    private String deviceIP;
    private String brandtyoe;
    private String linkphone;
    private String otherlink;
    private String deviceconf;

    private Date Collecttime;

    private List<TDeviceinfBean> beanlist;

    public String getIdplatform() {
        return Idplatform;
    }

    public void setIdplatform(String idplatform) {
        Idplatform = idplatform;
    }

    public int getIdconnlink() {
        return Idconnlink;
    }

    public void setIdconnlink(int idconnlink) {
        Idconnlink = idconnlink;
    }

    public String getAreacode() {
        return areacode;
    }

    public void setAreacode(String areacode) {
        this.areacode = areacode;
    }

    public int getIddevice() {
        return Iddevice;
    }

    public void setIddevice(int iddevice) {
        Iddevice = iddevice;
    }

    public String getDevicedesc() {
        return devicedesc;
    }

    public void setDevicedesc(String devicedesc) {
        this.devicedesc = devicedesc;
    }

    public String getDevicetypecode() {
        return devicetypecode;
    }

    public void setDevicetypecode(String devicetypecode) {
        this.devicetypecode = devicetypecode;
    }

    public String getDeviceIP() {
        return deviceIP;
    }

    public void setDeviceIP(String deviceIP) {
        this.deviceIP = deviceIP;
    }

    public String getBrandtyoe() {
        return brandtyoe;
    }

    public void setBrandtyoe(String brandtyoe) {
        this.brandtyoe = brandtyoe;
    }

    public String getLinkphone() {
        return linkphone;
    }

    public void setLinkphone(String linkphone) {
        this.linkphone = linkphone;
    }

    public String getOtherlink() {
        return otherlink;
    }

    public void setOtherlink(String otherlink) {
        this.otherlink = otherlink;
    }

    public String getDeviceconf() {
        return deviceconf;
    }

    public void setDeviceconf(String deviceconf) {
        this.deviceconf = deviceconf;
    }

    public Date getCollecttime() {
        return Collecttime;
    }

    public void setCollecttime(Date collecttime) {
        Collecttime = collecttime;
    }

    public void dataProcess(List<Equipment> databean, String idplatform) {
        this.Idplatform = idplatform;
        beanlist = new ArrayList<TDeviceinfBean>();
        Iterator<Equipment> databeanIt = databean.iterator();
        while (databeanIt.hasNext()) {
            beanlist.add(dataProcessToBean(databeanIt.next(), idplatform));
        }


    }

    protected TDeviceinfBean dataProcessToBean(Equipment databean, String idplatform) {
        TDeviceinfBean bean = new TDeviceinfBean();
        bean.setIdplatform(idplatform);
        bean.setIdconnlink(2);
        bean.setAreacode("03");
        bean.setIddevice(databean.getId());
        bean.setDevicedesc(databean.getEqu_name());
        bean.setDevicetypecode(databean.getEqu_type());
        bean.setDeviceIP(databean.getIp());
        bean.setBrandtyoe(databean.getManufacturer() + "/" + databean.getModel());
        bean.setLinkphone(databean.getEqu_phone());
        bean.setOtherlink(databean.getOther_phone());
        bean.setDeviceconf(null);
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
        fileName = this.Idplatform + "-" + UpFileUtils.getDateFormatUp(System.currentTimeMillis()) + "-Tdeviceinf-" + type.toUpperCase();
        StringBuffer buffer = new StringBuffer();
        StringBuffer temp = new StringBuffer();
        Iterator<TDeviceinfBean> it = beanlist.iterator();
        int index = 1;
        while (it.hasNext()) {
            buffer.append(beanToString(it.next(), type, 1));
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

    protected StringBuffer beanToString(TDeviceinfBean bean, String type, int index) {

        StringBuffer buffer = new StringBuffer();
        buffer.append(bean.Idplatform);
        buffer.append(UpFileUtils.dataSpilt);
        buffer.append(bean.Idconnlink);
        buffer.append(UpFileUtils.dataSpilt);
        buffer.append(bean.areacode);
        buffer.append(UpFileUtils.dataSpilt);
        buffer.append(bean.Iddevice);
        buffer.append(UpFileUtils.dataSpilt);
        if (bean.devicedesc != null)
            buffer.append(bean.devicedesc);
        buffer.append(UpFileUtils.dataSpilt);
        buffer.append(bean.devicetypecode);
        buffer.append(UpFileUtils.dataSpilt);
        buffer.append(bean.deviceIP);
        buffer.append(UpFileUtils.dataSpilt);
        buffer.append(bean.brandtyoe);
        buffer.append(UpFileUtils.dataSpilt);
        if (bean.linkphone != null)
            buffer.append(bean.linkphone);
        buffer.append(UpFileUtils.dataSpilt);
        if (bean.getOtherlink() != null)
            buffer.append(bean.otherlink);
        buffer.append(UpFileUtils.dataSpilt);
        if (bean.deviceconf != null && !bean.deviceconf.equalsIgnoreCase("")) {
            buffer.append(bean.deviceconf);

            String deviceconf = bean.deviceconf;
            bean.deviceconf = bean.Idplatform + "-" + UpFileUtils.getDateFormatUp(System.currentTimeMillis()) +
                    "-" + type.toUpperCase() + "-201-" + index + ".zip";
            new UpFileUtils().zipProcess(bean.deviceconf, deviceconf);
            new UpFileUtils().processFinish(bean.deviceconf + ".finished");
            buffer.append(bean.deviceconf);
        }
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
