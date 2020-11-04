package lk.abayafarm.pos.bo.custom.impl;

import lk.abayafarm.pos.bo.custom.AddItemToStore;
import lk.abayafarm.pos.dao.DaoFactory;
import lk.abayafarm.pos.dao.custom.EggDAO;
import lk.abayafarm.pos.dao.custom.ManureDAO;
import lk.abayafarm.pos.dao.custom.StoreDAO;
import lk.abayafarm.pos.dao.custom.impl.EggDAOImpl;
import lk.abayafarm.pos.dao.custom.impl.StoreDAOImpl;
import lk.abayafarm.pos.db.DBConnection;
import lk.abayafarm.pos.dto.EggDTO;
import lk.abayafarm.pos.dto.ManureDTO;
import lk.abayafarm.pos.entity.Egg;
import lk.abayafarm.pos.entity.Manure;
import lk.abayafarm.pos.util.Util;
import lk.abayafarm.pos.util.impl.UtilImpl;

import java.sql.Connection;

public class AddItemtoStoreBOImpl implements AddItemToStore {
    EggDAO eggDAO = DaoFactory.getInstance().getDao(DaoFactory.DaoType.EGG);
    ManureDAO manureDAO = DaoFactory.getInstance().getDao(DaoFactory.DaoType.MANURE);
    StoreDAO storeDAO = DaoFactory.getInstance().getDao(DaoFactory.DaoType.STORE);

    @Override
    public boolean addEggstoStore(EggDTO egg, String sid, int qty) throws Exception {
        Connection connection = DBConnection.getInstance().getConnection();
        try {
            connection.setAutoCommit(false);
            Egg e = new Egg(egg.getCageId(), egg.getStoreId(), egg.getType(), egg.getQty(), egg.getTerm(), egg.getDamage(), egg.getDate());
            if (eggDAO.save(e)) {
                if (storeDAO.addItem(sid, qty)) {
                    connection.commit();
                    return true;
                } else {
                    connection.rollback();
                }
            } else {
                connection.rollback();
            }
        } finally {
            connection.setAutoCommit(true);
        }
        return false;
    }

    @Override
    public boolean addManuretoStore(ManureDTO manureDTO, String sid, int qty) throws Exception {
        Connection connection = DBConnection.getInstance().getConnection();
        try {
            connection.setAutoCommit(false);
            Manure e = new Manure(manureDTO.getCageId(),manureDTO.getPackSize(),manureDTO.getQty(),manureDTO.getUnitPrice(),manureDTO.getDate());
            if (manureDAO.save(e)) {
                if (storeDAO.addItem(sid,qty)) {
                    connection.commit();
                    return true;
                } else {
                    connection.rollback();
                }
            } else {
                connection.rollback();
            }
        } finally {
            connection.setAutoCommit(true);
        }
        return false;
    }
}
