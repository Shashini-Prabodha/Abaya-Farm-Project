package lk.abayafarm.pos.bo.custom;

import lk.abayafarm.pos.dao.CrudDAO;
import lk.abayafarm.pos.dto.EggDTO;
import lk.abayafarm.pos.dto.StoreDTO;
import lk.abayafarm.pos.entity.Store;

import java.util.ArrayList;

public interface StoreBO{
    public boolean saveStore(StoreDTO dto)throws Exception;
    public boolean updateStore(StoreDTO dto)throws Exception;
    public boolean deleteStore(String id) throws Exception;
    public StoreDTO getStore(String id) throws Exception;
    public ArrayList<StoreDTO> getAllStore() throws Exception;
    public boolean updateUnitPrice(String sid,Double unitPrice) throws Exception;
    public boolean addEggs(String sid,int qty) throws Exception;

}
