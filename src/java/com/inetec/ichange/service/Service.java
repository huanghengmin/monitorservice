package com.inetec.ichange.service;


import com.inetec.common.exception.E;
import com.inetec.common.exception.Ex;
import com.inetec.common.i18n.Message;
import com.inetec.ichange.logs.service.LogServiceThread;
import com.inetec.ichange.main.api.DataAttributes;
import com.inetec.ichange.main.api.EChange;
import com.inetec.ichange.service.monitor.alarm.AlarmService;
import com.inetec.ichange.service.monitor.business.BusinessPlatformService;
import com.inetec.ichange.service.monitor.business.SysAbnormalIfService;
import com.inetec.ichange.service.monitor.databean.*;
import com.inetec.ichange.service.monitor.http.TerminalinfService;
import com.inetec.ichange.service.monitor.ping.PingMonitorService;
import com.inetec.ichange.service.monitor.snmp.InSnmpMonitorService;
import com.inetec.ichange.service.monitor.snmp.SnmpMonitorService;
import com.inetec.ichange.service.monitor.uplink.job.WebServiceUplinkCronTriggerRunner;
import com.inetec.ichange.service.monitor.utils.BusinessDataBeanSet;
import com.inetec.ichange.service.monitor.utils.Pagination;
import com.inetec.ichange.service.monitor.utils.ServiceMonitorFactory;
import com.inetec.ichange.service.utils.*;
import com.inetec.ichange.service.utils.syslog.SyslogServer;
import org.apache.log4j.Category;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.Enumeration;
import java.util.List;
import java.util.Properties;

/**
 * Created by IntelliJ IDEA.
 * User: wxh
 * Date: 2005-8-15
 * Time: 20:17:53
 * To change this template use File | Settings | File Templates.
 */
public class Service extends HttpServlet {
    private static final Logger logger = Logger.getLogger(Service.class);
    public static Category s_log = null;
    public static SipTypeStatusSet siptypeSet = null;
    private static boolean ippingIsRun = false;
    private static PingMonitorService pingMonitorService;
    public static boolean inSnmpIsRun = false;
    public static InSnmpMonitorService inSnmpService;

