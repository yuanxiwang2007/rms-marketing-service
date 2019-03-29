package com.rms.marketing.common.util;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.Date;

/**
 * 日期转化工具类
 * Created by wk on 2017/7/21.
 */
public class DateUtil {
    private static String patten = "yyyy-MM-dd HH:mm:ss";
    private static String simplePatten = "yyMMddHHmmss";

    public static String format(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat(patten);
        return sdf.format(date);
    }

    public static String formatSimple(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat(simplePatten);
        return sdf.format(date);
    }

    public static Integer dateToAge(Date date) {
        if (date == null) {
            return null;
        }
        LocalDateTime localDateTime = LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault());
        LocalDate birth = localDateTime.toLocalDate();
        return (int) birth.until(LocalDate.now(), ChronoUnit.YEARS);
    }

    public static Date ageToDate(Integer age) {
        LocalDate localDate = LocalDate.now();
        localDate = localDate.minusYears(age);
        Instant instant = localDate.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant();
        return Date.from(instant);
    }

    /**
     * 计算两个时间相差秒数
     * @param startDate
     * @param endDate
     * @return
     */
    public static int CompareTimeSecond(Date startDate, Date endDate) {
        long endTime = endDate.getTime();
        long startTime = startDate.getTime();
        int second = (int) ((endTime - startTime) / 1000);
        return second;
    }
    /**
     * 时间转字符串
     * @param time
     * @param pattern
     * @return
     */
    public static String dateToString(Date time, String pattern) {
        String temp = "";
        if (pattern != null && !"".equals(pattern)) {
            temp = pattern;
        } else {
            temp = patten;
        }
        SimpleDateFormat df = new SimpleDateFormat(temp);
        try {
            String str = df.format(time);
            return str;
        } catch (Exception e) {
            System.out.println("时间格式转换异常" + e.getMessage());
            return null;
        }
    }
    /**
     * 时间转字符串
     * @param time
     * @param pattern
     * @return
     */
    public static Date dateFormat(Date time, String pattern) {
        String temp = "";
        if (pattern != null && !"".equals(pattern)) {
            temp = pattern;
        } else {
            temp = patten;
        }
        SimpleDateFormat df = new SimpleDateFormat(temp);
        try {
            String str = df.format(time);
            Date formateDate=stringToDate(str,temp);
            return formateDate;
        } catch (Exception e) {
            System.out.println("时间格式转换异常" + e.getMessage());
            return null;
        }
    }
    /**
     * 字符串转时间
     * @param time
     * @param pattern
     * @return
     */
    public static Date stringToDate(String time, String pattern) {

        String temp = "";
        if (pattern != null && !"".equals(pattern)) {
            temp = pattern;
        } else {
            temp = patten;
        }
        SimpleDateFormat df = new SimpleDateFormat(temp);
        try {
            Date endDate = df.parse(time);
            return endDate;
        } catch (Exception e) {
            System.out.println("时间格式转换异常" + e.getMessage());
            return null;
        }
    }
    public static void main(String[] args) {
        System.out.println(format(ageToDate(10)));
    }

    //指定日期加上指定时间
    public static Date dateAddTime(Date date, int unit, int amount) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String currdate = format.format(date);
        Calendar ca = Calendar.getInstance();
        ca.add(unit, amount);// num为增加的天数，可以改变的
        date = ca.getTime();
        return date;
    }
}
