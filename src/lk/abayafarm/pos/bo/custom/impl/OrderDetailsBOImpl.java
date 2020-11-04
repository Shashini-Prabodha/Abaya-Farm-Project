package lk.abayafarm.pos.bo.custom.impl;

import lk.abayafarm.pos.bo.custom.OrderBO;
import lk.abayafarm.pos.bo.custom.OrderDetailsBO;
import lk.abayafarm.pos.dao.DaoFactory;
import lk.abayafarm.pos.dao.custom.OrderDetailsDAO;
import lk.abayafarm.pos.dto.ManureDTO;
import lk.abayafarm.pos.dto.OrderDTO;
import lk.abayafarm.pos.dto.OrderDetailsDTO;
import lk.abayafarm.pos.entity.Manure;
import lk.abayafarm.pos.entity.OrderDetails;

import java.util.ArrayList;
import java.util.List;

public class OrderDetailsBOImpl implements OrderDetailsBO {

    OrderDetailsDAO orderDetailsDAO= DaoFactory.getInstance().getDao(DaoFactory.DaoType.ORDERDETAILS);

    @Override
    public boolean saveOrderDetails(OrderDetailsDTO dto) throws Exception {
        return orderDetailsDAO.save(new OrderDetails(dto.getOrderId(),dto.getStoreId(),dto.getOrderQty()));
    }

    @Override
    public boolean updateOrderDetails(OrderDetailsDTO dto) throws Exception {
        return orderDetailsDAO.save(new OrderDetails(dto.getOrderId(),dto.getStoreId(),dto.getOrderQty()));
    }

    @Override
    public boolean deleteOrderDetails(String id) throws Exception {
        return orderDetailsDAO.delete(id);
    }

    @Override
    public OrderDetailsDTO getOrderDetails(String id) throws Exception {
        OrderDetails orderDetails = orderDetailsDAO.get(id);
        return new OrderDetailsDTO(orderDetails.getOrderId(),orderDetails.getStoreId(),orderDetails.getOrderQty());
    }

    @Override
    public ArrayList<OrderDetailsDTO> getAllOrderDetails() throws Exception {
        List<OrderDetails> list = orderDetailsDAO.getAll();
        ArrayList<OrderDetailsDTO> arrayList = new ArrayList<>();
        for (OrderDetails details : list) {
            arrayList.add(new OrderDetailsDTO(details.getOrderId(),details.getStoreId(),details.getOrderQty()));
        }
        return arrayList;
    }
}
