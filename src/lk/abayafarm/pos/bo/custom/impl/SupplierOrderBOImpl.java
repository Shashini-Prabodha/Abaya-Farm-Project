package lk.abayafarm.pos.bo.custom.impl;

import lk.abayafarm.pos.bo.custom.SupplierOrderBO;
import lk.abayafarm.pos.dao.DaoFactory;
import lk.abayafarm.pos.dao.custom.SupplierOrderDAO;
import lk.abayafarm.pos.dto.CageDTO;
import lk.abayafarm.pos.dto.SupplierOrderDTO;
import lk.abayafarm.pos.entity.Cage;
import lk.abayafarm.pos.entity.SupplierOrder;

import java.util.ArrayList;
import java.util.List;

public class SupplierOrderBOImpl implements SupplierOrderBO {

    SupplierOrderDAO supplierOrderDAO = DaoFactory.getInstance().getDao(DaoFactory.DaoType.SUPPLIERORDER);

    @Override
    public boolean saveSupplierOrder(SupplierOrderDTO dto) throws Exception {
        return supplierOrderDAO.save(new SupplierOrder(dto.getSupOrderId(), dto.getSupplierId(), dto.getbatchID(), dto.getDescription(), dto.getQtyOnHand(), dto.getUnitPrice(), dto.getDate()));
    }

    @Override
    public boolean updateSupplierOrder(SupplierOrderDTO dto) throws Exception {
        return supplierOrderDAO.update(new SupplierOrder(dto.getSupOrderId(), dto.getSupplierId(), dto.getbatchID(), dto.getDescription(), dto.getQtyOnHand(), dto.getUnitPrice(), dto.getDate()));
    }

    @Override
    public boolean deleteSupplierOrder(String id) throws Exception {
        return supplierOrderDAO.delete(id);
    }

    @Override
    public SupplierOrderDTO getSupplierOrder(String id) throws Exception {
        SupplierOrder supplierOrder = supplierOrderDAO.get(id);
        return new SupplierOrderDTO(supplierOrder.getSupOrderId(), supplierOrder.getSupplierId(), supplierOrder.getbatchID(), supplierOrder.getDescription(), supplierOrder.getQtyOnHand(), supplierOrder.getUnitPrice(), supplierOrder.getDate());
    }

    @Override
    public List<SupplierOrderDTO> getAllSupplierOrder() throws Exception {
        List<SupplierOrder> list = supplierOrderDAO.getAll();
        ArrayList<SupplierOrderDTO> arrayList = new ArrayList<>();
        for (SupplierOrder supplierOrder : list) {
            arrayList.add(new SupplierOrderDTO(supplierOrder.getSupOrderId(), supplierOrder.getSupplierId(), supplierOrder.getbatchID(), supplierOrder.getDescription(), supplierOrder.getQtyOnHand(), supplierOrder.getUnitPrice(), supplierOrder.getDate()));
        }
        return arrayList;


    }

    @Override
    public String getLastOrderID() throws Exception {
        return supplierOrderDAO.getLastOrderId();
    }
}
