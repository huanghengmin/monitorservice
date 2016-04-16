package com.inetec.ichange.service.monitor.alarm;

import org.apache.log4j.Logger;

import java.net.SocketAddress;
import java.io.Serializable;


/**
 * Created by IntelliJ IDEA.
 * User: bluesky
 * Date: 2009-3-31
 * Time: 18:13:17
 * To change this template use File | Settings | File Templates.
 */
public class RtPUdpDataBean implements Comparable, Serializable {
    final static Logger logger = Logger.getLogger(RtPUdpDataBean.class);
    /**
     * ���ݰ�id
     */
    private int id;
    /**
     * ���ݰ�����
     */
    private int length;
    /**
     * ��������
     */
    private byte[] data = new byte[0];

    public static final int code_data = 1;
    /**
     * һ��������,���ݰ���Ӧ.Ϊ�����ݰ�ȫ����,�����а�ȫ����,�����ʴ�ķ�ʽʵ��
     */
    public static final int code_data_arc = 2;
    public static final int code_data_ok = 3; //�������ݻ�Ӧ��-���ݰ��Ѿ��������,�뷢��һ�����ݰ�
    public static final int code_data_err_ind = -2; //�������ݻ�Ӧ-��ǰ�����ճ���,����ų���,��������������ڵȴ���,�����Ҫ�İ�
    public static String splitStr = "\r\n\r\n";

    public int getId() {
        return id;
    }

    public RtPUdpDataBean() {

    }

    public RtPUdpDataBean(int id, int length, byte[] data) {
        this.id = id;
        this.length = length;
        this.data = data;
    }

    /**
     * ����ת����byte.
     */
    public byte[] toBytes() {

        return data;
    }

    public String toString() {
        return new String(toBytes());
    }

    /**
     * �����ݽ�������Ӧ�Ķ���
     *
     * @param data
     * @return
     */
    public synchronized static RtPUdpDataBean getUdpDataBean(byte[] data) {
        if (data.length < 8) {
            return null;
        }
        RtPUdpDataBean bean = new RtPUdpDataBean();
        if (data.length > 4)
            bean.setId(bytesToUIntInt(data, 2));
        bean.setLength(data.length);
        bean.setData(data);
        return bean;
    }


    public void setId(int id) {
        this.id = id;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public byte[] getData() {
        return data;
    }

    public void setData(byte[] data) {
        this.data = data;
        this.length = data.length;
    }

    public void clear() {
        data = null;
        length = 0;
        id = 0;
    }

    public int compareTo(Object o) {
        RtPUdpDataBean s = (RtPUdpDataBean) o;
        return getId() > s.getId() ? 1 : (getId() == s.getId() ? 0 : -1);

    }

    public synchronized static java.util.ArrayList getUdpDataBeanSet(byte[] val) {
        java.util.ArrayList al = new java.util.ArrayList();
        byte[] split = splitStr.getBytes();
        int len = val.length;
        int len2 = split.length;

        int startPos = 0;
        for (int i = 0; i < len; i++) {
            int j = 0;
            if (len - i < len2) break;
            while (j < len2) {
                if (val[i + j] == split[j]) {
                    j++;
                    continue;
                } else
                    break;
            }
            if (j == len2) {
                byte[] b = new byte[i - startPos];
                System.arraycopy(val, startPos, b, 0, i - startPos);
                RtPUdpDataBean databean = RtPUdpDataBean.getUdpDataBean(b);
                if (databean != null)
                    al.add(databean);
                i += len2;
                startPos = i;
            }
        }
        if (len - startPos > 0) {
            byte[] b = new byte[len - startPos];
            System.arraycopy(val, startPos, b, 0, len - startPos);
            RtPUdpDataBean databean = RtPUdpDataBean.getUdpDataBean(b);
            if (databean != null)
                al.add(databean);
        }
        java.util.Collections.sort(al);
        return al;
    }

    /**
     * ��һ������ת����4�ֽ�����,ע��:��λ��ǰ,��λ�ں�.��Ϊ����ͨ�Ų��ܱ�֤���пͻ��˶�ʹ����ͬ��CPU,�ڴ�洢��ʽ���ܲ�ͬ
     *
     * @param val Ҫת����INTֵ
     * @return ת������ֽ�����
     */
    public static byte[] int32ToBytes(int val) {
        byte[] ret = new byte[4];
        /*for(int i=0;i<4;i++){
              int tmp = val>>>(24-i*8);
              ret[i] = (byte)(tmp&0xff);
          }*/
        int hi = val / (256 * 256);
        int low = val % (256 * 256);
        ret[3] = (byte) (hi / 256);
        ret[2] = (byte) (hi % 256);
        ret[1] = (byte) (low / 256);
        ret[0] = (byte) (low % 256);
        return ret;
    }

    /**
     * ��һ��4�ֽ�����ת����һ��INT��ֵ,����λ��ǰ,��λ�ں���֯.���������ж��ֽ������С,������ȫ���
     *
     * @param val Ҫת�����ֽ�����
     * @return ת�����INT��ֵ
     */
    public static int bytesToInt32(byte[] val) {
        int ret = 0;
        ret = ((val[0] & 0xff) + (val[1] & 0xff) * 256) + ((val[2] & 0xff) + (val[3] & 0xff) * 256) * 256;
        /*for(int i=0;i<4;i++)
          {
              ret = ret << 8;
              ret |= val[i]&0xff;
          }*/
        return ret;
    }

    /**
     * �ж���������������,��Ч��
     *
     * @param val ����������
     * @return ��Ч-TRUE,��Ч-FALSE
     */
    public static boolean isValidData(byte[] val) {
        boolean ret = false;
        int len = 0;
        if (val == null) return false;
        if ((val[0] & 0xff) == 0xAA) {
            switch (val[1] & 0xff) {
                case code_data_arc:
                case code_data:
                    byte[] tmp = new byte[4];
                    System.arraycopy(val, 2, tmp, 0, 4);
                    len = bytesToInt32(tmp);
                    if ((val[0] & 0xff) == 0xAA && (val[6 + len] & 0xff) == 0xFF)
                        ret = true;
                    break;
            }
        }
        return ret;
    }

    public static int bytesToUIntInt(byte[] bytes, int index) {
        int accum = 0;
        int i = 1;
        for (int shiftBy = 0; shiftBy < 16; shiftBy += 8) {
            accum |= ((long) (bytes[index + i] & 0xff)) << shiftBy;
            i--;
        }
        return accum;
    }
}