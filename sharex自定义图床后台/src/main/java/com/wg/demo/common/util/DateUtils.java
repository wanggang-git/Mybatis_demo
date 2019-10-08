/**
 * com.sunrise.commons.utils.DateUtils.java
 */
package com.wg.demo.common.util;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @author 贾海勇
 * @version 0.1
 * @file DateUtils.java
 * @todo 日期处理类
 * Copyright(C), 2013-2014
 * Shenzhen Coordinates Software Development Co., Ltd
 * History
 * 1. Date: 2014-4-18
 * Author: yangzhengxin(Jason)
 * Modification: this file was created
 * 2. ...
 */
public class DateUtils {

    public DateUtils() {
        super();
    }

    private static SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private static SimpleDateFormat sdf2 = new SimpleDateFormat("yyyyMMdd HH:mm:ss");
    private static SimpleDateFormat sdf3 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
    private static SimpleDateFormat sdf4 = new SimpleDateFormat("yyyyMMddHHmmss");
    private static SimpleDateFormat sdf5 = new SimpleDateFormat("yyyyMMdd");
    private static SimpleDateFormat sdf6 = new SimpleDateFormat("yyyyMMddHH");
    private static SimpleDateFormat sdf7 = new SimpleDateFormat("yyMMdd");
    private static SimpleDateFormat sdf8 = new SimpleDateFormat("yyyyMM");
    private static SimpleDateFormat sdf9 = new SimpleDateFormat("yyyy-MM-dd");
    private static SimpleDateFormat sdf10 = new SimpleDateFormat("yyyy-MM");
    public static String getDayStr(Date date) {
        return sdf5.format(date);
    }
    public static String getDateStr(Date date) {
        return sdf4.format(date);
    }
    public static String getDateString(Date date) {
        return sdf1.format(date);
    }

    public static String[] getTime(Date time) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(time);
        int dayWeek = cal.get(Calendar.DAY_OF_WEEK);
        if (1 == dayWeek) {
            cal.add(Calendar.DAY_OF_MONTH, -1);
        }
        cal.setFirstDayOfWeek(Calendar.MONDAY);
        int day = cal.get(Calendar.DAY_OF_WEEK);
        cal.add(Calendar.DATE, cal.getFirstDayOfWeek() - day);
        String timeBegin = sdf9.format(cal.getTime());
        cal.add(Calendar.DATE, 6);
        String timeEnd = sdf9.format(cal.getTime());
        return new String[]{timeBegin + " 00:00:00", timeEnd + " 23:59:59"};
    }

    public static Calendar getDate(String time) throws ParseException {
        Calendar cal = Calendar.getInstance();
        Date date = sdf9.parse(time);
        cal.setTime(date);
        return cal;
    }

    public static Date getDateWithHour(String time) throws ParseException {
        return sdf1.parse(time);
    }

    public static String getDay(Calendar time) {
        return sdf9.format(time.getTime());
    }

    public static String[] getTodayStr(){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String dateNowStr = sdf.format(new Date());
        return new String[]{dateNowStr + " 00:00:00", dateNowStr + " 23:59:59"};
    }
}
