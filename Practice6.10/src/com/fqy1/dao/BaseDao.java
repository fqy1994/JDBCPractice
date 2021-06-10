package com.fqy1.dao;

import com.fqy.util1.JDBCUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * 封装了针对于数据表的通用操作
 *
 * @author fan_jennifer
 * @create 2021-06-2021/6/10 15:19
 */
public abstract class BaseDao {
    /**
     * 考虑事务 的增删改
     *
     */
    /*public int update(){

    }*/

    /**
     * 考虑事务的查询，返回一个对象
     * @param <T>
     *
     */
  /*  public <T> T getInstance(){

    }*/
    /**
     * 考虑事务的查询，返回多个对象
     */
    /*public getForList(){

    }*/

    /**
     * 查询特殊的方法
     */
    public <E> E getValue(Connection conn, String sql, Object... args) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = conn.prepareStatement(sql);
            for (int i = 0; i < args.length; i++) {
                ps.setObject(i + 1, args[i]);

            }
            rs = ps.executeQuery();
            if (rs.next()) {
                return (E) rs.getObject(1);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            JDBCUtils.closeResource(null, ps, rs);
        }
        return null;

    }


}
