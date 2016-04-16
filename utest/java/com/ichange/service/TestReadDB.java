package com.ichange.service;

import com.inetec.ichange.service.monitor.uplink.databean.*;
import junit.framework.TestCase;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Ç®ÏþÅÎ
 * Date: 12-3-19
 * Time: ÉÏÎç9:53
 * To change this template use File | Settings | File Templates.
 */
public class TestReadDB extends TestCase{
    public static void main(String[] args){
        System.out.println("equipment(sysdevice) test is okay,it's size = "+ getEquipment());
        System.out.println("LowerSysabnormal test is okay,it's size = " + getLowerSysabnormal());
        System.out.println("LowerSysbizstatus test is okay,it's size = " + getLowerSysbizstatus());
        System.out.println("LowerSruntime test is okay,it's size = " + getLowerSysruntime());
        System.out.println("LowerSysstatus test is okay,it's size = " + getLowerSysstatus());
        System.out.println("ParentExchangePlatform test is okay,it's size = " + getParentExchangePlatform());
        System.out.println("ProvinceExchangePlatform test is okay,it's size = " + getProvinceExchangePlatform());
        System.out.println("Sysabnormal test is okay,it's size = " + getSysabnormal());
        System.out.println("SysbizRegister test is okay,it's size = " + getSysbizRegister());
        System.out.println("Sysbizstatus test is okay,it's size = " + getSysbizstatus());
        System.out.println("Sysclientservice test is okay,it's size = " + getSysclientservice());
        System.out.println("Syscontrolrulesinf test is okay,it's size = " + getSyscontrolrulesinf());
        System.out.println("Sysdelservice test is okay,it's size = " + getSysdelservice());
        System.out.println("Sysqueryservice test is okay,it's size = " + getSysqueryservice());
        System.out.println("Sysreginf test is okay,it's size = " + getSysreginf());
        System.out.println("Sysruntime test is okay,it's size = " + getSysruntime());
        System.out.println("Sysstatus test is okay,it's size = " + getSysstatus());
        System.out.println("Sysstrategyinf test is okay,it's size = " + getSysstrategyinf());
        System.out.println("Systerminalinf test is okay,it's size = " + getSysterminalinf());
        System.out.println("Systerminalstatus test is okay,it's size = " + getSysterminalstatus());
        System.out.println("TenimalList test is okay,it's size = " + getTenimalList());
        System.out.println("TenimalOperationAudit test is okay,it's size = " + getTenimalOperationAudit());
        System.out.println("TerminalAuthCode test is okay,it's size = " + getTerminalAuthCode());
        System.out.println("TerminalAccessAudit test is okay,it's size = " + getTerminalAccessAudit());
        System.out.println("TInlinkinf test is okay,it's size = " + getTInlinkinf());
        System.out.println("TOutlinkinf test is okay,it's size = " + getTOutlinkinf());
        System.out.println("TPlatinf test is okay,it's size = " + getTPlatinf());
        System.out.println("TProtocal test is okay,it's size = " + getTProtocal());
        System.out.println("TSbpz test is okay,it's size = " + getTSbpz());

    }

    public static  int getEquipment(){
        EquipmentDao dao = new EquipmentDao();
        List<EquipmentDataBean> list =  dao.list();
        return list.size();
    }

    public static int getLowerSysabnormal(){
        LowerSysabnormalDao dao = new LowerSysabnormalDao();
        List<LowerSysabnormalDataBean> list = dao.list();
        return list.size();
    }

    public static int getLowerSysbizstatus(){
        LowerSysbizstatusDao dao = new LowerSysbizstatusDao();
        return dao.list().size();
    }

    public static int getLowerSysruntime(){
        LowerSysruntimeDao dao = new LowerSysruntimeDao();
        return dao.list().size();
    }

    public static int getLowerSysstatus(){
        LowerSysstatusDao dao = new LowerSysstatusDao();
        return dao.list().size();
    }

    public static int getParentExchangePlatform(){
        ParentExchangePlatformDao dao = new ParentExchangePlatformDao();
        List<ParentExchangePlatformDataBean> list = dao.list();
        return list.size();
    }

    public static int getProvinceExchangePlatform(){
        ProvinceExchangePlatformDao dao = new ProvinceExchangePlatformDao();
        return dao.list().size();
    }

    public static int getSysabnormal(){
        SysabnormalDao dao = new SysabnormalDao();
        return dao.list().size();
    }

    public static int getSysbizRegister(){
        SysbizRegisterDao dao = new SysbizRegisterDao();
        return dao.list().size();
    }

    public static int getSysbizstatus(){
        SysbizRegisterDao dao = new SysbizRegisterDao();
        return dao.list().size();
    }

    public static int getSysclientservice(){
        SysclientserviceDao dao = new SysclientserviceDao();
        return dao.list().size();
    }

    public static int getSyscontrolrulesinf(){
        SyscontrolrulesinfDao dao = new SyscontrolrulesinfDao();
        return dao.list().size();
    }

    public static int getSysdelservice(){
        SysdelserviceDao dao = new SysdelserviceDao();
        return dao.list().size();
    }

    public static int getSysqueryservice(){
        SysqueryserviceDao dao = new SysqueryserviceDao();
        return dao.list().size();
    }

    public static int getSysreginf(){
        SysreginfDao dao = new SysreginfDao();
        return dao.list().size();
    }

    public static int getSysruntime(){
        SysruntimeDao dao = new SysruntimeDao();
        return dao.list().size();
    }

    public static int getSysstatus(){
        SysstatusDao dao = new SysstatusDao();
        return dao.list().size();
    }

    public static int getSysstrategyinf(){
        SysstrategyinfDao dao = new SysstrategyinfDao();
        return dao.list().size();
    }

    public static int getSysterminalinf(){
        SysterminalinfDao dao = new SysterminalinfDao();
        return dao.list().size();
    }

    public static int getSysterminalstatus(){
        SysterminalstatusDao dao = new SysterminalstatusDao();
        return dao.list().size();
    }

    public static int getTenimalList(){
        TenimalListDao dao = new TenimalListDao();
        return dao.list().size();
    }

    public static int getTenimalOperationAudit(){
        TenimalOperationAuditDao dao = new TenimalOperationAuditDao();
        return dao.list().size();
    }

    public static int getTerminalAuthCode(){
        TerminalAuthCodeDao dao = new TerminalAuthCodeDao();
        return dao.list().size();
    }

    public static int getTerminalAccessAudit(){
        TernimalAccessAuditDao dao = new TernimalAccessAuditDao();
        return dao.list().size();
    }

    public static int getTInlinkinf(){
        TInlinkinfDao dao = new TInlinkinfDao();
        return dao.list().size();
    }

    public static int getTOutlinkinf(){
        TOutlinkinfDao dao = new TOutlinkinfDao();
        return dao.list().size();
    }

    public static int getTPlatinf(){
        TPlatinfDao dao = new TPlatinfDao();
        return dao.list().size();
    }

    public static int getTProtocal(){
        TProtocolDao dao = new TProtocolDao();
        return dao.list().size();
    }

    public static int getTSbpz(){
        TSbpzDao dao = new TSbpzDao();
        return dao.list().size();
    }
}
