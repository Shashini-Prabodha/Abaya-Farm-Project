package lk.abayafarm.pos.dao.custom.impl;

import com.sun.java.browser.plugin2.liveconnect.v1.Result;
import lk.abayafarm.pos.dao.CrudUtil;
import lk.abayafarm.pos.dao.custom.EggDAO;
import lk.abayafarm.pos.entity.Egg;


import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class EggDAOImpl implements EggDAO {
    @Override
    public boolean save(Egg egg) throws Exception {
        return CrudUtil.execute("INSERT INTO egg VALUES(?,?,?,?,?,?,?)",egg.getCageId(),egg.getStoreId(),egg.getType(),egg.getQty(),egg.getTerm(),egg.getDamage(),egg.getDate());
    }

    @Override
    public boolean update(Egg egg) throws Exception {
        return CrudUtil.execute("UPDATE egg SET storeId=?, type=?, qty=?, term=?, damage=?, date=? WHERE cageId=?",egg.getStoreId(),egg.getType(),egg.getQty(),egg.getTerm(),egg.getDamage(),egg.getDate(),egg.getCageId());
    }

    @Override
    public boolean delete(String s) throws Exception {
        return CrudUtil.execute("DELETE FROM egg WHERE cageId=?",s);
    }

    @Override
    public Egg get(String s) throws Exception {
        ResultSet rst=CrudUtil.execute("SELECT * FROM egg WHERE cageId=?",s);
        if(rst.next()){
            return new Egg(rst.getString(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getInt(4),
                    rst.getInt(5),
                    rst.getInt(6),
                    rst.getDate(7));
        }
        return null;
    }

    @Override
    public List<Egg> getAll() throws Exception {
        ResultSet rst=CrudUtil.execute("SELECT * FROM egg");
        ArrayList<Egg> list=new ArrayList<>();
        while (rst.next()){
           list.add(new Egg(rst.getString(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getInt(4),
                    rst.getInt(5),
                    rst.getInt(6),
                    rst.getDate(7)));
        }
        return list;
    }
    @Override
    public int getLastTerm(String cageId, Date date) throws Exception {
        ResultSet rst=CrudUtil.execute("SELECT term FROM egg WHERE date=? and cageId=? ORDER BY term DESC LIMIT 1",date,cageId);
        int term=0;
        if(rst.next()){
            term=rst.getInt(1);
        }
        return term;

    }

    @Override
    public List<Integer> getEggsinWeek(String type) throws Exception {
        ResultSet rst=CrudUtil.execute("SELECT SUM(qty) as qty,date FROM egg WHERE storeId=? GROUP BY (date) Order by 2 DESC limit 7",type);
        ArrayList<Integer> list=new ArrayList<>();
        while (rst.next()){
            list.add(new Integer(rst.getString(1)));
        }
        return list;
    }


}
