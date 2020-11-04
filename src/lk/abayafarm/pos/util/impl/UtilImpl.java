package lk.abayafarm.pos.util.impl;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.animation.*;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
import javafx.scene.effect.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Paint;
import javafx.scene.transform.Rotate;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import lk.abayafarm.pos.bo.BoFactory;
import lk.abayafarm.pos.bo.custom.CustomerBO;
import lk.abayafarm.pos.bo.custom.EmployeeBO;
import lk.abayafarm.pos.bo.custom.SupplierOrderBO;
import lk.abayafarm.pos.util.Util;
import org.controlsfx.control.Notifications;
import tray.animations.AnimationType;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static tray.animations.AnimationType.POPUP;

public class UtilImpl implements Util {

    //loading ui
    @Override
    public void loadUI(String location, JFXButton btn) {

        loadUI(btn);
        loadUI(location);
    }

    //load ui to new scene
    public void loadUI(String location) {
        try {
            URL resource = this.getClass().getResource("/lk/abayafarm/pos/view/" + location + ".fxml");
            Parent load = FXMLLoader.load(resource);
            Scene scene = new Scene(load);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.setResizable(false);
            stage.initStyle(StageStyle.UNDECORATED);
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //close ui with btn click
    public void loadUI(JFXButton btn) {
        Stage stage = (Stage) btn.getScene().getWindow();
        stage.close();
    }

    public void initUi(String location, AnchorPane pane) throws IOException {
        pane.getChildren().clear();
        pane.getChildren().add(FXMLLoader.load(this.getClass().getResource("/lk/abayafarm/pos/view/" + location + ".fxml")));

    }

    //set time
    @Override
    public void setTime(JFXTextField textField) {
        try {
            Timeline timeline = new Timeline(new KeyFrame(Duration.ZERO, e -> {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("hh:mm:ss a");
                textField.setText(LocalDateTime.now().format(formatter));
            }), new KeyFrame(Duration.seconds(1)));
            timeline.setCycleCount(Animation.INDEFINITE);
            timeline.play();
        } catch (NullPointerException e) {
        }
    }

    //set time
    @Override
    public void setTime(Label label) {
        try {
            Timeline timeline = new Timeline(new KeyFrame(Duration.ZERO, e -> {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("hh:mm:ss a");
                label.setText(LocalDateTime.now().format(formatter));
            }), new KeyFrame(Duration.seconds(1)));
            timeline.setCycleCount(Animation.INDEFINITE);
            timeline.play();
        } catch (NullPointerException e) {
        }
    }

    //set Date
    @Override
    public void setDate(JFXTextField textField) {
        textField.setText(String.valueOf(LocalDate.now()));
    }

    @Override
    public void setToolTip(JFXButton btn, String text) {
        btn.setTooltip(new Tooltip(text));
    }

    //set Shortcut keys to button
    @Override
    public void getViewFromShortcut(KeyCode event, String location, JFXButton jfxButton) throws Exception {

        jfxButton.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent k) {
                if (k.getCode().equals(event)) {
                    try {
                        loadUI(location, jfxButton);
                    } catch (Exception exception) {
                        exception.printStackTrace();
                    }
                }
            }
        });
    }

    //set scale animation
    public void scaleAnimation(JFXButton btn, double x, double y) {

        ScaleTransition st = new ScaleTransition(Duration.millis(200), btn);
        st.setToX(x);
        st.setToY(y);
        st.play();

    }

    //set rotate transition
    public void rotateAnimation(Node node) {

        RotateTransition transition = new RotateTransition();
        transition.setAxis(Rotate.Y_AXIS);
        transition.setByAngle(360);
        transition.setCycleCount(500);
        transition.setDuration(Duration.seconds(15));
        transition.setAutoReverse(true);
        transition.setNode(node);
        transition.play();
    }

    //DashBoard Button effect
    public void btnEffectDash(Object... obj) {
        for (int i = 0; i < obj.length; i++) {
            JFXButton btn = (JFXButton) obj[i];
            btn.setOnMouseEntered(e -> {
                try {
                    scaleAnimation(btn, 1.1, 1.1);
                    btn.setStyle("-fx-background-color:#ff4d4d");

                } catch (Exception exception) {
                    exception.printStackTrace();
                }
            });
            btn.setOnMouseExited(e -> {
                btn.setStyle("-fx-background-color:linear-gradient(to left,  #6D214F 0%,  #130f40 100%)");

                scaleAnimation(btn, 1.0, 1.0);

            });
        }
    }

    //add effect to other button without color change & Effect SepiaTone
    public void btnEffect(Object... obj) {
        for (int i = 0; i < obj.length; i++) {
            JFXButton btn = (JFXButton) obj[i];
            btn.setOnMouseEntered(e -> {
                btn.setEffect(new SepiaTone());
                try {

                    scaleAnimation(btn, 1.1, 1.1);
                } catch (Exception exception) {

                    exception.printStackTrace();
                }
            });
            btn.setOnMouseExited(e -> {
                btn.setEffect(new SepiaTone(0));
                scaleAnimation(btn, 1.0, 1.0);
            });
        }
    }

    //other button effect with any color in last
    public void btnEffect(String color1, Object... obj) {
        for (int i = 0; i < obj.length; i++) {
            JFXButton btn = (JFXButton) obj[i];
            btn.setOnMouseEntered(e -> {
                btn.setStyle("-fx-background-color:#ff4d4d");
                scaleAnimation(btn, 1.1, 1.1);
                try {
                } catch (Exception exception) {
                    exception.printStackTrace();
                }
            });
            btn.setOnMouseExited(e -> {

                btn.setStyle("-fx-background-color:" + color1);
                scaleAnimation(btn, 1.0, 1.0);

            });
        }
    }

    //set Fade Transition
    public void setFadeTransition(Node node, int millis) throws IOException {
        /*FadeTransition ft = new FadeTransition(Duration.millis(3000), node);
        ft.setFromValue(1.0);
        ft.setToValue(0.1);
        ft.setCycleCount(Timeline.INDEFINITE);
        ft.setAutoReverse(true);
        ft.play();*/

        FadeTransition fade = new FadeTransition(Duration.millis(millis), node);
        fade.setFromValue(0.0);
        fade.setToValue(1.0);
        fade.play();
    }

    //set tray notification
    public void trayNotification(String title, String message, NotificationType nType) {

        TrayNotification tray = new TrayNotification();
        Stage stage;
        AnimationType type = POPUP;
        tray.setAnimationType(type);
        tray.setTitle(title);
        tray.setMessage(message);
        tray.setNotificationType(nType);

        tray.showAndDismiss(Duration.millis(1000));
    }

    //set Supplier Order ID
    SupplierOrderBO supplierOrderBO = BoFactory.getInstance().getBo(BoFactory.BoType.SUPPLIERORDER);

    public void genarateSupOrderID(Label label) {
        try {
            String orderId = supplierOrderBO.getLastOrderID();

            if (orderId != null) {
                String lastOrderId = orderId.split("[A-Z]")[1];

                if (Integer.parseInt(lastOrderId) == 9) {
                    label.setText("O010");
                } else if (Integer.parseInt(lastOrderId) < 9) {
                    String newID = "O00" + (Integer.parseInt(lastOrderId) + 1);
                    label.setText(newID);
                } else if (Integer.parseInt(lastOrderId) == 100) {
                    label.setText("O100");
                } else {
                    String newID = "O0" + (Integer.parseInt(lastOrderId) + 1);
                    label.setText(newID);
                }
            } else {
                label.setText("O001");
            }


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //set Customer  ID
    CustomerBO customerBO = BoFactory.getInstance().getBo(BoFactory.BoType.CUSTOMER);

    @Override
    public void genarateCustomerID(Label label) throws Exception {
        try {

            String customerID = customerBO.getLastCustomerID();
            if (customerID != null) {
                String lastId = customerID.split("[A-Z]")[1];

                if (Integer.parseInt(lastId) == 9) {
                    label.setText("C010");
                    return;
                } else if (Integer.parseInt(lastId) < 9) {
                    String newID = "C00" + (Integer.parseInt(lastId) + 1);
                    label.setText(newID);
                } else if (Integer.parseInt(lastId) == 100) {
                    label.setText("C100");
                } else {
                    String newID = "C0" + (Integer.parseInt(lastId) + 1);
                    label.setText(newID);
                }
            } else {
                label.setText("C001");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //set Employee  ID
    EmployeeBO employeeBO = BoFactory.getInstance().getBo(BoFactory.BoType.EMPLOYEE);

    @Override
    public void genarateEmployeeID(Label label) throws Exception {
        try {

            String employeeID = employeeBO.getLastEmployeeID();
            if (employeeID != null) {
                String lastId = employeeID.split("[A-Z]")[1];

                if (Integer.parseInt(lastId) == 9) {
                    label.setText("E010");
                    return;
                } else if (Integer.parseInt(lastId) < 9) {
                    String newID = "E00" + (Integer.parseInt(lastId) + 1);
                    label.setText(newID);
                } else if (Integer.parseInt(lastId) == 100) {
                    label.setText("E100");
                } else {
                    String newID = "E0" + (Integer.parseInt(lastId) + 1);
                    label.setText(newID);
                }
            } else {
                label.setText("E001");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void translateTransaction(int a, int b) {
        TranslateTransition slide = new TranslateTransition();
        slide.setDuration(Duration.seconds(0.4));
//        slide.setNode();
        slide.setToX(a);
        slide.play();
    }

    public void focus(JFXTextField txt) {

        txt.setFocusColor(Paint.valueOf("red"));
        txt.requestFocus();

    }
    public void notification(String title, String text) {
        javafx.scene.image.Image image = new javafx.scene.image.Image("lk\\abayafarm\\pos\\asserts\\delete_48px.png",50,50,false,false);
        Notifications notifications=Notifications.create();
        notifications.title(title);
        notifications.graphic(new ImageView(image));
        notifications.show();
        notifications.text(text);
        notifications.hideAfter(Duration.seconds(5));
        notifications.position(Pos.TOP_RIGHT);
        notifications.darkStyle();
        notifications.show();
    }

}
