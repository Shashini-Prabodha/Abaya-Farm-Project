package lk.abayafarm.pos.dao.custom.impl;

import lk.abayafarm.pos.dao.CrudUtil;
import lk.abayafarm.pos.dao.custom.SupplierOrderDAO;
import lk.abayafarm.pos.entity.Income;
import lk.abayafarm.pos.entity.Supplier;
import lk.abayafarm.pos.entity.SupplierOrder;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SupplierOrderDAOImpl implements SupplierOrderDAO {
    @Override
    public boolean save(SupplierOrder supplierOrder) throws Exception {
        return CrudUtil.execute("INSERT INTO supplierOrder VALUES(?,?,?,?,?,?,?)",supplierOrder.getSupOrderId(),supplierOrder.getSupplierId(),supplierOrder.getbatchID(),supplierOrder.getDescription(),supplierOrder.getQtyOnHand(),supplierOrder.getUnitPrice(),supplierOrder.getDate());

    }

    @Override
    public boolean update(SupplierOrder supplierOrder) throws Exception {
        return CrudUtil.execute("UPDATE supplierOrder SET supplierId=?, batchId=?, description=?, description=?, qtyOnHand=?, unitPrice=?, date=? WHERE supOrderId=?",supplierOrder.getSupplierId(),supplierOrder.getbatchID(),supplierOrder.getDescription(),supplierOrder.getQtyOnHand(),supplierOrder.getUnitPrice(),supplierOrder.getDate(),supplierOrder.getSupOrderId());

    }

    @Override
    public boolean delete(String s) throws Exception {
        return CrudUtil.execute("DELETE FROM supplierOrder WHERE supOrderId=?", s);
    }

    @Override
    public SupplierOrder get(String s) throws Exception {
        ResultSet rst = CrudUtil.execute("SELECT * FROM supplierOrder WHERE supOrderId=?", s);
        if (rst.next()) {
            return new SupplierOrder(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getString(4),
                    rst.getInt(5),
                    rst.getDouble(6),
                    rst.getDate(7)
            );
        }
        return null;
    }

    @Override
    public List<SupplierOrder> getAll() throws Exception {
        ResultSet rst = CrudUtil.execute("SELECT * FROM supplierOrder");
        ArrayList<SupplierOrder> list = new ArrayList<>();
        while (rst.next()) {
            list.add(
                    new SupplierOrder(
                            rst.getString(1),
                            rst.getString(2),
                            rst.getString(3),
                            rst.getString(4),
                            rst.getInt(5),
                            rst.getDouble(6),
                            rst.getDate(7)
                    )
            );
        }
        return list;
    }

    @Override
    public String getLastOrderId() throws SQLException, ClassNotFoundException {
        ResultSet rst=CrudUtil.execute("SELECT supOrderId FROM supplierOrder ORDER BY supOrderId DESC LIMIT 1");
        String id=null;
        if(rst.next()){
            id=rst.getString(1);
        }
        return id;
    }
}
