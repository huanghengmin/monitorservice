package com.inetec.ichange.service.monitor.alarm;

import com.inetec.ichange.service.monitor.alarm.utils.ServiceResponse;
import com.inetec.ichange.service.monitor.alarm.utils.ServiceUtil;
import com.inetec.ichange.service.monitor.databean.AlertDataBean;
import org.apache.log4j.Logger;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ConcurrentHashMap;

/**
 *短信报警
 * Created by IntelliJ IDEA.
 * User: bluesky
 * Date: 2010-9-7
 * Time: 16:23:09
 * To change this template use File | Settings | File Templates.
 */
public class MessageAlarmProcess implements IAlarmProcess {
    private static final Logger logger = Logger.getLogger(MessageAlarmProcess.class);
    private static final String DEFAULT_FORMAT = "yyyy-MM-dd HH:mm:ss";
    private String serviceMobileUrl;
    private ConcurrentHashMap messageSet = new ConcurrentHashMap();
    private ConcurrentHashMap messageIndexSet = new ConcurrentHashMap();
    public void init(String type, String name) {
        serviceMobileUrl = AlarmService.serviceMobileURl;
    }

    private int index = 0;
    public void process(String type, AlertDataBean bean) {
        if(bean.getAlert_info()!=null
                &&bean.getAlert_info().length()>0
                        &&bean.getAlert_time()!=null){
            if(messageSet.get(bean.getAlert_info())==null){
                index = 1;
                messageSet.put(bean.getAlert_info(),bean);
                send(bean,index,"");
                messageIndexSet.put(bean.getAlert_info(),index);
            } else {
                AlertDataBean beanOld = (AlertDataBean) messageSet.get(bean.getAlert_info());
                index = (Integer)messageIndexSet.get(bean.getAlert_info());
                boolean isOneHour = isOneHourLater(bean,beanOld);
                boolean isOneDay = isOneDayLater(bean, beanOld);
                if (!isOneDay) {
                    if(isOneHour) {
                        Date date = new Date(beanOld.getAlert_time().getTime());
                        String time = date2Str(date, DEFAULT_FORMAT);
                        send(bean,index,time);
                        index = 0;
                        messageIndexSet.put(bean.getAlert_info(),index);
                    } else {
                        index ++;
                        messageIndexSet.put(bean.getAlert_info(),index);
                    }
                } else {
                    index = 1;
                    messageSet.put(bean.getAlert_info(),bean);
                    send(bean,index,"");
                    messageIndexSet.put(bean.getAlert_info(),index);
                }
            }
        } else {
            logger.warn("报警信息为null,未报警,请检查错误...");
        }

    }

    /**
     * 一天以上
     * @param bean
     * @param beanOld
     * @return
     */
    private boolean isOneDayLater(AlertDataBean bean, AlertDataBean beanOld) {
        Timestamp time = bean.getAlert_time();
        Timestamp timeOld = beanOld.getAlert_time();
        long del = time.getTime()-timeOld.getTime();
        if(del>1000*60*60*24){
            return true;
        }
        return false;
    }

    /**
     * 一个小时以上
     * @param bean
     * @param beanOld
     * @return
     */
    private boolean isOneHourLater(AlertDataBean bean, AlertDataBean beanOld) {
        Timestamp time = bean.getAlert_time();
        Timestamp timeOld = beanOld.getAlert_time();
        long del = time.getTime()-timeOld.getTime();
        if(del>1000*60*60){
            return true;
        }
        return false;
    }

    private void send(AlertDataBean bean,int index,String time) {
        String message = bean.getAlert_info();
        if(index>1){
            message += "(该信息第"+index+"次报警,上一次报警时间"+time+",请尽快排查错误!)";
        }
        String[][] params = new String[][] {
                { "SERVICEREQUESTTYPE", "SERVICECONTROLPOST" },
                { "Command", "start" },
                { "message",  message} };
        ServiceResponse response = ServiceUtil.callService(params, serviceMobileUrl);
        if(response!=null&&response.getCode()==200){
            logger.info("短信报警成功");
        } else {
            logger.error("短信报警失败,错误代码"+response.getCode());
        }

    }

    public static String date2Str(Date date, String format) {
        if (null == date) {
            return null;
        }
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.format(date);
    }

    public void close() {

    }
}
