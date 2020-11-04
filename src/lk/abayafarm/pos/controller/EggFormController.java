package lk.abayafarm.pos.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.effect.Bloom;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import lk.abayafarm.pos.bo.BoFactory;
import lk.abayafarm.pos.bo.custom.AddItemToStore;
import lk.abayafarm.pos.bo.custom.CageBO;
import lk.abayafarm.pos.bo.custom.EggBO;
import lk.abayafarm.pos.bo.custom.StoreBO;
import lk.abayafarm.pos.dto.CageDTO;
import lk.abayafarm.pos.dto.EggDTO;
import lk.abayafarm.pos.dto.StoreDTO;
import lk.abayafarm.pos.util.Util;
import lk.abayafarm.pos.util.impl.UtilImpl;
import tray.notification.NotificationType;

import java.sql.Date;
import java.sql.SQLIntegrityConstraintViolationException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;


public class EggFormController {
    public JFXButton btnUpdate, btnClearS, btnClearM, btnClearL, btnHome, btnAddtoStore, btnSkipTerm;
    public JFXTextField txtQtyS, txtDamageS, txtQtyM, txtDamageM, txtQtyL, txtDamageL, txtLPrice, txtMPrice, txtSPrice;
    ;
    public JFXComboBox cmbCageId;
    public JFXDatePicker clndrDate;
    public TextField txtTerm, txtBatch;
    public Label lblTermStatus;
    public ImageView imgAdmin;
    public AnchorPane parentPane;


    Util util = new UtilImpl();
    CageBO bo = BoFactory.getInstance().getBo(BoFactory.BoType.CAGE);
    EggBO eggBO = BoFactory.getInstance().getBo(BoFactory.BoType.EGG);
    AddItemToStore addItemToStore = BoFactory.getInstance().getBo(BoFactory.BoType.ADDITEMTOSTORE);
    StoreBO storeBO = BoFactory.getInstance().getBo(BoFactory.BoType.STORE);


