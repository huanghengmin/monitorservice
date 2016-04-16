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
 * User: 王兴
 * Date: 2005-7-30
 * Time: 18:51:26
 * To change this template use File | Settings | File Templates.
 */
public final class AuditFactory {
    private static Jdbc m_jdbc = null;
    private static HashMap m_cache = null;
    private static DatabaseUtil m_dbUtil = null;

    /**
     * 初始化操作，完成从数据库取出统计记录的功能.
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
     * 从数据库中取出已有的统计记录。
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
     * 更新统计记录，存入数据库中
     *
     * @param auditRecords
     */

    private static void updateDb(AuditRecord[] auditRecords) {

    }

    /**
     * 创建统计记录
     *
     * @param changeType
     * @param location
     * @param dbname
     * @param tableName
     * @param destdb
     * @return 统计记录 (AuditRecord)
     */
    public static AuditRecord createAuditRecord(String changeType, String location, String dbname, String tableName, AuditDestDb destdb) {

        return new AuditRecord();
    }
}
