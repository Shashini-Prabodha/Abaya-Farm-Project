package lk.abayafarm.pos.controller;

import com.jfoenix.controls.*;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Paint;
import lk.abayafarm.pos.bo.BoFactory;
import lk.abayafarm.pos.bo.custom.AddItemToStore;
import lk.abayafarm.pos.bo.custom.CageBO;
import lk.abayafarm.pos.bo.custom.ManureBO;
import lk.abayafarm.pos.bo.custom.StoreBO;
import lk.abayafarm.pos.dto.CageDTO;
import lk.abayafarm.pos.dto.ManureDTO;
import lk.abayafarm.pos.dto.StoreDTO;
import lk.abayafarm.pos.util.Util;
import lk.abayafarm.pos.util.impl.UtilImpl;
import tray.notification.NotificationType;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.regex.Pattern;

public class ManureFormController {
    public JFXComboBox cmbCageId;
    public JFXDatePicker clndrDate;
    public JFXTextField txtQty, txt5Price, txt10Price, txt25Price, txt50Price1;
    public JFXRadioButton rbtn5, rbtn10, rbtn25, rbtn50;
    ;
    public ToggleGroup group;
    public JFXButton btnAdd, btnHome, btnClear;
    public ImageView imgAdmin;
    public JFXButton btnUpdate;
    public AnchorPane parentPane;

    Util util = new UtilImpl();
    ManureBO manureBO = BoFactory.getInstance().getBo(BoFactory.BoType.MANURE);
    StoreBO storeBO = BoFactory.getInstance().getBo(BoFactory.BoType.STORE);
    CageBO cageBO = BoFactory.getInstance().getBo(BoFactory.BoType.CAGE);
    AddItemToStore addItemToStore = BoFactory.getInstance().getBo(BoFactory.BoType.ADDITEMTOSTORE);

    public void initialize() {
        try {
            clndrDate.setValue(LocalDate.now());
            loadAllCageID();
            util.rotateAnimation(imgAdmin);
            cmbCageId.setFocusColor(Paint.valueOf("red"));
            cmbCageId.requestFocus();
            util.setFadeTransition(parentPane, 300);

        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    private void loadAllCageID() {
        try {
            List<CageDTO> list = cageBO.getAllCage();
            for (CageDTO cageDTO : list) {
                cmbCageId.getItems().add(cageDTO.getCageId());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void cmbCageIdOnAction(ActionEvent actionEvent) {

        rbtn5.requestFocus();
        rbtn10.requestFocus();
        rbtn25.requestFocus();
        rbtn50.requestFocus();
    }

    public void btnHomeOnAction(ActionEvent actionEvent) throws Exception {
        util.loadUI("AdminDashBoardForm", btnHome);
    }

    public void btnAddOnAction(ActionEvent actionEvent) {
        try {
            if (!cmbCageId.getSelectionModel().isEmpty()) {
                if (getPackSize() != null) {
                    if (Pattern.compile("^([0-9]{1,})$").matcher(txtQty.getText()).matches()) {

                        LocalDate value = clndrDate.getValue();
                        Date date = Date.valueOf(value);

                        int oldQty = oldQtyInStock(getStoreID());
                        StoreDTO storeDTO = storeBO.getStore(getStoreID());
                        ManureDTO manureDTO = new ManureDTO((String) (cmbCageId.getValue()), getPackSize(), Integer.parseInt(txtQty.getText()), storeDTO.getUnitPrice(), date);
                        boolean b = addItemToStore.addManuretoStore(manureDTO, getStoreID(), (Integer.parseInt(txtQty.getText()) + oldQty));
                        if (b) {
                            util.trayNotification("Add Manure", "Added Successfull .....! ", NotificationType.SUCCESS);
                        } else {
                            util.loadUI("AlertForm");
                            util.trayNotification("Add Manure", "Added Failed .....! ", NotificationType.ERROR);
                        }

                    } else {
                        util.focus(txtQty);
                    }
                } else {
                    util.notification("", "Please choose pack size...!");
                    rbtn5.requestFocus();
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

    private int oldQtyInStock(String sid) {
        try {
            StoreDTO store = storeBO.getStore(sid);
            int qtyOnStore = store.getQtyOnStore();
            return qtyOnStore;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;

    }

    public void btnClearOnAction(ActionEvent actionEvent) {
        txtQty.clear();
        rbtn5.setSelected(false);
        rbtn10.setSelected(false);
        rbtn25.setSelected(false);
        rbtn50.setSelected(false);

    }


    public String getPackSize() {
        if (rbtn5.isSelected()) {
            return "5kg";
        } else if (rbtn10.isSelected()) {
            return "10kg";
        } else if (rbtn25.isSelected()) {
            return "25kg";
        } else if (rbtn50.isSelected()) {
            return "50kg";
        } else {
            return null;
        }
    }

    public String getStoreID() {
        if (rbtn5.isSelected()) {
            return "S4";
        } else if (rbtn10.isSelected()) {
            return "S5";
        } else if (rbtn25.isSelected()) {
            return "S6";
        } else if (rbtn50.isSelected()) {
            return "S7";
        } else {
            return null;
        }
    }

    public void btnUpdateOnAction(ActionEvent actionEvent) {
        try {
            if (Pattern.compile("^([0-9.]{1,})$").matcher(txt5Price.getText()).matches()) {
                if (Pattern.compile("^([0-9.]{1,})$").matcher(txt10Price.getText()).matches()) {
                    if (Pattern.compile("^([0-9.]{1,})$").matcher(txt25Price.getText()).matches()) {
                        if (Pattern.compile("^([0-9.]{1,})$").matcher(txt50Price1.getText()).matches()) {

                            if (storeBO.updateUnitPrice("S4", Double.parseDouble(txt5Price.getText()))) {
                                if (storeBO.updateUnitPrice("S5", Double.parseDouble(txt10Price.getText()))) {
                                    if (storeBO.updateUnitPrice("S6", Double.parseDouble(txt25Price.getText()))) {
                                        if (storeBO.updateUnitPrice("S7", Double.parseDouble(txt50Price1.getText()))) {
                                            util.trayNotification("Update Price List", "Update Succsesfull!", NotificationType.SUCCESS);
                                        } else {
                                            util.loadUI("AlertForm");
                                            util.notification("", "Update Failed");
                                        }
                                    } else {
                                        util.loadUI("AlertForm");
                                        util.notification("", "Update Failed");
                                    }
                                } else {
                                    util.loadUI("AlertForm");
                                    util.notification("", "Update Failed");
                                }
                            } else {
                                util.loadUI("AlertForm");
                                util.notification("", "Update Failed");
                            }
                        } else {
                            util.notification("", "Please u only numbers for price");
                            util.focus(txt50Price1);
                        }
                    } else {
                        util.notification("", "Please u only numbers for price");
                        util.focus(txt25Price);
                    }
                } else {
                    util.notification("", "Please u only numbers for price");
                    util.focus(txt10Price);
                }
            } else {
                util.notification("", "Please u only numbers for price");
                util.focus(txt5Price);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void rbtnOnAction(ActionEvent actionEvent) {
        util.focus(txtQty);
    }
}
