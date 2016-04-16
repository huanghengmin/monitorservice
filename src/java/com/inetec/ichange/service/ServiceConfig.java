package com.inetec.ichange.service;

import com.inetec.ichange.main.api.DataAttributes;
import com.inetec.ichange.main.api.Status;
import com.inetec.ichange.service.utils.ServiceUtil;
import com.inetec.common.exception.Ex;
import com.inetec.common.exception.E;
import com.inetec.common.i18n.Message;

import java.io.*;

/**
 * Created by IntelliJ IDEA.
 * User: wxh
 * Date: 2005-8-16
 * Time: 21:47:37
 * To change this template use File | Settings | File Templates.
 */
public class ServiceConfig implements IServiceCommondProcess {
    private String m_configData = null;
    private static final String dataFormat = "yyyy-mm-dd";

    public DataAttributes process(InputStream input, DataAttributes dataAttributes) throws Ex {
        DataAttributes result = dataAttributes;
        String bprivate = result.getValue(ServiceUtil.STR_ChannelPrivate);
        /*String changeHome = System.getProperty("change.home");
        if (changeHome == null || changeHome == "") {
            throw new Ex().set(E.E_NullPointer, new Message("Channel home value is null."));
        }*/
        if (bprivate == null || bprivate == "") {
            throw new Ex().set(E.E_NullPointer, new Message("Channel Private value is null."));
        }
        //todo:????????????????????.
        if (bprivate.equalsIgnoreCase("true")) {
            throw new Ex().set(E.E_OperationError, new Message("????????????."));
        } else { //todo:????????????????????.
            try {
                if (!Service.m_platformConfigred) {
                    dataAttributes.putValue(ServiceUtil.STR_CommandBody, ServiceUtil.STR_CommandBody_Public);
                    dataAttributes.putValue(ServiceUtil.HDR_ServiceCommand, ServiceUtil.STR_ServiceData_Config);
                    dataAttributes = Service.m_dispPlatform.disposeDataPost(input, dataAttributes);
                    if (dataAttributes.getStatus().isSuccess()) {
                        Service.m_platformConfigred = true;
                        result.setStatus(Status.S_Success);

                    } else
                        result.setStatus(Status.S_Faild);

                } else {
                    result.setStatus(Status.S_Success);
                }

                result.putValue("Command", ServiceUtil.STR_ServiceData_Config);
            } catch (IOException e) {
                throw new Ex().set(E.E_IOException, new Message("???????????????."));
            }
        }
        return result;
    }

    public DataAttributes process(InputStream input) throws Ex {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public DataAttributes process(String fileName, DataAttributes dataAttributes) throws Ex {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public DataAttributes process(String fileName) throws Ex {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public int getProcessgetCapabilitie() {
        return I_StreamProcess;  //To change body of implemented methods use File | Settings | File Templates.
    }
}
