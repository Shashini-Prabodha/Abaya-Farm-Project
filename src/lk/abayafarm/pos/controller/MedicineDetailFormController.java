package lk.abayafarm.pos.controller;

import com.jfoenix.controls.*;
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
import lk.abayafarm.pos.bo.custom.MedicineBO;
import lk.abayafarm.pos.bo.custom.MedicineDetailsBO;
import lk.abayafarm.pos.bo.custom.UseMedicineBO;
import lk.abayafarm.pos.dto.CageDTO;
import lk.abayafarm.pos.dto.MedicineDTO;
import lk.abayafarm.pos.dto.MedicineDetailsDTO;
import lk.abayafarm.pos.dto.SupplierDTO;
import lk.abayafarm.pos.util.Util;
import lk.abayafarm.pos.util.impl.UtilImpl;
import lk.abayafarm.pos.view.TM.MedicineDetailTM;
import lk.abayafarm.pos.view.TM.SupplierTM;
import tray.notification.NotificationType;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;

public class MedicineDetailFormController {
    public JFXComboBox cmbCageId;
    public JFXDatePicker clndrDate;
    public JFXComboBox cmbMedicineName;
    public JFXTextField txtQtyOnHand, txtUseQty;
    public JFXTextArea Description;
    public JFXButton btnAddNew, btnBuy, btnUseMedicine, btnHome;
    public TableView<MedicineDetailTM> tblMedicineDetail;
    public TableColumn colMedicineId, colCageID, colUsedQty, colDate;
    public JFXTimePicker time;
    public ImageView imgAdmin;
    public Pane logoPane, Panecounter;
    public Label lblBatch;
    public AnchorPane parentPane;
//    public Spinner spinnerQtyUse;

    Util util = new UtilImpl();
    CageBO cageBO = BoFactory.getInstance().getBo(BoFactory.BoType.CAGE);
    MedicineBO medicineBO = BoFactory.getInstance().getBo(BoFactory.BoType.MEDICINE);
    MedicineDetailsBO medicineDetailsBO = BoFactory.getInstance().getBo(BoFactory.BoType.MEDICINEDETAILS);
    UseMedicineBO useMedicineBO = BoFactory.getInstance().getBo(BoFactory.BoType.USEMEDICINE);

