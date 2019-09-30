package com.qfedu.utils;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

public class SqlSessionFactoryUtils {

    static SqlSessionFactory sqlSessionFactory = null;

    static {

        SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
        try {
            InputStream inputStream = Resources.getResourceAsStream("dbconfig/SqlMapConfig.xml");
            sqlSessionFactory = builder.build(inputStream);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private SqlSessionFactoryUtils() {}

    public static SqlSessionFactory getSqlSessionFactory() {
        return sqlSessionFactory;
    }

}
