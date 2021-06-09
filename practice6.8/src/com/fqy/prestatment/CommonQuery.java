package com.fqy.prestatment;

import com.fqy.util.JDBCUtils;
import org.junit.Test;

import java.io.IOException;
import java.lang.reflect.Field;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author fan_jennifer
 * @create 2021-06-2021/6/9 12:39
 * 通用查找多个，
 */
public class CommonQuery {

    @Test
    public void testGetForList() {
        String sql = "select id,name,email from customers";
        List<Customer> forlist = getForlist(Customer.class, sql);//为什么不用另一种class实例生成forname
        forlist.forEach(System.out::println);

        String sql1 = "select order_id orderId,order_name orderName from `order`";
        List<Order> forlist1 = getForlist(Order.class, sql1);
        forlist1.forEach(System.out::println);



    }


    public<T> List<T> getForlist(Class<T> clazz, String sql, Object...args){
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

            ResultSetMetaData rsmd = rs.getMetaData();

            int columnCount = rsmd.getColumnCount();

            ArrayList<T> list = new ArrayList<>();

            while(rs.next()){
                T t = clazz.newInstance();

                for (int i = 0; i < columnCount; i++) {
                    Object columValue = rs.getObject(i + 1);

                    String columnLabel = rsmd.getColumnLabel(i + 1);

                    Field field = clazz.getDeclaredField(columnLabel);
                    field.setAccessible(true);
                    field.set(t,columValue);
                }
               list.add(t);
            }
            return list;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.closeResource(conn,ps,rs);
        }

        return null;




    }
}
