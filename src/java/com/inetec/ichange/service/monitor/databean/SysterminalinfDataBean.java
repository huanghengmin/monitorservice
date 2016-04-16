package com.inetec.ichange.service.monitor.databean;

import java.sql.Time;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.inetec.ichange.service.monitor.uplink.databean.SysterminalinfDao;
import net.sf.json.JSONObject;

import java.sql.Time;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import net.sf.json.JSONObject;

/**
 * ?????????????
 *
 * @author wxh
 */

public class SysterminalinfDataBean extends BaseDataBean {
    private static final long IonlineTime = 5 * 60 * 1000;

    private long idTerminal;

    private String cardType;

    private String cardName;
    private String cardModel;

    private String card_version;

    private String userId;

    private String userName;

    private String userDepart;

    private String userZone;
    private String userOrg;
    private String cn;

    private String policeNumber;

    private long in_flux;
    private long out_flux;

    private int count;
    private int warncount;


    private int othercount;      //其他   违规统计
    private int networkcount;    //网络   违规统计
    private int processcount;     //进程   违规统计
    private int peripheralcount;    // 外设  违规统计
    private int URLcount;           //url      违规统计
    private int fluxcount;          //流量     违规统计
    private int separationcount;   //  机卡分离  违规统计

    public int getOthercount() {
        return othercount;
    }

    public void setOthercount(int othercount) {
        this.othercount = othercount;
    }

    public int getNetworkcount() {
        return networkcount;
    }

    public void setNetworkcount(int networkcount) {
        this.networkcount = networkcount;
    }

    public int getProcesscount() {
        return processcount;
    }

    public void setProcesscount(int processcount) {
        this.processcount = processcount;
    }

    public int getPeripheralcount() {
        return peripheralcount;
    }

    public void setPeripheralcount(int peripheralcount) {
        this.peripheralcount = peripheralcount;
    }

    public int getURLcount() {
        return URLcount;
    }

    public void setURLcount(int URLcount) {
        this.URLcount = URLcount;
    }

    public int getFluxcount() {
        return fluxcount;
    }

    public void setFluxcount(int fluxcount) {
        this.fluxcount = fluxcount;
    }

    public int getSeparationcount() {
        return separationcount;
    }

    public void setSeparationcount(int separationcount) {
        this.separationcount = separationcount;
    }

    public int getWarncount() {
        return warncount;
    }

