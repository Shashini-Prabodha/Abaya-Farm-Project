package lk.abayafarm.pos.dao.custom.impl;

import lk.abayafarm.pos.dao.CrudUtil;
import lk.abayafarm.pos.dao.custom.ManureDAO;
import lk.abayafarm.pos.entity.FeedDetails;
import lk.abayafarm.pos.entity.Income;
import lk.abayafarm.pos.entity.Manure;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ManureDAOImpl implements ManureDAO {
    @Override
    public boolean save(Manure manure) throws Exception {
        return CrudUtil.execute("INSERT INTO manure VALUES(?,?,?,?,?)", manure.getCageId(), manure.getPackSize(), manure.getQty(), manure.getUnitPrice(), manure.getDate());
    }

    @Override
    public boolean update(Manure manure) throws Exception {
        return CrudUtil.execute("UPDATE manure SET qty=?, unitPrice=? WHERE cageId=? and packSize=? and  date=?", manure.getQty(), manure.getUnitPrice(), manure.getCageId(), manure.getPackSize(), manure.getDate());
    }

    @Override
    public boolean delete(String s) throws Exception {
        return CrudUtil.execute("DELETE FROM manure WHERE cageId=? and  packSize=? and  date=?", s);
    }

    @Override
    public Manure get(String s) throws Exception {
        ResultSet rst = CrudUtil.execute("SELECT * FROM manure WHERE cageId=? and  packSize=? and  date=?", s);
        if (rst.next()) {
            return new Manure(rst.getString(1),
                    rst.getString(2),
                    rst.getInt(3),
                    rst.getDouble(4),
                    rst.getDate(5)
            );
        }
        return null;
    }

    @Override
    public List<Manure> getAll() throws Exception {
        ResultSet rst = CrudUtil.execute("SELECT * FROM manure");
        ArrayList<Manure> list = new ArrayList<>();
        while (rst.next()) {
            list.add(
                    new Manure(
                            rst.getString(1),
                            rst.getString(2),
                            rst.getInt(3),
                            rst.getDouble(4),
                            rst.getDate(5)
                    )
            );
        }
        return list;
    }
}
