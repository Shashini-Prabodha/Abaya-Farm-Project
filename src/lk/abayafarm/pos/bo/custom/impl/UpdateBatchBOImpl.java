package lk.abayafarm.pos.bo.custom.impl;

import lk.abayafarm.pos.bo.custom.UpdateBatchBO;
import lk.abayafarm.pos.dao.DaoFactory;
import lk.abayafarm.pos.dao.custom.BatchDAO;
import lk.abayafarm.pos.dao.custom.CageDAO;
import lk.abayafarm.pos.db.DBConnection;
import lk.abayafarm.pos.dto.BatchDTO;
import lk.abayafarm.pos.dto.CageDTO;
import lk.abayafarm.pos.entity.Batch;
import lk.abayafarm.pos.entity.Cage;

import java.sql.Connection;

public class UpdateBatchBOImpl implements UpdateBatchBO {
    BatchDAO batchDAO= DaoFactory.getInstance().getDao(DaoFactory.DaoType.BATCH);
    CageDAO cageDAO= DaoFactory.getInstance().getDao(DaoFactory.DaoType.CAGE);

    @Override
    public boolean updateBatch(BatchDTO dto, CageDTO cageDTO) throws Exception {
        Connection connection = DBConnection.getInstance().getConnection();
        try {
            connection.setAutoCommit(false);
            Batch batch=new Batch(dto.getBatchId(),dto.getType(),dto.getQty(),dto.getStatus());
            Cage cage=new Cage(cageDTO.getCageId(),cageDTO.getBatchId(),cageDTO.getMaxQty(),cageDTO.getAvailableQty());
            if(batchDAO.update(batch)){
                if(cageDAO.update(cage)){
                    connection.commit();
                    return true;
                }else{
                    connection.rollback();
                }
            }else{
                connection.rollback();
            }

        }finally {
            connection.setAutoCommit(true);
        }
        return false;
    }
}
