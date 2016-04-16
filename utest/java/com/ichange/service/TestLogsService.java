package com.ichange.service;

import com.inetec.ichange.logs.service.LogServiceThread;
import com.ichange.common.utils.AuditClient;
import org.apache.log4j.Logger;
import com.inetec.unitest.UniTestCase;
import com.inetec.common.exception.Ex;
import junit.framework.TestCase;

import java.io.IOException;

/**
 * Created by IntelliJ IDEA.
 * User: bluesky
 * Date: 2007-12-25
 * Time: 14:19:31
 * To change this template use File | Settings | File Templates.
 */
public class TestLogsService extends TestCase {
    private final static Logger m_log = Logger.getLogger(TestLogsService.class);
    LogServiceThread service = new LogServiceThread();


    public TestLogsService() {
        try {
            service.init(8090);
            service.config("userid", "password", 4, 2);
        } catch (IOException e) {
            m_log.warn("IOException :", e);
        }
    }

    /**
     *
     */
    public void testLogsServiceUserValidation() throws Exception {
        AuditClient client = new AuditClient();
        client.init("127.0.0.1", 8090);
        boolean result = client.logon("userid", "001");
        client.close();
        assertEquals(false, result);
        AuditClient client1 = new AuditClient();
        client1.init("127.0.0.1", 8090);
        result = client1.logon("userid", "password");
        assertEquals(true, result);
    }

    /**
     * @throws Exception
     */
    public void testLogsServiceSysCache() throws Exception {
        while (true) {
            service.addLog(("{'appName':'Test','appType':'db','network':'f','auditLevel':'info','ip':'localhost','date':" +
                    "'2007-12-26 16:38:31','sourceDest':'d','databaseName':'db1','tableName':'table1','statusCode':0," +
                    "'recordcount':0,'appInfo':'','destUrl':'http://192.168.0.100:8060/console','userName':'test1'," +
                    "'operType':'access url','fileName':'c:\\download\\����.data','pkId':'pk;111:sss'},{'appName':'Test'," +
                    "'appType':'db','network':'f','auditLevel':'info','ip':'localhost','date':'2007-12-26 16:38:31','sourceDest':'d'," +
                    "'databaseName':'db1','tableName':'table1','statusCode':0,'recordcount':0,'appInfo':'','destUrl':" +
                    "'http://192.168.0.100:8060/console','userName':'test1','operType':'access url','fileName':" +
                    "'c:\\download\\����.data','pkId':'pk;111:sss'},").getBytes());
            Thread.sleep(10);
        }


    }

    /**
     * @throws Exception
     */
    public void testLogsServiceSysCacheOutMaxSize() throws Exception {
        service.addLog(("{'appName':'Test','appType':'db','network':'f','auditLevel':'info','ip':'localhost','date':" +
                "'2007-12-26 16:38:31','sourceDest':'d','databaseName':'db1','tableName':'table1','statusCode':0," +
                "'recordcount':0,'appInfo':'','destUrl':'http://192.168.0.100:8060/console','userName':'test1'," +
                "'operType':'access url','fileName':'c:\\download\\����.data','pkId':'pk;111:sss'},{'appName':'Test'," +
                "'appType':'db','network':'f','auditLevel':'info','ip':'localhost','date':'2007-12-26 16:38:31','sourceDest':'d'," +
                "'databaseName':'db1','tableName':'table1','statusCode':0,'recordcount':0,'appInfo':'','destUrl':" +
                "'http://192.168.0.100:8060/console','userName':'test1','operType':'access url','fileName':" +
                "'c:\\download\\����.data','pkId':'pk;111:sss'},").getBytes());
    }

    /**
     * @throws Exception
     */
    public void testLogsServiceSysCacheOutMaxDay() throws Exception {
        service.addLog(("{'appName':'Test','appType':'db','network':'f','auditLevel':'info','ip':'localhost','date':" +
                "'2007-12-26 16:38:31','sourceDest':'d','databaseName':'db1','tableName':'table1','statusCode':0," +
                "'recordcount':0,'appInfo':'','destUrl':'http://192.168.0.100:8060/console','userName':'test1'," +
                "'operType':'access url','fileName':'c:\\download\\����.data','pkId':'pk;111:sss'},{'appName':'Test'," +
                "'appType':'db','network':'f','auditLevel':'info','ip':'localhost','date':'2007-12-26 16:38:31','sourceDest':'d'," +
                "'databaseName':'db1','tableName':'table1','statusCode':0,'recordcount':0,'appInfo':'','destUrl':" +
                "'http://192.168.0.100:8060/console','userName':'test1','operType':'access url','fileName':" +
                "'c:\\download\\����.data','pkId':'pk;111:sss'},").getBytes());
    }

    /**
     * @throws Exception
     */
    public void testLogsServiceReadSysCache() throws Exception {
        AuditClient client1 = new AuditClient();
        while (true) {
            try {
                if (client1.isLogon()) {
                    System.out.println(new String(client1.readAuditLog()));
                } else {
                    client1.init("127.0.0.1", 8090);
                    client1.logon("userid", "password");
                }
                Thread.sleep(10 * 1000);
            } catch (Ex ex) {
                ex.printStackTrace();
            }
        }
    }
}
