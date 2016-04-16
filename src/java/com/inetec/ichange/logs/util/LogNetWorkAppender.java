package com.inetec.ichange.logs.util;

import com.inetec.common.exception.E;
import com.inetec.common.exception.Ex;

import com.inetec.common.i18n.Message;
import com.inetec.common.http.pooling.HttpClientImp;
import com.inetec.common.http.pooling.HttpcConfig;
import com.inetec.common.http.pooling.HttpPool;
import com.inetec.common.http.pooling.HttpFactory;
import com.inetec.ichange.main.api.DataAttributes;
import com.inetec.ichange.main.api.EChange;

import com.inetec.ichange.service.utils.ChannelInfo;
import com.inetec.ichange.service.utils.ServiceUtil;

import org.apache.log4j.Category;

import java.io.*;
import java.util.zip.GZIPOutputStream;


public class LogNetWorkAppender {
    private ChannelInfo m_channelInfo = null;
    public static final String STR_ServiceData_Log = "ServiceLog";
    public static final String STR_CommandBody_Public = "public";

    /**
     * ************************************************************************************
     * CommandDisposer
     * args:  none
     * <p/>
     * returns:  nothing
     * <p/>
     * This function constructs a new FileDisposer.
     * *************************************************************************************
     */
    public LogNetWorkAppender(ChannelInfo channleInfo, boolean isPlatform) {
        m_cat = Category.getInstance(LogNetWorkAppender.class);
        m_channelInfo = channleInfo;
        initHttpPool();
    }

    /**
     * ************************************************************************************
     * disposeFile
     * args:  istr - the input stream of the file to dispose
     * props - the properties of the file - nodepath, etc.
     * url - the destination url of the data
     * <p/>
     * returns:  nothing
     * <p/>
     * This function first determines if this machine is the final destination and
     * then writes the file if it is, or sends it if it isn't.
     * *************************************************************************************
     */
    public boolean disposeDataPost(String filename, DataAttributes props) throws Ex {
        boolean result = false;
        try {
            File file = new File(filename);
            FileInputStream is = new FileInputStream(filename);

            long filesize = file.length();
            props.putValue(DataAttributes.Str_FileSize, "" + filesize);
            result = disposeDataPost(is, props);
        } catch (IOException Ex) {
            m_cat.error("An IOException was caught while opening the file '" + filename + "'.", Ex);
            throw new Ex().set(EChange.E_CF_Faild, new Message("An IOException is caught while opening the file {0}.", filename));
        }
        return result;
    }

    public boolean disposeDataPost(InputStream is, DataAttributes props) throws IOException, Ex {
        return sendData(is, props);
    }

    /**
     * ************************************************************************************
     * sendData
     * args:  istr - the input stream of the file to dispose
     * props - the properties of the file - nodepath, etc.
     * url - the destination url of the data
     * isReturn -the boolean of return value
     * <p/>
     * returns:  nothing
     * <p/>
     * This function sends the data in istr and props to the destination url.
     * If USE_SSL was true, the connection is made securely.
     * *************************************************************************************
     */
    private boolean sendData(InputStream is, DataAttributes props) throws Ex {
        boolean bresult = false;
        File fileCompressed = null;
        DataAttributes stream = null;
        String dataSize = null;
        HttpClientImp http = null;
        try {
            http = getConnection();
        } catch (Ex ex) {
            throw new Ex().set(E.E_Unknown, ex, new Message("Create connection error!"));
        }
        try {
            http.addRequestHeader("Content-Type", "application/Ex-www-form-urlencoded");
            http.addRequestHeader("Connection", "Keep-Alive");

            http.addRequestHeader(ServiceUtil.HDR_ServiceRequestType, ServiceUtil.STR_REQTP_ServiceDataPost);

            http.addRequestHeader(ServiceUtil.HDR_ServiceDataType, STR_ServiceData_Log);

            http.addRequestHeader(ServiceUtil.STR_CommandBody, STR_CommandBody_Public);


            stream = createStream(is);

            if (stream != null) {
                try {
                    is = stream.getResultData();
                } catch (Ex Ex) {
                    m_cat.error(Ex);
                    throw new Ex().set(E.E_OperationFailed, new Message("Failed to read Data Stream:{0} ", Ex.getMessage()));
                }
                dataSize = stream.getValue(DataAttributes.Str_FileSize);
            }

            if (dataSize == null) {
                dataSize = Long.toString(fileCompressed.length());
                http.addRequestHeader("Content-Length", dataSize);
            } else {
                http.addRequestHeader("Content-Length", dataSize);
            }

            if (fileCompressed != null) {
                try {
                    is = new FileInputStream(fileCompressed);
                } catch (FileNotFoundException e) {
                    m_cat.error("Data File read faild for file :" + fileCompressed.getAbsolutePath());
                    throw new Ex().set(E.E_FileNotFound, new Message("Data File read faild for file:{0}", fileCompressed.getAbsolutePath()));
                }
            }

            // First, the data input stream
            BufferedInputStream in = new BufferedInputStream(is);
            // Then, the data output stream
            //OutputStream out= new BufferedOutputStream(httpOutput);

            m_cat.debug("Begin to Sending file data.");

            // Connection should happen implicitly, but we'll double check...

            // Determine the number of bytes available for read, without blocking.  In any event, don't
            // read more than than 10MB, because we don't want to waste memory.
            // If available is 0, set to 1 and make sure we're at the end of the stream.

            try {
                http.requestBody(in);
                http.sendRequst();
                in.close();
                m_cat.debug("All values have sent!");
            } catch (IOException Ex) {
                m_cat.error("Http IOException :" + Ex.getMessage());
            } finally {
                if (fileCompressed != null)
                    fileCompressed.delete();
            }

            int result = http.responseCode();

            // Check that the responseCode is ok, then add to the successful file list.
            if (result == 200) {
                m_cat.debug("Log sent successfully.");
                bresult = true;

            } else {
                bresult = false;
            }
        } finally {
            try {
                returnConnection(http);
                //configType.disconnect();
            } catch (Ex Ex) {
                m_cat.error(Ex);
            }
        }
        return bresult;
    }


