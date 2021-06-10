package com.fqy.blog;

import com.fqy.util.JDBCUtils;
import org.junit.Test;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * 向创建的goods表中插入2万条数据
 * @author fan_jennifer
 * @create 2021-06-2021/6/10 9:10
 */

public class BatchInsert {

    /**
     * 方式二：使用preparedStatement插入
     */
    @Test
    public void insert2Test() {
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            long start = System.currentTimeMillis();
            System.out.println("开始：" + start);
            conn = JDBCUtils.getConnection();
            conn.setAutoCommit(false);
            String sql = "insert into goods(name)values(?)";
            ps = conn.prepareStatement(sql);
            for (int i = 1; i <= 20000; i++) {

                ps.setObject(1,"name_"+i);

                ps.addBatch();
                if (0 == i%500){
                    ps.executeBatch();
                    ps.clearBatch();
                }else if(20000 == i){
                    ps.executeBatch();
                    ps.clearBatch();
                }
            }
            conn.commit();
            long end = System.currentTimeMillis();
            System.out.println("花费的时间为: " +(end-start));
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            JDBCUtils.closeResource(conn,ps);
        }

    }

    /**
     * 方式1：使用statement插入
     */
    @Test
    public void insert1Test() {
        Connection conn = null;
        Statement st = null;
        try {
            conn = JDBCUtils.getConnection();
            st = conn.createStatement();
            for (int i = 1; i <= 20000; i++) {
                String sql = "insert into goods(name)values(`name_" + i + "`)";
                st.execute(sql);
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            JDBCUtils.closeResource(conn,st);
        }




    }

}
