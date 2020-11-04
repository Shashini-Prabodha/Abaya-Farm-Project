package lk.abayafarm.pos.dao.custom.impl;

import lk.abayafarm.pos.dao.CrudUtil;
import lk.abayafarm.pos.dao.custom.CageDAO;
import lk.abayafarm.pos.entity.Cage;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CageDAOImpl implements CageDAO {
    @Override
    public boolean save(Cage cage) throws Exception {
        return CrudUtil.execute("INSERT INTO cage VALUES(?,?,?,?)", cage.getCageId(), cage.getBatchId(),cage.getMaxQty(), cage.getAvailableQty());
    }

    @Override
    public boolean update(Cage cage) throws Exception {
        return CrudUtil.execute("UPDATE cage SET batchId=?, maxQty=?, availableQty=? WHERE cageId=?",cage.getBatchId(),cage.getMaxQty(), cage.getAvailableQty(), cage.getCageId());
    }

    @Override
    public boolean delete(String s) throws Exception {
        return CrudUtil.execute("DELETE FROM cage WHERE cageId=?", s);
    }

    @Override
    public Cage get(String s) throws Exception {
        ResultSet rst = CrudUtil.execute("SELECT * FROM cage WHERE cageId=?", s);
        if (rst.next()) {
            return new Cage(rst.getString(1),
                    rst.getString(2),
                    rst.getInt(3),
                    rst.getInt(4)
            );
        }
        return null;
    }

    @Override
    public List<Cage> getAll() throws Exception {
        ResultSet rst = CrudUtil.execute("SELECT * FROM cage");
        List<Cage> list = new ArrayList<>();
        while (rst.next()) {
            list.add(
                    new Cage(
                            rst.getString(1),
                            rst.getString(2),
                            rst.getInt(3),
                            rst.getInt(4)
                    )
            );
        }
        return list;
    }

//    @Override
//    public List<Cage> getCageIDListIn() throws Exception {
//        ResultSet rst = CrudUtil.execute("SELECT * FROM cage WHERE batchId=?");
//        List<Cage> list = new ArrayList<>();
//        while (rst.next()) {
//            list.add(
//                    new Cage(
//                            rst.getString(1),
//                            rst.getString(2),
//                            rst.getInt(3),
//                            rst.getInt(4)
//                    )
//            );
//        }
//        return list;
//    }
    @Override
    public List<Cage> getCageIDListExit() throws Exception {
        ResultSet rst = CrudUtil.execute("SELECT * FROM cage WHERE batchId='B000'");
        List<Cage> list = new ArrayList<>();
        while (rst.next()) {
            list.add(
                    new Cage(
                            rst.getString(1),
                            rst.getString(2),
                            rst.getInt(3),
                            rst.getInt(4)
                    )
            );
        }
        return list;
    }

    @Override
    public String getLastCageID() throws SQLException, ClassNotFoundException {
        ResultSet rst=CrudUtil.execute("SELECT cageId FROM cage ORDER BY cageId DESC LIMIT 1");
        String id="C0";
        if(rst.next()){
            id=rst.getString(1);
        }
        return id;
    }
    public List<Cage> getCageIDListIn() throws Exception {
        ResultSet rst = CrudUtil.execute("SELECT * FROM cage WHERE batchId!='B000'");
        List<Cage> list = new ArrayList<>();
        while (rst.next()) {
            list.add(
                    new Cage(
                            rst.getString(1),
                            rst.getString(2),
                            rst.getInt(3),
                            rst.getInt(4)
                    )
            );
        }
        return list;
    }
}
