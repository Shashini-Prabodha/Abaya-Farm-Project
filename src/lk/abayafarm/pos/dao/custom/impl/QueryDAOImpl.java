package lk.abayafarm.pos.dao.custom.impl;

import lk.abayafarm.pos.dao.CrudUtil;
import lk.abayafarm.pos.dao.QueryDAO;
import lk.abayafarm.pos.dto.FeedDTO;
import lk.abayafarm.pos.entity.Cage;
import lk.abayafarm.pos.entity.Batch;
import lk.abayafarm.pos.entity.Feed;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class QueryDAOImpl implements QueryDAO {
    @Override
    public List<Cage> getCageIDInLayer() throws Exception {
        ResultSet rst = CrudUtil.execute("SELECT c.cageId, c.batchId, c.maxQty, c.availableQty FROM cage c, batch b WHERE (c.batchId=b.batchId and b.type='layer' and b.status='in')");
        ArrayList<Cage> list = new ArrayList<>();
        while (rst.next()) {
            list.add(new Cage(rst.getString("cageId"), rst.getString("batchId"), rst.getInt("maxQty"), rst.getInt("availableQty")));
        }
        return list;
    }

    @Override
    public FeedDTO getFeed(String type) throws Exception {
        ResultSet rst = CrudUtil.execute("SELECT f.feedId, f.qtyOnHand,f.feedType,f.packSize FROM feed f, batch b WHERE f.feedType=b.type and  b.type=? GROUP BY(feedType)", type);
        if (rst.next()) {
            return new FeedDTO(
                    rst.getString(1),
                    rst.getInt(2),
                    rst.getString(3),
                    rst.getInt(4)
            );
        }
        return null;
    }

    @Override
    public String getType(String cageID) throws Exception {
        ResultSet rst = CrudUtil.execute("SELECT b.type FROM cage c, batch b WHERE (c.batchId=b.batchId and c.cageId=?)", cageID);
        String type = "layer";
        if (rst.next()) {
            type = rst.getString(1);
        }
        return type;
    }

}
