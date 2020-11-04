package lk.abayafarm.pos.dao.custom.impl;

import lk.abayafarm.pos.dao.CrudUtil;
import lk.abayafarm.pos.dao.custom.OrderDAO;
import lk.abayafarm.pos.entity.Income;
import lk.abayafarm.pos.entity.Order;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrderDAOImpl implements OrderDAO {
    @Override
    public boolean save(Order order) throws Exception {
        return CrudUtil.execute("INSERT INTO orders VALUES(?,?,?)", order.getCustId(), order.getOrderId(), order.getDate());
    }

    @Override
    public boolean update(Order order) throws Exception {
        return CrudUtil.execute("UPDATE orders SET custId=?, date=? WHERE orderId=?", order.getCustId(), order.getDate(), order.getOrderId());
    }

    @Override
    public boolean delete(String s) throws Exception {
        return CrudUtil.execute("DELETE FROM orders WHERE orderId=?", s);

    }

    @Override
    public Order get(String s) throws Exception {
        ResultSet rst = CrudUtil.execute("SELECT * FROM orders WHERE orderId=?", s);
        if (rst.next()) {
            return new Order(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getDate(3)

            );
        }
        return null;
    }

    @Override
    public List<Order> getAll() throws Exception {
        ResultSet rst = CrudUtil.execute("SELECT * FROM orders");
        ArrayList<Order> list = new ArrayList<>();
        while (rst.next()) {
            list.add(
                    new Order(
                            rst.getString(1),
                            rst.getString(2),
                            rst.getDate(3)
                    )
            );
        }
        return list;
    }

    @Override
    public String getLastOrderID() throws SQLException, ClassNotFoundException {
            ResultSet rst=CrudUtil.execute("SELECT orderId FROM orders ORDER BY orderId DESC LIMIT 1");
            String id=null;
            if(rst.next()){
                id=rst.getString(1);
            }
            return id;
        }

}
