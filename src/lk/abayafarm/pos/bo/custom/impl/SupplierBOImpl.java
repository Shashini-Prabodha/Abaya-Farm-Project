package lk.abayafarm.pos.bo.custom.impl;

import lk.abayafarm.pos.bo.custom.SupplierBO;
import lk.abayafarm.pos.dao.DaoFactory;
import lk.abayafarm.pos.dao.custom.SupplierDAO;
import lk.abayafarm.pos.dto.CageDTO;
import lk.abayafarm.pos.dto.SupplierDTO;
import lk.abayafarm.pos.entity.Cage;
import lk.abayafarm.pos.entity.Supplier;

import java.util.ArrayList;
import java.util.List;

public class SupplierBOImpl implements SupplierBO {

    SupplierDAO supplierDAO = DaoFactory.getInstance().getDao(DaoFactory.DaoType.SUPPLIER);
    @Override
    public boolean saveSupplier(SupplierDTO dto) throws Exception {
        return supplierDAO.save(new Supplier(dto.getSupplierId(),dto.getSupplierName(),dto.getSupplierTp(),dto.getSupplierAddress(),dto.getType()));
    }

    @Override
    public boolean updateSupplier(SupplierDTO dto) throws Exception {
        return supplierDAO.update(new Supplier(dto.getSupplierId(),dto.getSupplierName(),dto.getSupplierTp(),dto.getSupplierAddress(),dto.getType()));
    }

    @Override
    public boolean deleteSupplier(String id) throws Exception {
        return supplierDAO.delete(id);
    }

    @Override
    public SupplierDTO getSupplier(String id) throws Exception {
        Supplier supplier=supplierDAO.get(id);
        return new SupplierDTO(supplier.getSupplierId(),supplier.getSupplierName(),supplier.getSupplierTp(),supplier.getSupplierAddress(),supplier.getType());
    }

    @Override
    public List<SupplierDTO> getAllSupplier() throws Exception {
        List<Supplier> list= supplierDAO.getAll();
        ArrayList<SupplierDTO> dtoList=new ArrayList<>();
        for (Supplier supplier : list) {
            dtoList.add(new SupplierDTO(supplier.getSupplierId(),supplier.getSupplierName(),supplier.getSupplierTp(),supplier.getSupplierAddress(),supplier.getType()));
        }
        return dtoList;
    }

    public SupplierDTO getSupplierForName(String name) throws Exception {
        Supplier supplier=supplierDAO.getSupplierForName(name);
        return new SupplierDTO(supplier.getSupplierId(),supplier.getSupplierName(),supplier.getSupplierTp(),supplier.getSupplierAddress(),supplier.getType());
    }

    @Override
    public List<SupplierDTO> getAllSupplierWithType(String type) throws Exception {
        List<Supplier> list= supplierDAO.getAllSupplierWithType(type);
        ArrayList<SupplierDTO> dtoList=new ArrayList<>();
        for (Supplier supplier : list) {
            dtoList.add(new SupplierDTO(supplier.getSupplierId(),supplier.getSupplierName(),supplier.getSupplierTp(),supplier.getSupplierAddress(),supplier.getType()));
        }
        return dtoList;
    }

    @Override
    public String getLastSupplierID() throws Exception {
        return supplierDAO.getLastSupplierID();
    }
}
