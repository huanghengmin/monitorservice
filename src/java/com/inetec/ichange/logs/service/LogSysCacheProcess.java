package com.inetec.ichange.logs.service;

import com.inetec.common.exception.E;
import com.inetec.common.exception.Ex;
import com.inetec.common.i18n.Message;
import com.inetec.common.security.DesEncrypterAsPassword;
import org.apache.commons.codec.binary.Base64;
import org.apache.log4j.Logger;

import java.io.*;
import java.util.Calendar;
import java.util.Date;
import java.util.Properties;

/**
 * Created by IntelliJ IDEA.
 * User: bluesky
 * Date: 2007-12-18
 * Time: 16:10:31
 * To change this template use File | Settings | File Templates.
 */
public class LogSysCacheProcess {
    public static final Logger m_logger = Logger.getLogger(LogSysCacheProcess.class);
    public static final String Str_CharsetName = "UTF-8";
    public static final int I_Buffsize = 100 * 1024;
    public static final int I_ReadLine = 100;
    public static final String Str_FilePrix = "auditlog";
    public static final boolean B_FileOperator_Append = true;
    public static final boolean B_FileOperator_REWriter = false;
    private String logsServerUser = "";
    private String logsServerPassword = "";
    private int logsMaxSize = 100 * 1024 * 1024;
    private int logsMaxDay = 7;
    private long currentSize = 0;
    private int currentDay = 0;
    private RandomAccessFile currentOut;
    private static final String Str_password = "audit~!@#$%^&*()_+data";
    private File incurrentFile;
    private File outcurrentFile;
    private RandomAccessFile currentIn;
    private String auditDir = "";
    private File auditfile;
    private Properties audit_config;
    private static final String Str_incurrentFile = "incurrentFile";
    private static final String Str_inlocation = "inlocation";
    private static final String Str_outcurrentFile = "outcurrentFile";
    private static final String Str_outlocation = "outlocation";

    /**
     * init currentSize and currentDay value.
     */
    private void init() throws Ex {
        String ichangeHomePath = System.getProperty("ichange.home");
        if (ichangeHomePath == null || ichangeHomePath == "") {
            throw new Ex().set(E.E_InvalidArgument, new Message("ϵͳ����������."));
        }
        auditDir = ichangeHomePath + File.separator + "logs";
        File file = new File(auditDir);
        File[] files = file.listFiles(new AuditFileNameFiler());
        if (files == null) {
            files = new File[0];
        }
        currentDay = files.length;
        for (int i = 0; i < files.length; i++) {
            currentSize = currentSize + files[i].length();
        }
        if (files.length == 0) {
            currentSize = 0;
            currentDay = 1;
        }
        incurrentFile = new File(auditDir + File.separator + Str_FilePrix + "_" + 1);
        if (currentDay <= logsMaxDay && currentSize <= logsMaxSize) {
            outcurrentFile = new File(auditDir + File.separator + Str_FilePrix + "_" + currentDay);
        } else {
            outcurrentFile = new File(auditDir + File.separator + Str_FilePrix + "_" + 1);
        }
        if (outcurrentFile != null && !outcurrentFile.exists()) {
            try {
                outcurrentFile.createNewFile();
            } catch (IOException e) {
                throw new Ex().set(E.E_IOException, e, new Message("Create Audit Data File faild.(FileName:{0})", outcurrentFile.getAbsolutePath()));
            }
        }
        audit_config = new Properties();
        try {
            auditfile = new File(auditDir + File.separator + "audit_config.properties");
            if (!auditfile.exists()) {
                auditfile.createNewFile();
            }
            InputStream in = new FileInputStream(auditfile);
            audit_config.load(in);
            in.close();

        } catch (FileNotFoundException e) {
            //okay
            throw new Ex().set(E.E_IOException, e, new Message(" Audit Data File faild.(FileName:{0})", auditfile.getAbsolutePath()));
        } catch (IOException e) {
            throw new Ex().set(E.E_IOException, e, new Message(" Init Config .(FileName:{0})", auditfile.getAbsolutePath()));
        }
    }

    public boolean isUseCache() {
        if (currentSize <= 0) {
            return false;
        }
        return false;
        //return true;
    }

    /**
     * config logSysCacheProcess
     *
     * @param user     logsserveruser value.
     * @param password logsserverpassword value (value is md5).
     * @param maxsize  logsmaxsize value.
     * @param maxday   logsmaxday value.
     */
    public void config(String user, String password, int maxsize, int maxday) {
        logsServerUser = user;
        logsServerPassword = password;

        if (logsMaxSize > 0)
            logsMaxSize = maxsize * 1024 * 1024;
        if (logsMaxDay > 0)
            logsMaxDay = maxday;
        try {
            init();
        } catch (Ex ex) {
            m_logger.warn("Init Operator failed.", ex);
        }
    }

