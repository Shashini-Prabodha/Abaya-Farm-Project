package lk.abayafarm.pos.dao.custom;

import lk.abayafarm.pos.dao.CrudDAO;
import lk.abayafarm.pos.entity.Login;

public interface LoginDAO extends CrudDAO<Login,String> {
    public String getRoll(String password,String userName) throws Exception;

}
