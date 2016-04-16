package com.inetec.ichange.service;

import com.inetec.ichange.main.api.DataAttributes;
import com.inetec.ichange.main.api.Status;
import com.inetec.ichange.service.utils.ServiceUtil;
import com.inetec.ichange.service.utils.SipTypeStatusSet;
import com.inetec.ichange.service.utils.OSSystemInfo;
import com.inetec.common.exception.Ex;
import com.inetec.common.exception.E;
import com.inetec.common.i18n.Message;
import com.inetec.common.io.IOUtils;

import java.io.InputStream;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * Created by IntelliJ IDEA.
 * User: wxh
 * Date: 2005-8-16
 * Time: 22:57:33
 * To change this template use File | Settings | File Templates.
 */
public class ServiceAppMonitor implements IServiceCommondProcess {
    public DataAttributes process(InputStream input, DataAttributes dataAttributes) throws Ex {
        DataAttributes result = dataAttributes;
        String bprivate = result.getValue(ServiceUtil.STR_CommandBody);
        if (bprivate == null || bprivate == "") {
            throw new Ex().set(E.E_NullPointer, new Message("Channel Private value is null."));
        }
        if (bprivate.equalsIgnoreCase(ServiceUtil.STR_CommandBoday_Private)) {
            result.clear();
            /*if (logger.isDebugEnabled()) {
                logger.debug("Type Status:" + new String(Service.m_typeStatus));
            }*/

            try {
                OSSystemInfo out = new OSSystemInfo();
                OSSystemInfo in = new OSSystemInfo();
                in.init();
                dataAttributes.putValue(ServiceUtil.STR_CommandBody, ServiceUtil.STR_CommandBoday_Private);
                dataAttributes = Service.m_dispService.disposeControl(ServiceUtil.STR_ServiceData_OSMonitor, dataAttributes);
                if (dataAttributes.getStatus().isSuccess()) {
                    String temp = null;
                    try {
                        temp = new String(DataAttributes.readInputStream(dataAttributes.getResultData()));
                        if (temp != null) {
                            String[] ts = temp.split(";");
                            out.setCpu(Double.parseDouble(ts[0].split(":")[1]));
                            out.setTotalmem(Long.parseLong(ts[1].split(":")[1]));
                            out.setUsemem(Long.parseLong(ts[2].split(":")[1]));
                        }
                    } catch (IOException e) {
                        //okay
                    }
                }
                /*Service.sipchange.setAppstatus(1);
                Service.sipchange.setCurrent_connect(Service.sipchange.getCurrent_connect() + 2);
                Service.sipchange.setMax_connect(100);
                Service.sipchange.setTotal_flux(Service.sipchange.getTotal_flux() + 2);
                Service.sipchange.setTotal_requests(Service.sipchange.getCurrent_connect() + Service.sipchange.getTotal_requests());*/

                Service.siptypeSet.AddResource(true, in);
                Service.siptypeSet.AddResource(false, out);
                /*if (Service.siptypeSet.size() == 0) {
                    Service.siptypeSet.setTypeStatus(Service.sipchange);
                } else {
                    Service.siptypeSet.clear();
                    Service.siptypeSet.setTypeStatus(Service.sipchange);
                }*/
                result.putValue("Command", ServiceUtil.STR_ServiceApp_Monitor);
                //Service.m_typeStatus = Service.siptypeSet.getByte();
                result.setResultData(Service.siptypeSet.getByte());
            } catch (IOException e) {
                throw new Ex().set(E.E_IOException, new Message("×Ö·ûÁ÷´íÎó"));
            }

            result.setStatus(Status.S_Success);

        } else {
            throw new Ex().set(E.E_Unknown, new Message("????????????."));
        }


        return result;  //To change body of implemented methods use File | Settings | File Templates.  //To change body of implemented methods use File | Set
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