package lk.abayafarm.pos.bo.custom;

import lk.abayafarm.pos.dto.EggDTO;
import lk.abayafarm.pos.dto.ManureDTO;

public interface AddItemToStore {
    public boolean addEggstoStore(EggDTO egg, String sid, int qty) throws Exception;
    public boolean addManuretoStore(ManureDTO manureDTO, String sid, int qty) throws Exception;
}
