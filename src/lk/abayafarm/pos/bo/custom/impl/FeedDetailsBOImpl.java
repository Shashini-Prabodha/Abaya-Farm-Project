package lk.abayafarm.pos.bo.custom.impl;

import lk.abayafarm.pos.bo.custom.FeedDetailsBO;
import lk.abayafarm.pos.dao.DaoFactory;
import lk.abayafarm.pos.dao.custom.FeedDAO;
import lk.abayafarm.pos.dao.custom.FeedDetailsDAO;
import lk.abayafarm.pos.dto.FeedDetailsDTO;
import lk.abayafarm.pos.dto.SupplierOrderDTO;
import lk.abayafarm.pos.entity.Feed;
import lk.abayafarm.pos.entity.FeedDetails;
import lk.abayafarm.pos.entity.SupplierOrder;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class FeedDetailsBOImpl implements FeedDetailsBO {

    FeedDetailsDAO feedDetailsDAO= DaoFactory.getInstance().getDao(DaoFactory.DaoType.FEEDDETAILS);
    @Override
    public boolean saveFeedDetails(FeedDetailsDTO dto) throws Exception {
        return feedDetailsDAO.save(new FeedDetails(dto.getFeedId(),dto.getCageId(),dto.getTerm(),dto.getUsedQty(),dto.getDate()));
    }

    @Override
    public boolean updateFeedDetails(FeedDetailsDTO dto) throws Exception {
        return feedDetailsDAO.update(new FeedDetails(dto.getFeedId(),dto.getCageId(),dto.getTerm(),dto.getUsedQty(),dto.getDate()));
    }

    @Override
    public boolean deleteFeedDetails(String id) throws Exception {
        return false;
    }

    @Override
    public boolean deleteFeedDetails(FeedDetailsDTO dto) throws Exception {
        return feedDetailsDAO.delete(new FeedDetails(dto.getFeedId(),dto.getCageId(),dto.getTerm(),dto.getUsedQty(),dto.getDate()));
    }

    @Override
    public FeedDetailsDTO getFeedDetails(String id) throws Exception {
       return null;
    }

    @Override
    public FeedDetailsDTO getFeedDetails(String s1, String s2, String s3, String s4) throws Exception {
        FeedDetails feedDetails=feedDetailsDAO.get(s1,s2,s3,s4);
        return new FeedDetailsDTO(feedDetails.getFeedId(),feedDetails.getCageId(),feedDetails.getTerm(),feedDetails.getUsedQty(),feedDetails.getDate());
    }

    @Override
    public ArrayList<FeedDetailsDTO> getAllFeedDetails() throws Exception {
        List<FeedDetails> list = feedDetailsDAO.getAll();
        ArrayList<FeedDetailsDTO> arrayList = new ArrayList<>();
        for (FeedDetails feedDetails : list) {
            arrayList.add(new FeedDetailsDTO(feedDetails.getFeedId(),feedDetails.getCageId(),feedDetails.getTerm(),feedDetails.getUsedQty(),feedDetails.getDate()));
        }
        return arrayList;
    }


    public int getLastTerm(String cageId, Date date) throws Exception{
        return feedDetailsDAO.getLastTerm(cageId,date);
    }
}
