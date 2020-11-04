package lk.abayafarm.pos.bo.custom;

import lk.abayafarm.pos.dao.CrudDAO;
import lk.abayafarm.pos.dto.EggDTO;
import lk.abayafarm.pos.dto.FeedDetailsDTO;
import lk.abayafarm.pos.entity.FeedDetails;

import java.util.ArrayList;
import java.util.Date;

public interface FeedDetailsBO{

    public boolean saveFeedDetails(FeedDetailsDTO dto)throws Exception;
    public boolean updateFeedDetails(FeedDetailsDTO dto)throws Exception;
    public boolean deleteFeedDetails(String id) throws Exception;
    public boolean deleteFeedDetails(FeedDetailsDTO dto) throws Exception;
    public FeedDetailsDTO getFeedDetails(String id) throws Exception;
    public FeedDetailsDTO getFeedDetails(String s1,String s2,String s3,String s4) throws Exception;
    public ArrayList<FeedDetailsDTO> getAllFeedDetails() throws Exception;
    public int getLastTerm(String cageId, Date date) throws Exception;
}
