package lk.abayafarm.pos.dao.custom;

import lk.abayafarm.pos.dao.CrudDAO;
import lk.abayafarm.pos.entity.FeedDetails;
import lk.abayafarm.pos.entity.Store;

import java.util.List;

public interface StoreDAO extends CrudDAO<Store,String> {
    boolean updateUnitPrice(String sId,Double unitPrice)throws Exception;
    public boolean update(List<Store> storeList) throws Exception;
    boolean addItem(String sId,int qty)throws Exception;
}
