package lk.abayafarm.pos.dao.custom;

import lk.abayafarm.pos.dao.CrudDAO;
import lk.abayafarm.pos.entity.FeedDetails;
import lk.abayafarm.pos.entity.Order;

import java.sql.SQLException;

public interface OrderDAO extends CrudDAO<Order,String> {
    String getLastOrderID() throws SQLException, ClassNotFoundException;
}
