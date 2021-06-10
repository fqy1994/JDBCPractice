package com.fqy.dao;

import com.fqy.bean.Customer;

import java.sql.Connection;
import java.sql.Date;
import java.util.List;

/**
 * 用于规范针对于customers表的常用操作
 * @author fan_jennifer
 * @create 2021-06-2021/6/10 15:37
 */
public interface CustomerDAO {
    /**
     * 将cust对象添加到数据库中
     *
     */
    void insert(Connection conn, Customer cust);
/**
 * 针对指定的id，删除表中的一条记录
 */
    void deleteById(Connection conn,int id);

    /**
     * 针对内存中的cust对象修改数据表中指定的记录
     * @param conn
     * @param cust
     */
    void updateById(Connection conn,Customer cust);

    /**
     * 根据指定的id查询对应的cust得到对应的customer对象
     * @param conn
     * @param id
     */
    void getCustomerById(Connection conn,int id);

    /**
     * 查询表中所有记录构成的集合
     * @param conn
     * @return
     */
    List<Customer> getAll(Connection conn);

    /**
     * 返回数据有多少条记录
     * @param conn
     * @return
     */
    Long getCount(Connection conn);

    /**
     * 返回数据表中最大的生日
     * @param conn
     * @return
     */
    Date getMaxBirth(Connection conn);

}
