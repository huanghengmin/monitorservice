package com.inetec.ichange.audit;

/**
 * Created by IntelliJ IDEA.
 * User: Õı–Àª·
 * Date: 2005-7-30
 * Time: 18:49:40
 * To change this template use File | Settings | File Templates.
 */
public class AuditRecord {
    public void setLocation(String m_location) {
        this.m_location = m_location;
    }

    public void setSourceTableName(String m_sourceTableName) {
        this.m_sourceTableName = m_sourceTableName;
    }

    public void setChangeType(String m_changeType) {
        this.m_changeType = m_changeType;
    }

    public void setSourceDbName(String m_sourceDbName) {
        this.m_sourceDbName = m_sourceDbName;
    }

    public void setSourceNumber(int m_sourceNumber) {
        this.m_sourceNumber = m_sourceNumber;
    }

    public void setDestDbArray(AuditDestDb[] m_destDbArray) {
        this.m_destDbArray = m_destDbArray;
    }

    public String getChangeType() {
        return m_changeType;
    }

    public String getLocation() {
        return m_location;
    }

    public String geSourceDbName() {
        return m_sourceDbName;
    }

    public String getSourceTableName() {
        return m_sourceTableName;
    }

    public int getSourceNumber() {
        return m_sourceNumber;
    }

    public AuditDestDb[] getDdestDbArray() {
        return m_destDbArray;
    }

    public String m_changeType = null;
    public String m_location = null;
    public String m_sourceDbName = null;
    public String m_sourceTableName = null;
    public int m_sourceNumber = 0;
    public AuditDestDb[] m_destDbArray = null;

    protected AuditRecord(String changeType, String location, String dbname, String tableName, int number, AuditDestDb[] destdb) {
        m_changeType = changeType;
        m_location = location;
        m_sourceDbName = dbname;
        m_sourceTableName = tableName;
        m_sourceNumber = number;
        m_destDbArray = destdb;
    }

    protected AuditRecord() {

    }

}
