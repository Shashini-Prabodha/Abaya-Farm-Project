package lk.abayafarm.pos.bo.custom.impl;

import lk.abayafarm.pos.bo.custom.EggBO;
import lk.abayafarm.pos.dao.DaoFactory;
import lk.abayafarm.pos.dao.custom.EggDAO;
import lk.abayafarm.pos.dto.EggDTO;
import lk.abayafarm.pos.entity.Egg;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class EggBOImpl implements EggBO {
    private EggDAO eggDAO= DaoFactory.getInstance().getDao(DaoFactory.DaoType.EGG);
    @Override
    public boolean saveEgg(EggDTO dto) throws Exception {
        return eggDAO.save(new Egg(dto.getCageId(),dto.getStoreId(),dto.getType(),dto.getQty(),dto.getTerm(),dto.getDamage(),dto.getDate()));
    }

    @Override
    public boolean updateEgg(EggDTO dto) throws Exception {
        return eggDAO.update(new Egg(dto.getCageId(),dto.getStoreId(),dto.getType(),dto.getQty(),dto.getTerm(),dto.getDamage(),dto.getDate()));
    }

    @Override
    public boolean deleteEgg(String id) throws Exception {
        return eggDAO.delete(id);
    }

    @Override
    public EggDTO getEgg(String id) throws Exception {
        Egg egg=eggDAO.get(id);
        return new EggDTO(egg.getCageId(),egg.getStoreId(),egg.getType(),egg.getQty(),egg.getTerm(),egg.getDamage(),egg.getDate());
    }

    @Override
    public ArrayList<EggDTO> getAllEgg() throws Exception {
        List<Egg> list = eggDAO.getAll();
        ArrayList<EggDTO> arrayList=new ArrayList<>();
        for (Egg egg : list) {
            arrayList.add(new EggDTO(egg.getCageId(),egg.getStoreId(),egg.getType(),egg.getQty(),egg.getTerm(),egg.getDamage(),egg.getDate()));
        }
        return arrayList;
    }

    @Override
    public int getLastTerm(String cageId, Date date) throws Exception {
        return eggDAO.getLastTerm(cageId,date);
    }

    @Override
    public List<Integer> getEggsinWeek(String type) throws Exception {
        List<Integer> list = eggDAO.getEggsinWeek(type);
        return list;
    }


}
