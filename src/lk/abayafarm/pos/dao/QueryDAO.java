package lk.abayafarm.pos.dao;

import lk.abayafarm.pos.dto.FeedDTO;
import lk.abayafarm.pos.entity.Cage;
import lk.abayafarm.pos.entity.Feed;

import java.util.List;

public interface QueryDAO extends SuperDAO{
    public List<Cage> getCageIDInLayer() throws Exception;
    public FeedDTO getFeed(String type) throws Exception;
    public String getType(String cageID) throws Exception;


    //public List getIncomeViseCustomer() throws Exception;

}
