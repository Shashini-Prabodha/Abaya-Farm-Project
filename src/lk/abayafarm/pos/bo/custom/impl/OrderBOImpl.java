package lk.abayafarm.pos.bo.custom.impl;

import lk.abayafarm.pos.bo.custom.OrderBO;
import lk.abayafarm.pos.dao.DaoFactory;
import lk.abayafarm.pos.dao.custom.OrderDAO;
import lk.abayafarm.pos.dto.OrderDTO;
import lk.abayafarm.pos.dto.OrderDetailsDTO;
import lk.abayafarm.pos.entity.Order;
import lk.abayafarm.pos.entity.OrderDetails;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrderBOImpl implements OrderBO {

    OrderDAO orderDAO= DaoFactory.getInstance().getDao(DaoFactory.DaoType.ORDER);
    @Override
    public boolean saveOrder(OrderDTO dto) throws Exception {
        return orderDAO.save(new Order(dto.getCustId(),dto.getOrderId(),dto.getDate()));
    }

    @Override
    public boolean updateOrder(OrderDTO dto) throws Exception {
        return orderDAO.update(new Order(dto.getCustId(),dto.getOrderId(),dto.getDate()));
    }

    @Override
    public boolean deleteOrder(String id) throws Exception {
        return orderDAO.delete(id);
    }

    @Override
    public OrderDTO getOrder(String id) throws Exception {
        Order order = orderDAO.get(id);
        return new OrderDTO(order.getCustId(),order.getOrderId(),order.getDate());
    }

    @Override
    public ArrayList<OrderDTO> getAllOrder() throws Exception {
        List<Order> list = orderDAO.getAll();
        ArrayList<OrderDTO> arrayList = new ArrayList<>();
        for (Order order : list) {
            arrayList.add(new OrderDTO(order.getCustId(),order.getOrderId(),order.getDate()));
        }
        return arrayList;
    }

    @Override
    public String getLastOrderID() throws SQLException, ClassNotFoundException {
        return orderDAO.getLastOrderID();
    }
}
