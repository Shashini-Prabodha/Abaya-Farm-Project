package lk.abayafarm.pos.bo.custom.impl;

import lk.abayafarm.pos.bo.custom.FeedServingBO;
import lk.abayafarm.pos.dao.DaoFactory;
import lk.abayafarm.pos.dao.custom.FeedDAO;
import lk.abayafarm.pos.dao.custom.FeedDetailsDAO;
import lk.abayafarm.pos.db.DBConnection;
import lk.abayafarm.pos.dto.FeedDTO;
import lk.abayafarm.pos.dto.FeedDetailsDTO;
import lk.abayafarm.pos.entity.Feed;
import lk.abayafarm.pos.entity.FeedDetails;

import java.sql.Connection;

public class FeedServingBOImpl implements FeedServingBO {

    FeedDAO feedDAO = DaoFactory.getInstance().getDao(DaoFactory.DaoType.FEED);
    FeedDetailsDAO feedDetailsDAO = DaoFactory.getInstance().getDao(DaoFactory.DaoType.FEEDDETAILS);

    @Override
    public boolean feedServe(FeedDTO feedDTO, FeedDetailsDTO feedDetailsDTO) throws Exception {
        Connection connection = DBConnection.getInstance().getConnection();

        Feed feed = new Feed(feedDTO.getFeedId(), feedDTO.getQtyOnHand(), feedDTO.getFeedType(), feedDTO.getPackSize());
        FeedDetails feedDetails = new FeedDetails(feedDetailsDTO.getFeedId(), feedDetailsDTO.getCageId(), feedDetailsDTO.getTerm(), feedDetailsDTO.getUsedQty(), feedDetailsDTO.getDate());

        try {
            connection.setAutoCommit(false);
            if (feedDetailsDAO.save(feedDetails)) {
                if (feedDAO.update(feed)) {
                    System.out.println("ksjfk");
                    connection.commit();
                    return true;
                } else {
                    connection.rollback();
                    return false;
                }

            } else {
                connection.rollback();
                return false;
            }
        } finally {
            connection.setAutoCommit(true);
        }

    }
}

