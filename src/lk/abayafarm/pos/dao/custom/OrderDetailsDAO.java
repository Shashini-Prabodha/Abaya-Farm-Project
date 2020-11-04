package lk.abayafarm.pos.dao.custom;

import lk.abayafarm.pos.dao.CrudDAO;
import lk.abayafarm.pos.entity.OrderDetails;

import java.util.List;

public interface OrderDetailsDAO extends CrudDAO<OrderDetails,String> {
    public boolean save(List<OrderDetails> orderDetailsList) throws Exception;

}
