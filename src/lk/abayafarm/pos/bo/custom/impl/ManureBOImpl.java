package lk.abayafarm.pos.bo.custom.impl;

import lk.abayafarm.pos.bo.custom.ManureBO;
import lk.abayafarm.pos.dao.DaoFactory;
import lk.abayafarm.pos.dao.custom.ManureDAO;
import lk.abayafarm.pos.dto.CustomerDTO;
import lk.abayafarm.pos.dto.ManureDTO;
import lk.abayafarm.pos.entity.Customer;
import lk.abayafarm.pos.entity.Manure;

import java.util.ArrayList;
import java.util.List;

public class ManureBOImpl implements ManureBO {

    ManureDAO manureDAO= DaoFactory.getInstance().getDao(DaoFactory.DaoType.MANURE);

    @Override
    public boolean saveManure(ManureDTO dto) throws Exception {
        return manureDAO.save(new Manure(dto.getCageId(),dto.getPackSize(),dto.getQty(),dto.getUnitPrice(),dto.getDate()));
    }

    @Override
    public boolean updateManure(ManureDTO dto) throws Exception {
        return manureDAO.update(new Manure(dto.getCageId(),dto.getPackSize(),dto.getQty(),dto.getUnitPrice(),dto.getDate()));
    }

    @Override
    public boolean deleteManure(String id) throws Exception {
        return manureDAO.delete(id);
    }

    @Override
    public ManureDTO getManure(String id) throws Exception {
        Manure manure=manureDAO.get(id);
        return new ManureDTO(manure.getCageId(),manure.getPackSize(),manure.getQty(),manure.getUnitPrice(),manure.getDate());
    }

    @Override
    public ArrayList<ManureDTO> getAllManure() throws Exception {
        List<Manure> list = manureDAO.getAll();
        ArrayList<ManureDTO> arrayList = new ArrayList<>();
        for (Manure manure : list) {
            arrayList.add(new ManureDTO(manure.getCageId(),manure.getPackSize(),manure.getQty(),manure.getUnitPrice(),manure.getDate()));
        }
        return arrayList;
    }
}
