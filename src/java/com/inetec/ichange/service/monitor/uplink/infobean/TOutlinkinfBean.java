package com.inetec.ichange.service.monitor.uplink.infobean;

import com.inetec.ichange.service.monitor.uplink.databean.TOutlinkinfDataBean;
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
public class TOutlinkinfBean {

    private int Idconnlink;
    private String Idplatform;
    private int Idoutconnlink;
    private String outlinkvender;
    private String connectobject;
    private int outlinkbandwidth;
    private String outlinkdisc;
    private List<TOutlinkinfBean> beanlist;

    public int getIdconnlink() {
        return Idconnlink;
    }

    public void setIdconnlink(int idconnlink) {
        Idconnlink = idconnlink;
    }

    public String getIdplatform() {
        return Idplatform;
    }

    public void setIdplatform(String idplatform) {
        Idplatform = idplatform;
    }

    public int getIdoutconnlink() {
        return Idoutconnlink;
    }

    public void setIdoutconnlink(int idoutconnlink) {
        Idoutconnlink = idoutconnlink;
    }

    public String getOutlinkvender() {
        return outlinkvender;
    }

    public void setOutlinkvender(String outlinkvender) {
        this.outlinkvender = outlinkvender;
    }

    public String getConnectobject() {
        return connectobject;
    }

    public void setConnectobject(String connectobject) {
        this.connectobject = connectobject;
    }

    public int getOutlinkbandwidth() {
        return outlinkbandwidth;
    }

    public void setOutlinkbandwidth(int outlinkbandwidth) {
        this.outlinkbandwidth = outlinkbandwidth;
    }

    public String getOutlinkdisc() {
        return outlinkdisc;
    }

    public void setOutlinkdisc(String outlinkdisc) {
        this.outlinkdisc = outlinkdisc;
    }

    private Date Collecttime;


    public Date getCollecttime() {
        return Collecttime;
    }

    public void setCollecttime(Date collecttime) {
        Collecttime = collecttime;
    }

    public void dataProcess(List<TOutlinkinfDataBean> databean, int idlink, String idplatform) {
        this.Idplatform = idplatform;
        beanlist = new ArrayList<TOutlinkinfBean>();
        Iterator<TOutlinkinfDataBean> databeanIt = databean.iterator();
        while (databeanIt.hasNext()) {
            beanlist.add(dataProcessToBean(databeanIt.next(), idlink, idplatform));
        }


    }

    protected TOutlinkinfBean dataProcessToBean(TOutlinkinfDataBean databean, int idconnlink, String idplatform) {
        TOutlinkinfBean bean = new TOutlinkinfBean();
        bean.setIdplatform(idplatform);
        bean.setIdconnlink(idconnlink);
        bean.setIdoutconnlink(databean.getId());
        bean.setOutlinkvender(databean.getLink_Corp());
        bean.setOutlinkbandwidth(databean.getLink_bandwidth());
        bean.setConnectobject(databean.getLink_name());

        bean.setOutlinkdisc("VPDN");
        Date collecttime = new Date();
        collecttime.setYear(111);
        collecttime.setMonth(4);
        collecttime.setDate(1);
        bean.setCollecttime(collecttime);
        return bean;
    }

    protected StringBuffer beanToString(TOutlinkinfBean bean) {
        StringBuffer buffer = new StringBuffer();
        buffer.append(this.Idplatform);
        buffer.append(UpFileUtils.dataSpilt);
        buffer.append(bean.Idconnlink);
        buffer.append(UpFileUtils.dataSpilt);
        buffer.append(bean.Idoutconnlink);
        buffer.append(UpFileUtils.dataSpilt);
        buffer.append(bean.outlinkvender);
        buffer.append(UpFileUtils.dataSpilt);
        buffer.append(bean.connectobject);
        buffer.append(UpFileUtils.dataSpilt);
        buffer.append(bean.outlinkbandwidth);
        buffer.append(UpFileUtils.dataSpilt);
        buffer.append(bean.outlinkdisc);
        buffer.append(UpFileUtils.dataSpilt);
        buffer.append(UpFileUtils.getDateFormat(bean.Collecttime));
        buffer.append("\n");
        return buffer;
    }

    /**
     * 生成上报数据文件
     *
     * @return 文件名，包含全路径。
     */
    public String processFile(String type) {
        String fileName = new String();
        fileName = this.Idplatform + "-" + UpFileUtils.getDateFormatUp(System.currentTimeMillis()) + "-Toutlinkinf-" + type.toUpperCase();
        StringBuffer buffer = new StringBuffer();
        StringBuffer temp = new StringBuffer();
        Iterator<TOutlinkinfBean> it = beanlist.iterator();
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
        TOutlinkinfBean bean = new TOutlinkinfBean();
        System.setProperty(UpFileUtils.Str_monitor_Home, "c:\\");

        bean.testData(bean);
        bean.processFile("A");
        bean.testData(bean);
        bean.processFile("U");

    }


    protected TOutlinkinfBean testData(TOutlinkinfBean bean) {
        /*    bean.setIdplatform("P51160001");
        bean.setLinkName("内部链路");
        bean.setIdconnlink(2);
        bean.setBandwidth(10);
        bean.setConnectobjectcode("01000");
        bean.setBizOperatestylecode("0001");
        bean.setFirewall("无");
        bean.setTbsg("无");
        bean.setGap("无");
        bean.setVPN("无");*/
        Date collecttime = new Date();
        collecttime.setYear(111);
        collecttime.setMonth(3);
        collecttime.setDate(1);
        this.Collecttime = collecttime;
        return bean;
    }
}
