package lk.abayafarm.pos.bo.custom;

import lk.abayafarm.pos.dto.SupplierOrderDTO;


import java.util.ArrayList;
import java.util.List;

public interface SupplierOrderBO{
    public boolean saveSupplierOrder(SupplierOrderDTO dto)throws Exception;
    public boolean updateSupplierOrder(SupplierOrderDTO dto)throws Exception;
    public boolean deleteSupplierOrder(String id) throws Exception;
    public SupplierOrderDTO getSupplierOrder(String id) throws Exception;
    public List<SupplierOrderDTO> getAllSupplierOrder() throws Exception;
    public String getLastOrderID() throws Exception;
}
