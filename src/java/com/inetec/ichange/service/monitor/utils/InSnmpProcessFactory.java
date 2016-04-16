package com.inetec.ichange.service.monitor.utils;

import com.inetec.ichange.service.monitor.snmp.*;

/**
 * Created by IntelliJ IDEA.
 * User: Administrator
 * Date: 12-11-28
 * Time: ÏÂÎç4:11
 * To change this template use File | Settings | File Templates.
 */
public class InSnmpProcessFactory {
    public static IInSnmpProcess getSnmpProcessByVer(String ver, String product, String type) {
        IInSnmpProcess result = null;

        if (product.equalsIgnoreCase("autodevice")) {
            result = new InSnmpProcessV2Random();
            return result;

        }

        if (ver.equalsIgnoreCase("v2")) {
            if (type.equalsIgnoreCase("pcserver")) {
                result = new InHostSnmpProcessV2Imp();
                return result;
            }
            if (product.equalsIgnoreCase("leadsec") && (type.equalsIgnoreCase("firewall") || type.equalsIgnoreCase("ips"))) {
                result = new InLenovoProcessV2Imp();
                return result;
            }
            if (product.equalsIgnoreCase("netchina") && (type.equalsIgnoreCase("firewall"))) {
                result = new InSnmpProcessV2Imp();
                return result;

            }

            result = new InSnmpProcessV2Imp();

        }



        return result;

    }
}
