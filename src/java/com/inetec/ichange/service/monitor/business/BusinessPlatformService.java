package com.inetec.ichange.service.monitor.business;

import com.inetec.ichange.service.monitor.databean.BusinessRegister;
import com.inetec.ichange.service.monitor.databean.BusinessRegisterDao;
import com.inetec.ichange.service.monitor.databean.ExchangePlatform;
import com.inetec.ichange.service.monitor.databean.ExchangePlatformDao;
import com.inetec.ichange.service.monitor.utils.BusinessDataBeanSet;
import com.inetec.ichange.service.monitor.utils.Pagination;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

/**
 * Busness ?????
 * Created by IntelliJ IDEA.
 * User: bluesky
 * Date: 2010-9-7
 * Time: 14:43:58
 * To change this template use File | Settings | File Templates.
 */
public class BusinessPlatformService extends Thread {
    private final static Logger m_log = Logger.getLogger(BusinessPlatformService.class);
    private boolean isRun = true;
    public static BusinessDataBeanSet dataset = new BusinessDataBeanSet();
    public static BusinessDataBeanSet datavideoset = new BusinessDataBeanSet();
    private List<BusinessMonitorTypeService> mplatforms = new ArrayList<BusinessMonitorTypeService>();

    public BusinessPlatformService() {

    }

    public void init(List<ExchangePlatform> platforms) {
        BusinessRegisterDao equs = new BusinessRegisterDao();
        Pagination<BusinessRegister> equpage = equs.listBusinessRegister(500, 1);
        dataset.init(equpage.getItems());
        datavideoset.init(equpage.getItems());
//        for (int i = 0; i < platforms.size(); i++) {
//            Pagination<BusinessRegister> equpage11 = equs.listBusinessRegister(500, 1, platforms.get(i).getPlatform_name());
//            BusinessMonitorTypeService businessService = new BusinessMonitorTypeService();
//            businessService.init(equpage11.getItems(), platforms.get(i).getPlatform_name(), platforms.get(i).getPlatform_ip(), platforms.get(i).getPlatform_port(), "audit", "audit");
//            mplatforms.add(businessService);
//        }

    }

    public boolean isRun() {
        return isRun;
    }

    public void run() {
        isRun = true;

        while (isRun) {
            try {
                businessProcessRun();
                Thread.sleep(2 * 1000);
            } catch (InterruptedException e) {
                //okay
            }
        }
    }

    /**
     *
     */
    public void close() {
        isRun = false;
        busiessProcessClose();

    }

    private void businessProcessRun() throws InterruptedException {

        for (int i = 0; i < mplatforms.size(); i++)
            new Thread(mplatforms.get(i)).start();

    }


    private void busiessProcessClose() {
        for (int i = 0; i < mplatforms.size(); i++)
            mplatforms.get(i).close();
    }
   public static void main(String arg[])throws Exception{
       BusinessPlatformService businessService=new BusinessPlatformService();
       ExchangePlatformDao platform = new ExchangePlatformDao();
       Pagination<ExchangePlatform> platforms = platform.listExchangePlatform(100, 1);
       if (platforms != null && platforms.getItems() != null) {

           /* BusinessRegisterDao equs = new BusinessRegisterDao();
      Pagination<BusinessRegister> equpage = equs.listBusinessRegister(100, 1, configBean.getPlatform_name());*/
           // businessService.init(equpage.getItems(), configBean.getPlatform_name(), configBean.getPlatform_ip(), configBean.getPlatform_port(), "audit", "audit");
           businessService.init(platforms.getItems());
           new Thread(businessService).start();

       }
   }

}
