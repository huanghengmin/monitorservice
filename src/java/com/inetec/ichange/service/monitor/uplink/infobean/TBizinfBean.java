package com.inetec.ichange.service.monitor.uplink.infobean;

import com.inetec.ichange.service.monitor.databean.BusinessRegister;
import com.inetec.ichange.service.monitor.uplink.databean.TInlinkinfDataBean;
import com.inetec.ichange.service.monitor.uplink.databean.TSbpzDataBean;
import com.inetec.ichange.service.monitor.uplink.ftp.FtpClient;
import com.inetec.ichange.service.monitor.uplink.ftp.UpFtpClient;
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
public class TBizinfBean {
    private int Idbiz;
    private int Idconnlink;

    private String Idplatform;
    private String BizManagedepart;
    private String BizName;
    private String Biztypecode;
    private String BizOperatestylecode;
    private String MaintainManager;
    private String MaintainManagerPhone;
    private String MaintainManagerMail;
    private String MaintainManagerLink;
    private String ApproveUnit;
    private Date ApproveTime;
    private String ApproveNo;
    private String ApproveMaterial;
    private Date RegisterTime;
    private String Dateexchangdirect;
    private String BaseProtocolcode;
    private String Realtime;
    private String DateexchangeDateflux;
    private String IsBackup;
    private String BackupUnitname;
    private String TopologyMap;

    private TSbpzDataBean tspz;
    private List<TBizinfBean> beanlist;

    public String getTopologyMap() {
        return TopologyMap;
    }

    public void setTopologyMap(String topologyMap) {
        TopologyMap = topologyMap;
    }

    public int getIdbiz() {
        return Idbiz;
    }

    public void setIdbiz(int idbiz) {
        Idbiz = idbiz;
    }

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

    public String getBizManagedepart() {
        return BizManagedepart;
    }

    public void setBizManagedepart(String bizManagedepart) {
        BizManagedepart = bizManagedepart;
    }

    public String getBizName() {
        return BizName;
    }

    public void setBizName(String bizName) {
        BizName = bizName;
    }

    public String getBiztypecode() {
        return Biztypecode;
    }

    public void setBiztypecode(String biztypecode) {
        Biztypecode = biztypecode;
    }

    public String getBizOperatestylecode() {
        return BizOperatestylecode;
    }

    public void setBizOperatestylecode(String bizOperatestylecode) {
        BizOperatestylecode = bizOperatestylecode;
    }

    public String getMaintainManager() {
        return MaintainManager;
    }

    public void setMaintainManager(String maintainManager) {
        MaintainManager = maintainManager;
    }

    public String getMaintainManagerPhone() {
        return MaintainManagerPhone;
    }

    public void setMaintainManagerPhone(String maintainManagerPhone) {
        MaintainManagerPhone = maintainManagerPhone;
    }

    public String getMaintainManagerMail() {
        return MaintainManagerMail;
    }

    public void setMaintainManagerMail(String maintainManagerMail) {
        MaintainManagerMail = maintainManagerMail;
    }

    public String getMaintainManagerLink() {
        return MaintainManagerLink;
    }

    public void setMaintainManagerLink(String maintainManagerLink) {
        MaintainManagerLink = maintainManagerLink;
    }

    public String getApproveUnit() {
        return ApproveUnit;
    }

    public void setApproveUnit(String approveUnit) {
        ApproveUnit = approveUnit;
    }

    public Date getApproveTime() {
        return ApproveTime;
    }

    public void setApproveTime(Date approveTime) {
        ApproveTime = approveTime;
    }

    public String getApproveNo() {
        return ApproveNo;
    }

    public void setApproveNo(String approveNo) {
        ApproveNo = approveNo;
    }

    public String getApproveMaterial() {
        return ApproveMaterial;
    }

    public void setApproveMaterial(String approveMaterial) {
        ApproveMaterial = approveMaterial;
    }

    public Date getRegisterTime() {
        return RegisterTime;
    }

    public void setRegisterTime(Date registerTime) {
        RegisterTime = registerTime;
    }

    public String getDateexchangdirect() {
        return Dateexchangdirect;
    }

    public void setDateexchangdirect(String dateexchangdirect) {
        Dateexchangdirect = dateexchangdirect;
    }

    public String getBaseProtocolcode() {
        return BaseProtocolcode;
    }

    public void setBaseProtocolcode(String baseProtocolcode) {
        BaseProtocolcode = baseProtocolcode;
    }

    public String getRealtime() {
        return Realtime;
    }

    public void setRealtime(String realtime) {
        Realtime = realtime;
    }

