package com.fqy.dao;

import com.fqy.bean.Customer;

import java.sql.Connection;
import java.sql.Date;
import java.util.List;

/**
 * @author fan_jennifer
 * @create 2021-06-2021/6/10 15:48
 */
public class CustomerDAOImpl extends BaseDao implements CustomerDAO {

    @Override
    public void insert(Connection conn, Customer cust) {

    }

    @Override
    public void deleteById(Connection conn, int id) {

    }

    @Override
    public void updateById(Connection conn, Customer cust) {

    }

    @Override
    public void getCustomerById(Connection conn, int id) {

    }

    @Override
    public List<Customer> getAll(Connection conn) {
        return null;
    }

    @Override
    public Long getCount(Connection conn) {
        return null;
    }

    @Override
    public Date getMaxBirth(Connection conn) {
        return null;
    }
}
