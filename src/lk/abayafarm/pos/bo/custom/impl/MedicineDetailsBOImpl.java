package lk.abayafarm.pos.bo.custom.impl;

import lk.abayafarm.pos.bo.custom.MedicineDetailsBO;
import lk.abayafarm.pos.dao.DaoFactory;
import lk.abayafarm.pos.dao.custom.MedicineDAO;
import lk.abayafarm.pos.dao.custom.MedicineDetailsDAO;
import lk.abayafarm.pos.dto.FeedDetailsDTO;
import lk.abayafarm.pos.dto.MedicineDetailsDTO;
import lk.abayafarm.pos.entity.FeedDetails;
import lk.abayafarm.pos.entity.Medicine;
import lk.abayafarm.pos.entity.MedicineDetails;

import java.util.ArrayList;
import java.util.List;

public class MedicineDetailsBOImpl implements MedicineDetailsBO {

   MedicineDetailsDAO medicineDetailsDAO= DaoFactory.getInstance().getDao(DaoFactory.DaoType.MEDICINEDETAILS);

    @Override
    public boolean saveMedicineDetails(MedicineDetailsDTO dto) throws Exception {
        return medicineDetailsDAO.save(new MedicineDetails(dto.getMedicineID(),dto.getCageId(),dto.getUsedQty(),dto.getDate()));
    }

    @Override
    public boolean updateMedicineDetails(MedicineDetailsDTO dto) throws Exception {
        return medicineDetailsDAO.update(new MedicineDetails(dto.getMedicineID(),dto.getCageId(),dto.getUsedQty(),dto.getDate()));
    }

    @Override
    public boolean deleteMedicineDetails(String id) throws Exception {
        return false;
    }

    @Override
    public boolean deleteMedicineDetails(MedicineDetailsDTO dto) throws Exception {
        return medicineDetailsDAO.delete(new MedicineDetails(dto.getMedicineID(),dto.getCageId(),dto.getUsedQty(),dto.getDate()));
    }

    @Override
    public MedicineDetailsDTO getMedicineDetails(String s1, String s2, String s3) throws Exception {
        MedicineDetails details=medicineDetailsDAO.get(s1,s2,s3);
        return new MedicineDetailsDTO(details.getMedicineID(),details.getCageId(),details.getUsedQty(),details.getDate());
    }

    @Override
    public MedicineDetailsDTO getMedicineDetails(String id) throws Exception {
        return null;
    }

    @Override
    public ArrayList<MedicineDetailsDTO> getAllMedicineDetails() throws Exception {
        List<MedicineDetails> list = medicineDetailsDAO.getAll();
        ArrayList<MedicineDetailsDTO> arrayList = new ArrayList<>();
        for (MedicineDetails details : list) {
            arrayList.add(new MedicineDetailsDTO(details.getMedicineID(),details.getCageId(),details.getUsedQty(),details.getDate()));
        }
        return arrayList;
    }
}
