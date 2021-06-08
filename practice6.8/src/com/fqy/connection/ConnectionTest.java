package com.fqy.connection;

import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * 获取连接的方式
 * 学习优化的过程
 * @author fan_jennifer
 * @create 2021-06-2021/6/8 20:10
 */
public class ConnectionTest {

    /**
     * 方式一
     */
    @Test
    public void test1() throws SQLException {
        Driver driver = new com.mysql.jdbc.Driver();//有第三方的API

        String url = "jdbc:mysql://localhost:3306/test?useUnicode=true&characterEncoding=utf8";
        Properties info = new Properties();
        info.setProperty("user","root");
        info.setProperty("password","123456");



        Connection conn = driver.connect(url,info);

        System.out.println("conn = " + conn);
    }

    /**
     * 方式二
     *     不出现第三方的API，是的程序具有更好的可移植性
     */

    @Test
    public void test2() throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException {
        //获取Driver实现类的对象，用反射
        Class clazz = Class.forName("com.mysql.jdbc.Driver");
        Driver driver = (Driver) clazz.newInstance();

        //提供要连接的数据库
        String url = "jdbc:mysql://localhost:3306/test?useUnicode=true&characterEncoding=utf8";

        //提供要连接的用户名和密码
        Properties info = new Properties();
        info.setProperty("user","root");
        info.setProperty("password","123456");

        //获取连接
        Connection conn = driver.connect(url,info);
        System.out.println("conn = " + conn);

    }

    /**
     * 方式三
     *   使用DriverManager替代Driver，获取连接多用manager
     */
    @Test
    public void test3() throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        //获取driver实现类的对象
        Class clazz = Class.forName("com.mysql.jdbc.Driver");
        Driver driver = (Driver) clazz.newInstance();

        //提供另外三个连接的基本信息
        String url = "jdbc:mysql://localhost:3306/test?useUnicode=true&characterEncoding=utf8";
        String user = "root";
        String password = "123456";
        //注册驱动:多了一部
        DriverManager.registerDriver(driver);

        //获取连接
        Connection conn = DriverManager.getConnection(url, user, password);
        System.out.println("conn = " + conn);

    }

    /**
     * 方式四
     *   对三再优化，不用再显示的注册驱动了
     */
    @Test
    public void test4() throws ClassNotFoundException, SQLException {
        //提供另外三个连接的基本信息
        String url = "jdbc:mysql://localhost:3306/test?useUnicode=true&characterEncoding=utf8";
        String user = "root";
        String password = "123456";

        //获取driver实现类的对象
        Class clazz = Class.forName("com.mysql.jdbc.Driver");//虽然这步已经做了，但是不能省因为只有mysql做了
        /*Driver driver = (Driver) clazz.newInstance();
        //注册驱动:多了一部
        DriverManager.registerDriver(driver);*///driver类里有static代码块
                                              // 随着类加载而加载

        //获取连接
        Connection conn = DriverManager.getConnection(url, user, password);
        System.out.println("conn = " + conn);

    }

    /**
     *
     * 方式五，最终版，将信息放在配置文件当中
     * 好处？
     * 实现了数据与代码的分离，实现了解耦
     * 如果需要修改配置文件信息，可以避免程序重新打包
     */
    @Test
    public void test5() throws IOException, SQLException, ClassNotFoundException {
        //读取配置文件的基本信息
        Properties pro = new Properties();
        InputStream is = ConnectionTest.class.getClassLoader().getResourceAsStream("jdbc.properties");
        pro.load(is);

        String user = pro.getProperty("user");
        String password = pro.getProperty("password");
        String url = pro.getProperty("url");
        String driver = pro.getProperty("driver");

        //加载驱动
        Class.forName(driver);

        //获取连接
        Connection conn = DriverManager.getConnection(url, user, password);
        System.out.println("conn = " + conn);


    }



}
