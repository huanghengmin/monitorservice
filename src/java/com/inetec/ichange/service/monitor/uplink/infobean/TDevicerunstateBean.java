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
public class TDevicerunstateBean {
    private String Idplatform;
    private int Iddevice;

    private String Runstatecode;
    private String Desc;

    private List<TDevicerunstateBean> beanlist;

    public int getIddevice() {
        return Iddevice;
    }

    public void setIddevice(int iddevice) {
        Iddevice = iddevice;
    }

    public String getRunstatecode() {
        return Runstatecode;
    }

    public void setRunstatecode(String runstatecode) {
        Runstatecode = runstatecode;
    }

    public String getDesc() {
        return Desc;
    }

    public void setDesc(String desc) {
        Desc = desc;
    }


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


    public void dataProcess(List<Equipment> databean, String idplatform) {
        this.Idplatform = idplatform;
        beanlist = new ArrayList<TDevicerunstateBean>();
        Iterator<Equipment> databeanIt = databean.iterator();
        while (databeanIt.hasNext()) {
            beanlist.add(dataProcessToBean(databeanIt.next(), idplatform));
        }
    }

    protected TDevicerunstateBean dataProcessToBean(Equipment databean, String idplatform) {
        TDevicerunstateBean bean = new TDevicerunstateBean();
        bean.setIdplatform(idplatform);
        bean.setIddevice(databean.getId());
        bean.setRunstatecode("001");
        bean.setDesc("正常");
        return bean;
    }

    /**
     * 生成上报数据文件
     *
     * @return 文件名，包含全路径。
     */
    public String processFile(String type) {
        String fileName = new String();
        fileName = this.Idplatform + "-" + UpFileUtils.getDateFormatUp(System.currentTimeMillis()) + "-Tdevicerunstate-" + type.toUpperCase();
        StringBuffer buffer = new StringBuffer();
        StringBuffer temp = new StringBuffer();
        Iterator<TDevicerunstateBean> it = beanlist.iterator();
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

    protected StringBuffer beanToString(TDevicerunstateBean bean, String type, int index) {

        StringBuffer buffer = new StringBuffer();
        buffer.append(bean.Idplatform);
        buffer.append(UpFileUtils.dataSpilt);
        buffer.append(bean.Iddevice);
        buffer.append(UpFileUtils.dataSpilt);
        buffer.append(bean.Runstatecode);
        buffer.append(UpFileUtils.dataSpilt);
        buffer.append(bean.Desc);
        buffer.append(UpFileUtils.dataSpilt);
        buffer.append(UpFileUtils.getFoundDayStart());
        buffer.append(UpFileUtils.dataSpilt);
        buffer.append(UpFileUtils.getFoundDayEnd());
        buffer.append("\n");
        return buffer;
    }
}
