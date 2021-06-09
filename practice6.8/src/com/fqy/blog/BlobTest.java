package com.fqy.blog;

import com.fqy.prestatment.Customer;
import com.fqy.util.JDBCUtils;
import org.junit.Test;

import java.io.*;
import java.sql.*;

/**
 * @author fan_jennifer
 * @create 2021-06-2021/6/9 14:35
 */
public class BlobTest {
    //查询数据表customers中blob类型的字段
    @Test
    public void queryTest() {
        Connection conn = null;
        PreparedStatement ps = null;
        InputStream is= null;
        FileOutputStream fos=null;
        ResultSet rs = null;
        try {
            conn = JDBCUtils.getConnection();
            String sql = "select id,name,email,birth,photo from customers where id =?";
            ps = conn.prepareStatement(sql);
            ps.setObject(1,21);

            rs = ps.executeQuery();
            if (rs.next()){
                int id = rs.getInt(1);
                String name = rs.getString(2);
                String email = rs.getString(3);
                Date birth = rs.getDate(4);

                Customer customer = new Customer(id, name, email, birth);
                System.out.println("customer = " + customer);

                //将blob类型的字段下载下来，保存到本地
                Blob photo = rs.getBlob("photo");
                is = photo.getBinaryStream();
                fos = new FileOutputStream("hello.jpg");
                byte[] buffer = new byte[1024];
                int len;
                while((len = is.read(buffer))!= -1){
                    fos.write(buffer,0,len);
                }


            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            JDBCUtils.closeResource(conn,ps,rs,is,fos);
        }



    }
    //向数据表customers中插入Blob类型的字段
    @Test
    public void insertTest() {
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = JDBCUtils.getConnection();
            String sql = "insert into customers(name,email,birth,photo)values(?,?,?,?)";

            ps = conn.prepareStatement(sql);
            ps.setObject(1,"tom");
            ps.setObject(2,"ser");
            ps.setObject(3,"1992-09-02");
            FileInputStream is = new FileInputStream(new File("src=http_%2F%2Fi0.hdslb.com%2Fbfs%2Farticle%2F7db50b79b091782c4e86986be03b526ce060ca2f.jpg&refer=http_%2F%2Fi0.hdslb.jpg"));
            ps.setBlob(4,is);

            ps.execute();
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
}
