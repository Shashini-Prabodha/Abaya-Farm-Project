package lk.abayafarm.pos.bo.custom.impl;

import lk.abayafarm.pos.bo.custom.BatchBO;
import lk.abayafarm.pos.dao.DaoFactory;
import lk.abayafarm.pos.dao.custom.BatchDAO;
import lk.abayafarm.pos.dto.BatchDTO;
import lk.abayafarm.pos.entity.Batch;

import java.util.ArrayList;
import java.util.List;

public class BatchBOImpl implements BatchBO {
    BatchDAO batchDAO = DaoFactory.getInstance().getDao(DaoFactory.DaoType.BATCH);

    @Override
    public boolean saveBatch(BatchDTO dto) throws Exception {
        return batchDAO.save(new Batch(dto.getBatchId(), dto.getType(), dto.getQty(), dto.getStatus()));
    }

    @Override
    public boolean updateBatch(BatchDTO dto) throws Exception {
        return batchDAO.update(new Batch(dto.getBatchId(), dto.getType(), dto.getQty(), dto.getStatus()));
    }

    @Override
    public boolean deleteBatch(String id) throws Exception {
        return batchDAO.delete(id);
    }

    @Override
    public BatchDTO getBatch(String id) throws Exception {
        Batch batch = batchDAO.get(id);
        return new BatchDTO(batch.getBatchId(), batch.getType(), batch.getQty(), batch.getStatus());
    }

    @Override
    public ArrayList<BatchDTO> getAllBatch() throws Exception {
        List<Batch> list = batchDAO.getAll();
        ArrayList<BatchDTO> arrayList = new ArrayList();
        for (Batch batch : list) {
            arrayList.add(new BatchDTO(batch.getBatchId(),
                    batch.getType(),
                    batch.getQty(),
                    batch.getStatus())
            );
        }
        return arrayList;
    }

    @Override
    public String getLastBatchID() throws Exception {
        return batchDAO.getLastBatchID();
    }

    @Override
    public int getQtyWiseBatch(String type) throws Exception {
        return batchDAO.getQtyWiseBatch(type);
    }

}
