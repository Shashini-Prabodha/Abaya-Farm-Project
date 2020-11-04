package lk.abayafarm.pos.dao.custom.impl;

import lk.abayafarm.pos.dao.CrudUtil;
import lk.abayafarm.pos.dao.custom.FeedDAO;
import lk.abayafarm.pos.entity.Feed;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class FeedDAOImpl implements FeedDAO {
    @Override
    public boolean save(Feed feed) throws Exception {
        return CrudUtil.execute("INSERT INTO feed VALUES (?,?,?,?)", feed.getFeedId(), feed.getQtyOnHand(), feed.getFeedType(),feed.getPackSize());
    }

    @Override
    public boolean update(Feed feed) throws Exception {
        return CrudUtil.execute("UPDATE feed SET qtyOnHand=?, feedType=?, packSize=? WHERE feedId=?", feed.getQtyOnHand(),feed.getFeedType(), feed.getPackSize(),feed.getFeedId());
    }

    @Override
    public boolean delete(String s) throws Exception {
        return CrudUtil.execute("DELETE FROM feed WHERE feedId=?", s);
    }

    @Override
    public Feed get(String s) throws Exception {
        ResultSet rst = CrudUtil.execute("SELECT * FROM feed WHERE feedId=?", s);
        if (rst.next()) {
            return new Feed(rst.getString(1),
                    rst.getInt(2),
                    rst.getString(3),
                    rst.getInt(4)
            );
        }
        return null;
    }

    @Override
    public List<Feed> getAll() throws Exception {
        ResultSet rst = CrudUtil.execute("SELECT * FROM feed");
        ArrayList<Feed> list = new ArrayList<>();
        while(rst.next()) {
            list.add(
                    new Feed(
                            rst.getString(1),
                            rst.getInt(2),
                            rst.getString(3),
                            rst.getInt(4)
                    )
            );
        }
        return list;
    }

    @Override
    public Feed getFeed(String type, int packSize) throws Exception {
        ResultSet rst =  CrudUtil.execute("SELECT * from Feed WHERE feedType=? and packSize=? ",type,packSize);
        if (rst.next()) {
            return new Feed(rst.getString(1),
                    rst.getInt(2),
                    rst.getString(3),
                    rst.getInt(4)
            );
        }
        return null;
    }
}
