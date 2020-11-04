package lk.abayafarm.pos.controller;

import com.jfoenix.controls.*;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Paint;
import javafx.util.Duration;
import lk.abayafarm.pos.bo.BoFactory;
import lk.abayafarm.pos.bo.custom.BatchBO;
import lk.abayafarm.pos.bo.custom.CageBO;
import lk.abayafarm.pos.bo.custom.UpdateBatchBO;
import lk.abayafarm.pos.dto.BatchDTO;
import lk.abayafarm.pos.dto.CageDTO;
import lk.abayafarm.pos.util.Util;
import lk.abayafarm.pos.util.impl.UtilImpl;
import tray.notification.NotificationType;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;

public class UpdateBatchFormController {
    public JFXTextField txtBatchID, txtQtyOnHand;
    public JFXRadioButton rbtnChick, rbtnGrower, rbtnLayer;
    public ToggleGroup group;
    public JFXTextArea Description;
    public JFXComboBox cmbCageId;
    public Pane logoPane;
    public JFXButton btnUpdate;
    public Spinner spinnerAvailableQty;
    public Pane Panecounter;
    public Label lblAvilableQty;
    public AnchorPane parentPane;

    BatchBO batchBO = BoFactory.getInstance().getBo(BoFactory.BoType.BATCH);
    CageBO cageBO = BoFactory.getInstance().getBo(BoFactory.BoType.CAGE);
    UpdateBatchBO updateBatchBO = BoFactory.getInstance().getBo(BoFactory.BoType.UPDATEBATCH);
    Util util = new UtilImpl();

    public void initialize() {
        try {
            util.setFadeTransition(parentPane,300);
        } catch (IOException e) {
            e.printStackTrace();
        }
        loadCmb();
    }

    private void loadCmb() {

        try {
            ArrayList<CageDTO> list = cageBO.getAllCage();
            for (CageDTO cageDTO : list) {
                cmbCageId.getItems().addAll(cageDTO.getCageId());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void cmbCageIdOnAction(ActionEvent actionEvent) {
        String cageId = String.valueOf(cmbCageId.getValue());
        try {
            CageDTO cage = cageBO.getCage(cageId);
            txtBatchID.setText(cage.getBatchId());
            BatchDTO batchDTO = batchBO.getBatch(txtBatchID.getText());
            setRadioButton(batchDTO.getType());

            txtQtyOnHand.setText(String.valueOf(cage.getMaxQty()));
           /* spinnerAvailableQty.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 1000, cage.getAvailableQty()));
            spinnerAvailableQty.requestFocus();*/
            lblAvilableQty.setText(String.valueOf(cage.getAvailableQty()));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void btnUpdateOnAction(ActionEvent actionEvent) {
        try {
            if (!cmbCageId.getSelectionModel().isEmpty()) {
            if (Pattern.compile("^([0-9]{1,})$").matcher(txtQtyOnHand.getText()).matches()) {

                BatchDTO batchDTO = new BatchDTO(txtBatchID.getText(), getRadioButonValue(), batchBO.getBatch(txtBatchID.getText()).getQty(), "in");
                CageDTO cageDTO = new CageDTO(String.valueOf(cmbCageId.getValue()), txtBatchID.getText(), Integer.parseInt(txtQtyOnHand.getText()), Integer.parseInt(lblAvilableQty.getText()));

                ButtonType ok = new ButtonType("Yes");
                ButtonType no = new ButtonType("No");
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you Sure Update this Batch ?", ok, no);
                Optional<ButtonType> result = alert.showAndWait();
                if (result.orElse(no) == ok) {
                    if (updateBatchBO.updateBatch(batchDTO, cageDTO)) {
                        util.trayNotification("Update Batch", "Update Success...!", NotificationType.SUCCESS);

                    } else {
                        util.trayNotification("Update Batch", "Update Failed...!", NotificationType.ERROR);
                    }
                } else {
                }
            } else {

                util.notification("Error", "Please Enter Qty On Hand");
                util.focus(txtQtyOnHand);
            }
            } else {
                //util.notification("","ඔබගේ ඇතුලත් කිරීම නොගැලපේ ");
                util.notification("","Please choose cage");
                cmbCageId.setFocusColor(Paint.valueOf("red"));
                cmbCageId.requestFocus();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //get selected radio button values
    private String getRadioButonValue() {
        if (rbtnChick.isSelected()) {
            return "chick";
        } else if (rbtnGrower.isSelected()) {
            return "grower";
        } else {
            return "layer";
        }
    }

    //type wise button selected
    private void setRadioButton(String type) {
        switch (type) {
            case "chick":
                rbtnChick.setSelected(true);
                break;
            case "grower":
                rbtnGrower.setSelected(true);
                break;
            case "layer":
                rbtnLayer.setSelected(true);
                break;
            default:
        }
    }

    public void rbtnOnAction(ActionEvent actionEvent) {

    }

    public void subOnAction(ActionEvent actionEvent) {
        int oldVlu = Integer.parseInt(lblAvilableQty.getText());
        if (oldVlu == 0)
            return;

        TranslateTransition tt = new TranslateTransition(Duration.millis(400), Panecounter);
        tt.setToX(115);
        tt.play();
        tt.setOnFinished(e -> {

            lblAvilableQty.setText((oldVlu - 1) + "");
            TranslateTransition tt2 = new TranslateTransition(Duration.millis(400), Panecounter);
            tt2.setToX(0);
            tt2.play();
        });
    }

    public void plusOnAction(ActionEvent actionEvent) {
        int oldVlu = Integer.parseInt(lblAvilableQty.getText());

        TranslateTransition tt = new TranslateTransition(Duration.millis(400), Panecounter);
        tt.setToX(-115);
        tt.play();
        tt.setOnFinished(e -> {
            lblAvilableQty.setText((oldVlu + 1) + "");
            TranslateTransition tt2 = new TranslateTransition(Duration.millis(400), Panecounter);
            tt2.setToX(0);
            tt2.play();
        });
    }


}
