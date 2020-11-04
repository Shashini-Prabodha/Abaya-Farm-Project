package lk.abayafarm.pos.bo.custom;

import lk.abayafarm.pos.dto.FeedDTO;
import lk.abayafarm.pos.dto.MedicineDTO;
import lk.abayafarm.pos.dto.SupplierOrderDTO;

public interface SupplierItemOrderTransactionBo {


    public boolean addBatch(SupplierOrderDTO supplierOrderDTO, FeedDTO feedDTO) throws Exception;
    public boolean addBatch(SupplierOrderDTO supplierOrderDTO, MedicineDTO medicineDTO) throws Exception;
 //   public boolean addBatch(SupplierOrderDTO supplierOrderDTO, DaoFactory.DaoType type) throws Exception;
    //public T addBatch(Object<T extends SuperEntity> obj) throws Exception;



}
