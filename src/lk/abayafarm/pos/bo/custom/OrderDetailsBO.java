package lk.abayafarm.pos.bo.custom;

import lk.abayafarm.pos.dao.CrudDAO;
import lk.abayafarm.pos.dto.EggDTO;
import lk.abayafarm.pos.dto.OrderDetailsDTO;
import lk.abayafarm.pos.entity.OrderDetails;

import java.util.ArrayList;

public interface OrderDetailsBO{
    public boolean saveOrderDetails(OrderDetailsDTO dto)throws Exception;
    public boolean updateOrderDetails(OrderDetailsDTO dto)throws Exception;
    public boolean deleteOrderDetails(String id) throws Exception;
    public OrderDetailsDTO getOrderDetails(String id) throws Exception;
    public ArrayList<OrderDetailsDTO> getAllOrderDetails() throws Exception;
}
