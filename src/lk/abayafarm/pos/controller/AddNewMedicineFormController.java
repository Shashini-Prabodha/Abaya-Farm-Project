package lk.abayafarm.pos.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Paint;
import lk.abayafarm.pos.bo.BoFactory;
import lk.abayafarm.pos.bo.custom.MedicineBO;
import lk.abayafarm.pos.dto.MedicineDTO;
import lk.abayafarm.pos.util.Util;
import lk.abayafarm.pos.util.impl.UtilImpl;
import tray.notification.NotificationType;

import java.util.Optional;
import java.util.regex.Pattern;

public class AddNewMedicineFormController {
    public JFXTextArea txtDescription;
    public JFXTextField txtMedicineName, txtMedicineID;
    public JFXButton btnAddNewMedicine, btnClose;
    public Pane imgLogo;
    public AnchorPane parentPane;

    Util util = new UtilImpl();
    MedicineBO medicineBO = BoFactory.getInstance().getBo(BoFactory.BoType.MEDICINE);

    public void initialize() {
        try {
            util.rotateAnimation(imgLogo);
            genarateMedicineID();
            util.setFadeTransition(parentPane,300);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void btnAddNewMedicineOnAction(ActionEvent actionEvent) {

        if (Pattern.compile("^[A-z, |0-9:./-]*\\b$").matcher(txtMedicineName.getText()).matches()) {
            if (Pattern.compile("^[A-z, |0-9:./-]*\\b$").matcher(txtDescription.getText()).matches()) {

                MedicineDTO medicineDTO = new MedicineDTO(
                        txtMedicineID.getText(),
                        txtMedicineName.getText(),
                        0,
                        txtDescription.getText()
                );

                ButtonType ok = new ButtonType("Yes");
                ButtonType no = new ButtonType("No");

                Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure add new medicine ?", ok, no);
                Optional<ButtonType> result = alert.showAndWait();
                if (result.orElse(no) == ok) {
                    try {
                        if (medicineBO.saveMedicine(medicineDTO)) {
                            util.trayNotification("Add New Medicine", "Added New Medicine .....! ", NotificationType.SUCCESS);
                            genarateMedicineID();
                        } else {
                            util.trayNotification("Add New Medicine", "Added Failed .....! ", NotificationType.ERROR);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            } else {
                util.trayNotification("", "Please fill description .....! ", NotificationType.ERROR);
                util.focus(txtMedicineName);
            }
        } else {
            util.trayNotification("Add New Medicine", "Please input medicine name .....! ", NotificationType.ERROR);
            util.focus(txtMedicineName);
        }
    }

    public void btnCloseOnAction(ActionEvent actionEvent) {
        try {
            util.loadUI(btnClose);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //set Medicine  ID
    public void genarateMedicineID() {

        try {

            String id = medicineBO.getLastMedicineID();
            String lastId = id.split("[A-Z]")[1];

            if (Integer.parseInt(lastId) == 9) {
                txtMedicineID.setText("M010");
                return;
            } else if (Integer.parseInt(lastId) < 9) {
                String newID = "M00" + (Integer.parseInt(lastId) + 1);
                txtMedicineID.setText(newID);
            } else {
                String newID = "M0" + (Integer.parseInt(lastId) + 1);
                txtMedicineID.setText(newID);
            }


        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
