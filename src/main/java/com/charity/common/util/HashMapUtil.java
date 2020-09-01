package com.charity.common.util;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author：王思峰
 * @Description:
 * @Date: Created in 10:26 2017/10/20
 * @Modified By:
 */
public class HashMapUtil {
    // 创建参数队列
    public static Map<String, String> getNameValuePairs(Object c){
        HashMap<String, String> map = new HashMap<String, String>();
        Class cls = c.getClass();
        Field[] fields = cls.getDeclaredFields();
        for(int i=0; i<fields.length; i++){
            Field f = fields[i];
            f.setAccessible(true);
            try {
                if((f.get(c))!=null){
                    map.put(f.getName(),(f.get(c)).toString());
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return map;
    }
}
