package lk.abayafarm.pos.controller;

import com.jfoenix.controls.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Paint;
import lk.abayafarm.pos.bo.BoFactory;
import lk.abayafarm.pos.bo.custom.CageBO;
import lk.abayafarm.pos.bo.custom.MedicineBO;
import lk.abayafarm.pos.bo.custom.SupplierBO;
import lk.abayafarm.pos.bo.custom.SupplierItemOrderTransactionBo;
import lk.abayafarm.pos.dao.DaoFactory;
import lk.abayafarm.pos.dto.CageDTO;
import lk.abayafarm.pos.dto.MedicineDTO;
import lk.abayafarm.pos.dto.SupplierDTO;
import lk.abayafarm.pos.dto.SupplierOrderDTO;
import lk.abayafarm.pos.util.Util;
import lk.abayafarm.pos.util.impl.UtilImpl;
import lk.abayafarm.pos.view.TM.MedicineTM;
import lk.abayafarm.pos.view.TM.SupplierTM;
import tray.notification.NotificationType;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.regex.Pattern;

public class MedicinePurchaseFormController {
    public JFXDatePicker clndrDate;
    public JFXButton btnBuy, btnUpdate, btnAddNew, btnClose;
    public JFXTextArea Description;
    public JFXComboBox cmbSupplierName, cmbMedicineName, cmbCageID;
    public JFXTextField txtUnitPrice, txtTotal, txtQtyOnHand;
    public TableView<MedicineTM> tblMedicineDetails;
    public TableColumn colMedicineID, colMedicineName, colDescription, colQtyOnHand;
    public Pane logoPane;
    public Label lblOrder;
    public JFXButton btnSupplierAdd;
    public AnchorPane parentPane;

    Util util = new UtilImpl();
    MedicineBO medicineBO = BoFactory.getInstance().getBo(BoFactory.BoType.MEDICINE);
    CageBO cageBO = BoFactory.getInstance().getBo(BoFactory.BoType.CAGE);
    SupplierBO supplierBO = BoFactory.getInstance().getBo(BoFactory.BoType.SUPPLIER);
    SupplierItemOrderTransactionBo bo = BoFactory.getInstance().getBo(BoFactory.BoType.SUPPLIERITEMORDER);

