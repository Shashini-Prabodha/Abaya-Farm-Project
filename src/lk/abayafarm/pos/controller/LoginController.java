package lk.abayafarm.pos.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import javafx.animation.*;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.effect.Bloom;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Paint;
import javafx.scene.transform.Rotate;
import javafx.util.Duration;
import lk.abayafarm.pos.bo.BoFactory;
import lk.abayafarm.pos.bo.custom.LoginBo;
import lk.abayafarm.pos.util.Util;
import lk.abayafarm.pos.util.impl.UtilImpl;
import tray.animations.AnimationType;
import tray.animations.PopupAnimation;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;

import java.awt.*;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.regex.Pattern;

import static tray.animations.AnimationType.*;

public class LoginController implements Initializable {
    public JFXTextField txtCashierUserName;
    public JFXPasswordField txtCashierPassword;
    public AnchorPane lyrLogin;
    public JFXButton btnLogin, btnAbout, btnCashier, imgFB, imgWhat, imgWeb;
    public ImageView imgCasheirName, imgCashierPassword;
    public Pane cashierPane;
    public AnchorPane parentPane;
    public Pane mainPane;
    public JFXTextArea txtDescription;
    public Label lblCaption;
    public Label lblVerstion;
    public JFXButton btnLinkedIn;

    Util util = new UtilImpl();
    LoginBo loginBo = BoFactory.getInstance().getBo(BoFactory.BoType.LOGIN);

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        btnCashier.setVisible(false);
        txtDescription.setVisible(false);
        lblCaption.setVisible(false);
        imgFB.setVisible(false);
        imgWeb.setVisible(false);
        imgWhat.setVisible(false);
        lblVerstion.setVisible(false);
        imgFB.setVisible(false);
        imgWeb.setVisible(false);
        imgWhat.setVisible(false);
        btnLinkedIn.setVisible(false);

        try {
            util.setFadeTransition(parentPane, 2000);

            util.btnEffect(btnCashier, btnAbout, btnLogin);
        } catch (Exception exception) {
            exception.printStackTrace();
        }

    }


    public void btnAboutOnAction(ActionEvent actionEvent) {
        aboutEvent();
    }

    private void aboutEvent() {
        txtCashierUserName.setVisible(false);
        txtCashierPassword.setVisible(false);
        imgCasheirName.setVisible(false);
        imgCashierPassword.setVisible(false);
        btnLogin.setVisible(false);
        btnCashier.setVisible(true);
        btnAbout.setVisible(false);
        btnLinkedIn.setVisible(true);

        txtDescription.setVisible(true);
        lblCaption.setVisible(true);
        imgFB.setVisible(true);
        imgWeb.setVisible(true);
        imgWhat.setVisible(true);
        lblVerstion.setVisible(true);
        imgFB.setVisible(true);
        imgWeb.setVisible(true);
        imgWhat.setVisible(true);
        translateTransaction(530, -390);

    }

    public void btnCashierOnAction(ActionEvent actionEvent) {

        cashierEvent();
    }

    private void cashierEvent() {

        txtDescription.setVisible(false);
        lblCaption.setVisible(false);
        imgFB.setVisible(false);
        imgWeb.setVisible(false);
        imgWhat.setVisible(false);
        lblVerstion.setVisible(false);
        imgFB.setVisible(false);
        imgWeb.setVisible(false);
        imgWhat.setVisible(false);
        btnLinkedIn.setVisible(false);

        btnCashier.setVisible(false);
        imgCasheirName.setVisible(true);
        imgCashierPassword.setVisible(true);
        btnLogin.setVisible(true);

        btnAbout.setVisible(true);
        txtCashierPassword.setVisible(true);
        txtCashierUserName.setVisible(true);
        translateTransaction(0, 0);

    }

    public void btnLoginOnAction(ActionEvent actionEvent) throws IOException {
        checkLogin();
    }

    public void translateTransaction(int a, int b) {
        TranslateTransition slide = new TranslateTransition();
        slide.setDuration(Duration.seconds(0.4));
        slide.setNode(lyrLogin);
        slide.setToX(a);
        slide.play();

        lyrLogin.setTranslateX(b);
    }


    public void checkLogin() {
        try {
            /*if (txtCashierUserName.getText().isEmpty()) {
                if (txtCashierPassword.getText().isEmpty()) {*/
            if (Pattern.compile("^[A-z ]{1,}$").matcher(txtCashierUserName.getText()).matches()) {
                String cashierPasswordText = NewAccount.getEncodedString(txtCashierPassword.getText());
                String roll = loginBo.getRoll(cashierPasswordText, txtCashierUserName.getText());

                if (roll == null) {
                    util.notification("Login", " Try Again...! ");
                    util.focus(txtCashierUserName);

                } else if (roll.equals("casheir")) {
                    util.loadUI("CashierForm", btnLogin);
                    util.trayNotification("Login", "Cashier " + txtCashierUserName.getText() + " Login Success !", NotificationType.SUCCESS);

                } else if (roll.equals("admin")) {
                    util.loadUI("BussinessReportDashBoardView", btnLogin);
                    util.trayNotification("Login", "Admin " + txtDescription.getText() + " Login Success !", NotificationType.SUCCESS);

                } else {
                    util.notification("Login", " Try Again...! ");
                    util.focus(txtCashierUserName);
                }

            } else {
                util.notification("Login", " User Name Empty...! ");
                util.focus(txtCashierUserName);
            }
                /*} else {
                    util.notification("Login", " Password Empty...! ");
                    txtCashierPassword.setFocusColor(Paint.valueOf("red"));
                    txtCashierPassword.requestFocus();
                }
            } else {
                util.notification("Login", " User Name Empty...! ");
                util.focus(txtCashierUserName);
            }*/
        } catch (NullPointerException e) {
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void fbHyperlinkOnAction(ActionEvent actionEvent) {
        try {
            Desktop.getDesktop().browse(new URL("https://www.facebook.com/shashiniprabodha.abeygunasekara.7").toURI());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }

    }

    public void webHyperlinkOnAction(ActionEvent actionEvent) {
        try {
            Desktop.getDesktop().browse(new URL("https://abaya-farm.business.site/").toURI());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }

    }

    public void imgWhatOnMouse(MouseEvent mouseEvent) {
        util.setToolTip(imgWhat, "041 2222 173");
    }

    public void whatsppMovesOnAction(MouseEvent mouseEvent) {
        util.setToolTip(imgWhat, "041 2222 173");
    }


    public void btnLinkedInOnAction(ActionEvent actionEvent) {
        try {
            Desktop.getDesktop().browse(new URL("https://www.linkedin.com/in/abaya-farm-4411031b9/").toURI());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }

    }
}
