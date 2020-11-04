package lk.abayafarm.pos.dao.custom.impl;

import lk.abayafarm.pos.dao.CrudUtil;
import lk.abayafarm.pos.dao.custom.CustomerDAO;
import lk.abayafarm.pos.dto.CustomerDTO;
import lk.abayafarm.pos.entity.Customer;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class CustomerDAOImpl implements CustomerDAO {
    @Override
    public boolean save(Customer customer) throws Exception {
        return CrudUtil.execute("INSERT INTO customer VALUES(?,?,?)", customer.getCustId(), customer.getCustName(), customer.getCustTp());
    }

    @Override
    public boolean update(Customer customer) throws Exception {
        return CrudUtil.execute("UPDATE customer SET custName=?, custTp=? WHERE custId=?", customer.getCustName(), customer.getCustTp(), customer.getCustId());
    }

    @Override
    public boolean delete(String s) throws Exception {
        return CrudUtil.execute("DELETE FROM customer WHERE custId=?", s);
    }

    @Override
    public Customer get(String s) throws Exception {
        ResultSet rst = CrudUtil.execute("SELECT * FROM customer WHERE custId=?", s);
        if (rst.next()) {
            return new Customer(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getInt(3)
            );
        }
        return null;
    }

    @Override
    public List<Customer> getAll() throws Exception {
        ResultSet rst = CrudUtil.execute("SELECT * FROM customer");
        ArrayList<Customer> list = new ArrayList();
        while (rst.next()) {
            list.add(
                    new Customer(
                            rst.getString(1),
                            rst.getString(2),
                            rst.getInt(3)
                    )
            );
        }
        return list;
    }

    @Override
    public String getCustomerLasiID() throws Exception{
        ResultSet rst=CrudUtil.execute("SELECT custId FROM customer ORDER BY custId DESC LIMIT 1");
        String id=null;
        if(rst.next()){
            id=rst.getString(1);
        }
        return id;
    }

    @Override
    public Customer getCustomerInTP(int tp) throws Exception {
        ResultSet rst = CrudUtil.execute("SELECT * FROM customer WHERE custTp=?",tp);
        if (rst.next()) {
            return new Customer(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getInt(3)
            );
        }
        return null;
    }
}
