package lk.abayafarm.pos.dao.custom.impl;

import lk.abayafarm.pos.dao.CrudUtil;
import lk.abayafarm.pos.dao.custom.MedicineDAO;
import lk.abayafarm.pos.entity.Income;
import lk.abayafarm.pos.entity.Medicine;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MedicineDAOImpl implements MedicineDAO {
    @Override
    public boolean save(Medicine medicine) throws Exception {
        return CrudUtil.execute("INSERT INTO medicine VALUES(?,?,?,?)",medicine.getMedicineId(),medicine.getMedicineName(),medicine.getQtyOnHand(),medicine.getDescription());
    }

    @Override
    public boolean update(Medicine medicine) throws Exception {
        return CrudUtil.execute("UPDATE medicine SET medicineName=?, qtyOnHand=?, description=? WHERE medicineId=?",medicine.getMedicineName(),medicine.getQtyOnHand(),medicine.getDescription(),medicine.getMedicineId());
    }

    @Override
    public boolean delete(String s) throws Exception {
        return CrudUtil.execute("DELETE FROM medicine WHERE medicineId=?", s);
    }

    @Override
    public Medicine get(String s) throws Exception {
        ResultSet rst = CrudUtil.execute("SELECT * FROM medicine WHERE medicineId=?", s);
        if (rst.next()) {
            return new Medicine(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getInt(3),
                    rst.getString(4)
            );
        }
        return null;
    }

    @Override
    public List<Medicine> getAll() throws Exception {
        ResultSet rst = CrudUtil.execute("SELECT * FROM medicine");
        ArrayList<Medicine> list = new ArrayList<>();
        while (rst.next()) {
            list.add(
                    new Medicine(
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
    public String getLastMedicineID() throws SQLException, ClassNotFoundException {
        ResultSet rst=CrudUtil.execute("SELECT medicineId FROM medicine ORDER BY medicineId DESC LIMIT 1");
        String id="M000";
        if(rst.next()){
            id=rst.getString(1);

        }
        System.out.println(id);
        return id;
    }

    @Override
    public Medicine getMedicineWithName(String medicineName) throws Exception {
        ResultSet rst = CrudUtil.execute("SELECT * FROM medicine WHERE medicineName=?", medicineName);
        if (rst.next()) {
            return new Medicine(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getInt(3),
                    rst.getString(4)
            );
        }
        return null;
    }
}
