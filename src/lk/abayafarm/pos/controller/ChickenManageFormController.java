package lk.abayafarm.pos.controller;

import com.jfoenix.controls.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import lk.abayafarm.pos.util.Util;
import lk.abayafarm.pos.util.impl.UtilImpl;

import java.io.IOException;

public class ChickenManageFormController {
    public JFXButton btnHome, btnAddBatch, btnUpdateBatch, btnSellBatch;
    public ToggleGroup group;
    public JFXTextArea Description;
    public JFXDatePicker clndrDate;
    public Label lblOrderID;
    public AnchorPane root;
    public ImageView imgAdmin;
    public AnchorPane mainPane;
    public Pane lblScroll1;
    public Pane lblScroll2;
    public Pane lblScroll3;
    public AnchorPane parentPane;

    Util util = new UtilImpl();

    public void initialize() {
        try {
            util.initUi("AddBatchForm", root);
            util.btnEffect(btnAddBatch, btnUpdateBatch, btnSellBatch);
            util.rotateAnimation(imgAdmin);
            setScroll(true,false,false);
            util.setFadeTransition(parentPane,300);

        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    void setScroll(boolean addbtn, boolean updtbtn, boolean sellbtn) {
        lblScroll1.setVisible(addbtn);
        lblScroll2.setVisible(updtbtn);
        lblScroll3.setVisible(sellbtn);
    }

    public void btnHomeOnAction(ActionEvent actionEvent) throws Exception {
        util.loadUI("AdminDashBoardForm", btnHome);
    }

    public void btnAddBatchOnAction(ActionEvent actionEvent) throws Exception {
        setScroll(true,false,false);
        util.initUi("AddBatchForm", root);
    }

    public void btnSellBatchOnAction(ActionEvent actionEvent) throws Exception {
        setScroll(false,false,true);
        util.initUi("SellBatchForm", root);
    }

    public void btnUpdateBatchOnAction(ActionEvent actionEvent) throws Exception {
        setScroll(false,true,false);
        util.initUi("UpdateBatchForm", root);
    }


}
