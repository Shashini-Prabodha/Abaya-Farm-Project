package lk.abayafarm.pos.bo.custom.impl;

import lk.abayafarm.pos.bo.custom.IncomeBO;
import lk.abayafarm.pos.dao.DaoFactory;
import lk.abayafarm.pos.dao.custom.IncomeDAO;
import lk.abayafarm.pos.dto.FeedDetailsDTO;
import lk.abayafarm.pos.dto.IncomeDTO;
import lk.abayafarm.pos.entity.FeedDetails;
import lk.abayafarm.pos.entity.Income;

import java.util.ArrayList;
import java.util.List;

public class IncomeBOImpl implements IncomeBO {
    IncomeDAO incomeDAO= DaoFactory.getInstance().getDao(DaoFactory.DaoType.INCOME);

    @Override
    public boolean saveIncome(IncomeDTO dto) throws Exception {
        return incomeDAO.save(new Income(dto.getOrderId(),dto.getDate(),dto.getTotal()));
    }

    @Override
    public boolean updateIncome(IncomeDTO dto) throws Exception {
        return incomeDAO.update(new Income(dto.getOrderId(),dto.getDate(),dto.getTotal()));
    }

    @Override
    public boolean deleteIncome(String id) throws Exception {
        return incomeDAO.delete(id);
    }

    @Override
    public IncomeDTO getIncome(String id) throws Exception {
        Income income=incomeDAO.get(id);
        return new IncomeDTO(income.getOrderId(),income.getDate(),income.getTotal());
    }

    @Override
    public ArrayList<IncomeDTO> getAllIncome() throws Exception {
        List<Income> list = incomeDAO.getAll();
        ArrayList<IncomeDTO> arrayList = new ArrayList<>();
        for (Income income : list) {
            arrayList.add(new IncomeDTO(income.getOrderId(),income.getDate(),income.getTotal()));
        }
        return arrayList;
    }
    @Override
    public double getTodayIncome() throws Exception {
        return incomeDAO.getTodayIncome();
    }

    @Override
    public double getYesterdayIncome() throws Exception {
        return incomeDAO.getYesterdayIncome();
    }

    @Override
    public double getLastMonthIncome() throws Exception {
        return incomeDAO.getLastMonthIncome();
    }


}
