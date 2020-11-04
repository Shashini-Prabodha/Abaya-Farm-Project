package lk.abayafarm.pos.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Paint;
import lk.abayafarm.pos.bo.BoFactory;
import lk.abayafarm.pos.bo.custom.SupplierBO;
import lk.abayafarm.pos.bo.custom.SupplierOrderBO;
import lk.abayafarm.pos.dto.SupplierDTO;
import lk.abayafarm.pos.util.Util;
import lk.abayafarm.pos.util.impl.UtilImpl;
import lk.abayafarm.pos.view.TM.SupplierTM;
import tray.notification.NotificationType;

import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;

public class AddSupplierFormController {
    public JFXDatePicker clndrDate;
    public TableView<SupplierTM> tblSupplier;
    public TableColumn colSupplierId, colName, colTP, colAddress, colType, colDelete;
    public JFXButton btnAdd, btnUpdate, btnClear, btnClose;
    public JFXRadioButton rbtnChick;
    public ToggleGroup group;
    public JFXRadioButton rbtnFeed, rbtnMedicine, rbtnEquipments;
    public Pane logoPane;
    public JFXTextField txtSupplierID;
    public JFXTextField txtxSupplierName;
    public JFXTextField txtSupplierTP;
    public JFXTextField txtAddress;
    public HBox hbox;
    public AnchorPane parentPane;

    SupplierBO supplierBO = BoFactory.getInstance().getBo(BoFactory.BoType.SUPPLIER);
    Util util = new UtilImpl();

    public void initialize() {
        try {
            util.rotateAnimation(logoPane);

            genarateSupOrderID();

            colSupplierId.setCellValueFactory(new PropertyValueFactory<>("supplierId"));
            colName.setCellValueFactory(new PropertyValueFactory<>("supplierName"));
            colTP.setCellValueFactory(new PropertyValueFactory<>("supplierTp"));
            colAddress.setCellValueFactory(new PropertyValueFactory<>("supplierAddress"));
            colType.setCellValueFactory(new PropertyValueFactory<>("type"));
            colDelete.setCellValueFactory(new PropertyValueFactory<>("btn"));

            loadAllSupplier();

            tblSupplier.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
                setData(newValue);
            });
            util.setFadeTransition(parentPane,300);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void setData(SupplierTM tm) {
        try {
            txtSupplierID.setText(tm.getSupplierId());
            txtxSupplierName.setText(tm.getSupplierName());
            txtAddress.setText(tm.getSupplierAddress());
            txtSupplierTP.setText(String.valueOf(tm.getSupplierTp()));
            setRadioButton(tm.getType());
        } catch (NullPointerException e) {

        }
    }

    //type wise button selected
    private void setRadioButton(String type) {
        switch (type) {
            case "chick":
                rbtnChick.setSelected(true);
                break;
            case "medicine":
                rbtnMedicine.setSelected(true);
                break;
            case "feed":
                rbtnFeed.setSelected(true);
                break;
            case "equipment":
                rbtnEquipments.setSelected(true);
            default:
        }
    }

