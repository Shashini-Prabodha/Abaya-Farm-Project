package lk.abayafarm.pos.bo.custom.impl;

import lk.abayafarm.pos.bo.BoFactory;
import lk.abayafarm.pos.bo.custom.LoginBo;
import lk.abayafarm.pos.dao.DaoFactory;
import lk.abayafarm.pos.dao.custom.LoginDAO;
import lk.abayafarm.pos.dto.LoginDTO;
import lk.abayafarm.pos.dto.ManureDTO;
import lk.abayafarm.pos.entity.Login;
import lk.abayafarm.pos.entity.Manure;

import java.util.ArrayList;
import java.util.List;


public class LoginBOImpl implements LoginBo {

LoginDAO loginDAO= DaoFactory.getInstance().getDao(DaoFactory.DaoType.LOGIN);
    @Override
    public boolean saveLogin(LoginDTO dto) throws Exception {
        return loginDAO.save(new Login(dto.getUserName(),dto.getPassword(),dto.getRoll()));
    }

    @Override
    public boolean updateLogin(LoginDTO dto) throws Exception {
        return loginDAO.update(new Login(dto.getUserName(),dto.getPassword(),dto.getRoll()));
    }

    @Override
    public boolean deleteLogin(String id) throws Exception {
        return loginDAO.delete(id);
    }

    @Override
    public LoginDTO getLogin(String id) throws Exception {
        Login login = loginDAO.get(id);
        return new LoginDTO(login.getUserName(),login.getPassword(),login.getRoll());
    }

    @Override
    public ArrayList<LoginDTO> getAllLogin() throws Exception {
        List<Login> list = loginDAO.getAll();
        ArrayList<LoginDTO> arrayList = new ArrayList<>();
        for (Login login : list) {
            arrayList.add(new LoginDTO(login.getUserName(),login.getPassword(),login.getRoll()));
        }
        return arrayList;
    }

    @Override
    public String getRoll(String password, String userName) throws Exception {
        return loginDAO.getRoll(password, userName);
    }
}
