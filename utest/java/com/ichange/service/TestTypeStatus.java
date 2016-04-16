package com.ichange.service;

import com.inetec.ichange.service.utils.DbInfo;
import com.inetec.ichange.service.utils.TypeStatus;
import com.inetec.ichange.service.utils.TypeStatusSet;
import com.inetec.ichange.service.utils.XmlSaxParser;
import com.inetec.ichange.service.Service;
import com.inetec.ichange.main.api.DataAttributes;

import java.io.FileInputStream;
import java.util.HashMap;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: bluesky
 * Date: 2007-11-13
 * Time: 22:51:36
 * To change this template use File | Settings | File Templates.
 */
public class TestTypeStatus {

    private TypeStatusSet m_typeset = new TypeStatusSet();

    /**
     * @param args
     * @throws com.ichange.common.utils.OSMonitSystemInfo.NegativeCPUTime
     *
     */
    public static void main(String[] args) throws Exception {
        TestTypeStatus test = new TestTypeStatus();
        test.testTypeStatus();
    }

    public void testTypeStatus() throws Exception {
        XmlSaxParser xmlParser = new XmlSaxParser();
        m_typeset = xmlParser.parse(new FileInputStream("D:\\inetec\\ichange\\platformService\\src\\resources\\typestart_i.xml"));
        TypeStatusSet typeset = xmlParser.parse(new FileInputStream("D:\\inetec\\ichange\\platformService\\src\\resources\\typestart.xml"));
        mergerTypeStatus(typeset);
        Service.m_typeStatus = DataAttributes.readInputStream(m_typeset.getDataInputStream());
        System.out.println("type status:" + new String(Service.m_typeStatus));

    }

    private void mergerTypeStatus(TypeStatusSet set) {
        TypeStatusSet typeset = new TypeStatusSet();
        HashMap imap = m_typeset.toMap();
        HashMap emap = set.toMap();
        List iarray = m_typeset.getTypeStatus();
        List earray = set.getTypeStatus();
        if (imap.size() >= emap.size()) {
            for (int i = 0; i < iarray.size(); i++) {
                TypeStatus type = (TypeStatus) iarray.get(i);
                TypeStatus type2 = (TypeStatus) emap.get(type.getName());
                if (type2 != null) {
                    if (type2.getExternalSourceDb() != null) {
                        type.setExternalSourceDb(type2.getExternalSourceDb());
                    }
                    DbInfo[] dbInfoArray = type2.getExternalTargetDbS();
                    for (int j = 0; j < dbInfoArray.length; j++) {
                        type.setExternalTargetDb(dbInfoArray[j]);
                    }

                }
                typeset.setTypeStatus(type);
            }
        } else {
            for (int i = 0; i < earray.size(); i++) {
                TypeStatus type = (TypeStatus) earray.get(i);
                TypeStatus type2 = (TypeStatus) imap.get(type.getName());
                if (type2 != null) {
                    if (type2.getInternalSourceDb() != null) {
                        type.setInternalSourceDb(type2.getInternalSourceDb());
                    }
                    DbInfo[] dbInfoArray = type2.getInternalTargetDbS();
                    for (int j = 0; j < dbInfoArray.length; j++) {
                        type.setInternalTargetDb(dbInfoArray[j]);
                    }

                }
                typeset.setTypeStatus(type);
            }
        }

        m_typeset = typeset;
    }
}
