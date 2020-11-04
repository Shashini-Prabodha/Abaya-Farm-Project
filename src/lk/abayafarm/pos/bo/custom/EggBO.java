package lk.abayafarm.pos.bo.custom;

import lk.abayafarm.pos.dto.BatchDTO;
import lk.abayafarm.pos.dto.CageDTO;
import lk.abayafarm.pos.dto.EggDTO;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public interface EggBO {
    public boolean saveEgg(EggDTO dto) throws Exception;

    public boolean updateEgg(EggDTO dto) throws Exception;

    public boolean deleteEgg(String id) throws Exception;

    public EggDTO getEgg(String id) throws Exception;

    public ArrayList<EggDTO> getAllEgg() throws Exception;

    public int getLastTerm(String cageId, Date date) throws Exception;

    public List<Integer> getEggsinWeek(String type) throws Exception;
}