    public String getDateexchangeDateflux() {
        return DateexchangeDateflux;
    }

    public void setDateexchangeDateflux(String dateexchangeDateflux) {
        DateexchangeDateflux = dateexchangeDateflux;
    }

    public String getIsBackup() {
        return IsBackup;
    }

    public void setIsBackup(String isBackup) {
        IsBackup = isBackup;
    }

    public String getBackupUnitname() {
        return BackupUnitname;
    }

    public void setBackupUnitname(String backupUnitname) {
        BackupUnitname = backupUnitname;
    }

    private Date Collecttime;


    public Date getCollecttime() {
        return Collecttime;
    }

    public void setCollecttime(Date collecttime) {
        Collecttime = collecttime;
    }

    public void dataProcess(List<BusinessRegister> databean, String idplatform) {
        this.Idplatform = idplatform;
        beanlist = new ArrayList<TBizinfBean>();
        Iterator<BusinessRegister> databeanIt = databean.iterator();

        while (databeanIt.hasNext()) {
            beanlist.add(dataProcessToBean(databeanIt.next(), idplatform));
        }


    }

    protected TBizinfBean dataProcessToBean(BusinessRegister databean, String idplatform) {
        TBizinfBean bean = new TBizinfBean();
        bean.setIdbiz(databean.getId());
        bean.setIdconnlink(3);
        bean.setIdplatform(idplatform);
        bean.setBizManagedepart(databean.getBusiness_depart());
        bean.setBizName(databean.getBusiness_desc().trim());
        bean.setBiztypecode(databean.getBusiness_code().trim());
        bean.setBizOperatestylecode(databean.getBusiness_exch_model());
        bean.setMaintainManager(databean.getBusiness_admin());
        bean.setMaintainManagerPhone(databean.getAdmin_phone());
        bean.setMaintainManagerMail(databean.getAdmin_email());
        bean.setMaintainManagerLink(databean.getAdmin_other_phone());
        bean.setApproveUnit(databean.getAuth_depart());
        bean.setApproveTime(databean.getAuth_date());
        bean.setApproveNo(databean.getAuth_serial());
        bean.setApproveMaterial(databean.getBusiness_name() + "-002.rar");
        bean.setRegisterTime(databean.getRegister_date());
        bean.setDateexchangdirect("1");
        bean.setBaseProtocolcode(databean.getExch_protocol());
        bean.setRealtime(databean.getIs_realtime());
        bean.setDateexchangeDateflux(databean.getDay_datavolume() + "M");
        bean.setIsBackup(databean.getIs_approved());
        bean.setBackupUnitname(databean.getApproved_depart());
        bean.setTopologyMap(databean.getBusiness_name() + "-005.jpg");
        bean.setCollecttime(UpFileUtils.getCurrentDayDate());
        bean.tspz = tspz;
        return bean;
    }

