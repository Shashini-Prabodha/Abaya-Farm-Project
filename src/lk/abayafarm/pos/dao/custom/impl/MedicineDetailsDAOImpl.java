package lk.abayafarm.pos.dao.custom.impl;

import lk.abayafarm.pos.dao.CrudUtil;
import lk.abayafarm.pos.dao.custom.MedicineDAO;
import lk.abayafarm.pos.dao.custom.MedicineDetailsDAO;
import lk.abayafarm.pos.entity.FeedDetails;
import lk.abayafarm.pos.entity.Income;
import lk.abayafarm.pos.entity.Medicine;
import lk.abayafarm.pos.entity.MedicineDetails;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class MedicineDetailsDAOImpl implements MedicineDetailsDAO {

    @Override
    public boolean save(MedicineDetails medicineDetails) throws Exception {
        return CrudUtil.execute("INSERT INTO medicineDetails VALUES(?,?,?,?)", medicineDetails.getMedicineID(),medicineDetails.getCageId(), medicineDetails.getUsedQty(), medicineDetails.getDate());
    }

    @Override
    public boolean update(MedicineDetails medicineDetails) throws Exception {
        return CrudUtil.execute("UPDATE medicineDetails SET usedQty=? WHERE medicineId=? and cageId=? and date=?", medicineDetails.getUsedQty(), medicineDetails.getMedicineID(),medicineDetails.getCageId(), medicineDetails.getDate());
    }

    @Override
    public boolean delete(String s) throws Exception {
        return false;
    }

    @Override
    public MedicineDetails get(String s) throws Exception {

        return null;
    }

    @Override
    public List<MedicineDetails> getAll() throws Exception {
        ResultSet rst = CrudUtil.execute("SELECT * FROM medicineDetails");
        ArrayList<MedicineDetails> list = new ArrayList<>();
        while (rst.next()) {
            list.add(
                    new MedicineDetails(
                            rst.getString(1),
                            rst.getString(2),
                            rst.getInt(3),
                            rst.getDate(4)
                    )
            );
        }
        return list;
    }

    @Override
    public boolean delete(MedicineDetails details) throws Exception {
        return CrudUtil.execute("DELETE FROM medicineDetails WHERE medicineId=? and cageId=? and date=?", details.getMedicineID(),details.getCageId(),details.getDate());
    }

    @Override
    public MedicineDetails get(String s1, String s2, String s3) throws Exception {
        ResultSet rst = CrudUtil.execute("SELECT * FROM medicineDetails WHERE medicineId=? and cageId=? and date=?",s1,s2,s3);
        if (rst.next()) {
            return new MedicineDetails(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getInt(3),
                    rst.getDate(4)
            );
        }
        return null;
    }
}
