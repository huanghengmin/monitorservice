package com.inetec.ichange.service.monitor.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by IntelliJ IDEA.
 * User: Administrator
 * Date: 12-11-28
 * Time: ÏÂÎç4:33
 * To change this template use File | Settings | File Templates.
 */
public class StringNumberUtils {
    public static boolean isNumeric(String str)
    {
        Pattern pattern = Pattern.compile("[0-9]*");
        Matcher isNum = pattern.matcher(str);
        if( !isNum.matches() )
        {
            return false;
        }
        return true;
    }
    public static boolean isEndWith(String srcstr,String endflag){
        return srcstr.endsWith(endflag);
    }
}
