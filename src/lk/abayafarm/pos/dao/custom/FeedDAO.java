package lk.abayafarm.pos.dao.custom;

import lk.abayafarm.pos.dao.CrudDAO;
import lk.abayafarm.pos.entity.Feed;

public interface FeedDAO extends CrudDAO<Feed,String> {
    public Feed getFeed(String type,int packSize) throws Exception;
}
