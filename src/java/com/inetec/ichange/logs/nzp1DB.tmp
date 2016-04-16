package com.inetec.ichange.logs;


import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.io.UnsupportedEncodingException;

import com.inetec.ichange.logs.util.LogCachce;
import net.sf.json.JSONArray;


/**
 * ��־��¼��
 * Created by IntelliJ IDEA.
 * User: ���˻�
 * Date: 2009-04-11
 * Time: 22:27:29
 * To change this template use File | Settings | File Templates.
 */
public class LogHelper {

    private LogCachce m_loger;
    private boolean m_bPrivate;
    private String ip = "";
    private String appType = "";
    private String appName = "";
    private String souORDes = "s";
    private String tableName = "";
    public static final String Str_souORDes_Source = "s";
    public static final String Str_souORDes_Dest = "d";
    private static final String dateFormat = "yyyy-MM-dd HH:mm:ss";
    public static final String Str_CharacterSet = "gb2312";
    private String dbName = "";
    private String recordCount = "0";

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    private String level = "info";
    private String statusCode = "";
    private String message = "";

    private String filename = "";
    private String pk_id = "";
    private String dest_url = "";
    private String operType = "";
    private String userName = "";

    //add by wxh time 2009.04-11
    private String client_ip;
    private String client_mac;
    private String source_id;
    private String source_ip;
    private String source_port;
    private String dest_id;
    private String dest_ip;
    private String dest_port;
    private String protocol;
    private String flux = "0";
    private String maxconnect;
    private String curconnect;
    private String date;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getFlux() {
        return flux;
    }

    public void setFlux(String flux) {
        this.flux = flux;
    }

    public String getClient_ip() {
        return client_ip;
    }//add by wxh time 2007.10.13

    public void setClient_ip(String client_ip) {
        this.client_ip = client_ip;
    }

    public String getClient_mac() {
        return client_mac;
    }

    public void setClient_mac(String client_mac) {
        this.client_mac = client_mac;
    }

    public void setClinet_MAC(String clinet_mac) {
        this.client_mac = clinet_mac;
    }

    public String getSource_id() {
        return source_id;
    }

    public void setSource_id(String source_id) {
        this.source_id = source_id;
    }

    public String getSource_ip() {
        return source_ip;
    }

    public void setSource_ip(String source_ip) {
        this.source_ip = source_ip;
    }

    public String getDest_id() {
        return dest_id;
    }

    public void setDest_id(String dest_id) {
        this.dest_id = dest_id;
    }

    public String getSource_port() {
        return source_port;
    }

    public void setSource_port(String source_port) {
        this.source_port = source_port;
    }

    public String getDest_ip() {
        return dest_ip;
    }

    public void setDest_ip(String dest_ip) {
        this.dest_ip = dest_ip;
    }

    public String getDest_port() {
        return dest_port;
    }

    public void setDest_port(String dest_port) {
        this.dest_port = dest_port;
    }

    public String getCurconnect() {
        return curconnect;
    }

    public void setCurconnect(String curconnect) {
        this.curconnect = curconnect;
    }

    public String getMaxconnect() {
        return maxconnect;
    }

    public void setMaxconnect(String maxconnect) {
        this.maxconnect = maxconnect;
    }


    public String getProtocol() {
        return protocol;
    }

    public void setProtocol(String protocol) {
        this.protocol = protocol;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }


