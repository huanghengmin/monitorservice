package com.inetec.ichange.service.monitor.http;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.URI;
import org.apache.commons.httpclient.URIException;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.log4j.Logger;

/**
 * VPN璋冪敤
 * Created by IntelliJ IDEA.
 * User: bluesky
 * Date: 11-8-8
 * Time: 涓嬪崍3:02
 * To change this template use File | Settings | File Templates.
 */
public class VpnHttpclient {
    Logger logger = Logger.getLogger(VpnHttpclient.class);
    private HttpClient client;
//    public static String url = "http://168.192.2.1:3457";
    private GetMethod getMethod;

    private String host;
    public static String Str_urlHttp = "http://";
    public static String url = "/DoTermStatus";
	public static String allUrl = "/DoTermStatusAll";

/*
    public String vpnOnline(String beginno, String endno, int pagesize) throws Exception {
        String result = null;
        getMethod.setRequestHeader("command", "onlinevpn");
        getMethod.setRequestHeader("beginno", beginno);
        getMethod.setRequestHeader("endno", endno);
        getMethod.setRequestHeader("pagesize", String.valueOf(pagesize));
        getMethod.setHttp11(true);
        int code = client.executeMethod(getMethod);
        if (code == 200 && getMethod.getResponseContentLength() > 0) {

            result = new String(getMethod.getResponseBody(), "gbk");
            logger.info("vpnOnline request command:onlinevpn,beginno:" + beginno + ",endno:" + endno + ",pagesize:" + pagesize
                    + "response body:" + result);
        } else {
            result = new String(getMethod.getResponseBody(), "gbk");
            logger.warn("vpnOnline request command:onlinevpn,beginno:" + beginno + ",endno:" + endno + ",pagesize:" + pagesize
                    + "response body:" + result);
        }
        return result;
    }

    public String vpnNew(String beginno, String endno, int pagesize) throws Exception {
        String result = null;
        getMethod.setRequestHeader("command", "newvpn");
        getMethod.setRequestHeader("beginno", beginno);
        getMethod.setRequestHeader("endno", endno);
        getMethod.setRequestHeader("pagesize", String.valueOf(pagesize));
        getMethod.setHttp11(true);
        int code = client.executeMethod(getMethod);
        if (code == 200 && getMethod.getResponseContentLength() > 0) {

            result = new String(getMethod.getResponseBody(), "gbk");
            logger.info("vpnOnline request command:onlinevpn,beginno:" + beginno + ",endno:" + endno + ",pagesize:" + pagesize
                    + "response body:" + result);
        } else {
            result = new String(getMethod.getResponseBody(), "gbk");
            logger.warn("vpnOnline request command:onlinevpn,beginno:" + beginno + ",endno:" + endno + ",pagesize:" + pagesize
                    + "response body:" + result);
        }
        return result;
    }
*/

    public String vpnAll(String beginno, String endno, int pagesize) throws Exception {
        String result = null;
        getMethod.setRequestHeader("command", "allvpn");
        getMethod.setRequestHeader("beginno", beginno);
        getMethod.setRequestHeader("endno", endno);
        getMethod.setRequestHeader("pagesize", String.valueOf(pagesize));
        getMethod.setHttp11(true);
        int code = client.executeMethod(getMethod);
        if (code == 200 && getMethod.getResponseContentLength() > 0) {

            result = new String(getMethod.getResponseBody(), "gbk");
            logger.info("vpnOnline request command:onlinevpn,beginno:" + beginno + ",endno:" + endno + ",pagesize:" + pagesize
                    + "response body:" + result);
        } else {
            result = new String(getMethod.getResponseBody(), "gbk");
            logger.warn("vpnOnline request command:onlinevpn,beginno:" + beginno + ",endno:" + endno + ",pagesize:" + pagesize
                    + "response body:" + result);
        }
        return result;

    }

    /*
     * block 终端阻断
     */
	public String vpnBlock(String policeno, String ip,String hour) throws Exception {
		String result = null;
		getMethod.setURI(new URI(Str_urlHttp + host +url));
		getMethod.setRequestHeader("command", "block");
		getMethod.setRequestHeader("policeno", policeno);
		getMethod.setRequestHeader("ip", ip);
        getMethod.setRequestHeader("hour",hour);
		getMethod.setHttp11(true);
		int code = client.executeMethod(getMethod);
		if (code == 200 && getMethod.getResponseContentLength() > 0) {

			result = new String(getMethod.getResponseBody(), "gbk");
			logger.info("vpnBlock request command:block,policeno:" + policeno
					+ ",ip:" + ip + ",hour"+hour+",response body:" + result);
		} else {
			result = new String(getMethod.getResponseBody(), "gbk");
			logger.info("vpnBlock request command:block,policeno:" + policeno
					+ ",ip:" + ip +  ",hour"+hour+",response body:" + result);
		}
		return result;
	}
    /*
     * 终端阻断恢复
     */
	public String vpnReleaseBlock(String policeno, String ip) throws Exception {
		String result = null;
		getMethod.setURI(new URI(Str_urlHttp + host +url));
		getMethod.setRequestHeader("command", "releaseblock");
		getMethod.setRequestHeader("policeno", policeno);
		getMethod.setRequestHeader("ip", ip);
		getMethod.setHttp11(true);
		int code = client.executeMethod(getMethod);
		if (code == 200 && getMethod.getResponseContentLength() > 0) {

			result = new String(getMethod.getResponseBody(), "gbk");
			logger.info("vpnReleaseBlock request command:releaseblock,policeno:"
					+ policeno + ",ip:" + ip + "response body:" + result);
		} else {
			result = new String(getMethod.getResponseBody(), "gbk");
			logger.info("vpnReleaseBlock request command:releaseblock,policeno:"
					+ policeno + ",ip:" + ip + "response body:" + result);
		}
		return result;
	}

