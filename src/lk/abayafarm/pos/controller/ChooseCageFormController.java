package lk.abayafarm.pos.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import lk.abayafarm.pos.bo.BoFactory;
import lk.abayafarm.pos.bo.custom.BatchBO;
import lk.abayafarm.pos.bo.custom.CageBO;
import lk.abayafarm.pos.dto.CageDTO;
import lk.abayafarm.pos.dto.SupplierDTO;
import lk.abayafarm.pos.util.Util;
import lk.abayafarm.pos.util.impl.UtilImpl;
import tray.notification.NotificationType;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public class ChooseCageFormController {
    public Label lblMaxHens;
    public JFXComboBox cmbCage;
    public JFXButton btnClose,btnAddCage,btnUseCage;
    public static String CageID;
    public AnchorPane parentPane;


    Util util=new UtilImpl();
    CageBO cageBO= BoFactory.getInstance().getBo(BoFactory.BoType.CAGE);

    public void initialize() {
        setCageId();
        try {
            util.setFadeTransition(parentPane,300);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void setCageId() {
        try {
            List<CageDTO> list = cageBO.getCageIDListExit();
            for (CageDTO cageDTO : list) {
                cmbCage.getItems().addAll(cageDTO.getCageId());
                System.out.println(cageDTO.getCageId());
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }

    }
    public void cmbCageOnAction(ActionEvent actionEvent) throws Exception {
        String s = (String) cmbCage.getValue();
        CageDTO cage = cageBO.getCage(s);
        lblMaxHens.setText(String.valueOf(cage.getMaxQty()));
    }

    public void btnCloseOnAction(ActionEvent actionEvent) {
        try {
            util.loadUI(btnClose);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void btnAddCageOnActon(ActionEvent actionEvent) throws Exception {
        util.loadUI("AddNewCageForm",btnAddCage);
    }
    public void btnUseCageOnAction(ActionEvent actionEvent) throws Exception {
        ButtonType ok=new ButtonType("Yes");
        ButtonType no=new ButtonType("No");
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you Sure get this Cage ?", ok, no);
        Optional<ButtonType> result = alert.showAndWait();
        if (result.orElse(no) == ok) {
            CageID = ((String) cmbCage.getValue());

            //AddBatchFormController.lblCage.setText("dshjkfh");
            util.trayNotification("Get Cage", "Your get Cage " + CageID + " !", NotificationType.SUCCESS);
            util.loadUI(btnUseCage);
        }else{}
    }


}
