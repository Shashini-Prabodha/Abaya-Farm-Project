package lk.abayafarm.pos.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import javafx.animation.TranslateTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Paint;
import javafx.util.Duration;
import lk.abayafarm.pos.bo.BoFactory;
import lk.abayafarm.pos.bo.custom.CageBO;
import lk.abayafarm.pos.dto.CageDTO;
import lk.abayafarm.pos.dto.SupplierDTO;
import lk.abayafarm.pos.util.Util;
import lk.abayafarm.pos.util.impl.UtilImpl;
import lk.abayafarm.pos.view.TM.CageTM;
import tray.notification.NotificationType;

import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;

public class CageFormController {
    public JFXButton btnMin, btnMax, btnHome, btnUpdate;
    public JFXComboBox cmbCageId;
    public Label lblBatch;
    public TextField txtMax;
    public ImageView imgAdmin;
    public TableView<CageTM> tblCage;
    public TableColumn colCageId, colBatchId, colMaxQty, colAvailableQty, colDelete;
    public Pane logoPane;
    public JFXButton btnAddNew;
    public Pane Panecounter;
    public Label lblAvilableQty;
    public AnchorPane parentPane;

    Util util = new UtilImpl();
    CageBO cageBO = BoFactory.getInstance().getBo(BoFactory.BoType.CAGE);

    public void initialize() {
        try {
            util.rotateAnimation(imgAdmin);
            loadAllCage();
            loadCmbCageID();

            colCageId.setCellValueFactory(new PropertyValueFactory<>("cageId"));
            colBatchId.setCellValueFactory(new PropertyValueFactory<>("batchId"));
            colMaxQty.setCellValueFactory(new PropertyValueFactory<>("maxQty"));
            colAvailableQty.setCellValueFactory(new PropertyValueFactory<>("availableQty"));

            tblCage.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
                setData(newValue);
            });
            util.setFadeTransition(parentPane,300);


        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    private void setData(CageTM tm) {
        try {
            txtMax.setText(String.valueOf(tm.getMaxQty()));
            lblBatch.setText(tm.getBatchId());
            lblAvilableQty.setText(String.valueOf(tm.getAvailableQty()));
            cmbCageId.setValue(tm.getCageId());
        } catch (NullPointerException e) {

        }
    }

    private void loadAllCage() {
        ObservableList<CageTM> list = FXCollections.observableArrayList();
        try {
            List<CageDTO> dtos = cageBO.getAllCage();
            for (CageDTO dto : dtos) {
                CageTM cageTM = new CageTM(dto.getCageId(), dto.getBatchId(), dto.getMaxQty(), dto.getAvailableQty());
                list.add(cageTM);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        tblCage.setItems(list);
    }

    public void btnHomeOnAction(ActionEvent actionEvent) throws Exception {
        util.loadUI("AdminDashBoardForm", btnHome);
    }

    private void loadCmbCageID() {
        cmbCageId.getItems().clear();
        try {
            List<CageDTO> list = cageBO.getAllCage();
            for (CageDTO dto : list) {
                cmbCageId.getItems().add(dto.getCageId());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void cmbCageIdOnAction(ActionEvent actionEvent) {
        String cageIdValue = (String) cmbCageId.getValue();
        try {
            CageDTO cage = cageBO.getCage(cageIdValue);
            lblBatch.setText(cage.getBatchId());
            lblAvilableQty.setText(String.valueOf(cage.getAvailableQty()));
            txtMax.setText(String.valueOf(cage.getMaxQty()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void btnUpdateOnAction(ActionEvent actionEvent) {
        try {
            if (!cmbCageId.getSelectionModel().isEmpty()) {
                String cageIdValue = (String) cmbCageId.getValue();
                CageDTO dto = new CageDTO(cageIdValue, lblBatch.getText(), Integer.parseInt(txtMax.getText()), Integer.parseInt(lblAvilableQty.getText()));

                ButtonType ok = new ButtonType("Yes");
                ButtonType no = new ButtonType("No");
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure ?", ok, no);
                Optional<ButtonType> result = alert.showAndWait();
                if (result.orElse(no) == ok) {

                    if (cageBO.updateCage(dto)) {
                        util.trayNotification("Update cage", "Update Success .....! ", NotificationType.SUCCESS);
                        loadAllCage();
                        return;
                    } else {
                        util.loadUI("AlertForm");
                        util.trayNotification("Update cage", "Update Failed .....! ", NotificationType.ERROR);

                    }

                }
            } else {
                util.notification("", "Please choose cage");
                cmbCageId.setFocusColor(Paint.valueOf("red"));
                cmbCageId.requestFocus();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void btnAddNewOnAction(ActionEvent actionEvent) throws Exception {
        util.loadUI("AddNewCageForm");
    }

    public void mainPaneOnAction(MouseEvent mouseEvent) {
        loadAllCage();
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

    public void onMouseClick(MouseEvent mouseEvent) {
        loadCmbCageID();
    }
}
