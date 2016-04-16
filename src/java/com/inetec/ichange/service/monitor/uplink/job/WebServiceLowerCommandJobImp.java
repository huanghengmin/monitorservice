package com.inetec.ichange.service.monitor.uplink.job;

import com.crgs.client.WebServiceClient;
import com.crgs.webservices.IReceiveData;
import com.crgs.webservices.ISysClient;
import com.inetec.ichange.service.monitor.uplink.client.SysClientImpl;
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
 * Time: 上午11:22
 * To change this template use File | Settings | File Templates.
 */
public class WebServiceLowerCommandJobImp implements Job {
    private static final Logger logger = Logger.getLogger(WebServiceLowerCommandJobImp.class);
    private static final String command_sysclientservice = "sysclientservice";
    private static final String command_sysdelservice = "sysdelservice";
    private static final String command_sysqueryservice = "sysqueryservice";
    private static final String command_systerminalstatus = "systerminalstatus";
    private String command;
    private List<String> commands = new ArrayList<String>();

    public WebServiceLowerCommandJobImp() {
    }

    public WebServiceLowerCommandJobImp(String command) {
        this.command = command;
    }

    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        ParentExchangePlatformDao dao = new ParentExchangePlatformDao();
        List<ParentExchangePlatformDataBean> dataBeans = dao.WebServicelist();
        if (dataBeans==null ){
            logger.warn("集控系统-上级级联平台(WebService)信息没有初始化！无法级联上报");
            return;
        }
        if(dataBeans.size() == 0) {
            logger.warn("集控系统-上级级联平台(WebService)没有选择允许上报！无法级联上报");
            return;
        }
        for(ParentExchangePlatformDataBean dataBean : dataBeans){
            String ip = dataBean.getPlatform_ip();
            int port = dataBean.getPlatform_port();
            String server = dataBean.getAddress();
    //        String server = "/Webservice/services/inceptData";
            String url = "http://" + ip + ":" + port + server;//                    "http://192.168.2.227:8080/Webservice/services/inceptData";

            String keyFilePath = dataBean.getCertificate_path();//                          "D:\\fartec\\ichange\\monitorservice\\src\\resources\\clientStore.jks";
            String keyPassword = dataBean.getCertificate_pwd();//                          "1qaz@wsxstorepass";
            WebServiceClient client = new WebServiceClient();
            IReceiveData service = client.getServiceClient(url, keyFilePath, keyPassword);
    //                "http://10.2.164.55:8080/Webservice/services/inceptData",
    //                "/usr/app/cms/security/clientStore.jks", "1qaz@wsxstorepass");

            for (String comm : commands) {
                if (comm.equals(command_sysclientservice)) {
                    ISysClient sSysClient = new SysClientImpl();
                    new SysclientserviceProcess().process(sSysClient);
                    logger.info("下级监控系统-级联上报-移动警务客户端-上报成功");
                    if(jobExecutionContext!=null){
                        jobExecutionContext.setResult("下级监控系统-级联上报-移动警务客户端-上报成功");
                    }
                } else if (comm.equals(command_sysdelservice)) {
                    //new SysdelserviceProcess().process(service);
                    // jobExecutionContext.setResult("下级监控系统-级联上报-删除已上报内容-上报成功");
                } else if (comm.equals(command_sysqueryservice)) {
                    //new SysqueryserviceProcess("").process(service);
                    //jobExecutionContext.setResult("下级监控系统-级联上报-查询已上报内容-上报成功");
                } else if (comm.equals(command_systerminalstatus)) {
                    new SysterminalstatusProcess().process(service);
                    logger.info("下级监控系统-级联上报-接入终端状态-上报成功");
                    if(jobExecutionContext!=null){
                        jobExecutionContext.setResult("下级监控系统-级联上报-接入终端状态-上报成功");
                    }
                }
            }
        }
    }

    public String getCommand() {
        return command;
    }

    public void setCommand(String command) {
        this.command = command;
    }

    private List<String> toList(String reportCommand) {
        if (reportCommand != null) {
            String[] strs = reportCommand.split(":");
            if (strs.length > 1) {
                for (int i = 0; i < strs.length; i++) {
                    commands.add(strs[i]);
                }
            } else {
                commands.add(reportCommand);
            }
            return commands;
        }
        return null;
    }

    public static void main(String arg[]) throws Exception {
        String url = "http://192.168.2.227:8080/Webservice/services/inceptData";
        String keyFilePath = "D:\\fartec\\ichange\\monitorservice\\src\\resources\\clientStore.jks";
        String keyPassword = "1qaz@wsxstorepass";
        WebServiceLowerCommandJobImp job = new WebServiceLowerCommandJobImp(command_sysclientservice);
        job.execute(null);
    }

}
