package lk.abayafarm.pos.bo.custom;

import lk.abayafarm.pos.dto.IncomeDTO;
import lk.abayafarm.pos.dto.OrderDTO;
import lk.abayafarm.pos.dto.OrderDetailsDTO;
import lk.abayafarm.pos.dto.StoreDTO;

import java.util.List;

public interface PlaceOrderBO {
    public boolean placeOrder(OrderDTO orderDTO, List<OrderDetailsDTO> orderDetailsDTO, List<StoreDTO> storeDTO, IncomeDTO incomeDTO ) throws Exception;
}
