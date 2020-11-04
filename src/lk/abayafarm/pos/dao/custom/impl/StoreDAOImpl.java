package lk.abayafarm.pos.dao.custom.impl;

import lk.abayafarm.pos.dao.CrudUtil;
import lk.abayafarm.pos.dao.custom.StoreDAO;
import lk.abayafarm.pos.entity.Income;
import lk.abayafarm.pos.entity.OrderDetails;
import lk.abayafarm.pos.entity.Store;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class StoreDAOImpl implements StoreDAO {
    @Override
    public boolean save(Store store) throws Exception {
        return CrudUtil.execute("INSERT INTO store VALUES(?,?,?)",store.getStoreId(),store.getType(),store.getQtyOnStore(),store.getUnitPrice());
    }

    @Override
    public boolean update(Store store) throws Exception {
        return CrudUtil.execute("UPDATE store SET type=?, qtyOnStore=?, unitPrice=? WHERE storeId=?", store.getType(),store.getQtyOnStore(),store.getUnitPrice(),store.getStoreId());
    }


    @Override
    public boolean update(List<Store> storeList) throws Exception {
        if (storeList != null) {
            for (Store store : storeList) {
                if (!update(store)) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    @Override
    public boolean delete(String s) throws Exception {
        return CrudUtil.execute("DELETE FROM store WHERE storeId=?", s);
    }

    @Override
    public Store get(String s) throws Exception {
        ResultSet rst = CrudUtil.execute("SELECT * FROM store WHERE storeId=?", s);
        if (rst.next()) {
            return new Store(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getInt(3),
                    rst.getDouble(4)
            );
        }
        return null;
    }

    @Override
    public List<Store> getAll() throws Exception {
        ResultSet rst = CrudUtil.execute("SELECT * FROM store");
        ArrayList<Store> list = new ArrayList<>();
        while (rst.next()) {
            list.add(
                    new Store(
                            rst.getString(1),
                            rst.getString(2),
                            rst.getInt(3),
                            rst.getDouble(4)
                    )
            );
        }
        return list;
    }

    @Override
    public boolean updateUnitPrice(String sID,Double unitPrice) throws Exception {
        return CrudUtil.execute("UPDATE store SET unitPrice=? WHERE storeId=?",unitPrice,sID);

    }

    @Override
    public boolean addItem(String sId, int qty) throws Exception {
        return CrudUtil.execute("UPDATE store SET qtyOnStore=? WHERE storeId=?",qty,sId);
    }
}
