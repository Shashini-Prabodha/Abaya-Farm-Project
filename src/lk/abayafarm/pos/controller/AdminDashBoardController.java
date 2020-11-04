package lk.abayafarm.pos.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.util.Duration;
import lk.abayafarm.pos.util.Util;
import lk.abayafarm.pos.util.impl.UtilImpl;
import tray.notification.NotificationType;

import java.time.LocalDate;

public class AdminDashBoardController {

    public JFXButton btnEgg, btnFeed, btnCage, btnManure, btnMedicine, btnSupplier, btnCustomer, btnReport, btnEmployee, btnChicken, btnSetting, btnSwitchAcc;
    public AnchorPane root;
    public JFXTextField txtTime;
    public JFXTextField txtDate;
    public JFXButton btnHome;
    public JFXButton btnLogout;
    public JFXButton btnInfo;

    Util util = new UtilImpl();

    public void initialize() {
        try {
            System.out.println("k");
           // translateTransaction(-500, 0);
            util.getViewFromShortcut(KeyCode.F2, "ChickenManageForm", btnChicken);
            util.getViewFromShortcut(KeyCode.F3, "EggForm", btnEgg);
            util.getViewFromShortcut(KeyCode.F4, "FeedDetailForm", btnFeed);
            util.getViewFromShortcut(KeyCode.F5, "ManureForm", btnManure);
            util.getViewFromShortcut(KeyCode.F6, "MedicineDetailForm", btnMedicine);
            util.getViewFromShortcut(KeyCode.F7, "SupplierOrderForm", btnSupplier);
            util.getViewFromShortcut(KeyCode.F8, "CustomerForm", btnCustomer);
            util.getViewFromShortcut(KeyCode.F9, "EmployeeForm", btnEmployee);
            util.getViewFromShortcut(KeyCode.F10, "ReportForm", btnReport);

            util.setFadeTransition(root,300);
            util.setTime(txtTime);
            setToolTip();

            util.setDate(txtDate);
            util.btnEffectDash(btnEgg, btnFeed, btnCage, btnManure, btnMedicine, btnSupplier, btnCustomer, btnEmployee, btnReport, btnChicken);
        } catch (Exception exception) {
            exception.printStackTrace();
        }

    }

    public void translateTransaction(int a, int b) {
        TranslateTransition slide = new TranslateTransition();
        slide.setDuration(Duration.seconds(5));
        slide.setNode(root);
        slide.setToX(a);
        slide.play();

    }


    public void btnChickenOnAction(ActionEvent actionEvent) throws Exception {
        util.loadUI("ChickenManageForm", btnChicken);
    }

    public void btnEggOnAction(ActionEvent actionEvent) throws Exception {
        util.loadUI("EggForm", btnEgg);
    }

    public void btnFeedOnAction(ActionEvent actionEvent) throws Exception {
        util.loadUI("FeedDetailForm", btnFeed);
    }

    public void btnCageOnAction(ActionEvent actionEvent) throws Exception {
        util.loadUI("CageForm", btnCage);
    }

    public void btnManureOnAction(ActionEvent actionEvent) throws Exception {
        util.loadUI("ManureForm", btnManure);
    }

    public void btnMedicineOnAction(ActionEvent actionEvent) throws Exception {
        util.loadUI("MedicineDetailForm", btnMedicine);
    }

    public void btnSupplierOnAction(ActionEvent actionEvent) throws Exception {
        util.loadUI("SupplierOrderForm", btnSupplier);
    }

    public void btnCustomerOnAction(ActionEvent actionEvent) throws Exception {
        util.loadUI("CustomerForm", btnCustomer);
    }

    public void btnEmployeeOnAction(ActionEvent actionEvent) throws Exception {
        util.loadUI("EmployeeForm", btnEmployee);
    }

    public void btnReportOnAction(ActionEvent actionEvent) throws Exception {
        util.loadUI("AllReportForm", btnReport);
    }

    public void btnSettingOnAction(ActionEvent actionEvent) throws Exception {
            util.loadUI("NewAccount");
    }

    public void btnSwitchAccOnAction(ActionEvent actionEvent) throws Exception {
        util.loadUI("Login", btnSwitchAcc);
    }

    public void btnHomeOnAction(ActionEvent actionEvent) throws Exception {
        util.loadUI("BussinessReportDashBoardView", btnHome);

    }

    public void btnLogoutOnAction(ActionEvent actionEvent) {
        try {
            util.loadUI(btnLogout);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    void setToolTip(){
        util.setToolTip(btnSetting,"Add new Account");
        util.setToolTip(btnLogout,"Logout");
        util.setToolTip(btnSwitchAcc,"Switch Account");
        util.setToolTip(btnHome,"Go to Back");
        util.setToolTip(btnInfo,"info");

    }

    public void btnInfoOnAction(ActionEvent actionEvent) throws Exception {
        util.loadUI("Info");

    }
}
