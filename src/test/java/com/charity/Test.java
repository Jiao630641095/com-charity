package com.charity;

import sun.applet.Main;

import java.util.Random;

/**
 * @Author：王思峰
 * @Description:
 * @Date: Created in 10:04 2017/10/11
 * @Modified By:
 */
public class Test {

    @org.junit.Test
    public void contextLoads() throws InterruptedException {
        String str = "B2";
        str += (int)((Math.random()+new Random().nextInt(10))*10000);
        str += (int)((Math.random()+new Random().nextInt(10))*1000);
        str += (int)((Math.random()+new Random().nextInt(10))*100000);
        str += (int)((Math.random()+new Random().nextInt(10))*1000000);
        if (str.length()<24){
            while (24-str.length()>0){
                str += new Random().nextInt(10);
            }
        }
        System.out.println(str.length());
        System.out.println(str);

    }


}
