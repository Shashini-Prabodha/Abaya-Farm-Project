package lk.abayafarm.pos.bo.custom;

import lk.abayafarm.pos.dao.CrudDAO;
import lk.abayafarm.pos.dao.CrudUtil;
import lk.abayafarm.pos.dto.BatchDTO;
import lk.abayafarm.pos.entity.Batch;
import lk.abayafarm.pos.entity.Cage;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public interface BatchBO {
    public boolean saveBatch(BatchDTO dto) throws Exception;

    public boolean updateBatch(BatchDTO dto) throws Exception;

    public boolean deleteBatch(String id) throws Exception;

    public BatchDTO getBatch(String id) throws Exception;

    public ArrayList<BatchDTO> getAllBatch() throws Exception;

    public String getLastBatchID() throws Exception;

    public int getQtyWiseBatch(String type) throws Exception;


}
