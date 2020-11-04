package lk.abayafarm.pos.bo.custom;

import lk.abayafarm.pos.dto.MedicineDTO;
import lk.abayafarm.pos.dto.MedicineDetailsDTO;

public interface UseMedicineBO {
    public boolean useMedicine(MedicineDTO medicineDTO , MedicineDetailsDTO medicineDetailsDTO) throws Exception;

}
