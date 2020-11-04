package lk.abayafarm.pos.controller;

import com.jfoenix.controls.*;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Paint;
import javafx.util.Duration;
import lk.abayafarm.pos.bo.BoFactory;
import lk.abayafarm.pos.bo.custom.*;
import lk.abayafarm.pos.dto.BatchDTO;
import lk.abayafarm.pos.dto.CageDTO;
import lk.abayafarm.pos.dto.SupplierDTO;
import lk.abayafarm.pos.dto.SupplierOrderDTO;
import lk.abayafarm.pos.util.Regex;
import lk.abayafarm.pos.util.Util;
import lk.abayafarm.pos.util.impl.RegexImpl;
import lk.abayafarm.pos.util.impl.UtilImpl;
import tray.notification.NotificationType;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.regex.Pattern;

public class AddBatchFormController {
    public JFXDatePicker clndrDate;
    public Label lblOrderID, lblTotal;
    public JFXTextField txtBatchID, txtQtyOnHand, txtSuplierId, txtChickPrice;
    public ToggleGroup group;
    public JFXRadioButton rbtnChick, rbtnGrower, rbtnLayer;
    public JFXTextArea Description;
    public JFXComboBox cmbSupplierName, cmbCage;
    public JFXButton btnAdd, btnAddCage;
    public Pane logoPane;
    public Label lblMaxHens;
    public AnchorPane parentPane;


    BatchBO batchBO = BoFactory.getInstance().getBo(BoFactory.BoType.BATCH);
    CageBO cageBO = BoFactory.getInstance().getBo(BoFactory.BoType.CAGE);
    SupplierOrderBO supplierOrderBO = BoFactory.getInstance().getBo(BoFactory.BoType.SUPPLIERORDER);
    SupplierBO supplierBO = BoFactory.getInstance().getBo(BoFactory.BoType.SUPPLIER);
    AddBatchBO addBatchBO = BoFactory.getInstance().getBo(BoFactory.BoType.ADDBATCH);
    Regex regex = new RegexImpl();
    Util util = new UtilImpl();

