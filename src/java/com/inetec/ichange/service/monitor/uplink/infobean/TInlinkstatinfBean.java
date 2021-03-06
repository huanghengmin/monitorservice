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
 * To change this template use File | Settings | File Templates.
 */
public class TInlinkstatinfBean {
    private String Idplatform;
    private int Idconnlink;
    private int Access;
    private int Terminalnum;


    private int Influx;
    private int Outflux;
    private int Infiles;
    private int Outfiles;
    private int Inrecords;
    private int Outrecords;
    private Date Starttime;
    private List<TInlinkstatinfBean> beanlist;

    public int getIdconnlink() {
        return Idconnlink;
    }

    public void setIdconnlink(int idconnlink) {
        Idconnlink = idconnlink;
    }

    public Date getStarttime() {
        return Starttime;
    }


    public String getIdplatform() {
        return Idplatform;
    }

    public void setIdplatform(String idplatform) {
        Idplatform = idplatform;
    }


    public int getTerminalnum() {
        return Terminalnum;
    }

    public void setTerminalnum(int terminalnum) {
        Terminalnum = terminalnum;
    }

    public int getAccess() {
        return Access;
    }

    public void setAccess(int access) {
        Access = access;
    }

    public int getInflux() {
        return Influx;
    }

    public void setInflux(int influx) {
        Influx = influx;
    }

    public int getOutflux() {
        return Outflux;
    }

    public void setOutflux(int outflux) {
        Outflux = outflux;
    }

    public int getInfiles() {
        return Infiles;
    }

    public void setInfiles(int infiles) {
        Infiles = infiles;
    }

    public int getOutfiles() {
        return Outfiles;
    }

    public void setOutfiles(int outfiles) {
        Outfiles = outfiles;
    }

    public int getInrecords() {
        return Inrecords;
    }

    public void setInrecords(int inrecords) {
        Inrecords = inrecords;
    }

    public int getOutrecords() {
        return Outrecords;
    }

    public void setOutrecords(int outrecords) {
        Outrecords = outrecords;
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

    public void dataProcess(List<TInlinkinfDataBean> databean, String idplatform) {
        this.Idplatform = idplatform;
        beanlist = new ArrayList<TInlinkstatinfBean>();
        Iterator<TInlinkinfDataBean> databeanIt = databean.iterator();
        while (databeanIt.hasNext()) {
            beanlist.add(dataProcessToBean(databeanIt.next(), idplatform));
        }


    }

    protected TInlinkstatinfBean dataProcessToBean(TInlinkinfDataBean databean, String idplatform) {
        TInlinkstatinfBean bean = new TInlinkstatinfBean();
        bean.setIdplatform(idplatform);
        bean.setIdconnlink(databean.getId());
        bean.setTerminalnum(0);
        bean.setAccess(0);
        bean.setInflux(0);
        bean.setOutflux(0);
        bean.setInfiles(0);
        bean.setOutfiles(0);
        bean.setInrecords(0);
        bean.setOutrecords(0);

        return bean;
    }


    public String processFile(String type) {
        String fileName = new String();
        fileName = this.Idplatform + "-" + UpFileUtils.getDateFormatUp(System.currentTimeMillis()) + "-Tinlinkstatinf-" + type.toUpperCase();
        StringBuffer buffer = new StringBuffer();
        StringBuffer temp = new StringBuffer();
        Iterator<TInlinkstatinfBean> it = beanlist.iterator();
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

    protected StringBuffer beanToString(TInlinkstatinfBean bean) {
        StringBuffer buffer = new StringBuffer();
        buffer.append(bean.Idplatform);
        buffer.append(UpFileUtils.dataSpilt);
        buffer.append(bean.Idconnlink);
        buffer.append(UpFileUtils.dataSpilt);
        buffer.append(Integer.toHexString(bean.Access));
        buffer.append(UpFileUtils.dataSpilt);
        buffer.append(Integer.toHexString(bean.Terminalnum));
        buffer.append(UpFileUtils.dataSpilt);
        buffer.append(Integer.toHexString(bean.Influx));
        buffer.append(UpFileUtils.dataSpilt);
        buffer.append(Integer.toHexString(bean.Outflux));
        buffer.append(UpFileUtils.dataSpilt);
        buffer.append(Integer.toHexString(bean.Influx));
        buffer.append(UpFileUtils.dataSpilt);
        buffer.append(Integer.toHexString(bean.Outflux));
        buffer.append(UpFileUtils.dataSpilt);
        buffer.append(Integer.toHexString(bean.Inrecords));
        buffer.append(UpFileUtils.dataSpilt);
        buffer.append(Integer.toHexString(bean.Outrecords));
        buffer.append(UpFileUtils.dataSpilt);
        buffer.append(UpFileUtils.getFoundDayStart());
        buffer.append(UpFileUtils.dataSpilt);
        buffer.append(UpFileUtils.getFoundDayEnd());
        buffer.append("\n");
        return buffer;
    }
}
