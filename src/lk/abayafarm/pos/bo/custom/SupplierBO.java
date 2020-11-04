package lk.abayafarm.pos.bo.custom;

import lk.abayafarm.pos.dao.CrudDAO;
import lk.abayafarm.pos.dto.EggDTO;
import lk.abayafarm.pos.dto.SupplierDTO;
import lk.abayafarm.pos.entity.Supplier;

import java.util.ArrayList;
import java.util.List;

public interface SupplierBO{
    public boolean saveSupplier(SupplierDTO dto)throws Exception;
    public boolean updateSupplier(SupplierDTO dto)throws Exception;
    public boolean deleteSupplier(String id) throws Exception;
    public SupplierDTO getSupplier(String id) throws Exception;
    public List<SupplierDTO> getAllSupplier() throws Exception;
    public SupplierDTO getSupplierForName(String name) throws Exception;
    public List<SupplierDTO> getAllSupplierWithType(String type) throws Exception;
    public String getLastSupplierID() throws Exception;



}
