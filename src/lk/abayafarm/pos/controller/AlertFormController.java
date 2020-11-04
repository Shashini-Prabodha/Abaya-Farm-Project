package lk.abayafarm.pos.controller;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.scene.control.Label;
import lk.abayafarm.pos.util.Util;
import lk.abayafarm.pos.util.impl.UtilImpl;

public class AlertFormController {
    public JFXButton btnOK;
    public Label lblText;
    Util util = new UtilImpl();

    public void btnOKOnAction(ActionEvent actionEvent) {
        try {
            util.loadUI(btnOK);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
