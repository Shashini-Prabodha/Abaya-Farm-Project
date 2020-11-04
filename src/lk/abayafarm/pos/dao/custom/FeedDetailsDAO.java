package lk.abayafarm.pos.dao.custom;

import lk.abayafarm.pos.dao.CrudDAO;
import lk.abayafarm.pos.entity.FeedDetails;

import java.util.Date;

public interface FeedDetailsDAO extends CrudDAO<FeedDetails,String> {
    public boolean delete(FeedDetails feedDetails) throws Exception;
    public FeedDetails get(String s1, String s2, String s3, String s4) throws Exception;
    public int getLastTerm(String cageId, Date date) throws Exception;
}
