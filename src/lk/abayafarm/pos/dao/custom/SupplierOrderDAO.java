package lk.abayafarm.pos.dao.custom;

import lk.abayafarm.pos.dao.CrudDAO;
import lk.abayafarm.pos.entity.FeedDetails;
import lk.abayafarm.pos.entity.SupplierOrder;

public interface SupplierOrderDAO extends CrudDAO<SupplierOrder,String> {
    String getLastOrderId()throws Exception;
}
