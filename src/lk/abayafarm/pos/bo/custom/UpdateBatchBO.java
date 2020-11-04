package lk.abayafarm.pos.bo.custom;

import lk.abayafarm.pos.dto.BatchDTO;
import lk.abayafarm.pos.dto.CageDTO;
import lk.abayafarm.pos.entity.Cage;

public interface UpdateBatchBO {
    public boolean updateBatch(BatchDTO dto, CageDTO cageDTO)throws Exception;
}
