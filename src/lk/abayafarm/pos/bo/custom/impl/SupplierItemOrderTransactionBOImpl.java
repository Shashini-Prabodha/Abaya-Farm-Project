package lk.abayafarm.pos.bo.custom.impl;

import lk.abayafarm.pos.bo.custom.SupplierItemOrderTransactionBo;
import lk.abayafarm.pos.dao.DaoFactory;
import lk.abayafarm.pos.dao.custom.*;
import lk.abayafarm.pos.db.DBConnection;
import lk.abayafarm.pos.dto.*;
import lk.abayafarm.pos.entity.*;

import java.sql.Connection;

public class SupplierItemOrderTransactionBOImpl implements SupplierItemOrderTransactionBo {

    FeedDAO feedDAO = DaoFactory.getInstance().getDao(DaoFactory.DaoType.FEED);
    MedicineDAO medicineDAO= DaoFactory.getInstance().getDao(DaoFactory.DaoType.MEDICINE);
    SupplierOrderDAO supplierOrderDAO = DaoFactory.getInstance().getDao(DaoFactory.DaoType.SUPPLIERORDER);

    public boolean addBatch(SupplierOrderDTO supplierOrderDTO, FeedDTO feedDTO) throws Exception {
        Feed dto=new Feed(feedDTO.getFeedId(),feedDTO.getQtyOnHand(),feedDTO.getFeedType(),feedDTO.getPackSize());
        Connection connection = DBConnection.getInstance().getConnection();
        try {
            connection.setAutoCommit(false);
            SupplierOrder supplierOrder = new SupplierOrder(supplierOrderDTO.getSupOrderId(), supplierOrderDTO.getSupplierId(),
                    supplierOrderDTO.getbatchID(), supplierOrderDTO.getDescription(), supplierOrderDTO.getQtyOnHand(), supplierOrderDTO.getUnitPrice(), supplierOrderDTO.getDate());
            if (supplierOrderDAO.save(supplierOrder)) {
                if (feedDAO.update(dto)) {
                    connection.commit();
                    return true;
                } else {
                    connection.rollback();
                    return false;
                }


            } else {
                connection.rollback();
                return false;
            }

        } finally {
            connection.setAutoCommit(true);
        }
    }



    @Override
    public boolean addBatch(SupplierOrderDTO supplierOrderDTO,MedicineDTO medicineDTO) throws Exception {
        Connection connection = DBConnection.getInstance().getConnection();
        try {
            connection.setAutoCommit(false);
            Medicine medicine=new Medicine(medicineDTO.getMedicineId(),medicineDTO.getMedicineName(),medicineDTO.getQtyOnHand(),medicineDTO.getDescription());
            SupplierOrder supplierOrder = new SupplierOrder(supplierOrderDTO.getSupOrderId(), supplierOrderDTO.getSupplierId(),
                    supplierOrderDTO.getbatchID(), supplierOrderDTO.getDescription(), supplierOrderDTO.getQtyOnHand(), supplierOrderDTO.getUnitPrice(), supplierOrderDTO.getDate());
                if (supplierOrderDAO.save(supplierOrder)) {
                    if (medicineDAO.update(medicine)) {
                        connection.commit();
                        return true;
                    } else {
                        connection.rollback();
                        return false;
                    }


            } else {
                connection.rollback();
                return false;
            }

        } finally {
            connection.setAutoCommit(true);
        }

    }
}
