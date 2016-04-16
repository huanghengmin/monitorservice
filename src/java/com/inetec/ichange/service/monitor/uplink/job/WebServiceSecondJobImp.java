package com.inetec.ichange.service.monitor.uplink.job;

import com.crgs.client.WebServiceClient;
import com.crgs.webservices.IReceiveData;
import com.inetec.ichange.service.monitor.uplink.webservice.SysbizstatusProcess;
import com.inetec.ichange.service.monitor.uplink.webservice.SysruntimeProcess;
import com.inetec.ichange.service.monitor.uplink.webservice.SysstatusProcess;
import com.inetec.ichange.service.monitor.uplink.webservice.SysterminalinfoSecondNewProcess;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

/**
 * Created by IntelliJ IDEA.
 * User: bluesky
 * Date: 11-4-29
 * Time: ÉÏÎç9:14
 * To change this template use File | Settings | File Templates.
 */
public class WebServiceSecondJobImp implements Job {


    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        WebServiceClient client = new WebServiceClient();

        IReceiveData service = client.getServiceClient("http://10.2.164.55:8080/Webservice/services/inceptData",
                "/usr/app/cms/security/clientStore.jks", "1qaz@wsxstorepass");
        new SysterminalinfoSecondNewProcess();



        //Sysbizstatus

        /* //TPlatreginfProcess
   new TPlatreginfProcess().process();   //okay ´ý²âÊÔ

   // TPlatbizinfProcess
   new TPlatbizinfProcess().process();   //okay ´ý²âÊÔ

   //TPlatstatinfProcess
   new TPlatstatinfProcess().process();    //okay ´ý²âÊÔ

   //TinlinkstatinfProcess
   new TinlinkstatinfProcess().process(); //okay ´ý²âÊÔ
   //TPlatrunstateProcess
   new TPlatrunstateProcess().process(); //okay ´ý²âÊÔ

   //TinlinkrunstateProcess
   new TinlinkrunstateProcess().process();  //okay ´ý²âÊÔ

   //TbizrunstateProcess
   new TbizrunstateProcess().process();  //okay ´ý²âÊÔ

   //TdevicerunstateProcess
   new TdevicerunstateProcess().process();   //okay ´ý²âÊÔ*/

        //TPlatalertinfProcess
        // new TPlatalertinfProcess().process();
        if (jobExecutionContext!=null){
            jobExecutionContext.setResult("¼¶ÁªÉÏ±¨-ÈÕ±¨²Ù×÷-ÉÏ±¨³É¹¦£¡");
        }


    }

    public static void main(String arg[]) throws Exception {
        WebSeviceMonthJobImp job = new WebSeviceMonthJobImp();
        job.execute(null);
    }

}
