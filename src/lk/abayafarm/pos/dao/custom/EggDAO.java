package lk.abayafarm.pos.dao.custom;

import lk.abayafarm.pos.dao.CrudDAO;
import lk.abayafarm.pos.entity.Egg;

import java.util.Date;
import java.util.List;

public interface EggDAO extends CrudDAO<Egg, String> {
    public int getLastTerm(String cageId, Date date) throws Exception;

    public List<Integer> getEggsinWeek(String type) throws Exception;


}