    /**
     * @param validation simple(user:2342342,password:1231231231231231;)
     * @return
     */
    public boolean isValidationUser(String validation) throws Ex {
        validation = new String(new DesEncrypterAsPassword(Str_password).decrypt(validation.getBytes()));
        boolean result = false;
        if (validation.endsWith(";")) {
            String[] temp = validation.split(",");
            if (temp.length != 2) {
                return false;
            }
            try {
                String userid = temp[0].split(":")[1];
                String password = temp[1].split(":")[1];
                if (Base64.isArrayByteBase64(userid.getBytes())) {
                    userid = new String(Base64.decodeBase64(userid.getBytes()));
                    if (userid.equalsIgnoreCase(logsServerUser)) {
                        result = true;
                    }
                } else {
                    return result;
                }
                if (result) {
                    result = false;
                    password = password.substring(0, password.length() - 1);
                    if (Base64.isArrayByteBase64(password.getBytes())) {
                        password = new String(Base64.decodeBase64(password.getBytes()));
                        if (password.equalsIgnoreCase(logsServerPassword)) {
                            result = true;
                        } else {
                            result = false;
                        }
                    }
                }
            } catch (IndexOutOfBoundsException e) {
                return false;
            }
        }
        return result;
    }

    public void cache(byte[] buff) throws Ex {
        currentSize = currentSize + buff.length;
        try {
            initCache();
            String us = new String(buff, "gb2312");
            //us.replaceAll(",\n",",#");
            String[] tempstring = us.split(",##");
            if (currentSize > logsMaxSize) {
                if (currentOut == null)
                    outcurrentFile.setLastModified(System.currentTimeMillis());
                currentOut = new RandomAccessFile(outcurrentFile, "rwd");
            } else {
                if (currentOut == null) {
                    currentOut = new RandomAccessFile(outcurrentFile, "rwd");
                }
            }
            if (getWriteLocation() > 0 && (getWriteLocation() > currentOut.getFilePointer())) {
                currentOut.seek(getWriteLocation());
            }
            for (int i = 0; i < tempstring.length; i++) {
                if (!currentFileProcess()) {
                    currentOut.close();
                    outcurrentFile = new File(getNextFileName(outcurrentFile.getAbsolutePath()));
                    if (!outcurrentFile.exists()) {
                        outcurrentFile.createNewFile();
                    }
                    if (currentSize > logsMaxSize) {
                        if (currentOut == null)
                            currentOut = new RandomAccessFile(outcurrentFile, "rwd");
                    } else {
                        if (currentOut == null) {
                            currentOut = new RandomAccessFile(outcurrentFile, "rwd");
                        }
                    }
                }
                String temp = tempstring[i] + "\r\n";
                currentOut.write(temp.getBytes());
            }
            submit(false);
        } catch (FileNotFoundException e) {
            m_logger.warn("FileNotFoundException File Name: " + outcurrentFile.getAbsolutePath());
        } catch (UnsupportedEncodingException e) {
            m_logger.warn("UnsupportedEncodingException charset : " + Str_CharsetName);
        } catch (IOException e) {
            m_logger.warn("Writer Cache File Data IOException :", e);
            try {
                if (currentOut != null)
                    currentOut.close();
            } catch (IOException e1) {
                //okay
            }
        }
    }

    public byte[] readCache() throws Ex {
        StringBuffer buff = new StringBuffer();
        try {
            if (currentIn == null)
                currentIn = new RandomAccessFile(incurrentFile, "rw");
            if (getReadLocation() > 0 && (getReadLocation() > currentIn.getFilePointer())) {
                currentIn.seek(getWriteLocation());
            }
            initRead();
            for (int i = 0; i < I_ReadLine; i++) {
                String line = currentIn.readLine();
                if (line == null) {
                    currentSize = currentSize - incurrentFile.length();
                    currentIn.close();
                    incurrentFile.delete();
                    currentIn = null;
                    File temp = new File(getNextFileName(incurrentFile.getAbsolutePath()));
                    if (temp.exists()) {
                        incurrentFile = temp;
                        currentIn = new RandomAccessFile(incurrentFile, "rw");
                    } else {
                        currentSize = 0;
                    }
                    break;
                } else {
                    buff.append(line);
                    buff.append("\n");
                }
            }
            submit(true);
        } catch (FileNotFoundException e) {
            m_logger.warn("FileNotFoundException File Name: " + incurrentFile.getAbsolutePath());
        } catch (IOException e) {
            m_logger.warn("Read Cache Data IOException: " + incurrentFile.getAbsolutePath());
        }
        return buff.toString().trim().getBytes();
    }

