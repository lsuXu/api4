package com.wxtoplink.api4.util;

import android.text.TextUtils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by 上官丹意 on 2016/05/09 14:55.
 */
public class DateUtil {

    public static SimpleDateFormat formatYearCn = new SimpleDateFormat("yyyy年MM月dd日");
    /**
     * 15:03:34
     */
    public static final String FORMAT_HH_MM_SS = "HH:mm:ss";
    /**
     * 15:03
     */
    public static final String FORMAT_HH_MM = "HH:mm";
    /**
     * 12月12日
     */
    public static final String FORMAT_MM_DD = "MM月dd日";
    /**
     * 20120219
     */
    public static final String FORMAT_YYYYMMDD = "yyyyMMdd";
    /**
     * 2012021909
     */
    public static final String FORMAT_YYYYMMDDHH = "yyyyMMddHH";
    /**
     * 2012-02-19
     */
    public static final String FORMAT_YYYY_MM_DD = "yyyy-MM-dd";
    public static final String FORMAT_YYYY_MM_DD1 = "yyyy.MM.dd";
    public static final String FORMAT_MM_DD2 = "MM.dd";

    public static final String FORMAT_YYYY_MM = "yyyy-MM";
    public static final String FORMAT_YYYY = "yyyy";
    /**
     * 2012-02-19
     */
    public static final String FORMAT_YYYY_MM_DD_ZH = "yyyy年MM月dd日";
    /**
     * 2012-02-19 05:11
     */
    public static final String FORMAT_YYYY_MM_DD_HH_MM = "yyyy-MM-dd HH:mm";
    /**
     * 20120219150334
     */
    public static final String FORMAT_YYYYMMDDHHMMSS = "yyyyMMddHHmmss";
    /**
     * 20120219150334
     */
    public static final String FORMAT_DATABASE = "yyyyMMddHHmmss";
    /**
     * 20120219150334
     */
    public static final String FORMAT_YYYYMMDDHHMMSSSSS = "yyyyMMddHHmmssSSS";
    /**
     * 2012-02-19 15:03:34
     */
    public static final String FORMAT_YYYY_MM_DD_HH_MM_SS = "yyyy-MM-dd HH:mm:ss";
    /**
     * 2012-02-19 15:03:34.876
     */
    public static final String FORMAT_YYYY_MM_DD_HH_MM_SS_SSS = "yyyy-MM-dd HH:mm:ss.SSS";
    /**
     * 2012-02-19 12时19分
     */
    public static final String FORMAT_YYYY_MM_DD_HHMM_WORD = "yyyy-MM-dd HH时mm分";
    /**
     * 2012年02月19日12:19
     */
    public static final String FORMAT_YYYY_MM_DD_HHMM_WORD_ZH = "yyyy年MM月dd日HH:mm";


    /**
     * @param dateStr 日期字符串
     * @param format  日期字符串样式
     * @return Date对象
     * @Title: formatString2Date
     * @Description: 将日期字符串转换成Date对象
     * @date: 2013-5-22 下午2:07:29
     */
    public static Date formatString2Date(String dateStr, String format) {
        if (TextUtils.isEmpty(dateStr)) {
            return null;
        }
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(format);
            return sdf.parse(dateStr.trim());
        } catch (Exception e) {
            return null;
        }
    }

    public static Long formatString2Long(String dateStr, String format) {
        long date = 0;
        if (TextUtils.isEmpty(dateStr)) {
            return null;
        }
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(format);
            date = sdf.parse(dateStr.trim()).getTime();
        } catch (Exception e) {
        }
        return date;
    }

    /**
     * @param date   日期对象
     * @param format 日期字符串样式
     * @return 日期字符串
     * @Title: formatDate2String
     * @Description: 将日期对象转换成日期字符串
     * @date: 2013-5-22 下午2:12:17
     */
    public static String formatDate2String(Date date, String format) {
        if (date == null) {
            return "";
        }
        try {
            SimpleDateFormat formatPattern = new SimpleDateFormat(format);
            return formatPattern.format(date);
        } catch (Exception e) {
            return "";
        }
    }

    public static String formatLong2String(long dateLong, String format) {

        // 1、将原始日期字符串转换成Date对象
        Date date = new Date(dateLong);

        // 2、将Date对象转换成目标样式字符串
        return formatDate2String(date, format);
    }
}