    /*
     * 吊销证书
     */
    public String vpnRevokeCert(String policeno, String ip) throws Exception {
		String result = null;
		getMethod.setURI(new URI(Str_urlHttp + host +url));
		getMethod.setRequestHeader("command", "revokecert");
		getMethod.setRequestHeader("policeno", policeno);
		getMethod.setRequestHeader("ip", ip);
		getMethod.setHttp11(true);
		int code = client.executeMethod(getMethod);
		if (code == 200 && getMethod.getResponseContentLength() > 0) {

			result = new String(getMethod.getResponseBody(), "gbk");
			logger.info("vpnRevokeCert request command:revokecert,policeno:"
					+ policeno + ",ip:" + ip + "response body:" + result);
		} else {
			result = new String(getMethod.getResponseBody(), "gbk");
			logger.info("vpnRevokeCert request command:revokecert,policeno:"
					+ policeno + ",ip:" + ip + "response body:" + result);
		}
		return result;
	}
    /*
     * 截屏
     */
    public String vpnPrintScreen(String policeno, String ip) throws Exception {
		String result = null;
		getMethod.setURI(new URI(Str_urlHttp + host +url));
		getMethod.setRequestHeader("command", "printscreen");
		getMethod.setRequestHeader("policeno", policeno);
		getMethod.setRequestHeader("ip", ip);
		getMethod.setHttp11(true);
		int code = client.executeMethod(getMethod);
		if (code == 200 && getMethod.getResponseContentLength() > 0) {

			result = new String(getMethod.getResponseBody(), "gbk");
			logger.info("vpnPrintScreen request command:printscreen,policeno:"
					+ policeno + ",ip:" + ip + "response body:" + result);
		} else {
			result = new String(getMethod.getResponseBody(), "gbk");
			logger.info("vpnPrintScreen request command:printscreen,policeno:"
					+ policeno + ",ip:" + ip + "response body:" + result);
		}
		return result;
	}

    /**
     *   手动上报
     * @param uplinkTypes     以冒号相连的上报内容
     * @return
     * @throws Exception
     */
    public String vpnUpLinkTypes(String uplinkTypes) throws Exception {
		String result = null;
		getMethod.setURI(new URI(Str_urlHttp + host +url));
		getMethod.setRequestHeader("command", "uplinkprocess");
		getMethod.setRequestHeader("uplinktypes", uplinkTypes);
		getMethod.setHttp11(true);
		int code = client.executeMethod(getMethod);
		if (code == 200 && getMethod.getResponseContentLength() > 0) {
			result = new String(getMethod.getResponseBody(), "gbk");
			logger.info("vpnUpLinkTypes request command:uplinkprocess,uplinktypes:"
					+ uplinkTypes + ",response body:" + result);
		} else {
			result = new String(getMethod.getResponseBody(), "gbk");
			logger.info("vpnUpLinkTypes request command:uplinkprocess,uplinktypes:"
					+ uplinkTypes + ",response body:" + result);
		}
		return result;
	}

    /**
     * 监管中心通过webservice 发送的命令
     * @param reportCommand   指令名称
     * @param report          指令内容  以冒号相连(系统标识:接入终端标识:请求动作)组成
     * @return
     * @throws Exception
     */
     public String vpnWebService(String reportCommand,String report ) throws Exception{
        String result = null;
		getMethod.setURI(new URI(Str_urlHttp + host +url));
		getMethod.setRequestHeader("command", "webservicecommand");
		getMethod.setRequestHeader("reportCommand", reportCommand);
		getMethod.setRequestHeader("report", report);
		getMethod.setHttp11(true);
		int code = client.executeMethod(getMethod);
		if (code == 200 && getMethod.getResponseContentLength() > 0) {
			result = new String(getMethod.getResponseBody(), "gbk");
			logger.info("vpnWebService request command:webservicecommand,reportCommand:"+reportCommand+",report:"
					+ report + ",response body:" + result);
		} else {
			result = new String(getMethod.getResponseBody(), "gbk");
			logger.info("vpnWebService request command:webservicecommand,reportCommand:"+reportCommand+",report:"
					+ report + ",response body:" + result);
		}
		return result;
    }


    public void init(String url) throws URIException {
        client = new HttpClient();
        getMethod = new GetMethod();
        getMethod.setURI(new URI(url));

    }

    public static void main(String arg[]) throws Exception {
        VpnHttpclient vpn = new VpnHttpclient();
        vpn.init(url);
//        vpn.vpnOnline("1", "40", 40);
        vpn.vpnAll("100", "00040", 40);
//        vpn.vpnNew("00001", "00040", 40);
    }
}
