package com.fqy.prestatment;

import com.fqy.connection.ConnectionTest;
import com.fqy.util.JDBCUtils;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

/**
 * @author fan_jennifer
 * @create 2021-06-2021/6/8 21:39
 * 插入
 * 占位符的使用
 * 日期的转换
 * 通用化打包
 * for循环事从0开始的，但是ps参数列表数是从1开始的要注意
 */
public class PreStatementUpdateTest {

    /**
     * 通用代码设置
     */
    @Test
    public void commonTest() {
        String sql = " delete from customers where id = ?";
        JDBCUtils.update(sql,3);

        String sql1 = "update `order` set order_name = ? where order_id = ?";
        JDBCUtils.update(sql1,"DD","2");

    }






    /**
     * 修改记录
     */
    @Test
    public void updateTest() throws SQLException, IOException, ClassNotFoundException {
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            //获取数据库的连接
            conn = JDBCUtils.getConnection();

            //预编译sql语句，返回preparestatment的实例,已经携带了要做的事
            String sql = "update customers set name = ? where id = ?";
            ps = conn.prepareStatement(sql);

            //填充占位符
            ps.setObject(1, "王一博");
            ps.setObject(2,18);


            //执行
            ps.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }  finally {
            JDBCUtils.closeResource(conn,ps);
        }

        //资源的关闭



    }

    /**
     * 增加记录
     */
    @Test
    public void insertTest() throws IOException, ClassNotFoundException, SQLException {
        Connection conn = null;
        PreparedStatement ps = null;
        try {
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
            conn = DriverManager.getConnection(url, user, password);
            //System.out.println("conn = " + conn);

            //预编译sql语句，返回preparedstatemnt实例
            String sql = "insert into customers(name,email,birth)values(?,?,?) ";
            ps = conn.prepareStatement(sql);

            //填充占位符:从1开始
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date date = sdf.parse("1982-02-03");
            ps.setString(1, "周杰论");
            ps.setString(2, "123@.com");
            ps.setDate(3, new java.sql.Date(date.getTime()));

            ps.execute();
        } catch (ParseException e) {
            e.printStackTrace();
        } finally {
            try {
                if (ps != null) {
                    ps.close();
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }


    }
}