    public void initialize() {
        try {
            getLoadAllCmb();
            util.rotateAnimation(imgAdmin);
            clndrDate.setValue(LocalDate.now());
//            spinnerQtyUse.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 1000, 0));

            colMedicineId.setCellValueFactory(new PropertyValueFactory<>("medicineID"));
            colCageID.setCellValueFactory(new PropertyValueFactory<>("cageId"));
            colUsedQty.setCellValueFactory(new PropertyValueFactory<>("usedQty"));
            colDate.setCellValueFactory(new PropertyValueFactory<>("date"));
            cmbCageId.setFocusColor(Paint.valueOf("red"));
            cmbCageId.requestFocus();
            loadAllDetails();
            util.setFadeTransition(parentPane, 300);

        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    private void loadAllDetails() {
        ObservableList<MedicineDetailTM> list = FXCollections.observableArrayList();
        try {
            List<MedicineDetailsDTO> dtoList = medicineDetailsBO.getAllMedicineDetails();
            for (MedicineDetailsDTO dto : dtoList) {
                MedicineDetailTM medicineDetailTM = new MedicineDetailTM(dto.getMedicineID(), dto.getCageId(), dto.getUsedQty(), dto.getDate());
                list.add(medicineDetailTM);
            }
        } catch (NullPointerException e) {
        } catch (Exception e1) {
            e1.printStackTrace();
        }
        tblMedicineDetail.setItems(list);
    }

    //load All cage ID to combo
    public void getLoadAllCmb() {

        try {
            List<CageDTO> list = cageBO.getCageIDListIn();
            for (CageDTO i : list) {
                cmbCageId.getItems().addAll(i.getCageId());
            }
            List<MedicineDTO> list2 = medicineBO.getAllMedicine();
            for (MedicineDTO i : list2) {
                cmbMedicineName.getItems().addAll(i.getMedicineName());
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }

    }

    public void cmbCageIdOnAction(ActionEvent actionEvent) throws Exception {
        CageDTO cage = cageBO.getCage(String.valueOf(cmbCageId.getValue()));
        lblBatch.setText(cage.getBatchId());
        cmbMedicineName.setFocusColor(Paint.valueOf("red"));
        cmbMedicineName.requestFocus();
    }

    public void btnHomeOnAction(ActionEvent actionEvent) throws Exception {
        util.loadUI("AdminDashBoardForm", btnHome);
    }

    public void cmbMedicineNameOnAction(ActionEvent actionEvent) {
        try {
            MedicineDTO medicineWithName = medicineBO.getMedicineWithName(String.valueOf(cmbMedicineName.getValue()));
            txtQtyOnHand.setText(String.valueOf(medicineWithName.getQtyOnHand()));
            Description.setText(medicineWithName.getDescription());
            txtUseQty.requestFocus();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void btnAddNewOnAction(ActionEvent actionEvent) throws Exception {
        util.loadUI("AddNewMedicineForm");
    }


    public void btnUseMedicineOnAction(ActionEvent actionEvent) {
        try {
            int use = Integer.parseInt(txtUseQty.getText());

            if (!cmbCageId.getSelectionModel().isEmpty()) {
                if (!cmbMedicineName.getSelectionModel().isEmpty()) {
                    if (Pattern.compile("^([0-9]{1,})$").matcher(txtUseQty.getText()).matches()) {
                        if (use > 0) {

                            LocalDate value = clndrDate.getValue();
                            Date date = Date.valueOf(value);
                            int av = Integer.parseInt(txtQtyOnHand.getText());
                            if (av >= use) {

                                int availbleQty = av - use;
                                MedicineDTO medicinetemp = medicineBO.getMedicineWithName(String.valueOf(cmbMedicineName.getValue()));
                                MedicineDTO medicine = new MedicineDTO(medicinetemp.getMedicineId(), medicinetemp.getMedicineName(), availbleQty, medicinetemp.getDescription());
                                MedicineDetailsDTO medicineDetailsDTO = new MedicineDetailsDTO(medicinetemp.getMedicineId(), String.valueOf(cmbCageId.getValue()), use, date);

                                ButtonType yes = new ButtonType("Yes", ButtonBar.ButtonData.OK_DONE);
                                ButtonType no = new ButtonType("No", ButtonBar.ButtonData.CANCEL_CLOSE);

                                Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Do you need use this medicine? ", yes, no);
                                Optional<ButtonType> result = alert.showAndWait();

                                if (result.orElse(no) == yes) {
                                    if (useMedicineBO.useMedicine(medicine, medicineDetailsDTO)) {
                                        util.trayNotification("Use Medicine", "Used Medicine  .....! ", NotificationType.SUCCESS);
                                        loadAllDetails();
                                    } else {
                                        util.trayNotification("Use Medicine", "Can't Use this Medicine .....! ", NotificationType.ERROR);

                                    }
                                }
                            } else {
                                util.notification("", "Out Of Stock....! Please add new medicine");
                                util.focus(txtUseQty);
                            }
                        } else {
                            util.focus(txtUseQty);
                            util.notification("", "Please enter quantity...!");
                        }
                    } else {
                        util.focus(txtUseQty);
                        util.notification("", "Please enter quantity in Numbers...!");
                    }
                } else {
                    util.notification("", "Please choose medicine...!");
                    cmbMedicineName.setFocusColor(Paint.valueOf("red"));
                    cmbMedicineName.requestFocus();
                }
            } else {
                util.notification("", "Please choose cage...!");
                cmbCageId.setFocusColor(Paint.valueOf("red"));
                cmbCageId.requestFocus();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void btnBuyOnAction(ActionEvent actionEvent) throws Exception {
        util.loadUI("MedicinePurchaseForm");
    }

    public void subOnAction(ActionEvent actionEvent) {
        int oldVlu = Integer.parseInt(txtUseQty.getText());
        if (oldVlu == 0)
            return;

        TranslateTransition tt = new TranslateTransition(Duration.millis(400), Panecounter);
        tt.setToX(115);
        tt.play();
        tt.setOnFinished(e -> {

            txtUseQty.setText((oldVlu - 1) + "");
            TranslateTransition tt2 = new TranslateTransition(Duration.millis(400), Panecounter);
            tt2.setToX(0);
            tt2.play();
        });
    }

    public void plusOnAction(ActionEvent actionEvent) {
        int oldVlu = Integer.parseInt(txtUseQty.getText());

        TranslateTransition tt = new TranslateTransition(Duration.millis(400), Panecounter);
        tt.setToX(-115);
        tt.play();
        tt.setOnFinished(e -> {
            txtUseQty.setText((oldVlu + 1) + "");
            TranslateTransition tt2 = new TranslateTransition(Duration.millis(400), Panecounter);
            tt2.setToX(0);
            tt2.play();
        });
    }
}
