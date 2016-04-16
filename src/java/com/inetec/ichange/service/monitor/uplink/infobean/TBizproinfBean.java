package com.inetec.ichange.service.monitor.uplink.infobean;

import com.inetec.ichange.service.monitor.databean.BusinessRegister;
import com.inetec.ichange.service.monitor.uplink.databean.TProtocolDao;
import com.inetec.ichange.service.monitor.uplink.databean.TProtocolDataBean;
import com.inetec.ichange.service.monitor.uplink.utils.UpFileUtils;
import com.inetec.ichange.service.monitor.utils.Pagination;

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
public class TBizproinfBean {

    private String Idplatform;
    private int Idbiz;
    private int Idpro;
    private String protocolname;
    private String Protocolcode;
    private String srcip;
    private String destip;
    private String srcport;
    private String destport;
    private Date Collecttime;

    private List<TBizproinfBean> beanlist;

    public String getIdplatform() {
        return Idplatform;
    }

    public void setIdplatform(String idplatform) {
        Idplatform = idplatform;
    }

    public int getIdbiz() {
        return Idbiz;
    }

    public void setIdbiz(int idbiz) {
        Idbiz = idbiz;
    }

    public int getIdpro() {
        return Idpro;
    }

    public void setIdpro(int idpro) {
        Idpro = idpro;
    }

    public String getProtocolname() {
        return protocolname;
    }

    public void setProtocolname(String protocolname) {
        this.protocolname = protocolname;
    }

    public String getProtocolcode() {
        return Protocolcode;
    }

    public void setProtocolcode(String protocolcode) {
        Protocolcode = protocolcode;
    }

    public String getSrcip() {
        return srcip;
    }

    public void setSrcip(String srcip) {
        this.srcip = srcip;
    }

    public String getDestip() {
        return destip;
    }

    public void setDestip(String destip) {
        this.destip = destip;
    }

    public String getSrcport() {
        return srcport;
    }

    public void setSrcport(String srcport) {
        this.srcport = srcport;
    }

    public String getDestport() {
        return destport;
    }

    public void setDestport(String destport) {
        this.destport = destport;
    }

    public Date getCollecttime() {
        return Collecttime;
    }

    public void setCollecttime(Date collecttime) {
        Collecttime = collecttime;
    }

    public void dataProcess(List<BusinessRegister> databean, String idplatform) {
        this.Idplatform = idplatform;
        beanlist = new ArrayList<TBizproinfBean>();
        Iterator<BusinessRegister> databeanIt = databean.iterator();
        while (databeanIt.hasNext()) {
            beanlist.add(dataProcessToBean(databeanIt.next(), idplatform));
        }

    }

    protected TBizproinfBean dataProcessToBean(BusinessRegister databean, String idplatform) {
        TBizproinfBean bean = new TBizproinfBean();
        bean.setIdplatform(idplatform);
        bean.setIdbiz(databean.getId());
        TProtocolDataBean probean = getProtocolDataBean(databean.getExch_protocol());
        bean.setIdpro(probean.getId());
        bean.setProtocolcode(probean.getProtocol_code());
        bean.setProtocolname(probean.getProtocol_name());
        bean.setSrcip(databean.getS_ip_range());
        bean.setDestip(databean.getD_ip_range());
        if (databean.getS_port_range() == null || databean.getS_port_range().equalsIgnoreCase("")) {
            bean.setSrcport("无");
            //this.VPN = "无";
        } else {
            bean.setSrcport(databean.getS_port_range());
        }
        if (databean.getD_port_range() == null || databean.getD_port_range().equalsIgnoreCase("")) {
            bean.setDestport("无");
            //this.VPN = "无";
        } else {
            bean.setDestport(databean.getD_port_range());
        }
        Date collecttime = new Date();
        collecttime.setYear(111);
        collecttime.setMonth(4);
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
        fileName = this.Idplatform + "-" + UpFileUtils.getDateFormatUp(System.currentTimeMillis()) + "-Tbizproinf-" + type.toUpperCase();
        StringBuffer buffer = new StringBuffer();
        StringBuffer temp = new StringBuffer();
        Iterator<TBizproinfBean> it = beanlist.iterator();
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

    protected StringBuffer beanToString(TBizproinfBean bean) {

        StringBuffer buffer = new StringBuffer();
        buffer.append(bean.Idplatform);
        buffer.append(UpFileUtils.dataSpilt);
        buffer.append(bean.Idbiz);
        buffer.append(UpFileUtils.dataSpilt);
        buffer.append(bean.Idpro);
        buffer.append(UpFileUtils.dataSpilt);
        buffer.append(bean.protocolname);
        buffer.append(UpFileUtils.dataSpilt);
        buffer.append(bean.protocolname);
        buffer.append(UpFileUtils.dataSpilt);
        buffer.append(bean.srcip);
        buffer.append(UpFileUtils.dataSpilt);
        buffer.append(bean.destip);
        buffer.append(UpFileUtils.dataSpilt);
        buffer.append(bean.srcport);
        buffer.append(UpFileUtils.dataSpilt);
        buffer.append(bean.destport);
        buffer.append(UpFileUtils.dataSpilt);
        buffer.append(UpFileUtils.getDateFormat(bean.Collecttime));
        buffer.append("\n");
        return buffer;
    }

    public TProtocolDataBean getProtocolDataBean(String name) {
        TProtocolDao dao = new TProtocolDao();
        Pagination<TProtocolDataBean> protobeanlist = dao.listProtocolByCode(1, 1, name);
        return protobeanlist.getItems().get(0);
    }
}
