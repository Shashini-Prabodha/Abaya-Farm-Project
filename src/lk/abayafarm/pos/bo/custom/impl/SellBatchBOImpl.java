package lk.abayafarm.pos.bo.custom.impl;

import lk.abayafarm.pos.bo.custom.SellBatchBO;
import lk.abayafarm.pos.dao.DaoFactory;
import lk.abayafarm.pos.dao.custom.BatchDAO;
import lk.abayafarm.pos.dao.custom.CageDAO;
import lk.abayafarm.pos.db.DBConnection;
import lk.abayafarm.pos.dto.BatchDTO;
import lk.abayafarm.pos.dto.CageDTO;
import lk.abayafarm.pos.entity.Batch;
import lk.abayafarm.pos.entity.Cage;

import java.sql.Connection;

public class SellBatchBOImpl implements SellBatchBO {

    BatchDAO batchDAO = DaoFactory.getInstance().getDao(DaoFactory.DaoType.BATCH);
    CageDAO cageDAO = DaoFactory.getInstance().getDao(DaoFactory.DaoType.CAGE);

    @Override
    public boolean sellBatch(BatchDTO dto, CageDTO cagedto) throws Exception {
        Connection connection = DBConnection.getInstance().getConnection();
        Batch batch=new Batch(dto.getBatchId(),dto.getType(),dto.getQty(),dto.getStatus());
        Cage cage = new Cage(cagedto.getCageId(), "B000", cagedto.getMaxQty(), 0);
        try {
            connection.setAutoCommit(false);
            if (batchDAO.update(batch)) {
                if (cageDAO.update(cage)) {
                    connection.commit();
                    return true;
                } else {
                }
            } else {
            }
            connection.rollback();
            return false;

        } finally {
            connection.setAutoCommit(true);
        }
    }
}
