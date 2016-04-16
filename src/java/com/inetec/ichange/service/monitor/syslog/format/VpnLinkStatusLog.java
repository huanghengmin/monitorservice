package com.inetec.ichange.service.monitor.syslog.format;

import com.inetec.ichange.service.monitor.databean.BusinessLog;
import com.inetec.ichange.service.monitor.databean.Equipment;
import com.inetec.ichange.service.monitor.databean.EquipmentLog;
import com.inetec.ichange.service.monitor.uplink.databean.TOutlinkinfDao;
import com.inetec.ichange.service.monitor.uplink.databean.TOutlinkinfDataBean;
import com.inetec.ichange.service.monitor.uplink.databean.TenimalOperationAuditDataBean;
import com.inetec.ichange.service.monitor.uplink.databean.TernimalAccessAuditDataBean;
import org.apache.commons.lang.StringUtils;

import java.sql.Timestamp;

/**
 * Created with IntelliJ IDEA.
 * User: bluesky
 * Date: 12-4-5
 * Time: ‰∏ãÂçà3:23
 * To change this template use File | Settings | File Templates.
 */
public class VpnLinkStatusLog implements ILogFormat {
    private static final String S_Provider = "Provider=";
    private static final String S_Separator_Keys = " ";
    private static final String S_Separator_KeyValue = "=";
    private static final String S_Provider_ChinaMobile = "chinamobile";
    private static final String S_Provider_ChinaUnicom = "chinaunicom";
    private static final String S_Provider_ChinaTeleCom = "chinatelecom";
    private static final String S_Provider_ChinaMobile_Code = "001";
    private static final String S_Provider_ChinaUnicom_Code = "002";
    private static final String S_Provider_ChinaTeleCom_Code = "003";
    private static final String S_Provider_Other_Code = "003";

    private String log;
    /**
     * id
     */
    public String equid;
    /*
     *ipµÿ÷∑
     */
    public String ip;
    private String level;

    private String Provider;
    private String BindWidth;
    private String devip;
    private String LinkStatus;
    private String Delay;
    private String Time;


    public long getIn_Flux() {

        return 0;
    }

    public long getOut_Flux() {

        return 0;
    }

    @Override
    public int getDelay() {
        if (Delay != null && Delay.equalsIgnoreCase("")) {
            return Integer.parseInt(Delay);
        }
        return 0;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public void process(String log) {
        this.log = log;
        if (validate(log)) {

            String[] keyvalues = log.split(S_Separator_Keys);
            for (int i = 0; i < keyvalues.length; i++) {
                if (keyvalues[i].startsWith("deviceid=")) {
                    equid = keyvalues[i].substring("deviceid=".length());
                }
                if (keyvalues[i].startsWith("ip=")) {
                    ip = keyvalues[i].substring("ip=".length());
                }
                if (keyvalues[i].startsWith("Provider=")) {
                    Provider = keyvalues[i].substring("Provider=".length());
                    if (Provider.endsWith( "\""))
                        Provider = Provider.substring(0, Provider.length() - 1);
                    if (Provider.startsWith("\"")) {
                        Provider = Provider.substring(1);
                    }
                }
                if (keyvalues[i].startsWith("BandWidth=")) {
                    BindWidth = keyvalues[i].substring("BindWidth=".length());
                    if (BindWidth.endsWith("\""))
                        BindWidth = BindWidth.substring(0, BindWidth.length() - 1);
                    if (BindWidth.startsWith("\"")) {
                        BindWidth = BindWidth.substring(1);
                    }
                }
                if (keyvalues[i].startsWith("ip=")) {
                    devip = keyvalues[i].substring("ip=".length());
                    if (devip.endsWith("\""))
                        devip = devip.substring(0, devip.length() - 1);
                    if (devip.startsWith("\"")) {
                        devip = devip.substring(1);
                    }
                }
                if (keyvalues[i].startsWith("LinkStatus=")) {
                    LinkStatus = keyvalues[i].substring("LinkStatus=".length());
                    if (LinkStatus.endsWith("" +
                            "\""))
                        LinkStatus = LinkStatus.substring(0, LinkStatus.length() - 1);
                    if (LinkStatus.startsWith("\"")) {
                        LinkStatus = LinkStatus.substring(1);
                    }
                }
                if (keyvalues[i].startsWith("Delay=")) {
                    Delay = keyvalues[i].substring("Delay=".length());
                    if (Delay.endsWith( "\""))
                        Delay = Delay.substring(0, Delay.length() - 1);
                    if (Delay.startsWith("\"")) {
                        Delay = Delay.substring(1);
                    }
                }
                if (keyvalues[i].startsWith("Time=")) {
                    Time = keyvalues[i].substring("Time=".length());
                    if (Time.endsWith("" +
                            "\""))
                        Time = Time.substring(0, Time.length() - 1);
                    if (Time.startsWith("\"")) {
                        Time = Time.substring(1);
                    }
                }


            }

        }
    }

    @Override
    public void process(String log, String level) {
        //To change body of implemented methods use File | Settings | File Templates.
        this.level = level;
        process(log);
    }

    @Override
    public boolean validate(String log) {
        boolean result = false;


        if (StringUtils.containsIgnoreCase(log, S_Provider)
                && StringUtils.containsIgnoreCase(log, S_Provider))
            result = true;
        return result;
    }

    @Override
    public BusinessLog getBussinessLog() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public EquipmentLog getEquipmentLog() {
        return null;

    }

    @Override
    public Equipment getEquipment() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public TOutlinkinfDataBean getOutlink() {

        TOutlinkinfDataBean result = new TOutlinkinfDao().getOutlinkByLinkType(getProviderCode(Provider));
        if(result==null){
            result=new TOutlinkinfDataBean();

        }
        result.setLink_bandwidth(Integer.parseInt(BindWidth));
        result.setLink_Corp(getProviderCode(Provider));
        result.setIp("171.168.1.0");
        result.setMask("255.255.255.0");
        result.setGateway("171.168.1.2");
        //result.setLink_type();
        return result;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public TenimalOperationAuditDataBean getTenimalOperationLog() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public TernimalAccessAuditDataBean getTenimalAccessLog() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    private String getProviderCode(String provider) {
        String result = S_Provider_Other_Code;
        if (provider.equalsIgnoreCase(S_Provider_ChinaMobile))
            result = S_Provider_ChinaMobile_Code;
        if (provider.equalsIgnoreCase(S_Provider_ChinaUnicom))
            result = S_Provider_ChinaUnicom_Code;
        if (provider.equalsIgnoreCase(S_Provider_ChinaTeleCom))
            result = S_Provider_ChinaTeleCom_Code;
        return result;
    }

    public static void main(String arg[]) throws Exception {
        String log = "Provider=ChinaMobile BandWidth=20004 IP=192.168.20.20 LinkStatus=0 Delay=575 Time=2012-04-20 03:18:32";
        VpnLinkStatusLog vpnlog=new VpnLinkStatusLog();
        vpnlog.process(log,"level");
        System.out.println("vpn link bandwidth:"+vpnlog.getOutlink().getLink_bandwidth());
    }
}
