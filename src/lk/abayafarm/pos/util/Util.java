package lk.abayafarm.pos.util;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.animation.ScaleTransition;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import org.omg.CosNaming.NamingContextExtPackage.StringNameHelper;
import tray.notification.NotificationType;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.net.URL;

public interface Util {
    public void loadUI(String location, JFXButton btn) throws Exception;

    public void loadUI(String location) throws Exception;

    public void loadUI(JFXButton btn) throws Exception;

    public void initUi(String location, AnchorPane pane) throws Exception;

    public void btnEffectDash(Object... obj) throws Exception;

    public void scaleAnimation(JFXButton btn, double x, double y) throws Exception;

    public void rotateAnimation(Node node) throws Exception;

    public void btnEffect(Object... obj) throws Exception;

    public void setFadeTransition(Node node, int millis) throws IOException;

    public void trayNotification(String title, String message, NotificationType nType);

    public void btnEffect(String color1, Object... obj);

    public void setTime(JFXTextField textField);

    public void setTime(Label label) throws Exception;

    public void setDate(JFXTextField textField);

    public void setToolTip(JFXButton btn, String text);

    public void getViewFromShortcut(KeyCode event, String location, JFXButton jfxButton) throws Exception;

    public void genarateSupOrderID(Label label) throws Exception;

    public void genarateCustomerID(Label label) throws Exception;

    public void genarateEmployeeID(Label label) throws Exception;

    public void translateTransaction(int a, int b) throws Exception;

    public void focus(JFXTextField txt);

    public void notification(String title, String text) throws Exception;


}
