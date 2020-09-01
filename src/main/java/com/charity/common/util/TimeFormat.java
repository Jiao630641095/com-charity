package com.charity.common.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by ZhangXu on 2017/9/29.
 */
public class TimeFormat {
    private static SimpleDateFormat sp = new SimpleDateFormat();
    private static long lasttime;
    private static long count;
    public static String now(String pattern){
        Date date = new Date();
        synchronized(TimeFormat.class){
            sp.applyPattern(pattern);
            return sp.format(date);
        }
    }
    public static String format(String pattern,Date date){
        sp.applyPattern(pattern);
        return sp.format(date);
    }
    public static String date(){
        return now("yyyy-MM-dd");
    }
    public static String time(){
        return now("yyyy-MM-dd HH:mm:ss.SSS");
    }
    public static String onlytime(){
        return now("HH:mm:ss.SSS");
    }
    public static synchronized String uniquetime(){
        long l = System.currentTimeMillis();
        if(l!=lasttime){
            count=0;
            return String.format("%d",lasttime=l);
        }else{
            return String.format("%d%d",lasttime=l,count++);
        }
    }
}
