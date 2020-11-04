package lk.abayafarm.pos.bo.custom.impl;

import lk.abayafarm.pos.bo.custom.MedicineBO;
import lk.abayafarm.pos.dao.DaoFactory;
import lk.abayafarm.pos.dao.custom.MedicineDAO;
import lk.abayafarm.pos.dto.BatchDTO;
import lk.abayafarm.pos.dto.MedicineDTO;
import lk.abayafarm.pos.entity.Batch;
import lk.abayafarm.pos.entity.Medicine;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MedicineBOImpl implements MedicineBO {

    MedicineDAO medicineDAO= DaoFactory.getInstance().getDao(DaoFactory.DaoType.MEDICINE);

    @Override
    public boolean saveMedicine(MedicineDTO dto) throws Exception {
        return medicineDAO.save(new Medicine(dto.getMedicineId(),dto.getMedicineName(),dto.getQtyOnHand(),dto.getDescription()));
    }

    @Override
    public boolean updateMedicine(MedicineDTO dto) throws Exception {
        return medicineDAO.update(new Medicine(dto.getMedicineId(),dto.getMedicineName(),dto.getQtyOnHand(),dto.getDescription()));
    }

    @Override
    public boolean deleteMedicine(String id) throws Exception {
        return medicineDAO.delete(id);
    }

    @Override
    public MedicineDTO getMedicine(String id) throws Exception {
        Medicine medicine = medicineDAO.get(id);
        return new MedicineDTO(medicine.getMedicineId(),medicine.getMedicineName(),medicine.getQtyOnHand(),medicine.getDescription());
    }

    @Override
    public ArrayList<MedicineDTO> getAllMedicine() throws Exception {
        List<Medicine> list = medicineDAO.getAll();
        ArrayList<MedicineDTO> arrayList = new ArrayList();
        for (Medicine medicine : list) {
            arrayList.add(new MedicineDTO(
                    medicine.getMedicineId(),
                    medicine.getMedicineName(),
                    medicine.getQtyOnHand(),
                    medicine.getDescription())
            );
        }
        return arrayList;
    }

    @Override
    public String getLastMedicineID() throws SQLException, ClassNotFoundException {
        return medicineDAO.getLastMedicineID();
    }

    @Override
    public MedicineDTO getMedicineWithName(String medicineName) throws Exception{
        Medicine medicine = medicineDAO.getMedicineWithName(medicineName);
        return new MedicineDTO(medicine.getMedicineId(),medicine.getMedicineName(),medicine.getQtyOnHand(),medicine.getDescription());

    }
}