    public static boolean existSubString(String str, String separator) {
        int i = str.indexOf(separator);
        if (i != -1)
            return true;
        else
            return false;
    }

    public static String getSubString(String str, String separator) {
        int i = str.lastIndexOf(separator);
        if (i != -1)
            return str.substring(i + separator.length(), str.length());
        else
            return "";
    }

    public static String getFirstSubString(String str, String separator) {
        int i = str.lastIndexOf(separator);
        if (i != -1)
            return str.substring(0, i + 1);
        else
            return "";
    }

    public static String getEndSubString(String str, String separator) {
        int i = str.lastIndexOf(separator);
        if (i != -1) {
            return str.substring(i + 1);
        } else {
            return "";
        }

    }

    public String getNextFileName(String currentFileName) throws Ex {
        String result = auditDir + File.separator;
        if (existSubString(currentFileName, Str_FilePrix)) {
            String filename = getEndSubString(currentFileName, File.separator);
            String seqnumber = filename.split("_")[1];
            int seq = Integer.parseInt(seqnumber);
            seq++;
            result = result + Str_FilePrix + "_" + seq;
        } else {
            throw new Ex().set(E.E_FormatError, new Message(E.KEY_FORMATERROR, "Audit Log File Name Format Error(file:" +
                    "{0})", currentFileName));
        }
        return result;
    }

    private boolean currentFileProcess() {
        Date currentdate = new Date(System.currentTimeMillis());
        Date currFileDate = new Date(outcurrentFile.lastModified());
        Calendar currentday = Calendar.getInstance();
        currentday.setTime(currentdate);
        int currentDays = currentday.get(Calendar.DAY_OF_MONTH);
        currentday.setTime(currFileDate);
        int currentFileDays = currentday.get(Calendar.DAY_OF_MONTH);
        if (currentDays == currentFileDays) {
            return true;
        }
        return false;

    }

    /**
     *
     */
    private void initCache() throws Ex {
        if (currentIn != null && incurrentFile.getAbsolutePath().equalsIgnoreCase(outcurrentFile.getAbsolutePath())) {
            try {
                currentIn.close();
                currentIn = null;
                if (!outcurrentFile.exists()) {
                    outcurrentFile.createNewFile();
                }
            } catch (IOException e) {
                m_logger.warn("Write Cache Data IOException: " + outcurrentFile.getAbsolutePath());
            }
        }
    }

    /**
     *
     */
    private void initRead() throws Ex {
        if (currentOut != null && incurrentFile.getAbsolutePath().equalsIgnoreCase(outcurrentFile.getAbsolutePath())) {
            try {
                currentOut.close();
                currentOut = null;
            } catch (IOException e) {
                m_logger.warn("Read Cache Data IOException: " + incurrentFile.getAbsolutePath());
            }
        }
    }

    public void submit(boolean isRead) {
        try {
            if (isRead) {
                if (incurrentFile != null && currentIn != null) {
                    audit_config.setProperty(Str_incurrentFile, incurrentFile.getAbsolutePath());
                    audit_config.setProperty(Str_inlocation, String.valueOf(currentIn.getFilePointer()));
                }
            } else {
                if (outcurrentFile != null && currentOut != null) {
                    audit_config.setProperty(Str_outcurrentFile, outcurrentFile.getAbsolutePath());
                    audit_config.setProperty(Str_outlocation, String.valueOf(currentOut.getFilePointer()));
                }
            }
            audit_config.store(new FileOutputStream(auditfile, false), "");
        } catch (IOException e) {
            m_logger.warn("Save audit config  Data IOException: " + auditfile.getAbsolutePath());
        }
    }

    public long getReadLocation() {
        long location = 0;
        String incurrentfileName = audit_config.getProperty(Str_incurrentFile);
        if (incurrentfileName != null && incurrentfileName.equalsIgnoreCase(incurrentFile.getAbsolutePath())) {
            location = new Long(audit_config.getProperty(Str_inlocation)).longValue();
        }
        return location;
    }

    public long getWriteLocation() {
        long location = 0;
        String incurrentfileName = audit_config.getProperty(Str_outcurrentFile);
        if (incurrentfileName != null && incurrentfileName.equalsIgnoreCase(outcurrentFile.getAbsolutePath())) {
            location = new Long(audit_config.getProperty(Str_outlocation)).longValue();
        }
        return location;
    }

    public static void main(String args[]) throws Exception {
        String currentFileName = "c:\\logs\\audit_1";
        System.out.println(getEndSubString(currentFileName, File.separator));
    }
}
