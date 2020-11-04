package lk.abayafarm.pos.bo.custom.impl;

import lk.abayafarm.pos.bo.custom.StoreBO;
import lk.abayafarm.pos.dao.custom.StoreDAO;
import lk.abayafarm.pos.dao.custom.impl.StoreDAOImpl;
import lk.abayafarm.pos.dto.StoreDTO;
import lk.abayafarm.pos.entity.Store;

import java.util.ArrayList;
import java.util.List;

public class StoreBOImpl implements StoreBO {
    StoreDAO storeDAO = new StoreDAOImpl();

    @Override
    public boolean saveStore(StoreDTO dto) throws Exception {
        return storeDAO.save(new Store(dto.getStoreId(), dto.getType(), dto.getQtyOnStore(), dto.getUnitPrice()));
    }

    @Override
    public boolean updateStore(StoreDTO dto) throws Exception {
        return storeDAO.update(new Store(dto.getStoreId(), dto.getType(), dto.getQtyOnStore(), dto.getUnitPrice()));
    }

    @Override
    public boolean deleteStore(String id) throws Exception {
        return storeDAO.delete(id);
    }

    @Override
    public StoreDTO getStore(String id) throws Exception {
        Store store = storeDAO.get(id);
        return new StoreDTO(store.getStoreId(), store.getType(), store.getQtyOnStore(), store.getUnitPrice());
    }

    @Override
    public ArrayList<StoreDTO> getAllStore() throws Exception {
        List<Store> list = storeDAO.getAll();
        ArrayList<StoreDTO> arrayList = new ArrayList<>();
        for (Store store : list) {
            arrayList.add(
                    new StoreDTO(
                            store.getStoreId(),
                            store.getType(),
                            store.getQtyOnStore(),
                            store.getUnitPrice()
                    )
            );
        }
        return arrayList;
    }

    @Override
    public boolean updateUnitPrice(String sid, Double unitPrice) throws Exception {
        return storeDAO.updateUnitPrice(sid,unitPrice);
    }

    @Override
    public boolean addEggs(String sid, int qty) throws Exception {
        return storeDAO.addItem(sid,qty);
    }
}
