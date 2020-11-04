package lk.abayafarm.pos.bo.custom.impl;

import lk.abayafarm.pos.bo.custom.CageBO;
import lk.abayafarm.pos.dao.DaoFactory;
import lk.abayafarm.pos.dao.QueryDAO;
import lk.abayafarm.pos.dao.custom.CageDAO;
import lk.abayafarm.pos.dto.CageDTO;
import lk.abayafarm.pos.entity.Cage;

import java.util.ArrayList;
import java.util.List;

public class CageBOImpl implements CageBO {
    private QueryDAO queryDAO = DaoFactory.getInstance().getDao(DaoFactory.DaoType.QUERY);
    private CageDAO dao = DaoFactory.getInstance().getDao(DaoFactory.DaoType.CAGE);

    @Override
    public boolean saveCage(CageDTO dto) throws Exception {
        return dao.save(new Cage(dto.getCageId(), dto.getBatchId(), dto.getMaxQty(), dto.getAvailableQty()));
    }

    @Override
    public boolean updateCage(CageDTO dto) throws Exception {
        return dao.update(new Cage(dto.getCageId(), dto.getBatchId(), dto.getMaxQty(), dto.getAvailableQty()));
    }

    @Override
    public boolean deleteCage(String id) throws Exception {
        return dao.delete(id);
    }

    @Override
    public CageDTO getCage(String id) throws Exception {
        Cage cage = dao.get(id);
        return new CageDTO(cage.getCageId(), cage.getBatchId(), cage.getMaxQty(), cage.getAvailableQty());
    }

    @Override
    public ArrayList<CageDTO> getAllCage() throws Exception {
        List<Cage> list = dao.getAll();
        ArrayList<CageDTO> dtoList = new ArrayList<>();
        for (Cage cage : list) {
            dtoList.add(new CageDTO(cage.getCageId(), cage.getBatchId(), cage.getMaxQty(), cage.getAvailableQty()));
        }
        return dtoList;
    }

    @Override
    public List<CageDTO> getCageIDListInLayer() throws Exception {
        List<Cage> list = queryDAO.getCageIDInLayer();
        ArrayList<CageDTO> dtoList = new ArrayList<>();
        for (Cage cage : list) {
            dtoList.add(new CageDTO(cage.getCageId(), cage.getBatchId(), cage.getMaxQty(), cage.getAvailableQty()));
        }
        return dtoList;
    }

    @Override
    public List<CageDTO> getCageIDListExit() throws Exception {
        List<Cage> list = dao.getCageIDListExit();
        ArrayList<CageDTO> dtoList = new ArrayList<>();
        for (Cage cage : list) {
            dtoList.add(new CageDTO(cage.getCageId(), cage.getBatchId(), cage.getMaxQty(), cage.getAvailableQty()));
        }
        return dtoList;
    }

    @Override
    public String getLastCageID() throws Exception {
        return dao.getLastCageID();
    }
    public List<CageDTO> getCageIDListIn() throws Exception {
        List<Cage> list = dao.getCageIDListIn();
        ArrayList<CageDTO> dtoList = new ArrayList<>();
        for (Cage cage : list) {
            dtoList.add(new CageDTO(cage.getCageId(), cage.getBatchId(), cage.getMaxQty(), cage.getAvailableQty()));
        }
        return dtoList;
    }
}
