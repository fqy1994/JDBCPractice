package com.fqy.test;

import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * @author fan_jennifer
 * @create 2021-06-2021/6/10 12:28
 */
public class ForTest {
    @Test
    public void test() throws SQLException, ClassNotFoundException, IOException {

        Properties pro = new Properties();
        InputStream is = ClassLoader.getSystemClassLoader().getResourceAsStream("jdbc.properties");
        pro.load(is);

        String user = pro.getProperty("user");
        String password = pro.getProperty("password");
        String url = pro.getProperty("url");
        String driver = pro.getProperty("driver");

        //加载驱动
        Class.forName(driver);

        //获取连接
        Connection conn = DriverManager.getConnection(url, user, password);
        System.out.println(conn);

    }

}
