package lk.abayafarm.pos.bo.custom;

import lk.abayafarm.pos.dao.CrudDAO;
import lk.abayafarm.pos.dto.EggDTO;
import lk.abayafarm.pos.dto.MedicineDTO;
import lk.abayafarm.pos.entity.Medicine;

import java.sql.SQLException;
import java.util.ArrayList;

public interface MedicineBO{
    public boolean saveMedicine(MedicineDTO dto)throws Exception;
    public boolean updateMedicine(MedicineDTO dto)throws Exception;
    public boolean deleteMedicine(String id) throws Exception;
    public MedicineDTO getMedicine(String id) throws Exception;
    public ArrayList<MedicineDTO> getAllMedicine() throws Exception;

    String getLastMedicineID() throws SQLException, ClassNotFoundException;

    MedicineDTO getMedicineWithName(String medicineName) throws Exception;
}
