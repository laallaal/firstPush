package com.qfedu.utils;/*
package com.qfedu.utils;

import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.util.Properties;

//目的是想获取一个数据源 DataSource
public class JDBCUtils {

    private static DataSource dataSource;

    static{
        try{
            Properties properties=new Properties();
            properties.load(JDBCUtils.class.getClassLoader().getResourceAsStream("db.properties"));

            dataSource = DruidDataSourceFactory.createDataSource(properties);
        }catch(ExceptionController e){
            e.printStackTrace();
        }
    }

    //要知道我们用的是druid
    public static DataSource getDataSource() {
        return dataSource;
    }


}
*/