    public void setWarncount(int warncount) {
        this.warncount = warncount;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public long getIn_flux() {
        return in_flux;
    }

    public void setIn_flux(long inFlux) {
        in_flux = inFlux;
    }

    public long getOut_flux() {
        return out_flux;
    }

    public void setOut_flux(long outFlux) {
        out_flux = outFlux;
    }

    public String getUserOrg() {
        return userOrg;
    }

    public void setUserOrg(String userOrg) {
        this.userOrg = userOrg;
    }

    public String getPolicecate() {
        return policecate;
    }

    public void setPolicecate(String policecate) {
        this.policecate = policecate;
    }

    private String policecate;

    private String regTime;

    private boolean ifcancel = false;

    private String flag;
    private long lastDate;
    private String ip = "0.0.0.0";
    private String onlineTime;

    public SysterminalinfDataBean() {
        status = 0;

    }

    public String getCn() {
        return cn;
    }

    public void setCn(String cn) {
        this.cn = cn;
    }

    public String getOnlineTime() {
        long temp = System.currentTimeMillis()
                - Timestamp.valueOf(regTime).getTime();
        Time time2 = new Time(temp);
        onlineTime = time2.getHours() + ":" + time2.getMinutes() + ":"
                + time2.getSeconds();
        return onlineTime;
    }

    /**
     * ??????
     */
    private boolean isBlock = false;

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public long getIdTerminal() {
        return idTerminal;
    }

    public void setIdTerminal(long idTerminal) {
        this.idTerminal = idTerminal;
    }

    public String getCardType() {
        return cardType;
    }

    public void setCardType(String cardType) {
        this.cardType = cardType;
    }

    public String getCardName() {
        return cardName;
    }

    public void setCardName(String cardName) {
        this.cardName = cardName;
    }

    public String getCard_version() {
        return card_version;
    }

    public void setCard_version(String card_version) {
        this.card_version = card_version;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserDepart() {
        return userDepart;
    }

    public void setUserDepart(String userDepart) {
        this.userDepart = userDepart;
    }

    public String getUserZone() {
        return userZone;
    }

    public void setUserZone(String userZone) {
        this.userZone = userZone;
    }

    public String getPoliceNumber() {
        return policeNumber;
    }

    public void setPoliceNumber(String policeNumber) {
        this.policeNumber = policeNumber;
    }

    public String getRegTime() {
        return regTime;
    }

    public void setRegTime(String regTime) {
        this.regTime = regTime;
    }

    public boolean getIfcancel() {
        return ifcancel;
    }

    public void setIfcancel(boolean ifcancel) {
        this.ifcancel = ifcancel;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public void setLastDate(long lastDate) {
        this.lastDate = lastDate;
    }

    public long getLastDate() {
        return lastDate;
    }

    public boolean isBlock() {
        return isBlock;
    }

    public void setBlock(boolean isBlock) {
        this.isBlock = isBlock;
    }

    /**
     * ???????
     *
     * @return
     */

    public boolean isOnline() {
        long temp = System.currentTimeMillis() - getLastDate();
        if (temp <= SysterminalinfDataBean.IonlineTime)
            return true;
        else
            return false;
    }


    public String getCardModel() {
        return cardModel;
    }

    public void setCardModel(String cardModel) {
        this.cardModel = cardModel;
    }

    public String toJsonString() {
        // String
        // test="{status:'0',ifcancel:false,ip:'192.168.1.11',ifblock:false,cardtype:'001',cardmodel:'200',cardver:'v1.0',policecate:'1',policeno:'10101',policename:'test6',idno:'110108197903034913',org:'???1',depart:'koal',region:'?????',logindate:'2012-03-14 01:32:38',onlinetime:'01:32:38',createdate:'2012-03-27 10:15:21'}";
        StringBuffer buffer = new StringBuffer();
        buffer.append("{");
        buffer.append("status:'" + status + "'");
        buffer.append(",ifcancel:" + ifcancel);
        buffer.append(",ip:'" + ip + "'");
        buffer.append(",ifblock:" + isBlock());
        if (cardType.startsWith("'"))
            buffer.append(",cardtype:" + cardType);
        else
            buffer.append(",cardtype:'" + cardType + "'");
        if (cardModel.startsWith("'"))
            buffer.append(",cardmodel:" + cardModel);
        else
            buffer.append(",cardmodel:'" + cardModel + "'");
        if (card_version.startsWith("'"))
            buffer.append(",cardver:" + card_version);
        else
            buffer.append(",cardver:'" + card_version + "'");
        if (policecate.startsWith("'"))
            buffer.append(",policecate:" + policecate);
        else
            buffer.append(",policecate:'" + policecate + "'");
        buffer.append(",policeno:'" + policeNumber + "'");
        buffer.append(",policename:'" + userName + "'");
        buffer.append(",idno:'" + userId + "'");
        buffer.append(",org:'" + userOrg + "'");
        buffer.append(",depart:'" + userDepart + "'");
        buffer.append(",region:'" + userZone + "'");
        buffer.append(",logindate:'" + regTime + "'");
        buffer.append(",onlinetime:'" + getOnlineTime() + "'");
        buffer.append(",influx:" + getIn_flux());
        buffer.append(",outflux:" + getOut_flux());
        buffer.append(",createdate:'" + regTime + "'}");
        return buffer.toString();
    }

    public static SysterminalinfDataBean jsonToObject(String json) {
        SysterminalinfDataBean bean = new SysterminalinfDataBean();
        if (json.startsWith("{")) {
            json = json.substring(1);
        }
        if (json.endsWith("},")) {
            json = json.substring(0, json.length() - 3);
        }
        if (json.endsWith("}")) {
            json = json.substring(0, json.length() - 2);
        }
        json.replaceAll("'", "");
        String[] jsonbean = json.split(",");

        for (int i = 0; i < jsonbean.length; i++) {
            if (jsonbean[i].startsWith("status")) {
                String temp = jsonbean[i].split(":")[1];
                if (temp.startsWith("'")) {
                    temp = temp.substring(1);
                }
                if (temp.endsWith("'")) {
                    temp = temp.substring(0, temp.length() - 1);
                }
                bean.setStatus(Integer.parseInt(temp));
            }
            if (jsonbean[i].startsWith("ifcancel")) {
                bean.setIfcancel(Boolean.valueOf(jsonbean[i].split(":")[1]));
            }
            if (jsonbean[i].startsWith("ip")) {
                String temp = jsonbean[i].split(":")[1];
                if (temp.startsWith("'")) {
                    temp = temp.substring(1);
                }
                if (temp.endsWith("'")) {
                    temp = temp.substring(0, temp.length() - 1);
                }
                bean.setIp(temp);
            }
            if (jsonbean[i].startsWith("ifblock")) {
                bean.setBlock(Boolean.valueOf(jsonbean[i].split(":")[1]));
            }
            if (jsonbean[i].startsWith("cardtype")) {
                String temp = jsonbean[i].split(":")[1];
                if (temp.startsWith("'")) {
                    temp = temp.substring(1);
                }
                if (temp.endsWith("'")) {
                    temp = temp.substring(0, temp.length() - 1);
                }
                bean.setCardType(temp);
            }
            if (jsonbean[i].startsWith("cardmodel")) {
                String temp = jsonbean[i].split(":")[1];
                if (temp.startsWith("'")) {
                    temp = temp.substring(1);
                }
                if (temp.endsWith("'")) {
                    temp = temp.substring(0, temp.length() - 1);
                }
                bean.setCardModel(temp);
            }
            if (jsonbean[i].startsWith("cardver")) {
                String temp = jsonbean[i].split(":")[1];
                if (temp.startsWith("'")) {
                    temp = temp.substring(1);
                }
                if (temp.endsWith("'")) {
                    temp = temp.substring(0, temp.length() - 1);
                }
                bean.setCard_version(temp);
            }
            if (jsonbean[i].startsWith("policecate")) {
                String temp = jsonbean[i].split(":")[1];
                if (temp.startsWith("'")) {
                    temp = temp.substring(1);
                }
                if (temp.endsWith("'")) {
                    temp = temp.substring(0, temp.length() - 1);
                }
                bean.setPolicecate(temp);
            }
            if (jsonbean[i].startsWith("policeno")) {
                String temp = jsonbean[i].split(":")[1];
                if (temp.startsWith("'")) {
                    temp = temp.substring(1);
                }
                if (temp.endsWith("'")) {
                    temp = temp.substring(0, temp.length() - 1);
                }
                bean.setPoliceNumber(temp);
            }
            if (jsonbean[i].startsWith("policename")) {
                String temp = jsonbean[i].split(":")[1];
                if (temp.startsWith("'")) {
                    temp = temp.substring(1);
                }
                if (temp.endsWith("'")) {
                    temp = temp.substring(0, temp.length() - 1);
                }
                bean.setUserName(temp);
            }
            if (jsonbean[i].startsWith("idno")) {
                String temp = jsonbean[i].split(":")[1];
                if (temp.startsWith("'")) {
                    temp = temp.substring(1);
                }
                if (temp.endsWith("'")) {
                    temp = temp.substring(0, temp.length() - 1);
                }
                bean.setUserId(temp);
                com.inetec.ichange.service.monitor.uplink.databean.SysterminalinfDataBean ids = new SysterminalinfDao().getSysterminalInfByUserid(temp);
                if (ids != null) {

                    bean.setIdTerminal(ids.getId());
                    bean.setUserDepart(ids.getUserDepart());
                    //bean.setUserOrg(ids.getUserZone());
                    bean.setUserZone(ids.getUserZone());
                } else {
                    bean.setIdTerminal(-1);
                    bean.setUserDepart("");
                    //bean.setUserOrg(ids.getUserZone());
                    bean.setUserZone("");
                }
            }
            if (jsonbean[i].startsWith("org")) {
                String temp = jsonbean[i].split(":")[1];
                if (temp.startsWith("'")) {
                    temp = temp.substring(1);
                }
                if (temp.endsWith("'")) {
                    temp = temp.substring(0, temp.length() - 1);
                }
                bean.setUserOrg(temp);
            }
            if (jsonbean[i].startsWith("depart")) {
                String temp = jsonbean[i].split(":")[1];
                if (temp.startsWith("'")) {
                    temp = temp.substring(1);
                }
                if (temp.endsWith("'")) {
                    temp = temp.substring(0, temp.length() - 1);
                }
                bean.setUserDepart(temp);
            }
            if (jsonbean[i].startsWith("region")) {
                String temp = jsonbean[i].split(":")[1];
                if (temp.startsWith("'")) {
                    temp = temp.substring(1);
                }
                if (temp.endsWith("'")) {
                    temp = temp.substring(0, temp.length() - 1);
                }
                bean.setUserZone(temp);
            }
            /*if (jsonbean[i].startsWith("onlinetime")) {
                bean.set(jsonbean[i].split(":")[1]);
            }*/
            /* if (jsonbean[i].startsWith("influx")) {
                bean.setIn_flux(Long.valueOf(jsonbean[i].split(":")[1]));
            }
            if (jsonbean[i].startsWith("outflux")) {
                bean.setOut_flux(Long.valueOf(jsonbean[i].split(":")[1]));
            }*/
            if (jsonbean[i].startsWith("createdate")) {
                String tem = jsonbean[i].substring("createdate:".length());
                if (tem.startsWith("'")) {
                    tem = tem.substring(1);
                }
                if (tem.endsWith("'")) {
                    tem = tem.substring(0, tem.length() - 1);
                }
                bean.setRegTime(tem);
            }

        }
        return bean;
    }

    public static SysterminalinfDataBean jsonToObjectKoarl(String json) {
        SysterminalinfDataBean bean = new SysterminalinfDataBean();
        if (json.startsWith("{")) {
            json = json.substring(1);
        }
        if (json.endsWith("},")) {
            json = json.substring(0, json.length() - 3);
        }
        if (json.endsWith("}")) {
            json = json.substring(0, json.length() - 2);
        }

        String[] jsonbean = json.split(",");
        for (int i = 0; i < jsonbean.length; i++) {
            if (jsonbean[i].startsWith("cardtype")) {
                bean.setCardType(jsonbean[i].split(":")[1]);
            }
            if (jsonbean[i].startsWith("cardmodel")) {
                bean.setCardModel(jsonbean[i].split(":")[1]);
            }
            if (jsonbean[i].startsWith("cardver")) {
                bean.setCard_version(jsonbean[i].split(":")[1]);
            }
            if (jsonbean[i].startsWith("policecate")) {
                bean.setPolicecate(jsonbean[i].split(":")[1]);
            }
            if (jsonbean[i].startsWith("policeno")) {
                bean.setPoliceNumber(jsonbean[i].split(":")[1]);
            }
            if (jsonbean[i].startsWith("policename")) {
                String temp = jsonbean[i].split(":")[1];
                if (temp.split(" ").length == 2) {
                    bean.setUserName(temp.split(" ")[0]);
                    bean.setUserId(temp.split(" ")[1]);
                }
                if (bean.getPoliceNumber() == null) {
                    bean.setPoliceNumber(bean.getUserId());
                }
                bean.setCn(temp);
            }
            /*if (jsonbean[i].startsWith("idno")) {
                   String temp = jsonbean[i].split(":")[1];
                   if (!temp.equalsIgnoreCase("null")) {
                       bean.setUserId(jsonbean[i].split(":")[1]);
                   } else {
                       bean.setUserId(bean.getPoliceNumber());
                   }
               }*/
            if (jsonbean[i].startsWith("org")) {
                bean.setUserOrg(jsonbean[i].split(":")[1]);
            }
            if (jsonbean[i].startsWith("depart")) {
                bean.setUserDepart(jsonbean[i].split(":")[1]);
            }
            if (jsonbean[i].startsWith("region")) {
                bean.setUserZone(jsonbean[i].split(":")[1]);
            }
            if (jsonbean[i].startsWith("createdate")) {
                String tem = jsonbean[i].substring("createdate:".length());
                if (tem.startsWith("'")) {
                    tem = tem.substring(1);
                }
                if (tem.endsWith("'")) {
                    tem = tem.substring(0, tem.length() - 1);
                }
                bean.setRegTime(tem);
            }
        }

        return bean;
    }

    public static List<SysterminalinfDataBean> stringToBeans(String data) {
        List<SysterminalinfDataBean> beans = new ArrayList<SysterminalinfDataBean>();
        if (data.startsWith("[")) {
            data = data.substring(1);
        }
        if (data.endsWith("]")) {
            data = data.substring(0, data.length() - 2);
        }
        String[] obets = data.split("},");
        for (int i = 0; i < obets.length - 1; i++) {
            beans.add(SysterminalinfDataBean.jsonToObjectKoarl(obets[i]));
        }
        return beans;
    }

    public static List<SysterminalinfDataBean> stringToBeansForMc(String data) {
        List<SysterminalinfDataBean> beans = new ArrayList<SysterminalinfDataBean>();
        if (data.startsWith("[")) {
            data = data.substring(1);
        }
        if (data.endsWith("]")) {
            data = data.substring(0, data.length() - 2);
        }
        String[] obets = data.split("},");
        for (int i = 0; i < obets.length - 1; i++) {
            beans.add(SysterminalinfDataBean.jsonToObject(obets[i]));
        }
        return beans;
    }

    public boolean equals(Object o) {
        boolean result = false;
        SysterminalinfDataBean t = (SysterminalinfDataBean) o;
        if (t.getUserId() == this.getUserId()
                && t.getUserName().equalsIgnoreCase(this.getUserName())) {
            result = true;
        }
        return result;
    }

    public static void main(String arg[]) throws Exception {
        String terminal = "[{cardtype:'tf',cardmodel:'200',cardver:'v1.0',policecate:'1',policeno:123654,policename:sxl 123456789123456789,idno:123456789123465798,org:???????,depart:??????,region:??????,createdate:2012-04-19 03:26:22},{cardtype:'tf',cardmodel:'200',cardver:'v1.0',policecate:'1',policeno:null,policename:sxl 123456789012345678,idno:null,org:???????,depart:??????,region:?????,createdate:2012-04-13 09:10:54},{cardtype:'tf',cardmodel:'200',cardver:'v1.0',policecate:'1',policeno:123654,policename:???? 333333333333333333,idno:333333333333333333,org:????????,depart:??????,region:?????,createdate:2012-04-12 08:01:38},{cardtype:'tf',cardmodel:'200',cardver:'v1.0',policecate:'1',policeno:654321,policename:???? 222222222222222222,idno:222222222222222222,org:????????,depart:???????,region:?????,createdate:2012-04-12 08:00:21},{cardtype:'tf',cardmodel:'200',cardver:'v1.0',policecate:'1',policeno:123456,policename:???? 111111111111111111,idno:111111111111111111,org:???????,depart:??????,region:?????,createdate:2012-04-12 07:57:10},{cardtype:'tf',cardmodel:'200',cardver:'v1.0',policecate:'1',policeno:12345678,policename:????1 123456789012345678,idno:123456789012345678,org:???????,depart:??????,region:?????,createdate:2012-04-11 08:43:16},{total:6,beginno:null,engno:null,pagesize:null}]";
        List<SysterminalinfDataBean> beanlist = SysterminalinfDataBean
                .stringToBeans(terminal);
        for (int i = 0; i < beanlist.size(); i++) {
            System.out.println(beanlist.get(i).toJsonString());
        }
        // System.out.println(SysterminalinfDataBean.());
        /*
           *
           * SysterminalinfDataBean bean = SysterminalinfDataBean
           * .jsonToObjectKoarl(terminal); System.out.println("bean:" +
           * bean.toJsonString());
           */
    }

}
