package com.inetec.ichange.service.monitor.uplink.utils;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by IntelliJ IDEA.
 * User: bluesky
 * Date: 11-4-29
 * Time: 下午12:07
 * To change this template use File | Settings | File Templates.
 */
public class UpFileUtils {
    private static Logger logger = Logger.getLogger(UpFileUtils.class);
    public static String charset = "UTF-8";
    public static String EndDataString = "@@@@";
    public static String dataSpilt = "    ";
    public static String Str_monitor_Home = "monitor.home";
    public static String Str_FtpDir = "/incoming";

    public static String getDataPath() {
        String path = System.getProperty(UpFileUtils.Str_monitor_Home);

        if (path == null) {
            logger.warn("集控系统-Monitor Home路径没有设置！无法级联上报");
            path = "";
        } else {
//            path = path + "/upload/";
            path = path + "/data/";
        }
        return path;
    }

    public static String getUpLoadDataPath() {
        String path = System.getProperty(UpFileUtils.Str_monitor_Home);

        if (path == null) {
            logger.warn("集控系统-Monitor Home路径没有设置！无法级联上报");
            path = "";
        } else {
            path = path + "/data/upload/";
        }
        return path;
    }

    public boolean process(String filename, StringBuffer buffer) {
        if (!getDataPath().equalsIgnoreCase("")) {
            filename = getDataPath() + filename;
        }
        File file = new File(filename);
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                logger.warn("Create File faild." + filename, e);
            }
        }
        try {
            final byte[] bom = new byte[]{(byte) 0xEF, (byte) 0xBB, (byte) 0xBF};
            ByteArrayOutputStream bytes = new ByteArrayOutputStream();
            bytes.write(bom);
            bytes.flush();
            bytes.write(buffer.toString().getBytes(charset));
            bytes.flush();
            FileUtils.writeByteArrayToFile(file, bytes.toByteArray());
        } catch (IOException e) {
            logger.warn("Write File faild." + filename, e);
        }
        logger.info("write File :" + filename + " sucessed.");
        return true;
    }

    public boolean processFinish(String filename) {
        if (!getDataPath().equalsIgnoreCase("")) {
            filename = getDataPath() + filename;
        }
        File file = new File(filename);
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                logger.warn("Create File faild." + filename, e);
            }
        }
        try {
            FileUtils.writeStringToFile(file, "finished", charset);
        } catch (IOException e) {
            logger.warn("Write File faild." + filename, e);
        }
        return true;
    }

    public void zipProcess(String filename, String infile) {
        if (!getDataPath().equalsIgnoreCase("")) {
            filename = getDataPath() + filename;
        }
        try {
            new ZipFileUtils().Zip(filename, infile);
        } catch (Exception e) {
            logger.warn("Write File faild." + filename, e);
        }
    }

    public static String getDateFormatUp(Date date) {

        SimpleDateFormat format = new SimpleDateFormat("yyMMdd'T'HHmmss");

        return format.format(date);
    }

    public static String getDateFormatUp(long time) {

        return getDateFormatUp(new Date(time));
    }

    public static String getDateFormat(Date date) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        return format.format(date);
    }

    public static String getDateNoTimeFormat(Date date) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

        return format.format(date);
    }

    public static String getlength(int length) {
        String result = "00000000";
        String temp = Integer.toHexString(length);
        result = result.substring(0, 8 - temp.length());
        result = result + temp;
        return result;
    }
     public static String getlengthByFour(int length) {
        String result = "0000";
        String temp = Integer.toHexString(length);
        result = result.substring(0, 4 - temp.length());
        result = result + temp;
        return result;
    }

    public static String getFoundDay(){
        Calendar c = Calendar.getInstance();
        c.add(Calendar.DAY_OF_MONTH, -1);
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String mDateTime = formatter.format(c.getTime());
        String strStart = mDateTime.substring(0, 11);
        return strStart;
    }
    public static String getFoundDayStart() {
        Calendar c = Calendar.getInstance();
        c.add(Calendar.DAY_OF_MONTH, -1);
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String mDateTime = formatter.format(c.getTime());
        String strStart = mDateTime.substring(0, 11) + "00:01:03";
        return strStart;
    }
    public static String getCurrentDayStart() {
        Calendar c = Calendar.getInstance();
        c.add(Calendar.DAY_OF_MONTH, 0);
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String mDateTime = formatter.format(c.getTime());
        String strStart = mDateTime.substring(0, 11) + "00:01:03";
        return strStart;
    }
    public static Date getCurrentDayStartDate() {
        Calendar c = Calendar.getInstance();
        c.add(Calendar.DAY_OF_MONTH, 0);
        Calendar e = Calendar.getInstance();
        e.set(c.get(Calendar.YEAR),c.get(Calendar.MONTH),c.get(Calendar.DAY_OF_MONTH),00,00,00);
        return e.getTime();
    }
     public static Date getFoundDayStartDate() {
       Calendar c = Calendar.getInstance();
        c.add(Calendar.DAY_OF_MONTH, -1);
         Calendar e = Calendar.getInstance();
        e.set(c.get(Calendar.YEAR),c.get(Calendar.MONTH),c.get(Calendar.DAY_OF_MONTH),00,00,00);
        return e.getTime();
    }
    public static String getCurrentDay() {
        Calendar c = Calendar.getInstance();
        c.add(Calendar.DAY_OF_MONTH, 0);
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String mDateTime = formatter.format(c.getTime());
        String strStart = mDateTime.substring(0, 11);
        return strStart;
    }
     public static Date getCurrentDayDate() {
        Calendar c = Calendar.getInstance();
        c.add(Calendar.DAY_OF_MONTH, 0);
        return c.getTime();
    }

    public static int geCurrentHour() {
        Calendar c = Calendar.getInstance();
        return c.get(Calendar.HOUR_OF_DAY);//

    }

    public static Date getFoundDayEndDate() {
        Calendar c = Calendar.getInstance();
        c.add(Calendar.DAY_OF_MONTH, -1);
       Calendar e = Calendar.getInstance();
        e.set(c.get(Calendar.YEAR),c.get(Calendar.MONTH),c.get(Calendar.DAY_OF_MONTH),23,59,59);
        return e.getTime();
    }
      public static String getFoundDayEnd() {
        Calendar c = Calendar.getInstance();
        c.add(Calendar.DAY_OF_MONTH, -1);
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String mDateTime = formatter.format(c.getTime());
        String strStart = mDateTime.substring(0, 11) + "23:59:59";
        return strStart;
    }
    public static Date getCurrentDayEndDate() {
        Calendar c = Calendar.getInstance();
        c.add(Calendar.DAY_OF_MONTH, 0);
        Calendar e = Calendar.getInstance();
        e.set(c.get(Calendar.YEAR),c.get(Calendar.MONTH),c.get(Calendar.DAY_OF_MONTH),23,59,59);
        return e.getTime();
    }
    public static String getCurrentDayEnd() {
        Calendar c = Calendar.getInstance();
        c.add(Calendar.DAY_OF_MONTH, 0);
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String mDateTime = formatter.format(c.getTime());
        String strStart = mDateTime.substring(0, 11) + "23:59:59";
        return strStart;
    }
    public static void main(String arg[]) throws Exception {
        System.out.println(UpFileUtils.getDateFormatUp(System.currentTimeMillis()));
        System.out.println(UpFileUtils.getlength(400));
        System.out.println(Integer.parseInt("21d", 18));
        System.out.println(getFoundDayStart());
        System.out.println(getCurrentDayDate());
        System.out.println(getFoundDayEnd());
        System.out.println(geCurrentHour());
        System.out.println(getCurrentDay());
        System.out.print(getFoundDayEndDate());
        System.out.println(UpFileUtils.getlengthByFour(10));
    }
}
