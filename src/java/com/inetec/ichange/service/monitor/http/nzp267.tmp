package com.inetec.ichange.service.monitor.http;

import com.inetec.common.exception.Ex;
import com.inetec.ichange.service.monitor.databean.EquipmentDao;
import com.inetec.ichange.service.monitor.utils.ServiceMonitorFactory;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.URI;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.params.HttpClientParams;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;

import java.io.File;

/**
 * 探针 http client 接口
 * Created by IntelliJ IDEA.
 * User: bluesky
 * Date: 11-8-8
 * Time:
 * To change this template use File | Settings | File Templates.
 */
public class McHttpClient {
    Logger logger = Logger.getLogger(McHttpClient.class);
    private HttpClient client;
    private PostMethod postMethod;
    private String urlbegin;
    private String host;
    private String urlend;

    public byte[] vpnOnline(String beginno, String endno, int pagesize) throws Exception {
        byte[] result = null;
        postMethod.setURI(new URI(urlbegin + host + urlend));
        postMethod.addRequestHeader("SERVICEREQUESTTYPE", "SERVICECONTROLPOST");
        postMethod.addRequestHeader("Command", "onlinevpn");
        postMethod.addRequestHeader("beginno", beginno);
        postMethod.addRequestHeader("endno", endno);
        postMethod.addRequestHeader("pagesize", String.valueOf(pagesize));
        new EquipmentDao().getVpnDeviceIp();
        postMethod.addRequestHeader("deviceip", new EquipmentDao().getJBPGDeviceIp());
        postMethod.setHttp11(true);
        int code = client.executeMethod(postMethod);
        if (code == 200 && postMethod.getResponseContentLength() > 0) {

            result = postMethod.getResponseBody();
            logger.info("vpnOnline request command:onlinevpn,beginno:" + beginno + ",endno:" + endno + ",pagesize:" + pagesize
                    + "response body:" + new String(result,"gbk"));
        } else {
            result = postMethod.getResponseBody();
            logger.warn("vpnOnline request command:onlinevpn,beginno:" + beginno + ",endno:" + endno + ",pagesize:" + pagesize
                    + "response body:" + new String(result,"gbk"));
        }
        return result;
    }

    /*
    * block 终端阻断
    */
    public boolean vpnblock(String policeno, String ip) throws Exception {
        boolean result = false;
        postMethod.setURI(new URI(urlbegin + host + urlend));
        postMethod.addRequestHeader("SERVICEREQUESTTYPE", "SERVICECONTROLPOST");
        postMethod.addRequestHeader("Command", "block");
        postMethod.addRequestHeader("policeno", policeno);
        new EquipmentDao().getVpnDeviceIp();
        if (new EquipmentDao().getIPSecVPnIp() != null && !new EquipmentDao().getIPSecVPnIp().equalsIgnoreCase(""))
            postMethod.addRequestHeader("deviceip", new EquipmentDao().getIPSecVPnIp());
        else
            postMethod.addRequestHeader("deviceip", new EquipmentDao().getJBPGDeviceIp());
        postMethod.addRequestHeader("ip", ip);
        postMethod.setHttp11(true);
        int code = client.executeMethod(postMethod);
        if (code == 200) {

            result = true;
            logger.info("vpn block request command:block,policeno:" + policeno
                    + ",ip:" + ip + "response body:" + result);
        } else {
            result = false;
            logger.info("vpn block request command:block,policeno:" + policeno
                    + ",ip:" + ip + "response body:" + result);
        }
        return result;
    }

    /*
    * 终端阻断恢复
    */
    public boolean vpnnoblock(String policeno, String ip) throws Exception {
        boolean result = false;
        postMethod.setURI(new URI(urlbegin + host + urlend));
        postMethod.addRequestHeader("SERVICEREQUESTTYPE", "SERVICECONTROLPOST");

        postMethod.addRequestHeader("Command", "noblock");
        postMethod.addRequestHeader("policeno", policeno);
        new EquipmentDao().getVpnDeviceIp();
        if (new EquipmentDao().getIPSecVPnIp() != null && !new EquipmentDao().getIPSecVPnIp().equalsIgnoreCase(""))
            postMethod.addRequestHeader("deviceip", new EquipmentDao().getIPSecVPnIp());
        else
            postMethod.addRequestHeader("deviceip", new EquipmentDao().getJBPGDeviceIp());
        postMethod.addRequestHeader("ip", ip);
        postMethod.setHttp11(true);
        int code = client.executeMethod(postMethod);
        if (code == 200) {

            result = true;
            logger.info("vpn noblock request command:noblock,policeno:"
                    + policeno + ",ip:" + ip + "response body:" + result);
        } else {
            result = false;
            logger.info("vpn noblock request command:noblock,policeno:"
                    + policeno + ",ip:" + ip + "response body:" + result);
        }
        return result;
    }

