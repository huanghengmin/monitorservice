package com.inetec.ichange.main.api;


import com.inetec.common.config.nodes.IChange;
import com.inetec.common.exception.Ex;

import java.io.InputStream;

/**
 * ����������ڶ��档ƽ̨�������ݺ���ö�Ӧ�������������������ݵĺ���
 *
 * @author inetec System
 * @version 1.0
 */
public interface ITargetPlugin {
    /**
     * �ⶨ�������������Խ��յ������ݵĺ�������������ʽ
     * ��Ϊ���������ļ����֡��������Ҫ����ƽ̨������������
     * ��ʲô�������ݴ���������
     *
     * @return ����ֵΪ 1����������ʽ������ֵΪ2�����ļ���
     *         ��ʽ������ֵΪ3��ʾ�����������������������ִ���������
     */
    public int[] getCapabilities();

    /**
     * ��ʼ������������
     *
     * @param frp  ����������������Ӧ������������� �����
     *             ����ʹ��ͬһ���͵����������������������֮���ܹ�
     *             ���е�ͨ�š�.
     * @param type ��ƽ̨������ʶ���ݵĹؽ��֡�
     * @param dc   �ɼ��ӿڣ����������ݴ��䷽������������
     * @throws Ex ���д����ʱ��
     */
    void init(IChangeMain dc, IChangeType type, ISourcePlugin frp) throws Ex;
    /**
     * ����: ���������ļ� (inFilename).
     * �ڷ���֮ǰ������DataAttributes�������ļ����ͺ��ļ�����ֵ
     * ����ֵ˵��: ���������󣬰�������״̬���������ݡ�
     */
    /**
     * ��ʾ�ܹ���������ʽ�����ݡ�
     */
    public static final int I_StreamCapability = 1;
    /**
     * ��ʾ�ܹ������ļ���ʽ������
     */
    public static final int I_FileCapability = 2;

    public DataAttributes process(String collectionType
            , DataAttributes props
            , String inFilename) throws Ex;

    /**
     * ����������
     *
     * @param collectionType �ɼ�����
     * @param props          ���ݿظ�����
     * @param is             ������������
     * @return ��ο����Ʒ�����˵����
     * @throws Ex ���д�������
     */
    public DataAttributes process(String collectionType
            , DataAttributes props
            , InputStream is) throws Ex;

    /**
     * ͨ������ͨ�����Ƶ�ǰӦ�ã���������������
     *
     * @param command ���õ�����: disconnect, controlwrite, or getstatus; or
     *                other customer control command
     * @param fp      ִ�п�����������Լ��ϡ�
     * @throws Ex ���д������ʱ
     */
    DataAttributes control(String command, DataAttributes fp) throws Ex;

    public void config(IChange ichange) throws Ex;

    public boolean configred();

}
