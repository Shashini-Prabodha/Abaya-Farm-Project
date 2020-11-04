package lk.abayafarm.pos.bo.custom.impl;

import lk.abayafarm.pos.bo.custom.MedicineBO;
import lk.abayafarm.pos.bo.custom.MedicineDetailsBO;
import lk.abayafarm.pos.bo.custom.UseMedicineBO;
import lk.abayafarm.pos.dao.DaoFactory;
import lk.abayafarm.pos.dao.custom.MedicineDAO;
import lk.abayafarm.pos.dao.custom.MedicineDetailsDAO;
import lk.abayafarm.pos.db.DBConnection;
import lk.abayafarm.pos.dto.MedicineDTO;
import lk.abayafarm.pos.dto.MedicineDetailsDTO;
import lk.abayafarm.pos.entity.Feed;
import lk.abayafarm.pos.entity.FeedDetails;
import lk.abayafarm.pos.entity.Medicine;
import lk.abayafarm.pos.entity.MedicineDetails;

import java.sql.Connection;

public class UseMedicineBOImpl implements UseMedicineBO {
    MedicineDAO medicineDAO= DaoFactory.getInstance().getDao(DaoFactory.DaoType.MEDICINE);
    MedicineDetailsDAO medicineDetailsDAO= DaoFactory.getInstance().getDao(DaoFactory.DaoType.MEDICINEDETAILS);
    @Override
    public boolean useMedicine(MedicineDTO medicineDTO, MedicineDetailsDTO medicineDetailsDTO) throws Exception {
        Connection connection = DBConnection.getInstance().getConnection();

        Medicine medicine=new Medicine(medicineDTO.getMedicineId(),medicineDTO.getMedicineName(),medicineDTO.getQtyOnHand(),medicineDTO.getDescription());
        MedicineDetails medicineDetails = new MedicineDetails(medicineDetailsDTO.getMedicineID(), medicineDetailsDTO.getCageId(), medicineDetailsDTO.getUsedQty(), medicineDetailsDTO.getDate());
        try {
            connection.setAutoCommit(false);
            if (medicineDetailsDAO.save(medicineDetails)) {
                if (medicineDAO.update(medicine)) {
                    connection.commit();
                    return true;
                } else {
                    connection.rollback();
                    return false;
                }

            } else {
                connection.rollback();
                return false;
            }
        } finally {
            connection.setAutoCommit(true);
        }
    }
}
