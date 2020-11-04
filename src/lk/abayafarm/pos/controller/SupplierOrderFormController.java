package lk.abayafarm.pos.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXRadioButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import lk.abayafarm.pos.util.Util;
import lk.abayafarm.pos.util.impl.UtilImpl;

import java.io.IOException;

public class SupplierOrderFormController {


    public AnchorPane menuBar;
    public JFXButton btnChick, btnFeed, btnMedicine, btnEquipment, btnAddSupplier, btnAdd, btnUpdate, btnHome;
    public AnchorPane root;
    public JFXRadioButton rbtnChick;
    public ToggleGroup group;
    public ImageView imgAdmin;
    public JFXDatePicker clndrDate;
    public AnchorPane parentPane;

    Util util = new UtilImpl();

    public void initialize() {
        try {
            util.initUi("AddSupplierForm", root);
            util.rotateAnimation(imgAdmin);
            util.setFadeTransition(parentPane, 300);

        } catch (Exception exception) {
            exception.printStackTrace();
        }

    }

    public void btnChickOnAction(ActionEvent actionEvent) throws Exception {
        util.initUi("AddBatchForm", root);
    }

    public void btnFeedOnAction(ActionEvent actionEvent) throws Exception {

        util.initUi("FeedForm", root);
    }

    public void btnMedicineOnAction(ActionEvent actionEvent) throws Exception {
        util.initUi("MedicinePurchaseForm", root);
    }

    public void btnEquipmentOnAction(ActionEvent actionEvent) throws Exception {
        util.initUi("EquipmentForm", root);
    }

    public void btnAddSupplierOnAction(ActionEvent actionEvent) throws Exception {
        util.initUi("AddSupplierForm", root);
    }

    public void btnHomeOnAction(ActionEvent actionEvent) throws Exception {
        util.loadUI("AdminDashBoardForm", btnHome);
    }

}
