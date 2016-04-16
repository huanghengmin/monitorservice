package com.inetec.ichange.service.monitor.uplink.infobean;

import com.inetec.ichange.service.monitor.uplink.databean.TPlatinfDataBean;
import com.inetec.ichange.service.monitor.uplink.utils.UpFileUtils;

import java.io.UnsupportedEncodingException;
import java.util.Date;

/**
 * Created by IntelliJ IDEA.
 * User: bluesky
 * Date: 11-4-28
 * Time: 下午4:16
 * To change this template use File | Settings | File Templates.
 */
public class TPlatinfBean {
    private String Platformname;
    private String Idplatform;
    private String Address;
    private String Manager;
    private String ManagerPhone;
    private String ManagerMail;
    private String ManagerOtherlink;
    private String RemoteaccessIp;
    private String ConstructUnit;
    private String BuildingUnitCode = "FX";
    private Date BuildingTime;
    private String SignSecrecyprotocol;
    private String ApproveUnit;
    private Date ApproveTime;
    private String ApproveNo;
    private String ApproveMaterial;
    private String SecurityProject;
    private String SecrecyProtocol;
    private String MaintainUnit;
    private String MaintainManager;
    private String MaintainManagerPhone;
    private String MaintainManagerMail;
    private String MaintainManagerLink;
    private String PlatformMap;
    private Date Collecttime;


    public String getPlatformname() {
        return Platformname;
    }

    public void setPlatformname(String platformname) {
        Platformname = platformname;
    }

    public String getIdplatform() {
        return Idplatform;
    }

