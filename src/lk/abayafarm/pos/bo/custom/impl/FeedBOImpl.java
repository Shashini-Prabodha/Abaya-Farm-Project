package lk.abayafarm.pos.bo.custom.impl;

import lk.abayafarm.pos.bo.custom.FeedBO;
import lk.abayafarm.pos.dao.DaoFactory;
import lk.abayafarm.pos.dao.custom.FeedDAO;
import lk.abayafarm.pos.dto.EggDTO;
import lk.abayafarm.pos.dto.FeedDTO;
import lk.abayafarm.pos.entity.Egg;
import lk.abayafarm.pos.entity.Feed;

import java.util.ArrayList;
import java.util.List;

public class FeedBOImpl implements FeedBO {

    private FeedDAO feedDAO= DaoFactory.getInstance().getDao(DaoFactory.DaoType.FEED);
    @Override
    public boolean saveFeed(FeedDTO dto) throws Exception {
        return feedDAO.save(new Feed(dto.getFeedId(),dto.getQtyOnHand(),dto.getFeedType(),dto.getPackSize()));
    }

    @Override
    public boolean updateFeed(FeedDTO dto) throws Exception {
        return feedDAO.update(new Feed(dto.getFeedId(),dto.getQtyOnHand(),dto.getFeedType(),dto.getPackSize()));
    }

    @Override
    public boolean deleteFeed(String id) throws Exception {
        return feedDAO.delete(id);
    }

    @Override
    public FeedDTO getFeed(String id) throws Exception {
        Feed feed=feedDAO.get(id);
        return new FeedDTO(feed.getFeedId(),feed.getQtyOnHand(),feed.getFeedType(),feed.getPackSize());
    }

    @Override
    public ArrayList<FeedDTO> getAllFeed() throws Exception {
        List<Feed> list = feedDAO.getAll();
        ArrayList<FeedDTO> arrayList=new ArrayList<>();
        for (Feed feed : list) {
            arrayList.add(new FeedDTO(feed.getFeedId(),feed.getQtyOnHand(),feed.getFeedType(),feed.getPackSize()));
        }
        return arrayList;
    }

    @Override
    public FeedDTO getFeed(String type, int packSize) throws Exception {
        Feed feed=feedDAO.getFeed(type,packSize);
        return new FeedDTO(feed.getFeedId(),feed.getQtyOnHand(),feed.getFeedType(),feed.getPackSize());
    }
}
