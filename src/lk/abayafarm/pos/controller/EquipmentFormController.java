package lk.abayafarm.pos.controller;

import com.jfoenix.controls.*;
import javafx.event.ActionEvent;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Paint;
import lk.abayafarm.pos.bo.BoFactory;
import lk.abayafarm.pos.bo.custom.CageBO;
import lk.abayafarm.pos.bo.custom.SupplierBO;
import lk.abayafarm.pos.bo.custom.SupplierOrderBO;
import lk.abayafarm.pos.dto.CageDTO;
import lk.abayafarm.pos.dto.SupplierDTO;
import lk.abayafarm.pos.dto.SupplierOrderDTO;
import lk.abayafarm.pos.util.Util;
import lk.abayafarm.pos.util.impl.UtilImpl;
import tray.notification.NotificationType;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.regex.Pattern;

public class EquipmentFormController {
    public JFXDatePicker clndrDate;
    public JFXComboBox cmbCageID;
    public JFXComboBox cmbSupplierName;
    public JFXTextField txtSuplierId;
    public JFXTextField txtUnitPrice;
    public JFXTextField txtQtyOnHand;
    public JFXTextArea Description;
    public JFXButton btnAdd;
    public Pane logoPane;
    public JFXTextField txtTotal;
    public JFXButton btnClose;
    public Label lblSupOrderID;
    public JFXButton btnBuy;
    public AnchorPane parentPane;

    Util util = new UtilImpl();
    SupplierBO supplierBO = BoFactory.getInstance().getBo(BoFactory.BoType.SUPPLIER);
    SupplierOrderBO supplierOrderBO = BoFactory.getInstance().getBo(BoFactory.BoType.SUPPLIERORDER);
    CageBO cageBO = BoFactory.getInstance().getBo(BoFactory.BoType.CAGE);


    public void initialize() {
        try {
            util.genarateSupOrderID(lblSupOrderID);
            Description.setText("Buy Equipment");
            util.setFadeTransition(parentPane,300);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    public void cmbCageIDOnAction(ActionEvent actionEvent) {
        util.focus(txtUnitPrice);
    }

    public void cmbSupplierNameOnAction(ActionEvent actionEvent) {
        cmbCageID.setFocusColor(Paint.valueOf("red"));
        cmbCageID.requestFocus();
    }

    private void loadAllCmb(){
        try {
            List<SupplierDTO> list = supplierBO.getAllSupplierWithType("equipment");
            for (SupplierDTO dto : list) {
                cmbSupplierName.getItems().add(dto.getSupplierName());
            }

            List<CageDTO> list2 = cageBO.getAllCage();
            for (CageDTO dto : list2) {
                cmbCageID.getItems().add(dto.getCageId());
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void btnBuyOnAction(ActionEvent actionEvent) {
        try {
            if (!cmbSupplierName.getSelectionModel().isEmpty()) {
                if (!cmbCageID.getSelectionModel().isEmpty()) {
                        if (Pattern.compile("^([0-9.]{1,})$").matcher(txtUnitPrice.getText()).matches()) {
                            if (Pattern.compile("^([0-9]{1,})$").matcher(txtQtyOnHand.getText()).matches()) {

                                String supplierName = (String) cmbSupplierName.getValue();
                                String cageID = (String) cmbCageID.getValue();
                                LocalDate value = clndrDate.getValue();
                                int qty =Integer.parseInt( txtQtyOnHand.getText());
                                int uprice = Integer.parseInt(txtUnitPrice.getText());
                                Date date = Date.valueOf(value);
                                SupplierDTO s = supplierBO.getSupplierForName(supplierName);
                                CageDTO cage = cageBO.getCage(cageID);


                                SupplierOrderDTO dto=new SupplierOrderDTO(lblSupOrderID.getText(),s.getSupplierId(),cage.getBatchId(),Description.getText(),qty,uprice,date);

                                if (supplierOrderBO.saveSupplierOrder(dto)) {
                                    util.trayNotification("Buy Equipment", "Get Equipment .....! ", NotificationType.SUCCESS);
                                    util.genarateSupOrderID(lblSupOrderID);
                                } else {
                                    util.trayNotification("Buy Equipment", "Failed .....! ", NotificationType.ERROR);
                                }
                            } else {
                                util.focus(txtQtyOnHand);
                                util.notification("", "Please Input quantity in numbers...!");
                            }
                        } else {
                            util.focus(txtUnitPrice);
                            util.notification("", "Please Input unit price in numbers...!");
                        }

                } else {
                    util.notification("", "Please choose cage...!");
                    cmbCageID.setFocusColor(Paint.valueOf("red"));
                    cmbCageID.requestFocus();
                }

            } else {
                util.notification("", "Please choose Supplier...!");
                cmbSupplierName.setFocusColor(Paint.valueOf("red"));
                cmbSupplierName.requestFocus();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void txtQtyOnAction(ActionEvent keyEvent) {
        txtTotal.setText(String.valueOf(Double.parseDouble(txtQtyOnHand.getText()) * (Double.parseDouble(txtUnitPrice.getText()))));

    }

    public void btnCloseOnAction(ActionEvent actionEvent) throws Exception {
        util.loadUI(btnClose);
    }


    public void txtUnitPriceOnAction(ActionEvent actionEvent) {
        util.focus(txtQtyOnHand);
    }
}