    /**
     * 生成上报数据文件
     *
     * @return 文件名，包含全路径。
     */
    public String processFile(String type, FtpClient ftpclient) {
        String fileName = new String();
        fileName = this.Idplatform + "-" + UpFileUtils.getDateFormatUp(System.currentTimeMillis()) + "-TBizInf-" + type.toUpperCase();
        StringBuffer buffer = new StringBuffer();
        StringBuffer temp = new StringBuffer();
        Iterator<TBizinfBean> it = beanlist.iterator();
        while (it.hasNext()) {
            buffer.append(beanToString(it.next(), type, ftpclient));
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
        TBizinfBean bean = new TBizinfBean();
        System.setProperty(UpFileUtils.Str_monitor_Home, "c:\\");

        /*   bean.testData(bean);
        bean.processFile("A");
        bean.testData(bean);
        bean.processFile("U");*/

    }

    protected StringBuffer beanToString(TBizinfBean bean, String type, FtpClient ftpclient) {

        StringBuffer buffer = new StringBuffer();
        buffer.append(Integer.toHexString(bean.Idbiz));
        buffer.append(UpFileUtils.dataSpilt);
        buffer.append(Integer.toHexString(bean.Idconnlink));
        buffer.append(UpFileUtils.dataSpilt);
        buffer.append(bean.Idplatform);
        buffer.append(UpFileUtils.dataSpilt);
        buffer.append(bean.BizManagedepart);
        buffer.append(UpFileUtils.dataSpilt);
        buffer.append(bean.BizName.trim());
        buffer.append(UpFileUtils.dataSpilt);
        buffer.append(bean.Biztypecode.trim());
        buffer.append(UpFileUtils.dataSpilt);
        buffer.append(bean.BizOperatestylecode);
        buffer.append(UpFileUtils.dataSpilt);
        buffer.append(bean.MaintainManager);
        buffer.append(UpFileUtils.dataSpilt);
        buffer.append(bean.MaintainManagerPhone);
        buffer.append(UpFileUtils.dataSpilt);
        buffer.append(bean.MaintainManagerMail);
        buffer.append(UpFileUtils.dataSpilt);
        buffer.append(bean.getMaintainManagerLink());
        buffer.append(UpFileUtils.dataSpilt);
        buffer.append(bean.ApproveUnit);
        buffer.append(UpFileUtils.dataSpilt);
        if (UpFileUtils.getDateFormat(bean.getApproveTime()).endsWith("00:00:00")) {
            String time = UpFileUtils.getDateNoTimeFormat(bean.getApproveTime()) + " 10:02:03";
            buffer.append(UpFileUtils.dataSpilt);
            buffer.append(time);
        } else {
            buffer.append(UpFileUtils.dataSpilt);
            buffer.append(UpFileUtils.getDateFormat(bean.getApproveTime()));
        }
        buffer.append(UpFileUtils.dataSpilt);
        buffer.append(bean.ApproveNo);
        buffer.append(UpFileUtils.dataSpilt);
        if (bean.ApproveMaterial != null && !bean.ApproveMaterial.equalsIgnoreCase("")) {
            String oldApproveMaterial = bean.ApproveMaterial;
            bean.ApproveMaterial = bean.Idplatform + "-" + UpFileUtils.getDateFormatUp(System.currentTimeMillis()) +
                    "-" + type.toUpperCase() + "-101-" + bean.Idbiz + ".zip";
            new UpFileUtils().zipProcess(bean.ApproveMaterial, oldApproveMaterial);
            new UpFileUtils().processFinish(bean.ApproveMaterial + ".finished");


            ftpclient.upfile(
                    UpFileUtils.Str_FtpDir, UpFileUtils.getDataPath() + bean.ApproveMaterial);
            ftpclient.upfile(
                    UpFileUtils.Str_FtpDir, UpFileUtils.getDataPath() + bean.ApproveMaterial + ".finished");

            buffer.append(bean.ApproveMaterial);
        }
        buffer.append(UpFileUtils.dataSpilt);
        if (UpFileUtils.getDateFormat(bean.RegisterTime).endsWith("00:00:00")) {

            String time = UpFileUtils.getDateNoTimeFormat(bean.RegisterTime) + " 01:02:03";

            buffer.append(time);
        } else {

            buffer.append(UpFileUtils.getDateFormat(bean.RegisterTime));
        }

        buffer.append(UpFileUtils.dataSpilt);
        buffer.append(bean.Dateexchangdirect);
        buffer.append(UpFileUtils.dataSpilt);
        buffer.append(bean.BaseProtocolcode);
        buffer.append(UpFileUtils.dataSpilt);
        //附件
        buffer.append(bean.Realtime);
        buffer.append(UpFileUtils.dataSpilt);

        buffer.append(bean.DateexchangeDateflux);

        buffer.append(UpFileUtils.dataSpilt);

        buffer.append(bean.IsBackup);
        buffer.append(UpFileUtils.dataSpilt);
        if (bean.IsBackup == "1") {
            buffer.append(bean.BackupUnitname);
        }
        buffer.append(UpFileUtils.dataSpilt);
        if (bean.TopologyMap != null && !bean.TopologyMap.equalsIgnoreCase("")) {
            String oldTopologyMap = bean.TopologyMap;
            bean.TopologyMap = bean.Idplatform + "-" + UpFileUtils.getDateFormatUp(System.currentTimeMillis()) +
                    "-" + type.toUpperCase() + "-102-" + bean.Idbiz + ".zip";
            new UpFileUtils().zipProcess(bean.TopologyMap, oldTopologyMap);
            new UpFileUtils().processFinish(bean.TopologyMap + ".finished");


            ftpclient.upfile(
                    UpFileUtils.Str_FtpDir, UpFileUtils.getDataPath() + bean.TopologyMap);
            ftpclient.upfile(
                    UpFileUtils.Str_FtpDir, UpFileUtils.getDataPath() + bean.TopologyMap + ".finished");
            buffer.append(bean.TopologyMap);
        }

        buffer.append(UpFileUtils.dataSpilt);
        buffer.append(UpFileUtils.getDateFormat(bean.Collecttime));
        buffer.append("\n");
        return buffer;
    }


}
