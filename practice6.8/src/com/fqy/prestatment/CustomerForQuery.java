package com.fqy.prestatment;

import com.fqy.util.JDBCUtils;
import org.junit.Test;

import java.io.IOException;
import java.lang.reflect.Field;
import java.sql.*;

/**
 * @author fan_jennifer
 * @create 2021-06-2021/6/9 11:20
 */
public class CustomerForQuery {

    @Test
    public void commonQueryForCustomersTest() {
        String sql = "select id,name,birth,email from customers where id = ?";
        Customer customer = queryForCustomers(sql, 13);
        System.out.println("customer = " + customer);
        String sql1 = "select name,email from customers where `name` =?";
        Customer customer1 = queryForCustomers(sql1, "周杰伦");
        System.out.println("customer1 = " + customer1);

    }
    /**
     * result的next的使用
     * 获取result的元数据，获得列数
     * 通过反射输入对象内
     */
    public  Customer queryForCustomers(String sql,Object...args){
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = JDBCUtils.getConnection();

            ps = conn.prepareStatement(sql);

            for (int i = 0; i < args.length; i++) {
                ps.setObject(i+1,args[i]);
            }
            rs = ps.executeQuery();
            //获取结果集的元数据：重点
            ResultSetMetaData rsmd = rs.getMetaData();
            //调取元数据的列数
            int columnCount = rsmd.getColumnCount();
            if (rs.next()){
                Customer customer = new Customer();
                for (int i = 0; i < columnCount; i++) {
                    Object columnValue = rs.getObject(i + 1);
                    //获取列名，通过元数据
                    String columnName = rsmd.getColumnName(i + 1);


                    //给对象指定的属性赋值为value,通过反射,重点
                    Field field = Customer.class.getDeclaredField(columnName);
                    field.setAccessible(true);
                    field.set(customer,columnValue);

                }
                return customer;

            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.closeResource(conn,ps,rs);
        }

            return null;
        }

    @Test
    public void query1Test() {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet resultSet = null;
        try {
            //获取连接
            conn = JDBCUtils.getConnection();
            //预编译
            String sql = "select id,name,email,birth from customers where id = ?";
            ps = conn.prepareStatement(sql);
            //填充占位符
            ps.setObject(1, 1);
            //执行获取结果集
            resultSet = ps.executeQuery();

            //结果集输入到对象里
            if (resultSet.next()) {
                int id = resultSet.getInt(1);
                String name = resultSet.getString(2);
                String email = resultSet.getString(3);
                Date birth = resultSet.getDate(4);

                Customer customer = new Customer(id, name, email, birth);
                System.out.println("customer = " + customer);


            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //关闭资源
            JDBCUtils.closeResource(conn, ps, resultSet);
        }


    }


}
