package com.inetec.ichange.service.monitor.uplink.utils;

import org.apache.log4j.Logger;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * Created by IntelliJ IDEA.
 * User: bluesky
 * Date: 11-4-29
 * Time: 上午11:45
 * To change this template use File | Settings | File Templates.
 */
public class ZipFileUtils {
    private static Logger logger = Logger.getLogger(ZipFileUtils.class);

    public void Zip(String zipFileName, String inputFile) throws Exception {
//        logger.info("zipFileName : "+zipFileName);
//        logger.info("inputFile : "+inputFile);
        ZipOutputStream out = new ZipOutputStream(new FileOutputStream(zipFileName));
        Zip(out, new File(inputFile), "");
        logger.debug("zip : " + zipFileName + "okay.");
        out.close();
    }

    public void Zip(String zipFileName, File inputFile) throws Exception {
        ZipOutputStream out = new ZipOutputStream(new FileOutputStream(zipFileName));
        Zip(out, inputFile, "");
        logger.debug("zip : " + zipFileName + "okay.");
        out.close();
    }

    public void Zip(ZipOutputStream out, File f, String base) {
        try{
            if (f.isDirectory()) {
                File[] ary_f = f.listFiles();
                out.putNextEntry(new ZipEntry(base + "/"));
                base = base.length() == 0 ? "" : base + "/";
                for (int i = 0; i < ary_f.length; i++) {
                    Zip(out, ary_f[i], base + ary_f[i].getName());
                }
            } else {
                out.putNextEntry(new ZipEntry(f.getName()));
                FileInputStream in = new FileInputStream(f);
                int b;
                //System.out.println(base);
                while ((b = in.read()) != -1) {
                    out.write(b);
                }
                in.close();
            }
        } catch (IOException e) {
            logger.error("file is "+ f.getPath()+"/"+f.getName());
            logger.error("压缩包处理错误",e);
        }
    }

    public static void main(String[] args) {
        ZipFileUtils zipDemo = new ZipFileUtils();
        try {
            zipDemo.Zip("C:\\z.zip", "d:\\图标.jpg");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}



