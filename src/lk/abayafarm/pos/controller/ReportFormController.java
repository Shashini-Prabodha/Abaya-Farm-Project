package lk.abayafarm.pos.controller;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.scene.control.Label;
import javafx.scene.shape.Circle;
import javafx.util.Duration;
import animatefx.animation.Bounce;
import lk.abayafarm.pos.util.Util;
import lk.abayafarm.pos.util.impl.UtilImpl;

public class ReportFormController {
    public Circle cir1,cir2,cir3,cir4;
    public Label lblEggSQty,lblEggSPrice,lblEggMQty,lblEggMPrice1,lblEggLQty,lblEggLPrice2,lblChickFedd,lblGrowerFeed,lblLayerFeed,lblChick,lblGrower,lblLayer;
    public JFXButton btnDashBoard;

    Util util=new UtilImpl();
    public void initialize() {
        new Bounce(cir1).setCycleDuration(4).setCycleCount(10).setDelay(Duration.millis(500)).play();
        new Bounce(cir2).setCycleDuration(4).setCycleCount(10).setDelay(Duration.millis(1000)).play();
        new Bounce(cir3).setCycleDuration(4).setCycleCount(10).setDelay(Duration.millis(1500)).play();
        new Bounce(cir4).setCycleDuration(4).setCycleCount(10).setDelay(Duration.millis(2000)).play();
    }

    public void btnDashBoardOnAction(ActionEvent actionEvent) {
        //----
        try {
            util.loadUI("AdminDashBoardForm",btnDashBoard);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
