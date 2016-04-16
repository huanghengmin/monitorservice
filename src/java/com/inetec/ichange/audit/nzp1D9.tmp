package com.inetec.ichange.audit;

import com.inetec.common.exception.ErrorCode;
import com.inetec.common.exception.E;
import com.inetec.common.exception.Ex;
import com.inetec.common.i18n.Message;


/**
 * Created by IntelliJ IDEA.
 * User: Õı–Àª·
 * Date: 2005-7-30
 * Time: 18:36:28
 * To change this template use File | Settings | File Templates.
 */
public class LogAgent {

    public static final String Str_Location_private = "I";
    public static final String Str_Location_public = "O";
    public static final String Str_SourceOrDest_Source = "S";
    public static final String Str_SourceOrDest_Dest = "D";
    public static final String Str_AppType_Dbchange = "dbchange";
    public static final String Str_MessageLevel_ERROR = "1";
    public static final String Str_MessageLevel_WARN = "2";
    public static final String Str_MessageLevel_INFO = "3";
    public static final String Str_MessageLevel_DEBUG = "4";

    private String appType = null;
    private String appName = null;
    private String location = null;
    private String souORDes = null;
    private String tableName = null;
    private String dbName = null;
    private int recordCount = 0;
    private String message = null;
    private String messageLevel = null;
    private int errorCode = 0;
    private long createTime = 0;

    public String getAppType() {
        return appType;
    }


    public String getAppName() {
        return appName;
    }


    public String getLocation() {
        return location;
    }


    public String getSouORDes() {
        return souORDes;
    }


    public String getTableName() {
        return tableName;
    }


    public String getDbName() {
        return dbName;
    }


    public int getRecordCount() {
        return recordCount;
    }


    public String getMessage() {
        return message;
    }


    public String getMessageLevel() {
        return messageLevel;
    }


    public int getErrorCode() {
        return errorCode;
    }


    public long getCreateTime() {
        return createTime;
    }

    public void set(String appType, String appName, String location, String souORDes, String dbName, String tableName, int recordCount, String message, String messageLevel, ErrorCode errorCode) throws Ex {
        if (appType == null) {
            this.appType = Str_AppType_Dbchange;
        } else {
            this.appType = appType;
        }
        if (appName == null) {
            throw (Ex) new Ex().set(E.E_OperationFailed, new Message("Change Type value is null."));
        }
        if (location == null) {
            throw (Ex) new Ex().set(E.E_OperationFailed, new Message(" Location  value is null."));
        }
        if (!location.equalsIgnoreCase(Str_Location_private) && !location.equalsIgnoreCase(Str_Location_public)) {
            throw (Ex) new Ex().set(E.E_OperationFailed, new Message(" Location  value is invalid."));
        }
        this.location = location;
        if (souORDes == null) {
            throw (Ex) new Ex().set(E.E_OperationFailed, new Message(" Source or Dest  value is null."));
        }
        if (!souORDes.equalsIgnoreCase(Str_SourceOrDest_Dest) && !souORDes.equalsIgnoreCase(Str_SourceOrDest_Source)) {
            throw (Ex) new Ex().set(E.E_OperationFailed, new Message(" Source or Dest    value is invalid."));
        }
        this.souORDes = souORDes;
        this.dbName = dbName;
        this.tableName = tableName;
        this.recordCount = recordCount;
        this.message = message;
        if (messageLevel == null) {
            throw (Ex) new Ex().set(E.E_OperationFailed, new Message("MessageLevel value is null."));
        }
        if (!messageLevel.equalsIgnoreCase(Str_MessageLevel_WARN) && !messageLevel.equalsIgnoreCase(Str_MessageLevel_INFO) &&
                !messageLevel.equalsIgnoreCase(Str_MessageLevel_ERROR) && !messageLevel.equalsIgnoreCase(Str_MessageLevel_DEBUG)) {
            throw (Ex) new Ex().set(E.E_OperationFailed, new Message("Message level    value is invalid."));
        }
        this.messageLevel = messageLevel;
        if (errorCode == null) {
            this.errorCode = 0;
        } else {
            this.errorCode = errorCode.toInt();
        }
        this.createTime = System.currentTimeMillis();
    }


}
