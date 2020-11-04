package lk.abayafarm.pos.bo.custom;

import lk.abayafarm.pos.dao.CrudDAO;
import lk.abayafarm.pos.dto.EggDTO;
import lk.abayafarm.pos.dto.ManureDTO;
import lk.abayafarm.pos.entity.Manure;

import java.util.ArrayList;

public interface ManureBO {
    public boolean saveManure(ManureDTO dto)throws Exception;
    public boolean updateManure(ManureDTO dto)throws Exception;
    public boolean deleteManure(String id) throws Exception;
    public ManureDTO getManure(String id) throws Exception;
    public ArrayList<ManureDTO> getAllManure() throws Exception;
}
