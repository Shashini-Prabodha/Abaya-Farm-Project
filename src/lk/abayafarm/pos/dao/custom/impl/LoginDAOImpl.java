package lk.abayafarm.pos.dao.custom.impl;

import lk.abayafarm.pos.dao.CrudUtil;
import lk.abayafarm.pos.dao.custom.LoginDAO;
import lk.abayafarm.pos.entity.Login;
import lk.abayafarm.pos.entity.Manure;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class LoginDAOImpl implements LoginDAO {
    @Override
    public boolean save(Login login) throws Exception {
        return CrudUtil.execute("INSERT INTO login VALUES(?,?,?)", login.getUserName(), login.getPassword(), login.getRoll());
    }

    @Override
    public boolean update(Login login) throws Exception {
        return CrudUtil.execute("UPDATE login SET userName=?, roll=? WHERE  password=?", login.getUserName(), login.getRoll(), login.getPassword());
    }

    @Override
    public boolean delete(String s) throws Exception {
        return CrudUtil.execute("DELETE FROM login WHERE password=?", s);
    }

    @Override
    public Login get(String s) throws Exception {
        ResultSet rst = CrudUtil.execute("SELECT * FROM login WHERE password=?", s);
        if (rst.next()) {
            return new Login(rst.getString(1),
                    rst.getString(2),
                    rst.getString(3)
            );
        }
        return null;
    }

    @Override
    public List<Login> getAll() throws Exception {
        ResultSet rst = CrudUtil.execute("SELECT * FROM login");
        ArrayList<Login> list = new ArrayList<>();
        while (rst.next()) {
            list.add(
                    new Login(rst.getString(1),
                            rst.getString(2),
                            rst.getString(3)
                    ));

        }
        return list;
    }

    @Override
    public String getRoll(String password, String userName) throws Exception {
        ResultSet rst = CrudUtil.execute("SELECT roll FROM login WHERE password=? and userName=?",password,userName);
        String roll=null;
        if (rst.next()) {
            roll=rst.getString(1);
            System.out.println("dao in "+roll);

        }
        System.out.println("dao "+roll);
        return roll;
    }
}