    public void initialize() throws Exception {

        try {
            util.rotateAnimation(imgAdmin);
            getAllCageId();
            setPriceList();
            util.btnEffect("#67e6dc", btnAddtoStore);
            util.btnEffect("#B53471", btnClearL, btnClearM, btnClearS);
            util.btnEffect("#D980FA", btnUpdate);
            util.btnEffect(" #fab1a0", btnSkipTerm);
            util.rotateAnimation(imgAdmin);
            cmbCageId.setFocusColor(Paint.valueOf("red"));
            cmbCageId.requestFocus();
            genarateOrderDate();
            util.setFadeTransition(parentPane, 300);


        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    //set term
    public int setTerm() throws Exception {
        Date date = Date.valueOf(clndrDate.getValue());
        int lastTerm = eggBO.getLastTerm((String) cmbCageId.getValue(), date);
        txtTerm.setText((lastTerm + 1) + "");
        return lastTerm;

    }

    //set Price List
    void setPriceList() {
        try {
            StoreDTO s1 = storeBO.getStore("S1");
            StoreDTO s2 = storeBO.getStore("S2");
            StoreDTO s3 = storeBO.getStore("S3");
            txtSPrice.setText(String.valueOf(s1.getUnitPrice()));
            txtMPrice.setText(String.valueOf(s2.getUnitPrice()));
            txtLPrice.setText(String.valueOf(s3.getUnitPrice()));

        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    // Update store unit Price
    public void btnUpdateOnAction(ActionEvent actionEvent) {
        try {
            if (!cmbCageId.getSelectionModel().isEmpty()) {
                if (Pattern.compile("^([0-9.]{1,})$").matcher(txtSPrice.getText()).matches()) {
                    if (Pattern.compile("^([0-9.]{1,})$").matcher(txtMPrice.getText()).matches()) {
                        if (Pattern.compile("^([0-9.]{1,})$").matcher(txtLPrice.getText()).matches()) {

                            ButtonType yes = new ButtonType("Yes", ButtonBar.ButtonData.OK_DONE);
                            ButtonType no = new ButtonType("No", ButtonBar.ButtonData.CANCEL_CLOSE);

                            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are You Sure Update Price List? ", yes, no);
                            Optional<ButtonType> result = alert.showAndWait();
                            if (result.orElse(no) == yes) {
                                if (storeBO.updateUnitPrice("S1", Double.parseDouble(txtSPrice.getText()))) {
                                    if (storeBO.updateUnitPrice("S2", Double.parseDouble(txtMPrice.getText()))) {
                                        if (storeBO.updateUnitPrice("S3", Double.parseDouble(txtLPrice.getText()))) {
                                            // new Alert(Alert.AlertType.INFORMATION, "Update Succsessful !").show();
                                            util.trayNotification("Update Price List", "Update Succsesfull!", NotificationType.SUCCESS);

                                        } else {
                                            util.loadUI("AlertForm");

                                        }
                                    } else {
                                        util.loadUI("AlertForm");

                                    }
                                } else {
                                    util.loadUI("AlertForm");
                                }
                            } else {
                            }

                        } else {
                            util.loadUI("AlertForm");
                            util.notification("Error", "Please use only Numbers");
                            util.focus(txtLPrice);
                        }
                    } else {
                        util.loadUI("AlertForm");
                        util.notification("Error", "Please use only Numbers");
                        util.focus(txtMPrice);
                    }
                } else {
                    util.loadUI("AlertForm");
                    util.notification("Error", "Please use only Numbers");
                    util.focus(txtSPrice);
                }
            } else {
                util.notification("", "Please choose cage...!");
                cmbCageId.setFocusColor(Paint.valueOf("red"));
                cmbCageId.requestFocus();
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }


    //load All cage ID to combo
    public void getAllCageId() throws Exception {
        List<CageDTO> list = bo.getCageIDListInLayer();
        for (CageDTO i : list) {
            cmbCageId.getItems().addAll(i.getCageId());
        }
    }

    public void cmbCageIdOnAction(ActionEvent actionEvent) {
        try {

            CageDTO cage = bo.getCage((String) cmbCageId.getValue());
            txtBatch.setText(cage.getBatchId());
            setTerm();
            util.focus(txtQtyS);

        } catch (NullPointerException e) {
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }


    public void btnClearSOnAction(ActionEvent actionEvent) {
        txtQtyS.setText("0");
        txtDamageS.setText("0");
    }

    public void btnClearMOnAction(ActionEvent actionEvent) {
        txtQtyM.setText("0");
        txtDamageM.setText("0");
    }

    public void btnClearLOnAction(ActionEvent actionEvent) {
        txtQtyL.setText("0");
        txtDamageL.setText("0");
    }

    public void btnHomeOnAction(ActionEvent actionEvent) throws Exception {
        util.loadUI("AdminDashBoardForm", btnHome);
    }

    public void btnAddtoStoreOnAction(ActionEvent actionEvent) throws Exception {
        addBtnSetOnAction();
    }

    private void genarateOrderDate() {

        clndrDate.setValue(LocalDate.now());
    }

    private void addBtnSetOnAction() {
        try {
            if (!cmbCageId.getSelectionModel().isEmpty()) {
                System.out.println("in Egg");
                LocalDate value = clndrDate.getValue();
                Date date = Date.valueOf(value);
                String cageId = (String) cmbCageId.getValue();
                if (Pattern.compile("^([0-9]{1,})$").matcher(txtQtyS.getText()).matches()) {
                    if (Pattern.compile("^([0-9]{1,})$").matcher(txtDamageS.getText()).matches()) {
                        if (Pattern.compile("^([0-9]{1,})$").matcher(txtQtyM.getText()).matches()) {
                            if (Pattern.compile("^([0-9]{1,})$").matcher(txtDamageM.getText()).matches()) {
                                if (Pattern.compile("^([0-9]{1,})$").matcher(txtQtyL.getText()).matches()) {
                                    if (Pattern.compile("^([0-9]{1,})$").matcher(txtDamageL.getText()).matches()) {

                                        if (Integer.parseInt(txtTerm.getText()) < 10) {

                                            ButtonType ok = new ButtonType("Yes",
                                                    ButtonBar.ButtonData.OK_DONE);
                                            ButtonType no = new ButtonType("No",
                                                    ButtonBar.ButtonData.CANCEL_CLOSE);

                                            Alert alert = new Alert(Alert.AlertType.CONFIRMATION,
                                                    "Do you want to store all the eggs you collect ?", ok, no);
                                            Optional<ButtonType> result = alert.showAndWait();
                                            if (result.orElse(no) == ok) {
                                                EggDTO eggDTOs = new EggDTO(
                                                        cageId,
                                                        "S1",
                                                        "s",
                                                        Integer.parseInt(txtQtyS.getText()),
                                                        Integer.parseInt(txtTerm.getText()),
                                                        Integer.parseInt(txtDamageS.getText()),
                                                        date
                                                );
                                                EggDTO eggDTOm = new EggDTO(
                                                        cageId,
                                                        "S2",
                                                        "m",
                                                        Integer.parseInt(txtQtyM.getText()),
                                                        Integer.parseInt(txtTerm.getText()),
                                                        Integer.parseInt(txtDamageM.getText()),
                                                        date
                                                );
                                                EggDTO eggDTOL = new EggDTO(cageId,
                                                        "S3",
                                                        "l",
                                                        Integer.parseInt(txtQtyL.getText()),
                                                        Integer.parseInt(txtTerm.getText()),
                                                        Integer.parseInt(txtDamageL.getText()),
                                                        date
                                                );

                                                if (addItemToStore.addEggstoStore(eggDTOs, "S1", updateStock("S1", txtQtyS))) {
                                                    if (addItemToStore.addEggstoStore(eggDTOm, "S2", updateStock("S2", txtQtyM))) {
                                                        if (addItemToStore.addEggstoStore(eggDTOL, "S3", updateStock("S3", txtQtyL))) {
                                                            util.trayNotification("Add Eggs to Store ", "Added Succsessfull !", NotificationType.SUCCESS);
                                                            setTerm();
                                                        } else {
                                                            util.trayNotification("Add Eggs to Store ", "Added Failed !", NotificationType.ERROR);
                                                        }
                                                    } else {
                                                        util.trayNotification("Add Eggs to Store ", "Added Failed !", NotificationType.ERROR);
                                                    }
                                                } else {
                                                    util.trayNotification("Add Eggs to Store ", "Added Failed !", NotificationType.ERROR);
                                                }

                                            } else {

                                            }

                                        } else {
                                            util.notification("Error", "All Terms Are Over....");

                                        }
                                    } else {
                                        util.notification("Error", "Please use only Numbers");
                                        util.focus(txtDamageL);
                                    }
                                } else {
                                    util.notification("Error", "Please use only Numbers");
                                    util.focus(txtQtyL);
                                }
                            } else {
                                util.notification("Error", "Please use only Numbers");
                                util.focus(txtDamageM);
                            }
                        } else {
                            util.notification("Error", "Please use only Numbers");
                            util.focus(txtQtyM);
                        }
                    } else {
                        util.notification("Error", "Please use only Numbers");
                        util.focus(txtDamageS);
                    }
                } else {
                    util.notification("Error", "Please use only Numbers");
                    util.focus(txtQtyS);
                }
            } else {
                util.notification("", "Please choose cage...!");
                cmbCageId.setFocusColor(Paint.valueOf("red"));
                cmbCageId.requestFocus();
            }
        } catch (SQLIntegrityConstraintViolationException ex) {
            new Alert(Alert.AlertType.WARNING, "Eggs are stored during this term ").show();
        } catch (Exception e1) {
            e1.printStackTrace();
        }
    }


    private int updateStock(String storeID, JFXTextField txt) throws Exception {
        return storeBO.getStore(storeID).getQtyOnStore() + Integer.parseInt(txt.getText());
    }

    void clear() {
        txtQtyS.setText("0");
        txtDamageS.setText("0");
        txtQtyL.setText("0");
        txtDamageL.setText("0");
        txtQtyM.setText("0");
        txtDamageM.setText("0");

    }

    public void btnSkipTermOnAction(ActionEvent actionEvent) throws Exception {
        System.out.println("skip term" + setTerm());
        String cageId = (String) cmbCageId.getValue();
        if (cageId != null) {

            try {
                ButtonType ok = new ButtonType("Yes",
                        ButtonBar.ButtonData.OK_DONE);
                ButtonType no = new ButtonType("No",
                        ButtonBar.ButtonData.CANCEL_CLOSE);

                Alert alert = new Alert(Alert.AlertType.CONFIRMATION,
                        "Do you want skip this term ?", ok, no);
                Optional<ButtonType> result = alert.showAndWait();
                if (result.orElse(no) == ok) {


                    int last = Integer.parseInt(txtTerm.getText());
                    txtTerm.setText((last + 1) + "");

                } else {

                }
            } catch (NumberFormatException exception) {
                exception.printStackTrace();
            }
        }
    }

}