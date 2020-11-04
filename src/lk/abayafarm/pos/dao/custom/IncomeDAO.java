package lk.abayafarm.pos.dao.custom;

import lk.abayafarm.pos.dao.CrudDAO;
import lk.abayafarm.pos.entity.FeedDetails;
import lk.abayafarm.pos.entity.Income;

public interface IncomeDAO extends CrudDAO<Income,String> {

    public double getTodayIncome() throws Exception;

    public double getYesterdayIncome() throws Exception;

    public double getLastMonthIncome() throws Exception;
}
