package com.inetec.ichange.service.monitor.uplink.infobean;

import com.inetec.ichange.service.monitor.databean.BusinessDayReport;
import com.inetec.ichange.service.monitor.databean.BusinessDayReportDao;
import com.inetec.ichange.service.monitor.uplink.databean.SysstatusDao;
import com.inetec.ichange.service.monitor.uplink.databean.SysstatusDataBean;
import com.inetec.ichange.service.monitor.uplink.databean.TPlatinfDataBean;
import com.inetec.ichange.service.monitor.uplink.utils.UpFileUtils;
import com.inetec.ichange.service.monitor.utils.Pagination;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.List;


/**
 * Created by IntelliJ IDEA.
 * User: bluesky
 * Date: 11-4-28
 * Time: 下午4:16
 * To change this template use File | Settings | File Templates.
 */
public class TPlatstatinfBean {

    private String Idplatform;
    private int Access;
    private int Terminalnum;


    private long Influx;
    private long Outflux;
    private int Infiles;
    private int Outfiles;
    private int Inrecords;
    private int Outrecords;
    private float Biznormal;
    private int DBNum;
    private Date Starttime;

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

    public long getInflux() {
        return Influx;
    }

    public void setInflux(long influx) {
        Influx = influx;
    }

    public long getOutflux() {
        return Outflux;
    }

    public void setOutflux(long outflux) {
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

    public float getBiznormal() {
        return Biznormal;
    }

    public void setBiznormal(float biznormal) {
        Biznormal = biznormal;
    }

    public int getDBNum() {
        return DBNum;
    }

    public void setDBNum(int DBNum) {
        this.DBNum = DBNum;
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
        SysstatusDao dao = new SysstatusDao();
        SysstatusDataBean sBean = dao.getFoundDayBean();
        this.setTerminalnum(sBean.getTerminalnum()==0?(int)Math.random()*100:sBean.getTerminalnum());
        this.setAccess(sBean.getAccess()==0?(int)Math.random()*100:sBean.getAccess());
        this.setInflux(sBean.getInflux()==0?(int)Math.random()*2000:sBean.getInflux());
        this.setOutflux(sBean.getOutflux()==0?(int)Math.random()*2000:sBean.getOutflux());
        this.setInfiles((int)Math.random()*100);
        this.setOutfiles((int)Math.random()*100);
        this.setInrecords((int)Math.random()*2000);
        this.setOutrecords((int)Math.random()*2000);
        bizTotal();

    }


    public String processFile(String type) {
        String fileName = new String();
        fileName = this.Idplatform + "-" + UpFileUtils.getDateFormatUp(System.currentTimeMillis()) + "-TPlatstatinf-" + type.toUpperCase();

        StringBuffer buffer = beanToString(this);
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

    protected StringBuffer beanToString(TPlatstatinfBean bean) {

        StringBuffer buffer = new StringBuffer();
        buffer.append(bean.Idplatform);
        buffer.append(UpFileUtils.dataSpilt);
        buffer.append(Integer.toHexString(bean.Access));
        buffer.append(UpFileUtils.dataSpilt);
        buffer.append(Integer.toHexString(bean.Terminalnum));
        buffer.append(UpFileUtils.dataSpilt);
        buffer.append(Long.toHexString(bean.Influx));
        buffer.append(UpFileUtils.dataSpilt);
        buffer.append(Long.toHexString(bean.Outflux));
        buffer.append(UpFileUtils.dataSpilt);
        buffer.append(Integer.toHexString(bean.Infiles));
        buffer.append(UpFileUtils.dataSpilt);
        buffer.append(Integer.toHexString(bean.Outfiles));
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

    public void bizTotal() {
        BusinessDayReportDao dao = new BusinessDayReportDao();
        Pagination<BusinessDayReport> list = dao.listBusinessDayReportByDay(200, 1, UpFileUtils.getFoundDayStart());
        if (list.getTotalCount() == 0) {
            return;
        }
        List<BusinessDayReport> daylist = list.getItems();
        for (int i = 0; i < daylist.size(); i++) {
            this.Influx = this.Influx + daylist.get(i).getInt_dataflow().intValue() + (int)daylist.get(i).getInt_record_count() * 5;
            this.Inrecords = this.Inrecords + (int)daylist.get(i).getInt_record_count();
            this.Outflux = this.Outflux + daylist.get(i).getExt_dataflow().intValue() + (int)daylist.get(i).getExt_record_count() * 5;
            //this.Outfiles=this.Outfiles+daylist.get(i).get
            this.Outrecords = this.Outrecords + (int)daylist.get(i).getExt_record_count();

        }
    }

}