    /*
    * 终端截屏
    */
    public byte[] viewvpn(String policeno, String ip) throws Exception {
        byte[] result = null;
        postMethod.setURI(new URI(urlbegin + host + urlend));
        postMethod.addRequestHeader("SERVICEREQUESTTYPE", "SERVICECONTROLPOST");
        postMethod.addRequestHeader("Command", "viewvpn");
        postMethod.addRequestHeader("policeno", policeno);
        new EquipmentDao().getVpnDeviceIp();
        postMethod.addRequestHeader("deviceip", new EquipmentDao().getTSRSDeviceIp());
        //postMethod.addRequestHeader("deviceip", "192.168.20.23");
        postMethod.addRequestHeader("ip", ip);
        postMethod.setHttp11(true);
        int code = client.executeMethod(postMethod);
        if (code == 200 && postMethod.getResponseContentLength() > 0) {
            result = postMethod.getResponseBody();
            logger.info("vpnview request command:viewvpn,policeno:"
                    + policeno + ",ip:" + ip + "response code:200 response body:");

        } else {
            result = postMethod.getResponseBody();
            logger.info("vpnview request command:viewvpn,policeno:"
                    + policeno + ",ip:" + ip + "response code:" + code);
        }
        return result;
    }

    /*
    * 终端系统信息
    */
    public byte[] osquery(String policeno, String ip) throws Exception {
        byte[] result = null;
        postMethod.setURI(new URI(urlbegin + host + urlend));
        postMethod.addRequestHeader("SERVICEREQUESTTYPE", "SERVICECONTROLPOST");
        postMethod.addRequestHeader("Command", "osquery");
        postMethod.addRequestHeader("policeno", policeno);
        postMethod.addRequestHeader("deviceip", new EquipmentDao().getTSRSDeviceIp());
        postMethod.addRequestHeader("ip", ip);
        postMethod.setHttp11(true);
        int code = client.executeMethod(postMethod);
        if (code == 200 && postMethod.getResponseContentLength() > 0) {
            logger.info("vpnOnline request command:osquery,policeno:"
                    + policeno + ",ip:" + ip + "response body:" + result);
        } else {
            result = postMethod.getResponseBody();
            logger.info("vpnOnline request command:osquery,policeno:"
                    + policeno + ",ip:" + ip + "response body:" + result);
        }
        return result;
    }

    /*
    * 终端网络系统信息
    */
    public byte[] netQuery(String policeno, String ip) throws Exception {
        byte[] result = null;
        postMethod.setURI(new URI(urlbegin + host + urlend));
        postMethod.addRequestHeader("SERVICEREQUESTTYPE", "SERVICECONTROLPOST");
        postMethod.addRequestHeader("Command", "osquery");
        postMethod.addRequestHeader("policeno", policeno);
        postMethod.addRequestHeader("deviceip", new EquipmentDao().getTSRSDeviceIp());
        postMethod.addRequestHeader("ip", ip);
        postMethod.setHttp11(true);
        int code = client.executeMethod(postMethod);
        if (code == 200 && postMethod.getResponseContentLength() > 0) {
            logger.info("vpnOnline request command:osquery,policeno:"
                    + policeno + ",ip:" + ip + "response body:" + result);
        } else {
            result = postMethod.getResponseBody();
            logger.info("vpnOnline request command:osquery,policeno:"
                    + policeno + ",ip:" + ip + "response body:" + result);
        }
        return result;
    }

