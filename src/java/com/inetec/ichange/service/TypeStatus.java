package com.inetec.ichange.service;

/**
 * Created by IntelliJ IDEA.
 * User: bluesky
 * Date: 2009-3-27
 * Time: 19:54:28
 * To change this template use File | Settings | File Templates.
 */
public class TypeStatus {
    /**
     */
    public static final TypeStatus T_TypeRuning = new TypeStatus(0);
    /**
     * Ӧ�� ֹͣ(����)
     */
    public static final TypeStatus T_TypeStop = new TypeStatus(10);
    /**
     * Ӧ�� ֹͣ������ (״̬)
     */
    public static final TypeStatus T_TypeStopIng = new TypeStatus(11);
    /**
     * Ӧ�� ����(����)
     */
    public static final TypeStatus T_TypeRun = new TypeStatus(1);
    /**
     * Ӧ�� ������.(״̬)
     */
    public static final TypeStatus T_NotExitType = new TypeStatus(-2);

    /**
     * Ӧ�� ���г���.(״̬)
     */
    public static final TypeStatus T_TypeRunError = new TypeStatus(-4);
    /**
     * Ӧ�� ֹͣ����.(״̬)
     */
    public static final TypeStatus T_TypeStopError = new TypeStatus(-3);

    /**
     * Ӧ��״̬����֪.(״̬)
     */
    public static final TypeStatus T_TypeUNKNOWN = new TypeStatus(-1);

    private int status = 0;

    private TypeStatus(int typestatus) {
        this.status = typestatus;
    }

    public static TypeStatus getStatus(int i) {
        TypeStatus result = null;
        switch (i) {
            case 0:
                result = T_TypeRuning;
                break;
            case 1:
                result = T_TypeRun;
                break;
            case 10:
                result = T_TypeStop;
                break;
            case 11:
                result = T_TypeStopIng;
                break;
            case -2:
                result = T_NotExitType;
                break;
            case -4:
                result = T_TypeRunError;
                break;
            case -1:
                result = T_TypeUNKNOWN;
                break;
            case -3:
                result = T_TypeStopError;
                break;
            default:
                result = T_TypeUNKNOWN;
                break;

        }
        return result;
    }

    public int getStatus() {
        return status;
    }
}