    public Service() {
//        runSnmpMonitorService();
        runSysLogService();
        runBusinessService();
        runPingMonitorService();
        runInSnmpMonitorService();
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        response.setContentType("text/html");
        PrintWriter writer = response.getWriter();

        writer.println("<html>");
        writer.println("<head>");
        writer.println("<title>Monitor  Service Page</title>");
        writer.println("</head>");
        writer.println("<body bgcolor=white>");
        writer.println("<table border=\"0\">");
        writer.println("<tr>");
        writer.println("<td>");
        writer.println("<h1>Fertec Monitor  Status Page</h1>");
        writer.println("<P>Monitor  service is running.<P><BR>");
        writer.println("</td>");
        writer.println("</tr>");
        writer.println("</table>");
        writer.println("</body>");
        writer.println("</html>");
        runSnmpMonitorService();
        runSysLogService();
        runBusinessService();
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        //runSnmpMonitorService();
        //runSysLogService();
        runBusinessService();
        try {

            if (s_log == null)
                s_log = Category.getInstance(Service.class);
            String reqtype = request.getParameter(ServiceUtil.HDR_ServiceRequestType);
            if (reqtype == null) {
                reqtype = request.getHeader(ServiceUtil.HDR_ServiceRequestType);
            }
            if (reqtype == null)
                throw new Ex().set(EChange.E_UNKNOWN, new Message("Service Request  commond is null."));

            if (reqtype.equalsIgnoreCase(ServiceUtil.STR_REQTP_ServiceDataPost)) {
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            } else if (reqtype.equalsIgnoreCase(ServiceUtil.STR_REQTP_ServiceControlPost)) {

                //ChangeControlRequest req = new ChangeControlRequest(request);
                if (s_log.isDebugEnabled())
                    s_log.debug("Data Control Request received.");
                String commandBody = request.getParameter(ServiceUtil.Str_MonitorCommond);
                if (commandBody == null) {
                    commandBody = request.getHeader(ServiceUtil.Str_MonitorCommond);
                }
                if (commandBody == null) {
                    commandBody = "";
                }
                //???????????????
                if (commandBody.equalsIgnoreCase("")) {
                    s_log.debug("Data Control bad Request commandBody:" + commandBody);
                    response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                }

                DataAttributes fp = reciveServiceControl(request);
                byte[] data = null;
                if (fp.getValue("Command") == null) {
                    if (fp.getStatus().isSuccess()) {
                        String responsecode = fp.getProperty(ServiceUtil.Str_ResponseProcessStatus);
                        if (responsecode != null) {
                            int status = Integer.parseInt(responsecode);
                            response.setStatus(status);
                            if (fp.isResultData()) {
                                data = DataAttributes.readInputStream(fp.getResultData());
                                response.setContentLength(data.length);
                                response.getOutputStream().write(data);
                            }
                        }
                    } else {
                        response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                    }
                } else {
                    if (fp.getResultData() != null)
                        data = DataAttributes.readInputStream(fp.getResultData());
                    else
                        data = fp.getContent();
                    response.setContentLength(data.length);
                    response.getOutputStream().write(data);
                }
                response.flushBuffer();
                response.setStatus(HttpServletResponse.SC_OK);
            } else {

                s_log.error("The Request Type test '" + reqtype + "' is unrecognizable.");
                response.sendError(HttpServletResponse.SC_BAD_REQUEST,
                        "<HTML><BODY>The Request Type  '" + reqtype +
                                "' is unrecognizable.</Body></HTML>");
            }
        } catch (Ex Ex) {    // Server error; report to client.

            s_log.info("Could not process the request from " + Ex.getMessage());

            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR
                    , " - Could not process the request from " +
                    ": " + Ex.getMessage());

            s_log.error("" + HttpServletResponse.SC_INTERNAL_SERVER_ERROR +
                    " - Could not process the request: ", Ex);

        } catch (RuntimeException Ex) {
            s_log.error("Run-time exception is caught:- ", Ex);
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR
                    , " - Could not process the request from " +
                    ": " + Ex.getMessage());
        }
    }

    public void init(String path, String fileName) throws ServletException {
        runSnmpMonitorService();
        runSysLogService();
        runBusinessService();
        try {
            s_log = Category.getInstance(Service.class);
            //NdcProvider.push(ChangeServerImp.Str_DataCollectionService);
            m_fileseparator = System.getProperty("file.separator");
            //License.validator();
            //License.start();
            if (path == null) {
                throw new Ex().set(EChange.E_UNKNOWN, new Message("The required property 'monitor.home' is not set."));
            }

            PropertyConfigurator.configure(path + m_fileseparator + "etc" + m_fileseparator + "log4j.properties");
            if (s_log.isDebugEnabled())
                s_log.debug("Servlet Monitor Server is starting.");

            try {
                FileInputStream is = new FileInputStream(path + m_fileseparator + "etc" + m_fileseparator + "main.properties");
                Properties props = new Properties();
                props.load(new BufferedInputStream(is));
                Properties sysProps = System.getProperties();
                sysProps.putAll(props);

            } catch (IOException Ex) {
                s_log.error("Failed to read the property file " + path +
                        "/etc/main.properties", Ex);
                throw new Ex().set(EChange.E_UNKNOWN, new Message("Failed to read the property file{0}/etc/main.properties", path));
            }

            m_configName = fileName;

            if (m_configName == null) {
                new Ex().set(EChange.E_UNKNOWN, new Message("The required Config File  is not set."));
            }
            String ichangeHomePath = System.getProperty("monitor.home");
            if (ichangeHomePath == null || ichangeHomePath == "") {
                throw new Ex().set(E.E_InvalidArgument, new Message("????????????."));
            }
            m_configPath = ichangeHomePath + m_fileseparator + "repository" + m_fileseparator;
            String strConfigFile = m_configPath + m_configName;
            //String strConfigRuleFile = m_configPath + configRuleFile;
            if (s_log.isDebugEnabled())
                s_log.debug("config file name:" + strConfigFile);

            //try {


            //config(strConfigFile);
            clearTempFile();


            //} catch (Ex ex) {
            //========================
            //add the NDC information
            //========================
            //NdcProvider.push(ChangeServerImp.Str_DataCollectionService);
            // s_log.error("init ServiceConfig failed " + ex.getMessage());
            // throw new Ex().set(EChange.E_UNKNOWN, ex, new Message("Failed to ServiceConfig initialize"));
            //}
            if (s_log.isDebugEnabled())
                s_log.debug("ServiceConfig initialized.");


        } catch (Ex Ex) {
            s_log.error("Init/Config failed.", Ex);
        } catch (RuntimeException Ex) {
            s_log.error("RuntimeException was caught during servlet initialization.", Ex);
            throw Ex;
        }

    }

    public void clearTempFile() {
        String tempDir = System.getProperty("java.io.tmpdir");
        File tempDirs = new File(tempDir);
        if (tempDirs.exists()) {
            File[] tempFiles = tempDirs.listFiles();
            for (int i = 0; i < tempFiles.length; i++) {
                tempFiles[i].delete();
            }
        }
    }

    public DataAttributes reciveServiceControl(HttpServletRequest req) throws Ex {
        DataAttributes result = new DataAttributes();
        String common = req.getParameter(ServiceUtil.Str_MonitorCommond);
        if (common == null || common == "") {
            common = req.getHeader(ServiceUtil.Str_MonitorCommond);
        }
        if (common == null || common == "") {
            throw new Ex().set(E.E_ObjectNotFound, new Message("Commond is null or empty."));
        }


        IServiceCommondProcess serviceCommonProcess = ServiceMonitorFactory.createServiceCommon(common);
        Enumeration enumeration = req.getParameterNames();
        Enumeration reqheader = req.getHeaderNames();

        while (enumeration.hasMoreElements()) {
            String hdr = (String) enumeration.nextElement();
            result.putValue(hdr.toLowerCase(), req.getParameter(hdr));
            //s_log.info("request parameter:" + hdr + " value:" + req.getParameter(hdr));
        }
        while (reqheader.hasMoreElements()) {
            String hdr = (String) reqheader.nextElement();
            result.putValue(hdr.toLowerCase(), req.getHeader(hdr));
            //s_log.info("request hdr:" + hdr + " value:" + req.getHeader(hdr));
        }

        try {
            if (req.getContentLength() > 0)
                result.setResultData(DataAttributes.readInputStream(req.getInputStream()));
            result = serviceCommonProcess.process("", result);
        } catch (IOException e) {
            throw new Ex().set(E.E_IOException, new Message("Request get Stream error."));
        }
        return result;

    }

    private void runSnmpMonitorService() {
        if (Service.isRunSnmpMonitorService) {
            return;
        }
        MonitorAgentDao agent = new MonitorAgentDao();
        List<MonitorAgent> agents = agent.listMonitorAgent(200, 1);
        if (agents != null && agents.size() >0) {
            EquipmentDao equs = new EquipmentDao();
            Pagination<Equipment> equpage = equs.listDevice(200, 1);
            if (equpage != null) {
                snmpservice.init(equpage.getItems(), agents);
                new Thread(snmpservice).start();
            }
        }
        Service.isRunSnmpMonitorService = true;

    }

    private void runSysLogService() {
        if (Service.isRunSysLogService) {
            return;
        }

        syslogservice.config(null, 1514, "GBK");
        syslogservice.setPlatform("YZJW");

        syslogservice.start();
        Service.isRunSysLogService = true;

    }

    private void runBusinessService() {
        if (Service.isRunBusinessMonitorService) {
            return;
        }
        ExchangePlatformDao platform = new ExchangePlatformDao();
        Pagination<ExchangePlatform> platforms = platform.listExchangePlatform(100, 1);
        if (platforms != null && platforms.getItems() != null) {

//             BusinessRegisterDao equs = new BusinessRegisterDao();
//       Pagination<BusinessRegister> equpage = equs.listBusinessRegister(100, 1, configBean.getPlatform_name());
//            // businessService.init(equpage.getItems(), configBean.getPlatform_name(), configBean.getPlatform_ip(), configBean.getPlatform_port(), "audit", "audit");
            businessService.init(platforms.getItems());
            new Thread(businessService).start();

        }
        /**
         * 终端信息缓存服务
         */
        terminainfService.start();

        /**
         * 违规处理策略服务
         */
        sysabnormalIfService.start();

        //级联上报运行状态
        WebServiceUplinkCronTriggerRunner.start();
        try {
            alert.init();
        } catch (Ex ex) {
            s_log.warn("alert init error.", ex);
        }
        Service.isRunBusinessMonitorService = true;
    }

    private void runPingMonitorService() {
        if(Service.ippingIsRun){
            return;
        }
        if(pingMonitorService == null){
            pingMonitorService = new PingMonitorService();
        }
        if(pingMonitorService.isRun()){
            Service.ippingIsRun = true;
            return;
        }
        new Thread(pingMonitorService).start();
        Service.ippingIsRun=true;
    }

    public static void runInSnmpMonitorService() {
        if (Service.inSnmpIsRun) {
            return;
        }

        if (inSnmpService == null) {
            inSnmpService = new InSnmpMonitorService();
        }
        if (inSnmpService.isRun()) {
            Service.inSnmpIsRun = true;
            return;
        }
//        //获取探针配置中的
//        ConfigDao config = (ConfigDao) ApplicationContextUtil.getBean("configDAO");
//        Pagination<ConfigBean> paginatin = config.getConfig();
//        if (paginatin == null) {
//            logger.warn("configDAO reader Config is null.");
//            return;
//        }
//        if (paginatin.getItems() != null && paginatin.getTotalCount() > 0) {
//            ConfigBean configBean = (ConfigBean) paginatin.getItems().get(0);
            //获取探针的端口号
//            int port = Integer.parseInt(configBean.getPort().trim());
            //获取设备的信息
            EquipmentDao equs = new EquipmentDao();
            Pagination<Equipment> equpage = equs.listDevice(200, 1);
            //得到SNMPOID的信息
            SnmpOIDDao snmpOIDDao = new SnmpOIDDao();
            inSnmpService.init(equs.listDevice(200, 1).getItems(), snmpOIDDao.listSnmpOID(200, 1).getItems());
            new Thread(inSnmpService).start();
//        }
        Service.inSnmpIsRun = true;
    }
    public static boolean m_bPrivate = false;
    public static boolean m_public = false;
    public static boolean m_publicConfigured = false;
    public static boolean m_bPrivateStart = false;
    public static boolean m_publicStart = false;

    public static CommandDisposer m_dispService = null;
    public static CommandDisposer m_dispPlatform = null;
    public static int I_SleepTime = 30 * 1000;
    public static String m_configPath = null;
    public static String m_fileseparator = null;
    public static String m_configName = null;
    public static String m_config = null;
    public static String m_externalFilename = null;
    public static boolean m_configred = false;
    public static boolean m_platformConfigred = false;
    public static ServiceInitThread m_serInitThread = null;
    public static byte[] m_typeStatus = null;
    public static LogServiceThread logService = new LogServiceThread();

    public static String m_times = null;
    public static ReStartTimerTask m_restart = null;
    public static BusinessDataBeanSet busDataSet = new BusinessDataBeanSet();
    //public static DeviceDataBeanSet deviceDataSet = new DeviceDataBeanSet();
    public static boolean isRunSnmpMonitorService = false;
    public static boolean isRunSysLogService = false;
    public static boolean isRunBusinessMonitorService = false;
    public static SnmpMonitorService snmpservice = new SnmpMonitorService();
    //public static SysLogMonitorService syslogservice = new SysLogMonitorService();
    public static SyslogServer syslogservice = new SyslogServer();
    public static BusinessPlatformService businessService = new BusinessPlatformService();
    public static AlarmService alert = new AlarmService();
    public static TerminalinfService terminainfService = new TerminalinfService();
    public static SysAbnormalIfService sysabnormalIfService = new SysAbnormalIfService();


}
