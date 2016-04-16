package com.inetec.ichange.service.monitor.syslog.format;

import com.inetec.ichange.service.monitor.databean.BusinessLog;
import com.inetec.ichange.service.monitor.databean.Equipment;
import com.inetec.ichange.service.monitor.databean.EquipmentLog;
import com.inetec.ichange.service.monitor.uplink.databean.TOutlinkinfDataBean;
import com.inetec.ichange.service.monitor.uplink.databean.TenimalOperationAuditDataBean;
import com.inetec.ichange.service.monitor.uplink.databean.TernimalAccessAuditDataBean;

/*
*设备日志格式处理接口
*/
public interface ILogFormat {
    public static final String S_Deviceid = "deviceid";
    public static final String S_ip = "ip";

    /*
    *处理字符为日志格式，解析对应值为对象属�?
    */
    public void process(String log,String level);

    /**
     * 验证格式正确�?
     *
     * @param log
     * @return
     */
    public boolean validate(String log);

    /**
     * ҵ����־����
     *
     * @return
     */
    public BusinessLog getBussinessLog();

    /**
     * �豸��־����
     *
     * @return
     */
    public EquipmentLog getEquipmentLog();

    /**
     *  �豸��Ϣ����
     * @return
     */
    public Equipment getEquipment();

    /**
     *  �ⲿ��·��Ϣ����
     * @return
     */
    public TOutlinkinfDataBean getOutlink();

    /**
     * �ն��û�������־
     */
    public TenimalOperationAuditDataBean getTenimalOperationLog();

    /**
     * �ն��û�������־
     */
    public TernimalAccessAuditDataBean getTenimalAccessLog();

    /**
     * ������
     * @return
     */
    public long getIn_Flux();
    /**
     * ������
     * @return
     */
    public long getOut_Flux();

    /**
     * ��ʱ���
     */


    public int getDelay();



}
