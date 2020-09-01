package com.charity.timer;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @Author：王思峰
 * @Description: 定时任务
 * @Date: Created in 12:31 2017/9/21
 */
@Component
public class Task {
    //@Scheduled(fixedRate = 10000)
    public void reportCurrentTime() throws ParseException {
        Calendar now = Calendar.getInstance();
        int hours = 18-now.get(Calendar.HOUR_OF_DAY);
        int minutes  = 00-now.get(Calendar.MINUTE);
        int seconds  = 0-now.get(Calendar.SECOND);
        if(seconds<0){minutes = minutes-1;seconds = seconds+60;};
        if(minutes<0){hours = hours-1; minutes = minutes+60;}
        if(hours<0){
            System.out.println("下班啦!");
        }else{
            System.out.println("距下班时间还有：" + hours+"小时"+minutes+"分钟"+seconds+"秒");
        }

    }

}