    protected DataAttributes createCompressedStream(InputStream is) throws Ex {
        try {
            //File fileTemp= File.createTempFile("VtDC", ".gz");
            ByteArrayOutputStream os = new ByteArrayOutputStream();
            //FileOutputStream gzip= new FileOutputStream(fileTemp);
            GZIPOutputStream gzip = new GZIPOutputStream(os);

            final int TenMB = 10485760;
            int bytesAvailable = is.available();
            if (bytesAvailable > TenMB) {
                bytesAvailable = TenMB;
            }
            if (bytesAvailable == 0) {
                bytesAvailable = 1;
            }
            byte[] tempBuf = new byte[TenMB];
            int bytesRead = -1;

            // Now, just read a chunk at a time and send over the wire.
            bytesRead = is.read(tempBuf);
            while (bytesRead != -1) {           // not end of file
                gzip.write(tempBuf, 0, bytesRead);
                bytesAvailable = is.available();
                if (bytesAvailable > TenMB) {
                    bytesAvailable = TenMB;
                }
                if (bytesAvailable == 0) {
                    bytesAvailable = 1;
                }
                bytesRead = is.read(tempBuf);
            }

            gzip.finish();
            tempBuf = null;
            DataAttributes result = new DataAttributes();
            result.setResultData(os.toByteArray());
            result.putValue(DataAttributes.Str_FileSize, String.valueOf(os.toByteArray().length));
            gzip.close();
            os.close();
            return result;
        } catch (IOException ex) {
            m_cat.error("IOException caught while creating compressed stream.", ex);
            throw new Ex().set(E.E_OperationFailed, ex, new Message("IOException caught while creating compressed stream."));
        }
    }

    protected DataAttributes createStream(InputStream is) throws Ex {
        try {
            ByteArrayOutputStream gzip = new ByteArrayOutputStream();
            final int TenMB = 10485760;
            int bytesAvailable = is.available();
            if (bytesAvailable > TenMB) {
                bytesAvailable = TenMB;
            }
            if (bytesAvailable == 0) {
                bytesAvailable = 1;
            }
            byte[] tempBuf = new byte[TenMB];
            int bytesRead = -1;

            // Now, just read a chunk at a time and send over the wire.
            bytesRead = is.read(tempBuf);
            while (bytesRead != -1) {           // not end of file
                gzip.write(tempBuf, 0, bytesRead);
                bytesAvailable = is.available();
                if (bytesAvailable > TenMB) {
                    bytesAvailable = TenMB;
                }
                if (bytesAvailable == 0) {
                    bytesAvailable = 1;
                }
                bytesRead = is.read(tempBuf);
            }
            gzip.flush();
            DataAttributes result = new DataAttributes();
            result.setResultData(gzip.toByteArray());
            result.putValue(DataAttributes.Str_FileSize, String.valueOf(gzip.toByteArray().length));
            gzip.close();
            return result;
        } catch (IOException Ex) {
            m_cat.error("IOException caught while creating  stream.", Ex);
            throw new Ex().set(E.E_OperationFailed, Ex, new Message("IOException caught while creating  stream."));
        }
    }

    private HttpClientImp getConnection() throws Ex {
        try {
            // return m_controlConnectionPool.getConnection();
            HttpClientImp client = (HttpClientImp) m_httpPool.borrowObject();
            if (client == null)
                throw new Ex().set(E.E_NullPointer);
            return client;
        } catch (Exception e) {
            throw new Ex().set(E.E_IOException, e);
        }

    }


    private void initHttpPool() {
        HttpcConfig m_httpConfig = new HttpcConfig();
        m_httpConfig.setPort(m_channelInfo.getPort());
        m_httpConfig.setUrl(m_channelInfo.getUrl());
        m_httpConfig.setPrivateKeyPassword(m_channelInfo.getPassword());
        m_httpConfig.setPrivateKey(m_channelInfo.getPrivateKeyPath());
        m_httpConfig.setEnableSSl(m_channelInfo.isHttps());
        HttpFactory factroy = new HttpFactory();
        factroy.setHttpConfig(m_httpConfig);
        m_httpPool = new HttpPool(factroy);
        //m_httpPool.setFactory(factroy);
    }


    public void returnConnection(HttpClientImp conn) throws Ex {
        try {
            if (conn != null)
                m_httpPool.returnObject(conn);
        } catch (Exception e) {
            throw new Ex().set(E.E_IOException, e);
        }
    }

    /**
     * ************************************************************************************
     * Member variables.
     * *************************************************************************************
     */
    private HttpPool m_httpPool = null;
    protected boolean m_bConfigured = false;
    private Category m_cat = null;  // for logging
}
