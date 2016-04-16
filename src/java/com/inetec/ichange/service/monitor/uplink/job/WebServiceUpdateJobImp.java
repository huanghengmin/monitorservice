package com.inetec.ichange.service.monitor.uplink.job;

import com.crgs.client.WebServiceClient;
import com.crgs.webservices.IReceiveData;
import com.inetec.ichange.service.monitor.uplink.databean.ParentExchangePlatformDao;
import com.inetec.ichange.service.monitor.uplink.databean.ParentExchangePlatformDataBean;
import com.inetec.ichange.service.monitor.uplink.webservice.*;
import org.apache.log4j.Logger;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: 钱晓盼
 * Date: 12-3-16
 * Time: 上午11:32
 * 初始上报一次,以后有变更了再上报
 */
public class WebServiceUpdateJobImp implements Job{
    private static final Logger logger=Logger.getLogger(WebServiceUpdateJobImp.class);
    private static final String reportType_sysbizinf = "sysbizinf";
    private static final String reportType_syscontrolrulesinf = "syscontrolrulesinf";
    private static final String reportType_sysdeviceinf = "sysdeviceinf";
    private static final String reportType_sysoutlinkinf = "sysoutlinkinf";
    private static final String reportType_sysreginf = "sysreginf";
    private static final String reportType_sysstrategyinf = "sysstrategyinf";
    private static final String reportType_systerminalinf = "systerminalinf";
    private String uplinkTypes;
    private long id;
    private List<String> reportTypes = new ArrayList<String>();

    /**
     * 初始化上报
     */
    public WebServiceUpdateJobImp() {
    }

    /**
     *  手动上报
     * @param uplinkTypes (if this param is null,then it will do init report;else it will do
     */
    public WebServiceUpdateJobImp(String uplinkTypes ,long id) {
        this.uplinkTypes = uplinkTypes;
        this.id = id;
    }

    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        ParentExchangePlatformDao dao = new ParentExchangePlatformDao();
        ParentExchangePlatformDataBean dataBean = dao.findById(id);
        if(dataBean==null){
            logger.warn("集控系统-上级级联平台(WebService)信息没有初始化！无法级联上报");
            return;
        }
        String ip = dataBean.getPlatform_ip();
        int port = dataBean.getPlatform_port();
        String server = dataBean.getAddress();
//        String server = "/Webservice/services/inceptData";
        String url =  "http://"+ip+":"+port+server;
//                    "http://192.168.2.227:8080/Webservice/services/inceptData";

        String keyFilePath = dataBean.getCertificate_path();
//                          "D:\\fartec\\ichange\\monitorservice\\src\\resources\\clientStore.jks";
        String keyPassword = dataBean.getCertificate_pwd();
//                          "1qaz@wsxstorepass";

        WebServiceClient client = new WebServiceClient();
        IReceiveData service = client.getServiceClient(url,keyFilePath,keyPassword);
        reportTypes = toList(uplinkTypes);
        if(reportTypes == null ){
            new SysbizinfProcess().process(service);
            new SyscontrolrulesinfProcess().process(service);
            new SysdeviceinfProcess().process(service);
            new SysoutlinkinfProcess().process(service);
            new SysreginfProcess().process(service);
            new SysstrategyinfProcess().process(service);
            new SysterminalinfoProcess().process(service);
           // jobExecutionContext.setResult("级联上报-初始化移动警务各种注册信息-上报成功！");
        }else{
            for (String reportType : reportTypes){
                if(reportType.equals(reportType_sysbizinf)){
                    new SysbizinfProcess().process(service);
                    logger.info("级联上报-移动警务接入应用注册信息-变更操作-上报成功！");
                   // jobExecutionContext.setResult("级联上报-移动警务接入应用注册信息-变更操作-上报成功！");
                }else if(reportType.equals(reportType_syscontrolrulesinf)){
                    new SyscontrolrulesinfProcess().process(service);
                    logger.info("级联上报-移动警务系统控制策略注册信息-变更操作-上报成功！");
                    //jobExecutionContext.setResult("级联上报-移动警务系统控制策略注册信息-变更操作-上报成功！");
                }else if(reportType.equals(reportType_sysdeviceinf)){
                    new SysdeviceinfProcess().process(service);
                    logger.info("级联上报-移动警务系统核心设备注册信息-变更操作-上报成功！");
                    //jobExecutionContext.setResult("级联上报-移动警务系统核心设备注册信息-变更操作-上报成功！");
                }else if(reportType.equals(reportType_sysoutlinkinf)){
                    new SysoutlinkinfProcess().process(service);
                    logger.info("级联上报-移动警务系统外部链路注册信息-变更操作-上报成功！");
                    //jobExecutionContext.setResult("级联上报-移动警务系统外部链路注册信息-变更操作-上报成功！");
                }else if(reportType.equals(reportType_sysreginf)){
                    new SysreginfProcess().process(service);
                    logger.info("级联上报-移动警务系统注册信息-变更操作-上报成功！");
                    //jobExecutionContext.setResult("级联上报-移动警务系统注册信息-变更操作-上报成功！");
                }else if(reportType.equals(reportType_sysstrategyinf)){
                    new SysstrategyinfProcess().process(service);
                    logger.info("级联上报-移动警务系统安全策略注册信息-变更操作-上报成功！");
                   // jobExecutionContext.setResult("级联上报-移动警务系统安全策略注册信息-变更操作-上报成功！");
                }else if(reportType.equals(reportType_systerminalinf)){
                    new SysterminalinfoProcess().process(service);
                    logger.info("级联上报-移动警务接入终端信息-变更操作-上报成功！");
                   // jobExecutionContext.setResult("级联上报-移动警务接入终端信息-变更操作-上报成功！");
                }else{
                    logger.info("级联上报-该上报类型不存在！");
                    //jobExecutionContext.setResult("级联上报-该上报类型不存在！");
                }
            }
        }
    }

    private List<String> toList(String uplinkTypes) {
        if( uplinkTypes != null ){
            String[] strs = uplinkTypes.split(":");
            if(strs.length>1){
                for(int i = 0 ; i < strs.length ; i ++){
                    reportTypes.add(strs[i]);
                }
            }else {
                reportTypes.add(uplinkTypes);
            }
            return reportTypes;
        }
        return null;
    }

    public String getUplinkTypes() {
        return uplinkTypes;
    }

    public void setUplinkTypes(String uplinkTypes) {
        this.uplinkTypes = uplinkTypes;
    }

    public static void main(String arg[]) throws Exception {
        WebServiceUpdateJobImp job = new WebServiceUpdateJobImp();
//        JobExecutionContext jobExecutionContext = new JobExecutionContext(null,null,null);
        job.execute(null);
    }
}
