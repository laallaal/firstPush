package com.qfedu.utils;

import java.util.Properties;

//目的是想获取一个数据源 DataSource
public class InfoUtils {



   static Properties properties = null;
    static{
        try{
            properties=new Properties();
            properties.load(InfoUtils.class.getClassLoader().getResourceAsStream("info.properties"));
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public static String getProperties(String name){
        return properties.getProperty(name);
    }


}