    public void setLogCachce(LogCachce logcachce) {
        m_loger = logcachce;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPk_id() {
        return pk_id;
    }

    public void setPk_id(String pk_id) {
        this.pk_id = pk_id;
    }

    public String getDest_url() {
        return dest_url;
    }

    public void setDest_url(String dest_url) {
        this.dest_url = dest_url;
    }

    public String getOperate() {
        return operType;
    }

    public void setOperate(String operate) {
        this.operType = operate;
    }

    public void setOpertion(String operate) {
        this.operType = operate;
    }

    public String getAppType() {
        return appType;
    }

    public void setAppType(String appType) {
        this.appType = appType;
    }

    public void setapptype(String appType) {
        this.appType = appType;
    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public void setappname(String appName) {
        this.appName = appName;
    }


    public String getSouORDes() {
        return souORDes;
    }

    public void setSouORDes(String souORDes) {
        this.souORDes = souORDes;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getDbName() {
        return dbName;
    }

    public void setDbName(String dbName) {
        this.dbName = dbName;
    }

    public String getRecordCount() {
        return recordCount;
    }

    public void setRecordCount(String recordCount) {
        if (recordCount == null || recordCount.equals("")) {
            recordCount = "0";
        }
        this.recordCount = recordCount;
    }

    public String getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }

    public void setStatus(String statusCode) {
        this.statusCode = statusCode;
    }

    protected LogHelper(boolean bprivate, LogCachce m_cachce) {
        m_loger = null;
        m_bPrivate = false;
        //ip = LogHelperFactory.m_ip;
        appType = null;
        appName = null;
        souORDes = null;
        tableName = null;
        dbName = null;
        recordCount = null;
        level = null;
        statusCode = null;
        message = null;
        m_bPrivate = bprivate;
        m_loger = m_cachce;
        filename = null;
        pk_id = null;
        dest_url = null;
        operType = null;
        userName = null;
        client_ip = "";
        client_mac = "";
        source_id = null;
        source_ip = null;
        source_port = null;
        dest_id = null;
        dest_ip = null;
        dest_port = null;
        protocol = "sip";

        maxconnect = "0";
        curconnect = "0";
        String value = (new SimpleDateFormat("yyyy-MM-dd hh:mm:ss")).format(new Date(System.currentTimeMillis()));
        date = value;


    }

    public LogHelper() {

    }

    public void info(String message) {
        level = "info";
        this.message = message;
        m_loger.cachceLog(toByteArrays());
    }

    public void info(String message, Throwable throwable) {
        level = "info";
        this.message = message + throwable.toString();
        m_loger.cachceLog(toByteArrays());
    }

    public void debug(String message) {
        level = "debug";
        this.message = message;
        m_loger.cachceLog(toByteArrays());
    }

    public void debug(String message, Throwable throwable) {
        level = "debug";
        this.message = message + throwable.toString();
        m_loger.cachceLog(toByteArrays());
    }

    public void warn(String message) {
        level = "warn";
        this.message = message;
        m_loger.cachceLog(toByteArrays());
    }

    public void warn(String message, Throwable throwable) {
        level = "warn";
        this.message = message + throwable.toString();
        m_loger.cachceLog(toByteArrays());
    }

    public void error(String message) {
        level = "error";
        this.message = message;
        m_loger.cachceLog(toByteArrays());
    }

    public void error(String message, Throwable throwable) {
        level = "error";
        this.message = message + throwable.toString();
        m_loger.cachceLog(toByteArrays());
    }

    public String toString() {
        StringBuffer buff = new StringBuffer();
        String value = (new SimpleDateFormat("yyyy-MM-dd hh:mm:ss")).format(new Date(System.currentTimeMillis()));
        date = value;
        buff.append(value);
        buff.append(";");
        buff.append("level=");
        buff.append(level);
        buff.append(";");
        buff.append("apptype=");
        buff.append(appType);
        buff.append(";");
        buff.append("appname=");
        buff.append(appName);
        buff.append(";");
        buff.append("network=");
        if (m_bPrivate)
            buff.append("t");
        else
            buff.append("f");
        buff.append(";");
        buff.append("ip=");
        buff.append(ip);
        buff.append(";");
        if (souORDes != null) {
            buff.append("source_dest=");
            buff.append(souORDes);
            buff.append(";");
        }
        if (dbName != null) {
            buff.append("database=");
            buff.append(dbName);
            buff.append(";");
        }
        if (tableName != null) {
            buff.append("table=");
            buff.append(tableName);
            buff.append(";");
        }
        if (statusCode != null) {
            buff.append("status_code=");
            buff.append(statusCode);
            buff.append(";");
        }
        if (recordCount != null) {
            buff.append("recordCount=");
            buff.append(recordCount);
            buff.append(";");
        }
        if (message != null) {
            buff.append("app_info=");
            buff.append(message);
        }
        return buff.toString();
    }

    public String toStringJSO() {
        StringBuffer buff = new StringBuffer();
        buff.append("{'date':'");
        String value = (new SimpleDateFormat("yyyy-MM-dd hh:mm:ss")).format(new Date(System.currentTimeMillis()));
        date = value;
        buff.append(value);
        buff.append("',");
        buff.append("'level':'");
        buff.append(level);
        buff.append("',");
        buff.append("'apptype':'");
        buff.append(appType);
        buff.append("',");
        buff.append("'appname':'");
        buff.append(appName);
        buff.append("',");
        buff.append("'network':'");
        if (m_bPrivate)
            buff.append("t'");
        else
            buff.append("f'");
        buff.append(",");
        buff.append("'ip':'");
        buff.append(ip);
        buff.append("',");
        if (souORDes != null) {
            buff.append("'source_dest':'");
            buff.append(souORDes);
            buff.append("',");
        }
        if (dbName != null) {
            buff.append("'database':'");
            buff.append(dbName);
            buff.append("',");
        }
        if (tableName != null) {
            buff.append("'table':'");
            buff.append(tableName);
            buff.append("',");
        }
        if (statusCode != null) {
            buff.append("'status':");
            buff.append(statusCode);
            buff.append(",");
        }
        if (recordCount != null) {
            buff.append("'recordCount':");
            buff.append(recordCount);
            buff.append(",");
        }
        if (message != null) {
            buff.append("'app_info':'");
            buff.append(message);
            buff.append("'");
        }
        buff.append("}");
        return buff.toString();
    }

    /* {'appName':'name1',
    'appType':'db',
    'network':'t',
    'auditLevel':'error',
    'ip':'t',
    'date':'2007-10-12 23:12:22',
    'sourceDest':'D',
    'databaseName':'testDB',
    'tableName':'testTable',
    'statusCode':1,
    'recordcount':12,
    'appInfo':'�ɹ�',
    'destUrl':'http://www.sina.com.cn',
    'userName':'test',
    'operType':'����URL',
    'fileName':'test.temp',
    'pkId':13}*/

    public String toStringNewJSO() {

        StringBuffer buff = new StringBuffer();
        buff.append("{'appName':'");
        buff.append(appName);
        buff.append("',");
        buff.append("'appType':'");
        buff.append(appType);
        buff.append("',");
        buff.append("'network':'");
        if (m_bPrivate)
            buff.append("t'");
        else
            buff.append("f'");
        buff.append(",");
        buff.append("'auditLevel':'");
        buff.append(level);
        buff.append("',");
        buff.append("'ip':'");
        buff.append(ip);
        buff.append("',");
        buff.append("'date':'");
        String value = (new SimpleDateFormat("yyyy-MM-dd hh:mm:ss")).format(new Date(System.currentTimeMillis()));
        date = value;
        buff.append(value);
        buff.append("',");

        if (souORDes == null) {
            souORDes = "";
        }
        buff.append("'sourceDest':'");
        buff.append(souORDes);
        buff.append("',");
        if (dbName == null) {
            dbName = "";
        }
        buff.append("'databaseName':'");
        buff.append(dbName);
        buff.append("',");
        if (tableName == null) {
            tableName = "";
        }
        buff.append("'tableName':'");
        buff.append(tableName);
        buff.append("',");
        if (statusCode == null) {
            statusCode = "0";
        }
        buff.append("'status':");
        buff.append(statusCode);
        buff.append(",");
        if (recordCount == null) {
            recordCount = "0";
        }
        buff.append("'recordcount':");
        buff.append(recordCount);
        buff.append(",");
        if (message == null) {
            message = "";
        }
        buff.append("'appInfo':'");
        buff.append(message);
        buff.append("',");
        if (dest_url == null) {
            dest_url = "";
        }
        buff.append("'destUrl':'");
        buff.append(dest_url);
        buff.append("',");
        if (userName == null) {
            userName = "";
        }
        buff.append("'userName':'");
        buff.append(userName);
        buff.append("',");
        if (operType == null) {
            operType = "";
        }
        buff.append("'operType':'");
        buff.append(operType);
        buff.append("',");
        if (filename == null) {
            filename = "";
        }
        buff.append("'fileName':'");
        buff.append(filename);
        buff.append("',");
        if (pk_id == null) {
            pk_id = "";
        }
        buff.append("'pkId':'");
        buff.append(pk_id);
        buff.append("'");
        buff.append("}");
        return buff.toString();
    }


    public String toStringSipJSO() {

        StringBuffer buff = new StringBuffer();
        buff.append("{'date':'");
        String value = (new SimpleDateFormat("yyyy-MM-dd hh:mm:ss")).format(new Date(System.currentTimeMillis()));
        date = value;
        buff.append(value);
        buff.append("',");
        buff.append("'level':'");
        buff.append(level);
        buff.append("',");
        buff.append("'apptype':'");
        if (appType == null) {
            appType = "sip";
        }
        buff.append(appType);
        buff.append("',");
        buff.append("'appname':'");
        buff.append(appName);
        buff.append("',");
        /*buff.append("'network':'");
        if (m_bPrivate)
            buff.append("t'");
        else
            buff.append("f'");
        buff.append(",");*/

        if (client_ip == null) {
            client_ip = "";
        }
        buff.append("'client_ip':'");
        buff.append(client_ip);
        buff.append("',");
        if (client_mac == null) {
            client_mac = "";
        }
        buff.append("'client_mac':'");
        buff.append(client_mac);
        buff.append("',");
        if (tableName == null) {
            tableName = "";
        }
        buff.append("'source_id':'");
        buff.append(source_id);
        buff.append("',");

        if (source_ip == null) {
            source_ip = "0";
        }
        buff.append("'source_ip':'");
        buff.append(source_ip);
        buff.append("',");
        if (source_port == null) {
            source_port = "";
        }
        buff.append("'source_port':'");
        buff.append(source_port);
        buff.append("',");
        if (dest_id == null) {
            dest_id = "";
        }
        buff.append("'dest_id':'");
        buff.append(dest_id);
        buff.append("',");
        if (dest_ip == null) {
            dest_ip = "";
        }
        buff.append("'dest_ip':'");
        buff.append(dest_ip);
        buff.append("',");
        if (dest_port == null) {
            dest_port = "";
        }
        buff.append("'dest_port':'");
        buff.append(dest_port);
        buff.append("',");
        if (protocol == null) {
            protocol = "";
        }
        buff.append("'protocol':'");
        buff.append(protocol);
        buff.append("',");
        if (operType == null) {
            operType = "";
        }
        buff.append("'opertion':'");
        buff.append(operType);
        buff.append("',");
        if (statusCode == null) {
            statusCode = "0";
        }
        buff.append("'status':'");
        buff.append(statusCode);
        buff.append("',");
        buff.append("'flux':'");
        buff.append(flux);
        buff.append("'");
        buff.append("}");
        return buff.toString();
    }

    public String toStringAuditJSO() {

        StringBuffer buff = new StringBuffer();
        buff.append("{'date':'");
        String value = (new SimpleDateFormat("yyyy-MM-dd hh:mm:ss")).format(new Date(System.currentTimeMillis()));
        date = value;
        buff.append(value);
        buff.append("',");
        buff.append("'level':'");
        buff.append(level);
        buff.append("',");
        buff.append("'apptype':'");
        if (appType == null) {
            appType = "";
        }
        buff.append(appType);
        buff.append("',");
        buff.append("'appname':'");
        buff.append(appName);
        buff.append("',");
        buff.append("'maxconnect':'");
        buff.append(maxconnect);
        buff.append("',");
        buff.append("'curconnect':'");
        buff.append(curconnect);
        buff.append("',");
        if (client_ip == null) {
            client_ip = "";
        }
        buff.append("'client_ip':'");
        buff.append(client_ip);
        buff.append("',");
        if (client_mac == null) {
            client_mac = "";
        }
        buff.append("'client_mac':'");
        buff.append(client_mac);
        buff.append("',");
        if (tableName == null) {
            tableName = "";
        }
        buff.append("'source_id':'");
        buff.append(source_id);
        buff.append("',");

        if (source_ip == null) {
            source_ip = "0";
        }
        buff.append("'source_ip':'");
        buff.append(source_ip);
        buff.append("',");
        if (source_port == null) {
            source_port = "";
        }
        buff.append("'source_port':'");
        buff.append(source_port);
        buff.append("',");
        if (dest_id == null) {
            dest_id = "";
        }
        buff.append("'dest_id':'");
        buff.append(dest_id);
        buff.append("',");
        if (dest_ip == null) {
            dest_ip = "";
        }
        buff.append("'dest_ip':'");
        buff.append(dest_ip);
        buff.append("',");
        if (dest_port == null) {
            dest_port = "";
        }
        buff.append("'dest_port':'");
        buff.append(dest_port);
        buff.append("',");
        if (protocol == null) {
            protocol = "";
        }
        buff.append("'protocol':'");
        buff.append(protocol);
        buff.append("',");
        if (operType == null) {
            operType = "";
        }
        buff.append("'opertion':'");
        buff.append(operType);
        buff.append("',");
        if (statusCode == null) {
            statusCode = "0";
        }
        buff.append("'status':'");
        buff.append(statusCode);
        buff.append("',");
        buff.append("'flux':'");
        buff.append(flux);
        buff.append("'");
        buff.append("}");
        return buff.toString();
    }

    public byte[] toByteArrays() {
        byte result[] = null;
        StringBuffer buff = new StringBuffer();
        //buff.append("<#ichange>");
        buff.append(toStringAuditJSO());
        //buff.append("<#/ichange>");
        buff.append(",");
        try {
            result = buff.toString().getBytes(Str_CharacterSet);
        } catch (UnsupportedEncodingException e) {
            System.err.println("编码出错");
        }
        return result;
    }

    public static LogHelper[] getLogHelper(byte[] logs) {

        //InputStream in = logServer.getInputStream();
        List result = null;
        String audit_info = new String(logs);
        // String audit_info =
        // "{'appName':'Test','appType':'db','network':'f','auditLevel':'info','ip':'localhost','date':'2007-10-16
        // 14:16:37','sourceDest':'d','databaseName':'db1','tableName':'table1','statusCode':0,'recordcount':10,'appInfo':'','destUrl':'http://192.168.0.100:8060/console','userName':'test1','fileName':'c:\\download\\temp.data','pkId':'pk;111:sss'},";

        if (!audit_info.equals("")) {
            //ȥ������","
            //logger.info(audit_info);
            audit_info = "["
                    + audit_info.substring(0,
                    audit_info.lastIndexOf('}') + 1) + "]";
            // logger.info("json: "+audit_info);
            //JSONArray json = JSONArray.fromString(audit_info);


        }
        return (LogHelper[]) result.toArray(new LogHelper[0]);
    }

}
