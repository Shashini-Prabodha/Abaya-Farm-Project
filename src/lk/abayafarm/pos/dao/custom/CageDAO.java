package lk.abayafarm.pos.dao.custom;

import lk.abayafarm.pos.dao.CrudDAO;
import lk.abayafarm.pos.entity.Cage;

import java.util.List;

public interface CageDAO extends CrudDAO<Cage,String>{
    public List<Cage> getCageIDListExit() throws Exception;

    String getLastCageID() throws Exception;
    public List<Cage> getCageIDListIn() throws Exception;
}
