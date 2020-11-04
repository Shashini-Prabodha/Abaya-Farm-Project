package lk.abayafarm.pos.bo.custom;

import lk.abayafarm.pos.dto.BatchDTO;
import lk.abayafarm.pos.dto.CageDTO;

public interface SellBatchBO {
    public boolean sellBatch(BatchDTO dto, CageDTO cage)throws Exception;
}
