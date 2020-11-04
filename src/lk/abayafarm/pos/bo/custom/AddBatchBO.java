package lk.abayafarm.pos.bo.custom;

import lk.abayafarm.pos.dto.BatchDTO;
import lk.abayafarm.pos.dto.CageDTO;
import lk.abayafarm.pos.dto.SupplierOrderDTO;
import lk.abayafarm.pos.entity.Cage;

public interface AddBatchBO {
    public boolean addBatch( BatchDTO batchDTO,SupplierOrderDTO supplierOrderDTO, CageDTO cage) throws Exception;
}
