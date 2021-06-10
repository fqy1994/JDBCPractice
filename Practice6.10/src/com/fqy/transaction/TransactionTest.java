package com.fqy.transaction;

import com.fqy.util1.JDBCUtils;
import org.junit.Test;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * @author fan_jennifer
 * @create 2021-06-2021/6/10 10:44
 */
public class TransactionTest {

    /**
     *
     */
    @Test
    public void transactionSelectTest() {


    }


    /**
     *
     */
    @Test
    public void transactionTest() {



    }
    /**
     * 针对于数据表user-table来说
     * AA用户给BB用户转账100
     *不关闭连接的方法
     * update user-table set balance = balance- 100 where user=`AA`
     * update user-table set balance = balance+100 where user=`BB`
     */
    @Test
    public void updateTest() throws SQLException, IOException, ClassNotFoundException {
        Connection conn = null;
        try {
            conn = JDBCUtils.getConnection();
            conn.setAutoCommit(false);
            String sql1 = "update user_table set balance = balance - 100 where user = ?";
            JDBCUtils.update(conn,sql1,"AA");

            String sql2 = "update user_table set balance = balance +100 where user = ?";
            JDBCUtils.update(conn,sql2,"BB");

            System.out.println("转账成功");

            conn.commit();
        } catch (Exception e) {
            e.printStackTrace();
            try {
                conn.rollback();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        } finally {
            JDBCUtils.closeResource(conn,null);
        }




    }


}