    public void setIdplatform(String idplatform) {
        Idplatform = idplatform;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public String getManager() {
        return Manager;
    }

    public void setManager(String manager) {
        Manager = manager;
    }

    public String getManagerPhone() {
        return ManagerPhone;
    }

    public void setManagerPhone(String managerPhone) {
        ManagerPhone = managerPhone;
    }

    public String getManagerMail() {
        return ManagerMail;
    }

    public void setManagerMail(String managerMail) {
        ManagerMail = managerMail;
    }

    public String getManagerOtherlink() {
        return ManagerOtherlink;
    }

    public void setManagerOtherlink(String managerOtherlink) {
        ManagerOtherlink = managerOtherlink;
    }

    public String getRemoteaccessIp() {
        return RemoteaccessIp;
    }

    public void setRemoteaccessIp(String remoteaccessIp) {
        RemoteaccessIp = remoteaccessIp;
    }

    public String getConstructUnit() {
        return ConstructUnit;
    }

    public void setConstructUnit(String constructUnit) {
        ConstructUnit = constructUnit;
    }

    public String getBuildingUnitCode() {
        return BuildingUnitCode;
    }

    public void setBuildingUnitCode(String buildingUnitCode) {
        BuildingUnitCode = buildingUnitCode;
    }

    public Date getBuildingTime() {
        return BuildingTime;
    }

    public void setBuildingTime(Date buildingTime) {
        BuildingTime = buildingTime;
    }

    public String getSignSecrecyprotocol() {
        return SignSecrecyprotocol;
    }

    public void setSignSecrecyprotocol(String signSecrecyprotocol) {
        SignSecrecyprotocol = signSecrecyprotocol;
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

    public String getSecurityProject() {
        return SecurityProject;
    }

    public void setSecurityProject(String securityProject) {
        SecurityProject = securityProject;
    }

    public String getSecrecyProtocol() {
        return SecrecyProtocol;
    }

    public void setSecrecyProtocol(String secrecyProtocol) {
        SecrecyProtocol = secrecyProtocol;
    }

    public String getMaintainUnit() {
        return MaintainUnit;
    }

    public void setMaintainUnit(String maintainUnit) {
        MaintainUnit = maintainUnit;
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

    public String getPlatformMap() {
        return PlatformMap;
    }

    public void setPlatformMap(String platformMap) {
        PlatformMap = platformMap;
    }

    public Date getCollecttime() {
        return Collecttime;
    }

    public void setCollecttime(Date collecttime) {
        Collecttime = collecttime;
    }

    public void dataProcess(TPlatinfDataBean databean) {
        this.Platformname = databean.getPlatform_name();
        this.Idplatform = databean.getPlatform_id();
        this.Address = databean.getAddress();
        this.Manager = databean.getFzr_name();
        this.ManagerPhone = databean.getFzr_phone();
        this.ManagerMail = databean.getFzr_email();
        this.ManagerOtherlink = databean.getFzr_other_phone();
        this.RemoteaccessIp = databean.getJksys_ip();
        this.ConstructUnit = databean.getJsdw();
        this.BuildingUnitCode = databean.getZycjdw();
        this.BuildingTime = databean.getJs_day();
        if (databean.getBmxy().equalsIgnoreCase("Y"))
            this.SignSecrecyprotocol = "1";
        else
            this.SignSecrecyprotocol = "0";
        this.ApproveUnit = databean.getSpdw();
        this.ApproveTime = databean.getSp_day();
        this.ApproveNo = databean.getSpph();
        this.ApproveMaterial = "001.jpg";
        this.SecurityProject = "002.doc";
        this.SecrecyProtocol = "003.rar";
        this.MaintainUnit = databean.getMain_comp();
        this.MaintainManager = databean.getMaintainer_name();
        this.MaintainManagerPhone = databean.getMaintainer_phone();
        this.MaintainManagerMail = databean.getMaintainer_email();
        this.MaintainManagerLink = databean.getMaintainer_other_phone();
        this.PlatformMap = "004.jpg";
        Date collecttime = new Date();
        collecttime.setYear(111);
        collecttime.setMonth(3);
        collecttime.setDate(1);
        this.Collecttime = collecttime;

    }

    /**
     * 生成上报数据文件
     *
     * @return 文件名，包含全路径。
     */
    public String processFile(String type) {
        String fileName = new String();
        fileName = this.Idplatform + "-" + UpFileUtils.getDateFormatUp(System.currentTimeMillis()) + "-TPlatinf-" + type.toUpperCase();

        StringBuffer buffer = new StringBuffer();

        buffer.append(this.Platformname);
        buffer.append(UpFileUtils.dataSpilt);
        buffer.append(this.Idplatform);
        buffer.append(UpFileUtils.dataSpilt);
        buffer.append(this.Address);
        buffer.append(UpFileUtils.dataSpilt);
        buffer.append(this.Manager);
        buffer.append(UpFileUtils.dataSpilt);
        buffer.append(this.ManagerPhone);
        buffer.append(UpFileUtils.dataSpilt);
        buffer.append(this.ManagerMail);
        buffer.append(UpFileUtils.dataSpilt);
        buffer.append(this.ManagerOtherlink);
        buffer.append(UpFileUtils.dataSpilt);
        buffer.append(this.RemoteaccessIp);
        buffer.append(UpFileUtils.dataSpilt);
        buffer.append(this.ConstructUnit);
        buffer.append(UpFileUtils.dataSpilt);
        buffer.append(this.BuildingUnitCode);
        buffer.append(UpFileUtils.dataSpilt);
        buffer.append(UpFileUtils.getDateFormat(this.BuildingTime));
        buffer.append(UpFileUtils.dataSpilt);
        buffer.append(this.SignSecrecyprotocol);
        buffer.append(UpFileUtils.dataSpilt);
        if (this.ApproveUnit != null && !this.ApproveUnit.equalsIgnoreCase(""))
            buffer.append(this.ApproveUnit);
        buffer.append(UpFileUtils.dataSpilt);
        buffer.append(UpFileUtils.getDateFormat(this.ApproveTime));
        buffer.append(UpFileUtils.dataSpilt);
        buffer.append(this.ApproveNo);
        buffer.append(UpFileUtils.dataSpilt);
        if (this.ApproveMaterial != null && !this.ApproveMaterial.equalsIgnoreCase("")) {
            String oldApproveMaterial = this.ApproveMaterial;

            this.ApproveMaterial = this.Idplatform + "-" + UpFileUtils.getDateFormatUp(System.currentTimeMillis()) +
                    "-" + type.toUpperCase() + "-001-0.zip";
            new UpFileUtils().zipProcess(this.ApproveMaterial, oldApproveMaterial);
            new UpFileUtils().processFinish(this.ApproveMaterial + ".finished");
            buffer.append(this.ApproveMaterial);
        }
        buffer.append(UpFileUtils.dataSpilt);
        if (this.SecurityProject != null && !this.SecurityProject.equalsIgnoreCase("")) {
            String oldSecurityProject = this.SecurityProject;
            this.SecurityProject = this.Idplatform + "-" + UpFileUtils.getDateFormatUp(System.currentTimeMillis()) +
                    "-" + type.toUpperCase() + "-002-0.zip";

            new UpFileUtils().zipProcess(this.SecurityProject, oldSecurityProject);
            new UpFileUtils().processFinish(this.SecurityProject + ".finished");
            buffer.append(this.SecurityProject);

        }
        buffer.append(UpFileUtils.dataSpilt);
        if (this.SecrecyProtocol != null && !this.SecrecyProtocol.equalsIgnoreCase("")) {
            String oldSecrecyProtocol = this.SecrecyProtocol;

            this.SecrecyProtocol = this.Idplatform + "-" + UpFileUtils.getDateFormatUp(System.currentTimeMillis()) +
                    "-" + type.toUpperCase() + "-003-0.zip";
            new UpFileUtils().zipProcess(this.SecrecyProtocol, oldSecrecyProtocol);
            new UpFileUtils().processFinish(this.SecrecyProtocol + ".finished");
            buffer.append(this.SecrecyProtocol);
        }
        buffer.append(UpFileUtils.dataSpilt);
        buffer.append(this.MaintainUnit);
        buffer.append(UpFileUtils.dataSpilt);
        buffer.append(this.MaintainManager);
        buffer.append(UpFileUtils.dataSpilt);
        buffer.append(this.MaintainManagerPhone);
        buffer.append(UpFileUtils.dataSpilt);
        buffer.append(this.MaintainManagerMail);
        buffer.append(UpFileUtils.dataSpilt);
        buffer.append(this.MaintainManagerLink);
        buffer.append(UpFileUtils.dataSpilt);
        if (this.PlatformMap != null && !this.PlatformMap.equalsIgnoreCase("")) {
            String oldPlatformMap = this.PlatformMap;
            this.PlatformMap = this.Idplatform + "-" + UpFileUtils.getDateFormatUp(System.currentTimeMillis()) +
                    "-" + type.toUpperCase() + "-004-0.zip";
            new UpFileUtils().zipProcess(this.PlatformMap, oldPlatformMap);
            new UpFileUtils().processFinish(this.PlatformMap + ".finished");
            buffer.append(this.PlatformMap);
        }
        buffer.append(UpFileUtils.dataSpilt);
        buffer.append(UpFileUtils.getDateFormat(this.Collecttime));
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

    public static void main(String arg[]) throws Exception {
        TPlatinfBean bean = new TPlatinfBean();
        System.setProperty(UpFileUtils.Str_monitor_Home, "c:\\");
//        bean.setPlatformname("四川省广安市公安局边界接入平台");
//        bean.setIdplatform("P51160001");
//        bean.setAddress("广安市公安局9楼机房");
//        bean.setManager("李泽伟");
//        bean.setManagerPhone("18908289616");
//        bean.setManagerMail("licandys@gaj.gas.sc");
//        bean.setMaintainManagerLink("0826-2396196");
//        bean.setRemoteaccessIp("10.69.5.104");
//        bean.setConstructUnit("511600000000");
//        bean.setBuildingUnitCode("FX");
//        Date buildtime = new Date();
//        buildtime.setYear(110);
//        buildtime.setMonth(11);
//        buildtime.setDate(25);
//        bean.setBuildingTime(buildtime);
//        bean.setSignSecrecyprotocol("1");
//        bean.setApproveUnit("510000110000");
//        bean.setApproveMaterial("d:\\级联监控附件\\001.jpg");
//        bean.setApproveNo("公厅信通发[2010]196");
//        Date approvetime = new Date();
//        approvetime.setYear(110);
//        approvetime.setMonth(8);
//        approvetime.setDate(2);
//        bean.setApproveTime(approvetime);
//        bean.setSecurityProject("d:\\级联监控附件\\002.doc");
//        bean.setSecrecyProtocol("d:\\级联监控附件\\003.rar");
//        bean.setMaintainUnit("01511600");
//        bean.setMaintainManager("李泽伟");
//        bean.setMaintainManagerPhone("08262396196");
//        bean.setMaintainManagerMail("licandys@gaj.gas.sc");
//        bean.setMaintainManagerLink("18908289616");
//        bean.setPlatformMap("d:\\级联监控附件\\004.jpg");
//        Date collecttime = new Date();
//        collecttime.setYear(111);
//        collecttime.setMonth(4);
//        collecttime.setDate(1);
//        bean.setCollecttime(collecttime);
        bean.testData(bean);
        bean.processFile("A");
        /*    bean.testData(bean);
        bean.processFile("U");*/

    }

    protected TPlatinfBean testData(TPlatinfBean bean) {
        bean.setPlatformname("广安市公安局边界接入平台");
        bean.setIdplatform("P51160001");
        bean.setAddress("广安市公安局9楼机房");
        bean.setManager("李泽伟");
        bean.setManagerPhone("0826-2396196");
        bean.setManagerMail("licandys@gaj.gas.sc");
        bean.setManagerOtherlink("18908289616");
        bean.setRemoteaccessIp("10.69.5.104");
        bean.setConstructUnit("511600000000");
        bean.setBuildingUnitCode("FX");
        Date buildtime = new Date();
        buildtime.setYear(110);
        buildtime.setMonth(11);
        buildtime.setDate(25);
        bean.setBuildingTime(buildtime);
        bean.setSignSecrecyprotocol("1");
        bean.setApproveUnit("510000110000");
        bean.setApproveNo("公厅信通发[2010]196");
        Date approvetime = new Date();
        approvetime.setYear(110);
        approvetime.setMonth(8);
        approvetime.setDate(2);
        bean.setApproveTime(approvetime);
        bean.setApproveMaterial("001.jpg");
        bean.setSecurityProject("002.zip");
        bean.setSecrecyProtocol("003.doc");
        bean.setMaintainUnit("511600000000");
        bean.setMaintainManager("李泽伟");
        bean.setMaintainManagerPhone("0826-2396196");
        bean.setMaintainManagerMail("licandys@gaj.gas.sc");
        bean.setMaintainManagerLink("18908289616");
        bean.setPlatformMap("004.jpg");
        Date collecttime = new Date();
        collecttime.setYear(111);
        collecttime.setMonth(4);
        collecttime.setDate(1);
        bean.setCollecttime(collecttime);
        return bean;
    }
}
