package lk.abayafarm.pos.dao.custom.impl;

import lk.abayafarm.pos.dao.CrudUtil;
import lk.abayafarm.pos.dao.custom.OrderDAO;
import lk.abayafarm.pos.dao.custom.OrderDetailsDAO;
import lk.abayafarm.pos.entity.Income;
import lk.abayafarm.pos.entity.OrderDetails;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class OrderDetailsDAOImpl implements OrderDetailsDAO {
    @Override
    public boolean save(List<OrderDetails> orderDetailsList) throws Exception {
        if (orderDetailsList != null) {
            for (OrderDetails orderDetails : orderDetailsList) {
                if (!save(orderDetails)) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    @Override
    public boolean save(OrderDetails orderDetails) throws Exception {
        return CrudUtil.execute("INSERT INTO orderDetails VALUES(?,?,?)", orderDetails.getOrderId(), orderDetails.getStoreId(), orderDetails.getOrderQty());
    }

    @Override
    public boolean update(OrderDetails orderDetails) throws Exception {
        return CrudUtil.execute("UPDATE orderDetails SET orderQty=? WHERE orderId=? and storeId=?", orderDetails.getOrderQty(), orderDetails.getOrderId(), orderDetails.getStoreId());
    }

    @Override
    public boolean delete(String s) throws Exception {
        return CrudUtil.execute("DELETE FROM orderDetails WHERE orderId=? ", s);
    }

    @Override
    public OrderDetails get(String s) throws Exception {
        ResultSet rst = CrudUtil.execute("SELECT * FROM orderDetails WHERE orderId=? ", s);
        if (rst.next()) {
            return new OrderDetails(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getInt(3)
            );
        }
        return null;
    }

    @Override
    public List<OrderDetails> getAll() throws Exception {
        ResultSet rst = CrudUtil.execute("SELECT * FROM orderDetails");
        ArrayList<OrderDetails> list = new ArrayList<>();
        while (rst.next()) {
            list.add(
                    new OrderDetails(
                            rst.getString(1),
                            rst.getString(2),
                            rst.getInt(3)
                    )
            );
        }
        return list;
    }


}
