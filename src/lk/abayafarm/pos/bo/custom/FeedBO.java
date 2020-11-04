package lk.abayafarm.pos.bo.custom;

import lk.abayafarm.pos.dao.CrudDAO;
import lk.abayafarm.pos.dto.EggDTO;
import lk.abayafarm.pos.dto.FeedDTO;
import lk.abayafarm.pos.entity.Feed;

import java.util.ArrayList;

public interface FeedBO{
    public boolean saveFeed(FeedDTO dto)throws Exception;
    public boolean updateFeed(FeedDTO dto)throws Exception;
    public boolean deleteFeed(String id) throws Exception;
    public FeedDTO getFeed(String id) throws Exception;
    public ArrayList<FeedDTO> getAllFeed() throws Exception;
    public FeedDTO getFeed(String type, int packSize) throws Exception;
}
