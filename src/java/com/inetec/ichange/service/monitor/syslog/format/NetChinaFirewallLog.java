package com.inetec.ichange.service.monitor.syslog.format;

import com.inetec.ichange.service.monitor.databean.BusinessLog;
import com.inetec.ichange.service.monitor.databean.Equipment;
import com.inetec.ichange.service.monitor.databean.EquipmentLog;
import com.inetec.ichange.service.monitor.uplink.databean.TOutlinkinfDataBean;
import com.inetec.ichange.service.monitor.uplink.databean.TenimalOperationAuditDataBean;
import com.inetec.ichange.service.monitor.uplink.databean.TernimalAccessAuditDataBean;

import java.sql.Timestamp;

/**
 * net china forewall sys log format process;
 */
public class NetChinaFirewallLog implements ILogFormat {
    /**
     * id
     */
    public String equid;
    /*
     *ipµÿ÷∑
     */
    public String ip;
    private String log;

    @Override
    public int getDelay() {
        return 0;  //To change body of implemented methods use File | Settings | File Templates.
    }
	public void process(String log) {
        this.log = log;

	}

    @Override
    public void process(String log, String level) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
	public boolean validate(String log) {
		// TODO Auto-generated method stub
		return true;
	}

    @Override
    public BusinessLog getBussinessLog() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public EquipmentLog getEquipmentLog() {
        EquipmentLog log=new EquipmentLog();
        log.setEquipment_name(equid);
        log.setLog_time(new Timestamp(System.currentTimeMillis()));
        log.setLog_info(this.log);
        return log;
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

    @Override
    public long getIn_Flux() {
        return 0;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public long getOut_Flux() {
        return 0;  //To change body of implemented methods use File | Settings | File Templates.
    }

}
