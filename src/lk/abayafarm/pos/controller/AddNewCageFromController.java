package lk.abayafarm.pos.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.util.Duration;
import lk.abayafarm.pos.bo.BoFactory;
import lk.abayafarm.pos.bo.custom.CageBO;
import lk.abayafarm.pos.bo.custom.impl.CageBOImpl;
import lk.abayafarm.pos.dto.CageDTO;
import lk.abayafarm.pos.util.Util;
import lk.abayafarm.pos.util.impl.UtilImpl;
import tray.notification.NotificationType;

import java.awt.*;
import java.util.Optional;
import java.util.regex.Pattern;

public class AddNewCageFromController {
    public Pane logoPane;
    public JFXButton btnClose;
    public Label lblCageID;
    public JFXButton btnAddCage;
    public Pane Panecounter;
    public JFXTextField txtMaxQty;
    public AnchorPane parentPane;

    Util util = new UtilImpl();
    CageBO cageBO = BoFactory.getInstance().getBo(BoFactory.BoType.CAGE);

    public void initialize() {
        try {
            util.rotateAnimation(logoPane);
            genarateCageID();
            util.setFadeTransition(parentPane,300);

//            spinnerMaxBird.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 1000, 0));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void btnCloseOnAction(ActionEvent actionEvent) {
        try {
            util.loadUI(btnClose);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void btnAddCageOnAction(ActionEvent actionEvent) {
        try {
            if (Pattern.compile("^([0-9]{1,})$").matcher(txtMaxQty.getText()).matches()){
                int qty = Integer.parseInt(txtMaxQty.getText());
                if (lblCageID != null && qty > 0) {
                    CageDTO dto = new CageDTO(lblCageID.getText(), "B000", qty, 0);

                    ButtonType ok = new ButtonType("Yes");
                    ButtonType no = new ButtonType("No");

                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure add new cage ?", ok, no);
                    Optional<ButtonType> result = alert.showAndWait();
                    if (result.orElse(no) == ok) {

                        if (cageBO.saveCage(dto)) {
                            util.trayNotification("Add New Cage", "Added New Cage .....! ", NotificationType.SUCCESS);
                            genarateCageID();
                        } else {
                            util.loadUI("AlertForm");
                            util.trayNotification("Add New Cage", "Added Failed .....! ", NotificationType.ERROR);

                        }


                    }
                } else {
                    //new Alert(Alert.AlertType.INFORMATION,"Please enter the number of birds...").show();
                    util.notification("", "Please input max quantity");
                    util.focus(txtMaxQty);
                }
            }else{
                util.notification("", "Please input only numbers for max quantity");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //genarate Cage ID
    public void genarateCageID() {
        try {

            String cageID = cageBO.getLastCageID();
            if (cageID != null) {
                String lastId = cageID.split("[A-Z]")[1];

                if (Integer.parseInt(lastId) == 9) {
                    lblCageID.setText("C010");
                    return;
                } else if (Integer.parseInt(lastId) < 9) {
                    String newID = "C00" + (Integer.parseInt(lastId) + 1);
                    lblCageID.setText(newID);
                } else if (Integer.parseInt(lastId) == 100) {
                    lblCageID.setText("C100");
                } else {
                    String newID = "C0" + (Integer.parseInt(lastId) + 1);
                    lblCageID.setText(newID);
                }
            } else {
                lblCageID.setText("C001");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void subOnAction(ActionEvent actionEvent) {
        int oldVlu = Integer.parseInt(txtMaxQty.getText());
        if (oldVlu == 0)
            return;

        TranslateTransition tt = new TranslateTransition(Duration.millis(400), Panecounter);
        tt.setToX(115);
        tt.play();
        tt.setOnFinished(e -> {

            txtMaxQty.setText((oldVlu - 1) + "");
            TranslateTransition tt2 = new TranslateTransition(Duration.millis(400), Panecounter);
            tt2.setToX(0);
            tt2.play();
        });
    }

    public void plusOnAction(ActionEvent actionEvent) {
        int oldVlu = Integer.parseInt(txtMaxQty.getText());

        TranslateTransition tt = new TranslateTransition(Duration.millis(400), Panecounter);
        tt.setToX(-115);
        tt.play();
        tt.setOnFinished(e -> {
            txtMaxQty.setText((oldVlu + 1) + "");
            TranslateTransition tt2 = new TranslateTransition(Duration.millis(400), Panecounter);
            tt2.setToX(0);
            tt2.play();
        });
    }
}
