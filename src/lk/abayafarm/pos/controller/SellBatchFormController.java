package lk.abayafarm.pos.controller;

import com.jfoenix.controls.*;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Paint;
import lk.abayafarm.pos.bo.BoFactory;
import lk.abayafarm.pos.bo.custom.BatchBO;
import lk.abayafarm.pos.bo.custom.CageBO;
import lk.abayafarm.pos.bo.custom.SellBatchBO;
import lk.abayafarm.pos.db.DBConnection;
import lk.abayafarm.pos.dto.BatchDTO;
import lk.abayafarm.pos.dto.CageDTO;
import lk.abayafarm.pos.dto.OrderDetailsDTO;
import lk.abayafarm.pos.dto.StoreDTO;
import lk.abayafarm.pos.entity.Batch;
import lk.abayafarm.pos.util.Regex;
import lk.abayafarm.pos.util.Util;
import lk.abayafarm.pos.util.impl.RegexImpl;
import lk.abayafarm.pos.util.impl.UtilImpl;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.view.JasperViewer;
import tray.notification.NotificationType;

import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;

public class SellBatchFormController {
    public JFXTextField txtBatchID, txtQty, txtUnitPrice;
    public JFXRadioButton rbtnChick, rbtnGrower, rbtnLayer;
    public ToggleGroup group;
    public JFXTextArea Description;
    public JFXComboBox cmbCageId;
    public Pane logoPane;
    public JFXButton btnSell;
    public JFXDatePicker clndrDate;
    public Label lblTotal;
    public AnchorPane parentPane;

    CageBO cageBO = BoFactory.getInstance().getBo(BoFactory.BoType.CAGE);
    BatchBO batchBO = BoFactory.getInstance().getBo(BoFactory.BoType.BATCH);
    SellBatchBO sellBatchBO = BoFactory.getInstance().getBo(BoFactory.BoType.SELLBATCH);
    Util util = new UtilImpl();
    private Regex regex = new RegexImpl();

    public void initialize() {

        cmbCageId.setFocusColor(Paint.valueOf("red"));
        cmbCageId.requestFocus();
        loadCmb();
        try {
            util.setFadeTransition(parentPane,300);
        } catch (IOException e) {
            e.printStackTrace();
        }
        clndrDate.setValue(LocalDate.now());

    }

    //load cmb to cage id
    public void loadCmb() {
        try {
            List<CageDTO> list = cageBO.getCageIDListIn();
            for (CageDTO dto : list) {
                cmbCageId.getItems().add(dto.getCageId());
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //choose cage Id & set other text values
    public void cmbCageIdOnAction(ActionEvent actionEvent) {
        String cageId = (String) cmbCageId.getValue();
        try {
            CageDTO cage = cageBO.getCage(cageId);
            txtBatchID.setText(cage.getBatchId());

            BatchDTO batchDTO = batchBO.getBatch(txtBatchID.getText());
            setRadioButton(batchDTO.getType());
            txtQty.setText(String.valueOf(cage.getAvailableQty()));
            txtUnitPrice.requestFocus();
            Description.setText("Chicken Sell");
            rbtnChick.requestFocus();
            util.focus(txtUnitPrice);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //sll btn transaction
    public void btnSellOnAction(ActionEvent actionEvent) {
        try {

            if (!cmbCageId.getSelectionModel().isEmpty()) {
                //  String type = getRadioButonValue();
                //  if (null != type) {
                if (Pattern.compile("^([0-9]{1,})$").matcher(txtQty.getText()).matches()) {
                    if (Pattern.compile("^([0-9.]{1,})$").matcher(txtUnitPrice.getText()).matches()) {
                        String cageId = (String) cmbCageId.getValue();

                        BatchDTO batchDTO = new BatchDTO(txtBatchID.getText(), getRadioButonValue(), Integer.parseInt(txtQty.getText()), "exit");
                        CageDTO cageDTO = new CageDTO(cageId, txtBatchID.getText(), cageBO.getCage(cageId).getMaxQty(), Integer.parseInt(txtQty.getText()));

                        ButtonType ok = new ButtonType("Yes");
                        ButtonType no = new ButtonType("No");
                        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you Sure Sell this Batch ?", ok, no);
                        Optional<ButtonType> result = alert.showAndWait();
                        if (result.orElse(no) == ok) {
                            if (sellBatchBO.sellBatch(batchDTO, cageDTO)) {
                                util.trayNotification("Sell Batch", "Batch Sold Successfully .....! ", NotificationType.SUCCESS);
                                sellingBill();
                            } else {
                                util.trayNotification("Sell Batch", "Can't Sell this Batch .....! ", NotificationType.ERROR);
                            }
                        } else {

                        }


                    } else {
                        util.notification("Error", "Please use only Numbers");
                        util.focus(txtUnitPrice);
                    }
                } else {
                    util.notification("Error", "Please use only Numbers");
                    util.focus(txtQty);
                }
               /* } else {
                    util.notification("Error", "Please use only Numbers");
                }*/

            } else {
                util.notification("", "Please choose cage");
                cmbCageId.setFocusColor(Paint.valueOf("red"));
                cmbCageId.requestFocus();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private String getRadioButonValue() {
        if (rbtnChick.isSelected()) {
            return "chick";
        } else if (rbtnGrower.isSelected()) {
            return "grower";
        } else if (rbtnLayer.isSelected()) {
            return "layer";
        } else {
            return null;
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

    //Qty key On Action and Calculate total
    public void unitPriceKeyOnAction(KeyEvent keyEvent) {
        try {
            if (regex.regex("^[0-9]{1,}$", txtUnitPrice.getText()))
                lblTotal.setText(String.valueOf(Double.parseDouble(txtQty.getText()) * (Double.parseDouble(txtUnitPrice.getText()))));
            else {
                txtUnitPrice.setFocusColor(Paint.valueOf("red"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void txtUnitPriceOnAction(ActionEvent actionEvent) {
        Description.setFocusColor(Paint.valueOf("red"));
        Description.requestFocus();
    }

    public void sellingBill() {
        try {

            InputStream is = this.getClass().getResourceAsStream("/lk/abayafarm/pos/report/CustomerBill/SellHens.jrxml");
            JasperReport jr = JasperCompileManager.compileReport(is);

            HashMap hm = new HashMap();
            hm.put("Qty", txtQty.getText() + "");
            hm.put("uprice", txtUnitPrice.getText() + "");
            hm.put("invoiceId", "0001");
            hm.put("Total", lblTotal.getText() + "");
/*
            hm.put("qty", "312");
            hm.put("Uprice", "452.23");
            hm.put("invoiceID", "0001");
            hm.put("total", "1000");*/


            JasperPrint jp = JasperFillManager.fillReport(jr, hm, DBConnection.getInstance().getConnection());
            JasperViewer.viewReport(jp, false);


        } catch (JRException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void rbtnOnAction(ActionEvent actionEvent) {
        util.focus(txtQty);
    }

    public void txtQtyOnAction(ActionEvent actionEvent) {
        util.focus(txtUnitPrice);
    }
}
