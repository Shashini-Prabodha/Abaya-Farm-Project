package lk.abayafarm.pos.bo.custom;

import lk.abayafarm.pos.dao.CrudDAO;
import lk.abayafarm.pos.dto.BatchDTO;
import lk.abayafarm.pos.dto.CageDTO;
import lk.abayafarm.pos.entity.Cage;

import java.util.ArrayList;
import java.util.List;

public interface CageBO {
    public boolean saveCage(CageDTO dto) throws Exception;

    public boolean updateCage(CageDTO dto) throws Exception;

    public boolean deleteCage(String id) throws Exception;

    public CageDTO getCage(String id) throws Exception;

    public ArrayList<CageDTO> getAllCage() throws Exception;

    public List<CageDTO> getCageIDListInLayer() throws Exception;

    public List<CageDTO> getCageIDListExit() throws Exception;

    String getLastCageID() throws Exception;

    public List<CageDTO> getCageIDListIn() throws Exception;
}