    /*
    * 终端进程系统信息
    */
    public byte[] processQuery(String policeno, String ip) throws Exception {
        byte[] result = null;
        postMethod.setURI(new URI(urlbegin + host + urlend));
        postMethod.addRequestHeader("SERVICEREQUESTTYPE", "SERVICECONTROLPOST");
        postMethod.addRequestHeader("Command", "osquery");
        postMethod.addRequestHeader("policeno", policeno);
        postMethod.addRequestHeader("deviceip", new EquipmentDao().getTSRSDeviceIp());
        postMethod.addRequestHeader("ip", ip);
        postMethod.setHttp11(true);
        int code = client.executeMethod(postMethod);
        if (code == 200 && postMethod.getResponseContentLength() > 0) {
            logger.info("vpnOnline request command:osquery,policeno:"
                    + policeno + ",ip:" + ip + "response body:" + result);
        } else {
            result = postMethod.getResponseBody();
            logger.info("vpnOnline request command:osquery,policeno:"
                    + policeno + ",ip:" + ip + "response body:" + result);
        }
        return result;
    }

    public byte[] vpnAll(String beginno, String endno, int pagesize) throws Exception {
        byte[] result = null;
        postMethod.setURI(new URI(urlbegin + host + urlend));
        postMethod.addRequestHeader("SERVICEREQUESTTYPE", "SERVICECONTROLPOST");
        postMethod.addRequestHeader("Command", "allvpn");
        postMethod.addRequestHeader("beginno", beginno);
        postMethod.addRequestHeader("endno", endno);
        postMethod.addRequestHeader("deviceip", new EquipmentDao().getJBPGDeviceIp());
        postMethod.addRequestHeader("pagesize", String.valueOf(pagesize));
        postMethod.setHttp11(true);
        int code = client.executeMethod(postMethod);
        if (code == 200 && postMethod.getResponseContentLength() > 0) {

            result = postMethod.getResponseBody();
            logger.info("vpnall request command:onlinevpn,beginno:" + beginno + ",endno:" + endno + ",pagesize:" + pagesize
                    + "response body:" + new String(result,"gbk"));
        } else {
            String temp = new String(postMethod.getResponseBody(), "gbk");
            logger.warn("mc httpClient code:" + code + "vpnall request command:onlinevpn,beginno:" + beginno + ",endno:" + endno + ",pagesize:" + pagesize
                    + "response body:" + temp);
        }
        return result;

    }


    public void init(String host) throws Ex {
        HttpClientParams clientparams = new HttpClientParams();
        clientparams.setConnectionManagerTimeout(5 * 60 * 1000);
        client = new HttpClient(clientparams);
        postMethod = new PostMethod();
        this.urlbegin = ServiceMonitorFactory.getValueByKey("mcurlbegin");
        this.urlend = ServiceMonitorFactory.getValueByKey("mcurlend");
        this.host = host;
        logger.info("mc client host is:" + host);
        logger.info("this.uribegin" + urlbegin);
        logger.info("this.uriend" + urlend);


    }

    public void close() {
        postMethod.releaseConnection();
    }

    public static void main(String arg[]) throws Exception {
        McHttpClient vpn = new McHttpClient();
        /*  vpn.init("192.168.1.66");
    //vpn.vpnOnline("1", "40", 40);
    byte[] temp2 = vpn.vpnAll("0", "0", 500);
    if (temp2 != null && temp2.length > 0)
        System.out.println("vpn all data:" + new String(temp2));
    vpn.close();
     vpn = new McHttpClient();*/
        vpn.init("192.168.20.100");
        byte[] temp = vpn.viewvpn("11", "9.9.0.15");
        if (temp != null && temp.length > 0) {
            File jpeg = new File("C:\\\\tsrsviewvpn\\tsrscms" + System.currentTimeMillis() + ".jpg");
            jpeg.createNewFile();
            FileUtils.writeByteArrayToFile(jpeg, temp);
        }
        vpn.close();
        /* vpn = new McHttpClient();
      vpn.init("192.168.30.71");
      vpn.vpnblock("sxl 123456789123456789", "171.168.1.1");*/
        /* vpn = new McHttpClient();
      vpn.init("192.168.30.71");
        vpn.vpnnoblock("sxl 123456789123456789", "171.168.1.1");
        vpn.close();*/
    }
}
