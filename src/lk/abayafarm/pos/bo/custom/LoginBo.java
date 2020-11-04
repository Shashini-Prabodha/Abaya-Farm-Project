package lk.abayafarm.pos.bo.custom;

import lk.abayafarm.pos.dto.LoginDTO;

import java.util.ArrayList;

public interface LoginBo {
    public boolean saveLogin(LoginDTO dto)throws Exception;
    public boolean updateLogin(LoginDTO dto)throws Exception;
    public boolean deleteLogin(String id) throws Exception;
    public LoginDTO getLogin(String id) throws Exception;
    public ArrayList<LoginDTO> getAllLogin() throws Exception;
    public String getRoll(String password, String userName) throws Exception;
}
