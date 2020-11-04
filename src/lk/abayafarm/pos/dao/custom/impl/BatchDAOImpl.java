package lk.abayafarm.pos.dao.custom.impl;

import lk.abayafarm.pos.dao.CrudUtil;
import lk.abayafarm.pos.dao.custom.BatchDAO;
import lk.abayafarm.pos.entity.Batch;
import lk.abayafarm.pos.entity.Cage;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class BatchDAOImpl implements BatchDAO {
    @Override
    public boolean save(Batch batch) throws Exception {
        return CrudUtil.execute("INSERT INTO batch VALUES(?,?,?,?)", batch.getBatchId(), batch.getType(), batch.getQty(), batch.getStatus());
    }

    @Override
    public boolean update(Batch batch) throws Exception {
        return CrudUtil.execute("UPDATE batch SET type=?, qty=?, status=? WHERE batchId=?", batch.getType(), batch.getQty(), batch.getStatus(), batch.getBatchId());
    }

    @Override
    public boolean delete(String s) throws Exception {
        return CrudUtil.execute("DELETE FROM batch WHERE batchId=?", s);
    }

    @Override
    public Batch get(String s) throws Exception {
        ResultSet rst = CrudUtil.execute("SELECT * FROM batch WHERE batchId=?", s);
        if (rst.next()) {
            return new Batch(rst.getString(1),
                    rst.getString(2),
                    rst.getInt(3),
                    rst.getString(4)
            );
        }
        return null;
    }

    @Override
    public List<Batch> getAll() throws Exception {
        ResultSet rst = CrudUtil.execute("SELECT * FROM batch");
        ArrayList<Batch> list = new ArrayList<>();
        while (rst.next()) {
            list.add(
                    new Batch(
                            rst.getString(1),
                            rst.getString(2),
                            rst.getInt(3),
                            rst.getString(4)
                    )
            );
        }
        return list;
    }


    @Override
    public String getLastBatchID() throws Exception {
        ResultSet rst= CrudUtil.execute("SELECT batchId FROM batch ORDER BY batchId DESC LIMIT 1");
        String id="B0";
        if(rst.next()){
            id=rst.getString(1);
        }
        return id;
    }

    @Override
    public int getQtyWiseBatch(String type) throws Exception {
        ResultSet rst= CrudUtil.execute("SELECT SUM(qty) as qty FROM batch WHERE type=? and status='in' ",type);
        int qty=0;
        if(rst.next()){
            qty=rst.getInt(1);
        }
        return qty;
    }



}
