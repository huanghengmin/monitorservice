package com.inetec.ichange.audit;

import com.inetec.common.config.nodes.Jdbc;
import com.inetec.common.db.DatabaseUtil;
import com.inetec.common.exception.Ex;
import com.inetec.common.exception.E;
import com.inetec.common.i18n.Message;

import java.util.HashMap;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;

/**
 * Created by IntelliJ IDEA.
 * User: ����
 * Date: 2005-7-30
 * Time: 18:51:26
 * To change this template use File | Settings | File Templates.
 */
public final class AuditFactory {
    private static Jdbc m_jdbc = null;
    private static HashMap m_cache = null;
    private static DatabaseUtil m_dbUtil = null;

    /**
     * ��ʼ����������ɴ����ݿ�ȡ��ͳ�Ƽ�¼�Ĺ���.
     *
     * @param jdbc
     */
    public static void init(Jdbc jdbc) throws Ex {
        m_jdbc = jdbc;
        getDbRecoved();
        m_dbUtil = new DatabaseUtil();
        m_dbUtil.config(jdbc);
    }

    /**
     * �����ݿ���ȡ�����е�ͳ�Ƽ�¼��
     */
    private static void getDbRecoved() throws Ex {
        Connection con = null;
        try {
            String sql = "select * from statistics ";
            con = m_dbUtil.getConnection();
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {


            }

        } catch (SQLException e) {
            throw new Ex().set(E.E_DatabaseError, e, new Message("Get audit record error."));
        }


    }

    /**
     * ����ͳ�Ƽ�¼���������ݿ���
     *
     * @param auditRecords
     */

    private static void updateDb(AuditRecord[] auditRecords) {

    }

    /**
     * ����ͳ�Ƽ�¼
     *
     * @param changeType
     * @param location
     * @param dbname
     * @param tableName
     * @param destdb
     * @return ͳ�Ƽ�¼ (AuditRecord)
     */
    public static AuditRecord createAuditRecord(String changeType, String location, String dbname, String tableName, AuditDestDb destdb) {

        return new AuditRecord();
    }
}
