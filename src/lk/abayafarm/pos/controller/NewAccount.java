package lk.abayafarm.pos.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.AnchorPane;
import lk.abayafarm.pos.bo.BoFactory;
import lk.abayafarm.pos.bo.custom.LoginBo;
import lk.abayafarm.pos.dto.LoginDTO;
import lk.abayafarm.pos.util.Util;
import lk.abayafarm.pos.util.impl.UtilImpl;

import java.io.IOException;
import java.util.Base64;
import java.util.regex.Pattern;

public class NewAccount {

    public JFXTextField txtUserName;
    public JFXPasswordField txtPassword;
    public JFXButton btnSave;
    public ToggleGroup group;
    public JFXRadioButton rbtnCasheir, rbtnAdmin;
    public AnchorPane parentPane;
    public JFXButton btnClose;

    Util util = new UtilImpl();
    LoginBo loginBo = BoFactory.getInstance().getBo(BoFactory.BoType.LOGIN);

    public void initialize(){
        try {
            util.setFadeTransition(parentPane,300);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static String getEncodedString(String password) {
        return Base64.getEncoder().encodeToString(password.getBytes());
    }

    public void btnSaveOnAction(ActionEvent actionEvent) {
        try {
            if (Pattern.compile("^[A-z ]{1,}$").matcher(txtUserName.getText()).matches()) {
                if (!txtPassword.getText().isEmpty()) {
                    if (rbtnAdmin.isSelected() || rbtnCasheir.isSelected()) {
                        LoginDTO loginDTO = new LoginDTO(txtUserName.getText(), getEncodedString(txtPassword.getText()),getSelectedRoll());
                        loginBo.saveLogin(loginDTO);
                        System.out.println(loginDTO.getPassword());
                    }else{
                        util.notification("", "Please select roll ");

                    }

                } else {
                    util.notification("", "Please enter password ");
                }

            } else {
                util.notification("", "Use Only letters! Please Use this Format. Name : abcd efg ");

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    String getSelectedRoll() {
        if (rbtnAdmin.isSelected()) {
            return "admin";
        } else {
            return "casheir";
        }
    }

    public void btnCloseOnAction(ActionEvent actionEvent) {
        try {
            util.loadUI(btnClose);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
