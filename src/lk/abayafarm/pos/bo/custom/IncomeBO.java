package lk.abayafarm.pos.bo.custom;

import lk.abayafarm.pos.dao.CrudDAO;
import lk.abayafarm.pos.dto.EggDTO;
import lk.abayafarm.pos.dto.IncomeDTO;
import lk.abayafarm.pos.entity.Income;

import java.util.ArrayList;

public interface IncomeBO {
    public boolean saveIncome(IncomeDTO dto) throws Exception;

    public boolean updateIncome(IncomeDTO dto) throws Exception;

    public boolean deleteIncome(String id) throws Exception;

    public IncomeDTO getIncome(String id) throws Exception;

    public ArrayList<IncomeDTO> getAllIncome() throws Exception;

    public double getTodayIncome() throws Exception;

    public double getYesterdayIncome() throws Exception;

    public double getLastMonthIncome() throws Exception;

}
