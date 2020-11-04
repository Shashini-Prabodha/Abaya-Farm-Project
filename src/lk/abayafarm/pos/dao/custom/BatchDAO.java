package lk.abayafarm.pos.dao.custom;

import lk.abayafarm.pos.dao.CrudDAO;
import lk.abayafarm.pos.dao.CrudUtil;
import lk.abayafarm.pos.entity.Batch;
import lk.abayafarm.pos.entity.Cage;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public interface BatchDAO extends CrudDAO<Batch, String> {
    public String getLastBatchID() throws Exception;
    public int getQtyWiseBatch(String type) throws Exception;

}
