package com.inetec.ichange.service.monitor.syslog.format;

import com.inetec.ichange.service.monitor.databean.BusinessLog;
import com.inetec.ichange.service.monitor.databean.Equipment;
import com.inetec.ichange.service.monitor.databean.EquipmentLog;
import com.inetec.ichange.service.monitor.uplink.databean.TOutlinkinfDataBean;
import com.inetec.ichange.service.monitor.uplink.databean.TenimalOperationAuditDataBean;
import com.inetec.ichange.service.monitor.uplink.databean.TernimalAccessAuditDataBean;

/*
*璁惧鏃ュ織鏍煎紡澶勭悊鎺ュ彛
*/
public interface ILogFormat {
    public static final String S_Deviceid = "deviceid";
    public static final String S_ip = "ip";

    /*
    *澶勭悊瀛楃涓烘棩蹇楁牸寮忥紝瑙ｆ瀽瀵瑰簲鍊间负瀵硅薄灞炴?
    */
    public void process(String log,String level);

    /**
     * 楠岃瘉鏍煎紡姝ｇ‘銆?
     *
     * @param log
     * @return
     */
    public boolean validate(String log);

    /**
     * 业务日志对象
     *
     * @return
     */
    public BusinessLog getBussinessLog();

    /**
     * 设备日志对象
     *
     * @return
     */
    public EquipmentLog getEquipmentLog();

    /**
     *  设备信息对象
     * @return
     */
    public Equipment getEquipment();

    /**
     *  外部链路信息对象
     * @return
     */
    public TOutlinkinfDataBean getOutlink();

    /**
     * 终端用户操作日志
     */
    public TenimalOperationAuditDataBean getTenimalOperationLog();

    /**
     * 终端用户访问日志
     */
    public TernimalAccessAuditDataBean getTenimalAccessLog();

    /**
     * 内流量
     * @return
     */
    public long getIn_Flux();
    /**
     * 外流量
     * @return
     */
    public long getOut_Flux();

    /**
     * 延时情况
     */


    public int getDelay();



}
