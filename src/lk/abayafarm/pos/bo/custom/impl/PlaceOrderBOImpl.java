package lk.abayafarm.pos.bo.custom.impl;

import lk.abayafarm.pos.bo.custom.PlaceOrderBO;
import lk.abayafarm.pos.dao.DaoFactory;
import lk.abayafarm.pos.dao.custom.IncomeDAO;
import lk.abayafarm.pos.dao.custom.OrderDAO;
import lk.abayafarm.pos.dao.custom.OrderDetailsDAO;
import lk.abayafarm.pos.dao.custom.StoreDAO;
import lk.abayafarm.pos.db.DBConnection;
import lk.abayafarm.pos.dto.IncomeDTO;
import lk.abayafarm.pos.dto.OrderDTO;
import lk.abayafarm.pos.dto.OrderDetailsDTO;
import lk.abayafarm.pos.dto.StoreDTO;
import lk.abayafarm.pos.entity.Income;
import lk.abayafarm.pos.entity.Order;
import lk.abayafarm.pos.entity.OrderDetails;
import lk.abayafarm.pos.entity.Store;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

public class PlaceOrderBOImpl implements PlaceOrderBO {

    OrderDAO orderDAO = DaoFactory.getInstance().getDao(DaoFactory.DaoType.ORDER);
    OrderDetailsDAO orderDetailsDAO = DaoFactory.getInstance().getDao(DaoFactory.DaoType.ORDERDETAILS);
    StoreDAO storeDAO = DaoFactory.getInstance().getDao(DaoFactory.DaoType.STORE);
    IncomeDAO incomeDAO = DaoFactory.getInstance().getDao(DaoFactory.DaoType.INCOME);

    @Override
    public boolean placeOrder(OrderDTO orderDTO, List<OrderDetailsDTO> orderDetailsDTOList, List<StoreDTO> storeDTOList, IncomeDTO incomeDTO) throws Exception {
        Connection connection = DBConnection.getInstance().getConnection();

        Order order = new Order(orderDTO.getOrderId(), orderDTO.getCustId(), orderDTO.getDate());
        List<OrderDetails> orderDetailsList = new ArrayList<>();
        for (OrderDetailsDTO orderDetailsDTO : orderDetailsDTOList) {
            orderDetailsList.add(new OrderDetails(orderDetailsDTO.getOrderId(), orderDetailsDTO.getStoreId(), orderDetailsDTO.getOrderQty()));
        }
        List<Store> storeList = new ArrayList<>();
        for (StoreDTO storeDTO : storeDTOList) {
            storeList.add(new Store(storeDTO.getStoreId(), storeDTO.getType(), storeDTO.getQtyOnStore(), storeDTO.getUnitPrice()));
        }
        Income income=new Income(incomeDTO.getOrderId(),incomeDTO.getDate(),incomeDTO.getTotal());
        try {
            connection.setAutoCommit(false);
            if (orderDAO.save(order)) {
                if (orderDetailsDAO.save(orderDetailsList)) {
                    if (storeDAO.update(storeList)) {
                        if(incomeDAO.save(income)){
                        connection.commit();
                        return true;
                        } else {
                            connection.rollback();
                            return false;
                        }
                    } else {
                        connection.rollback();
                        return false;
                    }
                } else {
                    connection.rollback();
                    return false;
                }
            } else {
                connection.rollback();
                return false;
            }

        } finally {
            connection.setAutoCommit(true);
        }
    }


}
