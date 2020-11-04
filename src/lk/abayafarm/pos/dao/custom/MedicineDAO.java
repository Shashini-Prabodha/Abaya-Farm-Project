package lk.abayafarm.pos.dao.custom;

import lk.abayafarm.pos.dao.CrudDAO;
import lk.abayafarm.pos.entity.FeedDetails;
import lk.abayafarm.pos.entity.Medicine;

import java.sql.SQLException;

public interface MedicineDAO extends CrudDAO<Medicine,String> {
    String getLastMedicineID() throws SQLException, ClassNotFoundException;

    Medicine getMedicineWithName(String medicineName) throws Exception;
}
