package com.inetec.ichange.logs.util;

import org.apache.log4j.Category;

import java.util.ArrayList;
import java.io.InputStream;
import java.io.IOException;
import java.io.ByteArrayOutputStream;

/**
 * Created by IntelliJ IDEA.
 * User: wxh
 * Date: 2005-10-27
 * Time: 22:59:11
 * To change this template use File | Settings | File Templates.
 */
public class MegeByteArrayOutputStream extends ByteArrayOutputStream {
    private final static Category m_logger = Category.getInstance(MegeByteArrayOutputStream.class);
    private ArrayList isList = new ArrayList();
    public static int I_MaxSize = 20;

    public void addByteArrayOutputStream(ByteArrayOutputStream is) {
        isList.add(is);
    }

    /**
     * Reads the next byte of data from this input stream. The value
     * byte is returned as an <code>int</code> in the range
     * <code>0</code> to <code>255</code>. If no byte is available
     * because the end of the stream has been reached, the value
     * <code>-1</code> is returned.
     * <p/>
     * This <code>read</code> method
     * cannot block.
     *
     * @return the next byte of data, or <code>-1</code> if the end of the
     *         stream has been reached.
     */
    public byte[] toByteArray() {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        for (int i = 0; i < isList.size(); i++) {
            ByteArrayOutputStream out1 = (ByteArrayOutputStream) isList.get(i);
            try {
                out.write(out1.toByteArray());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        clear();
        return out.toByteArray();
    }

    public void clear() {
        isList.clear();
    }
}
