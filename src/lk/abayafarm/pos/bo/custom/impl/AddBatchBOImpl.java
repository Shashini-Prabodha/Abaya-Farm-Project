package lk.abayafarm.pos.bo.custom.impl;

import lk.abayafarm.pos.bo.custom.AddBatchBO;
import lk.abayafarm.pos.dao.DaoFactory;
import lk.abayafarm.pos.dao.custom.BatchDAO;
import lk.abayafarm.pos.dao.custom.CageDAO;
import lk.abayafarm.pos.dao.custom.SupplierOrderDAO;
import lk.abayafarm.pos.dao.custom.impl.BatchDAOImpl;
import lk.abayafarm.pos.db.DBConnection;
import lk.abayafarm.pos.dto.BatchDTO;
import lk.abayafarm.pos.dto.CageDTO;
import lk.abayafarm.pos.dto.SupplierOrderDTO;
import lk.abayafarm.pos.entity.Batch;
import lk.abayafarm.pos.entity.Cage;
import lk.abayafarm.pos.entity.SupplierOrder;

import java.sql.Connection;

public class AddBatchBOImpl implements AddBatchBO {
    BatchDAO batchDAO = DaoFactory.getInstance().getDao(DaoFactory.DaoType.BATCH);
    CageDAO cageDAO = DaoFactory.getInstance().getDao(DaoFactory.DaoType.CAGE);
    SupplierOrderDAO supplierOrderDAO = DaoFactory.getInstance().getDao(DaoFactory.DaoType.SUPPLIERORDER);

    @Override
    public boolean addBatch(BatchDTO batchDTO, SupplierOrderDTO supplierOrderDTO, CageDTO c) throws Exception {
        Connection connection = DBConnection.getInstance().getConnection();;
        try {

            connection.setAutoCommit(false);
            Batch batch = new Batch(batchDTO.getBatchId(), batchDTO.getType(), batchDTO.getQty(), batchDTO.getStatus());
            SupplierOrder supplierOrder = new SupplierOrder(supplierOrderDTO.getSupOrderId(), supplierOrderDTO.getSupplierId(), supplierOrderDTO.getbatchID(), supplierOrderDTO.getDescription(), supplierOrderDTO.getQtyOnHand(), supplierOrderDTO.getUnitPrice(), supplierOrderDTO.getDate());
            Cage cage = new Cage(c.getCageId(), c.getBatchId(), c.getMaxQty(), c.getAvailableQty());
            if (batchDAO.save(batch)) {
                if (supplierOrderDAO.save(supplierOrder)) {
                    if (cageDAO.update(cage)) {
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
            } else {
                connection.rollback();
                return false;
            }

        } finally {
            connection.setAutoCommit(true);
        }

    }
}
