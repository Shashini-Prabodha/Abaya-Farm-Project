package lk.abayafarm.pos.dao.custom;

import lk.abayafarm.pos.dao.CrudDAO;
import lk.abayafarm.pos.entity.FeedDetails;
import lk.abayafarm.pos.entity.Medicine;
import lk.abayafarm.pos.entity.MedicineDetails;

public interface MedicineDetailsDAO extends CrudDAO<MedicineDetails,String> {
    public boolean delete(MedicineDetails details) throws Exception;
    public MedicineDetails get(String s1, String s2, String s3) throws Exception;
}
