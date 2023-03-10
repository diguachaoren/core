package com.digua.core.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by yujia
 * on 2017/11/26.
 */

public class TimeUtils {


    private final static long minute = 60 * 1000;// 1分钟

    private final static long hour = 60 * minute;// 1小时

    private final static long day = 24 * hour;// 1天

    private final static long month = 31 * day;// 月

    private final static long year = 12 * month;// 年

    public final static String FORMAT_1 = "yyyy-MM-dd HH:mm:ss";
    public final static String FORMAT_2 = "yyyy-MM-dd";
    public final static String FORMAT_3 = "yyyy/MM/dd";
    public final static String FORMAT_4 = "";

    /**
     * 返回文字描述的日期
     * @return
     */
    public static String friendlyTimeFormat(String time) {
        Date date = toDate(time,FORMAT_1);
        if (date == null) {
            return null;
        }
        long diff = new Date().getTime() - date.getTime();
        long r = 0;
        if (diff > year) {
            r = (diff / year);
            return r + "年前";
        }
        if (diff > month) {
            r = (diff / month);
            return r + "个月前";
        }
        if (diff > day) {
            r = (diff / day);
            return r + "天前";
        }
        if (diff > hour) {
            r = (diff / hour);
            return r + "个小时前";
        }
        if (diff > minute) {
            r = (diff / minute);
            return r + "分钟前";
        }
        return "刚刚";
    }


    public static String friendlyTimeFormat2(String sdate) {
        Date time = toDate(sdate,FORMAT_1);
        if (time == null) {
            return "";
        }
        String ftime = "";
        Calendar cal = Calendar.getInstance();

        //判断是否是同一天
        String curDate = dateFormater2.get().format(cal.getTime());
        String paramDate = dateFormater2.get().format(time);
        if (curDate.equals(paramDate)) {
            int hour = (int) ((cal.getTimeInMillis() - time.getTime()) / 3600000);
            if (hour == 0) {
                ftime = Math.max((cal.getTimeInMillis() - time.getTime()) / 60000, 1) + "分钟前";
            } else {
                ftime = hour + "小时前";
            }
            return ftime;
        }


        long lt = time.getTime() / 86400000;
        long ct = cal.getTimeInMillis() / 86400000;
        int days = (int) (ct - lt);
        if (days == 0) {
            int hour = (int) ((cal.getTimeInMillis() - time.getTime()) / 3600000);
            if (hour == 0) {
                ftime = Math.max((cal.getTimeInMillis() - time.getTime()) / 60000, 1) + "分钟前";
            } else {
                ftime = hour + "小时前";
            }
        } else if (days == 1) {
            ftime = "昨天";
        } else if (days == 2) {
            ftime = "前天";
        }
        // else if(days > 2 && days <= 10){
        // ftime = days+"天前";
        // }
        else if (days > 2 && days <= 30) {
            ftime = days + "天前";
        } else if (days > 30 && days <= 60) {
            ftime = "1个月前";
        } else if (days > 60 && days <= 90) {
            ftime = "2个月前";
        } else if (days > 90 && days <= 120) {
            ftime = "3个月前";
        } else if (days > 120 && days <= 150) {
            ftime = "4个月前";
        } else if (days > 150 && days <= 180) {
            ftime = "5个月前";
        } else if (days > 180 && days <= 210) {
            ftime = "6个月前";
        } else if (days > 210 && days <= 240) {
            ftime = "7个月前";
        } else if (days > 240 && days <= 270) {
            ftime = "8个月前";
        } else if (days > 270 && days <= 300) {
            ftime = "9个月前";
        } else if (days > 300 && days <= 330) {
            ftime = "10个月前";
        } else if (days > 330 && days <= 360) {
            ftime = "11个月前";
        } else if (days > 360 && days <= 720) {
            ftime = "一年前";
        } else if (days > 720 && days <= 1080) {
            ftime = "两年前";
        } else if (days > 1080) {
            ftime = dateFormater2.get().format(time);
        }
        return ftime;
    }


    public static ThreadLocal<SimpleDateFormat> dateFormater2 = new ThreadLocal<SimpleDateFormat>() {
        @Override
        protected SimpleDateFormat initialValue() {
            return new SimpleDateFormat("yyyy-MM-dd");
        }
    };

    /**
     * 将字符串转位日期类型
     * @param sdate
     * @return
     */
    public static Date toDate(String sdate,String format) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(format);
            return sdf.parse(sdate);
        } catch (ParseException e) {
            return null;
        }
    }

    /**
     * 把long 转换成 日期 再转换成String类型
     */
    public static String toStr( Long millSec,String format) {
        String dateFormat = format;
        SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
        Date date = new Date(millSec);
        return sdf.format(date);
    }


    /**
     * 获取当前日期是星期几
     * @param date
     * @return
     */
    public static String getWeekOfDate(Date date) {
        String[] weekDays = { "周日", "周一", "周二", "周三", "周四", "周五", "周六" };
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
        if (w < 0)
            w = 0;
        return weekDays[w];
    }

}
