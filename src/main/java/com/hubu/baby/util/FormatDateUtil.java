package com.hubu.baby.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @Description: 日期匹配工具类
 * @Source: JDK 1.8
 * @Author: ZhaoKunsong
 * @Date: 2018-04-20 19:57
 * @Since: version 1.0
 **/
public class FormatDateUtil {

    public FormatDateUtil() {
    }

    // 设置日期格式
    static public String formatDateTime() {
        // 获取当前时间
        Date date = new Date();
        // DateFormat format=new SimpleDateFormat("yyyy-MM-dd");
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return format.format(date);
    }

    static public String formatDateTime2() {
        // 获取当前时间
        Date date = new Date();
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        // DateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return format.format(date);
    }

    // 设置日期格式，返回Date类型
    static public Date formatDate() throws ParseException {
        Date date = new Date();
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String time = format.format(date);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date2 = sdf.parse(time);
        return date2;
    }

    // 将一个字符串（格式化）日期转化为Date日期
    static public String fomatDate(String dateTime) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat(" yyyy-MM-dd HH:mm:ss ");// 传入的字符串参数的格式一定要与此相同
        Date date = sdf.parse(dateTime);
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return format.format(date);
    }

    // 将一个字符串（格式化）日期转化为Date日期
    static public String fomatDate31(String dateTime) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");// 传入的字符串参数的格式一定要与此相同
        Date date = sdf.parse(dateTime);
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        return format.format(date);
    }

    // 将一个字符串（格式化）日期转化为Date日期
    static public String fomatDate3(String dateTime) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");// 传入的字符串参数的格式一定要与此相同
        Date date = sdf.parse(dateTime);
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return format.format(date);
    }

    // 将一个字符串（格式化）日期转化为Date日期
    static public Date fomatDate2(String dateTime) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");// 传入的字符串参数的格式一定要与此相同
        Date date = sdf.parse(dateTime);
        return date;
    }

    // 将一个字符串（格式化）日期转化为Date日期
    static public Date fomatDate4(String dateTime) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// 传入的字符串参数的格式一定要与此相同
        Date date = sdf.parse(dateTime);
        return date;
    }

    // 格式化日期为字符串 "yyyy-MM-dd hh:mm"
    static public String formatDateTime(Date basicDate, String strFormat) {
        SimpleDateFormat df = new SimpleDateFormat(strFormat);
        return df.format(basicDate);
    }

    // 格式化日期为字符串 "yyyy-MM-dd hh:mm"
    static public String formatDateTime(String basicDate, String strFormat) {
        SimpleDateFormat df = new SimpleDateFormat(strFormat);
        Date tmpDate = null;
        try {
            tmpDate = df.parse(basicDate);
        } catch (Exception e) {
            // 日期型字符串格式错误
        }
        return df.format(tmpDate);
    }

    // 当前日期加减n天后的日期，返回String (yyyy-mm-dd)
    static public String nDaysAftertoday(int n) {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Calendar rightNow = Calendar.getInstance();
        // rightNow.add(Calendar.DAY_OF_MONTH,-1);
        rightNow.add(Calendar.DAY_OF_MONTH, +n);
        return df.format(rightNow.getTime());
    }

    // 当前日期加减n天后的日期，返回String (yyyy-mm-dd)
    static public Date nDaysAfterNowDate(int n) {
        Calendar rightNow = Calendar.getInstance();
        // rightNow.add(Calendar.DAY_OF_MONTH,-1);
        rightNow.add(Calendar.DAY_OF_MONTH, +n);
        return rightNow.getTime();
    }

    // 给定一个日期型字符串，返回加减n天后的日期型字符串
    static public String nDaysAfterOneDateString(String basicDate, int n) {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Date tmpDate = null;
        try {
            tmpDate = df.parse(basicDate);
        } catch (Exception e) {
            // 日期型字符串格式错误
        }
        long nDay = (tmpDate.getTime() / (24 * 60 * 60 * 1000) + 1 + n) * (24 * 60 * 60 * 1000);
        tmpDate.setTime(nDay);

        return df.format(tmpDate);
    }

    // 给定一个日期型字符串，返回加n小时后的日期型字符串
    static public String nHoursAfterOneDateString(String basicDate, int n) {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date tmpDate = null;
        try {
            tmpDate = df.parse(basicDate);
        } catch (Exception e) {
            // 日期型字符串格式错误
        }
        long nDay = (tmpDate.getTime() / (60 * 60 * 1000) + n) * (60 * 60 * 1000);
        tmpDate.setTime(nDay);

        return df.format(tmpDate);
    }

    // 给定一个日期型字符串，返回减n小时后的日期型字符串
    static public String nHoursBeforeOneDateString(String basicDate, int n) {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date tmpDate = null;
        try {
            tmpDate = df.parse(basicDate);
        } catch (Exception e) {
            // 日期型字符串格式错误
        }
        long nDay = (tmpDate.getTime() / (60 * 60 * 1000) - n) * (60 * 60 * 1000);
        tmpDate.setTime(nDay);

        return df.format(tmpDate);
    }

    // 给定一个日期，返回加减n天后的日期
    static public Date nDaysAfterOneDate(Date basicDate, int n) {
        long nDay = (basicDate.getTime() / (24 * 60 * 60 * 1000) + 1 + n) * (24 * 60 * 60 * 1000);
        basicDate.setTime(nDay);

        return basicDate;
    }

    // 计算两个日期相隔的天数
    static public int nDaysBetweenTwoDate(Date firstDate, Date secondDate) {
        int nDay = (int) ((secondDate.getTime() - firstDate.getTime()) / (24 * 60 * 60 * 1000));
        return nDay;
    }

    // 计算两个日期相隔的天数
    static public int nDaysBetweenTwoDate(String firstString, String secondString) {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Date firstDate = null;
        Date secondDate = null;
        try {
            firstDate = df.parse(firstString);
            secondDate = df.parse(secondString);
        } catch (Exception e) {
            // 日期型字符串格式错误
        }

        int nDay = (int) ((secondDate.getTime() - firstDate.getTime()) / (24 * 60 * 60 * 1000));
        return nDay;
    }

    // 计算两个日期相隔的小时数数
    static public int nHoursBetweenTwoDate(String firstString, String secondString) {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date firstDate = null;
        Date secondDate = null;
        try {
            firstDate = df.parse(firstString);
            secondDate = df.parse(secondString);
        } catch (Exception e) {
            // 日期型字符串格式错误
        }

        int nDay = (int) ((secondDate.getTime() - firstDate.getTime()) / (60 * 60 * 1000));
        return nDay;
    }
}