    public void initialize() {
        try {
            supplierNameLoad();
            txtQtyOnHand.setText("0");
            txtChickPrice.setText("0.0");
            lblTotal.setText("0.0");
            genarateBatchID();
            setCageId();
            setSupOrderID();
            clndrDate.setValue(LocalDate.now());
            util.rotateAnimation(logoPane);
            cmbSupplierName.setFocusColor(Paint.valueOf("red"));
            cmbSupplierName.requestFocus();
            util.setFadeTransition(parentPane,300);

        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    private void genarateBatchID() {
        try {

            String id = batchBO.getLastBatchID();
            String lastId = id.split("[A-Z]")[1];

            if (Integer.parseInt(lastId) == 9) {
                txtBatchID.setText("B010");
                return;
            } else if (Integer.parseInt(lastId) < 9) {
                String newID = "B00" + (Integer.parseInt(lastId) + 1);
                txtBatchID.setText(newID);
            } else {
                String newID = "B0" + (Integer.parseInt(lastId) + 1);
                txtBatchID.setText(newID);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    //set Supplier Order ID
    private void setSupOrderID() {
        try {
            String orderId = supplierOrderBO.getLastOrderID();
            String temp[] = orderId.split("O", 7);
            Integer number = Integer.parseInt(temp[1]);
            String newBatchID = "O" + (number + 1);
            lblOrderID.setText(newBatchID);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    //get Supplier Detail with name
    public void cmbSupplierNameOnAction(ActionEvent actionEvent) {
        try {
            String supName = (String) cmbSupplierName.getValue();
            SupplierDTO supplier = supplierBO.getSupplierForName(supName);
            txtSuplierId.setText(supplier.getSupplierId());
            txtChickPrice.requestFocus();

        } catch (Exception exception) {
            exception.printStackTrace();
        }

    }

    //combo box loading supplier name
    private void supplierNameLoad() {
        try {
            List<SupplierDTO> list = supplierBO.getAllSupplierWithType("chick");
            for (SupplierDTO dto : list) {
                cmbSupplierName.getItems().addAll(dto.getSupplierName());
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }

    }


    //transaction btn add
    public void btnAddOnAction(ActionEvent actionEvent) {
        LocalDate value = clndrDate.getValue();
        Date date = Date.valueOf(value);
        String supplierName = String.valueOf(cmbSupplierName.getValue());
        String cageId = String.valueOf(cmbSupplierName.getValue());

        System.out.println(supplierName);
        System.out.println(cageId);
        try {
            if (!cmbSupplierName.getSelectionModel().isEmpty()) {
                if (!cmbCage.getSelectionModel().isEmpty()) {
                    if (Pattern.compile("^([0-9.]{1,})$").matcher(txtChickPrice.getText()).matches()) {
                        if (Pattern.compile("^([0-9]{1,})$").matcher(txtQtyOnHand.getText()).matches()) {
                            if (Integer.parseInt(txtQtyOnHand.getText())>0) {

                                CageDTO cageDTO = new CageDTO((String) (cmbCage.getValue()), txtBatchID.getText(), Integer.parseInt(lblMaxHens.getText()), Integer.parseInt(txtQtyOnHand.getText()));
                                BatchDTO batchDTO = new BatchDTO(txtBatchID.getText(), "chick", Integer.parseInt(txtQtyOnHand.getText()), "in");
                                SupplierOrderDTO supplierOrderDTO = new SupplierOrderDTO(lblOrderID.getText(), txtSuplierId.getText(), txtBatchID.getText(), Description.getText(), Integer.parseInt(txtQtyOnHand.getText()), Double.parseDouble(txtChickPrice.getText()), date);

                                lblTotal.setText(String.valueOf(Integer.parseInt(txtQtyOnHand.getText()) * (Double.parseDouble(txtChickPrice.getText()))));
                                ButtonType ok = new ButtonType("Yes");
                                ButtonType no = new ButtonType("No");
                                Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure add new Batch ?", ok, no);
                                Optional<ButtonType> result = alert.showAndWait();
                                if (result.orElse(no) == ok) {
                                    try {
                                        if (addBatchBO.addBatch(batchDTO, supplierOrderDTO, cageDTO)) {
                                            util.trayNotification("Add New Batch", "Added New Batch .....! ", NotificationType.SUCCESS);
                                        } else {
                                            util.trayNotification("Add New Batch", "Added Failed .....! ", NotificationType.ERROR);

                                        }
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }
                                } else {
                                }
                            } else {
                                util.notification("Error","Please Enter Qty On Hand");
                                util.focus(txtQtyOnHand);
                            }
                        } else {
                            util.notification("Error","Please use only Numbers");
                            util.focus(txtQtyOnHand);
                        }
                    } else {
                        util.notification("Error","Please use only Numbers");
                        util.focus(txtChickPrice);
                    }
                } else {
                    //util.notification("","ඔබගේ ඇතුලත් කිරීම නොගැලපේ ");
                    util.notification("","Please choose cage");
                    cmbCage.setFocusColor(Paint.valueOf("red"));
                    cmbCage.requestFocus();
                }
            } else {
                util.notification("","Please choose Supplier");
                cmbSupplierName.setFocusColor(Paint.valueOf("red"));
                cmbSupplierName.requestFocus();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    //genarate batchid
    private String genarateBatchID(String lastID) {
        String temp[] = lastID.split("B", 5);
        Integer number = Integer.parseInt(temp[1]);
        String newBatchID = "B" + (number + 1);
        return newBatchID;
    }

    //Qty key On Action and Calculate total
    public void qtyKeyOnAction(KeyEvent keyEvent) {
        try {
            if (Pattern.compile("^([0-9.]{1,})$").matcher(txtChickPrice.getText()).matches()) {
                if (Pattern.compile("^([0-9]{1,})$").matcher(txtQtyOnHand.getText()).matches()) {

                    lblTotal.setText(String.valueOf(Integer.parseInt(txtQtyOnHand.getText()) * (Double.parseDouble(txtChickPrice.getText()))));
                } else {
                    util.focus(txtQtyOnHand);
                }
            } else {
                util.focus(txtChickPrice);

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //get max hen value with cage id
    public void cmbCageOnAction(ActionEvent actionEvent) {
        String s = (String) cmbCage.getValue();

        try {
            CageDTO cage = cageBO.getCage(s);
            lblMaxHens.setText(String.valueOf(cage.getMaxQty()));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //combo box loading Empty Cage id
    private void setCageId() {
            cmbCage.getItems().clear();
        try {
            List<CageDTO> list = cageBO.getCageIDListExit();
            for (CageDTO cageDTO : list) {
                cmbCage.getItems().addAll(cageDTO.getCageId());
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }

    }

    //add new Cage
    public void btnAddCageOnActon(ActionEvent actionEvent) throws Exception {
        util.loadUI("AddNewCageForm");

    }

    public void onMouseClicked(MouseEvent mouseEvent) {
        setCageId();
    }
}