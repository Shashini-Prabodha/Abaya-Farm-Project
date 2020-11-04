package lk.abayafarm.pos.bo.custom;

import lk.abayafarm.pos.dao.CrudDAO;
import lk.abayafarm.pos.dto.EggDTO;
import lk.abayafarm.pos.dto.OrderDTO;
import lk.abayafarm.pos.entity.Order;

import java.sql.SQLException;
import java.util.ArrayList;

public interface OrderBO{
    public boolean saveOrder(OrderDTO dto)throws Exception;
    public boolean updateOrder(OrderDTO dto)throws Exception;
    public boolean deleteOrder(String id) throws Exception;
    public OrderDTO getOrder(String id) throws Exception;
    public ArrayList<OrderDTO> getAllOrder() throws Exception;

    String getLastOrderID() throws SQLException, ClassNotFoundException;
}
