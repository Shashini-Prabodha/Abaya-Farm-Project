package lk.abayafarm.pos.bo.custom;

import lk.abayafarm.pos.dao.CrudDAO;
import lk.abayafarm.pos.dto.EggDTO;
import lk.abayafarm.pos.dto.MedicineDetailsDTO;
import lk.abayafarm.pos.entity.FeedDetails;
import lk.abayafarm.pos.entity.MedicineDetails;

import java.util.ArrayList;

public interface MedicineDetailsBO{
    public boolean saveMedicineDetails(MedicineDetailsDTO dto)throws Exception;
    public boolean updateMedicineDetails(MedicineDetailsDTO dto)throws Exception;
    public boolean deleteMedicineDetails(String id) throws Exception;
    public boolean deleteMedicineDetails(MedicineDetailsDTO dto) throws Exception;
    public MedicineDetailsDTO getMedicineDetails(String s1, String s2, String s3) throws Exception;
    public MedicineDetailsDTO getMedicineDetails(String id) throws Exception;
    public ArrayList<MedicineDetailsDTO> getAllMedicineDetails() throws Exception;
}
