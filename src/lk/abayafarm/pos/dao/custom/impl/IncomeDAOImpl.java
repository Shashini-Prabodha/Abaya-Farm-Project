package lk.abayafarm.pos.dao.custom.impl;

import lk.abayafarm.pos.dao.CrudUtil;
import lk.abayafarm.pos.dao.custom.IncomeDAO;
import lk.abayafarm.pos.db.DBConnection;
import lk.abayafarm.pos.entity.Feed;
import lk.abayafarm.pos.entity.Income;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class IncomeDAOImpl implements IncomeDAO {
    @Override
    public boolean save(Income income) throws Exception {
        return CrudUtil.execute("INSERT INTO income VALUES(?,?,?)", income.getOrderId(), income.getDate(), income.getTotal());
    }

    @Override
    public boolean update(Income income) throws Exception {
        return CrudUtil.execute("UPDATE income SET date=?, total=? WHERE orderId=?", income.getDate(), income.getTotal(), income.getOrderId());
    }

    @Override
    public boolean delete(String s) throws Exception {
        return CrudUtil.execute("DELETE FROM income WHERE orderId=?", s);
    }

    @Override
    public Income get(String s) throws Exception {
        ResultSet rst = CrudUtil.execute("SELECT * FROM income WHERE orderId=?", s);
        if (rst.next()) {
            return new Income(
                    rst.getString(1),
                    rst.getDate(2),
                    rst.getDouble(3)
            );
        }
        return null;
    }

    @Override
    public List<Income> getAll() throws Exception {
        ResultSet rst = CrudUtil.execute("SELECT * FROM income");
        ArrayList<Income> list = new ArrayList<>();
        while (rst.next()) {
            list.add(
                    new Income(
                            rst.getString(1),
                            rst.getDate(2),
                            rst.getDouble(3)
                    )
            );
        }
        return list;
    }
    @Override
    public double getTodayIncome() throws Exception {
        ResultSet rst=CrudUtil.execute("SELECT SUM(total) as total,date FROM income WHERE date=CURRENT_DATE");
        double total=0.0;
        if(rst.next()){
            total=rst.getDouble(1);
            //System.out.println("DAo term "+term);
        }
        return total;
    }

    @Override
    public double getYesterdayIncome() throws Exception {
        ResultSet rst=CrudUtil.execute("SELECT SUM(total) as total,date FROM income WHERE date=CURRENT_DATE-INTERVAL 1 DAY");
        double total=0.0;
        if(rst.next()){
            total=rst.getDouble(1);
        }
        return total;
    }

    @Override
    public double getLastMonthIncome() throws Exception {
        /*ResultSet rst=CrudUtil.execute(" SELECT SUM(total) as total,date FROM income GROUP BY MONTH(date) Order by 2 DESC limit 1");
        double total=0.0;
        if(rst.next()){
            total=rst.getDouble(1);
        }
        return total;*/
        System.out.println("****  ");
        PreparedStatement pstm = DBConnection.getInstance().getConnection().prepareStatement(" SELECT SUM(total) as total,date FROM income GROUP BY MONTH(date) Order by 2 DESC limit 1");
        ResultSet rst = pstm.executeQuery();
        double total=0.0;
        if(rst.next()){
            total=rst.getDouble(1);
            System.out.println("total  "+total);
        }
        return total;
    }


}