    private void loadAllSupplier() {

        ObservableList<SupplierTM> list = FXCollections.observableArrayList();
        try {
            List<SupplierDTO> dtoList = supplierBO.getAllSupplier();
            for (SupplierDTO dto : dtoList) {
                JFXButton btn = new JFXButton("Delete");
                SupplierTM tm = new SupplierTM(dto.getSupplierId(), dto.getSupplierName(), dto.getSupplierTp(), dto.getSupplierAddress(), dto.getType(), btn);
                list.add(tm);

                btn.setStyle("-fx-background-color: #81ecec; ");
                btn.setOnAction((e) -> {
                    try {
                        ButtonType ok = new ButtonType("OK",
                                ButtonBar.ButtonData.OK_DONE);
                        ButtonType no = new ButtonType("NO",
                                ButtonBar.ButtonData.CANCEL_CLOSE);

                        Alert alert = new Alert(Alert.AlertType.CONFIRMATION,
                                "Are You Sure ?", ok, no);
                        Optional<ButtonType> result = alert.showAndWait();
                        if (result.orElse(no) == ok) {
                            if (supplierBO.deleteSupplier(tm.getSupplierId())) {
                                util.trayNotification("Delete Supplier", "Supplier Deleted .....! ", NotificationType.SUCCESS);
                                loadAllSupplier();
                                return;
                            } else {
                            }
                            util.trayNotification("Delete Supplier", "Delete Failed .....! ", NotificationType.ERROR);

                        } else {

                        }

                    } catch (Exception e1) {
                        e1.printStackTrace();
                    }
                });
            }
            tblSupplier.setItems(list);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void btnAddOnAction(ActionEvent actionEvent) {
        String type = getType();
        try {
            if (Pattern.compile("^[A-z ]{1,}$").matcher(txtxSupplierName.getText()).matches()) {
                if (Pattern.compile("^\\d{10}$").matcher(txtSupplierTP.getText()).matches()) {
                    if (Pattern.compile("^[A-z, |0-9:./]*\\b$").matcher(txtAddress.getText()).matches()) {
                        if (null != type) {
                            SupplierDTO supplierDTO = new SupplierDTO(txtSupplierID.getText(), txtxSupplierName.getText(), Integer.parseInt(txtSupplierTP.getText()), txtAddress.getText(), type);

                            ButtonType ok = new ButtonType("Yes");
                            ButtonType no = new ButtonType("No");
                            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure add new supplier ?", ok, no);
                            Optional<ButtonType> result = alert.showAndWait();
                            if (result.orElse(no) == ok) {

                                if (supplierBO.saveSupplier(supplierDTO)) {
                                    util.trayNotification("Add new supplier", "Added New supplier .....! ", NotificationType.SUCCESS);
                                    loadAllSupplier();
                                    genarateSupOrderID();
                                    return;
                                } else {
                                    util.trayNotification("Add new supplier", "Added Failed .....! ", NotificationType.ERROR);
                                }
                            }

                        } else {
                            util.notification("", "Please Choose type...");
                        }
                    } else {
                        util.notification("", "Please enter quantity in Numbers...!");
                        util.focus(txtAddress);
                    }
                } else {
                    util.notification("", "Please enter quantity in Numbers...!");
                    util.focus(txtSupplierTP);
                }
            } else {
                util.notification("", "Please enter quantity in Numbers...!");
                util.focus(txtxSupplierName);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void btnUpdateOnAction(ActionEvent actionEvent) {
        String type = getType();
        if (Pattern.compile("^[A-z ]{1,}$").matcher(txtxSupplierName.getText()).matches()) {
            if (Pattern.compile("^\\d{10}$").matcher(txtSupplierTP.getText()).matches()) {
                if (Pattern.compile("^[A-z, |0-9:./]*\\b$").matcher(txtAddress.getText()).matches()) {
                    if (null != type) {
                        SupplierDTO supplierDTO = new SupplierDTO(txtSupplierID.getText(), txtxSupplierName.getText(), Integer.parseInt(txtSupplierTP.getText()), txtAddress.getText(), getType());

                        ButtonType ok = new ButtonType("Yes");
                        ButtonType no = new ButtonType("No");
                        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure add new supplier ?", ok, no);
                        Optional<ButtonType> result = alert.showAndWait();
                        if (result.orElse(no) == ok) {
                            try {
                                if (supplierBO.updateSupplier(supplierDTO)) {
                                    util.trayNotification("Update supplier", "Update Success .....! ", NotificationType.SUCCESS);
                                    loadAllSupplier();
                                    return;
                                } else {
                                    util.trayNotification("Update supplier", "Update Failed .....! ", NotificationType.ERROR);

                                }
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    } else {
                        new Alert(Alert.AlertType.INFORMATION, "Please Choose type...").show();
                    }
                } else {
                    util.focus(txtAddress);
                }
            } else {
                util.focus(txtSupplierTP);
            }
        } else {
            util.focus(txtxSupplierName);
        }
    }

    public void btnClearOnAction(ActionEvent actionEvent) {
        txtSupplierID.setText("");
        txtxSupplierName.setText(null);
        txtAddress.setText(null);
        txtSupplierTP.setText(null);
        setRadioButton(null);
    }

    public String getType() {
        if (rbtnChick.isSelected()) {
            return "chick";
        } else if (rbtnFeed.isSelected()) {
            return "feed";
        } else if (rbtnMedicine.isSelected()) {
            return "medicine";
        } else if (rbtnEquipments.isSelected()) {
            return "equipment";
        } else {
            return null;
        }
    }

    //set Supplier Order ID
    public void genarateSupOrderID() {
        try {
            String orderId = supplierBO.getLastSupplierID();
            String temp[] = orderId.split("SP", 7);
            Integer number = Integer.parseInt(temp[1]);
            String newBatchID = "SP" + (number + 1);
            txtSupplierID.setText(newBatchID);

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
}
