package lk.abayafarm.pos.dao.custom.impl;

import lk.abayafarm.pos.dao.CrudUtil;
import lk.abayafarm.pos.dao.custom.FeedDetailsDAO;
import lk.abayafarm.pos.entity.FeedDetails;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class FeedDetailsDAOImpl implements FeedDetailsDAO {
    @Override
    public boolean save(FeedDetails feedDetails) throws Exception {
        return CrudUtil.execute("INSERT INTO feedDetails VALUES(?,?,?,?,?)", feedDetails.getFeedId(),feedDetails.getCageId(), feedDetails.getTerm(), feedDetails.getUsedQty(), feedDetails.getDate());
    }

    @Override
    public boolean update(FeedDetails feedDetails) throws Exception {
        return CrudUtil.execute("UPDATE feedDetails SET  usedQty=? WHERE feedId=? and cageId=? and term=? and date=?", feedDetails.getUsedQty(),feedDetails.getFeedId(), feedDetails.getCageId(), feedDetails.getTerm(), feedDetails.getDate());
    }

    @Override
    public boolean delete(String s) throws Exception {
        return false;
    }

    @Override
    public boolean delete(FeedDetails feedDetails) throws Exception {
        return CrudUtil.execute("DELETE FROM feedDetails WHERE  feedId=? and cageId=? and term=? and date=?",feedDetails.getFeedId(),feedDetails.getCageId(),feedDetails.getTerm(),feedDetails.getDate());
    }

    @Override
    public FeedDetails get(String s1, String s2, String s3, String s4) throws Exception {
        ResultSet rst = CrudUtil.execute("SELECT * FROM feedDetails WHERE feedId=? and cageId=? and term=? and date=?", s1,s2,s3,s4);
        if (rst.next()) {
            return new FeedDetails(rst.getString(1),
                    rst.getString(2),
                    rst.getInt(3),
                    rst.getInt(4),
                    rst.getDate(5));
        }
        return null;
    }

    @Override
    public FeedDetails get(String cageId) throws Exception {

        return null;
    }


    @Override
    public List<FeedDetails> getAll() throws Exception {
        ResultSet rst = CrudUtil.execute("SELECT * FROM feedDetails ");
        ArrayList<FeedDetails> list = new ArrayList<>();
        while (rst.next()) {
            list.add(
                    new FeedDetails(
                            rst.getString(1),
                            rst.getString(2),
                            rst.getInt(3),
                            rst.getInt(4),
                            rst.getDate(5)
                    )
            );
        }
        return null;
    }

    public int getLastTerm(String cageId, Date date) throws Exception {
        ResultSet rst=CrudUtil.execute("SELECT term FROM feeddetails WHERE date=? and cageId=? ORDER BY term DESC LIMIT 1",date,cageId);
        int term=0;
        if(rst.next()){
            term=rst.getInt(1);
            System.out.println("DAo term "+term);
        }
        return term;

    }
}
