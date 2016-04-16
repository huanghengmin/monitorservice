package com.inetec.ichange.service.monitor.uplink.infobean;

import com.inetec.ichange.service.monitor.uplink.databean.TPlatinfDataBean;
import com.inetec.ichange.service.monitor.uplink.utils.UpFileUtils;

import java.io.UnsupportedEncodingException;
import java.util.Date;

/**
 * Created by IntelliJ IDEA.
 * User: bluesky
 * Date: 11-4-28
 * Time: ‰∏ãÂçà4:16
 * To change this template use File | Settings | File Templates.
 */
public class TPlatrunstateBean {

    private String Idplatform;
    private String Runstatecode;
    private String Desc;

    private Date Starttime;


    public Date getStarttime() {
        return Starttime;
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


    public void dataProcess(TPlatinfDataBean bean) {
        this.Idplatform = bean.getPlatform_id();
        this.Runstatecode = "001";
        this.Desc = "’˝≥£";

    }

    public String processFile(String type) {
        String fileName = new String();
        fileName = this.Idplatform + "-" + UpFileUtils.getDateFormatUp(System.currentTimeMillis()) + "-TPlatrunstate-" + type.toUpperCase();

        StringBuffer buffer = new StringBuffer();
        buffer.append(this.Idplatform);
        buffer.append(UpFileUtils.dataSpilt);
        buffer.append(this.Runstatecode);
        buffer.append(UpFileUtils.dataSpilt);
        buffer.append(this.Desc);
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
