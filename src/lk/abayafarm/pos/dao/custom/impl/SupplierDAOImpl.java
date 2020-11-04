package lk.abayafarm.pos.dao.custom.impl;

import lk.abayafarm.pos.dao.CrudUtil;
import lk.abayafarm.pos.dao.custom.SupplierDAO;
import lk.abayafarm.pos.dto.SupplierDTO;
import lk.abayafarm.pos.entity.Income;
import lk.abayafarm.pos.entity.Supplier;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class SupplierDAOImpl implements SupplierDAO {
    @Override
    public boolean save(Supplier supplier) throws Exception {
        return CrudUtil.execute("INSERT INTO supplier VALUES(?,?,?,?,?)",supplier.getSupplierId(),supplier.getSupplierName(),supplier.getSupplierTp(),supplier.getSupplierAddress(),supplier.getType());
    }

    @Override
    public boolean update(Supplier supplier) throws Exception {
        return CrudUtil.execute("UPDATE supplier SET supplierName=?, supplierTp=?, supplierAddress=?, type=? WHERE supplierId=?",supplier.getSupplierName(),supplier.getSupplierTp(),supplier.getSupplierAddress(),supplier.getType(),supplier.getSupplierId());
    }

    @Override
    public boolean delete(String s) throws Exception {
        return CrudUtil.execute("DELETE FROM supplier WHERE supplierId=?", s);
    }

    @Override
    public Supplier get(String s) throws Exception {
        ResultSet rst = CrudUtil.execute("SELECT * FROM supplier WHERE supplierId=?", s);
        if (rst.next()) {
            return new Supplier(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getInt(3),
                    rst.getString(4),
                    rst.getString(5)
            );
        }
        return null;
    }

    @Override
    public List<Supplier> getAll() throws Exception {
        ResultSet rst = CrudUtil.execute("SELECT * FROM supplier");
        ArrayList<Supplier> list = new ArrayList<>();
        while (rst.next()) {
            list.add(
                    new Supplier(
                            rst.getString(1),
                            rst.getString(2),
                            rst.getInt(3),
                            rst.getString(4),
                            rst.getString(5)
                    )
            );
        }
        return list;
    }

    @Override
    public List<Supplier> getAllSupplierWithType(String type) throws Exception {
        ResultSet rst = CrudUtil.execute("SELECT * FROM supplier WHERE type=?",type);
        List<Supplier> list = new ArrayList<>();
        while (rst.next()) {
            list.add(
                    new Supplier(
                            rst.getString(1),
                            rst.getString(2),
                            rst.getInt(3),
                            rst.getString(4),
                            rst.getString(5)
                    )
            );
        }
        return list;
    }

    @Override
    public Supplier getSupplierForName(String name) throws Exception {
        ResultSet rst = CrudUtil.execute("SELECT * FROM supplier WHERE supplierName=?", name);
        if (rst.next()) {
            return new Supplier(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getInt(3),
                    rst.getString(4),
                    rst.getString(5)
            );
        }
        return null;
    }

    @Override
    public String getLastSupplierID() throws Exception {
        ResultSet rst=CrudUtil.execute("SELECT supplierId FROM supplier ORDER BY supplierId DESC LIMIT 1");
        String id=null;
        if(rst.next()){
            id=rst.getString(1);
        }
        return id;
    }
}
