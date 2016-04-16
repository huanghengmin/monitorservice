package com.inetec.ichange.service.monitor.http;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.URI;
import org.apache.commons.httpclient.URIException;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.io.IOUtils;


import java.io.File;
import java.io.FileOutputStream;

/**
 * 终端加固调用
 * Created by IntelliJ IDEA.
 * User: bluesky
 * Date: 11-8-8
 * Time: 下午3:02
 * To change this template use File | Settings | File Templates.
 */
public class TSRSHttpclient {
    private HttpClient client;
    public static String url = "http://192.168.1.104:7500/";
    private GetMethod getMethod;

    public void viewvpn() throws Exception {
        getMethod.setRequestHeader("command", "viewvpn");
        getMethod.setRequestHeader("ip", "192.168.2.170");
        getMethod.setRequestHeader("policeno", "10001");
        getMethod.setRequestHeader("sizescale", "1");
        getMethod.setRequestHeader("compressscale", "80");

        getMethod.setHttp11(true);
        int code = client.executeMethod(getMethod);
        if (code == 200&&getMethod.getResponseContentLength()>0) {
            String imgname = getMethod.getResponseHeader("imgno").getValue();
            String imgtype = getMethod.getResponseHeader("imgtype").getValue();
            File out = new File("c:\\tsrsviewvpn\\" + imgname + "." + imgtype);
            if(!out.exists()){
                 out.createNewFile();
            }
            FileOutputStream outfile = new FileOutputStream("c:\\tsrsviewvpn\\" + imgname + "." + imgtype);
            IOUtils.copy(getMethod.getResponseBodyAsStream(), outfile);
            outfile.flush();
            outfile.close();
        } else {
            System.out.print(new String(getMethod.getResponseBody(), "gbk"));
        }
    }

    public void init(String url) throws URIException {
        client = new HttpClient();
        getMethod = new GetMethod();
        getMethod.setURI(new URI(url));

    }

    public static void main(String arg[]) throws Exception {
        TSRSHttpclient tsrs = new TSRSHttpclient();
        tsrs.init(url);
        tsrs.viewvpn();

    }
}
