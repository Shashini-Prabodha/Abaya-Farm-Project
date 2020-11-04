package lk.abayafarm.pos.dao.custom;

import lk.abayafarm.pos.dao.CrudDAO;
import lk.abayafarm.pos.dto.SupplierDTO;
import lk.abayafarm.pos.entity.FeedDetails;
import lk.abayafarm.pos.entity.Supplier;

import java.util.List;

public interface SupplierDAO extends CrudDAO<Supplier,String> {
    public List<Supplier> getAllSupplierWithType(String type) throws Exception;
    public Supplier getSupplierForName(String name) throws Exception;
    public String getLastSupplierID() throws Exception;
}
