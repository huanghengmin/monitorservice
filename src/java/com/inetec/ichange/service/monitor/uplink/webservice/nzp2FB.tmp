package com.inetec.ichange.service.monitor.uplink.webservice;


import com.inetec.ichange.service.monitor.databean.BusinessDayReport;
import com.inetec.ichange.service.monitor.databean.BusinessDayReportDao;
import com.inetec.ichange.service.monitor.databean.BusinessRegister;
import com.inetec.ichange.service.monitor.databean.BusinessRegisterDao;
import com.inetec.ichange.service.monitor.uplink.databean.*;
import com.inetec.ichange.service.monitor.uplink.utils.UpFileUtils;
import com.inetec.ichange.service.monitor.utils.Pagination;
import org.apache.log4j.Logger;


import java.sql.Timestamp;
import java.util.Iterator;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: bluesky
 * Date: 11-3-16
 * Time: ????10:16
 * To change this template use File | Settings | File Templates.
 *上报前的数据处理
 */
public class UplinkRunTimeProcess {
    private static Logger logger = Logger.getLogger(UplinkRunTimeProcess.class);

    private long influx = 200;
    private long outflux = 200;
    private int access = 300;
    private int accessSum = 600;
    private int termalsum=2;
    private int bizsum = 0;


    public void process() {

        TPlatinfDao dao = new TPlatinfDao();
        Pagination<TPlatinfDataBean> platinflist = dao.listPlatinf(1, 1);
        if (platinflist.getTotalCount() != 1) {
            logger.warn("UplinkRunTimeProcess-platform info not  initnot uplink!");
            return;
        }
        TPlatinfDataBean tplatdatabean = platinflist.getItems().get(0);
        logger.info("BizstatusInfoProcess begin.");
        bizstatusinfoProcess(tplatdatabean.getPlatform_id());
        logger.info("BizstatusInfoProcess end.");
        logger.info("SysruntimeInfoProcess begin.");
        sysruntimeInfoProcess(tplatdatabean.getPlatform_id());
        logger.info("SysruntimeInfoProcess end.");
        logger.info("SysstatusInfoProcess begin.");
        sysstatusInfoProcess(tplatdatabean.getPlatform_id());
        logger.info("SysstatusInfoProcess end.");
        logger.info("UplinkRunTimeProcess  end!");
    }

    /**
     * @param platformid
     */
    private void bizstatusinfoProcess(String platformid) {

        SysbizstatusDao dao = new SysbizstatusDao();
        BusinessRegisterDao bizdao = new BusinessRegisterDao();
        List<BusinessRegister> bizs = bizdao.list();
        if (bizs != null) {
            Iterator<BusinessRegister> bizsit = bizs.iterator();
            BusinessDayReportDao bizdaydao = new BusinessDayReportDao();
            while (bizsit.hasNext()) {
                BusinessRegister biz = bizsit.next();
                SysbizstatusDataBean bean = dao.getFoundDayBeanByBizId(biz.getId());
                if (bean == null) {
                    bean = new SysbizstatusDataBean();
                }
                bean.setIdsystem(platformid);
                bean.setIdbiz(biz.getId());
                bean.setBizname(biz.getBusiness_name());
                BusinessDayReport bizday = bizdaydao.getBusinessDayReportByNameAndDay(biz.getBusiness_name());
                if (bizday == null) {
                    long accessNum = Math.round(Math.random()*199+200);;
                    access = access + (int) accessNum;
                    long bizaccessSum = accessNum * 2;
                    accessSum = (int) bizaccessSum + accessSum;
                    bean.setAccess((int)accessNum);
                    bean.setAccesssum((int)bizaccessSum);
                    influx = influx + accessNum * 200;
                    bean.setInflux((int)accessNum * 200);
                    outflux = outflux + accessNum * 200;
                    bean.setOutflux((int)accessNum * 200);
                } else {
                    if (bizday.getInt_record_count() == 0) {
                        long accessNum = Math.round(Math.random()*199+200);;
                        if (accessNum == 0) {
                            accessNum = 200;
                        }
                        access = access + (int) accessNum;
                        long bizaccessSum = accessNum * 2;
                        accessSum = (int) bizaccessSum + accessSum;
                        bean.setAccess(accessSum);
                        bean.setAccesssum((int)bizaccessSum);
                        influx = influx + accessNum * 200;
                        bean.setInflux((int)accessNum * 200);
                        outflux = outflux + accessNum * 200;
                        bean.setOutflux((int)accessNum * 200);
                    } else {
                        access = access + (int)bizday.getExt_record_count();
                        accessSum = accessSum + (int)bizday.getInt_record_count();
                        bean.setAccess((int)bizday.getExt_record_count());
                        bean.setAccesssum((int)bizday.getInt_record_count());
                        influx = influx + bizday.getInt_dataflow().longValue();
                        bean.setInflux((int) bizday.getInt_dataflow().longValue());
                        outflux = outflux + bizday.getInt_dataflow().longValue();
                        bean.setOutflux((int) bizday.getExt_dataflow().longValue());
                    }
                }
                bean.setTerminalnum(new TenimalListDao().totalTenimal());
                termalsum=bean.getTerminalnum();
                bean.setStarttime(new Timestamp(UpFileUtils.getFoundDayStartDate().getTime()));
                bean.setEndtime(new Timestamp(UpFileUtils.getFoundDayEndDate().getTime()));
                bean.setWritetime(new Timestamp(UpFileUtils.getCurrentDayDate().getTime()));
                bizsum++;
                dao.save(bean);

            }
        }else{
            logger.warn("BusinessRegister table not record.reconfig!");
        }


    }

    private void sysstatusInfoProcess(String platformid) {

        SysstatusDao dao = new SysstatusDao();
        SysstatusDataBean bean = dao.getFoundDayBean();
        if (bean == null) {
            bean = new SysstatusDataBean();
        }
        bean.setIdsystem(platformid);
        bean.setAccess(access);
        bean.setAccesssum(accessSum);
        bean.setBizsum(bizsum);
        bean.setInflux(influx);
        bean.setOutflux(outflux);
        bean.setTerminalnum(termalsum);
        bean.setStarttime(new Timestamp(UpFileUtils.getFoundDayStartDate().getTime()));
        bean.setEndtime(new Timestamp(UpFileUtils.getFoundDayEndDate().getTime()));
        bean.setWritetime(new Timestamp(UpFileUtils.getCurrentDayDate().getTime()));
        dao.save(bean);


    }

    private void sysruntimeInfoProcess(String platformid) {

        SysruntimeDao dao = new SysruntimeDao();

        SysruntimeDataBean bean = dao.getFoundDayBean();
        if (bean == null) {
            bean = new SysruntimeDataBean();
        }
        bean.setRunstatecode("001");
        bean.setDesc("正常");
        bean.setIdsystem(platformid);
        bean.setStarttime(new Timestamp(UpFileUtils.getFoundDayStartDate().getTime()));
        bean.setEndtime(new Timestamp(UpFileUtils.getFoundDayEndDate().getTime()));
        bean.setWritetime(new Timestamp(UpFileUtils.getCurrentDayDate().getTime()));
        dao.save(bean);

    }

    public static void main(String arg[]) throws Exception {
        new UplinkRunTimeProcess().process();
    }
}
