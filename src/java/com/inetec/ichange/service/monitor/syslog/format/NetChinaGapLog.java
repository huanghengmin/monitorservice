package com.inetec.ichange.service.monitor.syslog.format;

import com.inetec.ichange.service.monitor.databean.BusinessLog;
import com.inetec.ichange.service.monitor.databean.Equipment;
import com.inetec.ichange.service.monitor.databean.EquipmentLog;
import com.inetec.ichange.service.monitor.uplink.databean.TOutlinkinfDataBean;
import com.inetec.ichange.service.monitor.uplink.databean.TenimalOperationAuditDataBean;
import com.inetec.ichange.service.monitor.uplink.databean.TernimalAccessAuditDataBean;

/**
 * net china gap sys log format process;
 */
public class NetChinaGapLog implements ILogFormat {


    public void process(String log) {
        // TODO Auto-generated method stub

    }
    @Override
    public int getDelay() {
        return 0;  //To change body of implemented methods use File | Settings | File Templates.
    }
    public long getIn_Flux() {

        return 0;
    }

    public long getOut_Flux() {

        return 0;
    }

    @Override
    public void process(String log, String level) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public boolean validate(String log) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public BusinessLog getBussinessLog() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public EquipmentLog getEquipmentLog() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public Equipment getEquipment() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public TOutlinkinfDataBean getOutlink() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }


    @Override
    public TenimalOperationAuditDataBean getTenimalOperationLog() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public TernimalAccessAuditDataBean getTenimalAccessLog() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

}
