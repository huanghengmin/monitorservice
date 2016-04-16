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
public class TInlinkrunstateBean {
    private String Idplatform;
    private int Idconnlink;
    private String Runstatecode;
    private String Desc;
    private List<TInlinkrunstateBean> beanlist;

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

    public int getIdconnlink() {
        return Idconnlink;
    }

    public void setIdconnlink(int idconnlink) {
        Idconnlink = idconnlink;
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


    public void dataProcess(List<TInlinkinfDataBean> databean, String idplatform) {
        this.Idplatform = idplatform;
        beanlist = new ArrayList<TInlinkrunstateBean>();
        Iterator<TInlinkinfDataBean> databeanIt = databean.iterator();
        while (databeanIt.hasNext()) {
            beanlist.add(dataProcessToBean(databeanIt.next(), idplatform));
        }


    }

    protected TInlinkrunstateBean dataProcessToBean(TInlinkinfDataBean databean, String idplatform) {
        TInlinkrunstateBean bean = new TInlinkrunstateBean();
        bean.setIdplatform(idplatform);
        bean.setIdconnlink(databean.getId());
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
        fileName = this.Idplatform + "-" + UpFileUtils.getDateFormatUp(System.currentTimeMillis()) + "-Tinlinkrunstate-" + type.toUpperCase();
        StringBuffer buffer = new StringBuffer();
        StringBuffer temp = new StringBuffer();
        Iterator<TInlinkrunstateBean> it = beanlist.iterator();
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

    protected StringBuffer beanToString(TInlinkrunstateBean bean) {

        StringBuffer buffer = new StringBuffer();
        buffer.append(bean.Idplatform);
        buffer.append(UpFileUtils.dataSpilt);
        buffer.append(bean.Idconnlink);
        buffer.append(UpFileUtils.dataSpilt);
        buffer.append(bean.Runstatecode);
        buffer.append(UpFileUtils.dataSpilt);
        buffer.append(bean.Desc);
        buffer.append(UpFileUtils.dataSpilt);
        buffer.append(UpFileUtils.getFoundDayStart());
        buffer.append(UpFileUtils.dataSpilt);
        buffer.append(UpFileUtils.getFoundDayStart());

        buffer.append("\n");
        return buffer;
    }
}