    public void initialize() {
        try {
            util.genarateSupOrderID(lblOrder);
            loadAllCmb();
            util.rotateAnimation(logoPane);
            clndrDate.setValue(LocalDate.now());

            colMedicineID.setCellValueFactory(new PropertyValueFactory<>("medicineId"));
            colMedicineName.setCellValueFactory(new PropertyValueFactory<>("medicineName"));
            colQtyOnHand.setCellValueFactory(new PropertyValueFactory<>("qtyOnHand"));
            colDescription.setCellValueFactory(new PropertyValueFactory<>("description"));

            util.setFadeTransition(parentPane, 2000);
            loadAllMedicine();
            cmbMedicineName.setFocusColor(Paint.valueOf("red"));
            cmbMedicineName.requestFocus();
            tblMedicineDetails.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
                setData(newValue);
            });

        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    private void loadAllMedicine() {
        ObservableList<MedicineTM> list = FXCollections.observableArrayList();
        try {
            List<MedicineDTO> dtoList = medicineBO.getAllMedicine();
            for (MedicineDTO dto : dtoList) {
                MedicineTM tm = new MedicineTM(dto.getMedicineId(), dto.getMedicineName(), dto.getQtyOnHand(), dto.getDescription());
                list.add(tm);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        tblMedicineDetails.setItems(list);
    }

    private void setData(MedicineTM newValue) {
    }

    private void loadAllCmb() {
        cmbMedicineName.getItems().clear();
        cmbSupplierName.getItems().clear();

        try {
            List<MedicineDTO> list = medicineBO.getAllMedicine();
            for (MedicineDTO dto : list) {
                cmbMedicineName.getItems().add(dto.getMedicineName());
            }

            List<CageDTO> list2 = cageBO.getCageIDListIn();
            for (CageDTO dto : list2) {
                cmbCageID.getItems().add(dto.getCageId());
            }

            List<SupplierDTO> list3 = supplierBO.getAllSupplierWithType("medicine");
            for (SupplierDTO dto : list3) {
                cmbSupplierName.getItems().add(dto.getSupplierName());
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void cmbMedicineNameOnAction(ActionEvent actionEvent) {
        String medicineName = (String) cmbMedicineName.getValue();
        try {
            MedicineDTO dto = medicineBO.getMedicineWithName(medicineName);
            Description.setText(dto.getDescription());
            cmbCageID.setFocusColor(Paint.valueOf("red"));
            cmbCageID.requestFocus();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void btnBuyOnAction(ActionEvent actionEvent) {
        try {
            if (!cmbMedicineName.getSelectionModel().isEmpty()) {
                if (!cmbCageID.getSelectionModel().isEmpty()) {
                    if (!cmbSupplierName.getSelectionModel().isEmpty()) {
                        if (Pattern.compile("^([0-9.]{1,})$").matcher(txtUnitPrice.getText()).matches()) {
                            if (Pattern.compile("^([0-9]{1,})$").matcher(txtQtyOnHand.getText()).matches()) {

                                String medicineName = (String) cmbMedicineName.getValue();
                                String supplierName = (String) cmbSupplierName.getValue();
                                String cageID = (String) cmbCageID.getValue();
                                LocalDate value = clndrDate.getValue();
                                Date date = Date.valueOf(value);

                                MedicineDTO m = medicineBO.getMedicineWithName(medicineName);
                                SupplierDTO s = supplierBO.getSupplierForName(supplierName);
                                CageDTO cage = cageBO.getCage(cageID);
                                MedicineDTO dto = new MedicineDTO(m.getMedicineId(), m.getMedicineName(), (m.getQtyOnHand() + Integer.parseInt(txtQtyOnHand.getText())), m.getDescription());
                                SupplierOrderDTO supplierOrderDTO = new SupplierOrderDTO(lblOrder.getText(), s.getSupplierId(), cage.getBatchId(), Description.getText(), Integer.parseInt(txtQtyOnHand.getText()), Double.parseDouble(txtUnitPrice.getText()), date);

                                if (bo.addBatch(supplierOrderDTO, dto)) {
                                    util.trayNotification("Buy Medicine", "Get Medicine .....! ", NotificationType.SUCCESS);
                                    loadAllCmb();
                                    loadAllMedicine();
                                } else {
                                    util.trayNotification("Buy Medicine", "Failed .....! ", NotificationType.ERROR);
                                }
                            } else {
                                util.focus(txtQtyOnHand);
                                util.notification("", "Please Input quantity...!");
                            }
                        } else {
                            util.focus(txtUnitPrice);
                            util.notification("", "Please Input unit price...!");
                        }
                    } else {
                        util.notification("", "Please choose supplier...!");
                        cmbSupplierName.setFocusColor(Paint.valueOf("red"));
                        cmbSupplierName.requestFocus();
                    }
                } else {
                    util.notification("", "Please choose cage...!");
                    cmbCageID.setFocusColor(Paint.valueOf("red"));
                    cmbCageID.requestFocus();
                }

            } else {
                util.notification("", "Please choose Medicine...!");
                cmbMedicineName.setFocusColor(Paint.valueOf("red"));
                cmbMedicineName.requestFocus();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void btnAddNewOnAction(ActionEvent actionEvent) throws Exception {
        util.loadUI("AddNewMedicineForm");
    }

    public void btnCloseOnAction(ActionEvent actionEvent) throws Exception {
        util.loadUI(btnClose);
    }

    public void btnSupplierAddOnAction(ActionEvent actionEvent) throws Exception {
        util.loadUI("AddSupplierForm");
    }

    public void cmbCageIDOnAction(ActionEvent actionEvent) {
        cmbSupplierName.setFocusColor(Paint.valueOf("red"));
        cmbSupplierName.requestFocus();
    }

    public void cmbSupplierNameOnAction(ActionEvent actionEvent) {

        util.focus(txtUnitPrice);
    }

    public void txtqtyOnAction(ActionEvent actionEvent) {
        txtTotal.setText(String.valueOf(Double.parseDouble(txtQtyOnHand.getText()) * (Double.parseDouble(txtUnitPrice.getText()))));
    }

    public void txtUnitPriceOnAction(ActionEvent actionEvent) {
        util.focus(txtQtyOnHand);
    }

    public void onMouseClickMedicineName(MouseEvent mouseEvent) {
        loadAllCmb();
    }

    public void onMouseClickSupName(MouseEvent mouseEvent) {
        loadAllCmb();
    }
}
